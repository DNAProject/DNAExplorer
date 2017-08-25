export const testData = state => {
  return state.test.test.data
}
export const tradeData = state => {
  return state.home.tradeData
}
export const degreeList = state => {
  return state.main.degreeList
}
export const blockList = state => {
  return state.main.blockList
}
export const nodeList = state => {
  return state.main.nodeList
}
export const nodeStatus = state => {
  return state.main.nodeStatus
}
export const blockStatus = state => {
  return state.main.blockStatus
}
export const degreeInfo = state => {
  return state.degreeCz.degreeInfo
}
export const degreeLog = state => {
  return state.degreeCzLog.degreeLog
}
export const degreeSum = state => {
  return state.main.degreeSum
}
export const degreeInfoNew = state => {
  return state.main.degreeInfoNew
}

export const degreeSumByStatus = state => {
  return state.main.degreeSumByStatus
}
export const machineInfo = state => {
  return state.main.machineInfo
}
export const oneNodeInfo = state => {
  return state.main.oneNodeInfo
}
//temporary for HoneyWell 
export const nameSpace = state => {
  return state.main.namespace
}
export const stateUpdateTxn = state => {
  return state.main.stateupdatetxn
}
export const txnByAddress = state => {
  return state.main.txnbyaddress
}
export const txnByAddressMock = state => {
  return state.main.txnbyaddressmock
}
//for new browser
export const txnByTxid = state => {
  return state.main.txnbytxid
}
export const txnList = state => {
  return state.main.txnlist
}
export const blockByHeight = state => {
  return state.main.blockbyheight
}
export const blockListNew = state => {
  return state.main.blocklistnew
}
export const txnByParam = state => {
  return state.main.txnbyparam
}