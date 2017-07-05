import Vue from 'vue'
import Vuex from 'vuex'
import * as actions from './actions'
import * as getters from './getters'
import test from './modules/test'
import home from './modules/home'
import main from './modules/main'
import degreeCz from './modules/degreeCz'
import degreeCzLog from './modules/degreeCzLog'


const debug = process.env.NODE_ENV !== 'production'

Vue.use(Vuex)
Vue.config.debug = debug

export default new Vuex.Store({
  modules: {
    test,
    home,
    main,
    degreeCz,
    degreeCzLog
  },
  actions,
  getters
})
