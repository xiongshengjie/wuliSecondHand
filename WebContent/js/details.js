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
				if(weChatD==""||!weChatD)//卖家未填写微信
				{
					$("#weChat").css("display","none");
				}	
				if(qqD==""||!qqD)//卖家未填写QQ
				{
					$("#qq").css("display","none");
				}
				if(telNumD==""||!telNumD) //未填写电话
				{
					$("#telNum").css("display","none");
					$("#telCall").css("display","none");
				}
				
				if (t=="weChat") 
				{
					alert("已复制到您的粘贴板!");
					s=weChatD;
				}
				if (t == "qq") 
				{
					
					alert("已复制到您的粘贴板!");
					s=qqD;
				}
				if (t == "telNum") 
				{
					s=telNumD;
				}
			}
			
		}
	});
	return s;
}


window.onload=function()
{
	//给联系方式的按钮添加clipboard属性
	//QQ
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

        //微信
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

        
        
        /*var clipboard_telNum = new Clipboard("#telNum", {
        text: function() {
                return request_cn("telNum");
            }
        });

        clipboard_telNum.on('success', function() {
        });

        clipboard_telNum.on('error', function() {
        	alert("复制失败!");
        });*/


		//点击添加或取消收藏
        document.getElementById('collection').onclick = function changeImage() 
		{
            var good_id=sessionStorage.goods_id;
            var srcimg=$("#collection").attr("src");
            if(srcimg=="../img/noselect.png") //当前是未收藏状态,则执行收藏功能
            {
				$.ajax(
				{
					type:"GET",
					url:"../addCollection",
					data:{"id":good_id},
					dataType:"json",
					async:false,
					success: function(data)
					{
						var image = document.getElementById('collection');
			            image.src = "../img/select.png";
			            alert("收藏成功");		
					}
				});
			}
			else  //当前是收藏状态，则执行取消收藏功能
			{
				$.ajax(
				{
					type:"post",
					url:"../delCollection",
					data:{"id":good_id},
					dataType:"",
					async:false,
					success: function(data)
					{
						var image = document.getElementById('collection');
			            image.src = "../img/noselect.png";
			            alert("取消收藏成功");

							
					}
				});				
			}
        };

		//点击弹出联系方式列表
		document.getElementById('contact').onclick = function () {
			//发送短信
			var telnum=request_cn("telNum");
			if(telnum!="")
			{
				var u=navigator.userAgent;
				var app=navigator.appVersion;
				var isAndroid=u.indexOf("Android")>-1||u.indexOf("Linux")>-1;
				var isIos=u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/);
				if(isAndroid)
				{
					$("#telNum").attr("href","sms:"+telnum+"?body=您好！我是在wuli二手上面看到您的宝贝。");

				}
				else if(isIos)
				{
					$("#telNum").attr("href","sms:"+telnum+"&body=您好！我是在wuli二手上面看到您的宝贝。");
				}
				else
				{
					$("#telNum").attr("href","sms:"+telnum);
				}
				$("#telCall").attr("href","tel:"+telnum);
			}
			
			$("#contact-list").fadeIn();
		};

		//点击隐藏联系方式列表
		document.getElementById('cancel').onclick = function () {
			$("#contact-list").fadeOut();
		};
		//加载具体信息
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
				
				var username=jsonData.user.nickname;
				if(username==null){
					username = jsonData.product.user;
				}
				var whatclass=jsonData.user.grade+" "+jsonData.user.classes;

				var issuetime=jsonData.product.pushtime;
				var pricename=jsonData.product.isbargin=="on"?"一口价":"可协商";
				
				var t_price=0;
				if(jsonData.product.flag==0)
				{
					t_price=jsonData.product.price;
				}
				else
				{
					t_price=(parseFloat(jsonData.product.price)-parseFloat(jsonData.product.flag))+"-"+(parseFloat(jsonData.product.price)+parseFloat(jsonData.product.flag));
				}
				var price="￥"+t_price;

				var goods_name=jsonData.product.title;
				var isColl=jsonData.iscoll;
				if(isColl==1)
				{
					var image = document.getElementById('collection');
					image.src = "../img/select.png";
				}
				var imgurl=jsonData.product.imgurlcompress;
				var img_arr=[];
				img_arr=imgurl.split("|");//取出多张图片

				var schoolarea=jsonData.product.schoolarea;
				var description=jsonData.product.description;
				$(".first-box .username").text(username);
				$(".first-box .whatclass").text(whatclass);
				$(".first-box .issuetime").text(issuetime);

				$(".first-box .pricename").text(pricename);
				$(".first-box .price").text(price);
				
				$(".second-box .goods-name").text(goods_name);
				//添加多张图片
				for(var i=0;i<img_arr.length-1;i++)
				{
					var imgTag=$("<img>").addClass("product img-responsive").attr("src","..//"+img_arr[i]).insertAfter($(".second-box>.goods-name"));
					$(imgTag).width($(window).width()*0.8);
				}
				
				$(".second-box .address .xiaoqu").text(schoolarea);
				$(".second-box .intro-product .intro-text").text(description);
				
				$("body").css("display","block");
			}
		});
}