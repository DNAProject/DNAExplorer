package com.onchain.projects.web;

import com.onchain.projects.domain.*;
import com.onchain.projects.service.ITransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


@Controller
@RequestMapping("transaction/service")
public class TransactionController {

    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);
    @Autowired
    private ITransactionService degreeViewService;


    /**
     * 获取最新存证交易信息列表(10条)
     *
     * @author zhouq
     * @version 1.1
     */
    @RequestMapping(value = "/getRecentlyList", method = RequestMethod.GET)
    @ResponseBody
    public List<Transaction> getDegreeRecentlyList(HttpServletRequest request, HttpServletResponse response) {

        List<Transaction> transactionsList = degreeViewService.getRecentlyList();

        //交易信息类型转换和输出输入信息
        for (int i = 0; i < transactionsList.size(); i++) {
            Transaction recentlyTransaction = transactionsList.get(i);
            String assername = "";
            List<Map> output = new ArrayList<>();
            List<Map> input = new ArrayList<>();
            int txnType = recentlyTransaction.getTxType();
            String txid = recentlyTransaction.getTxId();
            //获取交易输出list
            List<TransactionOutput> outputList = degreeViewService.getTxnOutput(txid);

            for(int j = 0; j < outputList.size(); j++){
                Map<String,String> outmap = new HashMap<>();
                outmap.put("address",outputList.get(j).getScriptHash());
                outmap.put("value",outputList.get(j).getValue()+"");
                output.add(outmap);
            }

            //获取交易输入list
            List<TransactionInput> inputList = degreeViewService.getTxnInput(txid);
            List<Map> in = new ArrayList<>();
            for(int k =0; k < inputList.size(); k++){
                //根据交易输入的Prevhash和Previndex再去查找output
                Map<String,String> inmap = new HashMap<>();
                inmap.put("txid",inputList.get(k).getPrevhash());
                inmap.put("outputid",inputList.get(k).getPrevindex()+"");
                List<Map> list1 = degreeViewService.getTxnOutput2(inmap);
                if(list1.size()>0){
                    input.add(list1.get(0));
                }

            }
            recentlyTransaction.setInputs(input);
            recentlyTransaction.setOutputs(output);

            if (txnType == 208) {
                recentlyTransaction.setTxTypeDesc("智能合约");
            } else if (txnType == 128) {
                //128：转账交易or存证交易，暂时只会有存证交易
                recentlyTransaction.setTxTypeDesc("存证交易");
            } else if (txnType == 64) {
                recentlyTransaction.setTxTypeDesc("注册交易");
            } else if (txnType == 17) {
                recentlyTransaction.setTxTypeDesc("存证交易");
            } else if (txnType == 16) {
                recentlyTransaction.setTxTypeDesc("转账交易");
            } else if (txnType == 1) {
                recentlyTransaction.setTxTypeDesc("发行交易");
            } else {
            }

        }
        return transactionsList;
    }


    /**
     * 获取最新一条存证交易信息
     *
     * @author zhouq
     * @version 1.1
     */
    @RequestMapping(value = "/getRecentlyOne", method = RequestMethod.GET)
    @ResponseBody
    public Transaction getDegreRecentlyOne(HttpServletRequest request, HttpServletResponse response) {

        List<Transaction> transactionsList = degreeViewService.getRecentlyList();
        if (transactionsList.size() > 0) {
            Transaction recentlyTransaction = transactionsList.get(0);
            Integer txnType = recentlyTransaction.getTxType();
            //类型转换
            if (txnType == 208) {
                recentlyTransaction.setTxTypeDesc("智能合约");
            } else if (txnType == 128) {
                //128：转账交易or存证交易，暂时只会有存证交易
                recentlyTransaction.setTxTypeDesc("存证交易");
            } else if (txnType == 64) {
                recentlyTransaction.setTxTypeDesc("注册交易");
            } else if (txnType == 17) {
                recentlyTransaction.setTxTypeDesc("存证交易");
            } else if (txnType == 16) {
                recentlyTransaction.setTxTypeDesc("转账交易");
            } else if (txnType == 1) {
                recentlyTransaction.setTxTypeDesc("发行交易");
            } else {
            }

            return recentlyTransaction;
        } else {
            Transaction recentlyTransaction = new Transaction();
            return recentlyTransaction;
        }

    }

    /**
     * 获取utxo交易
     *
     * @author zhouq
     */
    @RequestMapping(value = "/Utxo", method = RequestMethod.POST)
    @ResponseBody
    public List<Object> getUtxo(HttpServletRequest request, HttpServletResponse response, @RequestBody TransactionOutput utxoTemp) {
        String scriptHash = utxoTemp.getScriptHash();
        logger.debug("scriptHash is {}", scriptHash);
        //System.out.println("scriptHash =" + scriptHash);
        List<TransactionOutput> utxotTxnList = degreeViewService.getTransactionsUtxo(scriptHash);

        Set<String> assetIdSet = new HashSet<>();
        for (int i = 0; i < utxotTxnList.size(); i++) {
            System.out.println(i + ": output=" + utxotTxnList.get(i).toString());
            assetIdSet.add(utxotTxnList.get(i).getAssetId());
        }

        Map<String, String> params = new HashMap<>();
        List<Object> finalout = new ArrayList<>();

        for (Iterator it = assetIdSet.iterator(); it.hasNext(); ) {
            int balance = 0;
            List<Result> resultList = new ArrayList<>();
            String assetId = it.next().toString();
            logger.debug("assetId= {}", assetId);
            System.out.println("assetId=" + assetId);

            params.put("address", scriptHash);
            params.put("assetid", assetId);
            //根据assetId和address再去获取utxo
            List<TransactionOutput> utxoTxnList2 = degreeViewService.getTransactionsUtxo2(params);
            String assetName = "";
            if (utxoTxnList2.size() > 0) {
                assetName = utxoTxnList2.get(0).getAssetName();
            }

            OutputParam outputParam = new OutputParam();
            outputParam.setAssetid(assetId);
            outputParam.setName(assetName);

            for (int j = 0; j < utxoTxnList2.size(); j++) {
                //计算余额
                balance = balance + Integer.valueOf(utxoTxnList2.get(j).getValue());
                Result result = new Result();
                result.setTxid(utxoTxnList2.get(j).getTxId());
                result.setN(utxoTxnList2.get(j).getOutputId());
                result.setState(1);
                result.setValue(String.valueOf(utxoTxnList2.get(j).getValue()));
                resultList.add(result);
            }
            outputParam.setList(resultList);
            outputParam.setBalance(String.valueOf(balance));

            finalout.add(outputParam);
        }


        return finalout;
    }

}
