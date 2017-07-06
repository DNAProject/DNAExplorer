package com.onchain.projects.web;

import com.onchain.projects.domain.Block;
import com.onchain.projects.domain.Summary;
import com.onchain.projects.service.IBlockService;
import com.onchain.projects.utils.common.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
@RequestMapping("blocks/service")
public class BlockController {

    @Autowired
    private IBlockService blockService;

    private Logger logger = LoggerFactory.getLogger(BlockController.class);

    /**
     * 获取当前交易数量和区块高度
     *
     * @return
     * @author zhouq
     * @version 1.1
     */
    @RequestMapping("/getCurrentSumInfo")
    @ResponseBody
    public Summary getCurrentSumInfo(HttpServletRequest request, HttpServletResponse response) {
        Summary summary = blockService.getSummaryInfo();
        return summary;
    }

    /**
     * 获取当前10个区块信息(按区块高度排序)
     *
     * @return
     * @author zhouq
     * @version 1.1
     */
    @RequestMapping(value = "/getBlocks", method = RequestMethod.GET)
    @ResponseBody
    public List<Block> getBlocks(HttpServletRequest request, HttpServletResponse response) {
        List<Block> list = this.getBlockList();
        return list;
    }

    /**
     * 获取最新区块信息
     *
     * @return
     * @author zhouq
     * @version 1.1
     */
    @RequestMapping(value = "/getBlocksOne", method = RequestMethod.GET)
    @ResponseBody
    public Block getBlocksOne(HttpServletRequest request, HttpServletResponse response) {
        List<Block> list = this.getBlockList();
        if (list.size() > 0) {
            Block block = list.get(0);
            return block;
        }
        Block block = new Block();
        return block;
    }

    /**
     * bloclList时间格式转换
     *
     * @return
     * @author zhouq
     * @version 1.1
     */
    public List<Block> getBlockList() {
        //修改时间格式
        List<Block> list = blockService.getBlocks();
        for (int i = 0; i < list.size(); i++) {
            String timestamp = list.get(i).getTimestamp();
            String timestampVo = DateUtil.timeStamp2Date(timestamp, "yyyy-MM-dd HH:mm:ss");
            list.get(i).setTimestamp(timestampVo);
        }
        return list;
    }

}
