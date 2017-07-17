<template>
   <div class="header-monitor">
      <div class="row">
        <div class="col-sm-6 col-xs-9">
          <!--<img src="../../static/img/onchain-logo.png" class="logo"/>-->
          <span style="padding-left: 10px;" @click="back">DNA区块链监控平台</span> 
        </div>
        <div class="col-sm-6 col-xs-3" style="padding:0;">
          <span style="right:0;padding-right:10px;font-size:18px;"@click="filtersearch">高级查询</span>
        </div>
      </div>
    </div>
</template>

<script>
  export default {
    methods:{
      back:function(){
        this.$router.push({'path': '/monitor'})
      },
      filtersearch:function(){
        this.$router.push({'path':'/filtersearch/0/0/1/5'})
      }
    }
  }
</script>

<style>
  .header-monitor span{
    color: #C6C7CD;
    font-size: 20px;
  }
  .header-monitor{
  padding: 10px;
  background-color: #25262C;
}
.logo{
  width: 100px;
  margin-left: 10px;
}
</style>