<template>
  <div id="monitor">
    <Top></Top>
    <div class="content-monitor">
      <div class="row">
        <div class="col-sm-4 col-xs-12 nav-list">
          
          <div class="nav-list-one col-sm-12 col-xs-12" @click="toChaininfo">
            <div class="col-xs-4 nodeicon mobile-noshow">
              <span class="icon icon-signal"></span>
            </div>
            <div class="col-xs-8 nav-list-name nav-list-name-mobile" >
              <p class="numberRun number" >{{ blockStatusheight }}</p>
              <p >当前区块高度</p>
            </div>
          </div>
          
          <div class="nav-list-two col-sm-12 col-xs-12" @click="toChaininfo">
            <div class="col-xs-4 nodeicon nodeicon-m">
              <span class="icon icon-sitemap icon-sitemap-m"></span>
            </div>
            <div class="col-xs-8 nav-list-name nav-list-name-m"  >
              <p class="number nodeNum-m">{{ nodeStatus.totalCount}}</p>
              <p class="nodeText-m">节点数量</p>
            </div>
          </div>
          
          <div class="nav-list-three col-sm-12 col-xs-12" @click="toChaininfo">
            <div class="col-xs-4 nodeicon nodeicon-m">
              <span class="icon icon-exchange icon-exchange-m"></span>
            </div>
            <div class="col-xs-8 nav-list-name nav-list-name-m" >
              <p class="number exchangeNum-m">{{ txncount }}</p>
              <p class="exchangeText-m">交易数量</p>
            </div>
          </div><br>
          <div class="showMoreDetail" @click="toChaininfo">更多信息</div>
          
        </div>
        <div class="col-sm-8 col-xs-12 nav-right mobile-noshow">
          <!--<p class="name">节点分布</p>-->
          <img src="static/img/ditu2.png" width="100%"/>
          <div id="oldNodes" >节点</div>
          <div class="legend">
            <p><span class="sampleIcon flag-icon-normal" ></span> 正常节点</p>
            <p><span class="sampleIcon flag-icon-error" ></span> 故障节点</p>
          </div>
        </div>
      </div>
      
      <div class="row main">
        <div class="col-sm-6 col-xs-12 main-left">
          <p class="main-left-title">节点类型</p>
          <div id="allnode" class="col-sm-6  col-xs-12 mobile-noshow" ></div>
          <div id="normalnode" class="col-sm-6 col-xs-12 mobile-noshow"></div>
          <div class="normelPro-pie col-xs-12-m-l normelPro-right mobile-show">
            <span >验证节点:{{nodeType1}}</span></br>
            <span >服务节点:{{nodeType2}}</span>            
          </div>
          <div class="normelPro col-xs-12-m-l normelPro-right">
            <span >正常节点占比:</span>
            <span >{{ (nornum/(nornum+dannum)*100).toFixed(1) }}%</span>
          </div>
        </div>
        
        <div class="col-sm-6 main-right">
           <div class="main-right-content">
              <div class="row">
                <ul>
                  <li class="list-title mobile-noshow">
                    <span class="col-xs-2 nodename">节点名称</span>
                    <span class="col-xs-2">出块状态</span>
                    <span class="col-xs-3">网络状态</span>
                    <span class="col-xs-3 showdate" >时间</span>
                    <span class="col-xs-2">操作</span>
                  </li>
                  <div v-for="item in nodeList">
                  <li v-if="item.blockStatus==='出块异常'" class="list-content" >
                    <span class="col-xs-2 nodename danger col-xs-6-m bottom-line-1" >{{ item.id }}</span>
                    <span class="col-xs-2 danger col-xs-6-m bottom-line-1">{{ item.blockStatus }}</span>
                    <span class="col-xs-3 danger col-xs-6-m">{{ item.rpcStatus }}</span>
                    <span class="col-xs-3 danger showdate col-xs-6-m" >{{ item.date }}</span>
                    <span class="col-xs-2 check col-xs-12-m-r" @click="nodeInfo(item.url)">查看>>></span>
                  </li>
                  <li v-if="item.blockStatus==='出块正常'" class="list-content" >
                    <span class="col-xs-2 nodename col-xs-6-m bottom-line-1" >{{ item.id }}</span>
                    <span class="col-xs-2 col-xs-6-m bottom-line-1">{{ item.blockStatus }}</span>
                    <span class="col-xs-3 col-xs-6-m">{{ item.rpcStatus }}</span>
                    <span class="col-xs-3 showdate col-xs-6-m"  >{{ item.date }}</span>
                    <span class="col-xs-2 check col-xs-12-m-r" @click="nodeInfo(item.url)">查看>>></span>
                  </li>
                  </div>
                </ul>
               
              </div>
            </div>
        </div>
      </div>
    </div>
    
  </div>
</template>

<script>
  import '../assets/ebro/css/index.css'
  import { mapGetters, mapActions } from 'vuex'
  import Bottom from './bottom.vue'
  import Top from './head.vue'
  var Highcharts = require('highcharts');
  require('highcharts/modules/exporting')(Highcharts);


  export default {

    watch: {
      'nodeStatus': function(val, oldVal) {
        this.nodeType1 = parseFloat(val.type1Num);
        this.nodeType2 = parseFloat(val.type2Num);
        //数据有变化or第一次进页面，渲染饼图
        debugger
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
        this.nodeDistribution()
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
    mounted: function() {

      this.nodeDistribution();
      this.node_pie();
      this.normalnode();
      $(".highcharts-point").css("stroke", "#282932");
    },
    computed: mapGetters({
      nodeList: 'nodeList',
      nodeStatus: 'nodeStatus',
      blockStatus: 'blockStatus'
    }),
    created() {
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

    methods: {
      toChaininfo: function() {
        this.$router.push({
          'path': '/chaininfo'
        })
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
      nodeInfo: function(node_url) {
        var realIp = node_url.replace(/\./g, "_");
        realIp = realIp.replace(/\:/g, "_");
        this.$router.push({
          'path': '/nodeinfo/' + realIp
        })
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
     nodeDistribution:function(){
        if(this.nodeList.length == 0){
          return;
        }
        var size=this.nodeStatus.totalCount;
        var newNodes="<div id='oldNodes'></div>";
//      $(".main-left").append(newNodes);
        $("#oldNodes").replaceWith(newNodes)
        for (var i=0;i<size;i++) {
          var xx=parseInt(this.nodeList[i].x)
          var yy=parseInt(this.nodeList[i].y)
          var k=i+1
          var newNode="<div class='node'><p><span class='nodeIcon"+k+" nodeIcon'><span/></p> <p> 节点"+k+"</p></div>";
//        $(".main-left").append(newNode);
          $("#oldNodes").append(newNode);
          $(".node").attr("class","node"+k);
//        $(".nodeIcon").attr("class","nodeIcon"+k);
          $(".node"+k).css({"position":"absolute","left":xx+"px","top":yy+"px"});
          $(".node"+k).addClass("normal");
          if(this.nodeList[i].blockStatus==="出块异常"){
            $(".node"+k).css("color","red");
            $(".nodeIcon"+k).addClass("error-icon");
          }
        }
      }
     },
    data() {
      return {
        nodeType1: '',
        nodeType2: '',
        times: 0,
        nornum:'',
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
/*数字滚动插件的CSS可调整样式*/
.mt-number-animate{ font-family: '微软雅黑'; line-height:40px; height: 40px;/*设置数字显示高度*/; font-size: 30px;/*设置数字大小*/ overflow: hidden; display: inline-block; position: relative; }
.mt-number-animate .mt-number-animate-dot{ width: 15px;/*设置分割符宽度*/ line-height: 40px; float: left; text-align: center;}
.mt-number-animate .mt-number-animate-dom{ width: 20px;/*设置单个数字宽度*/ text-align: center; float: left; position: relative; top: 0;}
.mt-number-animate .mt-number-animate-dom .mt-number-animate-span{ width: 100%; float: left; color: #b8b8c2;}
</style>
