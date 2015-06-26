//扩展选中Ckeckbox数据方法
//tigerContent：Ckeckbox所在的DIV
//multiOrSingle：0单选，1多选
//callBackHandle：处理函数 参数为IDs
//返回选择项目的ID数组IDs

window.ApiBaseURL = "http://productadmin.lenovo.com";
//window.ApiBaseURL = "http://127.0.0.1:8080";
window.SelectWindowPageSize = 10;
function myAlert(message,ErrorOrSuccess)
{
	//参数描述：ErrorOrSuccess-默认错误消息，成功消息为0
   
   showPop(message,ErrorOrSuccess?ErrorOrSuccess:101);
}
 
///trigger: 触发者，range：被选中或取消Checkbox所在元素
function SelectAllOrCannelAll(trigger,range)
{

    $(trigger).click(function () {


        if ($(this).prop("checked")) {
            $(range + " :checkbox").prop("checked", true);
        } else {
            $(range + " :checkbox").prop("checked", false);
        }



    });
}
function CrossdomainGet(url, mysuccess, mydata, Qtype) {

    $.ajax({
        type: Qtype ? Qtype : "GET",
        url: url,
        data: mydata,
        dataType: "jsonp",
        jsonp: 'jsoncallback',
        success: function (data) {
            if (data.errorCode == "0") {
                mysuccess(data);
            }
            else {
                //显示错误信息
                var errorMessage = data.errors;
                myAlert(errorMessage);
            }
        }
        ,
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            myAlert("请求数据异常，状态码：" + XMLHttpRequest.status);
        }
    });

}

(function ($) {
    $.extend({
        getCkeckboxSelected: function (tigerContent, multiOrSingle, callBackHandle) {
            var IDs = [];
            var myselectRow = $("#" + tigerContent + " input[type=checkbox]:checked");
            if (myselectRow.length > 0) {
                $.each(myselectRow, function () {
                    IDs.push($(this).val());
                });
                //单选
                if (multiOrSingle == 0) {
                    if (myselectRow.length == 1) {
                        callBackHandle(IDs);
                        $.each(myselectRow, function () {
                           $(this).attr("checked",false);
                        });
                    }
                    else {
                        myAlert("只能选择一条数据");
                        return false;
                    }
                }
                    //多选
                else {
                    callBackHandle(IDs);
                    $.each(myselectRow, function () {
                        $(this).attr("checked", false);
                    });
                }
            }
            else {

                myAlert("您没有选择任何数据");
                return false;
            }
        }



    });
})(jQuery); 
//导出excel
function makeExcel(el) {
    //var _db = $(el).serialize();
    var url = window.ApiBaseURL + "/download/productExcel?";
    //var pageN = "&curPage=" + parseInt($("#pageNo").val());
    //var action = url + _db;
    var _nameLike = $("#productName").val();
    $("#formQuery_excel").find("[name='nameLike']").val(_nameLike);

    var _code = $("#goodsCode").val();
    $("#formQuery_excel").find("[name='code']").val(_code);

    var _salesType = $("#product-salesType").val();
    $("#formQuery_excel").find("[name='salesType']").val(_salesType);

    var _platformID = $("#platform_combox").val();
    $("#formQuery_excel").find("[name='platformID']").val(_platformID);

    //var _curPage = $("#excel_curPage").val();
    //$("#formQuery_excel").find("[name='curPage']").val(_curPage);
    var _marketable = $("#product-marketable").val();
    $("#formQuery_excel").find("[name='marketable']").val(_marketable);
    var _materialNumber = $("#goodsmaterialNumber").val();
    $("#formQuery_excel").find("[name='materialNumber']").val(_materialNumber);
    var _status = $("#product-status").val();
    $("#formQuery_excel").find("[name='status']").val(_status);

    //console.log(action);
    $("#formQuery_excel").attr("action", url).submit();;
    //$("#formQuery_excel")
}


