/**
 * 
 */

//把符合要求的书显示出来
$(function showBookList(){
	var type = GetQueryString('statment');
	
	$
	.ajax({
		type : "POST",
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		url : "/bookSystem/servlet/BookServlet",
		async : false,
		dataType : 'json',
		data:{'method':'findByCondition','condition':type,'inputValue':null,'page':null},
		success : function(data) {
			var books = data;

			var list = document.getElementById("bookShow");

			var result = "<div >";
			for (var i =0; i < books.length; i++) {

				result += "<a href='BuyBook.html?id="+books[i].id+"' title='"+books[i].descript+"' >"
				+"<div style='position: relative;float: left;margin-right: 41px;margin-top: 20px;    border-color: gray;border: 1px solid gray;'>"
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
	
	
});

function addToShoppingCart(bookid){
	var id = bookid;
$.ajax({
		
		type : "POST",
		url : "/bookSystem/servlet/BookServlet",
		dataType : 'json',
		data:{'method':'addToShoppingCart','bookId':id},
		success : function(data) {
			if(data == false){
				alert("请先登录！");
			}
			else{
				alert("宝贝在购物车等你哟！");
			}
			
		},
		error : function(error) {
			alert("啊，请重新添加！");
		}

	});
}

//传参，传递行下标
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;

}