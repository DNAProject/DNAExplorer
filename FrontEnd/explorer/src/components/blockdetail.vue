<template>
  <div id="blockinfo">
    <Top></Top>
    <div class="content-monitor">
      <div class="row">
        <span class="col-sm-12">
        <h1>区块详情</h1>
        <hr />
        <span class="col-sm-12" style="margin:0 0 20px 0;padding: 0;">
          <ul>
            <li class="breadcrumb-li-active" @click="back">首页</li> 
            <li class="breadcrumb-li">/</li>
            <li class="breadcrumb-li-active" @click="toBlockInfo">区块</li>
            <li class="breadcrumb-li">/</li>
            <li class="breadcrumb-li-active">高度:{{height}}</li>
          </ul>
        </span>
        <ul>
          <li>
            <span class="col-sm-3 col-xs-4">高度</span>
            <span class="col-sm-9 col-xs-6 underline">{{block.height}}</span>
          </li>
          <li>
            <span class="col-sm-3 col-xs-4">时间</span>
            <span class="col-sm-9 col-xs-6 underline">{{block.date}}</span>
          </li>
          <li>
            <span class="col-sm-3 col-xs-4">交易数</span>
            <span class="col-sm-9 col-xs-6 underline">{{block.txnum}}</span>
          </li>
          <li>
            <span class="col-sm-3 col-xs-4">散列值</span>
            <span class="col-sm-9 col-xs-6 underline">{{block.hash}}</span>
          </li>
          <li>
            <span class="col-sm-3 col-xs-4">默克尔树根植</span>
            <span class="col-sm-9 col-xs-6 underline">{{block.merkleroot}}</span>
          </li>
          <li>
            <span class="col-sm-3 col-xs-4">上一块 散列值</span>
            <span class="col-sm-9 col-xs-6 underline click-able-item" @click="toBlockDetail(height-1)">{{block.prevblock}}</span>
          </li>
          <li v-if="block.nextblock">
            <span class="col-sm-3 col-xs-4">下一块 散列值</span>
            <span class="col-sm-9 col-xs-6 underline click-able-item" @click="toBlockDetail(height+1)">{{block.nextblock}}</span>
          </li>
<!--           <li else>
            <span class="col-sm-3 col-xs-4">下一块 散列值</span>
            <span class="col-sm-9 col-xs-6 underline">无</span>
          </li> -->
          <li>
            <span class="col-sm-3 col-xs-4">记账人</span>
            <span class="col-sm-9 col-xs-6 underline">{{block.miner}}</span>
          </li>
          <li>
            <span class="col-sm-3 col-xs-4">数据大小</span>
            <span class="col-sm-9 col-xs-6">{{block.size}}</span>
          </li>
          <span class="col-sm-12 underline" style="height:20px;"></span>
          <span class="col-sm-12 " style="padding:0px;">
            <span class="col-sm-1">
              <span id="detaillisttile"class="col-sm-1 padding0 switch-btn active " @click="showTransaction()">交易</span>
            </span>
            <span class="col-sm-5" style="margin-top:10px;">矿工交易已省略</span>
          </span>
          <div id="detailList">
            <div v-if="0 < showlist.length" >
            <li style="height: 20px;margin-bottom: 0px;">
              <span class="col-sm-3 pc listTitle underline">交易类型</span>
              <span class="col-sm-2 pc listTitle underline">交易块高度</span>
              <span class="col-sm-6 col-xs-12 listTitle underline">交易编号</span>
              <span class="col-sm-1"> </span>
            </li>
            <li v-for="(item,index) in showlist">
              <span class="col-sm-3 pc" style="margin-top:10px" :class="(index%2 == 1) ? 'withBcakgroundColor' : 'withoutBcakgroundColor' ">{{ item.txTypeDesc }}</span>
              <span class="col-sm-2 pc" style="margin-top:10px" :class="(index%2 == 1) ? 'withBcakgroundColor' : 'withoutBcakgroundColor' ">{{ item.height }}</span>
              <span class="col-sm-6 col-xs-12" style="margin-top:10px" @click="toTransactiondetail(item.txId)" :class="(index%2 == 1) ? 'withBcakgroundColor click-able-item' : 'withoutBcakgroundColor click-able-item' ">{{ item.txId}}</span>
              <div class="col-sm-1" @click="showDetails(index)" style="padding:0;margin-top:10px" :class="(index%2 == 1) ? 'withBcakgroundColor' : 'withoutBcakgroundColor' ">
                <span class="col-sm-12" style="padding:0;" >
                  <i class="glyphicon glyphicon-menu-right arrow" style="top:0px;" v-if="!showFlag[index]"></i>
                  <i class="glyphicon glyphicon-menu-down arrow"style="top:0px;" v-if="showFlag[index]"></i>
                </span>
              </div>
              <div v-if="showFlag[index]" style="padding:0px;" class="col-sm-12" :class="(index%2 == 1) ? 'withBcakgroundColor' : 'withoutBcakgroundColor' ">
                <span class="col-sm-12" style="padding:0px;">
                  <span class="col-sm-3  " style="text-align: left;">时间 :</span>
                  <span class="col-sm-9 ">{{ item.datemap }}</span>
                </span>
                <span class="col-sm-12" style="padding:0;"v-for="(subitem,index) in item.inputs">
                  <span class="col-sm-3">输入{{index+1}}</span>
                  <span class="col-sm-6 click-able-item" @click="toAddressDetail(subitem.ADDRESS)">{{ subitem.ADDRESS }}</span>
                  <span class="col-sm-3">{{ subitem.VALUE }}</span>
                </span>
                <span class="col-sm-12" style="padding:0;" v-for="(subitem,index) in item.outputs">
                  <span class="col-sm-3">输出{{index+1}}</span>
                  <span class="col-sm-6 click-able-item" @click="toAddressDetail(subitem.ADDRESS)">{{ subitem.ADDRESS }}</span>
                  <span class="col-sm-3">{{ subitem.VALUE }}</span>
                </span>
              </div>
            </li> 
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
                     blockByHeight: 'blockByHeight',
                   }),
      pages:function(){
        var pag = [];
        if( this.current < this.showItem ){ //如果当前的激活的项 小于要显示的条数
          //总页数和要显示的条数那个大就显示多少条
          var i = Math.min(this.showItem,this.allpage);
          while(i){
           pag.unshift(i--);
          }
        }else{ //当前页数大于显示页数了
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
      this.height = this.$route.params.height
      this.$store.dispatch('blockByHeight',this.$route.params)
    },
    watch:{
      '$route':function(){
        this.$store.dispatch('blockByHeight',this.$route.params) 
      },
      'blockByHeight':function(){
        if(this.blockByHeight.status != undefined){
          this.back()    
          alert("请输入正确的高度")      
          return
        }else{
          if(this.showFlag.length == 0){
            for(var i=0;i<this.blockByHeight.transaction.length;i++){
              this.showFlag[i]=false;
            }
          }
          this.showlist = this.blockByHeight.transaction
          this.showlist.reverse().reverse()
          this.block = this.blockByHeight.block
          var date = new Date(parseInt(this.block.timestamp+"000"));
          this.block.date = this.getLocalDate(date)
          /* this.block.reverse().reverse() */
          for(var i=0;i<this.showlist.length;i++){
            this.showlist[i].datemap = this.showlist[i].date.substr(0,4)+"-"+
                                       this.showlist[i].date.substr(4,2)+"-"+
                                       this.showlist[i].date.substr(6,2)+" "+
                                       this.showlist[i].date.substr(8,2)+":"+
                                       this.showlist[i].date.substr(10,2)+":"+
                                       this.showlist[i].date.substr(12,2)
          }
        }
      }
    },
    methods: {
      back:function(){
        this.$router.push({'path': '/browser'})
      },
      toBlockDetail:function(height){
        if(height > this.height){
          this.height++
        }else{
          if(height < this.height){
            this.height--
          }
        }
        this.$router.push({'path':'/blockdetail/'+height})
        this.$store.dispatch('blockByHeight',this.$route.params)
      },
      toTransactiondetail:function(txid){
        this.$router.push({'path':'/transactiondetail/'+txid})
      },
      toBlockInfo:function(){
        this.$router.push({'path':'/blockinfo/1/10'})
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
          this.showlist.reverse().reverse();  
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
      getLocalDate:function(date){
        var Month=date.getMonth() + 1;
        if(Month<10)
        Month="0"+Month
        var day=date.getDate();
        if(day<10){
          day="0"+day
        }
        var Hours=date.getHours();
        if(Hours<10){
          Hours="0"+Hours
        }
        var Minutes=date.getMinutes();
        if(Minutes<10){
          Minutes="0"+Minutes 
        }
        var Seconds=date.getSeconds();
        if(Seconds<10){
          Seconds="0"+Seconds
        }
        return date.getFullYear() + "-" + Month + "-" + day + " " + Hours + ":" + Minutes + ":" + Seconds;
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
        block:{},
        showlist:[],
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
.listTitle{
  font-size: 17px;
 /*  font-weight: bold; */
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
