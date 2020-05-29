var sendUrl = "http://localhost:8082/";
//获取表单
function getFormParam(from_id){
	var data = {};
	var t = $('#'+from_id).serializeArray();
	$.each(t, function() {
	    data[this.name] = this.value;
	});
	return data;
}

function setCookie(key, value) {
    var oDate = new Date();
    oDate.setDate(oDate.getDate() + 60 * 20000);

    // Cookie 的expires 属性指定浏览器可发送Cookie 的有效期。当省略expires 属性时，Cookie仅在浏览器关闭之前有效。

    document.cookie = key + '=' + value + ';expires=' + oDate;

}
function removeCookie(key) {
    setCookie(key, '', -1);//这里只需要把Cookie保质期退回一天便可以删除
}
function getCookie(key) {
    var cookieArr = document.cookie.split('; ');
    for(var i = 0; i < cookieArr.length; i++) {
        var arr = cookieArr[i].split('=');
        if(arr[0] === key) {
            return arr[1];
        }
    }
    return false;
}
function dateLong2String(time){
    var date = new Date(time);
    var year = date.getFullYear();
    var month = date.getMonth()+1;
    var day = date.getDate();
    month = month < 10 ? "0"+month:month;
    day = day < 10 ? "0"+day:day;
    return year+"-"+month+"-"+day;
}