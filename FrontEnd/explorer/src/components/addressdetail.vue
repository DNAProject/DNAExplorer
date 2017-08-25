<template>
  <div id="blockinfo">
    <Top></Top>
    <div class="content-monitor">
      <div class="row">
        <span class="col-sm-12">
        <h1>地址详情</h1>
        <hr />
        <span class="col-sm-12" style="margin:0 0 20px 0;padding: 0;">
          <ul>
            <li class="breadcrumb-li-active" @click="back">首页</li> 
            <li class="breadcrumb-li">/</li>
            <li class="breadcrumb-li-active" >地址</li>
            <li class="breadcrumb-li">/</li>
            <li class="breadcrumb-li-active">交易地址:{{address}}</li>
          </ul>
        </span>        
        <ul>
          <li>
            <span class="col-sm-3 col-xs-4">地址</span>
            <span class="col-sm-9 col-xs-6 underline">{{address}}</span>
          </li>
          <li v-if="txnByAddress.balance !== null">
            <span class="col-sm-3 col-xs-4">余额</span>
            <span class="col-sm-9 col-xs-6 underline">{{txnByAddress.balance}}</span>
          </li>
          <li v-else>
            <span class="col-sm-3 col-xs-4">余额</span>
            <span class="col-sm-9 col-xs-6 underline">暂无</span>
          </li>
          <li>
            <span class="col-sm-3 col-xs-4">收入总计</span>
            <span class="col-sm-9 col-xs-6 underline">{{txnByAddress.allincome}}</span>
          </li>
          <li>
            <span class="col-sm-3 col-xs-4">交易数</span>
            <span class="col-sm-9 col-xs-6 underline">{{txnByAddress.total}}</span>
          </li>
          <span class="col-sm-12 underline" style="height:20px;"></span>
          <span class="col-sm-12" style="padding:0px;">
            <span class="col-sm-5">
              <span id="summarytitle"class="col-sm-1 padding0 switch-btn item-active" @click="showSummary()">概要</span>
              <span id="detaillisttile"class="col-sm-1 padding0 switch-btn" @click="showTransaction()">交易</span>
            </span>
          </span>
          <div id="summary">
            <span class="col-sm-6">
              <h3 class="transaction-title">第一笔交易</h3>
              <li transaction-item>
                <span class="col-sm-4 left0 transaction-item">交易类型:</span>
                <span class="col-sm-8 transaction-item">{{firsttxn.txTypeDesc}}</span>
              </li>
              <li>
                <span class="col-sm-4 left0 transaction-item">区块高度:</span>
                <span class="col-sm-8 transaction-item click-able-item" @click="toBlockDetail(firsttxn.height)">{{firsttxn.height}}</span>
              </li>
              <li v-if="firsttxn.txId">
                <span class="col-sm-4 left0 transaction-item">散列值:</span>
                <span class="col-sm-8 transaction-item click-able-item" @click="toTransactiondetail(firsttxn.txId)">{{firsttxn.txId.substr(0,35)}}...</span>
              </li>
              <li v-for="(item,index) in firsttxn.inputs" v-if="firsttxn.inputs">
                <span class="col-sm-4 col-xs-4 left0 transaction-item ">输入{{index+1}}</span>
                <span class="col-sm-5 col-xs-6 underline click-able-item transaction-item" @click="toAddressDetail(item.ADDRESS)">{{item.ADDRESS.substr(0,25)}}</span>
                <span class="col-sm-3 col-xs-6 underline transaction-item" style="text-align:right">{{item.VALUE}} </span>
              </li>
              <li v-for="(item,index)  in firsttxn.outputs" v-if="firsttxn.outputs">
                <span class="col-sm-4 col-xs-4 left0 transaction-item">输出{{index+1}}</span>
                <span class="col-sm-5 col-xs-6 underline click-able-item transaction-item" @click="toAddressDetail(item.ADDRESS)">{{item.ADDRESS.substr(0,25)}}</span>
                <span class="col-sm-3 col-xs-6 underline transaction-item" style="text-align:right">{{item.VALUE}} </span>
              </li>
              <li transaction-item>
                <span class="col-sm-4 left0 transaction-item transaction-item">交易时间:</span>
                <span class="col-sm-8 transaction-item">{{firsttxn.datemap}}</span>
              </li>
            </span>
            <span class="col-sm-6">
              <h3 class="transaction-title">最近的交易</h3>
              <li transaction-item>
                <span class="col-sm-4 left0 transaction-item ">交易类型:</span>
                <span class="col-sm-8 transaction-item">{{lasttxn.txTypeDesc}}</span>
              </li>
              <li>
                <span class="col-sm-4 left0 transaction-item">区块高度:</span>
                <span class="col-sm-8 transaction-item click-able-item" @click="toBlockDetail(lasttxn.height)">{{lasttxn.height}}</span>
              </li>
              <li v-if="lasttxn.txId">
                <span class="col-sm-4 left0 transaction-item">散列值:</span>
                <span class="col-sm-8 transaction-item click-able-item" @click="toTransactiondetail(lasttxn.txId)">{{lasttxn.txId.substr(0,35)}}...</span>
              </li>
              <li v-for="(item,index) in lasttxn.inputs" v-if="lasttxn.inputs">
                <span class="col-sm-4 col-xs-4 left0  transaction-item">输入{{index+1}}</span>
                <span class="col-sm-5 col-xs-6 underline click-able-item transaction-item" @click="toAddressDetail(item.ADDRESS)">{{item.ADDRESS.substr(0,25)}}</span>
                <span class="col-sm-3 col-xs-6 underline transaction-item" style="text-align:right">{{item.VALUE}} </span>
              </li>
              <li v-for="(item,index)  in lasttxn.outputs" v-if="lasttxn.outputs">
                <span class="col-sm-4 col-xs-4 left0  transaction-item">输出{{index+1}}</span>
                <span class="col-sm-5 col-xs-6 underline click-able-item transaction-item" @click="toAddressDetail(item.ADDRESS)">{{item.ADDRESS.substr(0,25)}}</span>
                <span class="col-sm-3 col-xs-6 underline transaction-item" style="text-align:right">{{item.VALUE}} </span>
              </li>
              <li transaction-item>
                <span class="col-sm-4 left0 transaction-item">交易时间:</span>
                <span class="col-sm-8 transaction-item">{{lasttxn.datemap}}</span>
              </li>
            </span>
          </div> 
          <div id="detailList" class="hide">
            <li style="height: 20px;margin-bottom: 0px;">
              <span class="col-sm-2 pc">交易类型</span>
              <span class="col-sm-3 pc">交易块高度</span>
              <span class="col-sm-6 col-xs-12">交易编号</span>
              <span class="col-sm-1"> </span>
            </li>
            <li v-for="(item,index) in showList">
              <span class="col-sm-2 pc" style="margin-top:10px" :class="(index%2 == 1) ? 'withBcakgroundColor' : 'withoutBcakgroundColor' ">{{ item.txTypeDesc }}</span>
              <span class="col-sm-3 pc" style="margin-top:10px" :class="(index%2 == 1) ? 'withBcakgroundColor' : 'withoutBcakgroundColor' ">{{ item.height }}</span>
              <span class="col-sm-6 col-xs-12" style="margin-top:10px" @click="toTransactiondetail(item.txId)" :class="(index%2 == 1) ? 'withBcakgroundColor click-able-item' : 'withoutBcakgroundColor click-able-item' ">{{ item.txId}}</span>
              <div class="col-sm-1" @click="showDetails(index)" style="padding:0;margin-top:10px" :class="(index%2 == 1) ? 'withBcakgroundColor' : 'withoutBcakgroundColor' ">
                <span class="col-sm-12" style="padding:0;" >
                  <i class="glyphicon glyphicon-menu-right arrow" style="top:0px;" v-if="!showFlag[index]"></i>
                  <i class="glyphicon glyphicon-menu-down arrow"style="top:0px;" v-if="showFlag[index]"></i>
                </span>
              </div>
              <div v-if="showFlag[index]" style="padding:0px;" class="col-sm-12" :class="(index%2 == 1) ? 'withBcakgroundColor' : 'withoutBcakgroundColor' ">
                <span class="col-sm-12" style="padding:0px;">
                  <span class="col-sm-2  " style="text-align: left;">时间 :</span>
                  <span class="col-sm-10 ">{{ item.datemap }}</span>
                </span>
                <span class="col-sm-12" style="padding:0;"v-for="(subitem,index) in item.inputs">
                  <span class="col-sm-2">输入{{index+1}}</span>
                  <span class="col-sm-7 click-able-item" @click="toAddressDetail(subitem.ADDRESS)">{{ subitem.ADDRESS }}</span>
                  <span class="col-sm-3">{{ subitem.VALUE }}</span>
                </span>
                <span class="col-sm-12" style="padding:0;" v-for="(subitem,index) in item.outputs">
                  <span class="col-sm-2">输出{{index+1}}</span>
                  <span class="col-sm-7 click-able-item" @click="toAddressDetail(subitem.ADDRESS)">{{ subitem.ADDRESS }}</span>
                  <span class="col-sm-3">{{ subitem.VALUE }}</span>
                </span>
              </div>
            </li>
            <div id="page" v-show="allpage!=1">
              <ul class="pagination" >
                <li style="" @click="goto(1)" ><a>首页</a> </li>
                <li v-show="current != 1" @click="current-- && goto(current--)" style=""><a>上一页</a></li>
                <li v-for="index in pages" v-show="index>0" @click="goto(index)" :class="{'active':current == index}" :key="index">
                  <a style="font-size:14px;">{{index}}</a>
                </li>
                <li v-show="allpage != current && allpage != 0 && !isNaN(allpage)" @click="current++  &&  goto(current++)" style=""><a>下一页</a></li>
                <li style="" @click="goto(allpage)" :class="{'active':current == allpage}" :key="allpage"><a>共{{allpage}}页</a> </li>
              </ul>
            </div>
          </div>
        </ul>
        </span>
      </div>
    </div>
  </div>
</template>

<script>
  import '../assets/ebro/css/index.css'
  import { mapGetters, mapActions } from 'vuex'
  import Bottom from './bottom.vue'
  import Top from './head-active.vue'

  export default {
    computed: {
      ...mapGetters({
                     txnByAddress: 'txnByAddress',
                   }),
      pages:function(){
                var pag = [];
                  if( this.current < this.showItem ){ //如果当前的激活的项 小于要显示的条数
                       //总页数和要显示的条数那个大就显示多少条
                       console.log(1)
                       var i = Math.min(this.showItem,this.allpage);
                       while(i){
                           pag.unshift(i--);
                       }
                   }else{ //当前页数大于显示页数了
                       console.log(1)
                       var middle = this.current - Math.floor(this.showItem / 2 ),//从哪里开始
                           i = this.showItem;
                       if( middle >  (this.allpage - this.showItem)  ){
                           middle = (this.allpage - this.showItem) + 1
                       }
                       while(i--){
                           pag.push( middle++ );
                       }
                   }
                 return pag
               }
    },
    created() {
      this.address = this.$route.params.address
      var params = this.$route.params
      params.page=1
      params.pagesize=5
      for(var i=0;i<5;i++){
        this.showFlag[i]=false;
      }
      this.$store.dispatch('txnByAddress',params)
    },
    watch:{
      '$route':function(){
        this.address = this.$route.params.address
        var params = this.$route.params
        params.page=1
        params.pagesize=5
        for(var i=0;i<5;i++){
          this.showFlag[i]=false;
        }
        this.$store.dispatch('txnByAddress',params)
      }, 
      'txnByAddress':function(){
        if(this.txnByAddress.total == undefined){
          this.back()    
          alert("请输入正确的地址")      
          return
        }else{
          this.showList=this.txnByAddress.data
          for(var i=0;i<this.showList.length;i++){
            this.showList[i].datemap = this.showList[i].date.substr(0,4)+"-"+
                                          this.showList[i].date.substr(4,2)+"-"+
                                          this.showList[i].date.substr(6,2)+" "+
                                          this.showList[i].date.substr(8,2)+":"+
                                          this.showList[i].date.substr(10,2)+":"+
                                          this.showList[i].date.substr(12,2)          
          }
          if(this.showList.length==0){
            this.allnum=0
            this.allpage=1
          }else{
            this.allnum=this.txnByAddress.total
            this.allpage=Math.ceil(this.allnum/5)
          } 
          this.firsttxn = this.txnByAddress.firsttxn
          this.firsttxn.datemap = this.firsttxn.date.substr(0,4)+"-"+
                                          this.firsttxn.date.substr(4,2)+"-"+
                                          this.firsttxn.date.substr(6,2)+" "+
                                          this.firsttxn.date.substr(8,2)+":"+
                                          this.firsttxn.date.substr(10,2)+":"+
                                          this.firsttxn.date.substr(12,2)
          this.lasttxn = this.txnByAddress.lasttxn
          this.lasttxn.datemap = this.lasttxn.date.substr(0,4)+"-"+
                                          this.lasttxn.date.substr(4,2)+"-"+
                                          this.lasttxn.date.substr(6,2)+" "+
                                          this.lasttxn.date.substr(8,2)+":"+
                                          this.lasttxn.date.substr(10,2)+":"+
                                          this.lasttxn.date.substr(12,2)
        }
      }
    },
    methods: {
      back:function(){
        this.$router.push({'path': '/browser'})
      },
      toTransactiondetail:function(txid){
        this.$router.push({'path':'/transactiondetail/'+txid})
      },
      toBlockDetail:function(height){
        this.$router.push({'path':'/blockdetail/'+height})
      },
      showDetails: function(index) {
        if(this.showFlag[index] == false ){
          this.showFlag[index] = true;   
        }else{
          this.showFlag[index] = false;  
        }   
          this.showList.reverse().reverse();  
      },
      toAddressDetail:function(address){
        this.$router.push({'path':'/addressdetail/'+address})
        this.address =address
        var params = {}
        params.page=1
        params.pagesize=1
        params.address=address
        this.$store.dispatch('txnByAddress',params)
      },
      goto:function(index){
        var params = this.$route.params
        params.page=index
        params.pagesize=1
        this.$store.dispatch('txnByAddress',params)
      },
      showTransaction:function(){
        $('#detailList').removeClass('hide')
        $('#summary').addClass('hide')
        $('#summarytitle').removeClass('item-active')
        $('#detaillisttile').addClass('item-active')
      },
      showSummary:function(){
        $('#detailList').addClass('hide')
        $('#summary').removeClass('hide')
        $('#summarytitle').addClass('item-active')
        $('#detaillisttile').removeClass('item-active')
      },
      comdify:function(num){
        if(num == undefined){
          return "0";
        }
        var numStr = String(num);
        var numArr = numStr.split("");
        numArr = numArr.reverse();
        var resultStr = "";
        for(var i=0;i<numArr.length;i++){
          var j = i%3;
          resultStr = numArr[i]+resultStr;
          if(j==2&&i+1<numArr.length){
            resultStr = "," + resultStr;
          }
        }
        return resultStr;
      },
     filtersearch:function(){
        this.$router.push({'path':'/filtersearch/0/0/1/5/0/0'})
      }
     },
    data() {
      return {
        times: 0,
        allnum:'',
        allpage:1,
        showFlag:[],
        inputList:[],
        current:1,
        showItem:5,
        outputList:[],
        showList:[],
        firsttxn:{},
        lasttxn:{},
        dannum:'',
        normalnodeSuccess:false
      }
    },
    components: {
      Bottom,
      Top
    },
    beforeDestroy() {
      clearInterval(this.intervalBlock)
    }
  }
</script>

<style>
 .row{
   color:#e4e4e4;
 }
 .right0{
   padding-right:0px;
 }
 .left0{
   padding-left:0px;
 }
 .padding0{
   padding:0;
 }
 .danger{
   color: red;
   font-size: 12px;
 }
 .normal{
   color: #57d2ff;
   font-size: 14px;
 }
 .normal p{
   text-align: center;
   margin: 0;
 }
 #allnode,#normalnode{
   min-height:180px
 }
.nodeIcon{
    box-shadow: 0 0 25px #fff;
    -webkit-animation: nucleus_ 1s infinite linear;
    animation: nucleus_ 1s infinite linear;
    position: absolute;
    width: 10px;
    height: 10px;
    border-radius: 50%;
    background-color: rgb(87, 210, 255);
    top: -10px;
    left: 12px;
 }
.sampleIcon{
    position: absolute;
    width: 10px;
    height: 10px;
    border-radius: 50%;
    background-color: rgb(87, 210, 255);
 }
.error-icon{
  background-color: red;
}
.flag-icon-normal{
    color:#57d2ff; 
    background-color:#57d2ff;
    left: -14px;
    top: 5px; 
}
.flag-icon-error{
    color: red;
    background-color:red;
    left: -14px;
    top: 25px;
}
 @keyframes nucleus_{
    0%{
      box-shadow: 0 0 0 transparent;
    }
    0%{
      box-shadow: 0 0 25px #fff;
    }
    100%{
      box-shadow: 0 0 0 transparent;
    }
}
.numberRun {
    font-size: 30px;
    color: #b8b8c2 !important;
    max-height: 42.24px;
}
.showMoreDetail{
    position: relative;
    float: left;
    font-size: 12px;
    padding: 1px;
    width: 30%;
    min-width: 63px;
    left: 35%;
    border: 1px solid black;
    height: 20px;
    background-color: #0e67a9;
    border-radius: 5px;
    margin-top: 5px;  

}
.showMoreDetail:hover{
  cursor: pointer;
}
.pagination>li>a{
  border:0px;
  background-color: #212124;
}
.transaction-title{
  border-bottom: 1px solid transparent;
  border-color: #d0d0d0;
  padding-bottom:10px;
}
.transaction-item{
  border-bottom: 1px solid transparent;
  border-color: #d0d0d0;
  padding-bottom:5px; 
}
.hide{
  display:none;
}
.item-active{
  background-color: #565555;
  font-size: 25px;
  width: 50px;
}
.Separation-line{
  height: 20px;
  border-bottom: 1px solid rgb(250, 250, 250);
}
.underline{
  border-bottom: 1px solid rgb(250, 250, 250);
}
.withBcakgroundColor{
  background-color: #353434;
}
.breadcrumb-li-active{
  float:left;
  opacity:0.5;
  margin:0 2px;
}
.breadcrumb-li-active:hover{
  float:left;
  opacity:1 !important;
  color: white;
  margin:0 2px;
}
.breadcrumb-li{
  float:left;
  opacity:0.5;
  margin:0 2px;
}
.click-able-item{
  color: #05a2b5;
}
.click-able-item:hover{
  color: #08d2eb;
}
</style>
