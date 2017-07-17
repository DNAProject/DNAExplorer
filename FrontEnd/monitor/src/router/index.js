import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const App = r => require.ensure([], () => r(require('../App')), 'app')
const Home = r => require.ensure([], () => r(require('../components/Home')), 'home')
const Main = r => require.ensure([], () => r(require('../components/Main')), 'main')
const Monitor = r => require.ensure([], () => r(require('../components/Monitor')), 'monitor')
const nodeinfo = r => require.ensure([], () => r(require('../components/nodeinfo')), 'nodeinfo')
const chaininfo = r => require.ensure([], () => r(require('../components/chaininfo')), 'chaininfo')
const filtersearch = r => require.ensure([], () => r(require('../components/filtersearch')), 'filtersearch')

const routes = [
  {
    path: '/',
    component: App,
    children: [
      {
        path: '',
        redirect: '/monitor'
      },
      /*{
        path: '/degreeCz/:txId/:type',
        component: DegreeCz
      }*/
      {
        path: '/monitor',
        component: Monitor
      },
      {
        path:'/nodeinfo/:ip',
        component:nodeinfo
      },
      {
        path:'/chaininfo',
        component:chaininfo
      },
      {
        path:'/filtersearch/:namespace/:key/:page/:pagesize/:begintime/:endtime',
        component:filtersearch
      }

    ]
  },
  { path: '*', redirect: '/' }
];

export default new Router({
  mode: 'hash',
  scrollBehavior: () => ({ y: 0 }),
  routes
})
