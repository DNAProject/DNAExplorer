package com.onchain.projects.dao;

import com.onchain.projects.domain.Transaction;
import com.onchain.projects.domain.TransactionInput;
import com.onchain.projects.domain.TransactionOutput;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class TransactionDao {

    @Autowired
    private SqlSession sqlSession;

    public List<Transaction> getRecentlyList() {
        // TODO Auto-generated method stub
        return sqlSession.selectList("DegreeViewDao.getRecentlyList");
    }

    public List<TransactionOutput> getTransactionsUtxo(String address) {
        return sqlSession.selectList("DegreeViewDao.getTransactionsUtxo", address);
    }

    public List<TransactionOutput> getTransactionsUtxo2(Map<String, String> params) {
        return sqlSession.selectList("DegreeViewDao.getTransactionsUtxo2", params);
    }

    public List<TransactionOutput> getTxnOutput(String txid){
        return sqlSession.selectList("DegreeViewDao.getTxnOutput", txid);
    }

    public List<TransactionInput> getTxnInput(String txid){
        return sqlSession.selectList("DegreeViewDao.getTxnInput", txid);
    }

    public List<Map> getTxnOutput2(Map param){
        return sqlSession.selectList("DegreeViewDao.getTxnOutput2", param);
    }

}
