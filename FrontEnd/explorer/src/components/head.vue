<template>
   <div class="header-monitor">
      <div class="row">
        <div class="col-sm-6">
          <!--<img src="../../static/img/onchain-logo.png" class="logo"/>-->
          <span style="padding-left: 10px;z-index: 99;position: relative;" @click="back" >DNA浏览器</span> 
        </div>
      </div>
    </div>
</template>

<script>
  export default {
    methods:{
      back:function(){
        this.$router.push({'path': '/monitor'})
      }
    }
  }
</script>

<style>
  .header-monitor span{
    color: #C6C7CD;
    font-size: 20px;
  }
  .header-monitor span:hover{
  	cursor: pointer;
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