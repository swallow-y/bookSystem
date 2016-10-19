/**
 * 
 */
//加载当前的书
function loadBook(){

		var id = GetQueryString("id");
	
		$.ajax({
			
			type : "POST",
			url : "/bookSystem/servlet/BookServlet",
			dataType : 'json',
			data:{'method':'findThisBook','id':id},
			success : function(data) {
				var thisBook = eval(data);
				var imageShow = $("#imageShow");
				var image = "<div>";
				image += '<img src="/bookSystem/servlet/ShowImgServlet?imgSrc='+img+'" width= 365px height= 238px>';
				image +="</div>";
				image.innerHTML = image;
				var img = thisBook[0].imagePath;
			    $("#imageShow").append('<img src="/bookSystem/servlet/ShowImgServlet?imgSrc='+img+'" width= 365px height= 238px>');
				var messageShow = $("#messageShow");
				var book ="<div>";
				book +="<p>书名："+thisBook[0].bookName+"</p>"
				     +"<p>编号: "+thisBook[0].id+"</p>"
				     +"<span>价格: </span><span style='font-size:23px; color: red;'>￥"+thisBook[0].price+"</span>"
				     +"<p >库存: <span id ='bookSize'> "+thisBook[0].bookSize+"</span></p>"
				     +"<p>描述: "+thisBook[0].descript+"</p>"
				     +"<span>数量： <input type='button' value='-' onclick='downNumber()' style='width:23px;height: 26px;'><input type='text' value='1' id='buyNumber' style='width:39px; height: 20px; text-align:center'><input type='button' value='+' onclick='upNumber()' style='width:23px;height: 26px;'></span><br/>"
				     +"<input type='button' value='立即购买' onclick='buy("+thisBook[0].bookName+","+thisBook[0].id+","+thisBook[0].price+")' style='width: 120px; height: 37px; font-size: 20px; border-top-left-radius: 5px;border-top-right-radius: 5px;border-bottom-left-radius: 5px;border-bottom-right-radius: 5px;'>"
				     +"<input type='button' value='加入购物车' onclick='addToShoppingCart("+thisBook[0].id+")' style='width: 120px; height: 37px; font-size: 20px; border-top-left-radius: 5px;border-top-right-radius: 5px;border-bottom-left-radius: 5px;border-bottom-right-radius: 5px;'>"

				     ;
				book += "</div>";
				messageShow.append(book);
				
				
			
			},
			error : function(error) {
				alert("显示失败");
			}

		});
		
	}

function downNumber(){
	var buyNumber = $("#buyNumber").val();
	if(buyNumber == '1'){
		
	}
	else{
		buyNumber --;
		$("#buyNumber").val(buyNumber);
		
	}
}
function upNumber(){
	var buyNumber = $("#buyNumber").val();
	var bookSize = $("#bookSize").text();
	if(buyNumber == parseInt(bookSize)){
		
	}
	else{
		buyNumber ++;
		$("#buyNumber").val(buyNumber);
		
	}
}
function buy(bookName,id,price){
	var bookSize = parseInt($("#bookSize").text());
	var bookName = bookName;
	var id = id;
	var price = price;
	var buyNumber = $("#buyNumber").val();
	$.ajax({
		
		type : "POST",
		url : "/bookSystem/servlet/BookServlet",
		dataType : 'json',
		data:{'method':'BuyBook','bookId':id,'buyNumber':buyNumber,'bookSize':bookSize,'price':price,'bookName':bookName},
		success : function(data) {
			if(data == false){
				alert("购买前，请先登录！");
			}
			else{
				alert("购买成功");
			}
			
		},
		error : function(error) {
			alert("购买失败");
		}

	});
}

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