$(function () {
  
   let id = isquery.querystring(location.search).id
   console.log(id);


   $('#inputCover').on('change',function () {
    let myimg = $('#inputCover')[0].files[0]
    let url = URL.createObjectURL(myimg)
    $('.article_cover').attr('src',url)
    })
   isquery.chajian()
     
   $('.btn-success').click(function (e) {
    e.preventDefault()
    ajaxData('已发布')
})
$('.btn-default').click(function(e){
    e.preventDefault()
    ajaxData('草稿')
})

function ajaxData(state) {
    let formdata = new FormData($('#form')[0])
    // 追加富文本框的数据
    formdata.append('content',tinymce.activeEditor.getContent())
    // 追加id
    formdata.append('id',id)
    // 追加状态
    formdata.append('state',state)
        $.ajax({
            type:'post',
            url:bigapi.articleedit,
            data:formdata,
            dataType:'json',
            processData:false,
            contentType:false,
            success:function (res) {
                if(res.code == 200){
                    location.href = './article_list.html'
                }
                alert(res.msg)
            }
        })
}
})