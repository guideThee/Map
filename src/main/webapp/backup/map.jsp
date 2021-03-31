<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+
					request.getServerName()+":"+
					request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="keywords"
	content="sonic, responsive, free template, fluid layout, bootstrap, templatemo" />
<meta name="description"
	content="Sonic is one-page responsive free template using Bootstrap. This layout is suitable for creative portfolio showcase." />
<meta name="viewport" content="initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="robots" content="all,follow">
<style type="text/css">
body, html {
	width: 100%;
	height: 100%;
	margin: 0;
	font-family: "微软雅黑";
}

#allmap {
	width: 100%;
	height: 100%;
	float: right;
}
#r-result {
	width: 100%;
	margin-top: 5px;
}

p {
	margin: 5px;
	font-size: 14px;
}
</style>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
<script src="<%=path%>/layer/layer.js"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=QWipRQbtworVjld3ZvAAv7T0TMQYRknD"></script>
<title>可视化智能物流配送系统</title>
<link rel="shortcut icon" href="<%=path%>/img/favicon.ico">
<!-- global stylesheets -->
<link href="https://fonts.googleapis.com/css?family=Roboto+Condensed"
	rel="stylesheet">
<link rel="stylesheet" href="<%=path%>/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=path%>/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="<%=path%>/css/font-icon-style.css">
<link rel="stylesheet" href="<%=path%>/css/style.default.css"
	id="theme-stylesheet">
<!-- Core stylesheets -->
<link rel="stylesheet" href="<%=path%>/css/ui-elements/card.css">
<link rel="stylesheet" href="<%=path%>/css/style.css">
<link rel="stylesheet" href="<%=path%>/layui/css/layui.css" media="all">
</head>
<body>

	<!--====================================================
                         MAIN NAVBAR
======================================================-->
	<header class="header"> <nav class="navbar navbar-expand-lg ">
	<div class="search-box">
		<button class="dismiss">
			<i class="icon-close"></i>
		</button>
		<form id="searchForm" name="searchForm" action="javascript:search()"
			role="search">
			<input type="search" name="search" placeholder="Search Now"
				class="form-control">
		</form>
	</div>
	<div class="container-fluid ">
		<div
			class="navbar-holder d-flex align-items-center justify-content-between">
			<div class="navbar-header">
				<a href="#" class="navbar-brand">
					<div class="brand-text brand-big hidden-lg-down">
						<img src="<%=path%>/img/logo-icon.png" alt="Logo" class="img-fluid">
						可视化智能物流配送系统
					</div>
					<div class="brand-text brand-small">
						<img src="img/logo-icon.png" alt="Logo" class="img-fluid">
					</div>
				</a> <a id="toggle-btn" href="#" class="menu-btn active"> <span></span>
					<span></span> <span></span>
				</a>
			</div>
		</div>
		<ul
			class="nav-menu list-unstyled d-flex flex-md-row align-items-md-center">
			<!-- Expand-->
			<!-- Search-->
			<li class="nav-item d-flex align-items-center"><a id="search"
				class="nav-link" href="#"><i class="icon-search"></i></a></li>
			<li class="nav-item dropdown"><a id="profile"
				class="nav-link logout" data-target="#" href="#"
				data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">${USER.userName}</a>
				<ul aria-labelledby="profile" class="dropdown-menu profile">
					<li><a rel="nofollow" href="javascript:yingcang()"
						class="dropdown-item">
							<div class="notification">
								<div class="notification-content">
									<i class="fa fa-user "></i>我的方案
								</div>
							</div>
					</a></li>
					<li><a rel="nofollow" href="login.jsp" class="dropdown-item">
							<div class="notification">
								<div class="notification-content">
									<i class="fa fa-power-off"></i>退出
								</div>
							</div>
					</a></li>
				</ul></li>
		</ul>
	</div>
	</nav> </header>

	<!--====================================================
                        PAGE CONTENT
======================================================-->
	<div class="page-content d-flex align-items-stretch">

		<!--***** SIDE NAVBAR *****-->
		<nav class="side-navbar"> <!-- Sidebar Navidation Menus-->
		<ul class="list-unstyled">
			<li><a href="#pages" aria-expanded="false"
				data-toggle="collapse"> <i class="fa fa-file-o"></i>文件
			</a>
				<ul id="pages" class="collapse list-unstyled">
					<li><a href="javascript:newPlan()">新建</a></li>
					<li><a href="javascript:restart()">重置</a></li>
					<li><a href="javascript:yingcang()">读取</a></li>
					<li><a href="javascript:save()">保存</a></li>
				</ul></li>
			<li><a href="#tables" aria-expanded="false"
				data-toggle="collapse"><i class="icon-grid"></i>标记 </a>
				<ul id="tables" class="collapse list-unstyled">
					<li><a href="javascript:suspended()">停止标记</a></li>
					<li><a href="javascript:Continue()">继续标记</a></li>
					<li><a href="javascript:setview()">调整视野</a></li>
				</ul></li>
			<li><a href="#forms" aria-expanded="false"
				data-toggle="collapse"> <i class="fa fa-building-o"></i>仓库
			</a>
				<ul id="forms" class="collapse list-unstyled">
					<li><a href="javascript:manual()">手动生成</a></li>
					<li><a href="javascript:manualstop()">停止生成</a></li>
					<li><a href="javascript:warehouse()">自动生成</a></li>
				</ul></li>
			<li><a href="javascript:calculate()"> <i class="fa fa-map-o"></i>生成解决方案
			</a></li>
		</ul>
		</nav>
		<div class="content-inner">
			<div id="Plantable" class="Plantable"><table id="demo" lay-filter="test"></table>		
			<script type="text/html" id="barDemo">
            <a class="layui-btn layui-btn-primary" lay-event="detail">查看</a>
            <a class="layui-btn layui-btn-danger" lay-event="del">删除</a>
            </script></div>
			<div id="allmap"></div>
		</div>
	</div>

	<!--Global Javascript -->
	<script src="<%=path%>/js/jquery.min.js"></script>
	<script src="<%=path%>/js/popper/popper.min.js"></script>
	<script src="<%=path%>/js/tether.min.js"></script>
	<script src="<%=path%>/js/bootstrap.min.js"></script>
	<script src="<%=path%>/js/jquery.cookie.js"></script>
	<script src="<%=path%>/js/jquery.validate.min.js"></script>
	<script src="<%=path%>/js/chart.min.js"></script>
	<script src="<%=path%>/js/front.js"></script>
	<script src="<%=path%>/layui/layui.js"></script>
	<!--Core Javascript -->
	<script src="<%=path%>/js/mychart.js"></script>
</body>
</html>
<script>
	var map;
	function Plan() {
	}
	var Plan = new Plan();
	var PlanName;
	var centerlabel;
	var discripoint = 0, discrimap = 0, discriwarehouse = 0, discriPlanName = 0,discridraw=0,discrimanual=0;
	var pointArray = new Array();//坐标数组
	var pointArraydetail=new Array();//处理坐标数组
	var sort = new Array();//ID序列数组
	var cluster = new Array();//ID序列数组
	var cangk = new Array();//仓库数组
	var Cpoint = new Array();//分类数组
	var local = new BMap.LocalSearch(map, {
		renderOptions : {
			map : map
		}
	});
	sort.length = 0;
	pointArray.length = 0;
	cangk.length = 0;
	var polyline;
	var Tdistance=parseFloat("0");
	var getRandomColor = function(){
	    return "hsb(" + Math.random()  + ", 1, 1)";
	 }
	//开始
	function newPlan() {
		var x=0;
		layer.prompt({
			title : '请输入方案名'
		}, function(value, index, elem) {
			$.ajax({
				async : false,
				type : "POST",
				url : "PlanController/newPlan",
				dataType : "json",
				contentType : "application/json;charset=UTF-8",
				data : JSON.stringify(value),
				crossDomain : true,
				success : function(data) {
					x=eval(data);
					if(x==1){
					PlanName = value;
					layer.close(index);
					discrimap=1;
					restart();
					}
					else
					{
						layer.msg('方案名已存在！', {icon: 5});
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(XMLHttpRequest.status);
					alert(XMLHttpRequest.readyState);
					alert(textStatus);
				}
			});		
		});
	}
	</script>
	<script>
	function start() {
		document.getElementById("Plantable").style.display="none";//隐藏
		document.getElementById("allmap").style.display="block";//显示
		discripoint = 1;
		discrimap = 1;
		// 百度地图API功能
		map = new BMap.Map("allmap", {
			enableMapClick : false
		});//构造底图时，关闭底图可点功能
		map.enableScrollWheelZoom(true); //开启鼠标滚轮缩放
		map.centerAndZoom("兰州", 15); // 初始化地图,用城市名设置地图中心点
		//1.添加工具条、比例尺控件
		var top_left_control = new BMap.ScaleControl({
			anchor : BMAP_ANCHOR_TOP_LEFT
		});// 左上角，添加比例尺
		var top_left_navigation = new BMap.NavigationControl(); //左上角，添加默认缩放平移控件
		map.addControl(top_left_control);
		map.addControl(top_left_navigation);

		//2.添加城市列表控件
		map.enableScrollWheelZoom();
		map.enableInertialDragging();
		map.enableContinuousZoom();
		var size = new BMap.Size(10, 20);
		map.addControl(new BMap.CityListControl({
			anchor : BMAP_ANCHOR_TOP_RIGHT,
			offset : size,
		// 切换城市之间事件
		// onChangeBefore: function(){
		//    alert('before');
		// },
		// 切换城市之后事件
		// onChangeAfter:function(){
		//   alert('after');
		// }
		}));
		var myIcon = new BMap.Icon("img/cangku.jpg", new BMap.Size(300, 157), {
			anchor : new BMap.Size(25, 25)
		});
		//3.添加标记
		map.addEventListener("click", function(e) {
			//标记
			if (discripoint == 1){
			local.clearResults();
			var point = new BMap.Point(e.point.lng, e.point.lat);//添加点坐标
			pointArray.length++;
			var spoint = new Object();
			spoint.id = pointArray.length;
			spoint.lng = e.point.lng.toString();
			spoint.lat = e.point.lat.toString();
			spoint.flage = 0;
			pointArray[pointArray.length - 1] = spoint;
			var marker = new BMap.Marker(point); // 创建标注  
			map.addOverlay(marker); // 将标注添加到地图中    
		    marker.disableMassClear();//mark不被清除
			var label = new BMap.Label(spoint.id, {
				offset : new BMap.Size(20, -10)
			});
			label.setStyle({
				fontSize : "20px"
			});
			marker.setLabel(label);		
			var removeMarker = function(e, ee, marker) {
				var marketpoint = marker.getPosition();
				var n, m, x;
				for (n = 0; n < pointArray.length; n++)
					if (pointArray[n].lng == marketpoint.lng
							&& pointArray[n].lat == marketpoint.lat) {
						x = pointArray[n].id;
						for (m = n; m < pointArray.length - 1; m++) {
							pointArray[m] = pointArray[m + 1];
							pointArray[m].id = m + 1;
						}
					}
				pointArray.length--;
				map.removeOverlay(marker);
				var List = map.getOverlays();
				for (var i = 0; i < pointArray.length+cangk.length; i++) {
					if(List[i].getLabel().content ==x+1){
					List[i].getLabel().setContent(x);
					x++;
					}
				}
			}
			//创建右键菜单
			var markerMenu = new BMap.ContextMenu();
			markerMenu.addItem(new BMap.MenuItem('删除', removeMarker
					.bind(marker)));
			marker.addContextMenu(markerMenu);
			}
			//添加仓库
			if(discrimanual==1){
				discriwarehouse = 2;
				discridraw=2;
				local.clearResults();
				var point = new BMap.Point(e.point.lng, e.point.lat);//添加点坐标
				cangk.length++;
				var spoint = new Object();
				spoint.id = cangk.length;
				spoint.lng = e.point.lng.toString();
				spoint.lat = e.point.lat.toString();
				spoint.flage = 0;
				cangk[cangk.length - 1] = spoint;
				var marker = new BMap.Marker(point, {
					icon : myIcon
				}); // 创建标注
				map.addOverlay(marker); // 将标注添加到地图中
				marker.disableMassClear();//mark不被清除
				var label = new BMap.Label("仓库" + spoint.id, {
					offset : new BMap.Size(20, -30)
				});
				label.setStyle({
					fontSize : "20px"
				});
				marker.setLabel(label);
				var removeMarker = function(e, ee, marker) {
					var marketpoint = marker.getPosition();
					var n, m, x;
					for (n = 0; n < cangk.length; n++)
						if (cangk[n].lng == marketpoint.lng
								&& cangk[n].lat == marketpoint.lat) {
							x = cangk[n].id;
							for (m = n; m < cangk.length - 1; m++) {
								cangk[m] = cangk[m + 1];
								cangk[m].id = m + 1;
							}
						}
					cangk.length--;
					map.removeOverlay(marker);
					var List = map.getOverlays();
					for (var i = 0; i < cangk.length+pointArray.length; i++) {
						if(List[i].getLabel().content =="仓库"+(x+1)){
						List[i].getLabel().setContent("仓库" + x);
						x++;
						}
					}
				}
				//创建右键菜单
				var markerMenu = new BMap.ContextMenu();
				markerMenu.addItem(new BMap.MenuItem('删除', removeMarker
						.bind(marker)));
				marker.addContextMenu(markerMenu);
			}
		});
	}
	</script>
	<script>
	//重置
	function restart() {
		if (discrimap == 0) {
			alert("请新建或读取方案!");
			return;
		}
		pointArray = new Array();//坐标数组
		sort = new Array();//ID序列数组
		cluster = new Array();//ID序列数组
		cangk = new Array();//仓库数组
		Cpoint = new Array();//分类数组
		sort.length = 0;
		pointArray.length = 0;
		discriPlanName = 0;
		discripoint = 0;
		discrimap = 0;
		discriwarehouse = 0;
		discridraw=0;
		discrimanual=0;
		Tdistance=parseFloat("0");
		start();
	}
	//停止标记
	function suspended() {
		if (discrimap == 0) {
			alert("请点击”开始使用“!");
			return;
		}
		discripoint = 0;
	}
	//继续标记
	function Continue() {
		if (discrimap == 0) {
			alert("请点击”开始使用“!");
			return;
		}
		discripoint = 1;
		discrimanual=0;
	}
	</script>
	<script>
	//调整视野
	function setview() {
		map.setViewport(pointArray);
	}
	//搜索
	function search() {
		if (discrimap == 0) {
			alert("请点击”开始使用“!");
			return;
		}
		if (discripoint == 1) {
			alert("搜索前请停止标记！");
			return;
		}
		var search = searchForm.search.value;
		local = new BMap.LocalSearch(map, {
			renderOptions : {
				map : map
			}
		});
		local.search(search);
	}
	//生成方案
	</script>
	<script>
	function calculate() {
		if (discrimap == 0) {
			alert("请点击”开始使用“!");
			return;
		}
		if (pointArray.length == 0) {
			alert("请标记完成后生成方案！");
			return;
		}
		if (pointArray.length == 1) {
			alert("标记过少！请继续标记！");
			return;
		}
		layer.msg('正在生成方案，请稍后...', {
			icon : 16,
			shade : 0.01,
			time : 100000
		});
		map.clearOverlays();
		Tdistance=0;
		if (discriwarehouse == 0){
			discridraw=1;
			calculate1(pointArray);
		}else if(discriwarehouse==1){
			calculate2();
		}
		else if(discriwarehouse==2){
			calculate3();
		}		
	}
	//无仓库方案
	</script>
	<script>
	function calculate1(list) {
		$.ajax({
			type : "POST",
			url : "getdata",
			dataType : "json",
			async : false,
			contentType : "application/json;charset=UTF-8",
			data : JSON.stringify(list),
			crossDomain : true,
			beforeSend: function () {
		        // 禁用按钮防止重复提交
				layer.msg('正在生成方案，请稍后...', {
					icon : 16,
					shade : 0.01,
					time : 100000
				});
		    },
			success : function(data) {
				sort = eval(data);
				draw(list);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(XMLHttpRequest.status);
				alert(XMLHttpRequest.readyState);
				alert(textStatus);
			}
		});
	}
	//无仓库方案绘图
	</script>
	<script>
	function draw(list) {
		var sortArray = new Array();//坐标数组
		for (var i = 0; i < list.length; i++) {
			sortArray.length++;
			for (var j = 0; j < list.length; j++) {
				if (sort[i] == list[j].id) {
					sortArray[sortArray.length - 1] = new BMap.Point(
							list[j].lng, list[j].lat);
				}
			}
		}
		var sy = new BMap.Symbol(BMap_Symbol_SHAPE_BACKWARD_OPEN_ARROW, {
			scale : 0.6,//图标缩放大小
			strokeColor : '#fff',//设置矢量图标的线填充颜色
			strokeWeight : '2',//设置线宽
		});
		var icons = new BMap.IconSequence(sy, '10', '30');
		// 创建polyline对象
		sortArray[sortArray.length] = new BMap.Point(sortArray[0].lng,
				sortArray[0].lat);
		polyline = new BMap.Polyline(sortArray, {
			enableEditing : false,//是否启用线编辑，默认为false
			enableClicking : true,//是否响应点击事件，默认为true
			icons : [ icons ],
			strokeWeight : '8',//折线的宽度，以像素为单位
			strokeOpacity : 0.8,//折线的透明度，取值范围0 - 1
			strokeColor :'#'+('00000'+ (Math.random()*0x1000000<<0).toString(16)).substr(-6)    //折线颜色
		});
		map.addOverlay(polyline); //增加折线
		map.setViewport(sortArray);
		FormLabel(list);
		layer.closeAll();
	}
	</script>
	<script>
	//手动生成仓库-1.标记仓库
	function manual() {
		discripoint = 0;
		discrimanual=1;
	}
	</script>
	<script>
	//手动生成仓库-1.停止仓库标记
	function manualstop() {
		discrimanual = 0;
		discripoint=1;
	}
	//手动生成仓库-2.分类
	function calculate3(){
		discridraw=3;
		for(var i=0;i<pointArray.length;i++){
			pointArray[i].flage=1;
		}
		for(var i=0;i<cangk.length;i++){
			for(var j=0;j<pointArray.length;j++){
				
				if(map.getDistance(new BMap.Point(cangk[i].lng, cangk[i].lat),new BMap.Point(pointArray[j].lng,
						pointArray[j].lat))<map.getDistance(new BMap.Point(cangk[pointArray[j].flage-1].lng, cangk[pointArray[j].flage-1].lat),new BMap.Point(pointArray[j].lng,
								pointArray[j].lat)))
					{
					pointArray[j].flage=i+1;
					}
			}
		}
		/*map.removeOverlay(polyline);*/
		var list=ReadDraw();
		for(var i=0;i<list.length;i++)
			calculate1(list[i]);
		/*map.setViewport(pointArray);*/
	}
	</script>
	<script>
	//自动生成仓库-1.仓库个数
	function warehouse() {
		layer.prompt({
			title : '请输入仓库个数'
		}, function(value, index, elem) {
			layer.msg('正在生成仓库，请稍后...', {
				icon : 16,
				shade : 0.01,
				time : 100000
			});
			Cwarehouse(value);
			layer.close(index);
		});
	}
	//自动生成仓库-2.聚类
	function Cwarehouse(value)
	{
		$.ajax({
			async : false,
			type : "POST",
			url : "generate",
			dataType : "json",
			contentType : "application/json;charset=UTF-8",
			data : JSON.stringify(pointArray) + value,
			crossDomain : true,
			success : function(data) {
				discridraw=2;
				cluster = eval(data);
				generate();
				discriwarehouse = 1;
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(XMLHttpRequest.status);
				alert(XMLHttpRequest.readyState);
				alert(textStatus);
			}
		});
	}
	</script>
	<script>
	//自动生成仓库-3.绘制仓库
	function generate() {
		var myIcon = new BMap.Icon("img/cangku.jpg", new BMap.Size(300, 157), {
			anchor : new BMap.Size(25, 25)
		});
		cangk.length = 0;
		for (var i = 0; i < cluster.length; i++) {
			cangk.length++;
			var p = eval(cluster[i]);
			cangk[i] = p[p.length - 1];
			cangk[i].id = i + 1;
			var pt = new BMap.Point(cangk[i].lng, cangk[i].lat);
			var marker = new BMap.Marker(pt, {
				icon : myIcon
			}); // 创建标注
			map.addOverlay(marker); // 将标注添加到地图中
			marker.disableMassClear();//mark不被清除
			var label = new BMap.Label("仓库" + cangk[i].id, {
				offset : new BMap.Size(20, -30)
			});
			label.setStyle({
				fontSize : "20px"
			});
			marker.setLabel(label);
			var removeMarker = function(e, ee, marker) {
				var marketpoint = marker.getPosition();
				var n, m, x;
				for (n = 0; n < cangk.length; n++)
					if (cangk[n].lng == marketpoint.lng
							&& cangk[n].lat == marketpoint.lat) {
						x = cangk[n].id;
						for (m = n; m < cangk.length - 1; m++) {
							cangk[m] = cangk[m + 1];
							cangk[m].id = m + 1;
						}
					}
				cangk.length--;
				map.removeOverlay(marker);
				var List = map.getOverlays();
				for (var i = 0; i < cangk.length+pointArray.length; i++) {
					if(List[i].getLabel().content =="仓库"+(x+1)){
					List[i].getLabel().setContent("仓库" + x);
					x++;
					}
				}
			}
			//创建右键菜单
			var markerMenu = new BMap.ContextMenu();
			markerMenu.addItem(new BMap.MenuItem('删除', removeMarker
					.bind(marker)));
			marker.addContextMenu(markerMenu);		
		}
		layer.closeAll();
	}
	</script>
	<script>
	//自动生成仓库-a.绘制方案
	function calculate2() {
		/*map.removeOverlay(polyline);*/
		var i;
		for (i = 0; i < cluster.length; i++) {
			Cpoint = eval(cluster[i]);
			for (var j = 0; j < Cpoint.length; j++)
				Cpoint[j].id = j + 1;
			$.ajax({
				async : false,
				type : "POST",
				url : "getdata",
				dataType : "json",
				contentType : "application/json;charset=UTF-8",
				data : JSON.stringify(Cpoint),
				crossDomain : true,
				success : function(data) {
					sort = eval(data);
					Cdraw();
					discridraw=3;
					if (i == cluster.length - 1)
						layer.closeAll();
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(XMLHttpRequest.status);
					alert(XMLHttpRequest.readyState);
					alert(textStatus);
				}
			});
		}
	}
	//自动生成仓库-b.绘图
	function Cdraw() {
		var sortArray = new Array();//坐标数组
		for (var i = 0; i < Cpoint.length; i++) {
			sortArray.length++;
			for (var j = 0; j < Cpoint.length; j++) {
				if (sort[i] == Cpoint[j].id) {
					sortArray[sortArray.length - 1] = new BMap.Point(
							Cpoint[j].lng, Cpoint[j].lat);
				}
			}
		}
		var sy = new BMap.Symbol(BMap_Symbol_SHAPE_BACKWARD_OPEN_ARROW, {
			scale : 0.6,//图标缩放大小
			strokeColor : '#fff',//设置矢量图标的线填充颜色
			strokeWeight : '2',//设置线宽
		});
		var icons = new BMap.IconSequence(sy, '10', '30');
		// 创建polyline对象
		sortArray[sortArray.length] = new BMap.Point(sortArray[0].lng,
				sortArray[0].lat);
		polyline = new BMap.Polyline(sortArray, {
			enableEditing : false,//是否启用线编辑，默认为false
			enableClicking : true,//是否响应点击事件，默认为true
			icons : [ icons ],
			strokeWeight : '8',//折线的宽度，以像素为单位
			strokeOpacity : 0.8,//折线的透明度，取值范围0 - 1
			strokeColor : '#'+('00000'+ (Math.random()*0x1000000<<0).toString(16)).substr(-6) //折线颜色
		});
		map.addOverlay(polyline); //增加折线
		FormLabel(Cpoint);
		discriwarehouse=2;
	/*	map.setViewport(pointArray);*/
	}
	</script>
	<script>
	//保存
	function save() {
		savePlan();
		savePoint();
		saveWarehouse();
	}
	//保存方案
	function savePlan() {
		Plan.planName = PlanName;
		var s = ${USER.userLoginname};
		Plan.userName = s;
		Plan.point = pointArray.length;
		Plan.warehouse = cangk.length;
		Plan.flage = discridraw;
		Plan.distance=Tdistance;
		$.ajax({
			async : false,
			type : "POST",
			url : "PlanController/savePlan",
			dataType : "json",
			contentType : "application/json;charset=UTF-8",
			data : JSON.stringify(Plan),
			crossDomain : true,
			success : function(data) {
				var checkplan=eval(data);
				if(pointArray.length==0){
				alert("success");
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(XMLHttpRequest.status);
				alert(XMLHttpRequest.readyState);
				alert(textStatus);
			}
		});
	}
	//保存标记
	</script>
	<script>
	function savePoint() {
	if(discridraw==0||discridraw==1){
		$.ajax({
			async : false,
			type : "POST",
			url : "PointController/savePoint",
			dataType : "json",
			contentType : "application/json;charset=UTF-8",
			data : JSON.stringify(pointArray),
			crossDomain : true,
			success : function(data) {
				var checkplan=eval(data);
				if(checkplan.flage!=3){
				alert("success");
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(XMLHttpRequest.status);
				alert(XMLHttpRequest.readyState);
				alert(textStatus);
			}
		});
		}
	if(discridraw==3)
	{
		for (i = 0; i < cluster.length; i++) {
			Cpoint = eval(cluster[i]);
			for(var j=0;j<Cpoint.length-1;j++)
			{
				for(var ii=0;ii<pointArray.length;ii++)
				{
					if(Cpoint[j].lat==pointArray[ii].lat&&Cpoint[j].lng==pointArray[ii].lng)
						pointArray[ii].flage=i+1;
				}
			}
			
		}
		$.ajax({
			async : false,
			type : "POST",
			url : "PointController/savePoint",
			dataType : "json",
			contentType : "application/json;charset=UTF-8",
			data : JSON.stringify(pointArray),
			crossDomain : true,
			success : function(data) {
				var checkplan=eval(data);
				if(checkplan.flage==3){
				alert("success");
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(XMLHttpRequest.status);
				alert(XMLHttpRequest.readyState);
				alert(textStatus);
			}
		});
	}
	}
	</script>
	<script>
	//保存仓库
	function saveWarehouse() {
		if (discriwarehouse != 0) {
			for(var i=0;i<cangk.length;i++)
				cangk[i].id=i+1;
			$.ajax({
				async : false,
				type : "POST",
				url : "WarehouseController/saveWarehouse",
				dataType : "json",
				contentType : "application/json;charset=UTF-8",
				data : JSON.stringify(cangk),
				crossDomain : true,
				success : function(data) {
					alert("success");
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(XMLHttpRequest.status);
					alert(XMLHttpRequest.readyState);
					alert(textStatus);
				}
			});
		}
	}
	//计算环长
	function distance(array) {
		var mile = 0;
		var i;
		for (i = 0; i < array.length - 1; i++) {
		   mile+=map.getDistance(new BMap.Point(array[i].lng, array[i].lat),new BMap.Point(array[i + 1].lng,
					array[i + 1].lat))/1000;
		}
		mile=mile.toFixed(4);
		return mile;
	}
	</script>
	<script>
	function FormLabel(array)
	{
		$.ajax({
			async : false,
			type : "POST",
			url : "center",
			dataType : "json",
			contentType : "application/json;charset=UTF-8",
			data : JSON.stringify(array),
			crossDomain : true,
			success : function(data) {
				centerlabel = eval(data)
				var point = new BMap.Point(centerlabel[0].lng,
						centerlabel[0].lat);
				var opts = {
					position : point, // 指定文本标注所在的地理位置
					offset : new BMap.Size(-30, 0)
				//设置文本偏移量
				}
				var miles = distance(array);
				miles=parseFloat(miles);
				Tdistance +=miles;
				var label = new BMap.Label("总距离为：" + miles + "公里", opts); // 创建文本标注对象
				label.setStyle({
					color : "red",
					fontSize : "20px",
					height : "25px",
					lineHeight : "25px",
					fontFamily : "微软雅黑"
				});
				map.addOverlay(label);
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(XMLHttpRequest.status);
				alert(XMLHttpRequest.readyState);
				alert(textStatus);
			}
		});
	}
	</script>
	<script>
	function yingcang()
	{
		document.getElementById("allmap").style.display="none";//隐藏
		document.getElementById("Plantable").style.display="block";//显示
		layui.use('table', function() {
			var table = layui.table;
			
			table.render({
				elem : '#demo',
				url : 'PlanController/getdata' //数据接口
				,
				height : 600,
				contentType : 'application/json',
				cols : [ [ //表头
				{
					field : 'planName',
					title : '方案名',
					align : 'center',
					width : 180
				}, {
					field : 'point',
					title : '标记数',
					align : 'center',
					width : 80
				}, {
					field : 'warehouse',
					title : '仓库数',
					align : 'center',
					width : 80
				}, {
					field : 'flage',
					title : '是否已生成方案',
					align : 'center',
					width : 130
				},  {
					field : 'distance',
					title : '总距离',
					align : 'center',
					width : 180
				},{
					field : 'creattime',
					title : '保存时间',
					align : 'center',
					width : 180
				}, {
					field : 'right',
					title : '操作',
					align : 'center',
					width : 250,
					toolbar : "#barDemo"
				} ] ]
			});
			table.on('tool(test)', function(obj){
			    var data = obj.data;
			    var Plandetail=new Object();
			    Plandetail.userLoginname=${USER.userLoginname};
			    Plandetail.planName=data.planName;
			    PlanName=data.planName;
			    if(obj.event === 'detail'){
			    	layer.msg('正在生成方案，请稍后...', {
						icon : 16,
						shade : 0.01,
						time : 100000
					});
			      $.ajax({
						async : false,
						type : "POST",
						url : "PlanController/detail",
						dataType : "json",
						contentType : "application/json;charset=UTF-8",
						data : JSON.stringify(Plandetail),
						crossDomain : true,
						success : function(data) {
							var list=eval(data);
							plan=list[0];
							pointArraydetail=eval(list[1]);
							cangk=eval(list[2]);
							start();
							for(var i=0;i<pointArraydetail.length;i++)
							{
								for(var j=0;j<pointArraydetail.length;j++)
									if(pointArraydetail[j].id==i+1)
										pointArray[i]=pointArraydetail[j];
							}
							ReadPoint();
							if(plan.flage==1)
								{
								calculate1(pointArray);
								discriwarehouse=0;
								}
							if(plan.flage>1)
								{
								ReadWarehouse();
								}
							if(plan.flage==3)
								{
								/*map.removeOverlay(polyline);*/
								var list=ReadDraw();
								for(var i=0;i<list.length;i++)
									calculate1(list[i]);
								discriwarehouse=2;
								}
							map.setViewport(pointArray);
							layer.closeAll();
								
						},
						error : function(XMLHttpRequest, textStatus, errorThrown) {
							alert(XMLHttpRequest.status);
							alert(XMLHttpRequest.readyState);
							alert(textStatus);
						}
					});
			    } else if(obj.event === 'del'){
			      layer.confirm('确定删除此方案吗？', function(index){
						$.ajax({
							async : false,
							type : "POST",
							url : "PlanController/delete",
							dataType : "json",
							contentType : "application/json;charset=UTF-8",
							data : JSON.stringify(Plandetail),
							crossDomain : true,
							success : function(data) {
						        obj.del();
						        layer.close(index);
							},
							error : function(XMLHttpRequest, textStatus, errorThrown) {
								alert(XMLHttpRequest.status);
								alert(XMLHttpRequest.readyState);
								alert(textStatus);
							}
						});

			      });
			    } /*else if(obj.event === 'edit'){
			      layer.alert('编辑行：<br>'+ JSON.stringify(data))
			    }*/
			  });
		});		
	}
	</script>
	<script>
	function ReadPoint()
	{
		for(var i=0;i<pointArray.length;i++)
		{
			var point = new BMap.Point(pointArray[i].lng, pointArray[i].lat);//添加点坐标
			var marker = new BMap.Marker(point); //创建标注
			map.addOverlay(marker); // 将标注添加到地图中    
			marker.disableMassClear();//mark不被清除
			var label = new BMap.Label(pointArray[i].id, {
				offset : new BMap.Size(20, -10)
			});
			label.setStyle({
				fontSize : "20px"
			});
			marker.setLabel(label);
			var removeMarker = function(e, ee, marker) {
				var marketpoint = marker.getPosition();
				var n, m, x;
				for (n = 0; n < pointArray.length; n++)
					if (pointArray[n].lng == marketpoint.lng
							&& pointArray[n].lat == marketpoint.lat) {
						x = pointArray[n].id;
						for (m = n; m < pointArray.length - 1; m++) {
							pointArray[m] = pointArray[m + 1];
							pointArray[m].id = m + 1;
						}
					}
				pointArray.length--;
				map.removeOverlay(marker);
				var List = map.getOverlays();
				for (var i = 0; i < pointArray.length+cangk.length; i++) {
					if(List[i].getLabel().content ==x+1){
					List[i].getLabel().setContent(x);
					x++;
					}
				}
			}
			//创建右键菜单
			var markerMenu = new BMap.ContextMenu();
			markerMenu.addItem(new BMap.MenuItem('删除', removeMarker
					.bind(marker)));
			marker.addContextMenu(markerMenu);
		}
	}
	</script>
	<script>
	function ReadWarehouse()
	{
		var myIcon = new BMap.Icon("img/cangku.jpg", new BMap.Size(300, 157), {
			anchor : new BMap.Size(25, 25)
		});
		for (var i = 0; i < cangk.length; i++) {
			var pt = new BMap.Point(cangk[i].lng, cangk[i].lat);
			var marker = new BMap.Marker(pt, {
				icon : myIcon
			}); // 创建标注
			map.addOverlay(marker); // 将标注添加到地图中
			marker.disableMassClear();//mark不被清除
			var label = new BMap.Label("仓库" + cangk[i].id, {
				offset : new BMap.Size(20, -30)
			});
			label.setStyle({
				fontSize : "20px"
			});
			marker.setLabel(label);
			var removeMarker = function(e, ee, marker) {
				var marketpoint = marker.getPosition();
				var n, m, x;
				for (n = 0; n < cangk.length; n++)
					if (cangk[n].lng == marketpoint.lng
							&& cangk[n].lat == marketpoint.lat) {
						x = cangk[n].id;
						for (m = n; m < cangk.length - 1; m++) {
							cangk[m] = cangk[m + 1];
							cangk[m].id = m + 1;
						}
					}
				cangk.length--;
				map.removeOverlay(marker);
				var List = map.getOverlays();
				for (var i = 0; i < cangk.length+pointArray.length; i++) {
					if(List[i].getLabel().content =="仓库"+(x+1)){
					List[i].getLabel().setContent("仓库" + x);
					x++;
					}
				}
			}
			//创建右键菜单
			var markerMenu = new BMap.ContextMenu();
			markerMenu.addItem(new BMap.MenuItem('删除', removeMarker
					.bind(marker)));
			marker.addContextMenu(markerMenu);
		}
	}
	</script>
	<script>
	function ReadDraw()
	{
		var list=new Array();
		for(var i=0;i<cangk.length;i++){
			var slist=new Array();
			var ii=0;
			for(var j=0;j<pointArray.length;j++)
				if(pointArray[j].flage==i+1){
					var point=new Object();
					point.id=ii+1;
					point.lng=pointArray[j].lng;
					point.lat=pointArray[j].lat;
					point.flage=pointArray[j].flage;
					slist[ii]=point;
					ii++;
				}
			var point=new Object();
			point.id=ii+1;
			point.lng=cangk[i].lng;
			point.lat=cangk[i].lat;
			point.flage=cangk[i].id;
			slist[ii]=point;
			ii++;
			list[i]=slist;
		}
		return list;
	}
</script>