function init(parm){
    var parmData = JSON.parse(parm);
    var url = "http://" +  parmData.host + ":" + parmData.port +"/data/app/find/" +parmData.lpid+"/bindapps/info?site=data&appCode="+parmData.appcode+"&mac="+parmData.imei;
    $.ajax({
        type: "GET",
        url: url,
        dataType: "json",
        success: function(data){
            done(data,parmData.host,parmData.port,parmData.imei);
        },
        error:function(){
            error({msg:"连接服务器（"+ parmData.host +"）失败.<br/>可能原因：服务器宕机或网络连接中断。（请联系技术人员，排查并修复！）"});
        }
    });
    return parm;
}

var done = function (re,host,port,mac) {
    var redUrl = "http://" + host + ":" + port + "/" + re.msg;//服务端版本首页URL
    window.location = redUrl ;
};

/** 终端页异常显示 */
var error = function (re) {
    $("#c").hide();
    $(".label").html(re.msg);
};
function initFailure(){
    error({msg:"获取连接参数失败["+ host +"].请联系管理员进行修复。"});
}