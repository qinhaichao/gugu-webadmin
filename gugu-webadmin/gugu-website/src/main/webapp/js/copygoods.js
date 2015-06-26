// 商品详情 - 复制（标签切换）
function copyPro(){
    $("#proTab_1").show().addClass('active').siblings().removeClass('active');
    $("#tab-1").addClass('active').siblings().removeClass('active');
};
// 复制 - 详情 - 赋值
function proCopy(db){
    var ajaxInfo = {
        "url":hostURL + "/goodsInfo/queryGoodsDetail",
        "dataType":"jsonp",
        "jsonp":"jsoncallback",
        "data":{"id":db}, //783a60e8-1792-44e5-8119-718d1abb6fcc //"ea26a885-1e8e-487b-b976-95836c28f2ff"
        "callback":copyForm
    }
    ajaxQuery.ajax(ajaxInfo);
    //alert(json);  
}
// 复制详情 - 表单赋值
function copyForm(db){
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
    $("#tab-1 #basInfo_add [name]").each(function(index, el) {//基本信息
        //console.log(this.type);
        if(this.type == "checkbox"){
            
        }else{
            console.log($(this).attr("name"));
            var name = $(this).attr("name");
            console.log(myjson[name]);
            $(this).val(myjson[name]);
        }        
    });
    $("#tab-1 #proInfobox_add [name]").each(function(index, el) {//商品信息
        console.log($(this).attr("name"));
        var name = $(this).attr("name");
        console.log(myjson[name]);
        $(this).val(myjson[name]);
    });
    //分类赋值
    var category = $("#GoodsCategoryID_add").val();
    var categoryText = $("span[dataid="+category+"]").text()
    $("#GoodsCategoryID_name_add").val("categoryText");
}

// 生成商品详情 - 复制
function proDetailsCopy(db){
    var OPT = {
        mydb:"GoodsInfoParameters",
        boxID:"#GoodsParameterIDBox",
        tmplID:"#GoodsParameterID_tmpl"
    };
    var OPT2 = {
        mydb:"GoodsInfoServiceCategoryRels",
        boxID:"#allcate_add",
        tmplID:"#GoodsInfoServiceCategoryRels_tmpl"
    };
    var callback = function(db){
        //console.log(this);
        var _db = db;
        OPT.mydb = db.datas[OPT.mydb];
        makeTMPL(OPT);
        OPT2.mydb = db.datas[OPT2.mydb];
        makeTMPL(OPT2);
        proInfospecifications_copy(db);
        //$("#proID").val(_db.datas.goodsinfoes.id);
        //$("#wuliaoID").val(_db.datas.goodsmaterials.id);
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
function proInfospecifications_copy(db){
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
        listHtml += '<td><input name="typeGroup_'+0+'.price" type="number" placeholder="销售价格" value="'+myjson_goodsinfoes.price+'"></td>';
        listHtml += '<td><input name="typeGroup_'+0+'.cost" type="number" placeholder="成本价格" value="'+myjson_goodsinfoes.cost+'"></td>';
        listHtml += '<td><input name="typeGroup_'+0+'.mediaPrice" type="number" placeholder="媒体价格" value="'+myjson_goodsinfoes.mediaPrice+'"></td>';
        listHtml += '<td><input name="typeGroup_'+0+'.marketprice" type="number" placeholder="市场价格" value="'+myjson_goodsinfoes.marketprice+'"></td>';
        
        listHtml += '<td><div class="imgBox">';
        listHtml += '<div id="fileList_s'+101+'" imgtype="thumbnailsPicture" class="uploader-list fileList">';
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
        listHtml += '<div id="filePicker_s'+101+'" class="filePicker">选择图片</div></div></td>';

        listHtml += '<td><div class="imgBox">';
        listHtml += '<div id="fileList'+199+'" class="uploader-list fileList">';
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
        listHtml += '<div id="filePicker'+199+'" class="filePicker">选择图片</div></div></td>';
        listHtml += '<td><input class="form-control" name="combination_'+0+'.lenovoMaterialNumber" type="text" placeholder="联想物料编号" value="'+myjson_materials.lenovoMaterialNumber+'"></td>';
        listHtml += '<td><input class="form-control" name="combination_'+0+'.factory" type="text" placeholder="工厂" value="'+myjson_materials.factory+'"></td>';
        listHtml += '<td><input class="form-control" name="combination_'+0+'.warehouse" type="text" placeholder="库存地" value="'+myjson_materials.warehouse+'"></td>';
        listHtml += '<td><input class="form-control" name="combination_'+0+'.salesOrganization" type="text" placeholder="销售组织" value="'+myjson_materials.salesOrganization+'"></td>';
        listHtml += '<td><select class="form-control" name="combination_'+0+'.isGift" placeholder="是否赠品"><option value="0">否</option><option value="1">是</option></select></td>';
        listHtml += '<td><select class="form-control" name="combination_'+0+'.isPhysical" placeholder="是否实物"><option value="0">否</option><option value="1">是</option></select></td>';
        listHtml += '<td><select class="form-control" name="combination_'+0+'.isStockSeperate" placeholder="是否分平台"><option value="0">否</option><option value="1">是</option></select></td>';
        listHtml += '</tr>';

    $("#pro_Combination_list table tbody").html(listHtml);
    $("#pro_Combination_list table tbody").find('[name$="isGift"]').val(myjson_materials.isGift);
    $("#pro_Combination_list table tbody").find('[name$="isPhysical"]').val(myjson_materials.isPhysical);
    $("#pro_Combination_list table tbody").find('[name$="isStockSeperate"]').val(myjson_materials.isStockSeperate);
    $("#pro_Combination_list table tbody").find('[type="checkbox"]').prop("checked",true);
    setTimeout(function(){
        new UploaderImg({
            fileBox : $("#fileList"+199),
            btn     : $("#filePicker"+199)
        })
        $("#filePicker"+199).find('div').css({"width":"100%","height":"38px"});
        new UploaderImg({
            multiple : false,
            fileNumLimit :1,
            fileBox : $("#fileList_s"+101),
            btn     : {"id":$("#filePicker_s"+101),"multiple":false}
        })
        $("#filePicker_s"+101).find('div').css({"width":"100%","height":"38px"});
        $(".removeImg").on("click",this,function(event) {
            uploader.removeFile( file );
            $(this).parents(".file-item").remove();
            console.log(uploader.removeFile)
        });
    }, 100) ;
    ue.setContent(introduction);
}