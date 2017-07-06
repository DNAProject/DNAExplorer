package com.onchain.projects.service;

import com.onchain.projects.domain.Node;
import com.onchain.projects.utils.common.PagedResult;

import java.util.List;
import java.util.Map;

public interface INodeService {

    PagedResult<Node> getAllNodeList(String param, Integer pageNumber, Integer pageSize);

    List<Node> getAllNodeList();

    Integer getNodeNumByFlag(String blockFlag);

    List<String> getNodeUrl();

    Integer updateNodeInfo(Map<String, String> paramMap);

    List<Node> getOneNode(String node);


}
