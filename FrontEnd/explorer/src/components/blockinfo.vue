<template>
  <div id="blockinfo">
    <Top></Top>
    <div class="content-monitor">
      <div class="row">
        <span class="col-sm-12">
        <h1>区块</h1>
        <hr />
        <span class="col-sm-12" style="margin:0 0 20px 0;padding: 0;">
          <ul>
            <li @click="back" class="breadcrumb-li-active" style="float:left;opacity:0.5;margin:0 2px;">首页</li> 
            <li class="breadcrumb-li" style="float:left;opacity:0.5;margin:0 2px;">/</li>
            <li class="breadcrumb-li-active" style="float:left;opacity:0.5;margin:0 2px;">区块</li>
          </ul>
        </span>
        <ul class="text-center"> 
          <li >
            <!--<span class="col-sm-2 col-xs-4">高度</span>
            <span class="col-sm-5 col-xs-6">时间</span>
            <span class="col-sm-2 col-xs-6">交易数</span>
            <span class="col-sm-3 pc">数据大小</span>-->
            <span class="col-sm-2 col-xs-3 title">高度</span>
            <span class="col-sm-6 col-xs-6 title">区块MerKle根</span>
            <span class="col-sm-2 col-xs-6 title">时间</span>
            <span class="col-sm-1 col-sx-3 title">交易数</span>
            <span class="col-sm-1 pc title">数据大小</span>
          </li>
          <li v-for="item in showlist" style="line-height: 30px;">
            <!--<span class="col-sm-2 col-xs-4" @click="toBlockDetail(item.height)">{{ item.height }}</span>
            <span class="col-sm-5 pc">{{ item.timestamp }}</span>
            <span class="col-sm-2 col-xs-6">{{ item.txnum }}</span>
            <span class="col-sm-3 pc">{{ item.size }}</span> -->
            <span class="col-sm-2 col-xs-3 click-able-item" style="cursor:pointer" @click="toBlockDetail(item.height)">{{ item.height }}</span>
            <span class="col-sm-6 col-xs-3">{{ item.merkleroot }}</span>
            <span class="col-sm-2 col-xs-6">{{ item.timestamp }}</span>
            <span class="col-sm-1 col-xs-3">{{ item.txnum }}</span>
            <span class="col-sm-1 pc">{{ item.size }} 字节</span> 
          </li>
        </ul>
        </span>
        <div id="page" v-show="allpage!=1">
          <ul class="pagination" >
            <li style="" @click="goto(1)" ><a>首页</a> </li>
            <li v-show="current != 1" @click="current-- && goto(current--)" style=""><a>上一页</a></li>
            <li v-for="index in pages" v-show="index>0" @click="goto(index)" :class="{'active':current == index}" :key="index">
              <a style="font-size:14px;">{{index}}</a>
            </li>
            <li v-show="allpage != current && allpage != 0 && !isNaN(allpage)" @click="current++  &&  goto(current++)" style=""><a>下一页</a></li>
            <li style="" @click="goto(allpage)" ><a>末页</a> </li>
            <li style=""  :class="{'active':current == allpage}" :key="allpage"><a>共：{{allpage}}页</a> </li>
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
      ...mapGetters({blockListNew: 'blockListNew'}),
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
      this.$store.dispatch('blockListNew',this.$route.params)
    },
    watch:{
      'blockListNew':function(){
/*         if(this.showFlag.length == 0){
          for(var i=0;i<this.blockListNew.length;i++){
            this.showFlag[i]=false;
          }
        } */
        this.showlist=this.blockListNew.data 
      },
      'showlist':function(){
        if(this.showlist.length==0){
          this.allnum=0
          this.allpage=1
        }else{
          this.allnum=this.blockListNew.total
          this.allpage=Math.ceil(this.allnum/this.$route.params.pagesize)
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
      showDetails: function(index) {
        if(this.showFlag[index] == false ){
          this.showFlag[index] = true;   
        }else{
          this.showFlag[index] = false;  
        }   
          this.degreeList.reverse().reverse();  
      },
      goto:function(index){
        if(index == this.current) {
          return;
        }else{
          this.current = index;
        }
        this.$router.push({'path':'/blockinfo/'+this.current+'/15'})
        this.$store.dispatch('blockListNew',this.$route.params)
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
        current:1,
        showItem:5,
        showFlag:[],
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
.title{
	font-size: 16px;
	font-weight: 600;
	padding-bottom: 10px;
	color: white;
}
@media only screen and (max-width:768px ) {
	.pc{
		display: none;
	}
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
