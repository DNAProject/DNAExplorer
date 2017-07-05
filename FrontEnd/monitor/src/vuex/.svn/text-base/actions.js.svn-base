import api from '../api'
import * as types from './mutation-types'

export const test = ({ commit }) => {
  api.getTestData().then(response => {
    if(!response.ok) {
      return;
    }
    var data = response.body
    commit(types.TEST, { data })
  })
}

export const home = ({ commit }) => {
  api.getHomeData().then(response => {
    var data = response.body
    for(var i=0; i<data.length; i++) {
        if(data[i].txId.length > 55) {
          data[i].txId = data[i].txId.substr(0, 55) + "..."
        }
    }
    commit(types.HOME, { data })
  })
}

export const degreeList = ({ commit }) => {
  api.getDegreeListData().then(response => {
    var data = response.body
    for(var i=0; i<data.length; i++) {
      data[i].txIdS = data[i].txId.substr(0, 30) + "..."
    }
    commit(types.DEGREELIST, { data })
  })
}

export const blockList = ({ commit }) => {
  api.getBlockListData().then(response => {
    var data = response.body
    for(var i=0; i<data.length; i++) {
      data[i].hashS = data[i].hash.substr(0, 45) + "..."
    }
    commit(types.BLOCKLIST, { data })
  })
}

export const nodeList = ({ commit }) => {
  api.getNodeListData().then(response => {
    var data = response.body.dataList
    commit(types.NODELIST, { data })
  })
}


export const nodeStatus = ({ commit }) => {
  api.getNodeStatus().then(response => {
    var data = response.body
    commit(types.NODESTATUS, { data })
  })
}

export const blockStatus = ({ commit }) => {
  api.getBlockStatus().then(response => {
    var data = response.body
    commit(types.BLOCKSTATUS, { data })
  })
}

export const lastBlock = ({ commit }) => {
  api.getLastBlock().then(response => {
    var data = response.body
    commit(types.LASTBLOCK, { data })
  })
}

export const lastDegree = ({ commit }) => {
  api.getLastDegree().then(response => {
    var data = response.body
    commit(types.LASTDEGREE, {data})
  })
}

export const degreeInfo = ({ commit }, params) => {
  api.DegreeCzByTxid(params.txId).then(response => {
    var data = response.body
    commit(types.DEGREEINFO, {data})
  })
}

export const degreeLog = ({ commit }, params) => {
  if(params.id.length <= 8) {
    api.DegreeCzLogByPersonid(params.id).then(response => {
      var data = response.body
      commit(types.GEGREELOG, {data})
    })
  }else if(params.id.length == 64){
    api.DegreeCzLogByTxid(params.id).then(response => {
      var data = response.body
      commit(types.GEGREELOG, {data})
    })
  }
}
export const degreeSum = ({ commit }) => {
  api.getDegreeSum().then(response => {
    var data = response.body
    commit(types.DEGREESUM, { data })
  })
}

export const degreeInfoNew = ({ commit }) => {
  api.getDegreeInfoNew().then(response => {
    var data = response.body
    for(var i=0; i<data.length; i++) {
      if(data[i].eventName.length > 8){
        data[i].eventNameS = data[i].eventName.substr(0, 8) + "..."
      }else{
        data[i].eventNameS = data[i].eventName
      }
      data[i].txIdS = data[i].txId.substr(0, 30) + '...'
    }
    commit(types.DEGREEINFONEW, { data })
  })
}

export const degreeSumByStatus = ({ commit }) => {
  api.getDegreeSumByStatus().then(response => {
    var data = response.body
    commit(types.DEGREESUMBYSTATUS, { data })
  })
}

export const machineInfo = ({ commit }, params) => {
  api.getMachineInfo(params.ip).then(response => {
    var data = response.body
    commit(types.MACHINEINFO, {data})
  })
}
export const oneNodeInfo = ({ commit }, params) => {
  api.getOneNodeInfo(params.ip).then(response => {
    var data = response.body
    commit(types.ONENODEINFO, {data})
  })
}
