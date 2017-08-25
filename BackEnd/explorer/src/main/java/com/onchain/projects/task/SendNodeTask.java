package com.onchain.projects.task;

import com.onchain.projects.ws.service.SendNodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by ZhouQ on 2017/6/5.
 */
@Component("SendNodeTask")
@Scope("prototype")
public class SendNodeTask extends Thread {
    private Logger logger = LoggerFactory.getLogger(MachineInfoTask.class);

    @Autowired
    private SendNodeService sendNodeService;

    @Override
    public void run() {
        logger.debug("SendNodeTask begin...");
        try {
            sendNodeService.sendNodeInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
