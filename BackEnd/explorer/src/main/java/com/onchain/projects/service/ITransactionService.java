package com.onchain.projects.service;

import com.onchain.projects.domain.TransactionDetail;
import com.onchain.projects.domain.ViewTransaction;

import java.util.List;
import java.util.Map;

//import com.browser.model.Block;
public interface ITransactionService {

    List<Map> getTxnOutput(String txid);

    List<Map> getTxnInput(String txid);

    List<ViewTransaction> getTxnByHeight(Integer height);

    ViewTransaction getTxnByTxid(String txid);

    List<ViewTransaction> getTxnList(Map params);

    List<ViewTransaction> getTxnListOra(Map params);

    String getAllincomeByAddr(String address);

    String getBalanceByAddr(String address);

    List<Map> getTxnByParam(Map params);

    List<Map> getTxnByParamOra(Map params);

    List<ViewTransaction> getTxnInfo(List<String> params);

    List<TransactionDetail> getTxnDetailById(List<Map> prams);

    List<Map> getBankIssuAsset(String[] params);

    List<Map<String,String>> getBankAssetFlow(Map<String,Object> paramsMap);

    List<Map<String,String>> getAssetFlow(String txid);

    Map<String,String> getBank2CompanyFlow(String txid);

}
