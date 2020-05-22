<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>java斗地主</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
	<meta name="description" 
		content="斗地主">
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
		<div class="row" >
			<div class="col-xs-4 col-xs-offset-4" >
			<div class="row" >
				<div id="leiShuliang" class="col-xs-4" style="color:green;font-family: 仿宋	;	">
					剩余数量：10
				</div>
				<div id="dianjicishu" class="col-xs-4"  style="color:green;font-family: 仿宋	;	">
					点击次数：0
				</div>
				<div id="shuaxin" class="col-xs-1" style="">
					<img style='width: 25px;height: 25px;' src='img/76.jpg'/>
				</div>
				<div id="jishi" class="col-xs-3" style="color:green;font-family: 仿宋;">
					耗时：0秒
				</div>
			</div>
			</div>
		</div>
		<!--一个简单的bootstrap布局写法。 行中只能包含列，列再包含行，再包含列。 每一个行都平均分为12列。
		<div class="container-fluid"><!--全屏-->
			<div class="row">
				<div class="col-xs-4 col-xs-offset-4"> // 向右偏移四列
					<div class="row">
						<div class="col-xs-3">td</div>
						<div class="col-xs-3">td</div>
						<div class="col-xs-3">td</div>
					</div>
				</div>
			</div>
		</div>
		-->
		
		<div class="row" style="height: 225px;">
			<div id="leis" class="center-block" style="color: blue;font-family: 仿宋;">
			</div>
		</div>
		<br>
		<br>
		<br>
		<div class="row" style="margin-top: 18%;">
			<p style="color: aliceblue;" target="_blank">
				bug：如果点击太快，出现多个图片渐隐的情况，同时又在其中点击出来两个相同的图片，这种情况下，有的图片会显示不出来（但不影响其他功能）。
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
var dianjicishu = 0;
var interval = setInterval(function(){
	if(stopjishi != 0){
		return;
	}
	$("#jishi").text("耗时："+jishi++ +"秒");
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
			$("#jishi").text("耗时："+jishi++ +"秒");
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
	function ol(ob){
		$("#dianjicishu").text("点击次数："+ (++dianjicishu) );
		$("#"+ob.id+" img").show();
		// 1、没有上一个的记录，则记录当前信息，并且保持显示图片，再次点击该图片，还是打开
		// 2、有上一个的记录。则与其比较，相同：都保持打开。不同：都关闭。
		// 一个原则：凡是打开的图片，再次打开时，还要是原图片
		
		var src = $('#'+ob.id+' img').attr('src'); // fanfankan/9.jpg
						// 点开时，查看有无上一个点开记录。（记录包括id、哪个图片）
						if(shang == null){
							// 没有上一个的记录，就记录当前信息
							shang = ob.id+","+src; // 1,fanfankan/1.jpg
						}else if(shang != null){
							
							// 有上一个方块的记录，那么与其比较
							// 相同
							var arr = shang.split(',');
							if(arr[0] == ob.id){ // 两次点击了同一个方块
								return;
							}
							// 不是同一个方块，并且图片id相同
							if(arr[0] != ob.id && arr[1] == src){
								// 将两个方块设置为不能再点击
								$("#"+arr[0]+"").attr('disabled', true);
								$("#"+ob.id+"").attr('disabled', true);
								// 解决，当已打开图片A，又去打开图片A2时，恰好A2正在渐隐，那么虽然已完成判断他们相同
								// 但是A2不会再显示了，添加下面的代码之后，A2会渐显出来。（A，A2是相同图片）
								$("#"+ob.id+" img").show(10); // 
								$(ob).css("background-color","#129FD2");
					    		$(ob).css("color","#129FD2"); // 由于上面方块不能点击了，鼠标离开方法失效了
								//alert("两个图片相同");
								shang = null; // 置空上一个
								// 剩余计数
								var a = $("#leiShuliang").text();	
					    		var a2 = a.split("：");
					    		var number = a2[1];
					    		$("#leiShuliang").text("剩余数量："+(parseInt(number)-1));	
					    		if( (parseInt(number)-1) == 0){
					    			stopjishi = 1; // 停止计时
					    			//alert("胜利");
					    			$("#jieshu").text("胜利！");
					    		}
							}
							// 不同
							if(arr[0] != ob.id && arr[1] != src){
//								$("#"+arr[0]+"").attr('disabled', true);
//								$("#"+ob.id+"").attr('disabled', true);
								//alert("两个图片不相同");
								// 都用toggle方法隐藏时，两个图片逐渐隐藏时，快速点击两个图片，两个图片会都显现，并且不会再隐藏了。hide方法没有这个问题。
								// hide方法时，重现上述操作，大概相当于没有点击。不知道是什么原因
								$("#"+arr[0]+" img").hide(2000); // // 隐藏上一个
								$("#"+ob.id+" img").hide(2000); // 
								//sleep(2000);
								//$("#"+arr[0]+" img").remove(); // 隐藏上一个
								//$("#"+ob.id+" img").remove(); // 
//								$("#"+arr[0]+"").attr('disabled', false);
//								$("#"+ob.id+"").attr('disabled', false);
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

