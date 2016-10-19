/**
 * 
 */

function serchBook(){
	var serch =getParam("serch") 
	var bookMessage;
	if(serch == null){
		bookMessage = serch;
	}
	else{
	    bookMessage = decodeURI(serch);

	}
	$.ajax({
		type : "POST",
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		url : "/bookSystem/servlet/BookServlet",
		async : false,
		dataType : 'json',
		data:{'method':'serchBook','condition':bookMessage,'inputValue':null,'page':null},
		success : function(data) {
			var books = data;

			var list = document.getElementById("serchBook");

			var result = "<div >";
			for (var i =0; i < books.length; i++) {

				result += "<a href='BuyBook.html?id="+books[i].id+"' title='"+books[i].descript+"' >"
				+"<div style='position: relative;float: left;margin-right: 15px;margin-top: 20px;    border-color: gray;border: 1px solid gray;'>"
                        +"<img  src='/bookSystem/servlet/ShowImgServlet?imgSrc="+books[i].imagePath+"'  ><br/>"
						+ "<span style='text-decoration:none'> 书名："+books[i].bookName+"&nbsp;</span>"
						+ "<span style='text-decoration:none'>库存："+books[i].bookSize+"</span><br/>"
						+ "<span style='text-decoration:none'>价格："+books[i].price+"&nbsp;</span>"
						+ "<span style='text-decoration:none'>类型："+books[i].type+"</span><br/>"
						+ "<input type='button'  name='购买' value='购买' style='background-color:pink;border-top-left-radius:5px;border-top-right-radius:5px;border-bottom-left-radius:5px;border-bottom-right-radius:5px; ' >"
						+"</a>"
						+ "&nbsp;&nbsp;&nbsp;&nbsp;<input type='button'  name='加入购物车' value='加入购物车' onclick='addToShoppingCart("+books[i].id+")' style='background-color:pink;border-top-left-radius:5px;border-top-right-radius:5px;border-bottom-left-radius:5px;border-bottom-right-radius:5px; '>"
						+"</div>";

			}
			result += "</div>";
			list.innerHTML = result;

		},
		error : function(error) {
			alert("显示失败");
		}
	});
}
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

function GetRequest() {
	   var url = location.search; //获取url中"?"符后的字串
	   var theRequest = new Object();
	   if (url.indexOf("?") != -1) {
	      var str = url.substr(1);
	      strs = str.split("&");
	      for(var i = 0; i < strs.length; i ++) {
	         theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
	      }
	   }
	   return theRequest;
	}
//传参，传递行下标
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;

}