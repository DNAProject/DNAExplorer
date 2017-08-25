package com.onchain.projects.dao;

import com.onchain.projects.domain.TransactionDetail;
import com.onchain.projects.domain.TransactionOutput;
import com.onchain.projects.domain.ViewTransaction;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class TransactionDao {

    @Autowired
    private SqlSession sqlSession;

    public List<Map> getTxnOutput(String txid){
        return sqlSession.selectList("TxnDao.getTxnOutput", txid);
    }

    public List<Map> getTxnInput(String txid){
        return sqlSession.selectList("TxnDao.getTxnInput", txid);
    }

    public List<ViewTransaction> getTxnByHeight(Integer height) {
        return sqlSession.selectList("TxnDao.getTxnByHeight",height);
    }

    public ViewTransaction getTxnByTxid(String txid) {
        return sqlSession.selectOne("TxnDao.getTxnByTxid",txid);
    }

    public List<ViewTransaction> getTxnList(Map params) {
        return sqlSession.selectList("TxnDao.getTxnList",params);
    }

    public List<ViewTransaction> getTxnListOra(Map params) {
        return sqlSession.selectList("TxnDao.getTxnListOra", params);
    }

    public String getAllincomeByAddr(String address) {
        return sqlSession.selectOne("TxnDao.getAllincomeByAddr",address);
    }

    public String getBalanceByAddr(String address) {
        return sqlSession.selectOne("TxnDao.getBalanceByAddr",address);
    }

    public List<Map> getTxnByParam(Map params) {
        return sqlSession.selectList("TxnDao.getTxnByParam",params);
    }

    public List<Map> getTxnByParamOra(Map params) {
        return sqlSession.selectList("TxnDao.getTxnByParamOra",params);
    }

    public List<ViewTransaction> getTxnInfo(List<String> params) {
        return sqlSession.selectList("TxnDao.getTxnInfo",params);
    }

    public List<TransactionDetail> getTxnDetailById(List<Map> params) {
        return sqlSession.selectList("TxnDao.getTxnDetailById",params);
    }

    public List<Map> getBankIssuAsset(String[] params) {
        return sqlSession.selectList("TxnDao.getBankIssuAsset",params);
    }

    public List<Map<String,String>> getBankAssetFlow(Map<String,Object> paramsMap) {
        return sqlSession.selectList("TxnDao.getBankAssetFlow", paramsMap);
    }

    public List<Map<String,String>> getAssetFlow(String txid) {
        return sqlSession.selectList("TxnDao.getAssetFlow", txid);
    }

    public Map<String,String> getBank2CompanyFlow(String txid) {
        return sqlSession.selectOne("TxnDao.getBank2CompanyFlow",txid);
    }

}
