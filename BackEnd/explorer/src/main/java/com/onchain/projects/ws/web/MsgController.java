package com.onchain.projects.ws.web;

import com.onchain.projects.ws.service.SendTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by DELL on 2017/5/23.
 */
@Controller
public class MsgController {

    @Autowired
    private SendTransactionService ws;

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    @ResponseBody
    public String hello() throws Exception {
        ws.sendTransactionInfo();
        return "hello";
    }


}
