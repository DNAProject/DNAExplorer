/**
 * Created by tanyuan on 21/4/2017.
 */
import {HOME} from '../mutation-types'

const state = {
  tradeData: []
}

const mutations = {
  [HOME] (state, { data }) {
    state.tradeData = data
  }
}

export default {
  state,
  mutations
}
