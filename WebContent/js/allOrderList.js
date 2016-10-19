/**
 * 
 */
function allOrderList(){
	$.ajax({
		type : "POST",
		url : "/bookSystem/servlet/BookServlet",
		async : false,
		dataType : 'json',
		data:{'method':'allOrderList'},
		success : function(data) {
			var allOrder = data;
			cartLength = allOrder.length;
               var result = "<tr><td></td></tr>";
			for (var i =0; i < allOrder.length; i++) {
				
				
				result += "<tr style='font-size:10px'><td>"
						+ allOrder[i].userName
						+ "</td><td>"
						+ allOrder[i].orderId
						+ "</td><td>"
						+ allOrder[i].bookName
						+ "</td><td>"
						+ allOrder[i].bookNumber
						+ "</td><td>"
						+ allOrder[i].price
						+ "</td>"
						+ "<td>"
						+"<a href='BuyBook.html?id="+allOrder[i].bookId+"'  ><input type='button' value='查看详情'></a>"
						+ "</td></tr>";
			}
			
			$("#table").append(result);
			
		},
		error : function(error) {
			alert("显示失败");
		}
	});

}