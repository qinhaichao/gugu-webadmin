
/*=============== 生成笛卡尔积 ================*/
function pro_typeCallback(db){    //选择产品类型后 - 回调函数 - 生成产品分类
    
    // if(db.GoodsSpecificationDetailID.length<=0){
    //     db.GoodsSpecificationDetailID = {} //"name" : "规格","rows":[{"specificationValeName":"无","goodsSpecificationID":"","id":""}]       
    // }
    var pro_type_thml = $("#pro_type_tmpl").tmpl(db.GoodsSpecificationDetailID);
    var pro_type_null = $(
        '<div class="form-group">'+
            '<label class="col-sm-2 control-label">商品规格</label>'+
            '<div class="col-sm-10">'+
                '<p> '+                
                    '<label class="checkbox-inline">'+
                        '<input type="checkbox" name="goodsSpecificationID" value="" data-name="无">'+
                        '<span class="pro_type_name_val">无</span>'+
                    '</label>'+                    
                '</p>'+
            '</div>'+
        '</div>'+
        '<div class="form-group-separator"></div>'   
    );  
    //console.log(pro_type_thml);     
    $('#pro_type_list').html(pro_type_thml);     //生成产品分类
    $('#pro_type_list').prepend(pro_type_null);

    $("#pro_type_list [type='checkbox']").on("click",function(event) {  //绑定check事件
        //console.log($(this).prop("checked"));
        //$(this).prop("checked",false)
        var thisCheckbox = $(this);      
        proCombination(thisCheckbox);
    });
    /* 生成产品参数 */
    var GoodsParameterIDHhml = $("#GoodsParameterID_tmpl").tmpl(db.GoodsTypeParameterID); 
    //console.log(pro_type_thml);     
    $('#GoodsParameterIDBox').html(GoodsParameterIDHhml);     //生成产品参数
}
var combination = {};
var CheckedValue = {};
//CheckedValue.a = "111";
//console.log($.Object.count.call( CheckedValue));
function proCombination(thisCheckbox){   //生成组合数组    
    
    var thisCheckbox_name = thisCheckbox.attr("name");
    var checkbox_checked = $("[name="+thisCheckbox_name+"]:checked");
    
    if (checkbox_checked.length > 0) {
        combination[thisCheckbox_name] = [];
        for (var i = 0; i < checkbox_checked.length; i++) {
            //var a = [];
            //a[i] = $(checkbox_checked[i]).attr("val");
            var b = {"_value":$(checkbox_checked[i]).val(),"_name":$(checkbox_checked[i]).attr("data-name"),"inputname":$(checkbox_checked[i]).attr("name")}
            combination[thisCheckbox_name][i] = b;
        };
    }  else {
        delete combination[thisCheckbox_name]; 
    }
    var listArray = descartes(combination) ;
     
    //console.log(listArray); 
    /* 输出 组合类型 */
    var listHtml = "";
    for (var i = 0; i < listArray.length; i++) {//console.log(listArray[i]); 
        var valueArray = [];//获取组合value
        var nameArray = [];//获取组合name
        var dataVal = "";
        for(var j = 0 ; j < listArray[i].length ; j++){
            valueArray[j] = listArray[i][j]._value;
            dataVal += listArray[i][j]._value;
            nameArray += "<span>"+listArray[i][j]._name+"</span> - <input type='hidden' name='goodsSpecificationDetailID_"+i+"."+listArray[i][j].inputname+"' value='"+listArray[i][j]._value+"'>";
        }        
        

        listHtml += '<tr>';
        listHtml += '<td><input type="checkbox" name="combination_'+i+'.checkbox"></td>';//data-val="'+dataVal+'" value="'+valueArray+'"
        listHtml += '<td>'+nameArray+'</td>';
        listHtml += '<td><input id="myprice'+i+'" class="myprice" name="typeGroup_'+i+'.price" type="number" placeholder="销售价格"><span style="color:#e74c3c;">*</span></td>';
        listHtml += '<td><input  name="typeGroup_'+i+'.cost" type="number" placeholder="成本价格" value="0"></td>';
        listHtml += '<td><input  name="typeGroup_'+i+'.mediaPrice" type="number" placeholder="媒体价格" value="0"></td>';
        listHtml += '<td><input  name="typeGroup_'+i+'.marketprice" type="number" placeholder="市场价格" value="0"></td>';
        listHtml += '<td><div class="imgBox">';
        listHtml += '<div id="fileList_s'+i+'" imgtype="thumbnailsPicture" class="uploader-list fileList fileList'+i+'"></div>';
        listHtml += '<div id="filePicker_s'+i+'" class="filePicker filePicker'+i+'">选择图片</div></div></td>';
        listHtml += '<td><div class="imgBox">';
        listHtml += '<div id="fileList'+i+'" imgtype="Picture" class="uploader-list fileList fileList'+i+'"></div>';
        listHtml += '<div id="filePicker'+i+'" class="filePicker filePicker'+i+'">选择图片</div></div></td>';
        listHtml += '<td><input id="my_MaterialNumber'+i+'" class="" name="combination_'+i+'.lenovoMaterialNumber" type="text" placeholder="联想物料编号"><span style="color:#e74c3c;">*</span></td>';
        listHtml += '<td><input id="my_factory'+i+'" class="" name="combination_'+i+'.factory" type="text" placeholder="工厂"><span style="color:#e74c3c;">*</span></td>';
        listHtml += '<td><input id="my_warehouse'+i+'" class="" name="combination_'+i+'.warehouse" type="text" placeholder="库存地"><span style="color:#e74c3c;">*</span></td>';
        listHtml += '<td><input id="my_salesOrganization'+i+'" class="" name="combination_'+i+'.salesOrganization" type="text" placeholder="销售组织"><span style="color:#e74c3c;">*</span></td>';
        listHtml += '<td><select class="form-control" name="combination_'+i+'.isGift" placeholder="是否赠品"><option value="0">否</option><option value="1">是</option></select></td>';
        listHtml += '<td><select class="form-control" name="combination_'+i+'.isPhysical" placeholder="是否实物"><option value="0">否</option><option value="1">是</option></select></td>';
        listHtml += '<td><select class="form-control" name="combination_'+i+'.isStockSeperate" placeholder="是否分平台"><option value="0">否</option><option value="1">是</option></select></td>';
        listHtml += '</tr>';
    };
    $("#pro_Combination_list table tbody").html(listHtml);

    $("#pro_Combination_list table tbody").find("[type='checkbox']").each(function(index, el) {
        var _thisvalue = $(this).attr("data-val");
        //console.log(CheckedValue[_thisvalue]);
        //console.log(CheckedValue.length);
        var CheckedValue_length = $.Object.count.call( CheckedValue);//获取对象的长度
        for(var i = 0 ; i < CheckedValue_length ; i++){      
            if (_thisvalue == CheckedValue[_thisvalue]) {
                $(this).prop("checked",true);
            };
        }        
    });

    $("#myprice0").rules("add",{required: true,number:true});

    //初始化多图上传
    var dom = $(".imgBox");
    for(var i =  0 ; i < dom.length ; i++){
        new UploaderImg({
            fileBox : $("#fileList"+i),
            btn     : $("#filePicker"+i)
        })
        new UploaderImg({
            multiple : false,
            fileNumLimit :1,
            fileBox : $("#fileList_s"+i),
            btn     : {"id":$("#filePicker_s"+i),"multiple":false}
        })
    } 
    $(".filePicker").find('div').css({"width":"100%","height":"38px"});
    //绑定checkbox点击事件
    doChecked($("#pro_Combination_list table tbody").find("[type='checkbox']"));
}
function doChecked(checkbox){
    //console.log(checkbox.prop("checked"))
    var check_rules = {};
    checkbox.click(function(event) {
        var click_number = $("[name^='combination']:checked").length;
        $(checkbox).each(function(index, el) {            
            if($(this).prop("checked")){
                var c = $(this).attr("data-val");
                CheckedValue[c] = c;
                //console.log(CheckedValue[c]);
                
                var myprice_index = $(this).parents("tr").index();
                //var myprice_class = $(this).parents("tr").find('.myprice').addClass("myprice" + myprice_index);
                $("#myprice" + myprice_index).rules("add",{required: true,number:true});
                $("#my_MaterialNumber" + myprice_index).rules("add",{required: true});
                $("#my_factory" + myprice_index).rules("add",{required: true});
                $("#my_warehouse" + myprice_index).rules("add",{required: true});
                $("#my_salesOrganization" + myprice_index).rules("add",{required: true});
                //click_number++;
            }else{
                var myprice_index = $(this).parents("tr").index();
                //var myprice_class = $(this).parents("tr").find('.myprice').addClass("myprice" + myprice_index);
                if($("#myprice" + myprice_index).attr("aria-required")){
                    $("#myprice" + myprice_index).rules("remove");
                    $("#my_MaterialNumber" + myprice_index).rules("remove");
                    $("#my_factory" + myprice_index).rules("remove");
                    $("#my_warehouse" + myprice_index).rules("remove");
                    $("#my_salesOrganization" + myprice_index).rules("remove");
                }                
                //click_number--;
            }           
            //console.log($(this).prop("checked"));
        });
        if(click_number == 0){
            $("#myprice0").rules("add",{required: true,number:true});
            $("#my_MaterialNumber0").rules("add",{required: true,number:true});
            $("#my_factory0").rules("add",{required: true});
            $("#my_warehouse0").rules("add",{required: true});
            $("#my_salesOrganization0").rules("add",{required: true});
        }else if(click_number != 0 && $("[name='combination_0.checkbox']").prop("checked") == false){
            $("#myprice0").rules("remove");
            $("#my_MaterialNumber0").rules("remove");
            $("#my_factory0").rules("remove");
            $("#my_warehouse0").rules("remove");
            $("#my_salesOrganization0").rules("remove");
        }
        // for(var i = 0 ; i < checkbox.length ; i++){
        //     console.log($(checkbox[i]).prop("checked"));
        // }
    });
    
    //console.log($(checkbox[0]).prop("checked"));
}
// $(function(){
// 	// pro_typeCallback(proClass);
// 	$("#proType_select").change(function(event) {
//         if($(this).val()==0){
//             return 
//         }
// 		var url  = $(this).attr("_url"); 
//         var data = $(this).val();  
//         pro_typeCallback(proClass);     
// 		//myAjax(url,pro_typeCallback,data);
// 	});
// })
/*=============== 生成笛卡尔积 end ================*/




// 验证规则
var my_rules = {
    //"typeGroup_0.price":{required: true,number:true},
    name:{required: true},
    GoodsCategoryID_name:{required: true},
    goodsTypeID:{required: true},
    productGroupID:{required: true},
    channelNumber:{required: true},
    orderReason:{required: true},
    salesType:{required: true},
    unit:{required: true}
    //weight:{required: true,number:true} //typeGroup_0.price
    
};
var rules_add = my_rules;
var rules_edit = my_rules;
$(function(){
    $("#saveGoodsInfoesIframe").validate({
        rules: rules_add,
        //debug:true  
        onsubmit:true,// 是否在提交时验证 
        submitHandler: function(form) {  //通过之后回调 
            save_goodsInfoesSubmit('#saveGoodsInfoesIframe');
        },
        invalidHandler: function(form, validator) {  //不通过回调 
            showPop("请填写必填信息！",1) 
        } 
    })
    $("#goodsInfoesSubmitForm").validate({
        rules: rules_edit,
        //debug:true  
        onsubmit:true,// 是否在提交时验证 
        submitHandler: function(form) {  //通过之后回调 
            goodsInfoesSubmit('#goodsInfoesSubmitForm');
        },
        invalidHandler: function(form, validator) {  //不通过回调 
            showPop("请填写必填信息！",1) 
        } 
    })
    pageLoad();
    loadFA ();
})

function pageLoad(){
    $.ajax({
        async: false, 
        url: hostURL + "/queryData/queryInitData", 
        type: "GET", 
        dataType: 'jsonp', 
        //jsonp的值自定义,如果使用jsoncallback,那么服务器端,要返回一个jsoncallback的值对应的对象. 
        jsonp: 'jsoncallback', 
        //要传递的参数,没有传参时，也一定要写上 
        data: null, 
        timeout: 5000, 
        //返回Json类型 
        contentType: "application/json;utf-8", 
        //服务器段返回的对象包含name,data属性. 
        success: function (result) { 
            var basInfoHtml = $("#basInfo_html").tmpl(result);
            $(".basInfo").append(basInfoHtml);//基本信息

            var a = result.GoodsCategoryID.onelevels;
            var makeTree = forTree(a);
            $(".wrapGroup").append(makeTree);//树型下拉
            treeClick();

            var makeTreegrid = forTreegrid(a);
            $("#allcate tbody").append(makeTreegrid);//树型表格
            $('#allcate').treegrid({
                "initialState":"collapsed", //初始化折叠所有的节点
                "treeColumn" : 1
            });

            var topInfoHtml = $("#topInfo_html").tmpl(result);
            var topInfoHtml2 = $("#topInfo_html").tmpl(result);
            $("#GoodsTypeIDBox").html(topInfoHtml);
            $("#GoodsTypeIDBox2").html(topInfoHtml2);

            $("#GoodsTypeIDBox .GoodsTypeIDselect").change(function(event) {
                if($(this).val()==0){
                    return 
                }
                combination = {};
                CheckedValue = {};
                var url  = '/querySpecAndParam/queryByGoodsTypeID'; 
                var data = "param="+$(this).val();  
                //pro_typeCallback(proClass);     
                myAjax(url,pro_typeCallback,data);
                $(".pro_Combination_list").find("tbody").html("");
            });
            $("#GoodsTypeIDBox2 .GoodsTypeIDselect").change(function(event) {
                if($(this).val()==0){
                    return 
                }
                combination2 = {};
                CheckedValue2 = {};
                var url  = '/querySpecAndParam/queryByGoodsTypeID'; 
                var data = "param="+$(this).val();  
                //pro_typeCallback(proClass);     
                myAjax(url,pro_typeCallback2,data);
                $(".pro_Combination_list").find("tbody").html("");
            });

            select_change_addData(".productGroupID_select",productGroupID_changed);
        },
        error:function(){
            alert("请求出错了……")
        }
    })  
}
function treeClick(){
    var _target = $(".select-product-cate"),
        _sBtn = _target.find(".sp-btn"),
        _proWrap = _target.find(".sidebar-menu"),
        _options = _proWrap.find("li");

    _sBtn.click(function(){
        _proWrap.toggle();
    });

    _options.find("a").click(function(){
        var _child = $(this).parent().find("ul");

        $(this).parent().addClass("active");
        $(this).parent().siblings().removeClass("active");

        if(_child.length==0){

            var _content = $(this).find("span").html(),
                _cateId = $(this).find("span").attr("dataid"),  
                _datacode = $(this).find("span").attr("datacode");           

            $(this).parents(".proClass_box").find("[name='GoodsCategoryID_name']").val(_content);
            $(this).parents(".proClass_box").find("[name='goodsCategoryID']").val(_cateId);
            $(this).parents(".proClass_box").find("[name='categoryCode']").val(_datacode);
            if ($(this).parents(".o_categoryCode").length>0) {
                $(".proaddClass_box").show();
            }else{
                $(".proaddClass_box").hide();
            };
            _proWrap.hide();
        }else{
            $(this).parent("li").toggleClass('expanded');
            $(this).parents('ul').siblings().find('ul').hide();
            $(this).siblings('ul').toggle();
        }
    });
}

// 新建商品
function addPro(){
    $("#proTab_1").show().addClass('active').siblings().removeClass('active');
    $("#tab-1").addClass('active').siblings().removeClass('active');
}
// 商品详情 - 修改（标签切换）
function editPro(){
    $("#proTab_3").show().addClass('active').siblings().removeClass('active');
    $("#tab-3").addClass('active').siblings().removeClass('active');
};
// 修改 - 详情 - 赋值
function proEdit(db){
    var ajaxInfo = {
        "url":hostURL + "/goodsInfo/queryGoodsDetail",
        "dataType":"jsonp",
        "jsonp":"jsoncallback",
        "data":{"id":db}, //783a60e8-1792-44e5-8119-718d1abb6fcc //"ea26a885-1e8e-487b-b976-95836c28f2ff"
        "callback":makeForm
    }
    ajaxQuery.ajax(ajaxInfo);
    //alert(json);  
}
// 修改详情 - 表单赋值
function makeForm(db){
    var myjson = db.datas.goodsinfoes;
    var my_infotagrel = db.datas.goodsinfotagrel; //自定标签
    var my_categoryrel = db.datas.GoodsInfoCustomCategoryRel; //自定义分类

    for (var i = 0; i < my_infotagrel.length; i++) {
        var check = my_infotagrel[i].GoodsInfoTagID;
        $("[value="+check+"]").prop("checked",true);
    };
    for (var i = 0; i < my_categoryrel.length; i++) {
        var check = my_categoryrel[i].GoodsInfoCustomCategoryID;
        $("[value="+check+"]").prop("checked",true);
    };

    console.log(db);
    $("#tab-3 #basInfo [name]").each(function(index, el) {//基本信息
        //console.log(this.type);
        if(this.type == "checkbox"){
            
        }else{
            console.log($(this).attr("name"));
            var name = $(this).attr("name");
            console.log(myjson[name]);
            $(this).val(myjson[name]);
        }        
    });
    $("#tab-3 #proInfobox [name]").each(function(index, el) {//商品信息
        console.log($(this).attr("name"));
        var name = $(this).attr("name");
        console.log(myjson[name]);
        $(this).val(myjson[name]);
    });
    //分类赋值
    var category = $("#GoodsCategoryID").val();
    var categoryText = $("span[dataid="+category+"]").text()
    $("#GoodsCategoryID_name").val("categoryText");
}
// 生成商品列表 - 查询
var pageTotalNum = 0 ;
function pro_listQuery(db,curPage){
    var OPT = {
        mydb:"datas",
        boxID:"#prolistTable tbody",
        tmplID:"#prolistQuery_tmpl"
    };
    var callback = function(db){
        console.log(db);
        var _db = db;
        OPT.mydb = db[OPT.mydb];
        makeTMPL(OPT);
        $("#pageTotalNum span").text(db.pageTotalNum);
        pageTotalNum = db.pageTotalNum;
        $(".fa-pencil").click(function(event) {
            var proNo = $(this).parents("tr").find('[type="checkbox"]').val();            
            editPro();
            proEdit(proNo);
            proDetailsQuery(proNo);            
        });
        $(".fa-copy").click(function(event) {
            var proNo = $(this).parents("tr").find('[type="checkbox"]').val();            
            copyPro();
            proCopy(proNo);
            proDetailsCopy(proNo);            
        });
        // $(".fa-times").click(function(event) {
        //     var proNo = $(this).parents("tr").find('[type="checkbox"]').val();            
        //     editPro();
        //     proEdit(proNo);
        //     proDetailsQuery(proNo);            
        // });
        // //fa-times
    };
    var ajaxInfo = {
        "url":hostURL + "/goodsInfo/pageQuery?curPage="+curPage+"&pageSize=10&",// queryData/queryForListByOptions?tableName=goodsinfoes&
        "dataType":"jsonp",
        "jsonp":"jsoncallback",
        "data":db,
        //"url":"learn.json",
        "callback":callback
    }    
    ajaxQuery.ajax(ajaxInfo);
    //alert(json);  
}


// 生成商品详情
function proDetailsQuery(db){
    var OPT = {
        mydb:"GoodsInfoParameters",
        boxID:"#GoodsParameterIDBox2",
        tmplID:"#GoodsParameterID_tmpl"
    };
    var OPT2 = {
        mydb:"GoodsInfoServiceCategoryRels",
        boxID:"#allcate_edit_box",
        tmplID:"#GoodsInfoServiceCategoryRels_tmpl"
    };
    var callback = function(db){
        //console.log(this);
        var _db = db;
        OPT.mydb = db.datas[OPT.mydb];
        makeTMPL(OPT);
        OPT2.mydb = db.datas[OPT2.mydb];
        makeTMPL(OPT2);
        proInfospecifications(db);
        $("#proID").val(_db.datas.goodsinfoes.id);
        $("#wuliaoID").val(_db.datas.goodsmaterials.id);
    };
    var ajaxInfo = {
        "url":hostURL + "/goodsInfo/queryGoodsDetail",
        "data":{"id":db}, //783a60e8-1792-44e5-8119-718d1abb6fcc //"ea26a885-1e8e-487b-b976-95836c28f2ff"
        "dataType":"jsonp",
        "jsonp":"jsoncallback",
        "callback":callback        
    }    
    ajaxQuery.ajax(ajaxInfo);
    //alert(json);  
}

// 生成商品详情 - 规格组合 - 物料信息
function proInfospecifications(db){
    var myjson_specs = db.datas.GoodsInfoSpecifications;
    var myjson_materials = db.datas.goodsmaterials;
    var myjson_goodsinfoes = db.datas.goodsinfoes;
    var myjson_pictures = db.datas.GoodsInfodetailpictures;
    var myjson_ThumbnailsPicture = db.datas.ThumbnailsPicture;
    var introduction = myjson_goodsinfoes.introduction;

    var listHtml = "";
    
        var valueArray = [];//获取组合value
        var nameArray = [];//获取组合name
        var dataVal = "";
        for(var j = 0 ; j < myjson_specs.length ; j++){
            nameArray += "<span>"+myjson_specs[j].specificationValeName+"</span> - <input type='hidden' name='goodsSpecificationDetailID_"+0+"."+myjson_specs[j].GoodsSpecificationDetailID+"' value='"+myjson_specs[j].id+"'>";
        }        
        

        listHtml += '<tr>';
        listHtml += '<td><input type="checkbox" name="combination_'+0+'.checkbox"></td>';//data-val="'+dataVal+'" value="'+valueArray+'"
        listHtml += '<td>'+nameArray+'</td>';
        listHtml += '<td><input name="typeGroup_'+0+'.price" type="number" placeholder="销售价格" value="'+myjson_goodsinfoes.price+'"><span style="color:#e74c3c;">*</span></td>';
        listHtml += '<td><input name="typeGroup_'+0+'.cost" type="number" placeholder="成本价格" value="'+myjson_goodsinfoes.cost+'"></td>';
        listHtml += '<td><input name="typeGroup_'+0+'.mediaPrice" type="number" placeholder="媒体价格" value="'+myjson_goodsinfoes.mediaPrice+'"></td>';
        listHtml += '<td><input name="typeGroup_'+0+'.marketprice" type="number" placeholder="市场价格" value="'+myjson_goodsinfoes.marketprice+'"></td>';
        
        listHtml += '<td><div class="imgBox">';
        listHtml += '<div id="fileList_s'+100+'" imgtype="thumbnailsPicture" class="uploader-list fileList">';
        if (myjson_ThumbnailsPicture) {
            //for (var i = 0; i < myjson_ThumbnailsPicture.length; i++) {           
                listHtml +=     '<div id="" class="file-item thumbnail">';
                listHtml +=         '<a class="removeImg" href="javascript:;">移除</a>';
                listHtml +=         '<img src="'+myjson_ThumbnailsPicture.path+'" width="100">';
                listHtml +=         '<input name="picture_0.ThumbnailsPicture,WU_FILE_0" type="hidden" value="'+myjson_ThumbnailsPicture.id+'">';
                listHtml +=         '<div class="info">'+myjson_ThumbnailsPicture.fileName+'</div>';
                listHtml +=     '</div>';
            //};
        };                
        listHtml += '</div>';
        listHtml += '<div id="filePicker_s'+100+'" class="filePicker">选择图片</div></div></td>';

        listHtml += '<td><div class="imgBox">';
        listHtml += '<div id="fileList'+99+'" class="uploader-list fileList">';
        if (myjson_pictures) {
            for (var i = 0; i < myjson_pictures.length; i++) {           
                listHtml +=     '<div id="WU_FILE_0" class="file-item thumbnail">';
                listHtml +=         '<a class="removeImg" href="javascript:;">移除</a>';
                listHtml +=         '<img src="'+myjson_pictures[i].Path+'" width="100">';
                listHtml +=         '<input name="picture_0.WU_FILE_0" type="hidden" value="'+myjson_pictures[i].PictureID+'">';
                listHtml +=         '<div class="info">'+myjson_pictures[i].FileName+'</div>';
                listHtml +=     '</div>';
            };
        };                
        listHtml += '</div>';
        listHtml += '<div id="filePicker'+99+'" class="filePicker">选择图片</div></div></td>';
        listHtml += '<td><input class="form-control" name="combination_'+0+'.lenovoMaterialNumber" type="text" placeholder="联想物料编号" value="'+myjson_materials.lenovoMaterialNumber+'"><span style="color:#e74c3c;">*</span></td>';
        listHtml += '<td><input class="form-control" name="combination_'+0+'.factory" type="text" placeholder="工厂" value="'+myjson_materials.factory+'"><span style="color:#e74c3c;">*</span></td>';
        listHtml += '<td><input class="form-control" name="combination_'+0+'.warehouse" type="text" placeholder="库存地" value="'+myjson_materials.warehouse+'"><span style="color:#e74c3c;">*</span></td>';
        listHtml += '<td><input class="form-control" name="combination_'+0+'.salesOrganization" type="text" placeholder="销售组织" value="'+myjson_materials.salesOrganization+'"><span style="color:#e74c3c;">*</span></td>';
        listHtml += '<td><select class="form-control" name="combination_'+0+'.isGift" placeholder="是否赠品"><option value="0">否</option><option value="1">是</option></select></td>';
        listHtml += '<td><select class="form-control" name="combination_'+0+'.isPhysical" placeholder="是否实物"><option value="0">否</option><option value="1">是</option></select></td>';
        listHtml += '<td><select class="form-control" name="combination_'+0+'.isStockSeperate" placeholder="是否分平台"><option value="0">否</option><option value="1">是</option></select></td>';
        listHtml += '</tr>';

    $("#pro_Combination_list2 table tbody").html(listHtml);
    $("#pro_Combination_list2 table tbody").find('[name$="isGift"]').val(myjson_materials.isGift);
    $("#pro_Combination_list2 table tbody").find('[name$="isPhysical"]').val(myjson_materials.isPhysical);
    $("#pro_Combination_list2 table tbody").find('[name$="isStockSeperate"]').val(myjson_materials.isStockSeperate);
    $("#pro_Combination_list2 table tbody").find('[type="checkbox"]').prop("checked",true);
    setTimeout(function(){
        new UploaderImg({
            fileBox : $("#fileList"+99),
            btn     : $("#filePicker"+99)
        })
        $("#filePicker"+99).find('div').css({"width":"100%","height":"38px"});
        new UploaderImg({
            multiple : false,
            fileNumLimit :1,
            fileBox : $("#fileList_s"+100),
            btn     : {"id":$("#filePicker_s"+100),"multiple":false}
        })
        $("#filePicker_s"+100).find('div').css({"width":"100%","height":"38px"});
        $(".removeImg").on("click",this,function(event) {
            uploader.removeFile( file );
            $(this).parents(".file-item").remove();
            console.log(uploader.removeFile)
        });
    }, 100) ;
    ue2.setContent(introduction);
}


/* 新建 - 提交 */
function save_goodsInfoesSubmit(el){    

    var my_serialize = $(el).serialize();

    var callback = function(db){
        //console.log(this);
        //alert("message");
        console.log(db);
    };
    var ajaxInfo = {
        "url":hostURL + "/goodsInfo/saveGoodsInfoes",
        "type":"POST",
        "data":my_serialize,
        "dataType":"jsonp",
        "jsonp":"jsoncallback",
        "callback":callback        
    }    
    ajaxQuery.ajax(ajaxInfo);
}
/* 修改 - 提交 */
function goodsInfoesSubmit(el){    

    var my_serialize = $(el).serialize();

    var callback = function(db){
        //console.log(this);
        //alert("message");
        console.log(db);
    };
    var ajaxInfo = {
        "url":hostURL + "/goodsInfo/modifyGoodsInfoes",
        "type":"POST",
        "data":my_serialize,
        "dataType":"jsonp",
        "jsonp":"jsoncallback",
        "callback":callback        
    }    
    ajaxQuery.ajax(ajaxInfo);
}
/* 加载FA列表 */
function loadFA (){
    var OPT = {
        mydb:"datas",
        boxID:"#FAsearch",
        tmplID:"#FAselect_html"
    };
    var callback = function(db){
        //console.log(this);
        OPT.mydb = db[OPT.mydb];
        makeTMPL(OPT); 
        $("#FAsearch").prepend('<option value="">全部</option>');       
    };
    var ajaxInfo = {
        "url":hostURL + "/faBaseInfo/queryFaBaseInfo",
        "type":"POST",
        //"data":{"id":db}, //783a60e8-1792-44e5-8119-718d1abb6fcc //"ea26a885-1e8e-487b-b976-95836c28f2ff"
        "dataType":"jsonp",
        "jsonp":"jsoncallback",
        "callback":callback        
    }    
    ajaxQuery.ajax(ajaxInfo);
}
/* 绑定fa */
function faIstied(){
    var isfaIstied = 0;
    var mytr = checkSubmit("#prolistTable");
    $(mytr).find('.td_faname').each(function(index, el) {
        if($(this).html != ""){
            isfaIstied = 1;
            return;
        }
    });
    if (isfaIstied) {
        alert("存在已绑定FA的商品~！");
        return isfaIstied;
    };
    
} 
function fabangding(){
    var OPT = {
        mydb:"datas",
        boxID:"#FAselect",
        tmplID:"#FAselect_html"
    };
    var callback = function(db){
        //console.log(this);
        OPT.mydb = db[OPT.mydb];
        makeTMPL(OPT);        
    };
    var ajaxInfo = {
        "url":hostURL + "/faBaseInfo/queryFaBaseInfo",
        "type":"POST",
        //"data":{"id":db}, //783a60e8-1792-44e5-8119-718d1abb6fcc //"ea26a885-1e8e-487b-b976-95836c28f2ff"
        "dataType":"jsonp",
        "jsonp":"jsoncallback",
        "callback":callback        
    }    
    ajaxQuery.ajax(ajaxInfo);
    var mytr = checkSubmit("#prolistTable");
    $("#FAbox").find('tbody').html(mytr);
    $("#FAbox").find('.re_FA').remove();
    $("#FAbox").find('[type="checkbox"]').prop("checked",true);    
}

/* 提交审核 */
function isReviewed(){//判断是否提交过
    var is_reviewed = 0;
    var mytr = checkSubmit("#prolistTable");
    $(mytr).find('[name="status"]').each(function(index, el) {
        if($(this).val() != 0){
            is_reviewed = 1;
            return;
        }
    });
    if (is_reviewed) {
        alert("存在提交过审核的商品~！");
        return is_reviewed;
    };
}
function gotoReview(){
   var mytr = checkSubmit("#prolistTable");
    $("#gotoReview_box").find('tbody').html(mytr);
    $("#gotoReview_box").find('.re_FA').remove();
    $("#gotoReview_box").find('[type="checkbox"]').prop("checked",true);  
}
$(function(){
    $("#FA_OK").click(function(event) {
        var my_serialize = $("#FA_form").serialize();
        var callback = function(db){
            //console.log(this);
            //alert("message");
            console.log(db);
            mySerialize('#formQuery',pro_listQuery);
        };
        var ajaxInfo = {
            "url":hostURL + "/faBaseInfo/saveFaGoodsRel",
            "data":my_serialize,
            "dataType":"jsonp",
            "jsonp":"jsoncallback",
            "callback":callback        
        }    
        ajaxQuery.ajax(ajaxInfo);
    });
    $("#gotoReview_OK").click(function(event) {
        var my_serialize = $("#gotoReview_form").serialize();
        var callback = function(db){
            //console.log(this);
            //alert("message");
            console.log(db);
            mySerialize('#formQuery',pro_listQuery);
        };
        var ajaxInfo = {
            "url":hostURL + "/goodsInfo/saveSubChange",
            "data":my_serialize,
            "dataType":"jsonp",
            "jsonp":"jsoncallback",
            "callback":callback        
        }    
        ajaxQuery.ajax(ajaxInfo);
    });
})

//服务商品添加分类
function services_add(){
    var mytr = checkSubmit("#allcate");
    $("#allcate_add").find('tbody').html(mytr);
    $("#allcate_edit").find('tbody').html(mytr);
    //$("#allcate_add").find('.re_FA').remove();
    $("#allcate_add").find('[type="checkbox"]').prop("checked",true);
    $("#allcate_edit").find('[type="checkbox"]').prop("checked",true);
}

// 商品复制
function goodsCopy(){
    var _checked = $("#prolistTable").find('[type="checkbox"]:checked');    
    if (_checked.length == 1) {
        var data_goodsCopy = {}
        data_goodsCopy.id = $(_checked[0]).val();
        console.log(data_goodsCopy);

        var callback = function(db){
            console.log(db);
        };
        var ajaxInfo = {
            "url":hostURL + "/goodsInfo/copyOneGoodsinfoes",
            "type":"POST",
            "data":data_goodsCopy,
            "dataType":"jsonp",
            "jsonp":"jsoncallback",
            "callback":callback        
        }    
        ajaxQuery.ajax(ajaxInfo);
    }else{
        alert("同时只能复制一条商品~！");
    };
}

// 判断 THINK 产品组
function productGroupID_changed(selectID,this_data){
    if(this_data == 84 || this_data == 85 || this_data == 86){//84、85、86为think产品组
        //alert("我是think产品组");
        thinkIrrelevant();
    }else{
        //alert("我不是think产品组");
        remove_thinkIrrelevant();
    }
}
function thinkIrrelevant (){
    $("[type='text'][thinkIrrelevant]").val("（think无关）");
    $("select[thinkIrrelevant]").val(-1);
}
function remove_thinkIrrelevant (){
    if($("[type='text'][thinkIrrelevant]").val() == "（think无关）"){
        $("[type='text'][thinkIrrelevant]").val("");
    }
    if($("select[thinkIrrelevant]").val() == -1){
        $("select[thinkIrrelevant]").val("");
    }    
}

//导出  Excel 10.113.219.128:8080/download/productExcel
function makeExcel(el){
    //var _db = $(el).serialize();
    var url = hostURL + "/download/goodsExcel?";
    //var pageN = "&curPage=" + parseInt($("#pageNo").val());
    //var action = url + _db;
    var _nameLike = $(el).find("[name='nameLike']").val();
    $("#formQuery_excel").find("[name='nameLike']").val(_nameLike);

    var _code = $(el).find("[name='code']").val();
    $("#formQuery_excel").find("[name='code']").val(_code);

    var _materialNo = $(el).find("[name='materialNo']").val();
    $("#formQuery_excel").find("[name='materialNo']").val(_materialNo);

    var _faId = $(el).find("[name='faId']").val();
    $("#formQuery_excel").find("[name='faId']").val(_faId);

    var _curPage = $("#pageNo").val();
    $("#formQuery_excel").find("[name='curPage']").val(_curPage);

    //console.log(action);
    $("#formQuery_excel").attr("action",url).submit();;
    //$("#formQuery_excel")
}