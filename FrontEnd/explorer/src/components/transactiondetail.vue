<template>
  <div id="blockinfo">
    <Top></Top>
    <div class="content-monitor">
      <div class="row">
        <span class="col-sm-12">
        <h1>交易详情</h1>
        <hr />
        <span class="col-sm-12" style="margin:0 0 20px 0;padding: 0;">
          <ul>
            <li class="breadcrumb-li-active" style="float:left;opacity:0.5;margin:0 2px;" @click="back">首页</li> 
            <li class="breadcrumb-li" style="float:left;opacity:0.5;margin:0 2px;">/</li>
            <li class="breadcrumb-li-active" @click="toTransactionInfo" style="float:left;opacity:0.5;margin:0 2px;">交易</li>
            <li class="breadcrumb-li" style="float:left;opacity:0.5;margin:0 2px;">/</li>
            <li class="breadcrumb-li-active" style="float:left;opacity:0.5;margin:0 2px;">交易ID:{{txid}}</li>
          </ul>
        </span>
        <ul>
          <li>
            <span class="col-sm-3 col-xs-4">高度</span>
            <span class="col-sm-9 col-xs-6 underline click-able-item" @click="toBlockDetail(txnByTxid.height)">{{txnByTxid.height}}</span>
          </li>
          <li>
            <span class="col-sm-3 col-xs-4">交易ID</span>
            <span class="col-sm-9 col-xs-6 underline">{{txnByTxid.txId}}</span>
          </li>
          <li v-for="(item,index) in txnByTxid.inputs">
            <span class="col-sm-3 col-xs-4">输入{{index+1}}</span>
            <span class="col-sm-7 col-xs-8 underline click-able-item" @click="toAddressDetail(item.ADDRESS)">{{item.ADDRESS}}</span>
            <span class="col-sm-2 col-xs-6 underline" style="text-align:right">{{item.VALUE}} </span>
          </li>
          <li v-for="(item,index)  in txnByTxid.outputs">
            <span class="col-sm-3 col-xs-4">输出{{index+1}}</span>
            <span class="col-sm-7 col-xs-8 underline click-able-item" @click="toAddressDetail(item.ADDRESS)">{{item.ADDRESS}}</span>
            <span class="col-sm-2 col-xs-6 underline" style="text-align:right">{{item.VALUE}} </span>
          </li>
<!--           <li>
            <span class="col-sm-3 col-xs-4">输入金额</span>
            <span class="col-sm-9 col-xs-6 underline">-{{txnByTxid.fromNum}}</span>
          </li>
          <li>
            <span class="col-sm-3 col-xs-4">输出金额</span>
            <span class="col-sm-9 col-xs-6 underline">{{txnByTxid.toNum}}</span>
          </li> -->
          <li>
            <span class="col-sm-3 col-xs-4">交易类别</span>
            <span class="col-sm-9 col-xs-6 underline">{{txnByTxid.txTypeDesc}}</span>
          </li>
          <li>
            <span class="col-sm-3 col-xs-4">交易时间</span>
            <span class="col-sm-9 col-xs-6 underline">{{txnByTxid.datemap}}</span>
          </li>
          <span class="col-sm-12 underline" style="height:20px;"></span>
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
                     txnByTxid:'txnByTxid'
                   })
    },
    created() {
      this.txid = this.$route.params.txid
      this.$store.dispatch('txnByTxid',this.$route.params)
    },
    watch:{
      '$route':function(){
        this.txid = this.$route.params.txid
        this.$store.dispatch('txnByTxid',this.$route.params)
      },
      'txnByTxid':function(){
        if(this.txnByTxid.height == undefined){
          this.back()    
          alert("请输入正确的交易ID")      
          return
        }else{
          this.txnByTxid.datemap = this.txnByTxid.date.substr(0,4)+"-"+
                                  this.txnByTxid.date.substr(4,2)+"-"+
                                  this.txnByTxid.date.substr(6,2)+" "+
                                  this.txnByTxid.date.substr(8,2)+":"+
                                  this.txnByTxid.date.substr(10,2)+":"+
                                  this.txnByTxid.date.substr(12,2)
        }
      }
    },
    methods: {
      back:function(){
        this.$router.push({'path': '/browser'})
      },
      toBlockDetail:function(height){
        this.$router.push({'path':'/blockdetail/'+height})
      },
      toTransactionInfo:function(){
        this.$router.push({'path':'/transactioninfo/1/15'})
      },
      toAddressDetail:function(address){
        this.$router.push({'path':'/addressdetail/'+address})
      },
      showDetails: function(index) {
        if(this.showFlag[index] == false ){
          this.showFlag[index] = true;   
        }else{
          this.showFlag[index] = false;  
        }   
          this.degreeList.reverse().reverse();  
      },
      showTransaction:function(){
        $('#detailList').removeClass('hide')
        $('#summary').addClass('hide')
        $('#summarytitle').removeClass('active')
        $('#detaillisttile').addClass('active')
      },
      showSummary:function(){
        $('#detailList').addClass('hide')
        $('#summary').removeClass('hide')
        $('#summarytitle').addClass('active')
        $('#detaillisttile').removeClass('active')
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
.active{
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
  cursor: pointer;
  color: #08d2eb;
}
</style>
