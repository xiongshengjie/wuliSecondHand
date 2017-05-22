// JavaScript Document
$(window).on("load",function()
{
	$("#good_name").keyup(function()
	{
		var restword=this.value.length;
		$("#name_wordnum").html("<span>"+restword+"</span>/15");
	})
	$("#good_descript").keyup(function()
	{
		var restword=this.value.length;
		$("#descript_wordnum").html("<span>"+restword+"</span>/200");
	})  
    $("#publish_btn").click(function()
	{
		var good_name_length=$("#good_name").val().length;   //物品名字长度
		var good_descript_length=$("#good_descript").val().length;  //物品描述长度
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
		//判断图片文件的选择
		var img=$("[name='img']").val();
		var exStart=img.lastIndexOf(".");
		var imgtype=img.substring(exStart,img.length).toUpperCase();
		if(img=="")
		{
			alert("请选择图片文件！");
			return;
		}
		if(imgtype!=".BMP"&&imgtype!=".PNG"&&imgtype!=".GIF"&&imgtype!=".JPG"&&imgtype!=".JPEG")
		{
			alert("请正确选择图片文件！图片限于png,gif,jpeg,jpg格式");
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
		var sc_partten="校区选择";
		var ca_partten="分类选择";
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
		
		
		var imgURL=document.getElementById("tempcanvas").toDataURL();
		var imgb64=imgURL.substring(22);
		var form = new FormData(document.getElementById("upform"));
		form.append("img",imgb64);
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
    })
})