

var pageSize = 36;
var pageNum = 1;
var total = 0;
$(function () {
    function init() {
        $.ajax({
            type: "POST",
            url: sendUrl + "findMechanismList",
            data: {pageNum: pageNum, pageSize: pageSize, mechanismName: $("#keyword").val()},
            dataType: "json",
            beforeSend: function (request) {
                request.setRequestHeader("Authorization", getCookie("token"));
            },

            success:function (data) {
                console.log(data);
                total = data.total;
                debugger;
                var htm = '';
                for (var i = 0; i < data.data.length; i++) {
                    htm+='<tr>\n' +
                        '            <td>'+data.data[i].id+'</td>\n' +
                        '            <td>'+data.data[i].mechanism_name+'</td>\n' +
                        '            <td>'+data.data[i].iphone+'</td>\n' +
                        '            <td>'+data.data[i].address+'</td>\n' +
                        '            <td class="text-center">'+dateLong2String(data.data[i].create_time)+'</td>\n' +
                        '            <td class="text-center">'+data.data[i].describe+'</td>\n' +
                        '            <td class="text-center caozuo" width="200">\n' +
                        '                <a onclick="reviewMechanism(' + 1 + ',' + data.data[i].user_id + ')">审核通过</a>\n' +
                        '                <a onclick="reviewMechanism(' + 2 + ',' + data.data[i].user_id + ')">审核拒绝</a>\n' +
                        '            </td>\n' +
                        '        </tr>';
                }
               $('#bodyList').html(htm);
            }
        })
    }
    init();
   
    function setPage(pageCurrent, pageSum) {
        $(".pagination").bootstrapPaginator({
            //设置版本号
            bootstrapMajorVersion: 3,
            // 显示第几页
            currentPage: pageCurrent,
            // 总页数
            totalPages: pageSum,
            //当单击操作按钮的时候, 执行该函数, 调用ajax渲染页面
            onPageClicked: function (event,originalEvent,type,cpage) {
                // 把当前点击的页码赋值给currentPage, 调用ajax,渲染页面
                page = cpage
                init()
            }
        })
    }



    $.ajax({
        type:'get',
        url:bigapi.artclecategory,
        dataType:'json',
        success:function (res) {
            console.log(res);
            
            let html = template('selectlist',res)
            $('#selCategory').html(html)
        }
    })
  $('#btnSearch').on('click',function (e) {
        e.preventDefault()
        page = 1
        init()
  })

  $('tbody').on('click','.delete',function () {
      let id = $(this).data('id')
      $.ajax({
          type:'post',
          url:bigapi.contentdelete,
          dataType:'json',
          data:{id},
          success:function (res) {
           if(res.code == 204){
               alert(res.msg)
               init()
           }
          }
      })
  })
})
function reviewMechanism(status,user_id) {
    $.ajax({
        type: "POST",
        url: sendUrl + "reviewMechanism",
        data: {userId: user_id,status: status},
        dataType: "json",
        beforeSend: function (request) {
            request.setRequestHeader("Authorization", getCookie("token"));
        },
        success: function (data) {
            console.log(data);
            if (data.state == 'success') {
                alert("机构审核成功")
            } else {
                alert(data.message)
            }
        }
    });
}