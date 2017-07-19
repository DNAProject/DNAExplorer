<template>
<div id="filterdetail">
 <Top></Top>
   <div class="header">
      <div class="row">
        <div class="col-sm-2 col-xs-4 col-xs-offset-10 back" @click="back"> 返回首页</div>
      </div>
  </div>
 <div class="select-date container pc row" id="select-date" v-show="this.size!=64">
    <span class="filter-cakey-key filter-margin-top col-sm-6" style="color:white;">Select By:</span>
    <span class="filter-cakey-value filter-margin-top col-sm-6">
    <select v-model="selectBy" style="color: black;">
      <option >Please Select</option>
      <option value='namespace'>namespace</option>
      <option value='address'>address</option>
    </select>
    </span>
    <div v-if="selectBy =='namespace'">
    <span class="filter-cakey-key filter-margin-top col-sm-6" style="color:white;">Select the NameSpace:</span>
    <span class="filter-cakey-value filter-margin-top col-sm-6">
    <select v-model="namespace" style="color: black;">
      <option >Please Select</option>
      <option v-for="(item,index) in nameSpaceList" :value='item'>{{item}}</option>
    </select>
    </span>
    <span class="filter-appid-key filter-margin-top col-sm-6" style="color:white;">Entry the Key:</span>
    <span class="filter-appid-value filter-margin-top col-sm-6"><input v-model="key" style="border-radius: 5px;outline: none;"></span></br>
    <span class="filter-appid-key filter-margin-top col-sm-6" style="color:white;">StartTime:</span>
    <span class="filter-time-start filter-margin-top col-sm-6" style="color:white;"><input v-model="value" type="date" name="" id="date" value=""   style="border-radius: 5px;outline: none;color:black;" placeholder="例：2017-01-01"/></span></br>
    <span class="filter-appid-key filter-margin-top col-sm-6" style="color:white;">EndTime:</span>
    <span class="filter-time-start filter-margin-top col-sm-6" style="color:white;"><input v-model="value2" type="date" name="" id="date" value="" @change="getDate"   style="color:black;border-radius: 5px;outline: none;" placeholder="例：2017-01-10"/></span></br>
    </div>
    <div v-if="selectBy !== 'namespace'">
      <span class="filter-appid-key filter-margin-top col-sm-6" style="color:white;">Entry the Address:</span>
      <span class="filter-appid-value filter-margin-top col-sm-6"><input v-model="address" style="border-radius: 5px;outline: none;"></span></br>
    </div>
    <span class="col-sm-12  filter-button"><button style="width:50%;max-width:90px" @click="filterData">Search</button></span>
 </div>
 <div class="select-date container phone" id="select-date" v-show="this.size!=64">
    <span  style="color:white;" >Select By:
      <select v-model="selectBy" style="color: black;">
        <option >Please Select</option>
        <option value='namespace'>namespace</option>
        <option value='address'>address</option>
      </select>
    </span>
    <div v-if="selectBy =='namespace'">
      <span  style="color:white;" >Select the NameSpace:
        <select v-model="nameSpace" style="color: black;">
          <option value="default">Please Select</option>
          <option v-for="(item,index) in nameSpaceList" value='item'>{{item}}</option>
        </select>
      </span>
      <span  style="color:white;">Entry the Key:
      <input v-model="key" style="border-radius: 5px;outline: none;"></span>
      <span style="color:white;">StartTime ：<input v-model="value" type="date" name="" id="date" value=""   style="border-radius: 5px;outline: none;color:black;" placeholder="例：2017-01-01"/></span>
      <span style="color:white;">EndTime：<input v-model="value2" type="date" name="" id="date" value="" @change="getDate"   style="color:black;border-radius: 5px;outline: none;" placeholder="例：2017-01-10"/></span>
    </div>
    <div v-if="selectBy !=='namespace'">
      <span  style="color:white;">Entry the Address:
      <input v-model="address" style="border-radius: 5px;outline: none;"></span>
    </div>
    <button style="width:60%;" @click="filterData">Search</button>
 </div>
 <div class="detial-content container" style="color:white;">
  
   <h1  style="color:white;">Search Result  <span style="font-size: 16px;"> total:{{ allnum }}</span></h1>

     <!--namespace搜索-->
    <div class="main-content" v-if="selectBy =='namespace'">
     <ul  class="text-left col-sm-12" v-for="(item,index) in showlist">
        <li class="col-sm-6 showdetial" @click="showDetails(index)">
        	<span class="col-sm-3">Key:</span><span class="col-sm-9">{{ item.key }}</span>
          <!--<span v-if="!showFlag[index]">点击查看详细信息</span>
          <span v-else>点击收起详细信息</span>-->
        </li>
        <li class="col-sm-6 showdetial" @click="showDetails(index)">
        	<span class="col-sm-3">Desc:</span>
        	<!-- <span class="col-sm-8">{{ JSON.parse(item.value).Desc }}</span> -->
          <span class="col-sm-8">{{ JSON.parse(item.value).Desc }}</span>
        	<i class="glyphicon glyphicon-chevron-right arrow" v-if="!showFlag[index]"></i>
        	<i class="glyphicon glyphicon-chevron-down arrow" v-if="showFlag[index]"></i>
        </li>
        <div v-if="showFlag[index]">
        <li class="col-sm-6">
        	<span class="col-sm-3">Height:</span>
        	<span class="col-sm-8">{{ item.height }}</span>
        </li>
        <li class="col-sm-6">
        	<span class="col-sm-3">Time:</span>
        	<span class="col-sm-8">{{ item.txtime }}</span>
        </li>
        <li class="col-xs-12">
        	<span class="col-sm-3" >Detail Information:</span>
        	<span class="col-sm-9"><pre id="pre">{{ JSON.parse(item.value) }}</pre></span>
        </li>
        </div>
      </ul>
    </div>

     <!--address搜索-->
    <div class="main-content" v-if="selectBy !=='namespace'">
     <ul  class="text-left col-sm-12" v-for="(item,index) in showlist">
        <li class="col-sm-6 showdetial" @click="showDetails(index)">
        	<span class="col-sm-3">HASH:</span><span class="col-sm-9">{{ item.HASH }}</span>
          <!--<span v-if="!showFlag[index]">点击查看详细信息</span>
          <span v-else>点击收起详细信息</span>-->
        </li>
        <li class="col-sm-6 showdetial" @click="showDetails(index)">
        	<span class="col-sm-3">Desc:</span>
        	<!-- <span class="col-sm-8">{{ JSON.parse(item.value).Desc }}</span> -->
          <span class="col-sm-8">{{ JSON.parse(item.value).Desc }}</span>
        	<i class="glyphicon glyphicon-chevron-right arrow" v-if="!showFlag[index]"></i>
        	<i class="glyphicon glyphicon-chevron-down arrow" v-if="showFlag[index]"></i>
        </li>
        <div v-if="showFlag[index]">
          <li class="col-sm-6">
            <span class="col-sm-3">Height:</span>
            <span class="col-sm-8">{{ item.height }}</span>
          </li>
          <li class="col-sm-6">
            <span class="col-sm-3">Time:</span>
            <span class="col-sm-8">{{ item.txtime }}</span>
          </li>
          <li class="col-xs-12">
            <span class="col-sm-3" >Detail Information:</span>
            <span class="col-sm-9"><pre id="pre">{{ JSON.parse(item.value) }}</pre></span>
          </li>
        </div>
      </ul>
    </div>
   
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
<!-- <div style="height:120px" v-if="!$route.params.length"></div>-->
</div>
</template>

<script>
  import { mapGetters, mapActions } from 'vuex'
  import Top from './head.vue'
   export default {
    name:'filterdetail',
    computed:{
      ...mapGetters({nameSpace: 'nameSpace'}),
      ...mapGetters({stateUpdateTxn: 'stateUpdateTxn'}),
      ...mapGetters({addressData: 'addressData'}),
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
      if(params.selectBy == "namespace"){
        this.$store.dispatch('nameSpace')
        this.$store.dispatch('stateUpdateTxn',params) 
      }else{
        this.$store.dispatch('addressData',params) 
      }
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
          selectBy:"namespace",
          id:'',
          namespace:'',
          allnum:'',
          nameSpace_backup:[],
          showFlag:[false,false,false,false,false],
          key:'',
          nameSpaceList:[],
          appIDFlag:false
        }
    },
    watch: {
      '$route': function() {
          if(this.$route.params.selectBy == 'namespace'){
            this.$store.dispatch('stateUpdateTxn',this.$route.params)
          }else{
            this.$store.dispatch('addressData',this.$route.params)
          }
//        this.size=this.$route.params.id.length
          this.id=this.$route.params.id
      },
      'nameSpace':function(){
        debugger
//      this.nameSpaceList = this.nameSpace
        for (var i=0;i<this.nameSpace.length;i++) {
        	if (this.nameSpaceList.indexOf(this.nameSpace[i]) == -1) {
        		this.nameSpaceList.push(this.nameSpace[i])
        	}
        }
      },
      'stateUpdateTxn':function(){
        debugger
        this.showlist = this.stateUpdateTxn.data
/*         if(this.showlist.length==0){
          this.allnum=0
          this.allpage=1
        }else{
          this.allnum=this.stateUpdateTxn.total
          this.allpage=Math.ceil(this.allnum/5)
        } */
      },
      'addressData':function(){
        this.showlist = this.addressData.data
      },
      'showlist':function(){
        if(this.showlist.length==0){
          this.allnum=0
          this.allpage=1
        }else{
          this.allnum=this.stateUpdateTxn.total
          this.allpage=Math.ceil(this.allnum/5)
        } 
      },
      'selectBy':function(){
        if(this.selectBy =="namespace"){
          this.$router.push({'path':'/filtersearch/namespace/0/0/1/5/0/0'})
        }else{
          this.$router.push({'path':'/filtersearch/address/0/1/5'})
        }
      }
    },
    methods:{
      back:function(){
        this.$router.push({'path': '/monitor'})
      },
      filterData:function(){
        this.getDate()
        var key = ''
        var namespace = ''
/*         if(!this.appIDFlag || this.appID == ''){
          appid = 0;
        }else{
          appid= this.appID
        } */
        if(this.namespace =="" || this.namespace=="Please Select"){
          namespace = 0;
        }else{
          namespace = this.namespace;
        }
        if(this.key ==""){
          key = 0;
        }else{
          key = this.key;
        }
        this.$router.push({'path': '/filtersearch/'+namespace+'/'+key+'/1/5/'+this.smarttime+'/'+this.smarttimeend})
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
      changed (selected) {
        console.log(selected)
      },
      showDetails: function(index) {
        if(this.showFlag[index] == false ){
          this.showFlag[index] = true;   
        }else{
          this.showFlag[index] = false;  
        }   
        this.nameSpace.reverse().reverse();
      },
      goto:function(index){
        if(index == this.current) {
          return;
        }else{
          this.current = index;
        }
        var key = ''
        var namespace = ''
        if(this.namespace =="" || this.namespace=="请选择"){
          namespace = 0;
        }else{
          namespace = this.namespace;
        }
        if(this.key ==""){
          key = 0;
        }else{
          key = this.key;
        }
        this.$router.push({'path': '/filtersearch/'+namespace+'/'+key+'/'+this.current+'/5/'+this.smarttime+'/'+this.smarttimeend})
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
    line-height: 40px;
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
      .pagination li{
        display: inline-block;
        margin:0 5px;
      }
      .pagination li a{
        padding:.5rem 1rem;
        display:inline-block;
        border:1px solid #ddd;
        background:#fff;

        color:black;
      }
      .pagination li a:hover{
        background:#eee;
      }
      .pagination li.active a{
        background:#0E90D2;
        color:#fff;
      }
      .phone{
        display: none;
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