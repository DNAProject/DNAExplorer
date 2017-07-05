package com.onchain.projects.service.impl;

import com.onchain.projects.dao.MachineDao;
import com.onchain.projects.domain.Machine;
import com.onchain.projects.service.IMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ZhouQ on 2017/6/1.
 */
@Service("IMachineService")
public class MachineServiceImpl implements IMachineService {

    @Autowired
    private MachineDao machineDao;

    @Override
    public Integer storeMachineInfo(Machine machine) {
        return machineDao.storeMachineInfo(machine);
    }

    @Override
    public Integer delMachineInfo(String ip) {
        return machineDao.delMachineInfo(ip);
    }

    @Override
    public List<Machine> getMachineInfo(String ip) {
        return machineDao.getMachineInfo(ip);
    }
}
