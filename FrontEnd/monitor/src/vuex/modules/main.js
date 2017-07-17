/**
 * Created by tanyuan on 25/4/2017.
 */
import * as types from '../mutation-types'

const state = {
  degreeList: [],
  blockList: [],
  nodeList: [],
  nodeStatus: {},
  blockStatus: {},
  degreeSum: {},
  degreeInfoNew:[],
  degreeSumByStatus: {},
  machineInfo:[],
  oneNodeInfo:{},
//temporary for HoneyWell 
  namespace:[],
  stateupdatetxn:[],
}

const mutations = {
  [types.DEGREELIST] (state, { data }) {
    state.degreeList = data
  },
  [types.BLOCKLIST] (state, { data }) {
    state.blockList = data
  },
  [types.NODELIST] (state, { data }) {
    state.nodeList = data
  },
  [types.NODESTATUS] (state, { data }) {
    data.failureRatio = Math.round(data.errorNum / data.totalCount * 10000) / 100
    state.nodeStatus = data
  },
  [types.BLOCKSTATUS] (state, { data }) {
    state.blockStatus = data
  },
  [types.LASTBLOCK] (state, { data }) {
    var flag = true
    for(var i in state.blockList) {
      if(state.blockList[i].hash == data.hash) {
        flag = false
        break
      }
    }
    if(flag) {
      data.hashS = data.hash.substr(0, 45) + "..."
      state.blockList.unshift(data)
      state.blockList.pop()
    }
  },
  [types.LASTDEGREE] (state, { data }) {
    var flag = true
    for(var i in state.degreeList) {
      if(state.degreeList[i].txId == data.txId) {
        flag = false
        break
      }
    }
    if(flag) {
      data.txIdS = data.txId.substr(0, 30) + "..."
      state.degreeList.unshift(data)
      state.degreeList.pop()
    }
  },

  [types.DEGREESUM] (state, { data }) {
    state.degreeSum = data
  },
  [types.DEGREEINFONEW] (state, { data }) {
    state.degreeInfoNew = data
  },

  [types.DEGREESUMBYSTATUS] (state, {data}) {
    state.degreeSumByStatus = data
  },

  [types.MACHINEINFO] (state, { data }) {
    state.machineInfo = data
  },
  [types.ONENODEINFO] (state, { data }) {
    state.oneNodeInfo = data
  },
//temporary for HoneyWell 
  [types.NAMESPACE] (state, { data }) {
    state.namespace = data
  },
  [types.STATEUPDATETXN] (state, { data }) {
    state.stateupdatetxn = data
  }


}
export default {
  state,
  mutations
}
