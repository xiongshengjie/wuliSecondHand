function out(c)
	{
		var a = document.getElementById(c);
		$.ajax(
		{
			type:"post",
			data:{"id":c,"type":"del"},
			dataType:"json",
			url:"../deleteProduct",
			success:function(data)
			{
				var jsonData=eval(data);
				if(jsonData.errCode==1)
					{
						alert("下架失败");
						return;
					}
				alert("下架成功");
				$(a).hide();
			}
		})
		
	}
window.onload=function()
{
	
	var currentPage=1;//表示加载数据的页数
	//首页加载
	var xmlhttp;  //存放xhr对象
	if(window.XMLHttpRequest)  //如果浏览器支持
	{
		xmlhttp=new XMLHttpRequest();
	}
	else   //如果不支持
	{
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");

	}
	xmlhttp.onreadystatechange=function()   //xhr对象状态改变时（即后台已经开始处理或者处理完毕）一次传递处理6个对象
	{
		if(xmlhttp.readyState==4&&xmlhttp.status==200)
	    {
			//后台处理完毕以后的操作
			var jsonData=xmlhttp.responseText;
			//解析json数据
			var data=JSON.parse(jsonData);
			var img=new Array();
			var title=new Array();
			var price=new Array();
			var id=new Array();
					
			for(var i=0;i<data.ps.length;i++)
			{
				var img_arr=[];
				img_arr=data.ps[i].imgurlcompress.split("|");
				img.push(img_arr[0]);
				title.push(data.ps[i].title);

				//修改
				if(data.ps[i].flag==0) //出售信息
				{
					price.push(data.ps[i].price);
				}
				else  //求购信息
				{
					var ac_price=(parseFloat(data.ps[i].price)-parseFloat(data.ps[i].flag))+"-"+(parseFloat(data.ps[i].price)+parseFloat(data.ps[i].flag));
					price.push(ac_price);
				}


				id.push(data.ps[i].id);
				
			}
			for(var flag=0;flag<img.length;flag++)
			{
				createObj(img[flag],price[flag],title[flag],id[flag]);	
			}
			
	    }
    }
	
	xmlhttp.open("GET","../showProductByPage?currentPage="+currentPage+"&type=personCenter",false);
	xmlhttp.send(null);
}

	function createObj(imgurl,price,title,id)  //flag用来判断a 标签应该加main-rignt 还是main-left flag%2=0代表左边，flag%2=1代表右边
	{

		var $main=$(".main");
		var Tag_div=$("<div>").addClass("good").appendTo($main); //加入 main中
		$(Tag_div).attr("id",id);
		/*var Tag_img=$("<img>").addClass("good-pic").appendTo($(Tag_div));
		Tag_img.attr("src",'../'+imgurl);*/

		var Tag_imgdiv=$('<div>').addClass('good-pic').appendTo($(Tag_div));
		Tag_imgdiv.css('background','url(../'+imgurl+') center no-repeat');
		Tag_imgdiv.css('background-size','cover');
		//点击图片进入detail页面
		$(Tag_imgdiv).click(function()
		{
			var goods_id=$(this).parent().attr("id");
			sessionStorage.goods_id=goods_id;
			window.location.href="details.html";				
		});
		var Tag_ul=$("<ul>").css("float","left").appendTo($(Tag_div));
		var Tag_li_name=$("<li>").addClass("good-name").appendTo($(Tag_ul));
		var Tag_li_price=$("<li>").addClass("good-price").appendTo($(Tag_ul));

		var Tag_a=$("<a>").addClass("good-out").appendTo($(Tag_div));
		$(Tag_a).attr("href","#");
		$(Tag_a).attr("onclick","out('"+id+"')");
		$(Tag_a).text("下架");
		$(Tag_li_name).text(title);
		$(Tag_li_price).text("￥"+price);
		
	}
