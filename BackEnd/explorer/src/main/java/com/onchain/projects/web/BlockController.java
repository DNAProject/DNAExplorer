package com.onchain.projects.web;

import com.onchain.projects.domain.Block;
import com.onchain.projects.domain.Summary;
import com.onchain.projects.domain.ViewTransaction;
import com.onchain.projects.service.IBlockService;
import com.onchain.projects.service.ITransactionService;
import com.onchain.projects.utils.common.DateUtil;
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
@RequestMapping("blocks/service")
public class BlockController {

    @Autowired
    private IBlockService blockService;
    @Autowired
    private ITransactionService transactionService;
    @Autowired
    private TransactionController transactionController;

    @Value("${db_mode}")
    private String dbMode;

    private Logger logger = LoggerFactory.getLogger(BlockController.class);

    /**
     * 获取当前交易数量和区块高度
     * 返回例子：
     *  {
     *   "height": 14263,
     *   "txncount": 100
     *   }
     *
     * @return
     * @author zhouq
     * @version 1.1
     */
    @RequestMapping("/getCurrentSumInfo")
    @ResponseBody
    public Summary getCurrentSumInfo(HttpServletRequest request, HttpServletResponse response) {

        logger.debug("begin getCurrentSumInfo method ...");

        Summary summary = blockService.getSummaryInfo();
        return summary;
    }

    /**
     * 获取当前10个区块信息(按区块高度降序排序)
     * 返回tbl_pro_blocks信息
     *
     * @return
     * @author zhouq
     * @version 1.1
     */
    @RequestMapping(value = "/getBlocks", method = RequestMethod.GET)
    @ResponseBody
    public List<Block> getBlocks(HttpServletRequest request, HttpServletResponse response) {

        logger.debug("begin getBlocks method ...");

        Map<String, Integer> params = new HashMap<>();
        params.put("startnumber", 0);
        params.put("endnumber", 10);

        List<Block> list = this.getBlockList(params);
        return list;
    }

    /**
     * 获取区块信息(分页查询)
     * 返回tbl_pro_blocks信息
     *
     * @param pagenumber 第几页(从1开始)
     * @param pagesize   每页多少条记录
     * @return
     * @author zhouq
     * @version 1.1
     */
    @RequestMapping(value = "/getBlockList/{pagenumber}/{pagesize}", method = RequestMethod.GET)
    @ResponseBody
    public Map getBlocksList(HttpServletRequest request, HttpServletResponse response,
                             @PathVariable("pagenumber") Integer pagenumber,
                             @PathVariable("pagesize") Integer pagesize) {

        logger.debug("begin getBlockList method ...");
        logger.debug("pagenumber: {},pagesize: {}",
                new Object[]{pagenumber, pagesize});

        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Integer> paramsMap = new HashMap<>();

        //不同db，分页查询条件计算有差别
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

        List<Block> blockList = this.getBlockList(paramsMap);
        //获取总数量
        Summary summary = blockService.getSummaryInfo();

        resultMap.put("total", summary.getHeight());
        resultMap.put("data", blockList);

        return resultMap;
    }


    /**
     * 获取指定高度区块信息和交易信息
     *
     * @param height 区块高度
     * @return
     * @author zhouq
     * @version 1.1
     */
    @RequestMapping(value = "/getBlock/{height}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getBlock(HttpServletRequest request, HttpServletResponse response,
                                        @PathVariable("height") Integer height) {

        logger.debug("begin getBlock method ...");
        logger.debug("height: {}",height);

        Map<String, Object> resultMap = new HashMap<>();

        Block block = blockService.getBlockByHeight(height);
        //从view获取txn基本信息
        List<ViewTransaction> txnList = transactionService.getTxnByHeight(height);
        //从tbl_pro_txn_details获取txn详细信息
        for (int i = 0; i < txnList.size(); i++) {
            ViewTransaction txn = txnList.get(i);
            txn = transactionController.getTxnDetailInfo(txn);
        }

        resultMap.put("block", block);
        resultMap.put("transaction", txnList);
        return resultMap;

    }

    /**
     * bloclList时间格式转换
     *
     * @return
     * @author zhouq
     * @version 1.1
     */
    public List<Block> getBlockList(Map<String, Integer> paramsMap) {
        List<Block> list = new ArrayList();

        if ("oracle".equals(this.dbMode)) {
            list = blockService.getBlockListOra(paramsMap);
        } else if ("mysql".equals(this.dbMode)) {
            list = blockService.getBlockList(paramsMap);
        }

        //修改时间格式
        for (int i = 0; i < list.size(); i++) {
            String timestamp = list.get(i).getTimestamp();
            String timestampVo = DateUtil.timeStamp2Date(timestamp, "yyyy-MM-dd HH:mm:ss");
            list.get(i).setTimestamp(timestampVo);
        }
        return list;
    }

}
