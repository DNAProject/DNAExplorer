package com.onchain.projects.task;

import com.onchain.projects.ws.service.SendSummaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by ZhouQ on 2017/6/5.
 */
@Component("SendSummaryTask")
@Scope("prototype")
public class SendSummaryTask extends Thread {
    private Logger logger = LoggerFactory.getLogger(MachineInfoTask.class);

    @Autowired
    private SendSummaryService sendSummaryService;

    @Override
    public void run() {
        logger.debug("SendSummaryTask begin...");
        try {
            sendSummaryService.sendSummaryInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
