$(function(){
	
})
var hostURL = "http://productadmin.lenovo.com"; //10.113.219.161:8080 //productadmin.lenovo.com
function myAjax(url, callback, data, type) {
    if (!type) {
        type = "GET"
    }
    $.ajax({
        url: hostURL + url,
        type: type,
        data: data,
        contentType:'application/x-www-form-urlencoded; charset=utf-8', 
        //dataType:'json',  
        dataType: 'jsonp', 
        //jsonp的值自定义,如果使用jsoncallback,那么服务器端,要返回一个jsoncallback的值对应的对象. 
        jsonp: 'jsoncallback',           
        success: function (db) {
            console.log(db);
            switch (db.errorCode) {
                case "1":
                    alert(db.errors[0]);
                    break;
                case "2":
                    alert("msg8")
                    break;                    
                default:
                    if (callback) {
                        callback(db);
                    }
                    break;
            }
        },
        error:function(){
            alert("请求出错了……")
        }
    })
}

//笛卡儿积组合
//var list = [[1,2],[3],[4,5]];
//var list = {"n1":[1,2],"n2":[3],"n3":[4,5]};
function descartes(list) 
{
    //parent上一级索引;count指针计数
    var point  = {};

    var result = [];
    var pIndex = null;
    var tempCount = 0;
    var temp   = [];

    //根据参数列生成指针对象
    for(var index in list)
    {
        if(typeof list[index] == 'object')
        {
            point[index] = {'parent':pIndex,'count':0}
            pIndex = index;
        }
    }

    //单维度数据结构直接返回
    if(pIndex == null)
    {
        return list;
    }

    //动态生成笛卡尔积
    while(true)
    {
        for(var index in list)
        {
            tempCount = point[index]['count'];
            temp.push(list[index][tempCount]);
        }

        //压入结果数组
        result.push(temp);
        temp = [];

        //检查指针最大值问题
        while(true)
        {
            if(point[index]['count']+1 >= list[index].length)
            {
                point[index]['count'] = 0;
                pIndex = point[index]['parent'];
                if(pIndex == null)
                {
                    return result;
                }

                //赋值parent进行再次检查
                index = pIndex;
            }
            else
            {
                point[index]['count']++;
                break;
            }
        }
    }
}
//console.log(descartes(list)[0]);

$.extend({
    //  获取对象的长度，需要指定上下文 this
    Object:     {
        count: function( p ) {
            p = p || false;
         
            return $.map( this, function(o) {
                if( !p ) return o;
                 
                return true;
            } ).length;
        }
    }
});



//多级树形结构
var str = "";
var forTree = function(o){
        for(var i=0;i<o.length;i++){
            var urlstr = "";
            //var o_categoryCode="";
            try{
                if(o[i]["leaf"] == true){
                    if(o[i]["id"]) {var o_id = o[i]["id"]}
                    urlstr = "<ul class='main-menu' style='padding:0 10px;'><li><a><i class='fa-file'></i><span datacode='"+o[i]["categoryCode"]+"' dataid='"+o_id+"'>"+ o[i]["name"] +"</span></a>";
                }else{
                    if(o[i]["categoryCode"] == "04") {var o_categoryCode = "o_categoryCode"}else{var o_categoryCode = ""}
                    urlstr = "<ul class='main-menu "+o_categoryCode+"' style='padding:0 10px;'><li class='has-sub'><a><i class='fa-file'></i><span datacode='"+o[i]["categoryCode"]+"' dataid='"+o[i]["id"]+"'>"+ o[i]["name"] +"</span></a>"; 
                }
                str += urlstr;
                if(o[i]["nodes"] != null){
                    forTree(o[i]["nodes"]);
                }
                str += "</li></ul>";
            }catch(e){}
        }
        return str;
    }

/* 异步查询 - 生成表格 */

var ajaxQuery = {
    ajaxJson:"",
    ajax : function(ajaxInfo){
        if (!ajaxInfo.type) {
            ajaxInfo.type = "GET";
        }
        if (!ajaxInfo.dataType) {
            ajaxInfo.dataType = "json";
        }
        if (!ajaxInfo.jsonp) {
            ajaxInfo.jsonp = "";
        }
        if (!ajaxInfo.data) {
            ajaxInfo.data = null;
        }
        $.ajax({
            url: ajaxInfo.url,
            data: ajaxInfo.data, 
            type: ajaxInfo.type,
            //data: ajaxInfo.data,
            contentType:'application/x-www-form-urlencoded; charset=utf-8', 
            //dataType:'json',  
            dataType: ajaxInfo.dataType, 
            
            //jsonp的值自定义,如果使用jsoncallback,那么服务器端,要返回一个jsoncallback的值对应的对象. 
            jsonp: ajaxInfo.jsonp,           
            success: function (db) {
                switch (db.errorCode) {
                    case "1":
                        //alert();
                        showPop(db.errors[0],1);
                        break;
                    case "2":
                        alert("msg8")
                        break;                    
                    default:
                        if(ajaxInfo.callback){                            
                            ajaxInfo.callback(db);
                            showPop("操作成功",0);
                        }else{
                            ajaxQuery.ajaxCallback(db);
                        } 
                        break;
                }                               
                //console.log(db);
            },
            error:function(){
                alert("请求出错了……")
            },
            beforeSend: function(){
                showloading();
            },
            complete: function(){
                hiddenloading();
            }
        })        
    },
    ajaxCallback : function(db){
        //ajaxQuery.ajaxJson = db;
        alert("操作成功~！");
    }
}

// 模板绑定数据
function makeTMPL(OPT){
    var tmplDB = $(OPT.tmplID).tmpl(OPT.mydb); 
    //console.log(pro_type_thml);     
    $(OPT.boxID).html(tmplDB);  
}
function mySerialize(el,callback){ //分页
    var my_serialize = $(el).serialize();
    callback(my_serialize,1);
    $("#pageNo").val(1);
    $("#pageGo").click(function(event) {
        var pageN = parseInt($("#pageNo").val());
        callback(my_serialize,pageN);
    });
    $("#pagePrev").click(function(event) {
        var pageN = parseInt($("#pageNo").val()) - 1;
        callback(my_serialize,pageN);
        $("#pageNo").val(pageN);
    });
    $("#pageNext").click(function(event) {
        var pageN = parseInt($("#pageNo").val()) + 1;
        callback(my_serialize,pageN);
        $("#pageNo").val(pageN);
    });
}
function makePages(){

}

function checkSubmit(el){//checkbox点击绑定table行
    var tr_html;
    var _el = $(el);
    var el_tbody = $(el).find('tbody');
    var el_checkbox = el_tbody.find('[type="checkbox"]:checked');
    for (var i = 0; i < el_checkbox.length; i++) {
        var _tr = $(el_checkbox[i]).parents("tr").clone().html();
        tr_html += '<tr>'+_tr+'</tr>';
    };
    return tr_html;
}

/* 树形表格 */
var treegrid_str = "";
var forTreegrid = function(o){
        for(var i=0;i<o.length;i++){
            var urlstr = "";
            try{
                if(o[i]["leaf"] == true){
                    var o_parentId="";
                    var o_id;
                    var o_isDisplayOnFrontSite;
                    var o_isDisplayOnMobile;
                    if(o[i]["isDisplayOnFrontSite"]){o_isDisplayOnFrontSite = "是"}else{o_isDisplayOnFrontSite = "否"}
                    if(o[i]["isDisplayOnMobile"]){o_isDisplayOnMobile = "是"}else{o_isDisplayOnMobile = "否"}
                    if(o[i]["id"]) { o_id = o[i]["id"]}
                    if(o[i]["parentId"]) {o_parentId = "treegrid-parent-" + o[i]["parentId"]}
                    urlstr += "<tr class='treegrid-"+o_id+" "+o_parentId+"' style='padding:0 10px;'><td><input name='GoodsCategory_ID' type='checkbox' value='"+o_id+"'><td><span class='treegrid-expander'></span><span dataid='"+o_id+"'>"+ o[i]["name"] +"</span></td>";
                    urlstr += "<td>"+o[i]["categoryCode"]+"</td>";
                    urlstr += "<td><span title='是否叶子节点'></span>是</td>";
                    urlstr += "<td>"+o_isDisplayOnFrontSite+"</td>";
                    urlstr += "<td>"+o_isDisplayOnMobile+"</td>";
                }else{
                    var o_parentId="";
                    var o_id;
                    var o_isDisplayOnFrontSite;
                    var o_isDisplayOnMobile;
                    if(o[i]["isDisplayOnFrontSite"]){o_isDisplayOnFrontSite = "是"}else{o_isDisplayOnFrontSite = "否"}
                    if(o[i]["isDisplayOnMobile"]){o_isDisplayOnMobile = "是"}else{o_isDisplayOnMobile = "否"}
                    if(o[i]["id"]) { o_id = o[i]["id"]}
                    if(o[i]["parentId"]) {o_parentId = "treegrid-parent-" + o[i]["parentId"]}
                    urlstr += "<tr class='treegrid-"+o_id+" "+o_parentId+"' style='padding:0 10px;'><td><input name='GoodsCategory_ID' type='checkbox' value='"+o_id+"'><td class='has-sub'><span class='treegrid-expander'></span><span dataid='"+o[i]["id"]+"'>"+ o[i]["name"] +"</span></td>"; 
                    urlstr += "<td>"+o[i]["categoryCode"]+"</td>";
                    urlstr += "<td><span title='是否叶子节点'></span>否</td>";
                    urlstr += "<td>"+o_isDisplayOnFrontSite+"</td>";
                    urlstr += "<td>"+o_isDisplayOnMobile+"</td>";
                }
                treegrid_str += urlstr;
                if(o[i]["nodes"] != null){
                    forTreegrid(o[i]["nodes"]);
                }
                treegrid_str += "</tr>";
            }catch(e){}
        }
        return treegrid_str;
    }
/* select 添加附属data */
function select_change_addData(selectID,fun){
    $(selectID).change(function(){
        var _selectID = selectID;
        var this_val = $(this).val();
        var this_data = $(this).find('option[value='+this_val+']').attr("da-no");
        $(this).attr("data-no",this_data);
        //console.log($(this).attr("data-no"));
        if(fun){fun(_selectID,this_data);}        
    })
}

// function aa(db){
//      var OPT = {
//         mydb:"datas",
//         boxID:"#prolistTable tbody",
//         tmplID:"#prolistQuery_tmpl"
//     };
//     var callback = function(db){
//         //console.log(this);
//         OPT.mydb = db[OPT.mydb];
//         makeTMPL(OPT);
//         $(".btn-secondary").click(function(event) {
//             proEdit();
//             proDetailsQuery();            
//         });
//     };
//     var ajaxInfo = {
//         "url":hostURL + "/queryData/queryForListByOptions?tableName=goodsinfoes&",
//         "dataType":"jsonp",
//         "jsonp":"jsoncallback",
//         "data":db,
//         //"url":"learn.json",
//         "callback":callback
//     }    
//     ajaxQuery.ajax(ajaxInfo);
//     alert(OPT);
// }