package com.onchain.projects.ws.service;

import com.onchain.projects.domain.ViewTransaction;
import com.onchain.projects.service.ITransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DELL on 2017/5/23.
 */
@Service
public class SendTransactionService {

    private Logger logger = LoggerFactory.getLogger(SendTransactionService.class);

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private ITransactionService degreeViewService;

    // @SendTo("/topic/getBlock")
    public void sendTransactionInfo() throws Exception{
        int i =0;
        //作为中间交易保存，用于确认数据变化
        ViewTransaction transactionTemp = new ViewTransaction();
        Map<String,Integer> paramsMap = new HashMap<>();
        paramsMap.put("startnumber",0);
        paramsMap.put("endnumber",10);

        while(true) {
            Thread.sleep(5000);
            logger.debug("sendTransactionInfo begin ...{}",i);
            List<ViewTransaction> transactionsList = degreeViewService.getTxnList(paramsMap);
            if(i == 0) {
                transactionTemp = transactionsList.get(0);
            }

            if(i >= 1){
                //如果两笔交易txId相等，表示数据库数据没有变化，不做数据推送
                if(transactionTemp.getTxId().equals(transactionsList.get(0).getTxId())){
                    logger.debug("same transaction ,txid is {}",transactionTemp.getTxId());
                    i++;
                    continue;
                }else{
                    transactionTemp = transactionsList.get(0);
                }
            }
            //交易信息类型转换
            for (int j = 0; j < transactionsList.size(); j++) {
                ViewTransaction recentlyTransaction = transactionsList.get(j);
                int txnType = recentlyTransaction.getTxType();
                if (txnType == 208) {
                    recentlyTransaction.setTxTypeDesc("智能合约");
                }else if (txnType == 128) {
                    //128：转账交易or存证交易，暂时只会有存证交易
                    recentlyTransaction.setTxTypeDesc("存证交易");
                }else if (txnType == 64) {
                    recentlyTransaction.setTxTypeDesc("注册交易");
                }else if (txnType == 17) {
                    recentlyTransaction.setTxTypeDesc("存证交易");
                }else if (txnType == 16) {
                    recentlyTransaction.setTxTypeDesc("转账交易");
                }else if (txnType ==1) {
                    recentlyTransaction.setTxTypeDesc("发行交易");
                }else{}
            }
            //数据推送
            template.convertAndSend("/topic/getDegree",transactionsList);
            i++;
        }
    }

}
