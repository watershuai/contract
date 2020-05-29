$(function () {
    if (!getCookie("token")) {
        alert("登录失效！请重新登录");
        location.href = 'login.html';
    }
    var token=getCookie("token");
    var id= localStorage.getItem("userId");
    $.ajax({
        type:'GET',
        url: sendUrl + "personInfo",
        dataType:'json',
        data: {id: localStorage.getItem("userId")},
        success:function (data) {
            debugger;
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