package com.onchain.projects.dao;

import com.onchain.projects.domain.Block;
import com.onchain.projects.domain.Summary;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BlockDao {

    @Autowired
    private SqlSession sqlSession;

    public Summary getSummaryInfo() {
        return sqlSession.selectOne("BlockDao.getSummaryInfo");
    }

    public List<Block> getBlockList(Map params) {
        return  sqlSession.selectList("BlockDao.getBlockList",params);
    }

    public List<Block> getBlockListOra(Map params) {
        return  sqlSession.selectList("BlockDao.getBlockListOra",params);
    }

    public Block getBlockByHeight(Integer height) {
        return sqlSession.selectOne("BlockDao.getBlockByHeight",height);
    }

}
