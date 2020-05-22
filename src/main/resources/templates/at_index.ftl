<!DOCTYPE html>

<html>
<head>
    <meta name="viewport" content="width=device-width" />
    <title>精准测试</title>
    <link rel="stylesheet" href="../css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../css/bootstrap-table/bootstrap-table.css"/>
    <link rel="stylesheet" href="../css/font-awesome.min.css"/>
    <link rel="stylesheet" href="../css/ionicons.min.css"/>
    <link rel="stylesheet" href="../css/datatables/dataTables.bootstrap.css"/>
    
    <script src="../js/jquery3.2.1.min.js"></script>
    <script src="../js/bootstrap3.3.7.min.js"></script>
    <link rel="stylesheet" href="../css/bootstrap-table1.15.3.min.css">
    <script src="../js/bootstrap-table1.15.3.min.js"></script>
    <script src="../js/bootstrap-table-zh-CN1.15.3.min.js"></script>
    <script src=""></script>
    <!--@*4、页面Js文件的引用*@
    <script src="~/Scripts/table/Home/Index.js"></script>
    -->
</head>
<body class="container" style="background-color: aliceblue;">
    <div class="panel-body" style="padding-bottom:0px;">
	    <div class="row text-center">
	    	<span class="col-xs-12 " style="font-size: xx-large;">精准测试项目</span>
	    </div>
        <div class="panel panel-default" style="height: 50px;">
            <div class="panel-body">
                <form id="formSearch" class="form-horizontal">
                    <div class="form-group" style="margin-top:-6px">
                        <label class="control-label col-sm-1" for="txt_search_departmentname">git仓库</label>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="txt_search_departmentname">
                        </div>
                        <label class="control-label col-sm-1" for="txt_search_statu">稳定分支</label>
                        <div class="col-sm-2">
                            <input type="text" class="form-control" id="txt_search_statu">
                        </div>
                        <label class="control-label col-sm-1" for="txt_search_statu">测试分支</label>
                        <div class="col-sm-2">
                        	<input type="text" class="form-control" id="txt_search_statu">
                        </div>
                        <div class="col-sm-2" style="text-align:left;">
                            <button type="button" style="margin-left:50px" id="btn_query" class="btn btn-primary">获取变更代码</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div id="toolbar" class="btn-group">
            <button id="btn_add" type="button" class="btn btn-success">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
            </button>
            <button id="btn_edit" type="button" class="btn btn-primary">
                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
            </button>
            <button id="btn_delete" type="button" class="btn btn-danger">
                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
            </button>
        </div>
        <table id="tb_departments"></table>
    </div>
    <script>
    $(function () {

        //1.初始化Table
        var oTable = new TableInit();
        oTable.Init();

        //2.初始化Button的点击事件
        var oButtonInit = new ButtonInit();
        oButtonInit.Init();

    });


    var TableInit = function () {
        var oTableInit = new Object();
        //初始化Table
        oTableInit.Init = function () {
            $('#tb_departments').bootstrapTable({
                url: '/accurateTesting/getAll',         //请求后台的URL（*）
                method: 'get',                      //请求方式（*）
                dataType: 'json',  
                toolbar: '#toolbar',                //工具按钮用哪个容器
                theadClasses:'.thead-light',
                striped: true,                      //是否显示行间隔色
                cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                pagination: true,                   //是否显示分页（*）
                showPaginationSwitch: false,        //是否显示分页数
                sortable: false,                     //是否启用排序
                sortName: "title",                     //是否启用排序
                sortOrder: "desc",                   //排序方式
                queryParams: oTableInit.queryParams,//传递参数（*）
                queryParamsType: '',                //如果要在oTableInit.queryParams方法获取pageNumber和pageSize的值，需要将此值设置为空字符串（*）
                sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
                pageNumber:1,                       //初始化加载第一页，默认第一页
                pageSize: 10,                       //每页的记录行数（*）
                pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
                search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
                strictSearch: true,
                minimumCountColumns: 2,             //最少允许的列数
                singleSelect: false,                 //是否单选模式
                height: $(window).height() - 200,   //table总高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
                showToggle: false,                    //是否显示详细视图和列表视图的切换按钮
                cardView: false,                    //是否显示详细视图
                detailView: false,                   //是否显示父子表
                showColumns: false,                  //是否显示所有的列
                uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
                showRefresh: true,                  //是否显示刷新按钮
                clickToSelect: false,                //是否启用点击选中行
                paginationPreText: "上一页",
                paginationNextText: "下一页",
                columns: [{
                    checkbox: true
                }, {
                    field: 'id',
                    title: '主键id'
                }, {
                    field: 'testingExampleName',
                    title: '测试用例名称', //align: 'center'
                	//events: operateEvents1
                	formatter: operateFormatter1
                }, ]
            });
        };

        //得到查询的参数
        oTableInit.queryParams = function (params) {
            // 特别说明：
            //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
    　　　　　// 如果queryParamsType=limit,params包含{limit, offset, search, sort, order}
    　　　　　// 如果queryParamsType!=limit,params包含{pageSize, pageNumber, searchText, sortName, sortOrder}
            var temp = {   
                pageSize: params.pageSize,   //页面大小
                pageNumber: params.pageNumber,  //页码
                departmentname: $("#txt_search_departmentname").val(),
                statu: $("#txt_search_statu").val()
            };
            return temp;
        };
        return oTableInit;
    };
    /**function operateFormatter(value, row, index) {
    	return [
    	'<div id="btn_detail" type="button" class="RoleOfA btn-default bt-select">详情/div>',  
    	].join('');
    }*/
    function operateFormatter1(value, row, index) {
    	return [
    		'<a title="查看关联方法" onclick="getLinkMethod('+row.id+')"'
    		+'style="background-color: ;cursor: pointer;text-decoration:underline;">'+value+'</a>',  
    		].join('');
    }
    function getLinkMethod(value){
    	// 请求后台，获取该测试用例关联的方法
    	$.post('/accurateTesting/getLinkMethod?id='+value,
				function(json){
					//pics = json.list;
    		alert("dsf");
		});
    }
    window.operateEvents1 = {
    		'click .RoleOfA': function(e, value, row, index) {
    			detailmodal.open();
    			$("#dev_id").val(row.id);
    			$("#seq_no").val(row.seq_no);
    			$("#dev_pos").val(row.position);
    			$("#dev_type1").val(row.type);
    			$("#dev_status").val(row.status);
    			$("#fault").val(row.fault);
    			$("#buy_time").val(row.purchase_time);
    			$("#quality_time").val(row.quality_time);
    			$("#inputer").val(row.inputer);
    			$("#maintain_unit").val(row.maintain_unit);
    			for(var i in row) console.log(i);
    		}
    	};
    
    var ButtonInit = function () {
        var oInit = new Object();
        var postdata = {};

        oInit.Init = function () {
            $("#btn_add").click(function () {
                alert("add")
            });

            $("#btn_edit").click(function () {
                var arrselections = $("#tb_departments").bootstrapTable('getSelections');
                if (arrselections.length > 1) {
                    alert('只能选择一行进行编辑');

                    return;
                }
                if (arrselections.length <= 0) {
                    alert('请选择有效数据');
                    return;
                }
                alert("edit")
                //$("#myModalLabel").text("编辑");
                //$("#txt_departmentname").val(arrselections[0].DEPARTMENT_NAME);
                //$("#txt_parentdepartment").val(arrselections[0].PARENT_ID);
                //$("#txt_departmentlevel").val(arrselections[0].DEPARTMENT_LEVEL);
                //$("#txt_statu").val(arrselections[0].STATUS);

                //postdata.DEPARTMENT_ID = arrselections[0].DEPARTMENT_ID;
                //$('#myModal').modal();
            });

            $("#btn_delete").click(function () {
                var arrselections = $("#tb_departments").bootstrapTable('getSelections');
                if (arrselections.length <= 0) {
                    alert('请选择有效数据');
                    return;
                }

                Ewin.confirm({ message: "确认要删除选择的数据吗？" }).on(function (e) {
                    if (!e) {
                        return;
                    }
                    $.ajax({
                        type: "post",
                        url: "/Home/Delete",
                        data: { "": JSON.stringify(arrselections) },
                        success: function (data, status) {
                            if (status == "success") {
                                toastr.success('提交数据成功');
                                $("#tb_departments").bootstrapTable('refresh');
                            }
                        },
                        error: function () {
                            toastr.error('Error');
                        },
                        complete: function () {

                        }

                    });
                });
            });

            $("#btn_submit").click(function () {
                //postdata.DEPARTMENT_NAME = $("#txt_departmentname").val();
                //postdata.PARENT_ID = $("#txt_parentdepartment").val();
                //postdata.DEPARTMENT_LEVEL = $("#txt_departmentlevel").val();
                //postdata.STATUS = $("#txt_statu").val();
                //$.ajax({
                //    type: "post",
                //    url: "/Home/GetEdit",
                //    data: { "": JSON.stringify(postdata) },
                //    success: function (data, status) {
                //        if (status == "success") {
                //            toastr.success('提交数据成功');
                //            $("#tb_departments").bootstrapTable('refresh');
                //        }
                //    },
                //    error: function () {
                //        toastr.error('Error');
                //    },
                //    complete: function () {

                //    }

                //});
            });

            $("#btn_query").click(function () {
                $("#tb_departments").bootstrapTable('refresh');
            });
        };

        return oInit;
    };
    </script>
    
    
</body>
</html>