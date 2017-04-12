// detail.js code by 田盛前


function click_btn(o) {
	var good_id = sessionStorage.goods_id
	$.ajax({
		type : "post",
		url : "../getTel",
		data : {
			"id" : good_id
		},
		dataType : "json",
		success : function(data) {
			var jsonData = eval(data);
			alert("555");
			if (jsonData.errCode == 0) {
				var weChatD = jsonData.data.wechat;
				var qqD = jsonData.data.qq;
				var telNumD = jsonData.data.telnum;

				var clipboard = new Clipboard(o);
				if ($(o).attr("id") == "weChat") {
					o.dataset.clipboardText = weChatD;
				}
				if ($(o).attr("id") == "qq") {
					o.dataset.clipboardText = qqD;
				}
				if ($(o).attr("id") == "telNum") {
					o.dataset.clipboardText = telNumD;
				}

				clipboard.on('success', function(e) {
					alert("已复制到粘贴板!");
				});

				clipboard.on('error', function(e) {
					$(o).val(o.dataset.clipboardText);
					alert("复制失败,请手动复制!");
				});

			}else{
				window.location.href="login.html";
				return;
			}
		}
	});
}


window.onload=function()
{
		
		//点击添加或取消收藏
        document.getElementById('collection').onclick = function changeImage() 
		{
           var good_id=sessionStorage.goods_id;
				$.ajax(
				{
					type:"GET",
					url:"../getTel",
					data:{"id":good_id},
					dataType:"json",
					async:false,
					success: function(data)
					{
						var image = document.getElementById('collection');
			            if (image.src.match("noselect")) 
			            {
			                image.src = "../img/select.png";
			                alert("收藏成功");
			            } 
			            else 
			            {
			                image.src = "../img/noselect.png";
			                alert("取消收藏");
			            }
							
					}
				});
        };

		//点击弹出联系方式列表
		document.getElementById('contact').onclick = function () {
			$("#contact-list").fadeIn();
		};

		//点击隐藏联系方式列表
		document.getElementById('cancel').onclick = function () {
			$("#contact-list").fadeOut();
		};
		var good_id=sessionStorage.goods_id;
		$.ajax(
		{
			type:"GET",
			url:"../findProductById",
			data:{"id":good_id},
			dataType:"json",
			success: function(data)
			{
				var jsonData=eval(data);
				var userpic=null;
				var username=null;
				var whatclass=null;
				var issuetime=jsonData.pushtime;
				
				var weChat=jsonData.wechat;
				var qq=jsonData.qq;
				var telNum=jsonData.telnum;
				var pricename=jsonData.isbargin=="on"?"一口价":"可协商";
				var price="￥"+jsonData.price;
				var goods_name=jsonData.title;
				var imgurl=jsonData.imgurl;
				var schoolarea=jsonData.schoolarea;
				var description=jsonData.description;
				
				$(".first-box>.pricename").text(pricename);
				$(".first-box>.price").text(price);
				$(".first-box>.issuetime").text(issuetime);
				$(".second-box>.goods-name").text(goods_name);
				var imgTag=$("<img>").addClass("product img-responsive").attr("src","..//"+imgurl).insertAfter($(".second-box>.goods-name"));
				$(imgTag).width($(window).width()*0.8);
				$(".second-box>.address>.xiaoqu").text(schoolarea);
				$(".second-box>.intro-product>.intro-text").text(description);
				
				//这个部分的js需要修改下
				$("body").css("display","block");
			}
		})
}