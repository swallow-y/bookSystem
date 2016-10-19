/**
 * 
 */
//显示所有的图书信息
function showOrderList() {
	$.ajax({
				type : "POST",
				url : "/bookSystem/servlet/BookServlet",
				async : false,
				dataType : 'json',
				data:{'method':'showOrderList'},
				success : function(data) {
					var myOrder = eval(data);

					var orderId = myOrder[0].orderId;
					
                        var result = "<tr><td></td></tr>";
					for (var i =0; i < myOrder.length; i++) {
						
						if(orderId == myOrder[i].orderId ){
							
						}
						else{
							orderId = myOrder[i].orderId;
							 b = new Date(myOrder[i-1].time);
							 var year = b.getFullYear();
							 var month = b.getMonth()+1;
							 var day = b.getDate();
							result +="<tr style='font-size:12px;color:red;font-weight:bold'><td>"+year+"-"+month+"-"+day+"</td><td>"+myOrder[i-1].orderId+"</td><td>总价："+myOrder[i-1].allprice+"</td></tr>";
						}
						 var b = new Date(myOrder[i].time);
						 var year = b.getFullYear();
						 var month = b.getMonth()+1;
						 var day = b.getDate();
						result += "<tr style='font-size:10px'><td>"
								+ year+"-"+month+"-"+day
								+ "</td><td>"
								+ myOrder[i].orderId
								+ "</td><td>"
								+ myOrder[i].bookName
								+ "</td><td>"
								+  myOrder[i].bookId
								+ "</td><td>"
								+myOrder[i].bookNumber
								+ "</td><td>"
								+ myOrder[i].price
								+ "</td><td>"
								+"<a href='BuyBook.html?id="+myOrder[i].bookId+"'  ><input type='button' value='查看详情'></a>"
								+ "</td></tr>";

					}
					var length = myOrder.length-1;
				    b = new Date(myOrder[length].time);
				    var year = b.getFullYear();
					 var month = b.getMonth()+1;
					 var day = b.getDate();
					result +="<tr style='font-size:12px;color:red;font-weight:bold'><td>"+year+"-"+month+"-"+day+"</td><td>"+myOrder[length].orderId+"</td><td>总价："+myOrder[length].allprice+"</td></tr>";
					$("#table").append(result);
				},
				error : function(error) {
					alert("显示失败");
				}
			});

}
function showCartList() {
	$.ajax({
				type : "POST",
				url : "/bookSystem/servlet/BookServlet",
				async : false,
				dataType : 'json',
				data:{'method':'showCartList'},
				success : function(data) {
					var myCart = data;
					cartLength = myCart.length;
                       var result = "<tr><td></td></tr>";
					for (var i =0; i < myCart.length; i++) {
						
						
						result += "<tr style='font-size:10px'><td id='bookName"+i+"'><a href='BuyBook.html?id="+myCart[i].bookId+"'  >"
								+ myCart[i].bookName
								+ "</a></td><td id='price"+i+"'>"
								+ myCart[i].price
								+ "</td><td id='bookId"+i+"'>"
								+ myCart[i].bookId
								+ "</td><td id='bookSize"+i+"'>"
								+ myCart[i].bookSize
								+ "</td><td>"
								+ "<input type='button' value='-' onclick='downNumber("+i+")' style='width:23px;height: 26px;'><input type='text' value='1' id='buyNumber"+i+"' style='width:39px; height: 20px; text-align:center'><input type='button' value='+' onclick='upNumber("+i+")' style='width:23px;height: 26px;'>"
								+ "</td>"
								+ "<td>"
								+"<div style=' position: relative;display: inline-block;width: 15px;height: 15px;overflow: hidden '><input style='position: absolute;z-index: 0;left: -2px;top: 0px;'  type='checkbox' name='chekBox' value="+i+"></div>"
								+ "</td></tr>";
					}
					result+="<tr><td><input type = 'button' name = '立即购买' value='立即购买' onclick='buyFromCart()'></td></tr>";
					
					$("#table").append(result);
					
				},
				error : function(error) {
					alert("显示失败");
				}
			});

}

function downNumber(i){
	var buyNumber = $("#buyNumber"+i+"").val();
	if(buyNumber == '1'){
		
	}
	else{
		buyNumber --;
		$("#buyNumber"+i+"").val(buyNumber);
		
	}
}
function upNumber(i){
	var buyNumber = $("#buyNumber"+i+"").val();
	var bookSize = $("#bookSize"+i+"").text();
	if(buyNumber == parseInt(bookSize)){
		
	}
	else{
		buyNumber ++;
		$("#buyNumber"+i+"").val(buyNumber);
		
	}
}

function buyFromCart(){
	var i;
    var buyNumber;
    var bookId;
    var price;
    var bookName;
    var bookSize;
	var list=[];//大的集合

	$("[name='chekBox']:checked").each(function(){
//		alert($(this).val());//循环得到name为chk的值，至于是不是checkbox也无所谓。
		
	    i = $(this).val();
	    buyNumber = $("#buyNumber"+i+"").val();
	    bookId = $("#bookId"+i+"").text();
	    price = $("#price"+i+"").text();
	    bookName = $("#bookName"+i+"").text();
	    bookSize = $("#bookSize"+i+"").text();
	    var obj = new Object();  //集合对象
		obj.bookId=bookId;  
		obj.buyNumber = buyNumber;
		obj.price = price;
		obj.bookName = bookName;
		obj.bookSize = bookSize;
		
		list.push(obj );   //对象放入集合
	   
		
		});
	 $.ajax({
			
			type : "POST",
			url : "/bookSystem/servlet/BookServlet",
			dataType : 'json',
			data:{'method':'BuyAllSelect','list':JSON.stringify(list)},
			success : function(data) {
				if(data == false){
					alert(data);
				}
				else{
					alert(data);
				}
				
			},
			error : function(error) {
				alert("购买失败");
			}

		});
}
