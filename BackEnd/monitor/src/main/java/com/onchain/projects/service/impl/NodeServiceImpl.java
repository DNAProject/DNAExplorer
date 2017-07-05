package com.onchain.projects.service.impl;

import com.github.pagehelper.PageHelper;
import com.onchain.projects.dao.NodeDao;
import com.onchain.projects.domain.Node;
import com.onchain.projects.service.INodeService;
import com.onchain.projects.utils.common.BeanUtil;
import com.onchain.projects.utils.common.PagedResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("INodeService")
public class NodeServiceImpl implements INodeService {
    @Autowired
    private NodeDao nodeDao;


    @Override
    public PagedResult<Node> getAllNodeList(String param, Integer pageNo, Integer pageSize) {
        // TODO Auto-generated method stub
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 10 : pageSize;
        PageHelper.startPage(pageNo, pageSize);  //startPage是告诉拦截器说我要开始分页了。分页参数是这两个。
        return BeanUtil.toPagedResult(nodeDao.getNodeList(param));
    }


    @Override
    public List<Node> getAllNodeList() {
        // TODO Auto-generated method stub
        return nodeDao.getAllNodeList();
    }

    @Override
    public Integer getNodeNumByFlag(String blockFlag) {
        // TODO Auto-generated method stub
        return nodeDao.getNodeNumByFlag(blockFlag);
    }

    @Override
    public List<String> getNodeUrl() {
        return nodeDao.getNodeUrl();
    }

    @Override
    public Integer updateNodeInfo(Map<String, String> paramMap) {
        return nodeDao.updateNodeInfo(paramMap);
    }

    @Override
    public List<Node> getOneNode(String node) {
        return nodeDao.getOneNode(node);
    }
}
