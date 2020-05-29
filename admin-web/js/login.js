$(function(){
    $('.input_sub').on('click',function () {
        var username = $('.input_txt').val()
        var password = $('.input_pass').val()
        $.ajax({
            url: sendUrl + "toLogin",
            type: "POST",
            dataType:'json',
            data: {userName: username,password:password},

            success(data) {
                if (data.code == 200) {
                    debugger;
                    setCookie("token", data.msg);
                    localStorage.setItem("userId", data.data.id);
                    localStorage.setItem("userType", data.data.type);
                    localStorage.setItem("userName", data.data.name);
                    location.href = 'index.html'
                } else {
                    $('#myModal').modal('show');
                    $('#tipinfo').text(data.msg)
                }
            }
        })
    })
})