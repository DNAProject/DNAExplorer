import * as resource from './resources'

export default {
  getTestData: function() {
    return resource.TestResource.get()
  },
  getDegreeListData: function() {
    return resource.DegreeListResource.get()
  },
  getBlockListData: function() {
    return resource.BlockListResource.get()
  },
  getNodeListData: function() {
    return resource.NodeListResource.get()
  },
  getNodeStatus: function () {
    return resource.NodeStatusResource.get()
  },
  getBlockStatus: function () {
    return resource.BlocStatusResource.get()
  },
  getLastBlock: function() {
    return resource.LastBlockResource.get()
  },
  getLastDegree: function() {
    return resource.LastDegreeResource.get()
  },
  DegreeCzByTxid: function (txId) {
    return resource.DegreeCzResource.get({txid: txId})
  },
  DegreeCzLogByTxid: function(origTxid) {
    return resource.DegreeCzLogResource.CunzhengList({origTxid: origTxid})
  },
  DegreeCzLogByPersonid: function(persionid) {
    return resource.DegreeCzLogResource.Collects({personid: persionid})
  },
  getDegreeSum: function () {
    return resource.DegreeCzSumResource.get()
  },
  getDegreeInfoNew: function () {
    return resource.DegreeCzInfoResource.get()
  },

  getDegreeSumByStatus:function () {
    return resource.DegreeCzSumByStatusResource.get()
  },
  getMachineInfo: function (ip) {
    return resource.MachineInfoReource.get({ip: ip})
  },
  getOneNodeInfo:function (ip) {
    return resource.OneNodeInfoResource.get({ip:ip})
  },

  getNameSpace: function () {
    return resource.namespaceReource.get()
  },
//getStateUpdateTxn: function () {
//  return resource.stateupdatetxnReource.get({namespace:namespace,key:key,pagenumber:pagenumber, pagesize:pagesize,begintime:begintime,endtime:endtime})
//}  
  getStateUpdateTxn: function (namespace,key,pagenumber,pagesize,begintime,endtime) {
    return resource.stateupdatetxnReource.get({namespace:namespace,key:key,pagenumber:pagenumber, pagesize:pagesize,begintime:begintime,endtime:endtime})
  } 
}
