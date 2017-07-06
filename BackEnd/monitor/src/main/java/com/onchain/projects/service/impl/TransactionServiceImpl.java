package com.onchain.projects.service.impl;

import com.onchain.projects.dao.TransactionDao;
import com.onchain.projects.domain.Transaction;
import com.onchain.projects.domain.TransactionInput;
import com.onchain.projects.domain.TransactionOutput;
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
    public List<Transaction> getRecentlyList() {
        // TODO Auto-generated method stub
        return dao.getRecentlyList();
    }

    @Override
    public List<TransactionOutput> getTransactionsUtxo(String address) {
        return dao.getTransactionsUtxo(address);
    }

    @Override
    public List<TransactionOutput> getTransactionsUtxo2(Map params) {
        return dao.getTransactionsUtxo2(params);
    }

    @Override
    public List<TransactionOutput> getTxnOutput(String txid) {
        return dao.getTxnOutput(txid);
    }

    @Override
    public List<TransactionInput> getTxnInput(String txid) {
        return dao.getTxnInput(txid);
    }

    @Override
    public List<Map> getTxnOutput2(Map param) {
        return dao.getTxnOutput2(param);
    }
}
