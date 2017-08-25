package com.onchain.projects.web;

import com.onchain.projects.domain.Machine;
import com.onchain.projects.service.IMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhouQ on 2017/5/27.
 */
@Controller
@RequestMapping(value = "/machine/service", method = RequestMethod.GET)
public class MachineServerController {

    @Value("${db_mode}")
    private String dbMode;
    @Autowired
    private IMachineService machineService;

    @RequestMapping(value = "/latest/{ipinfo}")
    @ResponseBody
    public Machine getServerInfo(HttpServletRequest request, HttpServletResponse response, @PathVariable("ipinfo") String ipinfo) {

//        System.out.println("realIp is "+ip);
        //传入ip格式为：127_0_0_1_20338
        String realIp = ipinfo.replace('_', '.');
        realIp = realIp.substring(0, realIp.lastIndexOf("."));

        List<Machine> machineList = new ArrayList<>();

        Machine machine = new Machine();
        if("oracle".equals(this.dbMode)){
            machineList = machineService.getMachineInfoOra(realIp);
        }else if("mysql".equals(this.dbMode)){
            machineList = machineService.getMachineInfo(realIp);
        }

        if (machineList.size() > 0) {
            machine = machineList.get(0);
        }
        return machine;
    }

    @RequestMapping(value = "/all/{ipinfo}", method = RequestMethod.GET)
    @ResponseBody
    public List<Machine> getNewestServerInfo(HttpServletRequest request, HttpServletResponse response, @PathVariable("ipinfo") String ipinfo) {
        //传入ip格式为：127_0_0_1_20338
        String realIp = ipinfo.replace('_', '.');
        realIp = realIp.substring(0, realIp.lastIndexOf("."));

        List<Machine> machineList = new ArrayList<>();

        if("oracle".equals(this.dbMode)){
            machineList = machineService.getMachineInfoOra(realIp);
        }else if("mysql".equals(this.dbMode)){
            machineList = machineService.getMachineInfo(realIp);
        }
        return machineList;

    }


}
