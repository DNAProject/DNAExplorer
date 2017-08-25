package com.onchain.projects.task;

import com.onchain.projects.ws.service.SendTransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by ZhouQ on 2017/6/5.
 */
@Component("SendTransactionTask")
@Scope("prototype")
public class SendTransactionTask extends Thread {

    private Logger logger = LoggerFactory.getLogger(MachineInfoTask.class);

    @Autowired
    private SendTransactionService sendTransactionService;

    @Override
    public void run() {
        logger.debug("SendTransactionTask begin...");
        try {
            sendTransactionService.sendTransactionInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
