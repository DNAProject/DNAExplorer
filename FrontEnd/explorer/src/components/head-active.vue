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
                <li><a @click="toBlockInfo()" style="cursor:pointer">区块</a></li>
                <li><a @click="toTransactionInfo()" style="cursor:pointer">交易</a></li>
<!--                 <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">资产 <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu" style="background-color: #080808;">
                        <li><a style="color: #838586;" href="#">小蚁股 ANS</a></li>
                        <li><a style="color: #838586;" href="#">小蚁币 ANC</a></li>
                        <li><a style="color: #838586;" href="#">所有资产</a></li>
                    </ul>
                </li> -->
                <li><a @click="filtersearch()" style="cursor:pointer">高级查询</a></li>
                <li><a @click="toMonitor" style="cursor:pointer">监控平台</a></li>
            </ul>
            <form class="navbar-form navbar-right" id="search-form">
                <div class="form-group" id="search-group">
                  <input type="text" id="search-input" v-model="inputValue" @keyup.enter="quickSearch" class="form-control wider" style="background-color:#404141;color:white;border: 1px solid #111;" placeholder="请输入交易ID、地址或区块高度">
                </div>
                <button type="button" class="btn btn-default" @click="quickSearch" style="background-color:#333;color:white;border:0px;">查询</button>
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
      toMonitor:function(){
        this.$router.push({'path': '/monitor'})
      },
      back:function(){
        this.$router.push({'path': '/monitor'})
      },
      filtersearch:function(){
        this.$router.push({'path':'/filtersearch/0/0/0/1/15/0/0'})
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
      toBlockDetail:function(height){
        this.$router.push({'path':'/blockdetail/'+height})
      },
      toTransactiondetail:function(txid){
        this.$router.push({'path':'/transactiondetail/'+txid})
      },
      toAddressDetail:function(address){
        this.$router.push({'path':'/addressdetail/'+address})
      },
      quickSearch:function (){
        if(this.inputValue.length==64){
          this.toTransactiondetail(this.inputValue)
        }
        if(this.inputValue.length==34){
          this.toAddressDetail(this.inputValue)
        }
        if(this.inputValue.length<10){
          this.toBlockDetail(this.inputValue)
        }
          switch(this.inputValue.length)
            {
            case 64:
              this.toTransactiondetail(this.inputValue)
              break;
            case 34:
              this.toAddressDetail(this.inputValue)
              break;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
              this.toBlockDetail(this.inputValue)
              break;
            default:
              this.back()
              alert("请输入准确的高度，地址或者交易ID")
            }
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