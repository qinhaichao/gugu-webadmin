/* 多图上传 - 初始化 */

var  getStrName = function(str,num)
{
    if(str.length>num)
    {
       return  str.substring(0, num) + "...";
    }
    else
    {
        return str;
    }
}
function UploaderImg(opt){
	var $list = opt.fileBox,
        // 优化retina, 在retina下这个值是2
        ratio = window.devicePixelRatio || 1,

        // 缩略图大小
        thumbnailWidth = 100 * ratio,
        thumbnailHeight = 100 * ratio,


    // 初始化Web Uploader
    uploader = WebUploader.create({

        // 自动上传。
        auto: true,
        
        // swf文件路径
        //swf: BASE_URL + '/js/Uploader.swf',

        // 文件接收服务端。
        server: hostURL + '/imageUpload/uploadJson',

        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: opt.btn,
        fileNumLimit:opt.fileNumLimit,
        fileSingleSizeLimit :524288,
        // 只允许选择文件，可选。
        accept: {
            title: 'Images',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/*'
        }
    });


    // 当有文件添加进来的时候
    uploader.on( 'fileQueued', function( file ) {
    	//console.log($list);
        var thisIdex = $list.parents("tr").index();
        var imgtype = $list.attr("imgtype");
        //console.log($list);
        var $li = $(
                '<div id="' + file.id + '" class="file-item " style="float:left;margin-left:10px">' +
                    '<div><a class="removeImg" href="javascript:;" style="vertical-align:top; margin-right:5px">移除</a></div>' +
                    '<a id="bigImg_'+file.id+'" href="" target="_blank"><img></a>' +
                    '<input name="picture_' + thisIdex + '.'+ imgtype +',' + file.id + '" type="hidden">' +
                    '<div class="info" title=' + file.name + '>' + getStrName(file.name, 7) + '</div>' +
                '</div>'
                ),
            $img = $li.find('img');

        $list.append( $li );

        // 创建缩略图
        uploader.makeThumb( file, function( error, src ) {
            if ( error ) {
                $img.replaceWith('<span>不能预览</span>');
                return;
            }

            $img.attr( 'src', src );
        }, thumbnailWidth, thumbnailHeight );

        $(".removeImg").on("click",this,function(event) {
    		uploader.removeFile( file );
    		$(this).parents(".file-item").remove();
    		console.log(uploader.removeFile)
    	});
    });

    // 文件上传过程中创建进度条实时显示。
    uploader.on( 'uploadProgress', function( file, percentage ) {
        var $li = $( '#'+file.id ),
            $percent = $li.find('.progress span');

        // 避免重复创建
        if ( !$percent.length ) {
            $percent = $('<p class="progress"><span></span></p>')
                    .appendTo( $li )
                    .find('span');
        }

        $percent.css( 'width', percentage * 100 + '%' );
    });

    // 文件上传成功，给item添加成功class, 用样式标记上传成功。
    uploader.on( 'uploadSuccess', function( file , response ) {
        $( '#'+file.id ).addClass('upload-state-done');
        $( '#bigImg_'+file.id ).attr("href",response.url);
        $( '[name$='+ file.id +']' ).val(response.id);
        console.log(response);
        //console.log(file);
    });

    // 文件上传失败，现实上传出错。
    uploader.on( 'uploadError', function( file , reason ) {
    	console.log(reason);
        var $li = $( '#'+file.id ),
            $error = $li.find('div.error');

        // 避免重复创建
        if ( !$error.length ) {
            $error = $('<div class="error"></div>').appendTo( $li );
        }
        $('#' + file.id + ' [type=hidden]').remove();
        $error.text('上传失败');
      
       
    });

    // 完成上传完了，成功或者失败，先删除进度条。
    uploader.on( 'uploadComplete', function( file ) {
        $('#' + file.id).find('.progress').remove();

    });
    // 上传文件不符合规定时触发（如大小，数量）
    uploader.on( 'error', function( file ) {
        console.log(file)
        switch(file){
            case "Q_EXCEED_NUM_LIMIT": 
                showPop("图片上传数量超出上限",1);
                break;
            case "Q_EXCEED_SIZE_LIMIT": 
                showPop("图片大小超出上限",1);
                break;
            case "F_EXCEED_SIZE": 
                showPop("图片大小超出上限",1);
                break;    
            case "Q_TYPE_DENIED": 
                showPop("图片格式不符合要求",1);
                break;
            default :
                showPop("图片不符合要求",1);
                break;
        }
    });

    // Web Uploader实例
  return   uploader;

}