package com.onchain.projects.web;

import com.onchain.projects.domain.Summary;
import com.onchain.projects.domain.ViewTransaction;
import com.onchain.projects.service.IBlockService;
import com.onchain.projects.service.ITransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("transaction/service")
public class TransactionController {

    private String CLASS_NAME = this.getClass().getSimpleName();

    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);
    @Autowired
    private ITransactionService transactionService;
    @Autowired
    private IBlockService blockService;

    @Value("${db_mode}")
    private String dbMode;

    @Value("${nsh_account}")
    private String NshAccount;

    private String currentMethod() {
        return new Exception("").getStackTrace()[1].getMethodName();
    }

    /**
     * 获取最新交易信息列表(10条)，包含输入输出详细信息
     *
     * @author zhouq
     * @version 1.1
     */
    @RequestMapping(value = "/getRecentlyList", method = RequestMethod.GET)
    @ResponseBody
    public List<ViewTransaction> getDegreeRecentlyList(HttpServletRequest request, HttpServletResponse response) {

        logger.debug("{}.{} start to run...", CLASS_NAME, currentMethod());

        Map<String, Integer> paramsMap = new HashMap<>();
        paramsMap.put("startnumber", 0);
        paramsMap.put("endnumber", 10);

        List<ViewTransaction> txnList = new ArrayList();

        if ("oracle".equals(this.dbMode)) {
            txnList = transactionService.getTxnListOra(paramsMap);
        } else if ("mysql".equals(this.dbMode)) {
            txnList = transactionService.getTxnList(paramsMap);
        }

        //交易信息类型转换和输出输入信息
        for (int i = 0; i < txnList.size(); i++) {
            ViewTransaction recentlyTransaction = txnList.get(i);
            recentlyTransaction = this.getTxnDetailInfo(recentlyTransaction);
        }
        return txnList;
    }

    /**
     * 根据txid获取特定交易,包含输入输出详细信息
     *
     * @param request
     * @param response
     * @param txid
     * @return
     */
    @RequestMapping(value = "/getTxn/{txid}", method = RequestMethod.GET)
    @ResponseBody
    public ViewTransaction getTxnByTxid(HttpServletRequest request, HttpServletResponse response,
                                        @PathVariable("txid") String txid) {

        logger.debug("{}.{} start to run...", CLASS_NAME, currentMethod());
        logger.debug("txid: {}", txid);

        ViewTransaction txn = transactionService.getTxnByTxid(txid);
        if (txn != null) {
            txn = this.getTxnDetailInfo(txn);
        }
        return txn;
    }

    /**
     * 分页获取交易基本信息
     *
     * @param request
     * @param response   total：交易总量 data:交易列表
     * @param pagenumber
     * @param pagesize
     * @return
     */
    @RequestMapping(value = "/getTxnList/{pagenumber}/{pagesize}", method = RequestMethod.GET)
    @ResponseBody
    public Map getTxnList(HttpServletRequest request, HttpServletResponse response,
                          @PathVariable("pagenumber") Integer pagenumber,
                          @PathVariable("pagesize") Integer pagesize) {

        logger.debug("{}.{} start to run...", CLASS_NAME, currentMethod());
        logger.debug("pagenumber: {},pagesize: {}",
                new Object[]{pagenumber, pagesize});

        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> paramsMap = new HashMap<>();

        //不同db，查询条件计算有差别
        if ("oracle".equals(this.dbMode)) {
            int startnumber = (pagenumber - 1) * pagesize + 1;
            int endnumber = pagenumber * pagesize;
            paramsMap.put("startnumber", startnumber);
            paramsMap.put("endnumber", endnumber);

        } else if ("mysql".equals(this.dbMode)) {
            int startnumber = (pagenumber - 1) * pagesize;
            paramsMap.put("startnumber", startnumber);
            paramsMap.put("endnumber", pagesize);
        }

        List<ViewTransaction> txnList = new ArrayList<>();
        if ("oracle".equals(this.dbMode)) {
            txnList = transactionService.getTxnListOra(paramsMap);
        } else if ("mysql".equals(this.dbMode)) {
            txnList = transactionService.getTxnList(paramsMap);
        }

        Summary summary = blockService.getSummaryInfo();

        resultMap.put("data", txnList);
        resultMap.put("total", summary.getTxncount());

        return resultMap;
    }

    /**
     * 根据地址获取交易详细信息
     *
     * @param request
     * @param response
     * @param address
     * @return
     */
    @RequestMapping(value = "/getTxnByAddr/{address}/{pagenumber}/{pagesize}", method = RequestMethod.GET)
    @ResponseBody
    public Map getTxnByAddress(HttpServletRequest request, HttpServletResponse response,
                               @PathVariable("address") String address,
                               @PathVariable("pagenumber") Integer pagenumber,
                               @PathVariable("pagesize") Integer pagesize) {

        logger.debug("{}.{} start to run...", CLASS_NAME, currentMethod());
        logger.debug("address: {}", address);

        Map<String, Object> paramsMap = new HashMap<>();
        Map<String, Object> resultMap = new HashMap<>();

        int startnumber = ((pagenumber - 1) * pagesize) > 0 ? ((pagenumber - 1) * pagesize) : 0;
        int endnumber = pagenumber * pagesize;

        paramsMap.put("address", address);
        paramsMap.put("starttime", 0);
        paramsMap.put("endtime", 0);
        paramsMap.put("txid", 0);
        paramsMap.put("blockheight", 0);

        List<ViewTransaction> resultList = new ArrayList<>();

        List<Map> tempList = new ArrayList<>();
        if ("oracle".equals(this.dbMode)) {
            tempList = transactionService.getTxnByParamOra(paramsMap);
        } else if ("mysql".equals(this.dbMode)) {
            tempList = transactionService.getTxnByParam(paramsMap);
        }

        if (tempList.size() == 0) {
            return resultMap;
        }

        int end = tempList.size() < endnumber ? tempList.size() : endnumber;

        for (int j = startnumber; j < end; j++) {
            String txid = tempList.get(j).get("TXID").toString();
            ViewTransaction txn = transactionService.getTxnByTxid(txid);
            txn = this.getTxnDetailInfo(txn);
            resultList.add(txn);
        }
        //获取最新交易
        ViewTransaction lasttxn = transactionService.getTxnByTxid(tempList.get(0).get("TXID").toString());
        lasttxn = this.getTxnDetailInfo(lasttxn);
        //获取最早交易
        ViewTransaction firsttxn = transactionService.getTxnByTxid(tempList.get(tempList.size() - 1).get("TXID").toString());
        firsttxn = this.getTxnDetailInfo(firsttxn);

        //获取所有输入
        String allIncome = transactionService.getAllincomeByAddr(address);
        //获取余额
        String balance = transactionService.getBalanceByAddr(address);

        resultMap.put("lasttxn", lasttxn);
        resultMap.put("firsttxn", firsttxn);
        resultMap.put("data", resultList);
        resultMap.put("allincome", allIncome);
        resultMap.put("balance", balance);
        resultMap.put("total", tempList.size());

        return resultMap;
    }

    /**
     * 根据多个参数查询交易
     *
     * @param request
     * @param response
     * @param txid
     * @param blockheight
     * @param address
     * @param starttime
     * @param endtime
     * @param pagenumber
     * @param pagesize
     * @return
     */
    @RequestMapping(value = "/getTxnByParam/{txid}/{blockheight}/{address}/{pagenumber}/{pagesize}/{starttime}/{endtime}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getTxnByParam(HttpServletRequest request, HttpServletResponse response,
                                             @PathVariable("txid") String txid,
                                             @PathVariable("blockheight") String blockheight,
                                             @PathVariable("address") String address,
                                             @PathVariable("starttime") String starttime,
                                             @PathVariable("endtime") String endtime,
                                             @PathVariable("pagenumber") Integer pagenumber,
                                             @PathVariable("pagesize") Integer pagesize) {

        logger.debug("{}.{} start to run...", CLASS_NAME, currentMethod());
        logger.debug("txid: {},blockheight: {},address: {},starttime: {},endtime: {},pagenumber: {},pagesize: {}",
                new Object[]{txid, blockheight, address, starttime, endtime, pagenumber, pagesize});

        Map<String, Object> paramsMap = new HashMap<>();
        Map<String, Object> resultMap = new HashMap<>();
        int startnumber = ((pagenumber - 1) * pagesize) > 0 ? ((pagenumber - 1) * pagesize) : 0;
        int endnumber = pagenumber * pagesize;

        paramsMap.put("starttime", starttime);
        paramsMap.put("endtime", endtime);
        paramsMap.put("txid", txid);
        paramsMap.put("blockheight", blockheight);
        paramsMap.put("address", address);

        List<Map> tempList = new ArrayList<>();
        List<ViewTransaction> txnList = new ArrayList<>();

        if ("oracle".equals(this.dbMode)) {
            tempList = transactionService.getTxnByParamOra(paramsMap);
        } else if ("mysql".equals(this.dbMode)) {
            tempList = transactionService.getTxnByParam(paramsMap);
        }
        if (tempList.size() == 0) {
            resultMap.put("total", 0);
            resultMap.put("data", txnList);
            return resultMap;
        }

        int end = tempList.size() < endnumber ? tempList.size() : endnumber;

        List<String> idList = new ArrayList<>();
        for (int i = startnumber; i < end; i++) {
            idList.add(tempList.get(i).get("TXID").toString());
        }

        txnList = transactionService.getTxnInfo(idList);

        resultMap.put("total", tempList.size());
        resultMap.put("data", txnList);
        return resultMap;
    }


    /**
     * 交易类型转换和获取输入输出的value和scripthash
     *
     * @param txn
     * @return
     */
    public ViewTransaction getTxnDetailInfo(ViewTransaction txn) {

        int txnType = txn.getTxType();
        String txid = txn.getTxId();

        //类型转换
        switch (txnType) {
            case 1:
                txn.setTxTypeDesc("发行交易");
                break;
            case 16:
                txn.setTxTypeDesc("DeployCode");
                break;
            case 24:
                txn.setTxTypeDesc("注销交易");
                break;
            case 64:
                txn.setTxTypeDesc("注册交易");
                break;
            case 128:
                txn.setTxTypeDesc("转账交易");
                break;
            case 129:
                txn.setTxTypeDesc("存证交易");
                break;
            case 144:
                txn.setTxTypeDesc("状态更新交易");
                break;
            case 208:
                txn.setTxTypeDesc("智能合约");
                break;
            default:
                txn.setTxTypeDesc("");
                break;
        }
        //发行，转账，注销交易才有输入输出
        if (txnType == 128 || txnType == 1 || txnType == 24) {
            //获取交易输出的value和scripthash
            List<Map> outputList = transactionService.getTxnOutput(txid);
            //获取交易输入的value和scripthash
            List<Map> inputList = transactionService.getTxnInput(txid);

            txn.setInputs(inputList);
            txn.setOutputs(outputList);
        }
        return txn;
    }


/*    *
     * 农商行定制，可视化资产流通情况接口
     * 获取银行发行资产信息列表
     *
     *  [
     *      {
     *       "ASSETID": "3d790e2cf6b54c02263bfa1c67805779ce02b6c43e77abf7677a74dbb75b8c4d",
     *       "ASSETNAME": "JF003",
     *       "OUTTXID": "e3a76058536f5cfe4d9c3453cc9c2ea59fb6c201d847693a822741bbeea2b1a9",
     *       "AMOUNT": 100,
     *       "TXTIME": "20170811174001"
     *       }
     *   ]
     *   OUTTXID：银行发行给商户的转账交易id
     **/

    @RequestMapping(value = "/getBankIssuAsset", method = RequestMethod.GET)
    @ResponseBody
    public List<Map> getBankIssuAsset(HttpServletRequest request, HttpServletResponse response) {

        logger.debug("{}.{} start to run...", CLASS_NAME, currentMethod());
        //农商行银行账户地址数组
        String[] account = this.NshAccount.split("\\|");

        List<Map> issuList = transactionService.getBankIssuAsset(account);
        return issuList;
    }


/*    *
     * 农商行定制，可视化资产流通情况接口
     * 获取从银行发行到企业，企业再往下流通的资产流通情况
     **/

    @RequestMapping(value = "/getAssetFlow4Comp/{txid}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getAssetFlow4Comp(@PathVariable("txid") String txid) {

        logger.debug("{}.{} start to run...", CLASS_NAME, currentMethod());

        List<Map<String, String>> company2merList = new ArrayList<>();
        List<Map<String, String>> mer2merList2 = new ArrayList<>();
        List<Map<String, String>> mer2merList3 = new ArrayList<>();
        Map<String, Object> resultMap = new HashMap<>();
        String temptxid = "";
        String spendflag = "";

        //查询资产由银行发行给企业的信息
        Map<String, String> ban2companyInfo = transactionService.getBank2CompanyFlow(txid);
        resultMap.put("0", ban2companyInfo);

        //未获取到任何信息
        if(ban2companyInfo == null) {
            return resultMap;
        }
        //查询企业发行给下级用户的资产流list
        spendflag = String.valueOf(ban2companyInfo.get("SPENDFLAG"));
        //spendflag=1，即该企业的资产有进行流通
        if ("0".equals(spendflag)) {
            return resultMap;
        }
        temptxid = ban2companyInfo.get("OUTTXID");
        logger.debug("企业资产流通交易id :{}", temptxid);
        company2merList = transactionService.getAssetFlow(temptxid);
        resultMap.put("1", company2merList);

        //查找二级商户或个人发行给下级用户的资产流list
        for (int j = 0; j < company2merList.size(); j++) {
            spendflag = String.valueOf(company2merList.get(j).get("SPENDFLAG"));
            //spendflag=1，即该商户的资产有进行流通
            if ("1".equals(spendflag)) {
                temptxid = company2merList.get(j).get("OUTTXID");
                logger.debug("一级商户或个人资产流通交易id is {}", txid);
                List temp = transactionService.getAssetFlow(temptxid);
                mer2merList2.addAll(temp);
            }
        }
        resultMap.put("2", mer2merList2);

        //查找三级商户或个人发行给下级用户的资产流list
        for (int k = 0; k < mer2merList2.size(); k++) {
            spendflag = String.valueOf(mer2merList2.get(k).get("SPENDFLAG"));
            //spendflag=1，即该商户的资产有进行流通
            if ("1".equals(spendflag)) {
                temptxid = mer2merList2.get(k).get("OUTTXID");
                logger.debug("二级商户或个人资产流通交易id is {}", txid);
                List temp = transactionService.getAssetFlow(temptxid);
                mer2merList3.addAll(temp);
            }
        }
        resultMap.put("3", mer2merList3);

        return resultMap;
    }

/*    *
     * 农商行定制，可视化资产流通情况接口
     *
     * 获取从可视化树中某个节点再往下流通的资产流通情况
     * */

    @RequestMapping(value = "/getAssetFlow/{txid}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getAssetFlow(@PathVariable("txid") String txid) {

        logger.debug("{}.{} start to run...", CLASS_NAME, currentMethod());

        List<Map<String, String>> mer2merList1 = new ArrayList<>();
        List<Map<String, String>> mer2merList2 = new ArrayList<>();
        List<Map<String, String>> mer2merList3 = new ArrayList<>();
        Map<String, Object> resultMap = new HashMap<>();
        String temptxid = "";
        String spendflag = "";

        //查询第1代每个商户发行给下级用户的资产流list
        mer2merList1 = transactionService.getAssetFlow(txid);
        resultMap.put("1", mer2merList1);

        //查找第2代商户与下级用户的资产流通
        for (int j = 0; j < mer2merList1.size(); j++) {
            spendflag = String.valueOf(mer2merList1.get(j).get("SPENDFLAG"));
            //spendflag=1，即该商户的资产有进行流通
            if ("1".equals(spendflag)) {
                temptxid = mer2merList1.get(j).get("OUTTXID");
                logger.debug("商户或个人资产流通交易id is {}", txid);
                List temp = transactionService.getAssetFlow(temptxid);
                mer2merList2.addAll(temp);
            }
        }
        resultMap.put("2", mer2merList2);

        //查找第3代商户与下级用户的资产流通
        for (int k = 0; k < mer2merList2.size(); k++) {
            spendflag = String.valueOf(mer2merList2.get(k).get("SPENDFLAG"));
            //spendflag=1，即该商户的资产有进行流通
            if ("1".equals(spendflag)) {
                temptxid = mer2merList2.get(k).get("OUTTXID");
                logger.debug("商户或个人资产流通交易id is {}", txid);
                List temp = transactionService.getAssetFlow(temptxid);
                mer2merList3.addAll(temp);
            }
        }
        resultMap.put("3", mer2merList3);

        return resultMap;
    }

    @RequestMapping(value = "/getBankAssetFlow/{assetid}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getBankAssetFlow(HttpServletRequest request, HttpServletResponse response,
                                      @PathVariable("assetid") String assetid) {

        logger.debug("{}.{} start to run...", CLASS_NAME, currentMethod());

        //银行账户数组
        String [] account = this.NshAccount.split("\\|");
        Map<String,Object> resultMap = new HashMap<>();

        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("assetid",assetid);
        paramsMap.put("bankaddr",account);

        //查询该资产银行发行给商户的list
        List<Map<String,String>> bank2merList = transactionService.getBankAssetFlow(paramsMap);
        resultMap.put("0",bank2merList);

        List<Map<String,String>> mer2merList1 = new ArrayList<>();
        List<Map<String,String>> mer2merList2 = new ArrayList<>();
        List<Map<String,String>> mer2merList3 = new ArrayList<>();

        //查询第1代每个商户发行给下级用户的资产流list
        for(int i = 0;  i < bank2merList.size(); i++) {
//            Map<String,List> map = new HashMap<>();
            String spendflag = String.valueOf(bank2merList.get(i).get("SPENDFLAG"));
            //spendflag=1，即该商户与下级用户有资产流通
            if("1".equals(spendflag)) {
                String txid = bank2merList.get(i).get("OUTTXID");
                logger.debug("OUTTXID is {}",txid);
                List temp = transactionService.getAssetFlow(txid);
//            map.put(txid,mer2merList1);
                mer2merList1.addAll(temp);
            }
//            mer2merList2.add(map);
        }
        resultMap.put("1",mer2merList1);

        //查找第2代商户与下级用户的资产流通
        for(int j = 0; j < mer2merList1.size(); j++) {
            String spendflag = String.valueOf(mer2merList1.get(j).get("SPENDFLAG"));
            //spendflag=1，即该商户与下级用户有资产流通
            if("1".equals(spendflag)) {
                String txid = mer2merList1.get(j).get("OUTTXID");
                logger.debug("OUTTXID is {}",txid);
                List temp = transactionService.getAssetFlow(txid);
//            map.put(txid,mer2merList1);
                mer2merList2.addAll(temp);
            }
        }
        resultMap.put("2",mer2merList2);

        //查找第3代商户与下级用户的资产流通
        for(int k = 0; k < mer2merList2.size(); k++) {
            String spendflag = String.valueOf(mer2merList2.get(k).get("SPENDFLAG"));
            //spendflag=1，即该商户与下级用户有资产流通
            if("1".equals(spendflag)) {
                String txid = mer2merList2.get(k).get("OUTTXID");
                logger.debug("OUTTXID is {}",txid);
                List temp = transactionService.getAssetFlow(txid);
                mer2merList3.addAll(temp);
            }
        }
        resultMap.put("3",mer2merList3);

        return resultMap;
    }


}
