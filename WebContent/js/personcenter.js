window.onload=function()
{
	$.ajax(
	{
		type:"post",
		data:"",
		dataType:"text",
		url:"../personCenter",
		success:function(data)
		{
		
			$("#user-name>span").text(data);
			$("body").css("display","block");
			$(".user-name").css("margin-left",(-$(".user-name").width()+$("#head>ul").width())/2);
			
		}
	})
}