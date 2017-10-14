layui.use(['jquery','layer','form'],function(){
   'use strict';
	var $ = layui.jquery
	   ,layer=layui.layer
	   ,form =layui.form;
    
    $(window).on('resize',function(){
        var w = $(window).width();
        var h = $(window).height();
        $('.larry-canvas').width(w).height(h);
    }).resize();
    
    //登录链接测试，使用时可删除
    $(".submit_btn").click(function(){
    	if(check()){
     var loginname = document.getElementById("loginname").value;
     var password = document.getElementById("password").value;
	 var code =$("#code").val();
		$.ajax({
			type: "post",
			url: '/system/login',
	    	data: {
	    		"loginname":loginname,
	    		"password":password,
	    		"code":code
	    	},
			dataType:'json',
			cache: false,
			success: function(data){
				if("success" == data.result){
					//saveCookie();
					window.location.href="system/index";
				}else if("loginerror" == data.result){
					$("#loginname").tips({
						side : 1,
						msg : "用户名或密码有误",
						bg : '#FF5080',
						time : 15
					});
					$("#loginname").focus();
				}else if("codeerror" == data.result){
					$("#code").tips({
						side : 1,
						msg : "验证码输入有误",
						bg : '#FF5080',
						time : 15
					});
					$("#code").focus();
				}else{
					$("#loginname").tips({
						side : 1,
						msg : "缺少参数",
						bg : '#FF5080',
						time : 15
					});
					$("#loginname").focus();
				}
			}
		});
    	};
    });
    $(function(){
        $("#canvas").jParticle({
            background: "#141414",
            color: "#E5E5E5"
        });
    });
    
    /**
     * 前端验证
     */
    function check(){
		 if($("#loginname").val() == "") {
				$("#loginname").tips({
					side : 2,
					msg : '用户名不得为空',
					bg : '#AE81FF',
					time : 3
				});
				$("#loginname").focus();
				return false;
			} else {
				$("#loginname").val(jQuery.trim($('#loginname').val()));
			}
			if ($("#password").val() == "") {
				$("#password").tips({
					side : 2,
					msg : '密码不得为空',
					bg : '#AE81FF',
					time : 3
				});
				$("#password").focus();
				return false;
			}
			if ($("#code").val() == "") {
				$("#code").tips({
					side : 1,
					msg : '验证码不得为空',
					bg : '#AE81FF',
					time : 3
				});
				$("#code").focus();
				return false;
			}
			$("#login_box").tips({
				side : 1,
				msg : '正在登录 , 请稍后 ...',
				bg : '#68B500',
				time : 10
			});
			return true;
		}
});