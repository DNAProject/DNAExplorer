import Vue from 'vue'
import VueResource from 'vue-resource'
import {API_ROOT} from '../config'
import { getCookie,signOut } from '../util/authService'

Vue.use(VueResource)

Vue.http.options.crossOrigin = true
Vue.http.options.credentials = true

Vue.http.interceptors.push((request, next) => {
  request.headers = request.headers || {}
  if(getCookie("token")) {
    request.headers.Authorizatin = 'Bearer ' + getCookie('token').replace(/(^\")|(\"$)/g, '')
  }
  next((response) => {
    if(response.status === 401) {
      signOut()
      window.location.pathname = '/login'
    }
  })
})

export const DegreeCzLogActions = {
  CunzhengList: {method: 'GET', url: API_ROOT + 'gy/cunzheng/service/CunzhengList{/origTxid}'},
  Collects: {method: 'GET', url: API_ROOT + 'gy/cunzheng/service/Collects{/personid}'}
}

//export const TestResource = Vue.resource(API_ROOT + 'test/data')
//export const DegreeListResource = Vue.resource(API_ROOT + 'gy/cunzheng/service/Cunzhengs')
//export const BlockListResource = Vue.resource(API_ROOT + 'gy/blocks/service/Blocks')
//export const NodeListResource = Vue.resource(API_ROOT + 'gy/node/service/Nodes')
//export const NodeStatusResource = Vue.resource(API_ROOT + 'gy/node/service/NodesInfo')
//export const BlocStatusResource = Vue.resource(API_ROOT + 'gy/blocks/service/SummaryInfo')
//export const LastBlockResource = Vue.resource(API_ROOT + 'gy/blocks/service/NewestBlock')
//export const LastDegreeResource = Vue.resource(API_ROOT + 'gy/cunzheng/service/NewestCunzheng')
export const DegreeCzResource = Vue.resource(API_ROOT + 'gy/cunzheng/service/Cunzhengs{/txid}')
export const DegreeCzLogResource = Vue.resource('gy/cunzheng/service{/id}', {}, DegreeCzLogActions)
export const DegreeCzSumResource = Vue.resource(API_ROOT + 'gy/cunzheng/service/CunzhengSumInfo')
export const DegreeCzInfoResource = Vue.resource(API_ROOT + 'gy/cunzheng/service/CunzhengInfoNew')

export const DegreeCzSumByStatusResource = Vue.resource(API_ROOT + 'gy/cunzheng/service/CunzhengNumByStatus')





export const TestResource = Vue.resource(API_ROOT + 'test/data')
export const DegreeListResource = Vue.resource(API_ROOT + 'transaction/service/getRecentlyList')
export const BlockListResource = Vue.resource(API_ROOT + 'blocks/service/getBlocks')
export const NodeListResource = Vue.resource(API_ROOT + 'node/service/getNodeLocalDB')
export const NodeStatusResource = Vue.resource(API_ROOT + 'node/service/getNodeLocalDBtotal')
export const BlocStatusResource = Vue.resource(API_ROOT + 'blocks/service/getCurrentSumInfo')
export const LastBlockResource = Vue.resource(API_ROOT + 'blocks/service/getBlocksOne')
export const LastDegreeReource = Vue.resource(API_ROOT + 'transaction/service/getRecentlyOne')
export const MachineInfoReource = Vue.resource(API_ROOT + 'machine/service/all{/ip}')
export const OneNodeInfoResource = Vue.resource(API_ROOT + 'node/service/getOneNode{/ip}')
//temporary for HoneyWell 
export const stateupdatetxnReource = Vue.resource(API_ROOT + 'transaction/service/getstateupdatetxn{/namespace}{/key}{/pagenumber}{/pagesize}{/begintime}{/endtime}')
export const namespaceReource = Vue.resource(API_ROOT + 'transaction/service/getnamespace') 
export const txnByAddressReource = Vue.resource(API_ROOT + 'transaction/service/getTxnByAddr{/address}{/pagenumber}{/pagesize}')
export const txnByAddressMockReource = Vue.resource('http://localhost:3000/' + 'transaction/service/getTxnByAddressMock')
//for new browser
export const TxnReource = Vue.resource(API_ROOT + 'transaction/service/getTxn{/txid}')
export const TxnListReource = Vue.resource(API_ROOT + 'transaction/service/getTxnList{/pagenumber}{/pagesize}')
export const BlockReource = Vue.resource(API_ROOT + 'blocks/service/getBlock{/height}')
export const BlockListNewReource = Vue.resource(API_ROOT + 'blocks/service/getBlockList{/pagenumber}{/pagesize}')
export const TxnByParamReource = Vue.resource(API_ROOT + 'transaction/service/getTxnByParam{/txid}{/height}{/address}{/pagenumber}{/pagesize}{/begintime}{/endtime}')