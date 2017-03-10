/*
无限下拉，与后台交互获取图片等信息，及时渲染网页
code by 田盛前
*/
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
					
			for(var i=0;i<data.ps.length;i++)
			{
				img.push(data.ps[i].imgurl);
				title.push(data.ps[i].title);
				price.push(data.ps[i].price);
			}
			for(var flag=0;flag<img.length;flag++)
			{
				createObj(img[flag],price[flag],title[flag],flag)	
			}
	    }
    }
	
	
	xmlhttp.open("GET","http://www.wulikm.cn/wuliSecondHand/showProductByPage?currentPage="+currentPage,false);
	
	xmlhttp.send(null);
	//如果下拉到最后，则进行刷新
	$(window).on('scroll',function()
	{
		if(needUpload())    //下拉到最下
		{
			currentPage=$("#main-sale>a").length/6+1;
			var Int_number=/^[0-9]*[1-9][0-9]*$/;
			if(Int_number.test(currentPage))
			{	
				xmlhttp.open("GET","http://www.wulikm.cn/wuliSecondHand/showProductByPage?currentPage="+currentPage,false);
				
				xmlhttp.send(null);
			}
		}
	}) 
}
	
/*$(window).on('scroll',function()
			{
				if(needUpload())    //下拉到最下
				{
					for(var flag=0;flag<6;flag++)
					{
						createObj("MockData/images/相机.png",5000,"商品名称",flag)	
					}
				}
				//uploadIndexPg(img,title,price);  
			})*/

function needUpload()
{
	var $lastBox=$("#main-sale>a").last();//获得最后一个物品展示的a对象
	var lastBoxTop=$lastBox.offset().top+Math.floor($lastBox.outerHeight()*2/3);
	var scrollTop=$(window).scrollTop(); //滚动条滚动的高度
	var dH=$(window).height();  //浏览器的高度
	return (lastBoxTop<scrollTop+dH)?true:false;
	
}
function createObj(imgurl,price,title,flag)  //flag用来判断a 标签应该加main-rignt 还是main-left flag%2=0代表左边，flag%2=1代表右边
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
	Tag_img.attr('src',imgurl);
	var Tag_ul=$('<ul>').addClass("goods-detial").appendTo($(Tag_a));
	var Tag_li_name=$('<li>').addClass("goods-name").appendTo($(Tag_ul));
	var Tag_li_price=$('<li>').addClass("goods-price").appendTo($(Tag_ul));
	$(Tag_li_name).text(title);
	$(Tag_li_price).text(price+"元");
	
}