<template>
    <nav class="navbar navbar-inverse" role="navigation">
        <div class="container-fluid"> 
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">DNA浏览器</a>
        </div>
        <div class="navbar-collapse collapse in" id="navbar-collapse-1"  aria-expanded="false">
            <ul class="nav navbar-nav">
                <li><a @click="toBlockInfo()">区块</a></li>
                <li><a @click="toTransactionInfo()">交易</a></li>
<!--                 <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">资产 <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu" style="background-color: #080808;">
                        <li><a style="color: #838586;" href="#">小蚁股 ANS</a></li>
                        <li><a style="color: #838586;" href="#">小蚁币 ANC</a></li>
                        <li><a style="color: #838586;" href="#">所有资产</a></li>
                    </ul>
                </li> -->
                <li><a @click="filtersearch()">高级查询</a></li>
            </ul>
        </div>
        </div>
    </nav>
</template>

<script>
  export default {
    methods:{
      back:function(){
        this.$router.push({'path': '/monitor'})
      },
      filtersearch:function(){
        this.$router.push({'path':'/filtersearch/namespace/0/0/1/10/0/0'})
      },
      toBlockInfo:function(){
        this.$router.push({'path':'/blockinfo/1/10'})
      },
      toAddressInfo:function(){
        this.$router.push({'path':'/addressinfo/1/10'})
      },
      toTransactionInfo:function(){
        this.$router.push({'path':'/transactioninfo/1/10'})
      },
      filtersearch:function(){
        this.$router.push({'path':'/filtersearch/namespace/0/0/1/10/0/0'})
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
.navbar-collapse{
  overflow: hidden;
}
</style>