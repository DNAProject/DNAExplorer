package com.onchain.projects.ws.service;

import com.onchain.projects.domain.Node;
import com.onchain.projects.service.INodeService;
import com.onchain.projects.utils.common.PagedResult;
import com.onchain.projects.web.param.NodeInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.List;

/**
 * Created by ZhouQ on 2017/6/5.
 */
@Service
public class SendNodeService {

    @Value("${nodeType_1}")
    private String nodeType1;
    @Value("${nodeType_2}")
    private String nodeType2;
    @Value("${nodeDes_1}")
    private String nodeDes1;
    @Value("${nodeDes_2}")
    private String nodeDes2;

    private Logger logger = LoggerFactory.getLogger(SendBlockService.class);

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private INodeService nodeService;

    // @SendTo("/topic/getNode")
    public void sendNodeInfo() throws Exception{
        int i = 0;
        String param = null;
        Integer pageNumber = null;
        Integer pageSize = null;
        Node nodeTemp = new Node();
        String blockErrorFlag = "0";
        String blockCorrectFlag = "1";
        String nodeType = "";
        NodeInfo nodeInfo = new NodeInfo();

        while(true){
            Thread.sleep(5000);
            PagedResult<Node> pageResult = nodeService.getAllNodeList(param,pageNumber,pageSize);
            //状态转换
            List<Node> nodeList = pageResult.getDataList();
            if(i == 0){
                nodeTemp = nodeList.get(0);
            }
            if(i >= 1){
                if(nodeTemp.getDate().equals(nodeList.get(0).getDate())){
                    logger.debug("same node, date is {}",nodeTemp.getDate());
                    i++;
                    continue;
                }else{
                    nodeTemp = nodeList.get(0);
                }
            }

            for (int j = 0; j < nodeList.size(); j++) {
                Node node = nodeList.get(j);
                if ("1".equals(node.getRpcStatus())) {
                    node.setRpcStatus("节点连接正常");
                }else if ("0".equals(node.getRpcStatus())) {
                    node.setRpcStatus("节点连接断开");
                }else{}

                if ("1".equals(node.getBlockStatus())) {
                    node.setBlockStatus("出块正常");
                }else if ("0".equals(node.getBlockStatus())) {
                    node.setBlockStatus("出块异常");
                }else{}

                //节点类型转换
                nodeType = node.getType();
                if(nodeType.equals(this.nodeType1)){
                    node.setType(this.nodeDes1);
                }else if(nodeType.equals(this.nodeType2)){
                    node.setType(this.nodeDes2);
                }else{}
            }

            int errorNum =  nodeService.getNodeNumByFlag(blockErrorFlag);
            int correctNum =  nodeService.getNodeNumByFlag(blockCorrectFlag);
            int total  = errorNum + correctNum;
            // 设置精确到小数点后2位
            NumberFormat numberFormat = NumberFormat.getInstance();
            numberFormat.setMaximumFractionDigits(2);
            //故障节点所占比例
            String errorPer = numberFormat.format((float) errorNum / (float) total * 100);
            //正常节点所占比例
            String correctPer = numberFormat.format((float) correctNum / (float) total * 100);

            nodeInfo.setTotalCount(total);
            nodeInfo.setCorrectNum(correctNum);
            nodeInfo.setErrorNum(errorNum);
            nodeInfo.setCorrectPer(correctPer);
            nodeInfo.setErrorPer(errorPer);
          //  String nodeStatus = "{\"totalCount\":"+total+","+ "\"errorNum\":"+errorNum+ ","+"\"correctNum\":"+correctNum+"\"errorPer\":"+errorPer+","+"\"correctPer\":"+correctPer+"}";
            //数据推送
            template.convertAndSend("/topic/getNodes", nodeList);
            template.convertAndSend("/topic/getNodeStatus",nodeInfo);

            i++;
        }
    }
}
