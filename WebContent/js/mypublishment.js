window.onload=function()
{
	
	var currentPage=1;//表示加载数据的页数
	//首页加载
	var xmlhttp;  //存放xhr对象
	if(window.XMLHttpRequest)  //如果浏览器支持
	{
		xmlhttp=new XMLHttpRequest();
		xmlhttp1=new XMLHttpRequest();
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
				img.push(data.ps[i].imgurlcompress);
				title.push(data.ps[i].title);
				price.push(data.ps[i].price);
				id.push(data.ps[i].id);
			}
			for(var flag=0;flag<img.length;flag++)
			{
				createObj(img[flag],price[flag],title[flag],id[flag])	
			}
			
	    }
    }
	
	xmlhttp.open("GET","../showProductByPage?currentPage="+currentPage+"&type=personCenter",false);
	xmlhttp.send(null);

	
	//需要加载  更新needUpload
	$(window).on('scroll',function()
	{
		if(needUpload())    //下拉到最下
		{
			currentPage=$("#main-sale>a").length/6+1;
			var Int_number=/^[0-9]*[1-9][0-9]*$/;
			if(Int_number.test(currentPage))
			{	
				xmlhttp.open("GET","../showProductByPage?currentPage="+currentPage+"&type=personCenter",false);
				
				xmlhttp.send(null);
			}
		}
	}) 
}
	
//该功能函数需要更改
	function needUpload()
	{
		var $lastBox=$("#main-sale>a").last();//获得最后一个物品展示的a对象
		var lastBoxTop=$lastBox.offset().top+Math.floor($lastBox.outerHeight()*2/3);
		var scrollTop=$(window).scrollTop(); //滚动条滚动的高度
		var dH=$(window).height();  //浏览器的高度
		return (lastBoxTop<scrollTop+dH)?true:false;
		
	}

	function createObj(imgurl,price,title,id)  //flag用来判断a 标签应该加main-rignt 还是main-left flag%2=0代表左边，flag%2=1代表右边
	{

		var $main=$(".main");
		var Tag_div=$("<div>").addClass("good").appendTo($main); //加入 main中
		$(Tag_div).attr("id",id);
		var Tag_img=$("<img>").addClass("good-pic").appendTo($(Tag_div));
		Tag_img.attr("src",'../'+imgurl);
		var Tag_ul=$("<ul>").css("float","left").appendTo($(Tag_div));
		var Tag_li_name=$("<li>").addClass("good-name").appendTo($(Tag_ul));
		var Tag_li_price=$("<li>").addClass("good-price").appendTo($(Tag_ul));

		var Tag_a=$("<a>").addClass("good-out").appendTo($(Tag_div));
		$(Tag_a).attr("href","#");
		$(Tag_a).attr("onclick","out('"+id+"')");
		$(Tag_a).text("下载"); 
		$(Tag_li_name).text(title);
		$(Tag_li_price).text("￥"+price);
		
	}
	function out(c)
	{
		alert("555");
		$.ajax(
		{
			type:"post",
			data:{"id":c},
			dataType:"text",
			url:"../deleteProduct",
			success:function(data)
			{
				alert(data);
				$(a).hide();
			}
		})
		
	}