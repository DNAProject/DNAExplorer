package com.onchain.projects.service;

import com.onchain.projects.domain.Block;
import com.onchain.projects.domain.Summary;

import java.util.List;


public interface IBlockService {

    List<Block> getBlocks();

    Summary getSummaryInfo();

}
