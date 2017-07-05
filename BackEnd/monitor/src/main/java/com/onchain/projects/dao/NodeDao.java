package com.onchain.projects.dao;

import com.onchain.projects.domain.Node;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class NodeDao {

    @Autowired
    private SqlSession sqlSession;


    public List<Node> getNodeList(String param) {
        return sqlSession.selectList("NodeDao.getNodeList", param);
    }


    public List<Node> getAllNodeList() {
        return sqlSession.selectList("NodeDao.getAllNodeList");
    }


    public int getNodeNumByFlag(String rpcFlag) {
        return sqlSession.selectOne("NodeDao.getNodeNumByFlag", rpcFlag);
    }


    public List<String> getNodeUrl() {
        return sqlSession.selectList("NodeDao.getNodeUrl");
    }

    public Integer updateNodeInfo(Map paramMap) {
        return sqlSession.update("NodeDao.updateNodeInfo", paramMap);
    }

    public List<Node> getOneNode(String node) {
        return sqlSession.selectList("NodeDao.getOneNode", node);
    }

}
