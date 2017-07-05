import Vue from 'vue'
import Element from 'element-ui'
import App from './App'
import store from './vuex/store'
import router from './router'
import { sync } from 'vuex-router-sync'
//import './assets/ebro/css/template.css'
//import './assets/ebro/css/bootstrap-simplify.css'
//import './assets/ebro/css/Site.css'
//import './assets/element/index.css'
//import './assets/ebro/css/style.css'
//import './assets/ebro/css/ie.css'
//import './assets/ebro/css/retina.css'
//import './assets/ebro/img/flags/flags.css'
//import './assets/ebro/theme/color_1.css'
//import './assets/ebro/js/lib/jvectormap/jquery-jvectormap-1.2.2.css'
//import './assets/ebro/js/lib/owl-carousel/owl.carousel.css'
//import './assets/ebro/js/lib/magnific-popup/magnific-popup.css'
//import './assets/ebro/css/linecons/style.css'
//import './assets/ebro/js/lib/fullcalendar/fullcalendar.css'
//import './assets/ebro/js/lib/magnific-popup/magnific-popup.css'
//import './assets/ebro/css/linecons/style.css'

//sync the route with the vuex store.
//this registers 'store.state.route'

Vue.use(Element)

sync(store, router)

/* eslint-disable no-new */
const app = new Vue({
  router,
  store,
  ...App //Object spread copying everything form App.vue
})

app.$mount('#app')
