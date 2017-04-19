var nowFile=new Array();
var issend=false;
$(document).ready(function(){
    var fileInput=$("#fileImage")[0];
    $("#uploadimg").click(function()
    {       
            fileInput.click();
    });
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
    // nowFile = nowFile.concat(filter(files));
    nowFile = filter(files);
}
function filter(files) {
        var arrFiles = [];
        for (var i = 0, file; file = files[i]; i++) {
            if (file.type.indexOf("image") != -1) {
                // if (file.size >= 10240000){
                // 	alert('您这张图片大小过大，应小于1M');	
                // } else {
                // 	arrFiles.push(file);	
                // }	
                arrFiles.push(file);		
            } else {
                alert(file.type);
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
            $("#uploadimg").css("display","block");
        }
    nowFile = arrFile;
}
// function resetImage () {
    // nowFile = [];
    // $("#fileForm").html("");
    // $("#fileForm").html('<input id="fileImage1" type="file" size="30" name="f[]" multiple />');
    
    // $("#preview").html("");
    // $("#uploadimg").css("display","block");
// }
//复选框样式
$(document).ready(function(){
    $('input').iCheck({
        checkboxClass: 'icheckbox_minimal-yellow',
        radioClass: 'iradio_minimal-yellow',
        increaseArea: '20%' // optional
    });
});