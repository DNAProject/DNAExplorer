package com.onchain.projects.service.impl;

import com.onchain.projects.dao.BlockDao;
import com.onchain.projects.domain.Block;
import com.onchain.projects.domain.Summary;
import com.onchain.projects.service.IBlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("IBlockService")
public class BlocksServiceImpl implements IBlockService {
    @Autowired
    private BlockDao blockDao;

    @Override
    public List<Block> getBlocks() {
        // TODO Auto-generated method stub
        return blockDao.getBlocks();
    }

    @Override
    public Summary getSummaryInfo() {
        return blockDao.getSummaryInfo();
    }

}
