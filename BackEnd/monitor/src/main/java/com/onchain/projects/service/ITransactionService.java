package com.onchain.projects.service;

import com.onchain.projects.domain.Transaction;
import com.onchain.projects.domain.TransactionInput;
import com.onchain.projects.domain.TransactionOutput;

import java.util.List;
import java.util.Map;

//import com.browser.model.Block;
public interface ITransactionService {

    List<Transaction> getRecentlyList();

    List<TransactionOutput> getTransactionsUtxo(String address);

    List<TransactionOutput> getTransactionsUtxo2(Map params);

    List<TransactionOutput> getTxnOutput(String txid);

    List<TransactionInput> getTxnInput(String txid);

    List<Map> getTxnOutput2(Map param);

}
