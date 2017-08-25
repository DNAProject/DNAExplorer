package com.onchain.projects.dao;

import com.onchain.projects.domain.Machine;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ZhouQ on 2017/6/1.
 */
@Repository
public class MachineDao {
    @Autowired
    private SqlSession sqlSession;

    public Integer storeMachineInfo(Machine machine) {
        return sqlSession.insert("MachineDao.storeMachineInfo", machine);
    }

    public Integer delMachineInfo(String ip) {
        return sqlSession.delete("MachineDao.delMachineInfo", ip);
    }

    public Integer delMachineInfoOra(String ip) {
        return sqlSession.delete("MachineDao.delMachineInfoOra", ip);
    }

    public Machine getNewestMachineInfo(String ip) {
        return sqlSession.selectOne("MachineDao.getNewestMachineInfo", ip);
    }

    public List<Machine> getMachineInfo(String ip) {
        return sqlSession.selectList("MachineDao.getMachineInfo", ip);
    }

    public List<Machine> getMachineInfoOra(String ip) {
        return sqlSession.selectList("MachineDao.getMachineInfoOra", ip);
    }

    public Integer getMachineInfoNum(String ip) {
        return sqlSession.selectOne("MachineDao.getMachineInfoNum", ip);
    }
}
