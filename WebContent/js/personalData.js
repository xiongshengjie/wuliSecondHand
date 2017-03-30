

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
			else{
				return str;
			}
		}
		input.value = hide(input.value);
	})();

	$("body").css("display","block");
	$.ajax(
	{
		type:"get",
		data:"",
		dataType:"json",
		url:"",
		success:function()
		{
			$("body").css("display","none");
		}
	})

}