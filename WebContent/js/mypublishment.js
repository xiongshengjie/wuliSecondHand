window.onload=funtion()
{
	$("body").css("display","block");
	$.ajax(
	{
		type:"get",
		data:"",
		dataType:"text",
		url:"",
		async:false,
		success:function(data)
		{
			
			$("body").css("display","block");
		}
	})
	/*
	var xmlhttp;
	if(window.XMLHttpRequest)
	{
		xmlhttp=new XMLHttpRequest();
	}
	else
	{
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange= function()
	{
		if(xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			var userdata=xmlhttp.responeText;
			var username=data.slice(0,7)+"****"+data.slice(-2);
			$(".user-name").text(username);
		}
	}
	xmlhttp.open("post","");
	xmlhttp.send();
	*/
}