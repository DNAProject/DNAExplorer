package com.onchain.projects.ws.service;

import com.onchain.projects.domain.Summary;
import com.onchain.projects.service.IBlockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by ZhouQ on 2017/6/5.
 */
@Service
public class SendSummaryService {

    private Logger logger = LoggerFactory.getLogger(SendBlockService.class);

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private IBlockService blockService;

    // @SendTo("/topic/getBlock")
    public void sendSummaryInfo()throws Exception{

        while(true){
            Thread.sleep(5000);
            Summary summary = blockService.getSummaryInfo();
            template.convertAndSend("/topic/getSummary",summary);
        }


    }
}
