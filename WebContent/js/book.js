/**
 * 
 */
/**
 * Created by ezgoing on 14/9/2014.
 */
'use strict';
var img ;
var page = 1;
var page_size;
var statment=0;
window.onload = function() {
    var options =
    {
        imageBox: '.imageBox',
        thumbBox: '.thumbBox',
        spinner: '.spinner',
        imgSrc: 'avatar.png'
    }
    var cropper;
    document.querySelector('#file').addEventListener('change', function(){
        var reader = new FileReader();
        reader.onload = function(e) {
            options.imgSrc = e.target.result;
            cropper = new cropbox(options);
        }
        reader.readAsDataURL(this.files[0]);
        this.file = [];
    })
    document.querySelector('#btnCrop').addEventListener('click', function(){
       img = cropper.getDataURL()
        document.querySelector('.cropped').innerHTML += '<img src="'+img+'">';
    })
    document.querySelector('#btnZoomIn').addEventListener('click', function(){
        cropper.zoomIn();
    })
    document.querySelector('#btnZoomOut').addEventListener('click', function(){
        cropper.zoomOut();
    })
     document.querySelector('#upload').addEventListener('click', function(){
        //点击请求发送ajax请求 
    	 statment = Number(GetQueryString("statment"));
    	 if(statment != 0){
     		  var method='updateBook';
     		  
     	   }
    	 else{
    		var method='saveBook';
    	 }
    	  $.ajax({
       	   
       	   type : "post",
          	   url : "/bookSystem/servlet/BookServlet",
              data:{"bookImg":img,"method":method,"bookName":$("#bookName").val(),
           	   "bookISN":$("#bookISN").val(),"bookPrice":$("#bookPrice").val(),"author":$("#author").val(),"bookStore":$("#bookStore").val(),
           	   "bookTypeBox":$("#bookTypeBox").val(),"bookDescript":$("#bookDescript").val()},
              type:"post",
              dataType:"text",
              success : function(data) {
      			if("no"==data){
      				alert("保存失败");
      				window.location = "AddBook.html";

      			}
      			
      			else{
      				alert("保存成功");
      				window.location = "AddBook.html";

      			}
      		},
      		error : function(error) {
      			alert("保存失败");
      			window.location = "AddBook.html";

      		}
   });
    })
     
};


var cropbox = function(options){
    var el = document.querySelector(options.imageBox),
    obj =
    {
        state : {},
        ratio : 1,
        options : options,
        imageBox : el,
        thumbBox : el.querySelector(options.thumbBox),
        spinner : el.querySelector(options.spinner),
        image : new Image(),
        getDataURL: function ()
        {
            var width = this.thumbBox.clientWidth,
                height = this.thumbBox.clientHeight,
                canvas = document.createElement("canvas"),
                dim = el.style.backgroundPosition.split(' '),
                size = el.style.backgroundSize.split(' '),
                dx = parseInt(dim[0]) - el.clientWidth/2 + width/2,
                dy = parseInt(dim[1]) - el.clientHeight/2 + height/2,
                dw = parseInt(size[0]),
                dh = parseInt(size[1]),
                sh = parseInt(this.image.height),
                sw = parseInt(this.image.width);

            canvas.width = width;
            canvas.height = height;
            var context = canvas.getContext("2d");
            context.drawImage(this.image, 0, 0, sw, sh, dx, dy, dw, dh);
            var imageData = canvas.toDataURL('image/png');
            return imageData;
        },
        getBlob: function()
        {
            var imageData = this.getDataURL();
            var b64 = imageData.replace('data:image/png;base64,','');
            var binary = atob(b64);
            var array = [];
            for (var i = 0; i < binary.length; i++) {
                array.push(binary.charCodeAt(i));
            }
            return  new Blob([new Uint8Array(array)], {type: 'image/png'});
        },
        zoomIn: function ()
        {
            this.ratio*=1.1;
            setBackground();
        },
        zoomOut: function ()
        {
            this.ratio*=0.9;
            setBackground();
        }
    },
    attachEvent = function(node, event, cb)
    {
        if (node.attachEvent)
            node.attachEvent('on'+event, cb);
        else if (node.addEventListener)
            node.addEventListener(event, cb);
    },
    detachEvent = function(node, event, cb)
    {
        if(node.detachEvent) {
            node.detachEvent('on'+event, cb);
        }
        else if(node.removeEventListener) {
            node.removeEventListener(event, render);
        }
    },
    stopEvent = function (e) {
        if(window.event) e.cancelBubble = true;
        else e.stopImmediatePropagation();
    },
    setBackground = function()
    {
        var w =  parseInt(obj.image.width)*obj.ratio;
        var h =  parseInt(obj.image.height)*obj.ratio;

        var pw = (el.clientWidth - w) / 2;
        var ph = (el.clientHeight - h) / 2;

        el.setAttribute('style',
                'background-image: url(' + obj.image.src + '); ' +
                'background-size: ' + w +'px ' + h + 'px; ' +
                'background-position: ' + pw + 'px ' + ph + 'px; ' +
                'background-repeat: no-repeat');
    },
    imgMouseDown = function(e)
    {
        stopEvent(e);

        obj.state.dragable = true;
        obj.state.mouseX = e.clientX;
        obj.state.mouseY = e.clientY;
    },
    imgMouseMove = function(e)
    {
        stopEvent(e);

        if (obj.state.dragable)
        {
            var x = e.clientX - obj.state.mouseX;
            var y = e.clientY - obj.state.mouseY;

            var bg = el.style.backgroundPosition.split(' ');

            var bgX = x + parseInt(bg[0]);
            var bgY = y + parseInt(bg[1]);

            el.style.backgroundPosition = bgX +'px ' + bgY + 'px';

            obj.state.mouseX = e.clientX;
            obj.state.mouseY = e.clientY;
        }
    },
    imgMouseUp = function(e)
    {
        stopEvent(e);
        obj.state.dragable = false;
    },
    zoomImage = function(e)
    {
        var evt=window.event || e;
        var delta=evt.detail? evt.detail*(-120) : evt.wheelDelta;
        delta > -120 ? obj.ratio*=1.1 : obj.ratio*=0.9;
        setBackground();
    }

    obj.spinner.style.display = 'block';
    obj.image.onload = function() {
        obj.spinner.style.display = 'none';
        setBackground();

        attachEvent(el, 'mousedown', imgMouseDown);
        attachEvent(el, 'mousemove', imgMouseMove);
        attachEvent(document.body, 'mouseup', imgMouseUp);
        var mousewheel = (/Firefox/i.test(navigator.userAgent))? 'DOMMouseScroll' : 'mousewheel';
        attachEvent(el, mousewheel, zoomImage);
    };
    obj.image.src = options.imgSrc;
    attachEvent(el, 'DOMNodeRemoved', function(){detachEvent(document.body, 'DOMNodeRemoved', imgMouseUp)});

    return obj;
};

//显示所有的图书信息
function booksShow() {
	page=page;
	$.ajax({
				type : "POST",
				url : "/bookSystem/servlet/BookServlet",
				async : false,
				dataType : 'json',
				data:{'method':'findBooks','page':page},
				success : function(data) {
					var books = data;

					var list = document.getElementById("list");

					var result = "<table border='1' id='table' position=absolute align=center cellspacing='0px'>";
					result += "<tr><td>&nbsp;书名&nbsp;</td><td>&nbsp;编码&nbsp;</td><td>&nbsp;余量&nbsp;</td><td>价格</td><td>&nbsp;类型&nbsp;</td><td>&nbsp;图片&nbsp;</td><td>&nbsp;操作&nbsp;</td></tr>";
					for (var i =0; i < books.length; i++) {

						result += "<tr><td>"
								+ books[i].bookName
								+ "</td><td>"
								+ books[i].id
								+ "</td><td>"
								+ books[i].bookSize
								+ "</td><td>"
								+  books[i].price
								+ "</td><td>"
								+books[i].type
								+ "</td><td>"
								+ books[i].imagePath
								+ "</td><td>"
								+ "<a href='/bookSystem/html/AddBook.html?id="+books[i].id+"&&statment=-1'>"
								+ "<input type='button'  name='编辑' value='编辑' ></a>"
								+ "<a href='javascript:deleteBook("
								+ "\""
								+ books[i].id
								+ "\")'><input type='button'  name='下架' value='下架' onclick='deleteBook()'></a>"
								+ "</td></tr>";

					}
					result += "</table>";
					list.innerHTML = result;

				},
				error : function(error) {
					alert("显示失败");
				}
			});

}
//按条件查找
function findByCondition() {
	var i;
	for (i = 0; i < check1.length; i++) {
		if (check1.options[i].selected == true)
			break;
	}
	var inputValue = document.getElementById('text').value;
	page = page;
	switch (i) {
	case 0:
		var condition = "id";
		findByAjax(condition,inputValue,page);
		break;
	case 1:
		var condition = "bookName";
		findByAjax(condition,inputValue,page);		
		break;
	case 2:
		var condition = "price";
		findByAjax(condition,inputValue,page);		
		break;
	case 3:
		var condition = "type";
		findByAjax(condition,inputValue,page);		
		break;
	case 4:
		var condition = "bookSize";
		findByAjax(condition,inputValue,page);		
		break;
	}
	
}
//显示需要的信息
function findByAjax(condition,inputValue,page){
	var condition = condition;
	var inputValue = inputValue;
	var page = page;
	$
	.ajax({
		type : "POST",
		url : "/bookSystem/servlet/BookServlet",
		async : false,
		dataType : 'json',
		data:{'method':'findByCondition','condition':condition,'inputValue':inputValue,'page':page},
		success : function(data) {
			var books = data;

			var list = document.getElementById("list");

			var result = "<table border='1' id='table' position=absolute align=center cellspacing='0px'>";
			result += "<tr><td>书名</td><td>编码</td><td>余量</td><td>价格</td><td>类型</td><td>图片</td><td>操作</td></tr>";
			for (var i =0; i < books.length; i++) {

				result += "<tr><td>"
						+ books[i].bookName
						+ "</td><td>"
						+ books[i].id
						+ "</td><td>"
						+ books[i].bookSize
						+ "</td><td>"
						+  books[i].price
						+ "</td><td>"
						+books[i].type
						+ "</td><td>"
						+ books[i].imagePath
						+ "</td><td>"
						+ "<a href='/bookSystem/html/AddBook.html?id="+books[i].id+"&&statment=-1'>"
						+ "<input type='button'  name='编辑' value='编辑' ></a>"
						+ "<a href='javascript:deleteBook("
						+ "\""
						+ books[i].id
						+ "\")'><input type='button'  name='删除' value='删除' onclick='deleteBook()'></a>"
						+ "</td></tr>";

			}
			result += "</table>";
			list.innerHTML = result;

		},
		error : function(error) {
			alert("显示失败");
		}
	});
		
}
 
//下一页
function nextPage() {

	if(page==page_size){
		alert("此页为尾页");
	}
	else{
			++page;
	
	document.getElementById('pageno').value = Math.ceil(page);
	if(GetQueryString(find)=='1'){
		booksShow();
	}
	else{
		newRefer();
	}
	}
}
//上一页
function prePage() {
	
	if(page==1){
		alert("此页为首页");
	}
	else{
		--page;
	document.getElementById('pageno').value = Math.ceil(page);
	if(GetQueryString(find)=='1'){
		booksShow();
	}
	else{
		newRefer();
	}
}
	}
$(function() {
	$.ajax({
		type : "POST",
		url : "/bookSystem/servlet/BookServlet",
		dataType : 'json',
		data:{'method':'findBooksLength'},
		success : function(data) {
			page_size= Math.ceil(data/8);
			

		},
		error : function(error) {
			alert("显示失败");
		}
	});
});
//尾页
function lastPage() {
	
	$.ajax({
		type : "POST",
		url : "/bookSystem/servlet/BookServlet",
		dataType : 'json',
		data:{'method':'findBooksLength'},
		success : function(data) {
			page_size= Math.ceil(data/8);
			page = page_size;
			document.getElementById('pageno').value = page_size;
			if(GetQueryString(find)=='1'){
				booksShow();
			}
			else{
				newRefer();
			}

		},
		error : function(error) {
			alert("显示失败");
		}
	});
	
}
//首页
function firstPage() {
	page = 1;
	document.getElementById('pageno').value = Math.ceil(page);
	if(GetQueryString(find)=='1'){
		booksShow();
	}
	else{
		newRefer();
	}
}
//跳转到某页
function aimPage() {

	page = Math.ceil(document.getElementById('pageno').value);
	document.getElementById('pageno').value = Math.ceil(page);
	if(GetQueryString(find)=='1'){
		booksShow();
	}
	else{
		newRefer();
	}
}
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
				$("#bookName").val(thisBook[0].bookName);
				$("#bookISN").val(thisBook[0].id);
				$("#bookPrice").val(thisBook[0].price);
				$("#bookStore").val(thisBook[0].bookSize);
				$("#bookTypeBox").val(thisBook[0].type);
				$("#bookDescript").val(thisBook[0].descript);
				$("#author").val(thisBook[0].author);

				var img = thisBook[0].imagePath;
						 document.querySelector('.cropped').innerHTML = '<img src="/bookSystem/servlet/ShowImgServlet?imgSrc='+img+'">';
			
			},
			error : function(error) {
				alert("显示失败");
			}

		});
		
	}
//编辑当前的书
function updateBook(){
	document.querySelector('#upload').addEventListener('click', function(){
        //点击请求发送ajax请求 
    	  $.ajax({
       	   
       	   type : "post",
          	   url : "/bookSystem/servlet/BookServlet",
              data:{"bookImg":img,"method":'updateBook',"bookName":$("#bookName").val(),
           	   "bookISN":$("#bookISN").val(),"bookPrice":$("#bookPrice").val(),"author":$("#author").val(),"bookStore":$("#bookStore").val(),
           	   "bookTypeBox":$("#bookTypeBox").val(),"bookDescript":$("#bookDescript").val()},
              type:"post",
              dataType:"text",
              success : function(data) {
      			if("no"==data){
      				alert("保存失败");
      				window.location = "AddBook.html";

      			}
      			
      			else{
      				alert("保存成功");
      				window.location = "AddBook.html";

      			}
      		},
      		error : function(error) {
      			alert("保存失败");
      			window.location = "AddBook.html";

      		}
   });
    })
     

}
// 传参，传递行下标
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;

}
//删除单本书
function deleteBook(id){
	var id = id;
	$.ajax({
		type : "POST",
		url : "/bookSystem/servlet/BookServlet",
		dataType : 'text',
		data : {
			 id : id,
			'method':'deleteBook',
		},
		success : function(data) {
			alert("删除成功");
			window.location="BookList.html";
			
		},
		error : function(error) {
			alert("删除失败");
			window.location="BookList.html";
			
		}
	});
}