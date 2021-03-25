<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
<script src="layer/layer.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>登录</title>
<link rel="stylesheet" href="css/auth.css">
</head>

<body>
	<div class="lowin lowin-blue">
		<div class="lowin-brand">
			<img src="img/kodinger.jpg" alt="logo">
		</div>
		<div class="lowin-wrapper">
			<div class="lowin-box lowin-login">
				<div class="lowin-box-inner">
					<form>
						<p>可视化智能物流配送系统</p>
						 <font color="red">${loginError }</font>
						<div class="lowin-group">
							<label>账号 <a href="#" class="login-back-link">登录</a></label> <input
								type="text" autocomplete="Login" name="Login"
								id="Login" class="lowin-input">
						</div>
						<div class="lowin-group password-group">
							<label>密码 <input type="password" name="Password"
								id="Password" autocomplete="current-password"
								class="lowin-input">
						</div>
						<button class="lowin-btn login-btn" onclick="javascript:login()">登录</button>
						<div class="text-foot">
							尚未注册? <a href="" class="register-link">注册</a>
						</div>
					</form>
				</div>
			</div>

			<div class="lowin-box lowin-register">
				<div class="lowin-box-inner">
					<form>
						<p>开始创建您的账户!</p>
						<div class="lowin-group">
							<label>账号</label> <input type="text" name="UserLogin"
								id="UserLogin" autocomplete="UserLogin" class="lowin-input">
						</div>
						<div class="lowin-group">
							<label>密码</label> <input type="password"
								autocomplete="current-password" name="UserPassword"
								id="UserPassword" class="lowin-input">
						</div>
						<div class="lowin-group">
							<label>姓名</label> <input type="text" name="UserName"
								id="UserName" autocomplete="UserName" class="lowin-input">
						</div>
						<div class="lowin-group">
							<label>性别</label>
							 <div class="layui-input-block">
							 <input type="radio" name="UserSex"
								autocomplete="UserSex" value="男" checked>男&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
								type="radio" name="UserSex" autocomplete="UserSex" value="女">女
						</div>
						<div class="lowin-group">
							<label>电话</label> <input type="text" name="UserTelephone"
								id="UserTelephone" autocomplete="UserTelephone"
								class="lowin-input">
						</div>
						<button class="lowin-btn" onclick="javascript:resign()">注册</button>
						<div class="text-foot">
							已有账号? <a href="" class="login-link">登录</a>
						</div>
					</form>
				</div>
			</div>
		</div>

		<footer class="lowin-footer"> Design By <a href="#">@丶BinZ</a>
		</footer>
	</div>

	<script src="js/auth.js"></script>
	<script>
		Auth.init({
			login_url : '#login',
			forgot_url : '#forgot'
		});
	</script>
</body>
</html>
<script type="text/javascript">
	function User() {
	}
	var x;
	var User = new User();
	function resign() {
		User.UserLogin = document.getElementById("UserLogin").value;
		User.UserPassword = document.getElementById("UserPassword").value;
		User.UserName = document.getElementById("UserName").value;
		var sex = document.getElementsByTagName("input");
		for (var i = 0; i < sex.length; i++) {
			if (sex[i].checked) {
				User.UserSex = sex[i].value;
			}
		}
		User.UserTelephone = document.getElementById("UserTelephone").value;
		$.ajax({
			type : "POST",
			url : "UserController/resign",
			dataType : "json",
			contentType : "application/json;charset=UTF-8",
			data : JSON.stringify(User),
			crossDomain : true,
			success : function(data) {
				x=eval(data);
				if(x==1)
				{
				layer.alert('注册成功!', {icon: 6});
				}
				else
				{
				layer.msg('账号已存在！', {icon: 5});
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(XMLHttpRequest.status);
				alert(XMLHttpRequest.readyState);
				alert(textStatus);
			}
		});
	}
	function login() {
		User.UserLogin = document.getElementById("Login").value;
		User.UserPassword = document.getElementById("Password").value;
		$.ajax({
			type : "POST",
			url : "UserController/login",
			dataType : "json",
			contentType : "application/json;charset=UTF-8",
			data : JSON.stringify(User),
			crossDomain : true,
			success : function(data) {
				x=eval(data);
				if(x==1)
					window.location.href = 'map.jsp';
				else 
					layer.msg('用户名或密码错误！', {icon: 5});
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(XMLHttpRequest.status);
				alert(XMLHttpRequest.readyState);
				alert(textStatus);
			}
		});
	}
</script>