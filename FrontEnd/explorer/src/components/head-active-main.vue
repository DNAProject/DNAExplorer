<template>
  <div id="top">
    <nav class="navbar navbar-inverse" style="background-color: rgb(24, 24, 32);margin-bottom:0px;"role="navigation">
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
            <form class="navbar-form navbar-right" id="search-form">
                <div class="form-group" id="search-group">
                  <input type="text" id="search-input" class="form-control wider" style="background-color:#404141;color:white;" placeholder="请输入交易ID、地址或区块高度">
                </div>
                <button type="button" class="btn btn-default" style="background-color:#333;color:white;border:0px;">Search</button>
            </form>            
        </div>
        </div>
    </nav>
<!--     <div class="search">
      <input  v-model="inputvalue" @keyup.enter="search" type="text" autocomplete="off" placeholder="请输入存证ID、存证地址或Appid" value=""/>
      <button type="submit"    @click="search" ><span class="glyphicon glyphicon-search"></span></button>
    </div> -->
  </div>
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
      },
      wider:function(){
        $('#search-input').addClass('wider')
      },
      narrow:function(){
        $('#search-input').removeClass('wider')
      }
    }
  }
</script>

<style>
  .wider{
    width:300px !important;
  }
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
</style>