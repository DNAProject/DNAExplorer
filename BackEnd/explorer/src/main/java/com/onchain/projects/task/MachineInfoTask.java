package com.onchain.projects.task;

import com.alibaba.fastjson.JSON;
import com.onchain.projects.domain.Machine;
import com.onchain.projects.service.IMachineService;
import com.onchain.projects.service.INodeService;
import com.onchain.projects.web.param.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * Created by ZhouQ on 2017/6/1.
 */
@Component("mTask")
@Scope("prototype")
public class MachineInfoTask extends Thread {

    private Logger logger = LoggerFactory.getLogger(MachineInfoTask.class);
    //ip参数封装
    private String ip;
    @Autowired
    private IMachineService machineService;
    @Autowired
    private INodeService nodeService;

    @Value("${machine_service_http}")
    private String serviceHttp;

    @Value("${machine_service_url}")
    private String serviceUrl;
    @Value("${db_mode}")
    private String dbMode;

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public void run() {
        logger.debug("{}. MachineInfoTask start run...", Thread.currentThread().getName());

        int i = 0;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //设置时区
        TimeZone tz = TimeZone.getTimeZone("GMT+08:00");
        formatter.setTimeZone(tz);
        String urlt = this.serviceHttp + "://" + this.ip + ":" + this.serviceUrl;
        URL url = null;
        HttpURLConnection connection = null;

        while (true) {
            try {
                url = new URL(urlt);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);

                //设置超时时间
                connection.setConnectTimeout(20000);
                connection.setReadTimeout(20000);
                connection.setRequestProperty("Content-type", "application/json");

                Map<String, String> paramsMap = new HashMap<>();
                paramsMap.put("id", "1");
                paramsMap.put("method", "machine");
                //  paramsMap.put("params",null);
                String params = JSON.toJSONString(paramsMap);

                // 写入参数
                OutputStream out = connection.getOutputStream();
                out.write(params.getBytes("UTF-8"));
                out.flush();

                //获取输出
                StringBuffer sb = new StringBuffer();
                String readLine = "";
                BufferedReader responseReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                while ((readLine = responseReader.readLine()) != null) {
                    sb.append(readLine);
                }
                logger.debug("machine response is :{}", sb.toString());

                //出参json解析
                OutputInfo outputInfo = JSON.parseObject(sb.toString(), OutputInfo.class);
                if ("0".equals(outputInfo.getErrorcode())) {
                    //只保存当前时间一个小时内的信息
                    if (i > 360 && machineService.getMachineInfoNum(this.ip) > 360) {
                        if ("oracle".equals(this.dbMode)) {
                            int code = machineService.delMachineInfoOra(this.ip);
                        } else if ("mysql".equals(this.dbMode)) {
                            int code = machineService.delMachineInfo(this.ip);
                        }
                    }
                    ResultInfo resultInfo = JSON.parseObject(outputInfo.getResult(), ResultInfo.class);
                    //系统信息
                    Host host = JSON.parseObject(resultInfo.getHost(), Host.class);
                    String systemInfo = host.getOS();
                    //node开始运行时间,runningtime其实获取的是createtime
                    String runningTime = "";
                    Proc proc = JSON.parseObject(resultInfo.getProc(), Proc.class);
                    String createTimeStr = proc.getCreateTime();
                    if ("0".equals(createTimeStr)) {
                        runningTime = new String("unknown".getBytes("UTF-8"), "UTF-8");
                    } else {
                        long createTime = Long.parseLong(createTimeStr) * 1000L;
                        Date date = new Date(createTime);
                        runningTime = formatter.format(date);
                    }

                    //cpu使用率
                    Cpu cpu = JSON.parseObject(resultInfo.getCpu(), Cpu.class);
                    String cpuUsedPer = cpu.getUsedPercent();
                    if (cpuUsedPer.indexOf(".") != -1) {
                        cpuUsedPer = cpuUsedPer.length() > 5 ? cpuUsedPer.substring(0, cpuUsedPer.indexOf(".") + 3) : cpuUsedPer;
                    }
                    //内存使用率
                    Mem memory = JSON.parseObject(resultInfo.getMem(), Mem.class);
                    String memUsedPer = memory.getUsedPercent();
                    if (memUsedPer.indexOf(".") != -1) {
                        memUsedPer = memUsedPer.length() > 5 ? memUsedPer.substring(0, memUsedPer.indexOf(".") + 3) : memUsedPer;
                    }
                    //硬盘使用率
                    Dis disk = JSON.parseObject(resultInfo.getDis(), Dis.class);
                    String distUsedPer = disk.getUsedPercent();
                    if (distUsedPer.indexOf(".") != -1) {
                        distUsedPer = distUsedPer.length() > 5 ? distUsedPer.substring(0, distUsedPer.indexOf(".") + 3) : distUsedPer;
                    }
                    //获取当前时间
                    Date curDate = new Date(System.currentTimeMillis());
                    String dateStr = formatter.format(curDate);
                    //封装数据
                    Machine machine = new Machine();
                    machine.setCpuUsedPer(cpuUsedPer);
                    machine.setMemUsedPer(memUsedPer);
                    machine.setDisUsedPer(distUsedPer);
                    machine.setDate(dateStr);
                    machine.setNode_ip(this.ip);
                    int code1 = machineService.storeMachineInfo(machine);
                    //封装数据
                    Map<String, String> nodeInfo = new HashMap<>();
                    nodeInfo.put("ip", this.ip + ":" + "%");
                    nodeInfo.put("systemInfo", systemInfo);
                    nodeInfo.put("runningTime", runningTime);
                    //前20分钟还可以插入
                    if (i < 120) {
                        int code2 = nodeService.updateNodeInfo(nodeInfo);
                    }
                } else {
                    logger.debug("machine task response error,errorcode is {}", outputInfo.getErrorcode());
                }
            } catch (SocketTimeoutException e) {
                logger.debug("{} can not get machine info now....network reason", Thread.currentThread().getName());
            } catch (java.net.ConnectException e) {
                logger.debug("{} can not get machine info now....network reason", Thread.currentThread().getName());
            } catch (Exception e) {
                logger.debug("get machineinfo error....{}",e);
                e.printStackTrace();
            } finally {
                // 关闭链接
                if (connection != null) {
                    connection.disconnect();
                }
            }
            //只保存最近一小时的数据，循环计数用于删除旧数据
            i++;

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}