// detail.js code by 田盛前
function request_cn(t) 
{	
	//var jsonData={"errCode":0,"data":{"category":"其他","description":"99成新ipad","id":"4bfcc0fc-c747-400e-9c47-ec37f43b4c59","imgurl":"productImg/10/12/cb24d770-6f2c-44e3-970e-c37b82be2170.jpg","imgurlcompress":"productImg/10/12/51ea9691-8124-4582-99a8-5d8a10e90ac9.jpg","isbargain":"on","ischange":"on","price":2000,"pushtime":"2017-04-08 00:22:09.0","qq":"123","schoolarea":"南湖","telnum":"15927201152","title":"ipad","user":"0121410880212","wechat":"123"}};
	var good_id = sessionStorage.goods_id;
	var s="";
	$.ajax({
		type : "post",
		url : "../getTel",
		data : {
			"id" : good_id
		},
		dataType : "json",
		async: false,
		success : function(data) 
		{
			var jsonData=eval(data);
			if(jsonData.errCode==1)
			{
				alert("您未登录，登陆以后可查看联系方式");
				window.location.href="login.html";
				return;
			}
			if (jsonData.errCode == 0) 
			{
				var weChatD = jsonData.data.wechat;
				var qqD = jsonData.data.qq;
				var telNumD = jsonData.data.telnum;	
				if (t=="weChat") 
				{
					if(weChatD==""||!weChatD)
					{
						alert("卖家未填写微信,请点击其他联系方式");
						return "";
					}
					alert("已复制到您的粘贴板!");
					s=weChatD;
				}
				if (t == "qq") 
				{
					if(qqD==""||!qqD)
					{
						alert("卖家未填写QQ,请点击其他联系方式");
						return "";
					}
					alert("已复制到您的粘贴板!");
					s=qqD;
				}
				if (t == "telNum") 
				{
					if(telNumD==""||!telNumD)
					{
						alert("卖家未填写电话,请点击其他联系方式");
						return;
					}
					alert("已复制到您的粘贴板!");
					s=telNumD;
				}
			}
			
		}
	});
	return s	;
}


window.onload=function()
{
	//给联系方式的按钮添加clipboard属性
	    var clipboard_qq = new Clipboard("#qq", {
        text: function() {
                return request_cn("qq") ;
            }
        });

        clipboard_qq.on('success', function(e) {	
        });

        clipboard_qq.on('error', function(e) {
        	alert("复制失败!");
        });


       	var clipboard_weChat = new Clipboard("#weChat", {
        text: function() {
                return request_cn("weChat");
            }
        });

        clipboard_weChat.on('success', function() {	
        });

        clipboard_weChat.on('error', function() {
        	alert("复制失败!");
        });


        var clipboard_telNum = new Clipboard("#telNum", {
        text: function() {
                return request_cn("telNum");
            }
        });

        clipboard_telNum.on('success', function() {
        });

        clipboard_telNum.on('error', function() {
        	alert("复制失败!");
        });


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