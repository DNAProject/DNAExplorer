<template>
  <div id="view-index">
  <div class="promotion-bar homepage-announcement hidden-tablet hidden-phone">
    <div class="container">
      <section>
        <h1>最新区块</h1>
        <hr />
        <div class="my-table striped">
          <div class="row title">
            <div class="col-sm-3 col-xs-3">高度</div>
            <div class="col-sm-4 col-xs-3">散列值</div>
            <div class="col-sm-3 col-xs-3">时间</div>
            <div class="col-sm-2 hidden-xs">数据大小</div>
          </div>
          <div class="empty">目前没有区块</div>
          <div id="div_blocks">
            <ul v-for="item in blockList">
              <li id="block_li_template" class="row">
                <div class="col-sm-3 col-xs-3 block-height">{{item.height}}</div>
                <div class="col-sm-4 col-xs-3">
                  <a class="block-hash">{{item.hashS}}</a>
                </div>
                <div class="col-sm-3 col-xs-3 block-time">{{item.timestamp}}</div>
                <div class="col-sm-2 hidden-xs block-size">{{item.size}} Bytes</div>
              </li>
            </ul>
            <ul id="ul_blocks">
            </ul>
          </div>
        </div>
      </section>
      <section>
        <h1>最新状态</h1>
        <hr />
        <div class="my-table striped">
          <div class="row title">
            <div class="col-sm-2 col-xs-3">类型</div>
            <div class="col-sm-3 col-xs-3">存证时间</div>
            <div class="col-sm-3 col-xs-3">存证编号</div>
            <div class="col-sm-2 col-xs-3">存证人</div>
            <div class="col-sm-2 col-xs-3">存证块高度</div>
          </div>
          <div id="div_txs">
            <ul v-for="item in degreeList">
              <li id="li_template" class="row">
                <div class="col-sm-2 col-xs-3 tx-type">{{ item.txType }}</div>
                <div class="col-sm-3 col-xs-3 tx-time">{{ item.date }}</div>
                <div class="col-sm-3 col-xs-3" @click="degreeCz(item.txId,item.txType)">
                  <a class="tx-hash">{{ item.txIdS }}</a>
                </div>
                <div class="col-sm-2 col-xs-3 tx-oprNm">{{ item.lastOprNm }}</div>
                <div class="col-sm-2 col-xs-3 tx-height">{{ item.height }}</div>
              </li>
            </ul>
            <ul id="ul_txs">
            </ul>
          </div>
        </div>
      </section>
      <section>
        <h1>节点信息</h1>
        <hr />
        <div class="my-table striped">
          <div class="row title">
            <div class="col-sm-3 col-xs-3">节点名称</div>
            <div class="col-sm-2 col-xs-3">出块状态</div>
            <div class="col-sm-2 col-xs-3">网络状态</div>
            <div class="col-sm-2 col-xs-3">节点类型</div>
            <div class="col-sm-3 col-xs-3">时间</div>
          </div>
          <ul id="ul_node" v-for="item in nodeList">
            <li class="row">
              <div class="col-sm-3 col-xs-3 node-name">{{ item.url }}</div>
              <div class="col-sm-2 col-xs-3">
                <a class="node-blockFlag">{{ item.blockStatus }}</a>
              </div>
              <div class="col-sm-2 col-xs-3 node-rpcFlag">{{ item.rpcStatus }}</div>
              <div class="col-sm-2 col-xs-3 node-type">{{ item.type }}</div>
              <div class="col-sm-3 col-xs-3 node-time">{{ item.date }}</div>
            </li>
          </ul>
        </div>
      </section>
    </div>
  </div>
  <div class="system-state">
    <div class="container">
      <section>
        <h1>节点状态</h1>
        <br /><br />
        <div class="row text-center">
          <div class="col-sm-3 col-xs-6 cell-bordered-right">
            <h3>节点数量</h3>
            <!--<div class="value">{{nodeStatus.totalCount}}</div>-->
            <div class="value">{{ nodeStatus.totalCount }}</div>
            <div class="unit">个</div>
          </div>
          <div class="col-sm-3 col-xs-6 cell-bordered-right">
            <h3>故障节点</h3>
   <!--         <div class="value">{{nodeStatus.errorNum}}</div>-->
            <div class="value">{{ nodeStatus.errorNum }}</div>
            <div class="unit">个</div>
          </div>
          <div class="col-sm-3 col-xs-6 cell-bordered-right">
            <h3>正常节点</h3>
         <!--   <div class="value">{{nodeStatus.correctNum}}</div>-->
            <div class="value">{{ nodeStatus.correctNum }}</div>
            <div class="unit">个</div>
          </div>
          <div class="col-sm-3 col-xs-6">
            <h3>故障节点比例</h3>
           <!-- <div class="value">{{nodeStatus.errorPer}}</div>-->
            <div class="value">{{ nodeStatus.errorPer }}</div>
            <div class="unit">%</div>
          </div>
        </div>
        <br />
      </section>
    </div>
  </div>
  </div>
</template>
<script>
  import { mapGetters, mapActions } from 'vuex'
  export default {
    name:'Home',
    computed: mapGetters({
      degreeList: 'degreeList',
      blockList: 'blockList',
      nodeList: 'nodeList',
      nodeStatus: 'nodeStatus'
/*      blockStatus: 'blockStatus'*/
    }),
    created () {
      this.$store.dispatch('degreeList')
      this.$store.dispatch('blockList')
      this.$store.dispatch('nodeList')
      this.$store.dispatch('nodeStatus')
/*      this.$store.dispatch('blockStatus')*/
      this.intervalBlock = setInterval(() => {
/*        this.$store.dispatch('blockStatus')*/
        this.$store.dispatch('nodeStatus')
/*        this.$store.dispatch('lastBlock')
        this.$store.dispatch('lastDegree')*/
        this.$store.dispatch('blockList')
        this.$store.dispatch('degreeList')
        this.$store.dispatch('nodeList')
      }, 500)
    },
    beforeDestroy () {
      clearInterval(this.intervalBlock)
    },
    methods: {
      degreeCz: function(txId,txType) {
        if(txType == '数据加工存证' || txType == '数据使用存证'){

        }else {
          this.$router.push({'path': '/degreeCz/'+txId+'/'+'01'})
        }
      }
    },
    data () {
      return {
        isInputShow: true
      };
    },
    components: {
    }
  }
</script>
