<template>
  <div id="chaininfo">
    <Top></Top>
  <div class="chaininfo">
  <div class="header">
      <div class="row">
        <div class="col-sm-2 col-xs-offset-10 back" @click="back"> 返回首页</div>
      </div>
  </div>
    <div class="chaininfo-content">
      <div class="list-left col-sm-7">
        <div class="list-left-block col-sm-12">
          <h4><img src="../assets/images/block.png"/>&nbsp;区块列表</h4>
          <ul>
          	<li class="list-titlename col-sm-12">
          	  <span class="col-sm-3">高度</span>
          	  <span class="col-sm-3">生成时间</span>
          	  <span class="col-sm-3">交易数</span>
          	  <span class="col-sm-3">大小</span>
          	</li>
          	<li v-for="(item,index) in blockList" class="col-sm-12 block"v-if="index<7" @click="showblockdetial(index)">
          	  <span class="col-sm-3">{{item.height}}</span>
          	  <span class="col-sm-3 time-item">{{item.timestamp}}</span>
          	  <span class="col-sm-3">{{item.txnum}}</span>
          	  <span class="col-sm-3">{{item.size}}</span>
          	</li>
          </ul>
        </div>
        
        <div class="list-left-exchange col-sm-12">
          <h4><img src="../assets/images/exchange.png"/>&nbsp;交易列表</h4>
          <ul>
            <li class="list-titlename col-sm-12">
              <span class="col-sm-3">交易ID</span>
              <span class="col-sm-3">时间</span>
              <span class="col-sm-3">高度</span>
              <span class="col-sm-3">类型</span>
            </li>
            <li v-for="(item,index) in degreeList" class="col-sm-12 exhange" v-if="index<3" @click="showexchangedetial(index)">
              <span class="col-sm-3 exchange-id">{{item.txId}}</span>
              <span class="col-sm-3 time-item"><div class="time-item">{{item.date}}</div></span>
              <span class="col-sm-3">{{item.height}}</span>
              <span class="col-sm-3">{{item.txTypeDesc}}</span>
            </li>
          </ul>
        </div>
      </div>
      
      <div class="list-right col-sm-5">
        <div class="list-right-block col-sm-12">
          <h4 class="detial"><img src="../assets/images/block.png"/>&nbsp;区块明细</h4>
          <ul v-if="blockList.length>0">
          	<li class="row">
          	  <span class="col-sm-4 list-titlename">高度：</span>
          	  <span class="col-sm-8">{{blockList[blocknum].height}}</span>
          	</li>
          	<li class="row">
          	  <span class="col-sm-4 list-titlename">区块时间：</span>
          	  <span class="col-sm-8">{{blockList[blocknum].timestamp}}</span>
          	</li>
          	<li class="row">
          	  <span class="col-sm-4 list-titlename">交易数量：</span>
          	  <span class="col-sm-8">{{blockList[blocknum].txnum}}</span>
          	</li>
          	<li class="row">
          	  <span class="col-sm-4 list-titlename">区块hash：</span>
          	  <span class="col-sm-8">{{blockList[blocknum].hash}}</span>
          	</li>
          	<li class="row">
          	  <span class="col-sm-4 list-titlename ">区块MerKle根:</span>
          	  <span class="col-sm-8">{{blockList[blocknum].merkleroot}}</span>
          	</li>
          </ul>
        </div>
        <div class="list-right-exchange col-sm-12">
          <h4 class="detial"><img src="../assets/images/exchange.png"/>&nbsp;交易明细</h4>
          <ul v-if="degreeList.length>0">
            <li class="row">
              <span class="col-sm-4 list-titlename">交易ID：</span>
              <span class="col-sm-8"> {{degreeList[exchangenum].txId}}</span>
            </li>
            <li class="row">
              <span class="col-sm-4 list-titlename">区块高度：</span>
              <span class="col-sm-8">{{degreeList[exchangenum].height}}</span>
            </li>
            <li class="row">
              <span class="col-sm-4 list-titlename">发起时间：</span>
              <span class="col-sm-8">{{degreeList[exchangenum].date}}</span>
            </li>
            <li class="row">
              <span class="col-sm-4 list-titlename">类型：</span>
              <span class="col-sm-8">{{degreeList[exchangenum].txTypeDesc}}</span>
            </li>
            <li class="row">
              <span class="col-sm-4 list-titlename">手续费：</span>
              <span class="col-sm-8">{{degreeList[exchangenum].fee}}</span>
            </li>
          </ul>
        </div>
      </div>
      
      <div v-show="degreeList.length==0">
        <div class="row chain-list chain-list-none" >
                     暂时没有相应的交易信息！
        </div>
      </div>
    </div>
    </div>
   
    </div>
</template>

<script>
  import '../assets/ebro/css/chaininfo.css'
  import Bottom from './bottom.vue'
  import Top from './head.vue'
  import { mapGetters, mapActions } from 'vuex'


  export default {
    computed: mapGetters({
      degreeList: 'degreeList',
      blockList: 'blockList',
      blockStatus: 'blockStatus'
    }),
    created () {
      this.$store.dispatch('degreeList')
     this.$store.dispatch('blockList')
      this.$store.dispatch('blockStatus')
      this.intervalBlock = setInterval(() => {
        this.$store.dispatch('blockStatus')
        this.$store.dispatch('blockList')
        this.$store.dispatch('degreeList')
      }, 2000)

    },
    data () {
      return {
        blocknum: 0,
        exchangenum:0
      }
    },
    methods:{
      back:function(){
        this.$router.push({'path': '/monitor'})
      },
      showblockdetial:function(index){
        debugger
        for (var i=0;i<(this.blockList.length<7?this.blockList.length:7);i++) {
          $(".block").eq(i).removeClass('itemSelected');
        }
        $(".block").eq(index).addClass('itemSelected')
        this.blocknum=index
      },
      showexchangedetial:function(index){
        for (var i=0;i<(this.blockList.length<7?this.blockList.length:7);i++) {
          $(".exhange").eq(i).removeClass('itemSelected');
        }
        $(".exhange").eq(index).addClass('itemSelected')
        this.exchangenum=index
      }
    },
    components: {
      Bottom,
      Top
    },
    beforeDestroy () {
      clearInterval(this.intervalBlock)
    },
   }
</script>

<style>
</style>
