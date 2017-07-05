package com.onchain.projects.dao;

import com.onchain.projects.domain.Block;
import com.onchain.projects.domain.Summary;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BlockDao {

    @Autowired
    private SqlSession sqlSession;

    public List<Block> getBlocks() {
        // TODO Auto-generated method stub
        return sqlSession.selectList("BlockDao.getBlocks");
    }

    public Summary getSummaryInfo() {
        return sqlSession.selectOne("BlockDao.getSummaryInfo");
    }


}
