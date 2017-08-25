package com.onchain.projects.service.impl;

import com.onchain.projects.dao.TransactionDao;
import com.onchain.projects.domain.TransactionDetail;
import com.onchain.projects.domain.ViewTransaction;
import com.onchain.projects.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("IDegreeViewService")
/*@Transactional*/
public class TransactionServiceImpl implements ITransactionService {

    @Autowired
    private TransactionDao dao;

    @Override
    public List<Map> getTxnOutput(String txid) {
        return dao.getTxnOutput(txid);
    }

    @Override
    public List<Map> getTxnInput(String txid) {
        return dao.getTxnInput(txid);
    }

    @Override
    public List<ViewTransaction> getTxnByHeight(Integer height) {
        return dao.getTxnByHeight(height);
    }

    @Override
    public ViewTransaction getTxnByTxid(String txid) {
        return dao.getTxnByTxid(txid);
    }

    @Override
    public List<ViewTransaction> getTxnList(Map params) {
        return dao.getTxnList(params);
    }

    @Override
    public List<ViewTransaction> getTxnListOra(Map params) {
        return dao.getTxnListOra(params);
    }

    @Override
    public String getAllincomeByAddr(String address) {
        return dao.getAllincomeByAddr(address);
    }

    @Override
    public String getBalanceByAddr(String address) {
        return dao.getBalanceByAddr(address);
    }

    @Override
    public List<Map> getTxnByParam(Map params) {
        return dao.getTxnByParam(params);
    }

    @Override
    public List<Map> getTxnByParamOra(Map params) {
        return dao.getTxnByParamOra(params);
    }

    @Override
    public List<ViewTransaction> getTxnInfo(List<String> params) {
        return dao.getTxnInfo(params);
    }

    @Override
    public List<TransactionDetail> getTxnDetailById(List<Map> params) {
        return dao.getTxnDetailById(params);
    }

    @Override
    public List<Map> getBankIssuAsset(String[] params) {
        return dao.getBankIssuAsset(params);
    }

    @Override
    public List<Map<String,String>> getBankAssetFlow(Map<String,Object> paramsMap) {
        return dao.getBankAssetFlow(paramsMap);
    }

    @Override
    public List<Map<String, String>> getAssetFlow(String txid) {
        return dao.getAssetFlow(txid);
    }

    @Override
    public Map<String,String> getBank2CompanyFlow(String txid) {
        return dao.getBank2CompanyFlow(txid);
    }
}
