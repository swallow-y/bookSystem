/**
 * 
 */
//树形表
$(document).ready(function(){

	$("#firstpane .menu_body:eq(0)").show();
	$("#firstpane h3.menu_head").click(function(){
		$(this).addClass("current").next("div.menu_body").slideToggle(300).siblings("div.menu_body").slideUp("slow");
		$(this).siblings().removeClass("current");
	});
	
	$("#secondpane .menu_body:eq(0)").show();
	$("#secondpane h3.menu_head").mouseover(function(){
		$(this).addClass("current").next("div.menu_body").slideDown(500).siblings("div.menu_body").slideUp("slow");
		$(this).siblings().removeClass("current");
	});
	
});

//存书
function saveBook(){
	$("#iframe").attr("src","AddBook.html?statment=0");
}
//显示用户订单
function OrderList(){
	$("#iframe").attr("src","allOrderList.html");
}
//图书显示
function findBook(){
	$("#iframe").attr("src","BookList.html");


}
//查询图书
function serchIn(){
	var serchIn = $("#SerchIn").val();
	$("#iframe").attr("src","serchBook.html?serch="+serchIn+"");
	
	
}
//显示订单
function myOrder(){
	$("#iframe").attr("src","orderList.html");
}
//显示购物车
function myCart(){
	$("#iframe").attr("src","cartList.html");
}
//查看入库记录
function inBookList(){
	$("#iframe").attr("src","inBookList.html");
}
//创建验证码
function createCode()  
{  
    var seed = new Array(  
            'abcdefghijklmnopqrstuvwxyz',  
            'ABCDEFGHIJKLMNOPQRSTUVWXYZ',  
            '0123456789'  
    );               //创建需要的数据数组  
    var idx,i;  
    var result = '';   //返回的结果变量  
    for (i=0; i<4; i++) //根据指定的长度  
    {  
        idx = Math.floor(Math.random()*3); //获得随机数据的整数部分-获取一个随机整数  
        result += seed[idx].substr(Math.floor(Math.random()*(seed[idx].length)), 1);//根据随机数获取数据中一个值  
    }  
    return result; //返回随机结果  
}  
//验证
function test() {  
    var inputRandom=document.getElementById("inputRandom").value;  
    var autoRandom=document.getElementById("autoRandom").innerHTML;  
    if((inputRandom==autoRandom)&&inputRandom!="") {  
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
    	 
    } else {  
        alert("没有通过验证");
        window.location='login.html'
    }  

}  
//传参，传递行下标
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}
//得到传递的参数
function getParam(key) {
	
	var url = window.location.href.toString();
	var reg = /\?.*/;
	var params = url.match(reg);
	var realParams = params[0].replace(/\?/,'').split('&');
	
	for(index in realParams) {
		var key_value = realParams[index].split('=');
		var tempkey = key_value[0];
		if(tempkey == key) {
			return key_value[1];
		}
	}

	return null;
}

//显示分类图书
function changeHtml(){
	var type = getParam('type');
	$("#iframe").attr("src","bookShow.html?statment=" + type + "");
}




