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

    var ex_html='<li><span>求职意向</span><select id="job_intention" name="job_intention" style="margin-left: 25px;"><option>人力/行政/管理</option><option>市场/媒介/公关</option><option>广告/会展/咨询</option><option>美术/设计/创意</option><option>生产管理/研发</option><option>物流/仓储</option><option>质控/安防 </option><option>汽车制造/服务</option><option>计算机/互联网/通信</option><option>电子/电气</option><option>机械/仪器仪表</option><option>法律</option><option>翻译</option><option>编辑/出版/印刷</option><option>财务/审计/统计</option><option>制药/生物工程</option><option>环保</option><option>建筑</option><option>软件/互联网开发</option><option>互联网产品/运营管理</option><option>IT运维与测试</option><option>化工</option></select></li>';


	var i=0;
	var arr=new Array();

	//人力/行政/管理
	arr[0]=new Array("文员","前台/总机/接待","人事专员/助理","行政专员/助理行政经理/主管","经理助理/秘书","招聘经理/主管");
	//市场/媒介/公关
	arr[1]=new Array("市场专员/助理","市场经理/总监","市场拓展","市场调研","市场策划","媒介专员/助理媒介经理/主管","会务会展专员/经理","品牌专员/经理","公关专员/助理","公关经理/主管","企划经理/主管");
	//广告/会展/咨询
	arr[2]=new Array("广告文案","创意指导/总监","广告设计/制作","广告创意","媒介策划/管理","会展策划/设计婚礼策划师","咨询顾问","咨询经理/主管","客户主管/专员","企业策划");
	//美术/设计/创意
	arr[3]=new Array("美编/美术设计", "服装设计","家具/家居用品设计","平面设计","美术指导","店面/陈列/展览设计","工艺/珠宝设计","多媒体/动画设计","产品/包装设计","装修装潢设计","CAD设计/制图"); 
	//生产管理/研发
	arr[4]=new Array("质量管理","设备管理维护","工艺设计","车间主任","生产计划","化验/检验","维修工程师","工业工程师","材料工程师","技术工程师");
	//物流/仓储
	arr[5]=new Array("物流专员/助理","调度员","快递员","仓库管理员","供应链管理","单证员","国际货运");
	//质控/安防
	arr[6]=new Array("质量管理/测试经理","质量检验员/测试员","测试工程师","安全消防","认证工程师/审核员","安全管理");
	//汽车制造/服务
	arr[7]=new Array("汽车设计工程师","装配工艺工程师","汽车/摩托车修理","汽车机械工程师","汽车电子工程师","4S店管理","汽车检验/检测","汽车美容","二手车评估师","发动机/总装工程师","安全性能工程师","理赔专员/顾问");
	//计算机/互联网/通信
	arr[8]=new Array("技术支持/维护","技术专员/助理","软件工程师","程序员","硬件工程师","质量工程师","测试工程师","系统架构师","数据库管理/DBA","游戏设计/开发","网页设计/制作","语音/视频/图形","产品经理/专员","网站运营","网站编辑","网站策划","网络管理员","网络与信息安全工程师","实施工程师","通信技术工程师");
	//电子/电气
	arr[9]=new Array("自动化工程师","电子/电气工程师","电路工程师/技术员","无线电工程师","测试/可靠性工程师","产品工艺/规划工程师","音频/视频工程师","灯光/照明设计工程师","研发工程师g","电子/电器维修");
	//机械/仪器仪表
	arr[10]=new Array("机电工程师","机械工程师","研发工程师","测试/可靠性工程师","版图设计工程师","仪器/仪表/计量");
	//法律
	arr[11]=new Array("律师/法律顾问","律师助理","法务专员/主管","产权/专利顾问","合规管理");
	//翻译
	arr[12]=new Array("英语翻译","日语翻译","韩语翻译","法语翻译","俄语翻译","德语翻译","西班牙语翻译","意大利语翻译","葡萄牙语翻译","阿拉伯语翻译","小语种翻译");
	//编辑/出版/印刷
	arr[13]=new Array("总编/副总编/主编","编辑/撰稿","记者/采编","出版/发行","排版设计/制作","印刷操作","装订/烫金");
	//财务/审计/统计
	arr[14]=new Array("会计/会计师","财务/会计助理","出纳","审计专员/助理","税务专员/助理","财务分析员","成本管理员");
	//制药/生物工程
	arr[15]=new Array("医药研发/生产/注册","临床研究/协调","生物工程/生物制药","医疗器械研发/维修");
	//环保
	arr[16]=new Array("污水处理工程师","环境工程技术","环境管理/保护","环保技术","EHS管理","环保工程师","环保检测水质检测员","环境绿化");
	//建筑
	arr[17]=new Array("工程项目管理", "工程监理", "建筑工程师/总工", "土木/土建工程师", "造价师/预算师", "幕墙工程师", "安防工程师安全管理/安全员","道路桥梁技术","给排水/制冷/暖通","测绘/测量","园林/景观设计","资料员","市政工程师","综合布线/弱电");
	//软件/互联网开发
	arr[18]=new Array("Web开发工程师","网站开发工程师","PHP开发工程师","Java软件开发工程师","c语言开发工程师","Android开发工程师","IOS开发工程师","ERP技术/开发应用","算法工程师","软件研发工程师","嵌入式");
	//互联网产品/运营管理
	arr[19]=new Array("产品专员/经理/主管", "产品运营", "游戏策划", "网店运营", "数据运", "市场运营", "运营主管/专员", "内容/新媒体运营", "电子商务专员/主管", "网站运营总监/经理");
	//IT运维与测试
	arr[20]=new Array("游戏测试","系统测试","软件测试","硬件测试","网络工程师","标准化工程师","配置管理工程师","计算机硬件维护工程师","系统管理员","网络管理","IT技术支持/维护","IT文档工程师/助理","ERP实施顾问","信息技术");
	//化工
	arr[21]=new Array("化工工程师","化学实验室技术员/研究员","化学分析","化学技术应用","化学操作","化学制剂研发","塑料工程师","橡胶工程师","配色技术员","化妆品研发","造纸研发","油漆/化工涂料研发","食品/饮料研发/检验","化工项目管理","化学/化工技术总监");
	

	//
	function ext_job(index)
	{
		if($("#extra_userinfo>li").length>1)
		{
			$("#extra_userinfo>li:eq(1)").remove();
			$("#extra_userinfo>li:eq(1)").remove();
		}
		var li_ct=$("<li>").appendTo(ct);
		$("<span>").text("具体方面").appendTo(li_ct);
		var op_ct=$("<select>").attr("id","ex_jobinfo").attr("name","ex_jobinfo");
		//var index=$("#job_intention")[0].selectedIndex;
		var l=0;
		for(l=0;l<arr[0].length;l++)
		{
			$("<option>").text(arr[index][l]).appendTo($(op_ct));
		}
		op_ct.appendTo(li_ct);
	}
	//改变简历选项的文字提示
	function change_lable(tr)
	{
		var file=$(tr)[0].files[0];
		var filename=file.name;


		var dotindex=filename.lastIndexOf(".")
		var ftype=filename.substring(dotindex+1).toLowerCase();
		if(ftype!="doc"&&ftype!="docx"&&ftype!="pdf")
		{
			alert("简历文件格式仅限于doc,docx,pdf");
			$("resume").val("");
			return;
		}
		$("#forresume").text(filename);
	}

	//动态创建选择项
	var ct=$("#extra_userinfo");//第二层 ul
	   //点击更多按钮
	$("#moreinfo").click(function()
	{
		$("#first_info").css("display","none");
		$("#second_info").css("display","block");
		ct.html("");
		ct.html(ex_html);
		ext_job(0);//创建默认二层选项
		var resume_ct=$("<li>").css("position","relative").appendTo(ct);
		$("<label>").attr("id","forresume").attr("for","resume").text("*上传简历").appendTo(resume_ct);
		var resume_file=$("<input>").attr("type","file").attr("name","resume").attr("id","resume").addClass("hidden_class").appendTo(resume_ct);
		resume_file.change(function()
		{
			change_lable(this);
		});
	});
		//点击上一页按钮
	$("#backpre").click(function()
	{
		$("#second_info").css("display","none");
		ct.html("");
		$("#first_info").css("display","block");
	});
	
        //动态改变
	$("#job_intention").change(function()
	{
		var index=$("#job_intention")[0].selectedIndex;
		ext_job(index);
		var resume_ct=$("<li>").appendTo(ct);
		$("<label>").attr("for","resume").text("*上传简历").appendTo(resume_ct);
		$("<input>").attr("type","file").attr("name","resume").attr("id","resume").addClass("hidden_class").appendTo(resume_ct);
	});
}