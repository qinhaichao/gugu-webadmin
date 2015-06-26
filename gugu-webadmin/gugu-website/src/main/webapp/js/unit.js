// JavaScript Document

/*
*tab : 选项卡
*radioChange : 单选按钮选择。 参数color { "current":"#fafafa","defaut":"#fff" }
*/
$.fn.extend({
	tab : function(evt){
		var head = this.children("ul");
		var cont = this.children("div");
		var evt = evt||"click";
		head.bind(evt,function(e){

			var childs = $(this).getTarget(e);
			if(childs !== this){
				$(childs).addClass("active").siblings().removeClass('active');
				cont.eq($(childs).index()).show().siblings("div").hide();
			}
		})
	},
	getTarget : function(e){
		var nodes = e.target||e.srcElement;
		return nodes;
	},
	showName:function(){
		var arr = [];
		var len = this.length;
		this.each(function(){
			if($(this).attr("showNode")){
				arr.push($(this).attr("showNode"));
			}
		})
		return arr;
	},
	radioChange:function(color){
		var color = color || { "current":"#fafafa","defaut":"#fff" };
		var current = color.current || "#fafafa",
			defaut = color.defaut || "#fff";
		
		var aNode = this.showName();
		var oThis = this;
		this.change(function () {
		    var _this = $(this).parent();
		    var parent = $(this).parent();
		    oThis.each(function (value) {
		       
		        var parent = oThis.eq(value).parent();
		        parent.css("background-color", defaut);
		        _this.css("background-color", current);
		    })
			
				var nodeName = $(this).attr("showNode");
				if(nodeName){
					$("div." + nodeName).show();
				}else {
					$.each(aNode,function(n,value){
						$("div." + value).hide();
					})
				}
		});
	},
	inputDefaut : function(){
		var sDefaut = this.attr("placeholder");
		this.val(sDefaut).css("color","#a9a9a9");
		this.blur(function(){
			if($(this).val()==sDefaut||!$(this).val()){			   
				$(this).val(sDefaut).css("color","#a9a9a9");
			}
		}).focus(function(){
			if($(this).val()==sDefaut||!$(this).val()){
				$(this).val("").removeAttr("style");
			}
		})
	},
	showComment : function(){
	    this.click(function () {
			var oRow = $(this).parent().parent().parent().next();
			var re = /none/i;
			if( re.test(oRow.css("display")) ){
			    oRow.show(200);
			    var _id = 'upload' + new Date().getTime();
			    oRow.find('.uploadBtn').attr('id', _id);
			    oRow.find('.queue').attr('id', _id + '_queue');
			    oRow.find('.imglistdiv').attr('id', _id + '_imgs');
			    SetUpload(_id, _id + '_queue', _id + '_imgs');
			}else{
				oRow.hide(200);
			}
		})
	},
	Score:function(num){
		$.each(this,function(n,value){
			var score =num || $(value).attr("score");
			children = $(value).children();
			$.each(children,function(n,value){
				if(n < score){
					children.eq(n).addClass("true")
				}else{
					children.eq(n).removeClass("true")
				}
			})
		})
	},
	starScore : function(){
		var num = 0;
		var oThis = this;
		$.each(this,function(n,value){
			var children = $(value).children();
			$(value).Score(num);
			children.bind({
				"mouseover":function(){
					oThis.Score($(this).index()+1);
				},
				"click":function(){
					num = $(this).index()+1;
					oThis.attr("score",num);
				},
				"mouseout":function(){
					oThis.Score(num);
				}
			})
		})
	}
})