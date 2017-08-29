<template>
  <div id="browser">
    <Top></Top>
    <div class="content-monitor">
      <div class="row">
        <div class="col-sm-12 col-xs-12">
          
          
          <div class="col-sm-6 col-xs-12 nav-list">
            <!--区块高度-->
            <div class="nav-list-one col-sm-6 col-xs-12">
              <div class="col-xs-7 nav-list-name " style="text-align:left;">
                <p class="numberRun number" >{{ blockStatusheight }}</p>
                <p class="number-name">当前区块高度</p>
              </div>
              <div class="col-xs-5 nodeicon " >
                <span class="icon icon-signal icon-monitor"></span>
              </div> 
            </div>
            <!--节点数量-->
            <div class="nav-list-two col-sm-6 col-xs-12" >
              <div class="col-xs-7 nav-list-name "  style="text-align:left;">
                <p class="number nodeNum-m">{{ nodeStatus.totalCount}}</p>
                <p class="nodeText-m number-name">节点数量</p>
              </div>
              <div class="col-xs-5 nodeicon ">
                <span class="icon icon-sitemap  icon-monitor"></span>
              </div>
            </div>
            <!--平均输出时间-->
            <div class="nav-list-one col-sm-6 col-xs-12">
              <div class="col-xs-7 nav-list-name" style="text-align:left;">
                <p class=" number" >15s</p>
                <p class="number-name">平均出块时间</p>
              </div>
              <div class="col-xs-5 nodeicon">
                <span class="icon icon-time icon-monitor"></span>
              </div> 
            </div>
            <!--交易数量-->
            <div class="nav-list-three col-sm-6 col-xs-12"> 
              <div class="col-xs-7 nav-list-name " style="text-align:left;">
                <p class="number">{{ txncount }}</p>
                <p class="number-name">交易数量</p>
              </div>
              <div class="col-xs-5 nodeicon ">
                <span class="icon icon-exchange  icon-monitor"></span>
              </div>
            </div>
          </div>
          
          
          <div class="col-sm-6 col-xs-12 ">
            <!-- <p class="main-left-title">节点类型</p> -->
            <div id="allnode" class="col-sm-6  col-xs-12 mobile-noshow"></div>
            <div id="normalnode" class="col-sm-6 col-xs-12 mobile-noshow"></div>
            <div class="normelPro-pie col-xs-12 normelPro-right mobile-show">
              <span >验证节点:{{nodeType1}}</span></br>
              <span >服务节点:{{nodeType2}}</span>            
            </div>
            <div class="normelPro col-xs-12 normelPro-right">
              <span >正常节点占比:</span>
              <span >{{ (nornum/(nornum+dannum)*100).toFixed(1) }}%</span>
            </div>
          </div>
        </div>
        
        <!--最新区块-->
        <span class="col-sm-6">
          <div class="col-sm-12 " style="border-bottom: 1px solid white;margin-bottom: 10px;">
            <span class="col-sm-6">
              <h1>最新区块</h1>
            </span>
            <span class="col-sm-6 click-able-more" style="text-align:right;margin-top: 40px;" @click="toBlockInfo">
              <h5 style="cursor:pointer">查看更多>>></h5>
            </span><br>
          </div> 

          <ul>
            <li>
              <span class="col-sm-2 col-xs-5 listTitle">高度</span>
              <span class="col-sm-5 col-xs-7 listTitle">时间</span>
              <span class="col-sm-2 pc listTitle">交易数</span>
              <span class="col-sm-3 pc listTitle">数据大小</span>
            </li>
            <li v-for="(item,index) in blockListShow">
              <span class="col-sm-2 col-xs-5" style="cursor:pointer" @click="toBlockDetail(item.height)"  :class="(index%2 == 1) ? 'withBcakgroundColor click-able-item' : 'withoutBcakgroundColor click-able-item' " >{{ item.height }}</span>
              <span class="col-sm-5 pc" :class="(index%2 == 1) ? 'withBcakgroundColor' : 'withoutBcakgroundColor' ">{{ item.timestamp }}</span>
              <span class="col-sm-2 col-xs-7" :class="(index%2 == 1) ? 'withBcakgroundColor' : 'withoutBcakgroundColor' ">{{ item.txnum }}</span>
              <span class="col-sm-3 pc" :class="(index%2 == 1) ? 'withBcakgroundColor' : 'withoutBcakgroundColor' ">{{ item.size }} 字节</span> 
            </li>
          </ul>
        </span>
        
        <!--最新交易-->
        <span class="col-sm-6">
          <div class="col-sm-12" style="border-bottom: 1px solid white;margin-bottom: 10px;">
            <span class="col-sm-6">
              <h1>最新交易</h1>
            </span>
            <span class="col-sm-6 click-able-more" style="text-align:right;margin-top: 40px;" @click="toTransactionInfo">
              <h5 style="cursor:pointer">查看更多>>></h5>
            </span><br>
          </div>

            <ul style="height: 20px;margin-bottom: 0px;padding:0;" class="col-sm-12">
              <span class="col-sm-3 pc listTitle">交易类型</span>
              <span class="col-sm-3 pc listTitle">交易块高度</span>
              <span class="col-sm-5 col-xs-12 listTitle">交易编号</span>
              <span class="col-sm-1 listTitle"> </span>
            </ul>
            <ul v-for="(item,index) in txnListShow" >
              <span class="col-sm-3 pc" style="margin-top:10px" :class="(index%2 == 1) ? 'withBcakgroundColor' : 'withoutBcakgroundColor' ">{{ item.txTypeDesc }}</span>
              <span class="col-sm-2 pc" style="margin-top:10px" :class="(index%2 == 1) ? 'withBcakgroundColor' : 'withoutBcakgroundColor' ">{{ item.height }}</span>
              <span class="col-sm-6 col-xs-11" style="margin-top:10px;cursor:pointer" @click="toTransactiondetail(item.txId)" :class="(index%2 == 1) ? 'withBcakgroundColor click-able-item' : 'withoutBcakgroundColor click-able-item' ">{{ item.txId.substr(0,25) }}...</span>
              <div class="col-sm-1 col-xs-1" @click="showDetails(index)" style="padding:0;margin-top:10px;cursor:pointer" :class="(index%2 == 1) ? 'withBcakgroundColor' : 'withoutBcakgroundColor' ">
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
                  <span class="col-sm-7" style="cursor:pointer">{{ subitem.ADDRESS }}</span>
                  <span class="col-sm-3">{{ subitem.VALUE }}</span>
                </span>
                <span class="col-sm-12" style="padding:0;" v-for="(subitem,index) in item.outputs">
                  <span class="col-sm-2">输出{{index+1}}</span>
                  <span class="col-sm-7" style="cursor:pointer">{{ subitem.ADDRESS }}</span>
                  <span class="col-sm-3">{{ subitem.VALUE }}</span>
                </span>
              </div>
            </ul>
        </span>
      </div>
      <div style="width:100%;height:70px;"></div>
    </div>
  </div>
</template>

<script>
  import '../assets/ebro/css/index.css'
  import { mapGetters, mapActions } from 'vuex'
  import Bottom from './bottom.vue'
  import Top from './head-active.vue'
   var Highcharts = require('highcharts');
  require('highcharts/modules/exporting')(Highcharts); 


  export default {
    mounted: function() {
      this.node_pie();
      this.normalnode(); 
      $(".highcharts-point").css("stroke", "#282932");
    },
    computed: mapGetters({
      nodeList: 'nodeList',
      blockListNew: 'blockListNew',
      txnList: 'txnList',
      nodeStatus: 'nodeStatus',
      blockStatus: 'blockStatus'
    }),
    created() {
      this.$store.dispatch('blockListNew',this.blockListparams)
      this.$store.dispatch('txnList',this.txnListparams)
      this.intervalBlockBrowser = setInterval(() => {
        this.$store.dispatch('blockListNew',this.blockListparams)
        this.$store.dispatch('txnList',this.txnListparams)
      }, 5000)
      var numRun = this.numberAnimate({num:"000000", speed:2000, symbol:","},".numberRun")    
      this.$store.dispatch('nodeList')  
      this.$store.dispatch('nodeStatus')
      this.$store.dispatch('blockStatus')
      this.timeoutBlock = setTimeout(() => {
        this.$store.dispatch('nodeStatus')
        numRun.resetData(this.blockStatus.height)
        this.txncount = this.comdify(this.blockStatus.txncount)
        }
      ,100)
      this.intervalBlock = setInterval(() => {
        this.$store.dispatch('blockStatus')
        this.$store.dispatch('nodeStatus')
        this.$store.dispatch('nodeList')  
        numRun.resetData(this.blockStatus.height)
        this.blockStatusheight = this.comdify(this.blockStatus.height);
        this.txncount = this.comdify(this.blockStatus.txncount);
      }, 5000)
    },
    watch:{
      'blockListNew':function(){
        this.blockListShow = this.blockListNew.data;
      },
      'txnList':function(){
        this.txnListShow = this.txnList.data;
        for(var i=0;i<this.txnListShow.length;i++){
          this.showFlag[i]=false;
          switch(this.txnListShow[i]['txType'])
            {
            case 208:
              this.txnListShow[i]['txTypeDesc'] = "智能合约"
              break;
            case 128:
              this.txnListShow[i]['txTypeDesc'] = "转账交易"
              break;
            case 64:
              this.txnListShow[i]['txTypeDesc'] = "注册交易"
              break;
            case 129:
              this.txnListShow[i]['txTypeDesc'] = "存证交易"
              break;
            case 16:
              this.txnListShow[i]['txTypeDesc'] = "DeployCode"
              break;
            case 1:
              this.txnListShow[i]['txTypeDesc'] = "发行交易"
              break;
            case 144:
              this.txnListShow[i]['txTypeDesc'] = "状态更新交易"
              break;
            case 24:
              this.txnListShow[i]['txTypeDesc'] = "注销交易"
              break;
            default:
              this.txnListShow[i]['txTypeDesc'] = "注销交易"
            }
          this.txnListShow[i].datemap = this.txnListShow[i].date.substr(0,4)+"-"+
                                        this.txnListShow[i].date.substr(4,2)+"-"+
                                        this.txnListShow[i].date.substr(6,2)+" "+
                                        this.txnListShow[i].date.substr(8,2)+":"+
                                        this.txnListShow[i].date.substr(10,2)+":"+
                                        this.txnListShow[i].date.substr(12,2)
        }
      },
      'nodeStatus': function(val, oldVal) {
        this.nodeType1 = parseFloat(val.type1Num);
        this.nodeType2 = parseFloat(val.type2Num);
        //数据有变化or第一次进页面，渲染饼图
        if(this.times == 0) {
          this.node_pie()
          this.normalnode()
        } else if(val.type1Num != oldVal.type1Num) {
          this.node_pie()
          this.normalnode()
        } else if(!this.normalnodeSuccess){
          this.normalnode()
        }
        this.times = this.times + 1;
      },
      'nodeList':function(){
        var size=this.nodeList.length;
        var nornum=0;
        var dannum=0;
        for (var i=0;i<size;i++) {
        	if (this.nodeList[i].blockStatus=='出块正常') {
        		nornum+=1;
        	}else{
        	  dannum+=1;
        	}
        }
        this.nornum=nornum;
        this.dannum=dannum;
      }
    },
    methods: {
      toMonitor:function(){
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
      toBlockInfo:function(){
        this.$router.push({'path':'/blockinfo/1/15'})
      },
      toAddressInfo:function(){
        this.$router.push({'path':'/addressinfo/1/10'})
      },
      toTransactionInfo:function(){
        this.$router.push({'path':'/transactioninfo/1/15'})
      },
      showDetails: function(index) {
        if(this.showFlag[index] == false ){
          this.showFlag[index] = true;   
        }else{
          this.showFlag[index] = false;  
        }   
          this.txnListShow.reverse().reverse();  
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
      numberAnimate:function(setting,className){
        var defaults = {
              speed : 1000,//动画速度
              num : "", //初始化值
              iniAnimate : true, //是否要初始化动画效果
              symbol : '',//默认的分割符号，千，万，千万
              dot : 0 //保留几位小数点
            }
      //如果setting为空，就取default的值
        var setting = $.extend(defaults, setting);
      //如果对象有多个，提示出错
        if($(className).length > 1){
          alert("just only one obj!");
          return;
        }
  
       //如果未设置初始化值。提示出错
        if(setting.num == ""){
          alert("must set a num!");
          return;
        }
        var nHtml = '<div class="mt-number-animate-dom" data-num="{{num}}">\
                       <span class="mt-number-animate-span">0</span>\
                       <span class="mt-number-animate-span">1</span>\
                       <span class="mt-number-animate-span">2</span>\
                       <span class="mt-number-animate-span">3</span>\
                       <span class="mt-number-animate-span">4</span>\
                       <span class="mt-number-animate-span">5</span>\
                       <span class="mt-number-animate-span">6</span>\
                       <span class="mt-number-animate-span">7</span>\
                       <span class="mt-number-animate-span">8</span>\
                       <span class="mt-number-animate-span">9</span>\
                       <span class="mt-number-animate-span">.</span>\
                     </div>';
      //数字处理
        var numToArr = function(num){
          num = parseFloat(num).toFixed(setting.dot);
          if(typeof(num) == 'number'){
            var arrStr = num.toString().split("");  
          }else{
            var arrStr = num.split("");
          }

          return arrStr;
        }
      //设置DOM symbol:分割符号
        var setNumDom = function(arrStr){
          var shtml = '<div class="mt-number-animate">';
          for(var i=0,len=arrStr.length; i<len; i++){
            if(i != 0 && (len-i)%3 == 0 && setting.symbol != "" && arrStr[i]!="."){
              shtml += '<div class="mt-number-animate-dot">'+setting.symbol+'</div>'+nHtml.replace("{{num}}",arrStr[i]);
            }else{
              shtml += nHtml.replace("{{num}}",arrStr[i]);
            }
          }
          shtml += '</div>';
          return shtml;
        }
      //执行动画
        var runAnimate = function($parent){
          $parent.find(".mt-number-animate-dom").each(function() {
            var num = $(this).attr("data-num");
            num = (num=="."?10:num);
            var spanHei = $(this).height()/11; //11为元素个数
            var thisTop = -1-num*spanHei+"px"; //如果 -num*spanHei == 0,动画不会加载，表现为不会从9跳回0，所以让其最大为-1 ~~~LingXiaoSu
            if(thisTop != $(this).css("top")){
              if(setting.iniAnimate){
            //HTML5不支持
                if(!window.applicationCache){
                  $(this).animate({
                    top : thisTop
                  }, setting.speed);
                }else{
                  $(this).css({
                    'transform':'translateY('+thisTop+')',
                    '-ms-transform':'translateY('+thisTop+')',   /* IE 9 */
                    '-moz-transform':'translateY('+thisTop+')',  /* Firefox */
                    '-webkit-transform':'translateY('+thisTop+')', /* Safari 和 Chrome */
                    '-o-transform':'translateY('+thisTop+')',
                    '-ms-transition':setting.speed/1000+'s',
                    '-moz-transition':setting.speed/1000+'s',
                    '-webkit-transition':setting.speed/1000+'s',
                    '-o-transition':setting.speed/1000+'s',
                    'transition':setting.speed/1000+'s'
                  }); 
                }
              }else{
                setting.iniAnimate = true;
                $(this).css({
                   top : thisTop
                });
              }
            }
          });
        }
  
      //初始化
          var init = function($parent){
        //初始化
          $parent.html(setNumDom(numToArr(setting.num)));
          runAnimate($parent);
        };
  
      //重置参数
        this.resetData = function(num){
          var newArr = numToArr(num);
          var $dom = $(className).find(".mt-number-animate-dom");
          if($dom.length < newArr.length){
            $(className).html(setNumDom(numToArr(num)));
          }else{
            $dom.each(function(index, el) {
              $(this).attr("data-num",newArr[index]);
            });
          }
          runAnimate($(className));
        }
      //init
        init($(className));
        return this;
      },

      node_pie: function() {
        new Highcharts.Chart('allnode', {
          chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            backgroundColor: '#212124',
            margin:[0,50,0,30]
          },
          title: {
            text: ' '
          },
          credits: {
            enabled: false // 禁用版权信息
          },
      
          plotOptions: {
            pie: {
              allowPointSelect: true,
              cursor: 'pointer',
              dataLabels: {
                enabled: false
              },
              showInLegend: true
            }
          },
          legend: {
            align: 'right',
            x:50,
            width:110,
            itemStyle: {
              cursor: 'pointer',
              color: 'white',
              fontWeight: 'normal'
            },
            itemHoverStyle: {
              color: 'gray'
            },
            symbolHeight: 12,
            symbolRadius: 6,
            layout: 'vertical',
            itemWidth: 100,
            itemMarginBottom: 2
          },
          series: [{
            name: '个数',
            type: 'pie',
            innerSize: '60%',
            colorByPoint: true,
            data: [{
              name: '验证节点',
//            color: '#D63092',
              color: '#1595F5',
//              y: parseFloat(this.nodeType1)
              y: typeof(this.nodeType1) == "number"?this.nodeType1:parseFloat(this.nodeType1)
            }, {
              name: '服务节点',
//            color: '#96468D',
              color: '#C1E1FE',
//              y: parseFloat(this.nodeType2)
              y: typeof(this.nodeType2) == "number"?this.nodeType2:parseFloat(this.nodeType2)
            }]
          }],
          exporting: {
            enabled: false
          }
        });
        $(".highcharts-point").css("stroke", "#282932");       
      },
      normalnode:function(){
        if(this.nodeList.length != 0 && this.nodeStatus != undefined){
          this.normalnodeSuccess = true;
        }
        new Highcharts.Chart('normalnode', {
          chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            backgroundColor: '#212124',
            margin:[0,50,0,30]
          },
          title: {
            text: ' '
          },
          credits: {
            enabled: false // 禁用版权信息
          },
          
          plotOptions: {
            pie: {
              allowPointSelect: true,
              cursor: 'pointer',
              dataLabels: {
                enabled: false
              }
//            showInLegend: true
            }
          },
          series: [{
            name: '个数',
            type: 'pie',
            innerSize: '60%',
            colorByPoint: true,
            data: [{
              name: '正常节点',
              color: '#1595F5',
              y:this.norunm == typeof(this.nornum) == "number"?this.nornum:parseFloat(this.nornum)
            }, {
              name: '故障节点',
              color: '#FF3B3B',
              y:this.dannum == typeof(this.dannum) == "number"?this.dannum:parseFloat(this.dannum)
            }]
          }],
          exporting: {
            enabled: false
          }
        });
        $(".highcharts-point").css("stroke", "#282932");
      },
      filtersearch:function(){
        this.$router.push({'path':'/filtersearch/0/0/1/5/0/0'})
      }
     },
    data() {
      return {
        nodeType1: '',
        nodeType2: '',
        times: 0,
        nornum:'',
        blockListShow:[],
        txnListShow:[],
        blockListparams:{
          "page":1,
          "pagesize":20
        },
        txnListparams:{
          "page":1,
          "pagesize":14
        },
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
      clearInterval(this.intervalBlock);
      clearInterval(this.intervalBlockBrowser)
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
   max-height: 236px;
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
.withBcakgroundColor{
  background-color: #353434;
}
.listTitle{
  font-size: 17px;
  font-weight: bold;
}
.search{
  top: 170px;
  width: 70%;
  left: 15%;
  position: absolute;
  z-index: 11;
}
.search input{
  font-size: 16px;
  width: 100%;
  padding:10px 60px 10px 20px;
  border-radius: 30px;
  border: 5px solid #6258c5;
  background-color: #E3E0F1;
  outline: none;
}
.search button{
  outline: none;
  font-size: 20px;
  position: absolute;
  background: #E3E0F1;
  border: none;
  right: 3%;
  top: 12px;
}
.icon-monitor{
  font-size: 49px !important;
  opacity: 0.8;
  color: #10a0de !important;
}
.click-able-item{
  color: #05a2b5;
}
.click-able-item:hover{
  color: #08d2eb;
}
.click-able-more{
  opacity: 0.5;
}
.click-able-more:hover{
  opacity: 1;
}
.nav-list-one:hover,.nav-list-two:hover,.nav-list-three:hover{
  cursor: auto !important;
}
/*数字滚动插件的CSS可调整样式*/
.mt-number-animate{ font-family: '微软雅黑'; line-height:40px; height: 40px;/*设置数字显示高度*/; font-size: 30px;/*设置数字大小*/ overflow: hidden; display: inline-block; position: relative; min-width: 112px;}
.mt-number-animate .mt-number-animate-dot{ width: 15px;/*设置分割符宽度*/ line-height: 40px; float: left; text-align: center;}
.mt-number-animate .mt-number-animate-dom{ width: 16px;/*设置单个数字宽度*/ text-align: center; float: left; position: relative; top: 0;}
.mt-number-animate .mt-number-animate-dom .mt-number-animate-span{ width: 100%; float: left; color: #b8b8c2;}

li span{
  overflow: hidden;
  text-overflow:ellipsis;
  white-space: nowrap;
}
ul span{
  overflow: hidden;
  text-overflow:ellipsis;
  white-space: nowrap;
}
@media only screen and (max-width:910px ) {
  h1{
    font-size: 30px;
  }
	.number-name{
	  font-size: 12px;
	}
}
@media only screen and (max-width:768px ) {
	.pc{
	  display: none;
	}
	.number-name{
    font-size: 16px;
  }
}
</style>
