<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <link rel="stylesheet" href="./css/login.css">
    <script type="text/javascript" src="./js/jquery/jquery-1.8.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/vue"></script>
    <script type="text/javascript" src="http://cdn.mingsoft.net/plugins/validator/5.5.0/validator.min.js"></script>
    <script type="text/javascript" src="http://cdn.mingsoft.net/plugins/less/2.5.3/less.min.js" ></script>
</head>
<body>
<div class="login">
    <div class="login_title">
        <p>YMCS服务平台</p>
    </div>
    <div class="login_main" id="login_main">
        <div class="main_left"></div>
        <div class="main_right">
            <div class="right_title">用户登录</div>
            <div class="ms-login-error-text">
					<img src="http://www.hycmkj.com/static/skin/manager/4.6.4/images/error.png" v-show="errorText != ''" />
					<span v-text="errorText" v-show="errorText != ''"></span>
			</div>
            <form name="user-form" id="user-form" action="${ctx }/login/check" method="post" onsubmit="return checkForm()">
                <div class="username">
                    <img src="./images/username.png" alt="">
                    <input type="text" name="username" id="username" placeholder="请输入用户名" @blur="checkPeopleName" v-model="peopleName">
                </div>
                <div class="password">
                    <img src="./images/password.png" alt="">
                    <input type="password" name="password" id="password" placeholder="请输入密码" @blur="checkPeoplePassword" v-model="peoplePassword">
                </div>
                <div class="code">
                    <img src="./images/code.png" alt="">
                    <input type="text" name="verify" id="verify" placeholder="请输入验证码" @blur="checkCode" v-model="code" >
                    <div class="code_img">
                        <img id="ys-login-code" src="${ctx }/login/code" alt="" @click="changeCode" >
                    </div>
                </div>
                <div class="yes_login" v-on:click="toLogin"><a href="#">登&nbsp;&nbsp;&nbsp;&nbsp;录</a></div>
            </form>
        </div>
    </div>
    <div class="login_footer">
        <!--<p class="name">北京XXXX信息科技有限公司&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;京ICP：京B2-20170028</p>-->
        <!--<p>联系电话：18588888888&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;010-2888888</p>-->
        <p>建议浏览器：IE9以上、Firefox v22</p>
    </div>
</div>

</body>
<script>
	    
        var loginForm = new Vue({
            el:'#login_main',
            data:{
                isRight: true,
                errorText:"",//错误提示
                error:"",//输入框错误的显示
                peopleName:"",//用户名输入框
                peoplePassword:"",//密码输入框
				code:"",//验证码
            },
            watch: {
				peopleName: function() {
                    var pattern = /[^\w\u4E00-\u9FA5]/ig;
                    if(!validator.isNull(this.peopleName) && this.peopleName.indexOf(" ") < 0 && validator.isLength(this.peopleName, {min:6,max:20}) && pattern.test(this.peopleName) == false && this.error == 'peopleName'){
                        this.errorText = "";
                        this.error = "";
					}
				},
                peoplePassword: function() {
                    if(!validator.isNull(this.peoplePassword) && this.peoplePassword.indexOf(" ") < 0 && validator.isLength(this.peoplePassword, {min:6,max:20}) && this.error == 'peoplePassword'){
                        this.errorText = "";
                        this.error = "";
					}
				},
				code: function(){
					if(!validator.isNull(this.code) && this.code.length == 4 && this.error == 'rand_code'){
						this.errorText = "";
                        this.error = "";
					}
				}
			},
			mounted: function() {
			      var _this = this;
		          //页面初始化时，如果帐号密码cookie存在则填充
                  if($.cookie('managerName') && $.cookie('managerPassword')){
                      _this.peopleName = $.cookie('managerName');
                      _this.peoplePassword = $.cookie('managerPassword');
                      $("#remember").attr("checked",true);
                  }
			      //检测浏览器版本
			      if (navigator.userAgent.toLowerCase().indexOf("msie") > 0) {
			      	    alert("您当前的浏览器版本太低，建议使用IE8以上版本浏览器，推荐使用Chrome浏览器");
			      }
			      
			      //js监听回车键 
			      document.onkeydown = function(e) {
			      	e = e ? e : window.event;
			      	var keyCode = e.which ? e.which : e.keyCode;
			      	if (keyCode == 13) {
			      		_this.login(); 
			      	}
			      }
			},
            methods:{
                //错误提示显示
                errorShow:function(msg,type){
                    this.errorText = msg;
                    this.error = type;
                },
                changeCode:function(){
                	$("#ys-login-code").attr("src","${ctx }/login/code?t="+new Date().getTime());
                },
                //判断用户名
                checkPeopleName:function(){
                    var pattern = /[^\w\u4E00-\u9FA5]/ig;
					if(validator.isNull(this.peopleName)){
						this.errorShow("用户名不能为空",'peopleName');
						this.isRight = false;
						return;
					}else if(this.peopleName.indexOf(" ") >= 0) {
						this.errorShow("用户名不能包含空格",'peopleName');
						this.isRight = false;
						return;
					}else if(!validator.isLength(this.peopleName, {min:3,max:12})){
						this.errorShow("用户名为3~12个字符",'peopleName');
						this.isRight = false;
						return;
					}else if(pattern.test(this.peopleName)){
                        this.errorShow("用户名不能包含特殊字符",'peopleName');
                        this.isRight = false;
						return;
                    }
                },
                //判断密码
                checkPeoplePassword:function(){
                    if(validator.isNull(this.peoplePassword)){
						this.errorShow("密码不能为空",'peoplePassword');
						this.isRight = false;
						return;
					}else if(!validator.isLength(this.peoplePassword, {min: 6,max: 20})){
						this.errorShow("密码长度在6~20位之间!",'peoplePassword');
						this.isRight = false;
						return;
					}else if(this.peoplePassword.indexOf(" ") >=0){
                        this.errorShow("密码是不能包含空格",'peoplePassword');
                        this.isRight = false;
						return;
					}
                },
				//判断验证码
                checkCode:function(){
                    if(validator.isNull(this.code)){
						this.errorShow("验证码不能为空",'rand_code');
						this.isRight = false;
						return;
					}else if(this.code.length != 4){
						this.errorShow("验证码为4位!",'rand_code');
						this.isRight = false;
						return;
					}
                },
                //登录判断验证
                checkLogin:function(){
                    this.checkPeoplePassword();
                    this.checkPeopleName();
                    this.checkCode();
                },
                //验证登录数据
                login:function(){
                    loginForm.checkLogin(); 
		            if(this.isRight){
		            	$.ajax({
		        			type : "POST",
		        			url : $("#user-form").attr("action"),
		        			dataType : "json",
		        			data : $("#user-form").serialize(),
		        			beforeSend : function() {
		        				$("#user-form").attr("disabled", true);
		        			},
		        			success : function(data) {
		        				if (data.code) {
		        					location.href="${ctx }/index";
		        				} else{
		        					
		        					alert(data.msg);
		        					$("#ys-login-code").attr("src","${ctx }/login/code?t="+new Date().getTime());
		        				}
		        				$("#user-form").removeAttr("disabled");
		        			}
		        		});
				       /*  $(this).postForm("#user-form",{loadingText:"正在登录中..",func:function(data) {
    				        if(data.result){
    					    location.href=base+"/ms/index.do";
    				            }else{
    				        alert(data.resultMsg);
    				        $("#ms-login-code").attr("src","http://www.hycmkj.com/code?t="+new Date().getTime())
    				        $("#login-button").html("登录");
    				        $("#login-button").attr("style","pointer-events: auto;");
    				        $("#login-button").css({"background-color":"#0099ff"});
				        };
				        }}); */
				     }else{
				         this.isRight=true;
				        
				          }
                     },
                   //删除cookies
                delCookies: function(names){
                     for(i=0;i<names.length;i++){
                     $.cookie(names[i],null,{ expires: -1 });
                     }
                  },
                  //删除并且修改cookies
                delAndSetCookies: function(names,values,date){
                     this.delCookies(names);
                     for(i=0;i<values.length;i++){
                        $.cookie(names[i], values[i], { expires: date });
                     }
                  },
                 
                  //点击登录方法
                toLogin: function(){
                     var names=new Array('username','password');
			         var values=new Array($("#username").val(),$("#password").val());
			         
                         this.login();
                     }
                 
                       
            }
            
			
        })
		
</script>
</html>