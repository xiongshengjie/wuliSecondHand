// detail.js code by 田盛前


//点击收藏
	 function changeImage() 
			{
				//alert(55);
				var good_id=sessionStorage.goods_id;
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
	            
	        }



window.onload=function()
{
	

		
		//点击弹出联系方式列表
        $(function()
		{
            //展示层
            function showLayer(id)
			{
                var layer = $('#'+id),
                layerwrap = layer.find('.hw-layer-wrap');
                layer.fadeIn();
                //屏幕居中
                layerwrap.css(
				{
                    'margin-top': -layerwrap.outerHeight()/2
                });
            }

            //隐藏层
            function hideLayer()
			{
                $('.hw-overlay').fadeOut();
            }

            $('.hwLayer-ok,.hwLayer-cancel,.hwLayer-close').on('click', function() 
			{
                hideLayer();
            });

            //触发弹出层
            $('.show-layer').on('click',  function() 
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
						
						var jsonData=eval(data);
						if(jsonData.errCode==1)
						{
							window.location.href="login.html";
							return;
						}
						var weChat=jsonData.data.wechat;
						var qq=jsonData.data.qq;
						var telNum=jsonData.data.telnum;
						$("#weChat").text(weChat);
						$("#qq").text(qq);
						$("#telnum").text(telNum);
						
						
					}
				});
				var layerid = $(this).data('show-layer');
       			showLayer(layerid);
                
            });

            //点击或者触控弹出层外的半透明遮罩层，关闭弹出层
            $('.hw-overlay').on('click',  function(event) 
			{

                if (event.target == this)
                {
                    hideLayer();
                }

            });

            //按ESC键关闭弹出层
            $(document).keyup(function(event) 
			{
                if (event.keyCode == 27) 
				{
                    hideLayer();
                }
            });
        });
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
					username = jsonData.user.name;
				}
				var whatclass=jsonData.user.grade+" "+jsonData.user.classes;

				var issuetime=jsonData.product.pushtime;
				var pricename=jsonData.product.isbargin=="on"?"一口价":"可协商";
				var price="￥"+jsonData.product.price;
				var goods_name=jsonData.product.title;
				var imgurl=jsonData.product.imgurl;
				var schoolarea=jsonData.product.schoolarea;
				var description=jsonData.product.description;
				
				$(".sec-box>.username").text(username);
				$(".sec-box>.whatclass").text(whatclass);
				$(".sec-box>.issuetime").text(issuetime);
				$(".sec-box>.pricename").text(pricename);
				$(".sec-box>.price").text(price);

				$(".thi-box>.goods-name").text(goods_name);
				var imgTag=$("<img>").addClass("product img-responsive").attr("src","..//"+imgurl).insertAfter($(".thi-box>.goods-name"));
				$(imgTag).width($(window).width()*0.8);
				$(".thi-box>.address>.xiaoqu").text(schoolarea);
				$(".thi-box>.intro-product>.intro-text").text(description);
				
				$("body").css("display","block");
			}
		});
		
		
}