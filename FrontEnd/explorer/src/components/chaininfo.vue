<template>
  <div id="chaininfo">
    <Top></Top>
  <div class="chaininfo">
  <div class="header">
      <div class="row">
        <div class="col-sm-2 col-xs-4 col-xs-offset-10 back" @click="back"> 返回首页</div>
      </div>
  </div>
    <div class="chaininfo-content pc">
      <div class="list-left col-sm-7 col-xs-12">
        <div class="list-left-block col-sm-12 col-xs-12">
          <h4><img src="../assets/images/block.png"/>&nbsp;区块列表</h4>
          <ul v-if="blockList.length>0">
          	<li class="list-titlename col-sm-12 col-xs-12">
          	  <span class="col-sm-3 col-xs-3">高度</span>
          	  <span class="col-sm-4 col-xs-4">生成时间</span>
          	  <span class="col-sm-2 col-xs-2">交易数</span>
          	  <span class="col-sm-3 col-xs-3">大小</span>
          	</li>
          	<li v-for="(item,index) in blockList" class="col-sm-12 col-xs-12 block" v-if="index<7" @click="showblockdetial(index)">
          	  <span class="col-sm-3 col-xs-3">{{item.height}}</span>
          	  <span class="col-sm-4 col-xs-4"><div class="time-item" >{{item.timestamp}}</div></span>
          	  <span class="col-sm-2 col-xs-2">{{item.txnum}}</span>
          	  <span class="col-sm-3 col-xs-3">{{item.size}}</span>
          	</li>
          </ul>
        </div>
        
        <div class="list-left-exchange col-sm-12 col-xs-12">
          <h4><img src="../assets/images/exchange.png"/>&nbsp;交易列表</h4>
          <ul v-if="degreeList.length>0">
            <li class="list-titlename col-sm-12 col-xs-12">
              <span class="col-sm-3 col-xs-3">交易ID</span>
              <span class="col-sm-4 col-xs-4">时间</span>
              <span class="col-sm-2 col-xs-2">高度</span>
              <span class="col-sm-3 col-xs-3">类型</span>
            </li>
            <li v-for="(item,index) in degreeList" class="col-sm-12 exhange col-xs-12" v-if="index<3" @click="showexchangedetial(index)">
              <span class="col-sm-3 col-xs-3 exchange-id">{{item.txId}}</span>
              <span class="col-sm-4 col-xs-4"><div class="time-item">{{item.date}}</div></span>
              <span class="col-sm-2 col-xs-2">{{item.height}}</span>
              <span class="col-sm-3 col-xs-3">{{item.txTypeDesc}}</span>
            </li>
          </ul>
          <ul v-else>
          	 <li class="no-exchange">暂时没有相应的交易信息！</li>
          </ul>
        </div>
      </div>
      
      <div class="list-right col-sm-5">
        <div class="list-right-block col-sm-12" >
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
            <li class="row" v-if="assetnameShow(exchangenum)">
              <span class="col-sm-4 list-titlename">资产类别：</span>
              <span class="col-sm-8">{{degreeList[exchangenum].assetname}}</span>
            </li>
<!--            <li class="row">
              <span class="col-sm-4 list-titlename">手续费：</span>
              <span class="col-sm-8">{{degreeList[exchangenum].fee}}</span>
            </li>-->
            <li class="row" v-if="inputsShow(exchangenum)">
              <span class="col-sm-4 list-titlename">交易输入：</span>
              <div class="col-sm-8">
                <li class="row row-ot" v-for="(item,index) in degreeList[exchangenum].inputs">
                  <span class="col-sm-8 ot-item-address">{{item.address}}</span>
                  <span class="col-sm-4 ot-item-value">-{{item.value}}</span>
                </li>
              </div>
            </li>
            <li class="row" v-if="outputsShow(exchangenum)">
              <span class="col-sm-4 list-titlename">交易输出：</span>
              <div class="col-sm-8">
                <li class="row row-ot" v-for="(item,index) in degreeList[exchangenum].outputs">
                  <span class="col-sm-8 ot-item-address">{{item.address}}</span>
                  <span class="col-sm-4 ot-item-value">{{item.value}}</span>
                </li>
              </div>
            </li>
          </ul>
          <ul v-else>
            <li class="no-exchange">暂时没有相应的交易信息！</li>
          </ul>
        </div>
      </div>
    </div>
    
    <!--手机端-->
    <div class="chaininfo-content phone">
      <div class="list-left  col-xs-12">
        <h4 class="row">
            <span id="block-title" class="col-xs-6 block-title active" @click="showblock"><img src="../assets/images/block.png"/>&nbsp;区块列表</span>
            <span id="exchange-title" class="col-xs-6 exchange-title" @click="showexchange"><img src="../assets/images/exchange.png"/>&nbsp;交易列表</span>
          </h4>
        <div class="list-left-block col-sm-12 col-xs-12" id="block">
          <ul >
            <li v-for="(item,index) in blockList" class="col-sm-12 col-xs-12 block" >
              <span class="col-xs-11 phone-list-left" @click="showblockdetials(index)">
                <span class="col-xs-12 title">区块hash:</span>
                <span class="col-xs-12 ">{{item.hash}}</span>
              </span>
              <span class="col-xs-1 text-right ico" @click="showblockdetials(index)">
                <i class="glyphicon glyphicon-chevron-right arrow" v-if="!showblocks[index]"></i>
                <i class="glyphicon glyphicon-chevron-down arrow" v-if="showblocks[index]"></i>
              </span>
              <div v-if="showblocks[index]">
              <span class="col-xs-12 phone-list-left">
                <span class="col-xs-3 title">高度:</span>
                <span class="col-xs-9 ">{{item.height}}</span>
              </span>
              <span class="col-xs-12 phone-list-left">
                <span class="col-xs-3 title">时间:</span>
                <span class="col-xs-9 ">{{item.timestamp}}</span>
              </span>
              <span class="col-xs-12 phone-list-left">
                <span class="col-xs-3 title">交易数:</span>
                <span class="col-xs-9 ">{{item.txnum}}</span>
              </span>
              <span class="col-xs-12 phone-list-left">
                <span class="col-xs-5 col-sm-3 title">区块Merkle根:</span>
                <span class="col-xs-7 ">{{item.merkleroot}}</span>
              </span>
              </div>
            </li>
          </ul>
        </div>
        
        <div v-if="degreeList.length>0" class="list-left-exchange hide col-sm-12 col-xs-12" id="exchange" >
          <ul>
            <li v-for="(item,index) in degreeList" class="col-sm-12 exhange col-xs-12" >
                <span class="col-xs-11 phone-list-left" @click="showexchangedetials(index)">
                  <span class="col-xs-12 title">交易ID:</span>
                  <span class="col-xs-12 ">{{item.txId}}</span>
                </span>
                <span class="col-xs-1 text-right ico" @click="showexchangedetials(index)">
                  <i class="glyphicon glyphicon-chevron-right arrow" v-if="!showexchanges[index]"></i>
                  <i class="glyphicon glyphicon-chevron-down arrow" v-if="showexchanges[index]"></i>
                </span>
              <div v-if="showexchanges[index]">
                <span class="col-xs-12 phone-list-left">
                  <span class="col-xs-4 title">交易时间:</span>
                  <span class="col-xs-8 ">{{item.date}}</span>
                </span>
                <span class="col-xs-12 phone-list-left">
                  <span class="col-xs-4 title">区块高度:</span>
                  <span class="col-xs-8 ">{{item.height}}</span>
                </span>
                <span class="col-xs-12 phone-list-left">
                  <span class="col-xs-4 title">类型:</span>
                  <span class="col-xs-8 ">{{item.txTypeDesc}}</span>
                </span>
                <span class="col-xs-12 phone-list-left" v-if="assetnameShow(exchangenum)">
                  <span class="col-xs-4 title">资产类别:</span>
                  <span class="col-xs-8 ">{{item.assetname}}</span>
                </span>
                <span class="col-xs-12 phone-list-left" v-if="inputsShow(exchangenum)">
                  <span class="col-xs-4 title">交易输入:</span>
                  <span v-for="(items,indexs) in degreeList[exchangenum].inputs" class="col-xs-8">
                    <span class="col-xs-12 ">{{items.address}}</span>
                    <span class="clo-xs-12 ">{{items.value}}</span>
                  </span>
                </span>
                <span class="col-xs-12 phone-list-left" v-if="outputsShow(exchangenum)">
                  <span class="col-xs-4 title">交易输出:</span>
                  <span v-for="(items,indexs) in degreeList[exchangenum].outputs" class="col-xs-8">
                    <span class="col-xs-12 ">{{items.address}}</span>
                    <span class="clo-xs-12 ">{{items.value}}</span>
                  </span> 
                </span>
              </div>
            </li>
          </ul>
        </div>
        <div v-else>
          <ul>
          	<li class="no-exchange">暂时没有相应的交易信息!</li>
          </ul>
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
    watch:{
      'degreeList':function(){
        var delist =this.degreeList;
        for(var i=0;i<delist.length;i++){
          var year=delist[i].date.substring(0,4); 
          var month=delist[i].date.substring(4,6);
          var day=delist[i].date.substring(6,8);
          var hour=delist[i].date.substring(8,10);
          var min=delist[i].date.substring(10,12);
          var sec=delist[i].date.substring(12,14);
          this.degreeList[i].date=year+'-'+month+'-'+day+' '+hour+':'+min+':'+sec;
        }
      }
    },
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
        exchangenum:0,
        showblocks:[false,false,false,false,false,false,false,false,false,false],
        showexchanges:[false,false,false,false,false,false,false,false,false,false]
      }
    },
    methods:{
      back:function(){
        this.$router.push({'path': '/monitor'})
      },
      showblockdetial:function(index){
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
      },
      assetnameShow:function(exchangenum){
        var flag = false;
        if((this.degreeList[exchangenum].txTypeDesc != "存证交易")&& (this.degreeList[exchangenum].txTypeDesc != "智能合约") ){
          flag = true;
        }
        return flag;
      },
      inputsShow:function(exchangenum){
        var flag = false;
        if(this.degreeList[exchangenum].txTypeDesc == "转移交易"){
          flag = true;
        }
        return flag;
      },
      outputsShow:function(exchangenum){
        var flag = false;
        if((this.degreeList[exchangenum].txTypeDesc == "发行交易")|| (this.degreeList[exchangenum].txTypeDesc == "转移交易") ){
          flag = true;
        }
        return flag;
      },
      showexchange:function(){
        $('#exchange').removeClass('hide')
        $('#block').addClass('hide')
        $('#block-title').removeClass('active')
        $('#exchange-title').addClass('active')
      },
      showblock:function(){
        $('#exchange').addClass('hide')
        $('#block').removeClass('hide')
        $('#block-title').addClass('active')
        $('#exchange-title').removeClass('active')
      },
      showblockdetials:function(index){
        if(this.showblocks[index] == false ){
          this.showblocks[index] = true;   
        }else{
          this.showblocks[index] = false;  
        }   
        this.blockList.reverse().reverse();
      },
      showexchangedetials:function(index){
        if(this.showexchanges[index] == false ){
          this.showexchanges[index] = true;   
        }else{
          this.showexchanges[index] = false;  
        }   
        this.degreeList.reverse().reverse();
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
