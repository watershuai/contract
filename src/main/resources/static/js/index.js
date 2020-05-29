$(function () {
    debugger;
    if (!getCookie("token")) {
        alert("登录失效！请重新登录");
        location.href = 'login.html';
    }
    var token=getCookie("token");
    $.ajax({
        type:'GET',
        url: "/personInfo",
        dataType:'json',
        data: {token: getCookie("token")},
        beforeSend: function (request) {
            request.setRequestHeader("Authorization", getCookie("token"));
            request.setRequestHeader("Access-Control-Allow-Origin","localhost:8080");
            request.setRequestHeader("Access-Control-Allow-Credentials", "true");
        },
        success:function (data) {
            if(data.code == 200){
                $('.user_info span').html('欢迎&nbsp&nbsp ' + data.data.nickName)
                $('.user_info img').attr('src',data.data.userPic)
            }
        }
    })

    //文章
    $('.toggle').on('click',function () {
       $(this).next().find('ul').slideToggle()
       $(this).find('b').toggleClass('rotate0')
    })
    $('.level01').on('click',function () {
        $(this).addClass('active').siblings().removeClass('active')
    })
    $('.level02 li').on('click',function () {
        $(this).addClass('active').siblings().removeClass('active')
    })
})