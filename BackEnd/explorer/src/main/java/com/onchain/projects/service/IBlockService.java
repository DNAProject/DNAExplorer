package com.onchain.projects.service;

import com.onchain.projects.domain.Block;
import com.onchain.projects.domain.Summary;

import java.util.List;
import java.util.Map;


public interface IBlockService {

    Summary getSummaryInfo();

    List<Block> getBlockList(Map params);

    List<Block> getBlockListOra(Map params);

    Block getBlockByHeight(Integer height);

}
