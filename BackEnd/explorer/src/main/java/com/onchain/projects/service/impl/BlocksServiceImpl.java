package com.onchain.projects.service.impl;

import com.onchain.projects.dao.BlockDao;
import com.onchain.projects.domain.Block;
import com.onchain.projects.domain.Summary;
import com.onchain.projects.service.IBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("IBlockService")
public class BlocksServiceImpl implements IBlockService {
    @Autowired
    private BlockDao blockDao;

    @Override
    public Summary getSummaryInfo() {
        return blockDao.getSummaryInfo();
    }

    @Override
    public List<Block> getBlockList(Map params) {
        return blockDao.getBlockList(params);
    }

    @Override
    public List<Block> getBlockListOra(Map params) {
        return blockDao.getBlockListOra(params);
    }

    @Override
    public Block getBlockByHeight(Integer height) {
        return blockDao.getBlockByHeight(height);
    }
}
