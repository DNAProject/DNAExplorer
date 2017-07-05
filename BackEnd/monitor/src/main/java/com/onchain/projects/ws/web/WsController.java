package com.onchain.projects.ws.web;

import com.onchain.projects.ws.web.param.RequestMessage;
import com.onchain.projects.ws.web.param.ResponseMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


/**
 * Created by DELL on 2017/5/23.
 */
@Controller
public class WsController {

    @MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public ResponseMessage say(RequestMessage message) {
        System.out.println("==================");
        System.out.println(message.getMessage());
        return new ResponseMessage("welcome," + message.getMessage() +" !");
    }
}
