 //列表没有内容，调用此HTML
function ShowNoDate(NoDateTips, type) { 
    var NoDateHTML = '<div class="prompt"><h3><img src="/lenovo-static/images/menu_ad.jpg"><span>' + NoDateTips + '</span></h3></div>';

    if (type == '1') {
        NoDateHTML = '<div class="prompt2"><div class="prompt2_body"><img class="xiaoxin" src="/lenovo-static/images/menu_ad.jpg"><div class="prompt2_info"><h3><span>温馨提示：暂时没有任何数据</span></h3></div> </div></div>';
    }

    return NoDateHTML;
}

//定义获取参数方法，输入参数名称，获得参数值
(function ($) {
    $.getUrlParam = function (commonname) {
        var reg = new RegExp("(^|&)" + commonname + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return decodeURIComponent(r[2]); return null;
    }
})(jQuery);
