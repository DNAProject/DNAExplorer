import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const App = r => require.ensure([], () => r(require('../App')), 'app')
const Home = r => require.ensure([], () => r(require('../components/Home')), 'home')
const Main = r => require.ensure([], () => r(require('../components/Main')), 'main')
const Browser = r => require.ensure([], () => r(require('../components/browser')), 'browser')
const Blockinfo = r => require.ensure([], () => r(require('../components/blockinfo')), 'blockinfo')
const Blockdetail = r => require.ensure([], () => r(require('../components/blockdetail')), 'blockdetail')
const Addressinfo = r => require.ensure([], () => r(require('../components/addressinfo')), 'addressinfo')
const Addressdetail = r => require.ensure([], () => r(require('../components/addressdetail')), 'addressdetail')
const Transactioninfo = r => require.ensure([],() => r(require('../components/transactioninfo')),'transactioninfo')
const Transactiondetail = r => require.ensure([],() => r(require('../components/transactiondetail')),'transactiondetail')


const Monitor = r => require.ensure([], () => r(require('../components/Monitor')), 'Monitor')
const nodeinfo = r => require.ensure([], () => r(require('../components/nodeinfo')), 'nodeinfo')
const chaininfo = r => require.ensure([], () => r(require('../components/chaininfo')), 'chaininfo')
const filtersearch = r => require.ensure([], () => r(require('../components/filtersearch')), 'filtersearch')
const filtersearchaddress = r => require.ensure([], () => r(require('../components/filtersearchaddress')), 'filtersearchaddress')

const routes = [
  {
    path: '/',
    component: App,
    children: [
      {
        path: '',
        redirect: '/browser'
      },
      /*{
        path: '/degreeCz/:txId/:type',
        component: DegreeCz
      }*/
      {
        path: '/browser',
        component: Browser
      },
      {
        path: '/monitor',
        component: Monitor
      },
      {
        path: '/blockinfo/:page/:pagesize',
        component:Blockinfo
      },
      {
        path: '/blockdetail/:height',
        component:Blockdetail
      },
      {
        path: '/addressinfo/:page/:pagesize',
        component:Addressinfo
      },
      {
        path: '/addressdetail/:address',
        component:Addressdetail
      },
      {
        path:'/transactioninfo/:page/:pagesize',
        component:Transactioninfo
      },
      {
        path:'/transactiondetail/:txid',
        component:Transactiondetail
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
        path:'/filtersearch/:txid/:height/:address/:page/:pagesize/:begintime/:endtime',
        component:filtersearch
      }

    ]
  },
  { path: '*', redirect: '/' }
];

const router = new Router({
  mode: 'hash',
  scrollBehavior: () => ({ y: 0 }),
  routes
})

/*  router.beforeEach((to, from, next) => {
  alert(to)
  console.log(from)
  next()
})  */
export default router
/* export default new Router({
  mode: 'hash',
  scrollBehavior: () => ({ y: 0 }),
  routes
}) */

