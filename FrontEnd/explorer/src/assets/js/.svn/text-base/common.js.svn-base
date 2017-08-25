export default {
  GetTime : function(createTime) {
    var currentdate = new Date();


    //var stringTime = "2014-07-10 10:21:12";
    var timestamp2 = Date.parse(new Date(createTime));
    //timestamp2 = timestamp2 / 1000;
    //2014-07-10 10:21:12的时间戳为：1404958872
    //console.log(stringTime + "的时间戳为：" + timestamp2);

    var s1 = currentdate.getTime(),s2 = timestamp2;
    //var s1 = currentdate,s2 = createTime;
    var total = (s1 - s2)/1000;

    var day = parseInt(total / (24*60*60));//计算整数天数
    var afterDay = total - day*24*60*60;//取得算出天数后剩余的秒数
    var hour = parseInt(afterDay/(60*60));//计算整数小时数
    var afterHour = total - day*24*60*60 - hour*60*60;//取得算出小时数后剩余的秒数
    var min = parseInt(afterHour/60);//计算整数分
    var afterMin = total - day*24*60*60 - hour*60*60 - min*60;//取得算出分后剩余的秒数

    var timeStr = "";
    if(day>=1){
      timeStr=day+"天前";
    }else if(hour>=1){
      timeStr=hour+"小时前";
    }else if(min>=1){
      timeStr=min+"分前";
    }else{
      timeStr="1分内";
    }
    return timeStr;
  }
}
