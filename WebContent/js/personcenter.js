window.onload=function()
{
	//$("body").css("display","block");
	$.ajax(
	{
		type:"get",
		data:"",
		dataType:"text",
		url:"../personCenter",
		success:function(data)
		{
		
			$("#user-name>span").text(data);
			$(".user-name").css("margin-left",(-$(".user-name").width()+$("#head>ul").width())/2);
			$("body").css("display","block");
			
		}
	})
}