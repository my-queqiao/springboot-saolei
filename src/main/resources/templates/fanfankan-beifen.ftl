<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>java翻翻看游戏</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
	<meta name="description" 
		content="翻翻看游戏">
</head>
<style type="text/css">

</style>
<body onselectstart="return false" style="background-color: #282C34;"><!-- 该属性阻止html双击选中事件 -->

	<div class="container-fluid"><!--全屏-->
		<div class="row">
		    <div class="text-right"><!-- 居右 -->
			    <a href="toLiuyanSaolei" target="_blank"
					onmousemove='move2(this);' onmouseout='out2(this);' 
								style="color: #129FD2;font-family: 仿宋;font-size: 20px;">
									<
								</a>
		    </div>
		</div>
		<br/>
		<div class="row">
			<div id="jieshu" class="text-center" style="color:red;font-family: 仿宋;">
				&nbsp&nbsp
			</div>
		</div>
		<div class="row ">
				<div id="leiShuliang" class="col-xs-2 col-md-offset-4" style="color:red;font-family: 仿宋	;	">
					数量：10
				</div>
				<div id="shuaxin" class="col-xs-1" style="margin-left: -1.5%;">
					<img style='width: 25px;height: 25px;' src='img/76.jpg'/>
				</div>
				<div id="jishi" class="col-xs-2" style="color:red;font-family: 仿宋;">
					已耗时：0秒
				</div>
		</div>
		<div class="row" style="height: 225px;">
			<div id="leis" class="center-block" style="color: blue;font-family: 仿宋;">
			</div>
		</div>
		<br>
		<br>
		<br>
		<div class="row" style="margin-top: 18%;">
			<p style="color: aliceblue;" target="_blank">
				bug：1、有图片未展示就隐藏了（可以先点击其他方块，回头再点击该图片）。2、有时会发生未配对成功的图片不能隐藏的错误（可再点击该图片，把其当作未打开的方块）。
				3、有时剩余数量不能正确计数
			</p>
		</div>
	</div>
</body>
</html>
<script>

function move2(ob){
	$(ob).text("留言");
}
function out2(ob){
	$(ob).text("<");
}

var stopjishi = 0;//不为0时，停止计时
var jishi = 0;
var interval = setInterval(function(){
	if(stopjishi != 0){
		return;
	}
	$("#jishi").text("已耗时："+jishi++ +"秒");
},1000);
	function jibie(){
		//alert(grade);
		//location.href="saolei";
		getLeis();
		$("#leis").width(400);
		clearInterval(interval);//结束上一次计时
		jishi = 0;//重新从0计时
		stopjishi = 0;//不停止计时，开始计时
		interval = setInterval(function(){
			if(stopjishi != 0){
				return;
			}
			$("#jishi").text("已耗时："+jishi++ +"秒");
		},1000);
		$("#jieshu").text(""); // 清空
	}
	$("#shuaxin").click(function(){
		location.reload();
		//jibie();
	});
	var leis;
	getLeis();
	$("#leis").width(400);
	function getLeis(){
		$("#leis").html('');
						for(var i = 1;i<=20;i++){
							$("#leis").append("<button style='height: 100px;width: 100px;display:block;float:left;"+ 
							"text-align:center;background-color:#129FD2;color:#129FD2;'"+
												"id='"+i+"'"+
												"value="+"100 "+
												"onclick='ol(this);'"+
												"onmousemove='move(this);' "+
												"onmouseout='out(this);'>"+""+ // 口是button的text值
												//"align='center' "+
												"</button>");
							var hh = 4;
							if(i % hh == 0){ //每30个就换行。"oncontextmenu='oct(this);'"+右键事件
								$("#leis").append("<br />");
							}
						}
						
						// leftLeiNumber();//左上角展示出来雷的总数量
	}
	//阻止html默认的右键
	$("#leis").bind("contextmenu", function(){
    	return false;
	});
	function sleep(n) {
	    var start = new Date().getTime();
	    while(true)  if(new Date().getTime()-start > n) break;
	    }
	var shang; // 只记录上一条信息
	var more; // 存放多个已经关闭、或者说去掉了图片的方块
	// 鼠标单击
	function post2(ob) {
		$.ajaxSettings.async = false; // 在$.post()前把ajax设置为 false同步， true异步
		$.post('getPicUrl',
				function(json){
					json2 = json;
					//alert("json2:"+json2);
			if(more != null && more.indexOf(ob.id+',') >= 0){
			}else{
				$(ob).append("<img id='"+json2+100+"' class='atlas' style='margin:0 auto;' " +
						"src='fanfankan/"+json2+".jpg' >");
			}
		});
	}
	function ol(ob){
		var json2;
		// 1、没有上一个的记录，则记录当前信息，并且保持显示图片，再次点击该图片，还是打开
		// 2、有上一个的记录。则与其比较，相同：都保持打开。不同：都关闭。
		// 一个原则：凡是打开的图片，再次打开时，还要是原图片
		
		var src = $('#'+ob.id+' img').attr('src');
		if(null == src || more == null || more.indexOf(ob.id+',') < 0 ){ // 没有img再添加。并且more中不包含
			// 如果方块的图片展示过，就不要再请求后台获取图片id了
			
			// 大概必须是异步方法，否则该方法内的隐藏图片方法，会出问题???
			post2(ob);
		}else{
			// json2需要一个值，从more中获取
			if(more != null){
				var arr = more.split(';');
				for(var i=1;i<arr.length;i++){
					var arr2 = arr[i].split(',');
					if(ob.id == arr2[0]){
						json2 = arr2[1];
						break;
					}
				}
			}
		}
						// 点开时，查看有无上一个点开记录。（记录包括id、哪个图片）
						if(shang == null){
							// 没有上一个的记录，就记录当前信息
							shang = ob.id+","+json2;
							
							// 关闭之后的图片，可以再次打开，并且是原图片
							if(more != null){
								var arr = more.split(';');
								for(var i=1;i<arr.length;i++){
									var arr2 = arr[i].split(',');
									if(ob.id == arr2[0]){
										$('#'+arr2[0]+' img').show();
										break;
									}
								}
								
							}
							
						}else if(shang != null){
							
							// 关闭之后的图片，可以再次打开，并且是原图片
							if(more != null){
								var arr = more.split(';');
								for(var i=1;i<arr.length;i++){
									var arr2 = arr[i].split(',');
									if(ob.id == arr2[0]){
										$('#'+arr2[0]+' img').show();
										json2 = arr2[1]; // 两个方块的图片比较是否相同时使用
										break;
									}
								}
								
							}
							
//							alert(shang);
							// 有上一个方块的记录，那么与其比较
							// 相同
							var arr = shang.split(',');
							// 不是同一个方块，并且图片id相同
							if(arr[0] != ob.id && arr[1] == json2){
								//alert("两个图片相同");
								shang = null; // 置空上一个
								// 剩余计数
								var a = $("#leiShuliang").text();	
					    		var a2 = a.split("：");
					    		var number = a2[1];
					    		$("#leiShuliang").text("数量："+(parseInt(number)-1));	
					    		if( (parseInt(number)-1) == 0){
					    			stopjishi = 1; // 停止计时
					    			alert("胜利");
					    		}
							}
							// 不同
							if(arr[0] != ob.id && arr[1] != json2){
								//alert("两个图片不相同");
								// 将两个方块的图片去掉、或隐藏。但是再次点击时必须是原图片
								more = more+";"+shang+";"+ob.id+","+json2;
								$("#"+arr[0]+" img").toggle(2000); // // 隐藏上一个 arr[0]存放的是id，错写为arr[1]（pic编号）找了很久，还以为方法有问题
								$("#"+ob.id+" img").toggle(2000); // "slow"隐藏当前(bug,频繁出现当前方块的图片还没显示，就隐藏display: none;的情况)
								//sleep(2000);
								//$("#"+arr[0]+" img").remove(); // 隐藏上一个
								//$("#"+ob.id+" img").remove(); // 
								shang = null; // 置空上一个
							}
						}
			
	}
	//鼠标悬浮
	function move(ob){
    	//$(ob).css("background-color","pink");
    	//$(ob).css("color","pink");
    	
    	var ob2 = $(ob).text();
    		$(ob).css("background-color","pink");
    		$(ob).css("color","pink");
    	
	}
	//鼠标悬浮移开
	function out(ob){
		
		var ob2 = $(ob).text();
    		$(ob).css("background-color","#129FD2");
    		$(ob).css("color","#129FD2");
	
	}
	
	//双击(根本不存在双击事件，但是有左键按下、弹起事件（但也可有可无，用于提示当前方块的周围有哪8个方块用的） "ondblclick='shuangji(this)'"+)
	function shuangji(ob){
		
	}
	//鼠标右键事件
	function oct(ob){
		var ob2 = $(ob).text();
		//如果格子中出现了数字，则是排雷成了，不能再更改
		if(ob2.indexOf("×") != -1 || ob2.indexOf("1") != -1 || ob2.indexOf("2") != -1 || ob2.indexOf("3") != -1 || 
		   ob2.indexOf("4") != -1 || ob2.indexOf("5") != -1 || ob2.indexOf("6") != -1 || ob2.indexOf("7") != -1 || 
		   ob2.indexOf("8") != -1){
			ol(ob);//有人习惯右键数字
			return;
		}
		var id3 = $(ob).attr("id")+1000;
		if(ob2 == "" && $(ob).has("#"+id3+"")){
			//alert(id3);
				//alert("dsfdsfsd");
	    		$(ob).text("口");
	    		$(ob).css("color","#129FD2");
	    		$(ob).css("background-color","#129FD2");
	    		var a = $("#leiShuliang").text();	
	    		var a2 = a.split("：");
	    		var number = a2[1];
	    		
	    		$("#leiShuliang").text("剩余数量："+(parseInt(number)+1));	
			}
		if(ob2 == ""){ //当该标签仅含有子标签时，text值为空
			return;
		}
		if(ob2 == "口"){	
			//$(ob).text("雷");     //  <img style="width:15px;" src="/queqiao/img/sanjiaoqi.jpg">
			$(ob).text("");//清空 口 字
			var id2 = $(ob).attr("id");
    		$(ob).append("<img id='"+id2+1000+"'"+
    				"style='width: 19px;margin-left: -6px;' src='img/sanjiaoqi.jpg'/>");
    		//var id = $(ob).attr("id");
    		//$("#"+id+1000+"").attr("src","img/sanjiaoqi.jpg");
    		$(ob).css("color","red");
    		$(ob).css("background-color","#d6d5b6");
    		
    		var a = $("#leiShuliang").text();	
    		var a2 = a.split("：");
    		var number = a2[1];
    		
    		$("#leiShuliang").text("剩余数量："+(number-1));	
		}
		
		//每一次右键都遍历一次 var leis;
		var totalLeiNumber = 0;
		var biaojiLei = 0;
		for(var i = 1;i<=leis.length;i++){
			//                有无雷arr[0]     周围雷的数量arr[1]       周围雷的idsarr[2]      周围方块的idsarr[3]
			//"value="+leis[i-1].hasLei+":"+leis[i-1].roundNum+":"+leis[i-1].leiIds+":"+leis[i-1].roundIds+" "+ 	
			
			var hasLei = leis[i-1].hasLei;
			if(hasLei == true){  //如果有雷的方块都有“雷”字，并且“雷”字的数量等于实际雷数，
				totalLeiNumber++;//实际的雷数量
				
				var id = $("#"+i+"").attr("id")+1000;
				var id2 = $("#"+id+"").length;
				if(id2 > 0){
					biaojiLei++; //标记的“雷”字数量
				}
				
				/* var lei = $("#"+i+"").text();
				if(lei == "雷"){
					biaojiLei++; //标记的“雷”字数量
				} */
				
			}
		}
		if(totalLeiNumber == biaojiLei){
			stopjishi = 1 ;
			var a = $("#jishi").text();
			
			//$("#jishi").text(a+"。恭喜你，胜利了！");
			var shengyu = $("#leiShuliang").text();
			if(shengyu.indexOf("-") != -1 ){
				return;//包含-，则不成功
			}
			if(shengyu.indexOf("0") != -1 ){
				//return;//包含0，则成功
				$("#jieshu").text("success！");
			}
			
			}
		
		
	}
    function leftLeiNumber(){
    	var totalLeiNumber = 0;
		for(var i = 1;i<=leis.length;i++){
			//                有无雷arr[0]     周围雷的数量arr[1]       周围雷的idsarr[2]      周围方块的idsarr[3]
			//"value="+leis[i-1].hasLei+":"+leis[i-1].roundNum+":"+leis[i-1].leiIds+":"+leis[i-1].roundIds+" "+ 	
			
			var hasLei = leis[i-1].hasLei;
			if(hasLei == true){  //如果有雷的方块都有“雷”字，并且“雷”字的数量等于实际雷数，
				totalLeiNumber++;//实际的雷数量
			}
		}
    	$("#leiShuliang").text("剩余数量："+totalLeiNumber);			
	}
		
</script>

