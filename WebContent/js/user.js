/**
 * 
 */
function login(){
	var userName = $("#name").val();
	var password = $("#password").val();
	$.ajax({
		type : "POST",
		url : "/bookSystem/servlet/BookServlet",
		dataType : 'text',
		data:{'method':'login','userName':userName,'password':password},
		  success : function(data) {
      			if("no"==data){
      				alert("登陆失败");
      				window.location = "Customer.html";

      			}
      			
      			else{
      				window.location = "Customer.html";

      			}
      		},
      		error : function(error) {
      			alert("登陆失败 ");
      			window.location = "Customer.html";

      		}	});
	


}
function newUser(){
	var userTel = $("#userTel").val();
	var userName = $("#userName").val();
	var password = $("#password").val();
	var ensurePass = $("#ensurePass").val();
	if(password != ensurePass){
		alert("前后输入的密码不同，请再次输入！");
		
	}
	else{
		$.ajax({
			type : "POST",
			url : "/bookSystem/servlet/BookServlet",
			dataType : 'text',
			data:{'method':'newUser','userTel':userTel,'userName':userName,'password':password},
			  success : function(data) {
	      			if("no"==data){
	      				alert("保存失败");
	      				window.location = "Customer.html";

	      			}
	      			
	      			else{
	      				alert("保存成功");
	      				window.location = "Customer.html";

	      			}
	      		},
	      		error : function(error) {
	      			alert("保存失败");
	      			window.location = "Customer.html";

	      		}	});
		
	}
}
