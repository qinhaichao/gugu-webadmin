
/*=============== 生成笛卡尔积 ================*/
function pro_typeCallback2(db){    //选择产品类型后 - 回调函数 - 生成产品分类

    if(db.GoodsSpecificationDetailID.length<=0){
        db.GoodsSpecificationDetailID = {} //"name" : "规格","rows":[{"specificationValeName":"无","goodsSpecificationID":"","id":""}]       
    }
    var pro_type_thml = $("#pro_type_tmpl").tmpl(db.GoodsSpecificationDetailID); 
    //console.log(pro_type_thml);  
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
    $('#pro_type_list2').html(pro_type_thml);     //生成产品分类
    $('#pro_type_list2').prepend(pro_type_null);

    $("#pro_type_list2 [type='checkbox']").on("click",function(event) {  //绑定check事件
        //console.log($(this).prop("checked"));
        //$(this).prop("checked",false)
        var thisCheckbox = $(this);      
        procombination2(thisCheckbox);
    });
}
var combination2 = {};
var CheckedValue2 = {};
//CheckedValue2.a = "111";
//console.log($.Object.count.call( CheckedValue2));
function procombination2(thisCheckbox){   //生成组合数组    

    var thisCheckbox_name = thisCheckbox.attr("name");
    var checkbox_checked = $("[name="+thisCheckbox_name+"]:checked");
    
    if (checkbox_checked.length > 0) {
        combination2[thisCheckbox_name] = [];
        for (var i = 0; i < checkbox_checked.length; i++) {
            //var a = [];
            //a[i] = $(checkbox_checked[i]).attr("val");
            var b = {"_value":$(checkbox_checked[i]).val(),"_name":$(checkbox_checked[i]).attr("data-name"),"inputname":$(checkbox_checked[i]).attr("name")}
            combination2[thisCheckbox_name][i] = b;
        };
    }  else {
        delete combination2[thisCheckbox_name]; 
    }
    var listArray = descartes(combination2) ;
     
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
        listHtml += '<td><input type="radio" name="combination_'+0+'.checkbox"></td>';//data-val="'+dataVal+'" value="'+valueArray+'"
        listHtml += '<td>'+nameArray+'</td>';
        listHtml += '<td><input id="myprice'+i+'" name="typeGroup_'+i+'.price" type="number" placeholder="销售价格"><span style="color:#e74c3c;">*</span></td>';
        listHtml += '<td><input name="typeGroup_'+i+'.cost" type="number" placeholder="成本价格" value="0"></td>';
        listHtml += '<td><input name="typeGroup_'+i+'.mediaPrice" type="number" placeholder="媒体价格" value="0"></td>';
        listHtml += '<td><input name="typeGroup_'+i+'.marketprice" type="number" placeholder="市场价格" value="0"></td>';
        listHtml += '<td><div class="imgBox">';
        listHtml += '<div id="fileList_bs'+i+'" imgtype="thumbnailsPicture" class="uploader-list fileList fileList'+i+'"></div>';
        listHtml += '<div id="filePicker_bs'+i+'" class="filePicker filePicker'+i+'">选择图片</div></div></td>';
        listHtml += '<td><div class="imgBox">';
        listHtml += '<div id="fileList_b'+i+'" imgtype="Picture" class="uploader-list fileList fileList'+i+'"></div>';
        listHtml += '<div id="filePicker_b'+i+'" class="filePicker filePicker'+i+'">选择图片</div></div></td>';
        listHtml += '<td><input id="my_MaterialNumber'+i+'" class="" name="combination_'+i+'.lenovoMaterialNumber" type="text" placeholder="联想物料编号"><span style="color:#e74c3c;">*</span></td>';
        listHtml += '<td><input id="my_factory'+i+'" class="" name="combination_'+i+'.factory" type="text" placeholder="工厂"><span style="color:#e74c3c;">*</span></td>';
        listHtml += '<td><input id="my_warehouse'+i+'" class="" name="combination_'+i+'.warehouse" type="text" placeholder="库存地"><span style="color:#e74c3c;">*</span></td>';
        listHtml += '<td><input id="my_salesOrganization'+i+'" class="" name="combination_'+i+'.salesOrganization" type="text" placeholder="销售组织"><span style="color:#e74c3c;">*</span></td>';
        listHtml += '<td><select class="form-control" name="combination_'+i+'.isGift" placeholder="是否赠品"><option value="0">否</option><option value="1">是</option></select></td>';
        listHtml += '<td><select class="form-control" name="combination_'+i+'.isPhysical" placeholder="是否实物"><option value="0">否</option><option value="1">是</option></select></td>';
        listHtml += '<td><select class="form-control" name="combination_'+i+'.isStockSeperate" placeholder="是否分平台"><option value="0">否</option><option value="1">是</option></select></td>';
        listHtml += '</tr>';
    };
    $("#pro_Combination_list2 table tbody").html(listHtml);

    $("#pro_Combination_list2 table tbody").find("[type='checkbox']").each(function(index, el) {
        var _thisvalue = $(this).attr("data-val");
        //console.log(CheckedValue2[_thisvalue]);
        //console.log(CheckedValue2.length);
        var CheckedValue2_length = $.Object.count.call( CheckedValue2);//获取对象的长度
        for(var i = 0 ; i < CheckedValue2_length ; i++){      
            if (_thisvalue == CheckedValue2[_thisvalue]) {
                $(this).prop("checked",true);
            };
        }        
    });
    //初始化多图上传
    var dom = $(".imgBox");
    for(var i =  0 ; i < dom.length ; i++){
        new UploaderImg({
            fileBox : $("#fileList_b"+i),
            btn     : $("#filePicker_b"+i)
        })
        new UploaderImg({
            multiple : false,
            fileNumLimit :1,
            fileBox : $("#fileList_bs"+i),
            btn     : {"id":$("#filePicker_bs"+i),"multiple":false}
        })
    } 
    $(".filePicker").find('div').css({"width":"100%","height":"38px"});
    //绑定checkbox点击事件
    doChecked2($("#pro_combination_list2 table tbody").find("[type='checkbox']"));
}
function doChecked2(checkbox){
    //console.log(checkbox.prop("checked"))
    
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
                //click_number++;
            }else{
                var myprice_index = $(this).parents("tr").index();
                //var myprice_class = $(this).parents("tr").find('.myprice').addClass("myprice" + myprice_index);
                if($("#myprice" + myprice_index).attr("aria-required")){
                    $("#myprice" + myprice_index).rules("remove");
                    $("#my_MaterialNumber" + myprice_index).rules("remove");
                }                
                //click_number--;
            }           
            //console.log($(this).prop("checked"));
        });
        if(click_number == 0){
            $("#myprice0").rules("add",{required: true,number:true});
            $("#my_MaterialNumber0").rules("add",{required: true,number:true});
        }else if(click_number != 0 && $("[name='combination_0.checkbox']").prop("checked") == false){
            $("#myprice0").rules("remove");
            $("#my_MaterialNumber0").rules("remove");
        }
        // for(var i = 0 ; i < checkbox.length ; i++){
        //     console.log($(checkbox[i]).prop("checked"));
        // }
    });
    
    //console.log($(checkbox[0]).prop("checked"));
}

/*=============== 生成笛卡尔积 end ================*/