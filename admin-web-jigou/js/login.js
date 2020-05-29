$(function(){
    $('.input_sub').on('click',function () {
        var username = $('.input_txt').val()
        var password = $('.input_pass').val()
        $.ajax({
            url: sendUrl + "login",
            type: "POST",
            dataType:'json',
            data: {userName: username,password:password},
            success(data) {
                if (data.state == 'success') {
                    setCookie("token", data.message, 1);
                    location.href = 'index.html'
                } else {
                    $('#myModal').modal('show');
                    $('#tipinfo').text(data.msg)
                }
            }
        })
    })
})