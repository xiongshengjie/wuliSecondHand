window.onload=function()
{
	(function()
	{
		var input = document.getElementById('name');
		function hide(str){
			if(str.length == 13){
			var arr = [];
			arr = str.split("");
			arr.splice(-6,4,"*","*","*","*");
			return arr.join("");
			}
			else
			{
				return str;
			}
		}
		input.value = hide(input.value);
	})();

	//$("body").css("display","block");
	//获取用户的信息
	$.ajax(
	{
		type:"get",
		data:"",
		dataType:"json",
		url:"../findUser",
		success:function(data)
		{
			
			var jsonData=eval(data);
			if(jsonData.errCode==1)
				{
					alert("您还未设置个人信息");
					$("body").css("display","block");
					return;
					
				}
			var name=jsonData.user.nickname;
			if(name==null){
				name = jsonData.user.name.slice(0,7)+"****"+jsonData.user.name.slice(11);
			}
			var xueyuan=jsonData.user.institute;
			var grade=jsonData.user.grade;
			var classes=jsonData.user.classes;
			$("#name").val(name);
			var i=0;

			var xueyuan_op=$("#xueyuan option");
			var grade_op=$("#grade option");
			var classes_op=$("#classes option");

			$("#xueyuan option").attr("selected",false);
			$("#grade option").attr("selected",false);
			$("#classes option").attr("selected",false);

			for(i=0;i<xueyuan_op.length;i++)
			{
				var ct1=$(xueyuan_op[i]).val();
				if(ct1==xueyuan)
				{
					$(xueyuan_op[i]).attr("selected",true);
					break;
				}
			}
			for(i=0;i<grade_op.length;i++)
			{
				var ct2=$(grade_op[i]).val();
				if(ct2==grade)
				{
					$(grade_op[i]).attr("selected",true);
					break;
				}
			}
			for(i=0;i<classes_op.length;i++)
			{
				var ct3=$(classes_op[i]).val();
				if(ct3==classes)
				{
					$(classes_op[i]).attr("selected",true);
					break;

				}
			}
			$("body").css("display","block");
		}
	});

}