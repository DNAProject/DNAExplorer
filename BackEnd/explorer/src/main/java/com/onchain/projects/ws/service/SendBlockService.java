package com.onchain.projects.ws.service;

import com.onchain.projects.domain.Block;
import com.onchain.projects.web.BlockController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ZhouQ on 2017/6/3.
 */
@Service
public class SendBlockService {

    private Logger logger = LoggerFactory.getLogger(SendBlockService.class);

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private BlockController blockController;

    // @SendTo("/topic/getBlock")
    public void sendBlockInfo()throws Exception{
        logger.debug("sendBlockInfo begin");
        int i =0;
        //中间变量，用于判断数据库数据是否有变化
        Block blockTemp = new Block();
        Map<String,Integer> param = new HashMap<>();
        param.put("startnumber",0);
        param.put("endnumber",10);
        while(true){
            logger.debug("sendBlockInfo begin....{}",i);
            Thread.sleep(5000);
            List<Block> blockList = blockController.getBlockList(param);
            if(i == 0){
                blockTemp = blockList.get(0);
            }
            if(i >= 1){
                if(blockTemp.getHeight().equals(blockList.get(0).getHeight())){
                    logger.debug("same block， height is {}",blockTemp.getHeight());
                    i++;
                    continue;
                }else{
                    blockTemp = blockList.get(0);
                }
            }
            String blocktest = "blocks test date";
            template.convertAndSend("/topic/getBlocks",blocktest);
            i++;

        }


    }
}
