// JavaScript Document
$(window).on("load",function()
{
	$(".login-btn").click(function()
	{
		var user=$(".user").val();
		var pass=$(".pass").val();
		if(user=="")
		{
			alert("请填写账号！");
			login_form.username.focus();
			return;
		}
		if(pass=="")
		{
			alert("请填写密码！");
			login_form.password.focus();
			return;
		}
		$("#wait").css("left",0);
		$.ajax
		({
			url:"../login",         //请求地址
			type:"POST",                       //请求方式
			data:{"username":user,"password":pass},           //请求参数
			dataType:"json",
			contentType:'application/x-www-form-urlencoded',
			success: function (data) 
			{
				if(data==false)
				{
					window.alert("您输入的用户名或密码有错！");
					login_form.username.focus();
					return;
				}
				else
				{
						
					window.location.href="../index.html";return;
				}
						
				
			},
			fail: function (status) 
			{}
		});//ajax
   })//click event
})