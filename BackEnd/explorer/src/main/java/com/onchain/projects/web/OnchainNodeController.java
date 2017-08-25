package com.onchain.projects.web;

import com.onchain.projects.domain.Node;
import com.onchain.projects.service.INodeService;
import com.onchain.projects.utils.common.PagedResult;
import com.onchain.projects.web.param.NodeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.NumberFormat;
import java.util.List;


@Controller
@RequestMapping("node/service")
public class OnchainNodeController {

    @Autowired
    private INodeService nodeService;
    @Value("${nodeType_1}")
    private String nodeType1;
    @Value("${nodeType_2}")
    private String nodeType2;
    @Value("${nodeDes_1}")
    private String nodeDes1;
    @Value("${nodeDes_2}")
    private String nodeDes2;

    /**
     * 获取当前所有节点信息
     *
     * @param pageNumber
     * @param pageSize
     * @return
     * @author zhouq
     * @version 1.1
     */
    @RequestMapping("/getNodeLocalDB")
    @ResponseBody
    public PagedResult<Node> getAllNodes(HttpServletRequest request, HttpServletResponse response, Integer pageNumber, Integer pageSize) {

        String param = null;
        String nodeType = "";
        String timeStr = "";
        PagedResult<Node> pageResult = nodeService.getAllNodeList(param, pageNumber, pageSize);
        //用于前端页面定位节点坐标
        int[] x = {150, 195, 320, 70, 414, 90, 180, 170, 200, 320};
        int[] y = {162, 103, 107, 150, 150, 100, 150, 200, 220, 150};
        //状态转换
        List<Node> nodeList = pageResult.getDataList();
        for (int i = 0; i < nodeList.size(); i++) {
            Node node = nodeList.get(i);
            if ("1".equals(node.getRpcStatus())) {
                node.setRpcStatus("节点连接正常");
            } else if ("0".equals(node.getRpcStatus())) {
                node.setRpcStatus("节点连接断开");
            } else {
            }

            if ("1".equals(node.getBlockStatus())) {
                node.setBlockStatus("出块正常");
            } else if ("0".equals(node.getBlockStatus())) {
                node.setBlockStatus("出块异常");
            } else {
            }
            //节点类型转换
            nodeType = node.getType();
            if (nodeType.equals(this.nodeType1)) {
                node.setType(this.nodeDes1);
            } else if (nodeType.equals(this.nodeType2)) {
                node.setType(this.nodeDes2);
            } else {
            }


            node.setX(i > 10 ? x[i % 10] + 30 : x[i]);
            node.setY(i > 10 ? y[i % 10] + 10 : y[i]);

        }

        return pageResult;
    }

    /**
     * 获取节点统计信息
     *
     * @return
     * @author zhouq
     * @version 1.1
     */
    @RequestMapping("getNodeLocalDBtotal")
    @ResponseBody
    public NodeInfo getNodesInfo(HttpServletRequest request, HttpServletResponse response) {

        String blockErrorFlag = "0";
        String blockCorrectFlag = "1";
        String errorPer = "";
        String correctPer = "";
        String param = null;
        Integer pageNumber = null;
        Integer pageSize = null;
        String nodeType = "";
        int type1num = 0;
        int type2num = 0;
        NodeInfo nodeInfo = new NodeInfo();

        int errorNum = nodeService.getNodeNumByFlag(blockErrorFlag);
        int correctNum = nodeService.getNodeNumByFlag(blockCorrectFlag);
        int total = errorNum + correctNum;
        if(total != 0){
            // 设置精确到小数点后2位
            NumberFormat numberFormat = NumberFormat.getInstance();
            numberFormat.setMaximumFractionDigits(2);
            //故障节点所占比例
            errorPer = numberFormat.format((float) errorNum / (float) total * 100);
            //正常节点所占比例
            correctPer = numberFormat.format((float) correctNum / (float) total * 100);

            PagedResult<Node> pageResult = nodeService.getAllNodeList(param, pageNumber, pageSize);
            List<Node> nodeList = pageResult.getDataList();
            for (int i = 0; i < nodeList.size(); i++) {
                Node node = nodeList.get(i);
                //节点类型转换
                nodeType = node.getType();
                if (nodeType.equals(this.nodeType1)) {
                    type1num++;
                } else if (nodeType.equals(this.nodeType2)) {
                    type2num++;
                } else {
                }
            }
        }

        nodeInfo.setTotalCount(total);
        nodeInfo.setCorrectNum(correctNum);
        nodeInfo.setErrorNum(errorNum);
        nodeInfo.setCorrectPer(correctPer);
        nodeInfo.setErrorPer(errorPer);
        nodeInfo.setType1Num(type1num);
        nodeInfo.setType2Num(type2num);

        return nodeInfo;
    }

    /**
     * @param
     * @return
     */
    @RequestMapping(value = "/getOneNode/{nodeinfo}", method = RequestMethod.GET)
    @ResponseBody
    public Node getOneNode(HttpServletRequest request, HttpServletResponse response, @PathVariable("nodeinfo") String nodeTemp) {

        //   System.out.println("nodeinfo is "+nodeTemp);192_168_1_125_20338
        String nodeInfo = nodeTemp.replace("_", ".");
        String port = nodeInfo.substring(nodeInfo.lastIndexOf(".")+1, nodeInfo.length());
        nodeInfo = nodeInfo.substring(0, nodeInfo.lastIndexOf(".")) + ":" + port;
        //  System.out.println("nodeInfo is "+nodeInfo);
        //可能在一个服务器上部署多个节点
        List<Node> nodeList = nodeService.getOneNode(nodeInfo);
        Node node = new Node();
        if (nodeList.size() > 0) {
            node = nodeList.get(0);
        }

        if ("1".equals(node.getRpcStatus())) {
            node.setRpcStatus("节点连接正常");
        } else if ("0".equals(node.getRpcStatus())) {
            node.setRpcStatus("节点连接断开");
        } else {
        }

        if ("1".equals(node.getBlockStatus())) {
            node.setBlockStatus("出块正常");
        } else if ("0".equals(node.getBlockStatus())) {
            node.setBlockStatus("出块异常");
        } else {
        }
        //节点类型转换
        if (this.nodeType1.equals(node.getType())) {
            node.setType(this.nodeDes1);
        } else if (this.nodeType2.equals(node.getType())) {
            node.setType(this.nodeDes2);
        } else {
        }

        return node;
    }

}
