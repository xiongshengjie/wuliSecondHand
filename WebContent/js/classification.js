window.onload=function()
{
	document.title=sessionStorage.class_id;
   var currentPage_sale=1;
   var currentPage_buy=1;
   $("#banner-sale").click(function(){ask_sale_info(currentPage_sale,true)});
   $("#banner-sale").click();//一开始进入sale，所以需要加载一次
   //ask_sale_info(currentPage)
   $("#banner-buy").click(function(){ask_buy_info(currentPage_buy,true)});

	
//如果下拉到最后，则进行刷新 sale info
	$(window).on('scroll',function()
	{
		if(needUpload())    //下拉到最下
		{
			if($("#main-buy").css("display")=="none")  //当前显示的sald信息
			{
				var currentPage=$("#main-sale>a").length/6+1;
				var Int_number=/^[0-9]*[1-9][0-9]*$/;
				if(Int_number.test(currentPage))
				{	
					ask_sale_info(currentPage,false); //请求sald信息
					return;
				}
			}
			if($("#main-buy").css("display")=="block")  //当前显示的是buy信息
			{
				var currentPage=$("#main-buy>a").length/6+1;
				var Int_number=/^[0-9]*[1-9][0-9]*$/;
				if(Int_number.test(currentPage))
				{	
					ask_buy_info(currentPage,false); //请求sald信息
					return;
				}
			}
			
		}
	}); 
}
	

function needUpload()
{
	var $lastBox;
	if($("#main-buy").css("display")=="none")  //当前显示的sald信息
	{
		$lastBox=$("#main-sale>a").last();//获得最后一个物品展示的a对象
	}
	if($("#main-buy").css("display")=="block")   //当前显示的buy信息
	{
		$lastBox=$("#main-buy>a").last();
	}
	var lastBoxTop=$lastBox.offset().top+Math.floor($lastBox.outerHeight()*2/3);
	var scrollTop=$(window).scrollTop(); //滚动条滚动的高度
	var dH=$(window).height();  //浏览器的高度
	return (lastBoxTop<scrollTop+dH)?true:false;
	
}
function createObj_buy(imgurl,price,title,flag,id)  //flag用来判断a 标签应该加main-rignt 还是main-left flag%2=0代表左边，flag%2=1代表右边
{
	var $main_buy=$('#main-buy');
	if(flag%2==1)  //1=左边
	{
		var Tag_a=$('<a>').addClass("goods-list main-right col-xs-6 zero_a").appendTo($main_buy);  //a标签
	}
	else
	{
		var Tag_a=$('<a>').addClass("goods-list main-left col-xs-6 zero_a").appendTo($main_buy);
	}
	//当中价格内容的操作可能需要改变
	var Tag_img=$('<img>').addClass("center-block img_size").appendTo($(Tag_a)); //创建图片
	Tag_img.attr('src','../'+imgurl);
	var Tag_ul=$('<ul>').addClass("goods-detial").appendTo($(Tag_a));
	var Tag_li_name=$('<li>').addClass("goods-name").appendTo($(Tag_ul));
	var Tag_li_id=$('<li>').addClass("goods-id").appendTo($(Tag_ul));
	var Tag_li_price=$('<li>').addClass("goods-price").appendTo($(Tag_ul));
	$(Tag_li_name).text(title);
	$(Tag_li_id).text(id);
	$(Tag_li_price).text(price+"元");
	
}
function createObj(imgurl,price,title,flag,id)  //flag用来判断a 标签应该加main-rignt 还是main-left flag%2=0代表左边，flag%2=1代表右边
{
	var $main_sale=$('#main-sale');
	if(flag%2==1)  //1=左边
	{
		var Tag_a=$('<a>').addClass("goods-list main-right col-xs-6 zero_a").appendTo($main_sale);  //a标签
	}
	else
	{
		var Tag_a=$('<a>').addClass("goods-list main-left col-xs-6 zero_a").appendTo($main_sale);
	}
	var Tag_img=$('<img>').addClass("center-block img_size").appendTo($(Tag_a)); //创建图片
	Tag_img.attr('src',"../"+imgurl);
	var Tag_ul=$('<ul>').addClass("goods-detial").appendTo($(Tag_a));
	var Tag_li_name=$('<li>').addClass("goods-name").appendTo($(Tag_ul));
	var Tag_li_id=$('<li>').addClass("goods-id").appendTo($(Tag_ul));
	var Tag_li_price=$('<li>').addClass("goods-price").appendTo($(Tag_ul));
	$(Tag_li_name).text(title);
	$(Tag_li_id).text(id);
	$(Tag_li_price).text(price+"元");
	
}
//请求buy信息
function ask_buy_info(currentPage,isfirst)
{
	if(isfirst) //第一次请求，清空原本的元素
	{
		$("#main-buy").html("");
	}
	//求购信息
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
				img.push(data.ps[i].imgurlcompress);
				title.push(data.ps[i].title);
				price.push(data.ps[i].price);
				id.push(data.ps[i].id);
			}
			for(var flag=0;flag<img.length;flag++)
			{
				createObj_buy(img[flag],price[flag],title[flag],flag,id[flag]);	
			}
			$("#main-buy>a").click(function()  //问题 怎么获取点击的a下面的 goods-id
			{
				var goods_id_Tag=$(this).find("ul").find("li")[1];
				var goods_id=$(goods_id_Tag).text();
				sessionStorage.goods_id=goods_id;
				window.location.href="details.html";
			})
	    }
    }
	xmlhttp.open("GET","../showProductByPage?currentPage="+currentPage+"&hello=world"+"&category="+sessionStorage.class_id,false);
	xmlhttp.send(null);
	
}
//请求sale信息
function ask_sale_info(currentPage,isfirst)
{
	if(isfirst) //第一次请求，清空原本的元素
	{
		$("#main-sale").html("");
	}
	//出售信息
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
				//img.push(data.ps[i].imgurlcompress);
				title.push(data.ps[i].title);
				price.push(data.ps[i].price);
				id.push(data.ps[i].id);
			}
			for(var flag=0;flag<img.length;flag++)
			{
				createObj(img[flag],price[flag],title[flag],flag,id[flag]);	
			}
			$("#main-sale>a").click(function()  //问题 怎么获取点击的a下面的 goods-id
			{
				var goods_id_Tag=$(this).find("ul").find("li")[1];
				var goods_id=$(goods_id_Tag).text();
				sessionStorage.goods_id=goods_id;
				window.location.href="details.html";
			})
	    }
    }
	xmlhttp.open("GET","../showProductByPage?currentPage="+currentPage+"&category="+sessionStorage.class_id,false);
	xmlhttp.send(null);
}