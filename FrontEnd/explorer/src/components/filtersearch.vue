<template>
<div id="filterdetail">
 <Top></Top>
<!--    <div class="header">
      <div class="row">
        <div class="col-sm-2 col-xs-4 col-xs-offset-10 back" @click="back"> 返回首页</div>
      </div>
  </div> -->
 <div class="select-date container pc row" id="select-date" v-show="this.size!=64">
<!--     <span class="filter-cakey-key filter-margin-top col-sm-6" style="color:white;">Select By:</span>
    <span class="filter-cakey-value filter-margin-top col-sm-6">
    <select v-model="selectBy" style="color: black;">
      <option >Please Select</option>
      <option value='namespace'>namespace</option>
      <option value='address'>address</option>
    </select>
    </span> -->
<!--     <span class="filter-cakey-key filter-margin-top col-sm-6" style="color:white;">Select the NameSpace:</span>
    <span class="filter-cakey-value filter-margin-top col-sm-6">
    <select v-model="namespace" style="color: black;">
      <option >Please Select</option>
      <option v-for="(item,index) in nameSpaceList" :value='item'>{{item}}</option>
    </select>
    </span> -->
    <div class="col-sm-2 serach-option" style="color:white;" > </div>
    <div class="col-sm-9" style="margin-top: 10px;">
    	<span class="filter-appid-key filter-margin-top col-sm-2 " style="color:white;">开始时间:</span>
    	<span class="filter-time-start filter-margin-top col-sm-2" style="color:white;"><input v-model="value" class="starttime" name="" id="date" value=""   style="border-radius: 5px;outline: none;color:black;" placeholder="例：2017-01-01"/></span>
    	<span class="filter-appid-key filter-margin-top col-sm-2 " style="color:white;">结束时间:</span>
    	<span class="filter-time-start filter-margin-top col-sm-2" style="color:white;"><input v-model="value2" class="endtime" name="" id="date" value="" @change="getDate"   style="color:black;border-radius: 5px;outline: none;" placeholder="例：2017-01-10"/></span>
    	</span>
    </div>
    <div class="col-sm-2 serach-option" style="color:white;"> </div>
    <div class="col-sm-9" style="margin-top:10px;">
      <div class="col-sm-12" style="padding:0">
    	  <span class="filter-appid-key filter-margin-top col-sm-2 " style="color:white;text-align:right;">高度查询:</span>
    	  <span class="filter-appid-value filter-margin-top col-sm-7"><input v-model="height" style="border-radius: 5px;outline: none;width: 100%;color: black;"></span>
    	</div>
      <div class="col-sm-12" style="padding:0">
        <span class="filter-appid-key filter-margin-top col-sm-2" style="color:white;">地址查询:</span>
    	  <span class="filter-appid-value filter-margin-top col-sm-7"><input v-model="address" style="border-radius: 5px;outline: none;width: 100%;color: black;"></span>
      </div>
      <div class="col-sm-12" style="padding:0">
    		<span class="filter-appid-key filter-margin-top col-sm-2" style="color:white;">交易查询:</span>
    		<span class="filter-appid-value filter-margin-top col-sm-7"><input v-model="txid" style="border-radius: 5px;outline: none; width: 100%;color: black;"></span>
      </div>
    </div>
    <!--修改：
    	<div class="col-sm-11">
    	  <span class="col-sm-4">
    	  	<span class="col-sm-5"></span>
    	  	<span class="col-sm-7"></span>
    	  </span>
        </div>   
    -->
    <span class="col-sm-12  filter-button"><button style="width:50%;max-width:90px;color:black;" @click="filterData()">查询</button></span>
 </div>
 <div class="select-date container phone" id="select-date" v-show="this.size!=64">
    <span  style="color:white;" >Select By:
      <select v-model="selectBy" style="color: black;">
        <option >Please Select</option>
        <option value='namespace'>namespace</option>
        <option value='address'>account</option>
      </select>
    </span>
    <div v-if="selectBy =='namespace'">
      <span  style="color:white;" >SELECT THE NAMESPACE:
        <select v-model="nameSpace" style="color: black;">
          <option value="default">Please Select</option>
          <option v-for="(item,index) in nameSpaceList" value='item'>{{item}}</option>
        </select>
      </span>
      <span  style="color:white;">ENTRY THE KEY:
      <input v-model="key" style="border-radius: 5px;outline: none;"></span>
      <span style="color:white;">STARTTIME：<input v-model="value" type="date" name="" id="date" value=""   style="border-radius: 5px;outline: none;color:black;" placeholder="例：2017-01-01"/></span>
      <span style="color:white;">ENDTIME：<input v-model="value2" type="date" name="" id="date" value="" @change="getDate"   style="color:black;border-radius: 5px;outline: none;" placeholder="例：2017-01-10"/></span>
    </div>
    <div v-if="selectBy !=='namespace'">
      <span  style="color:white;">ENTRY THE ACCOUNT:
      <input v-model="address" style="border-radius: 5px;outline: none;"></span>
    </div>
    <button style="width:60%;" @click="filterData">查询</button>
 </div>
 <div class="detial-content container" style="color:white;">
  
   <h1  style="color:white;">搜索结果  <span style="font-size: 16px;"> 共:{{ allnum }}条</span></h1>

     <!-- pc端 -->
    <div class="main-content pc" >
      <ul   class="text-left col-sm-12" style="margin-bottom:20px;border-bottom: 1px solid #F5F5F5;font-size:18px;">
        <span class="col-sm-2 pc">交易类型</span>
        <span class="col-sm-3 pc">交易块高度</span>
        <span class="col-sm-6 col-xs-12">交易编号</span>
        <span class="col-sm-1"> </span>
      </ul>
      <ul  class="text-left col-sm-12" style="margin-bottom:20px;border-bottom: 1px solid #F5F5F5;" v-for="(item,index) in showlist">
        <div class="col-sm-12" >
          <span class="col-sm-2 pc" >{{ item.txTypeDesc }}</span>
          <span class="col-sm-3 pc click-able-item"  style="cursor:pointer" @click="toBlockDetail(item.height)" >{{ item.height }}</span>
          <span class="col-sm-6 col-xs-12 click-able-item"  style="cursor:pointer" @click="toTransactiondetail(item.txId)" >{{ item.txId }}</span>
          <div class="col-sm-1" @click="showDetails(index)" style="cursor:pointer;text-align: right;cursor: pointer;" >
            <i class="glyphicon glyphicon-menu-right arrow" style="top:0px;" v-if="!showFlag[index]"></i>
            <i class="glyphicon glyphicon-menu-down arrow"style="top:0px;" v-if="showFlag[index]"></i>           
          </div>
          <div v-if="showFlag[index]"  class="col-sm-12" style="padding:0px;">
            <span class="col-sm-12" style="padding:0px;">
              <span class="col-sm-2  " style="text-align: left;">时间 :</span>
              <span class="col-sm-10 ">{{ item.date }}</span>
            </span>
            <span class="col-sm-12" style="padding:0;"v-for="(subitem,index) in item.inputs">
              <span class="col-sm-2">输入{{index+1}}</span>
              <span class="col-sm-7">{{ subitem.ADDRESS }}</span>
              <span class="col-sm-3">{{ subitem.VALUE }}</span>
            </span>
            <span class="col-sm-12" style="padding:0;" v-for="(subitem,index) in item.outputs">
              <span class="col-sm-2">输出{{index+1}}</span>
              <span class="col-sm-7">{{ subitem.ADDRESS }}</span>
              <span class="col-sm-3">{{ subitem.VALUE }}</span>
            </span>
          </div>
        </div>
      </ul>
    </div>
    <!-- 移动端 -->
    <div class="main-content phone" v-if="selectBy =='namespace'">
      <ul  class="text-left col-sm-12" v-for="(item,index) in showlist">
        <li class="col-sm-6 showdetial" @click="showDetails(index)">
        	<span class="col-sm-3">KEY:</span><span class="col-sm-9">{{ item.key }}</span>
        </li>
        <li class="col-sm-6 showdetial" @click="showDetails(index)">
        	<span class="col-sm-3">DESC:</span>
          <span class="col-sm-8">{{ JSON.parse(item.value).Desc }}</span>
        	<i class="glyphicon glyphicon-chevron-right arrow" v-if="!showFlag[index]"></i>
        	<i class="glyphicon glyphicon-chevron-down arrow" v-if="showFlag[index]"></i>
        </li>
        <div v-if="showFlag[index]">
        <li class="col-sm-6">
        	<span class="col-sm-3">HEIGHT:</span>
        	<span class="col-sm-8">{{ item.height }}</span>
        </li>
        <li class="col-sm-6">
        	<span class="col-sm-3">TIME:</span>
        	<span class="col-sm-8">{{ item.datemap }}</span>
        </li>
        <li class="col-xs-12">
        	<span class="col-sm-3" >DETAIL INFORMATION:</span>
        	<span class="col-sm-9"><pre id="pre">{{ JSON.parse(item.value) }}</pre></span>
        </li>
        </div>
      </ul> 
    </div> 
   
 <div id="page" v-show="allpage!=1">
    <ul class="pagination" >
      <li style="" @click="goto(1)" ><a>首页</a> </li>
      <li v-show="current != 1" @click="current-- && goto(current--)" style=""><a>上一页</a></li>
      <li v-for="index in pages" v-show="index>0" style="background-color:rgba(0, 0, 0, 0);" @click="goto(index)" :class="{'active':current == index}" :key="index">
        <a>{{index}}</a>
      </li>
      <li v-show="allpage != current && allpage != 0 && !isNaN(allpage)" @click="current++  &&  goto(current++)" style=""><a>下一页</a></li>
      <li style="" @click="goto(allpage)" ><a>末页</a> </li>
      <li style="" :class="{'active':current == allpage}" :key="allpage"><a>共：{{allpage}}页</a> </li>
      <li ><input style="color: black; width: 60px;" type="text" onkeyup="this.value=this.value.replace(/\s+/g,'')" v-model="pagenumber"/>&nbsp;<a @click="goto(pagenumber)">跳转</a> </li>
    </ul>
  </div>

 </div>
<!-- <div style="height:120px" v-if="!$route.params.length"></div>-->
</div>
</template>

<script>
  import { mapGetters, mapActions } from 'vuex'
  import Top from './head-active.vue'
   export default {
    name:'filterdetail',
    computed:{
      ...mapGetters({
                     txnByParam: 'txnByParam'
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
    created (){
      var params = this.$route.params 
      for(var i=0;i<params.pagesize;i++){
        this.showFlag[i]=false;
      }
      this.$store.dispatch('txnByParam',params)
      Date.prototype.toLocaleString = function() {
//        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this);
          var Month=this.getMonth() + 1;
          if(Month<10)
          Month="0"+Month
          var Date=this.getDate();
          if(Date<10)
          Date="0"+Date
          var Hours=this.getHours();
          if(Hours<10)
          Hours="0"+Hours
          var Minutes=this.getMinutes();
          if(Minutes<10)
          Minutes="0"+Minutes
          var Seconds=this.getSeconds();
          if(Seconds<10)
          Seconds="0"+Seconds
           return this.getFullYear() + "-" + Month + "-" + Date + " " + Hours + ":" + Minutes + ":" + Seconds;
//        return this.getFullYear() + "-" + (this.getMonth() + 1) + "-" + this.getDate() + " " + this.getHours() + ":" + this.getMinutes() + ":" + this.getSeconds();
       };
    },
    watch: {
      'txnByParam':function(){
        this.showlist=this.txnByParam.data
      },
      'showlist':function(){
        console.log(this.showFlag)
        if(this.showlist.length==0){
          this.allnum=0
          this.allpage=1
        }else{
          this.allnum=this.txnByParam.total
          this.allpage=Math.ceil(this.allnum/this.$route.params.pagesize)
        }
        for(var i=0;i<this.showlist.length;i++){
          switch(this.showlist[i]['txType'])
            {
            case 208:
              this.showlist[i]['txTypeDesc'] = "智能合约"
              break;
            case 128:
              this.showlist[i]['txTypeDesc'] = "转账交易"
              break;
            case 64:
              this.showlist[i]['txTypeDesc'] = "注册交易"
              break;
            case 129:
              this.showlist[i]['txTypeDesc'] = "存证交易"
              break;
            case 16:
              this.showlist[i]['txTypeDesc'] = "DeployCode"
              break;
            case 1:
              this.showlist[i]['txTypeDesc'] = "发行交易"
              break;
            case 144:
              this.showlist[i]['txTypeDesc'] = "状态更新交易"
              break;
            case 24:
              this.showlist[i]['txTypeDesc'] = "注销交易"
              break;
            default:
              this.showlist[i]['txTypeDesc'] = "注销交易"
            }
          this.showlist[i].datemap = this.showlist[i].date.substr(0,4)+"-"+
                                        this.showlist[i].date.substr(4,2)+"-"+
                                        this.showlist[i].date.substr(6,2)+" "+
                                        this.showlist[i].date.substr(8,2)+":"+
                                        this.showlist[i].date.substr(10,2)+":"+
                                        this.showlist[i].date.substr(12,2)
        }         
      }
    },
    mounted(){
    	$(".starttime").flatpickr();
    	$(".endtime").flatpickr();
    },
    data() {
        return {
          isInputShow: false,
          params: {},
          size:0,
          smarttime:0,
          smarttimeend:0,
          current:1,
          showItem:5,
          allpage:1,
          showlist:[],
          selectBy:'',
          id:'',
          namespace:'',
          allnum:'',
          showFlag:[],
          key:'',
          appIDFlag:false
        }
    },
    methods:{
      back:function(){
        this.$router.push({'path': '/monitor'})
      },
      toBlockDetail:function(height){
        this.$router.push({'path':'/blockdetail/'+height})
      },
      toTransactiondetail:function(txid){
        this.$router.push({'path':'/transactiondetail/'+txid})
      },
      toAddressDetail:function(address){
        this.$router.push({'path':'/addressdetail/'+address})
      },
      getDate:function(){
        if(this.value == undefined){
          this.value = "0000-00-00"
        }
        if(this.value2 == undefined){
          this.value2 = "0000-00-00"
        }
        this.smarttime = this.value.replace(/\-/g, "")+"000000";
        this.smarttimeend = this.value2.replace(/\-/g, "")+"235959";
        if(this.smarttime == "00000000000000"){
          this.smarttime = 0;
        }
        if(this.smarttimeend == "00000000235959"){
          this.smarttimeend = 0;
        }

        if (isNaN(this.smarttime)|| isNaN(this.smarttimeend)) {
        	this.smarttime=0;
        	this.smarttimeend=0;
        }
        /*this.$router.push({'path': '/detial/'+this.id+'/1/5/'+this.smarttime+'/'+this.smarttimeend})*/
      },
      filterData:function(){
        var address = ''
        var height = ''
        var txid = ''
        var value = ''
        var value2 = ''
        if(this.address =="" || this.address==undefined){
          address = 0;
        }else{
          address = this.address;
        }
        if(this.height =="" || this.height==undefined){
          height = 0;
        }else{
          height = this.height;
        }
        if(this.txid =="" || this.txid==undefined){
          txid = 0;
        }else{
          txid = this.txid;
        }
        if(this.value =="" || this.value==undefined){
          value = 0;
        }else{
          value = this.value.replace(/\-/g, "")+"000000";
        }
        if(this.value2 =="" || this.value2==undefined){
          value2 = 99999999999999;
        }else{
          value2 = this.value2.replace(/\-/g, "")+"235959";
        }
        this.$router.push({'path': '/filtersearch/'+txid+'/'+height+'/'+address+'/'+this.current+'/'+this.$route.params.pagesize+'/'+value+'/'+value2})
        this.$store.dispatch('txnByParam',this.$route.params)
      },
      showDetails: function(index) {
        if(this.showFlag[index] == false ){
          this.showFlag[index] = true;   
        }else{
          this.showFlag[index] = false;  
        }   
          this.showlist.reverse().reverse();  
      },
      goto:function(index){
        if(isNaN(index)||index>this.allpage||index==""){
            alert("请输入正确的页数！");
        }else{
          if(index == this.current) {
             return;
           }else{
             this.current = index;
           }
           this.filterData() 
        }    
      }
    },
    components: {
      Top
    },
    beforeDestroy() {
      this.nameSpace = [];
    }
  }
</script>

<style scoped>
.header{
    margin-top: -70px;
    margin-left: 50px;
    font-size: 18px;
    color: #57d2ff;
    padding: 0;
}
.back{
  color: gray;
  font-size: 14px;
  text-align: center;
  margin-top: -20px;
}
.showdetial:hover{
	cursor: pointer;
}
  .back:hover{
  	cursor: pointer;
  	color: white;
  }
  .collapse_title{
    cursor: pointer;
  }
  .detial-content{
    text-align: center;
    width: 80%;
    margin: 0 auto;
    padding: 50px 0;
  }
  .detial-content>h1{
    font-size: 25px;
    padding-bottom: 20px;
  }
  ul{
    padding: 0;
    margin: 0;
    margin-bottom: 50px;
    border-bottom: 4px solid #F5F5F5;
  }
 
  ul li span{
    /*overflow: hidden;
    text-overflow:ellipsis;
    white-space: nowrap;*/
   
    white-space: pre-line;
    word-break: break-all;
    word-wrap:break-word;
  }
  .detial-content ul li{
    min-height: 40px;
    padding-left: 20px;
    font-size: 16px;
    /*line-height: 40px;*/
    list-style: none;
    
  }
  .arrow{
    color: gray;
    font-size: 18px;
    float: right;
    top: 8px;
  }
  .main-content ul li span:after{
    clear: both;
    display: block;
  }
  .main-content{
    width:100%;
    float: left;
  }
  .main-content ul li{
  	padding-top: 0;
  	padding-bottom: 0;
  }
  .main-content ul li span{
  	text-align: left;
  }
  .select-date{
  width: 100%;
  padding-top: 40px;
  text-align: center;
  margin: 0 auto;
  }
  #page ul{
    border: none;
    box-shadow: none;
    font-size: 0;
  }
  #page ul>li{
    height: 40px;
    padding: 0;
    font-size: 16px;
    cursor: pointer;
    display: inline-block;
    border: none;
  }
  .filter-time-key{
    font-size: 16px;text-align: right;
  }
  .filter-time-start{
    text-align: left;
  }
  .filter-time-end{
    text-align: left;
  }
  .filter-cakey-key{
    font-size: 16px;text-align: right;
  }
  .filter-cakey-value{
    font-size: 16px;text-align: left;
  }
  .filter-appid-key{
    font-size: 16px;text-align: right;
  }
  .filter-appid-value{
    font-size: 16px;text-align: left;
  }
  .filter-button{
    margin-top: 20px;
  }
  .filter-margin-top{
    margin-top: 10px;
  }
  #detailList>li>div{
    padding: 5px 0;
  }
  #detailList>li>div span,#detailList>li>div i{
    padding: 5px 0;
  }
  #detailList .title{
    font-weight: bolder;
    font-size: 14px;
    color: #f6f7dd;
  }
  .exnum:hover{
    color: gray;
    text-decoration: underline;
    cursor: pointer;
  }
  #pre{
  	background-color:#212124;
  	border: 0px;
  	color: white;
  	font-size:16px;
  	margin-left: -65px;
  }
  .pagination {
    position: relative;

  }
  .active{
  background-color: rgba(0,0,0,0);
}
  .pagination li{
    display: inline-block;
    margin:0 5px;
  }
  .pagination li a{
    padding:.5rem 1rem;
    display:inline-block;
    float: none;
    border:1px none;
    background:none;
  }
  .pagination li a:hover{
    background:#eee;
  }
  .pagination li.active a{
    background:#0E90D2;
    color:#fff;
  }
  .active{
    background-color: none;
    width:auto;
  }
  .phone{
    display: none;
  }
  .click-able-item{
    color: #05a2b5;
  }
  .click-able-item:hover{
    color: #08d2eb;
  }
  .serach-option{
    font-size: 16px;
    margin-top: 20px;
    text-align: right;
  }
  @media only screen and (max-width:767px ) {
    ul li span{
      font-size: 0.8em;
      padding-left: 0;
    }
    .detial-content{
      padding: 0;
    }
    .pc{
      display: none;
    }
    .phone{
      display: block;
    }
    .header{
      margin-top: -15px;
      margin-right: 50px;
    }
  	.back{
    	padding: 0;
    }
    .phone span{
      font-size: 0.8em;
    }
    .title{
      font-weight: bold;
    }
    .detial-content ul li{
      line-height: 30px;
    }  
    .pagination a{
      font-size: 0.5em;
    }
    .select-date span{
      display: block;
      padding-bottom: 10px;
    }
    ul{
      border-bottom: none;
    }
  }
</style>