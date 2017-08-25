package com.onchain.projects;

import com.onchain.projects.service.INodeService;
import com.onchain.projects.task.MachineInfoTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhouQ on 2017/6/1.
 */
@Component
public class StartTask {

    private Logger logger = LoggerFactory.getLogger(StartTask.class);
    @Autowired
    private INodeService nodeService;
    @Autowired
    private ApplicationContextProvider applicationContextProvider;

    //定义在构造方法完毕后，执行这个初始化方法

    @PostConstruct
    public void init() {
        //获取需要监控的节点url
        List<String> urlList = nodeService.getNodeUrl();
        List<String> realUrlList = new ArrayList<>();
        String tempUrl = "";
        for (int j = 0; j < urlList.size(); j++) {
            tempUrl = urlList.get(j);
            tempUrl = tempUrl.substring(0, tempUrl.indexOf(":"));
            logger.debug("url{} is:{}", j, tempUrl);
            realUrlList.add(tempUrl);
        }
        logger.debug("total machineinfo_process have：{}", realUrlList.size());
        for (int i = 0; i < realUrlList.size(); i++) {

            MachineInfoTask moniotrTask = applicationContextProvider.getBean("mTask", MachineInfoTask.class);
            moniotrTask.setIp(realUrlList.get(i));
            moniotrTask.start();
            logger.debug("the number{} machineinfo_process begin...", i);

        }

/*        SendTransactionTask sendTransactionTask = ApplicationContextProvider.getBean("SendTransactionTask", SendTransactionTask.class);
        sendTransactionTask.start();
        logger.debug("交易消息推送进程启动...");

        SendBlockTask sendBlockTask = ApplicationContextProvider.getBean("SendBlockTask", SendBlockTask.class);
        sendBlockTask.start();
        logger.debug("区块消息推送进程启动...");

        SendNodeTask sendNodeTask = ApplicationContextProvider.getBean("SendNodeTask", SendNodeTask.class);
        sendNodeTask.start();
        logger.debug("节点消息推送进程启动...");

        SendSummaryTask sendSummaryTask = ApplicationContextProvider.getBean("SendSummaryTask", SendSummaryTask.class);
        sendSummaryTask.start();
        logger.debug("汇总消息推送进程启动...");*/
    }


}