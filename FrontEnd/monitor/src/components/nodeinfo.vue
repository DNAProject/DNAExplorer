<template>
  <div id="nodeinfo">
    <Top></Top>
    <div class="header">
      <div class="row">
        <div class="col-sm-2 col-xs-offset-10 back" @click="back"> 返回首页</div>
      </div>
    </div>

    <div class="content">
      <div class="content-top">

        <div class="showinfo row text-left">
          <div>
            <div class="col-md-5">
               <span class="color-blue col-xs-6">节点名称</span>
               <span class="col-xs-6" >{{ oneNodeInfo.id }}</span>
            </div>
            <div class="col-md-5">
               <span class="color-blue col-xs-6">操作系统版本</span>
               <span class="col-xs-6" v-show="oneNodeInfo.systemInfo!=null">{{ oneNodeInfo.systemInfo }}</span>
               <span class="col-xs-6" v-show="oneNodeInfo.systemInfo==null">unknown</span>
            </div>
          </div>
        </div>

        <div class="time row">
          <div>
            <div class="col-md-5">
              <span class="color-blue col-xs-6">区块链系统启动时间</span>
              <span class="col-xs-6" v-show="oneNodeInfo.runningTime!=null">{{ oneNodeInfo.runningTime }}</span>
              <span class="col-xs-6" v-show="oneNodeInfo.runningTime==null">unknown</span>
            </div>
            <div class="col-md-5">
              <span class="color-blue col-xs-6">网络状态</span>
              <span class="col-xs-6">{{ oneNodeInfo.rpcStatus }}</span>
            </div>
          </div>
        </div>


        <div class="chart">
          <div class="row">
            <div class="col-sm-6" id="cpu-area" ></div>
            <div class="col-sm-4" id="cpu-pie" ></div>
          </div>
          <div class="row">
            <div class="col-sm-6" id="memory-area" ></div>
            <div class="col-sm-4" id="memory-pie" ></div>
          </div>
          <div class="row">
            <div class="col-sm-6" id="disk-area" ></div>
            <div class="col-sm-4" id="disk-pie" ></div>
          </div>


        </div>
      </div>
    </div>
   
  </div>
</template>

<script>
  import '../assets/ebro/css/nodeinfo.css'
  import { mapGetters, mapActions } from 'vuex'
  import Bottom from './bottom.vue'
  import Top from './head.vue'
  var Highcharts = require('highcharts');
  require('highcharts/modules/exporting')(Highcharts);

  export default {
    mounted: function() {
      this.cpu_line()
      this.cpu_pie()
      this.mem_line()
      this.mem_pie()
      this.dis_line()
      this.dis_pie()
//    this.test()
    },

    data () {
      return {
        cpuUsed:0,
        cpuUnUsed:0,
        memUsed:0,
        memUnUsed:0,
        disUsed:0,
        disUnUsed:0,
        nowTime:'',
        info:{
          cpuUsedPer:'',
          memUsedPer:'',
          disUsedPer:'',
          date:''
        },
        infoList:[
          
        ],
      }
    },

    computed: mapGetters({
      machineInfo:'machineInfo',
      oneNodeInfo:'oneNodeInfo',
    }),

    created() {
      this.getNowFormatDate()
      var params = this.$route.params

      this.$store.dispatch('machineInfo', params)
      this.$store.dispatch('oneNodeInfo', params)

      this.intervalBlock = setInterval(() => {
        this.$store.dispatch('machineInfo', params)
        this.$store.dispatch('oneNodeInfo', params)
      }, 10000)
    },
    watch: {
      'machineInfo': function(val, oldVal) {
        if (val.length>0) {
        	this.cpuUsed = parseFloat(val[0].cpuUsedPer)
        this.cpuUnUsed = parseFloat(100 - this.cpuUsed);

        this.memUsed = parseFloat(val[0].memUsedPer)
        this.memUnUsed = parseFloat(100 - this.memUsed);

        this.disUsed = parseFloat(val[0].disUsedPer);
        this.disUnUsed = parseFloat(100 - this.disUsed);
        }
        

        this.infoList = val;
        var datetemp = '';
        var i = 0;
        for (i ; i<this.infoList.length; i++){
          datetemp = this.infoList[i].date
          datetemp = datetemp.substr(10)
          this.infoList[i].date = datetemp
        }

        this.cpu_pie()
        this.mem_pie()
        this.dis_pie()
        this.cpu_line()
        this.mem_line()
        this.dis_line()

      }
    },

    methods: {
      //格式化当前时间
      getNowFormatDate:function() {
        var date = new Date();
        var seperator1 = "-";
        var seperator2 = ":";
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
          month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
          strDate = "0" + strDate;
        }
        var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
        + " " + date.getHours() + seperator2 + date.getMinutes()
        + seperator2 + date.getSeconds();
        this.nowTime = currentdate
      },

      nodeInfo:function (node_url) {
        //192.168.1.103:8080转换成192_168_1_103
        var index = node_url.indexOf(":");
        var ipTemp = node_url.substring(0,index);
        var realIp = ipTemp.replace(/\./g,"_");
        this.$router.push({'path': '/nodeinfo/'+realIp})
      },
      back: function() {
        this.$router.push({
          'path': '/monitor'
        })
      },
      mem_pie:function () {
        //内存饼图
        var chart = new Highcharts.Chart('memory-pie',{
          chart: {
            plotBackgroundColor:null,
            plotBorderWidth:null,
            backgroundColor:'#212124'
          },
          title: {
            text: '当前内存使用率',
            align:'left',
            style:{
              color:'#57d2ff',
              fontSize:'14px'
            }
          },
          credits:{
            enabled:false // 禁用版权信息
          },
          tooltip: {
            pointFormat: '<b>{point.percentage:.1f}%</b>'
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
          legend:{
            align:'right',
            color:'white',
            itemStyle:{ cursor: 'pointer', color: 'white' },
            itemHoverStyle: { color: 'gray' },
            symbolRadius:0,
            layout:'vertical',
            itemWidth:100,
            itemMarginBottom:20
          },
          series: [{
            name:'',
            type: 'pie',
            innerSize: '60%',
            colorByPoint:true,
            data: [{
              name:'已使用',
              color:'#5B9BD5',
              y:this.getmemUsed()
            },{
              name:'未使用',
              color:'#ED7D31',
              y:this.getmemUnUsed()
            }]
          }],
          exporting: {
            enabled: false
          }
        });
        $(".highcharts-point").css("stroke","#282932");
      },

      dis_pie:function () {
        //磁盘饼图
        new Highcharts.Chart('disk-pie',{
          chart: {
            plotBackgroundColor:null,
            plotBorderWidth:null,
            backgroundColor:'#212124'
          },
          title: {
            text: '当前磁盘使用率',
            align:'left',
            style:{
              color:'#57d2ff',
              fontSize:'14px'
            }
          },
          credits:{
            enabled:false // 禁用版权信息
          },
          tooltip: {
            pointFormat: '<b>{point.percentage:.1f}%</b>'
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
          legend:{
            align:'right',
            color:'white',
            itemStyle:{ cursor: 'pointer', color: 'white' },
            itemHoverStyle: { color: 'gray' },
            symbolRadius:0,
            layout:'vertical',
            itemWidth:100,
            itemMarginBottom:20
          },
          series: [{
            name:'',
            type: 'pie',
            innerSize: '60%',
            colorByPoint:true,
            data: [{
              name:'已使用',
              color:'#5B9BD5',
              y:this.getdisUsed()
            },{
              name:'未使用',
              color:'#ED7D31',
//            y:this.disUnUsed
              y:this.getdisUnUsed()
            }]
          }],
          exporting: {
            enabled: false
          }
        });
        $(".highcharts-point").css("stroke","#282932");
      },

      cpu_pie:function () {
        //cpu饼图
        new Highcharts.Chart('cpu-pie',{
          chart: {
            plotBackgroundColor:null,
            plotBorderWidth:null,
            backgroundColor:'#212124'
          },
          title: {
            text: '当前CPU使用率',
            align:'left',
            style:{
              color:'#57d2ff',
              fontSize:'14px'
            }
          },
          credits:{
            enabled:false // 禁用版权信息
          },
          tooltip: {
            pointFormat: '<b>{point.percentage:.1f}%</b>'
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
          legend:{
            align:'right',
            color:'white',
            itemStyle:{ cursor: 'pointer', color: 'white' },
            itemHoverStyle: { color: 'gray' },
            symbolRadius:0,
            layout:'vertical',
            itemWidth:100,
            itemMarginBottom:20
          },
          series: [{
            name:'',
            type: 'pie',
            innerSize: '60%',
            colorByPoint:true,
            data: [{
              name:'已使用',
              color:'#5B9BD5',
//            y:this.cpuUsed
              y:this.getcpuUsed()
            },{
              name:'未使用',
              color:'#ED7D31',
//            y:this.cpuUnUsed
              y:this.getcpuUnUsed()
            }]
          }],
          exporting: {
            enabled: false
          }
        });
        $(".highcharts-point").css("stroke","#282932");
      },
      cpu_line:function () {
        //cpu折线图
        new Highcharts.Chart('cpu-area', {
          title: {
            text: 'CPU使用',
            align:'left',
            style:{
              color:'#57d2ff',
              fontSize:'14px'
            }
          },
          chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
//          backgroundColor: '#282932'
            backgroundColor:'#212124'
          },
          colors: ['#6B4259','#43415D'],
          credits: {
            enabled: false // 禁用版权信息
          },
          exporting: { //导出禁用
            enabled: false
          },
          legend: {//禁用图例
            enabled: false
          },
          xAxis: {
            tickWidth: 0.5,
            categories:this.getdate()
          },
          tooltip: {
            dateTimeLabelFormats: {
              millisecond: '%H:%M:%S.%L',
              second: '%H:%M:%S',
              minute: '%H:%M',
              hour: '%H:%M',
              day: '%Y-%m-%d',
              week: '%m-%d',
              month: '%Y-%m',
              year: '%Y'
            }
          },
          yAxis: {
            gridLineColor: '#212124',
            floor: 0,
            ceiling: 100,
            title: {
              text: 'cpu(单位：%)'
            },
            plotLines: [{
              width: 1,
              color: '#808080'
            }]
          },
          plotOptions: {
            area: {
              fillColor: {
                linearGradient: {
                  x1: 0.5,
                  y1: 0,
                  x2: 0.5,
                  y2: 1
                },
                stops: [
                  [0, Highcharts.getOptions().colors[5]],
                  [1, Highcharts.Color(Highcharts.getOptions().colors[1]).setOpacity(0).get('rgba')]
                ]
              },
              marker: {
                radius: 2
              },
              lineWidth: 1,
              states: {
                hover: {
                  lineWidth: 1
                }
              },
              threshold: null
            }
          },
          series: [{
            type: 'area',
            name: 'CPU',
            data:this.getcpuUsedPer()
          }]
        });
      },

      mem_line:function () {
        //内存折线图
        new Highcharts.Chart('memory-area', {
          title: {
            text: '内存使用',
            align:'left',
            style:{
              color:'#57d2ff',
              fontSize:'14px'
            }
          },
          chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            backgroundColor:'#212124'
          },
          colors: ['#6B4259','#43415D'],
          credits: {
            enabled: false // 禁用版权信息
          },
          exporting: { //导出禁用
            enabled: false
          },
          legend: {//禁用图例
            enabled: false
          },
          xAxis: {
            tickWidth: 0.5,
            categories:this.getdate()

          },
          tooltip: {
            dateTimeLabelFormats: {
              millisecond: '%H:%M:%S.%L',
              second: '%H:%M:%S',
              minute: '%H:%M',
              hour: '%H:%M',
              day: '%Y-%m-%d',
              week: '%m-%d',
              month: '%Y-%m',
              year: '%Y'
            }
          },
          yAxis: {
            gridLineColor: '#212124',
            floor: 0,
            ceiling: 100,
            title: {
              text: 'mem(单位：%)'
            },
            plotLines: [{
              width: 1,
              color: '#808080'
            }]
          },
          plotOptions: {
            area: {
              fillColor: {
                linearGradient: {
                  x1: 0.5,
                  y1: 0,
                  x2: 0.5,
                  y2: 1
                },
                stops: [
                  [0, Highcharts.getOptions().colors[5]],
                  [1, Highcharts.Color(Highcharts.getOptions().colors[1]).setOpacity(0).get('rgba')]
                ]
              },
              marker: {
                radius: 2
              },
              lineWidth: 1,
              states: {
                hover: {
                  lineWidth: 1
                }
              },
              threshold: null
            }
          },
          series: [{
            type: 'area',
            name: '内存',
            data:this.getmemUsedPer()

          }]
        });
      },
      dis_line:function () {
        //磁盘折线图
        new Highcharts.Chart('disk-area', {
          title: {
            text: '磁盘使用',
            align:'left',
            style:{
              color:'#57d2ff',
              fontSize:'14px'
            }
          },
          chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            backgroundColor:'#212124'
          },
          colors: ['#6B4259','#43415D'],
          credits: {
            enabled: false // 禁用版权信息
          },
          exporting: { //导出禁用
            enabled: false
          },
          legend: {//禁用图例
            enabled: false
          },
          xAxis: {
            tickWidth: 0.5,
            categories:this.getdate()

          },

          yAxis: {
            gridLineColor: '#212124',
            floor: 0,
            ceiling: 100,
            title: {
              text: 'dis(单位：%)'
            },
            plotLines: [{
              width: 1,
              color: '#808080'
            }]

          },
          plotOptions: {
            area: {
              fillColor: {
                linearGradient: {
                  x1: 0.5,
                  y1: 0,
                  x2: 0.5,
                  y2: 1
                },
                stops: [
                  [0, Highcharts.getOptions().colors[5]],
                  [1, Highcharts.Color(Highcharts.getOptions().colors[1]).setOpacity(0).get('rgba')]
                ]
              },
              marker: {
                radius: 2
              },
              lineWidth: 1,
              states: {
                hover: {
                  lineWidth: 1
                }
              },
              threshold: null
            }
          },
          series: [{
            type: 'area',
            name: '磁盘',
            data:this.getdisUsedPer()

          }]
        });
      },
      getdate:function(){
        if(typeof(this.infoList)=="undefined"||this.infoList.length==0){
          var data=[0,0,0,0,0,0,0,0,0,0,0,0,0]
        }
        else if(this.infoList.length >0){
          var size=this.infoList.size;
          var data=[];
          if(size<13)
     
          {
            for(var i=0;i<size-1;i++){
            data.unshift(this.infoList[i].date)
             }
          }else{
            for(var i=0; i<13;i++){
              data.unshift(this.infoList[i].date)
            }
           }
        }
            return data;
      },
      getcpuUsed:function(){
        var cpuUsed=0
        if(typeof(this.cpuUsed)=="undefined" || this.cpuUsed==0){
          console.log(11111111111111)
        }
        else{
          cpuUsed=this.cpuUsed
        }
        return cpuUsed
      },
      getcpuUnUsed:function(){
        var cpuUnUsed=1
        if(typeof(this.cpuUnUsed)=="undefined" || this.cpuUnUsed==0){
          console.log(11111111111111)
        }
        else{
          cpuUnUsed=this.cpuUnUsed
        }
        return cpuUnUsed
      },
      getmemUsed:function(){
        var memUsed=0
        if(typeof(this.memUsed)=="undefined" || this.memUsed==0){
          console.log(11111111111111)
        }
        else{
          memUsed=this.memUsed
        }
        return memUsed
      },
      getmemUnUsed:function(){
        var memUnUsed=1
        if(typeof(this.memUnUsed)=="undefined" || this.memUnUsed==0){
          console.log(11111111111111)
        }
        else{
          memUnUsed=this.memUnUsed
        }
        return memUnUsed
      },
      getdisUsed:function(){
        var disUsed=0
        if(typeof(this.disUsed)=="undefined" ||this.disUsed==0){
          console.log(11111111111111)
        }else{
          disUsed=this.disUsed
        }
        return disUsed
      },
      getdisUnUsed:function(){
        var disUnUsed=1
        if(typeof(this.disUnUsed)=="undefined" || this.disUnUsed==0){
          console.log(11111111111111)
        }else{
          disUnUsed=this.disUnUsed
        }
        return disUnUsed
      },
      getcpuUsedPer:function(){
        
        if (typeof(this.infoList)=="undefined" ||this.infoList.length==0) {
        	var data=[0,0,0,0,0,0,0,0,0,0,0,0,0]
        }else{
          var size=this.infoList.length;
          var data=[];
        if(size<13)
        {
          for(var i=0;i<size -1;i++){
            data.unshift(parseFloat(this.infoList[i].cpuUsedPer))
          }
        }else{
          for(var i=0;i<13;i++){
            data.unshift(parseFloat(this.infoList[i].cpuUsedPer))
          }
        }
       }
            return data;

      },
      getmemUsedPer:function(){
        if (typeof(this.infoList)=="undefined" ||this.infoList.length==0) {
        	var data=[0,0,0,0,0,0,0,0,0,0,0,0,0]
        }else{
          var size=this.infoList.length;
          var data=[];
        if(size<13)
        {
          for(var i=0;i<size -1;i++){
            data.push(parseFloat(this.infoList[i].memUsedPer))
          }
        }else{
          for(var i=0;i<13;i++){
            data.push(parseFloat(this.infoList[i].memUsedPer))
          }
         }
        }
        
            return data;
            
      },
      getdisUsedPer:function(){
        if (typeof(this.infoList)=="undefined" ||this.infoList.length==0) {
        	var data=[0,0,0,0,0,0,0,0,0,0,0,0,0]
        }else{
          var size=this.infoList.length;
          var data=[];
          if(size<13)
          {
            for(var i=0;i<size -1;i++){
            data.push(parseInt(this.infoList[i].disUsedPer))
            }
          }else{
            for(var i=0;i<13;i++){
              data.push(parseFloat(this.infoList[i].disUsedPer))
             }
          }
        }
          
            return data;

      }


    },
    components: {
      Bottom,
      Top
    },
    beforeDestroy () {
      clearInterval(this.intervalBlock)
    }
  }

</script>

<style>
</style>
