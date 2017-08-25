<template>
  <div id="blockinfo">
    <Top></Top>
    <div class="content-monitor">
      <div class="row">
        <span class="col-sm-12">
        <h1>地址</h1>
        <hr />
        <span class="col-sm-12" style="margin:0 0 20px 0;padding: 0;">
          <ul>
            <li class="breadcrumb-li-active" @click="back">首页</li> 
            <li class="breadcrumb-li">/</li>
            <li class="breadcrumb-li-active">交易</li>
          </ul>
        </span>
        <ul>
          <li>
            <span class="col-sm-4 col-xs-4">地址</span>
            <span class="col-sm-2 col-xs-6">时间</span>
            <span class="col-sm-4 col-xs-4">地址</span>
            <span class="col-sm-1 col-xs-6">交易数</span>
            <span class="col-sm-1 pc">状态</span>
          </li>
          <li v-for="item in blockList">
            <span class="col-sm-4 col-xs-4">{{ item.miner }}</span>
            <span class="col-sm-2 pc">{{ item.timestamp }}</span>
            <span class="col-sm-4 col-xs-4">{{ item.miner }}</span>
            <span class="col-sm-1 col-xs-6">{{ item.txnum }}</span>
            <span class="col-sm-1 pc">{{ item.size }}</span> 
          </li>
        </ul>
        </span>
        <div id="page" v-show="allpage!=1">
          <ul class="pagination" >
            <li style="" @click="goto(1)" ><a>First &nbsp;Page</a> </li>
            <li v-show="current != 1" @click="current-- && goto(current--)" style=""><a>上一页</a></li>
            <li v-for="index in pages" v-show="index>0" @click="goto(index)" :class="{'active':current == index}" :key="index">
              <a>{{index}}</a>
            </li>
            <li v-show="allpage != current && allpage != 0 && !isNaN(allpage)" @click="current++  &&  goto(current++)" style=""><a>Next Page</a></li>
            <li style="" @click="goto(allpage)" :class="{'active':current == allpage}" :key="allpage"><a>Total：{{allpage}}</a> </li>
          </ul>
        </div>
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
      ...mapGetters({blockList: 'blockList'}),
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
      this.$store.dispatch('blockList')
    },
    watch:{
      'blockList':function(){
        if(this.showFlag.length == 0){
          for(var i=0;i<this.blockList.length;i++){
            this.showFlag[i]=false;
          }
        }
        if(this.blockList.length==0){
          this.allnum=0
          this.allpage=1
        }else{
          this.allnum=this.blockList.length
          this.allpage=Math.ceil(this.allnum/10)
        } 
      }
    },
    methods: {
      back:function(){
        this.$router.push({'path': '/monitor'})
      },
      showDetails: function(index) {
        if(this.showFlag[index] == false ){
          this.showFlag[index] = true;   
        }else{
          this.showFlag[index] = false;  
        }   
          this.degreeList.reverse().reverse();  
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
/*.nodeIcon1,.nodeIcon2,.nodeIcon3,.nodeIcon4 {
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
 }*/
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
.click-able-item{
  color: #05a2b5;
}
.click-able-item:hover{
  color: #08d2eb;
}
</style>
