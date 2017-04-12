    var nowFile=new Array();
    var issend=false;
    $(document).ready(function(){
        var fileInput=$("#fileImage")[0];
        $("#uploadimg").click(function(){fileInput.click();});
        fileInput.addEventListener("change", function(e) {funGetFiles(e);}, false);	
    });
    function funGetFiles(e) {
        var files = e.target.files || e.dataTransfer.files;
        quchong(files);
        onSelect(nowFile);
    }
    function onSelect(files) {
            for (var i = 0, fil; fil = files[i]; i++) {
                fil.index = i;
            }
            if(files.length>0){$("#load_rq").fadeIn();}
            var html = '', i = 0;
            var funAppendImage = function() {
                file = files[i];
                if (file) {
                    var reader = new FileReader();
                    reader.onload = function(e) {
                        html += '<div id="uploadList_'+ i +'" class="upload_append_list" style="background:url(' + e.target.result + ') no-repeat center center;background-size:contain;">'+ 
                            '<a href="javascript:" class="upload_delete" title="删除" data-index="'+ i +'"></a><br />' +
                            '<span id="uploadProgress_' + i + '" class="upload_progress"></span>' +
                        '</div>';
                        
                        i++;
                        funAppendImage();
                    }
                    reader.readAsDataURL(file);
                } else {
                    if(i>0){
                        html+='<div onclick="add()" class="upload_append_list" id="pre_add"><br /></div>';
                        document.getElementById('uploadimg').style.display = 'none';
                    }
                    $("#load_rq").css("display","none");
                    $("#preview").html(html);
                    if (html) {
                        $(".upload_delete").click(function() {
                            funDeleteFile(files[parseInt($(this).attr("data-index"))]);
                            return false;	
                        });	
                    } 
                }
            };
        funAppendImage();		
    }
    function quchong(files){
        nowFile = nowFile.concat(filter(files));
    }
    function filter(files) {
            var arrFiles = [];
            for (var i = 0, file; file = files[i]; i++) {
                if (file.type.indexOf("image") == 0) {
                    // if (file.size >= 10240000){
                    // 	alert('您这张图片大小过大，应小于1M');	
                    // } else {
                    // 	arrFiles.push(file);	
                    // }	
                    arrFiles.push(file);		
                } else {
                    alert('您选择的不是图片。');	
                }
            }
            return arrFiles;
    }
    function add(){
        $("#fileImage")[0].click();
    }
    function funDeleteFile(fileDelete) {
            var arrFile = [];
            for (var i = 0, file; file = nowFile[i]; i++) {
                if (file != fileDelete) {
                    arrFile.push(file);
                } else {
                    $("#uploadList_" + fileDelete.index).remove();			
                }
            }
            if(arrFile.length==0){
                $("#pre_add").css("display","none");
                document.getElementById('uploadimg').style.display = 'block';
            }
        nowFile = arrFile;
    }

//复选框样式
$(document).ready(function(){
  $('input').iCheck({
    checkboxClass: 'icheckbox_minimal-yellow',
    radioClass: 'iradio_minimal-yellow',
    increaseArea: '20%' // optional
  });
});
window.onload=function()
{
$("#issue").click(function()
    {
        var good_name_length=$("[name='title']").val().length;   //物品名字长度
        var good_descript_length=$("[name='description']").val().length;  //物品描述长度
        var wechat_length=$("[name='wechat']").val().length;   //微信账号
        var qq_length=$("[name='qq']").val().length;  //qq长度
        if(good_name_length<1)
        {
            alert("物品名称未填写！");
            return;
        }
        if(good_descript_length<1)
        {
            alert("物品描述未填写！");
            return;
        }
        if(wechat_length<1)
        {
            alert("请填写微信账号！");
            return;
        }
        if(qq_length<1)
        {
            alert("请填写qq账号！");
            return;
        }
        //两个checbox判断
        var sc_partten="选择校区";
        var ca_partten="选择分类";
        var sc=$("[name='schoolarea']").val();
        var ca=$("[name='category']").val();
        if(sc_partten==sc)
        {
            alert("请选择校区！");
            return;
        }
        if(ca_partten==ca)
        {
            alert("请选择分类!");
            return;
        }
        var img=$("[name='img']").val();
        if(img=="")
        {
            alert("请选择图片文件！");
            return;
        }
        //判断电话号码
        var cell_partten = /^1[3,5,8]\d{9}$/;
        var partten = /^0(([1,2]\d)|([3-9]\d{2}))\d{7,8}$/;
        var phone_num=$("[name='telnum']").val();
        if(!(cell_partten.test(phone_num)||partten.test(phone_num)))
        {
            alert("电话格式不正确!");
            return;
        }
        //价格判断
        var price_length=$("[name='price']").val().length;
        if(price_length<1)
        {
            alert("请输入物品价格！")
            return;
        }
        
        var form = new FormData(document.getElementById("upform"));
        $.ajax({
            url:"../addProduct",         //请求地址
            type: "POST",                       //请求方式
            data:form,              //请求参数
            dataType: "json",
            processData:false,
            contentType:false,
            success: function(data) 
            {
                var json=eval(data);
                if(json.errCode==0)
                {
                    alert(json.errMsg);
                    window.location.href="../index.html";
                }
                if(json.errCode==1)
                {
                    alert(json.errMsg);
                }
            },
            fail: function (status) 
            {
                alert("失败");
            }
        });
    });
}