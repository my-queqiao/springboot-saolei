<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>java在线扫雷</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
	<meta name="description" 
		content="扫雷是一款大众类的益智小游戏，于1992年发行。游戏目标是在最短的时间内根据点击格子出现的数字找出所有非雷格子，同时避免踩雷，踩到一个雷即全盘皆输。">
</head>
<style type="text/css">

</style>
<body onselectstart="return false" style="background-color: #282C34;"><!-- 该属性阻止html双击选中事件 -->

	<div class="container-fluid"><!--全屏-->
		<br>
		<br>
		<br>
		<br>
		<br>
		<!-- center-block自身居中
			 text-center子元素居中。注意让div居中时，给出div一个宽度，才好看出来效果，就是说如果该div是全屏宽度，居中与否都没有区别。
			 固定宽度的div，里面放多个button标签时，如果超出div的宽度，会自动另起一行。
		-->
		<div id="leis" class="row" style="color: blue;font-family: 仿宋;">
			<div class="center-block" style="width:225px;">
				<button style="float:left;background-color:#129FD2;height: 25px;width: 25px;"></button>
				<button style="float:left;background-color:#129FD2;height: 25px;width: 25px;"></button>
				<button style="float:left;background-color:#129FD2;height: 25px;width: 25px;"></button>
				<button style="float:left;background-color:#129FD2;height: 25px;width: 25px;"></button>
				<button style="float:left;background-color:#129FD2;height: 25px;width: 25px;"></button>
				<button style="float:left;background-color:#129FD2;height: 25px;width: 25px;"></button>
				<button style="float:left;background-color:#129FD2;height: 25px;width: 25px;"></button>
				<button style="float:left;background-color:#129FD2;height: 25px;width: 25px;"></button>
				<button style="float:left;background-color:#129FD2;height: 25px;width: 25px;"></button>
			</div>
		</div>
		<br>
		<br>
		<br>
	</div>
</body>
</html>
<script>

	function getLeis(grade){
		$("#leis").html('');
		leis = 0;//点击等级之后，清零
		$.post('getLeis?grade='+grade,
				function(json){
						leis = json.leis;
						//private boolean hasLei;//此处有无雷
						//private int roundNum;//周围雷的个数。包括0个
						//private String leiIds;//周围雷的id。用,隔开
						for(var i = 1;i<=leis.length;i++){
							var hasLei = leis[i-1].hasLei;
							$("#leis").append("<button style='height: 25px;width: 25px;display:block;float:left;"+ 
							"text-align:center;background-color:#129FD2;color:#129FD2;'"+
												"id='"+leis[i-1].id+"'"+
												"value="+leis[i-1].hasLei+":"+leis[i-1].roundNum+":"+leis[i-1].leiIds+":"+leis[i-1].roundIds+" "+
												"oncontextmenu='oct(this);'"+
												"onclick='ol(this);'"+
												"onmousemove='move(this);' "+
												"onmouseout='out(this);'>"+"口"+
												
												"</button>");
							
							
							/*if(hasLei == true){             "<img id='"+leis[i-1].id+1000+"'/>"+
								$("#leis").append("<span style='color:red;'>"+"雷"+"</span>");
							}else{
								$("#leis").append("<span style='color: #129FD2;'>"+leis[i-1].roundNum+"</span>");
							}*/
							var hh;
							if(grade==1)hh=9
							if(grade==2)hh=16
							if(grade==3)hh=30
							if(i % hh == 0){ //每30个就换行。
								$("#leis").append("<br />");
							}
						}
						
						leftLeiNumber();//左上角展示出来雷的总数量
				}	
		)
	}
		
</script>

