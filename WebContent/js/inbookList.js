/**
 * 
 */
function inBookList(){
	$.ajax({
		type : "POST",
		url : "/bookSystem/servlet/BookServlet",
		async : false,
		dataType : 'json',
		data:{'method':'inBookList'},
		success : function(data) {
			var inBookList = data;
			cartLength = inBookList.length;
			
               var result = "<table><tr><td></td></tr>";
//               result += "<tr class='thfix'><td>时间</td><td>作者</td><td>书名</td><td>编码</td><td>数目</td><td>价格</td></tr>"
			for (var i =0; i < inBookList.length; i++) {
				
				 var b = new Date(inBookList[i].time);
				 var year = b.getFullYear();
				 var month = b.getMonth()+1;
				 var day = b.getDate();
				result += "<tr style='font-size:10px'><td id='bookName"+i+"'><a href='BuyBook.html?id="+inBookList[i].bookId+"'  >"
						+ year+"-"+month+"-"+day
						+ "</a></td><td id='price"+i+"'>"
						+ inBookList[i].author
						+ "</td><td id='bookId"+i+"'>"
						+ inBookList[i].bookName
						+ "</td><td>"
						+ inBookList[i].id
						+ "</td><td>"
						+ inBookList[i].bookSize
						+ "</td><td>"
						+ inBookList[i].price
						+ "</td>"

						+"</tr>";
			}
			result += "</table>"
			$("#list").append(result);
			
		},
		error : function(error) {
			alert("显示失败");
		}
	});

}