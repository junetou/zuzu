<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@	taglib uri="/WEB-INF/classes/woo.tld" prefix="woo"%>

<head><title>租一天</title>
<link href="<woo:url value='/static/css/lightGallery.css'/>" rel="stylesheet" type="text/css">
<script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
<style type="text/css">
	#page-wrapper {padding:0;}
	.tips-btn{
	position: absolute;
    top: 2px;
    right: 0;
    width: 16px;
    height: 16px;
    border-radius: 50%;
    background: #ff0033;
    text-align: center;
    line-height: 16px;
    font-size: 12px;
    color: #fff;
    z-index:99999;
	}
	* {margin:0; padding:0;} 
body {font-size:12px; color:#222; font-family:Verdana,Arial,Helvetica,sans-serif; background:#f0f0f0;} 
.clearfix:after {content: "."; display: block; height: 0; clear: both; visibility: hidden;} 
.clearfix {zoom:1;} 
ul,li {list-style:none;} 
img {border:0;} 
.wrapper {width:800px; margin:0 auto; padding-bottom:50px;} 

#top{  
   position: fixed;  
   top: 0px; /* 关键 */  
   left:0; /* IE下一定要记得 */  
   height: 20px;         /* footer的高度一定要是固定值*/
   z-index:999;  
   text-align:center;
   margin:0px auto;
   border:0;
   padding:0;
   background-color:#666;
   width:600px;
}  

#focus {width:800px; height:280px; overflow:hidden; position:relative;} 
#focus ul {height:380px; position:absolute;} 
#focus ul li {float:left; width:800px; height:280px; overflow:hidden; position:relative; background:#000;} 
#focus ul li div {position:absolute; overflow:hidden;} 
#focus .btnBg {position:absolute; width:800px; height:20px; left:0; bottom:0; } 
#focus .btn {position:absolute; width:780px; height:20px; padding:5px 10px; right:0; bottom:0; text-align:right;} 
#focus .btn span {display:inline-block; _display:inline; _zoom:1; width:30px; height:20px; _font-size:0; margin-left:5px; cursor:pointer; background:#fff;border-radius:70%; overflow:hidden;} 
#focus .btn span.on {background:#fff;} 
#focus .preNext {width:15px; height:28px; position:absolute; top:10px; background:#FFCCFF no-repeat 0 0; cursor:pointer; border-radius:40%; overflow:hidden;} 
#focus .pre {left:0;} 
#focus .next {right:0;} 

    #footer{  
   position: fixed;  
   bottom: 0px; /* 关键 */  
   left:0; /* IE下一定要记得 */  
   height: 35px;         /* footer的高度一定要是固定值*/
   z-index:999;  
   text-align:center;
   margin:0px auto;
   border:0;
   padding:0;
   background-color:#666;
   width:600px;
   background-color:#FFFFCC;
}  
 #topper{
 position:absolute;
 width:100%;
 z-index:999;  }
 .gallery li {
display: block;
float:left;
list-style:none;
margin-bottom: 6px;
margin-right: 6px;
   margin:0;
   border:0;
   padding:0;
   width: 33%;
   height:33%;
}
.gallery li a img {
			max-width: 90px;
}

</style>
<script>
 $(document).ready(function() {
	 $("#auto-loop").lightGallery({
		 loop:true,
		 auto:true,
		 pause:4000
	 });
	});
</script>
</head>

<div id="wrapper" style="background-color:#FFFFCC " >
     <nav class="navbar navbar-default navbar-static-top " role="navigation" style="margin-bottom: 0; background-color:#FFFFCC; top:35px; ">		
			<ul class="nav" id="side-menu">			
               <div id="focus"> 
               <ul> 
               <li> <a href="http://www.lanrentuku.com/" target="_blank"><img src="<woo:url value="/static/images/5.png"/>" alt="" id="lis" /></a></li> 
               <li ><a href="http://www.lanrentuku.com/" target="_blank"><img src="<woo:url value="/static/images/5.png"/>" alt=""  id="lis1" /></a></li> 
               <li ><a href="http://www.lanrentuku.com/" target="_blank"><img src="<woo:url value="/static/images/5.png"/>" alt=""  id="lis2" /></a></li> 
               <li ><a href="http://www.lanrentuku.com/" target="_blank"><img src="<woo:url value="/static/images/5.png"/>" alt="" id="lis3" /></a></li> 
               <li  ><a href="http://www.lanrentuku.com/" target="_blank"><img src="<woo:url value="/static/images/5.png"/>" alt="" id="lis4" /></a></li> 
               </ul> 
               </div> 
		</ul>
     </nav>
     <div id="top"   style="background-color:#FFFFCC;height:20px;" >
        <ol class="breadcrumb" style="background-color:#FFFFCC;text-align:center;height:20px;">
           <li>
             <form method="post"  style="background-color:#FFFFCC">
		  	 <div class="input-group" style=" margin:0;border:0;padding:0;" >
		  	 <span class="input-group-addon"><img src="<woo:url value="/static/images/4.png"/>" alt="QQ商城焦点图效果下载" id="lis" width="20px;" height="20px;" class="img-circle" /></span>
		  	 <input type="text" class="form-control" name="keyWord" id="keyWord" placeholder="Search for..." style="background-color:#FFFFCC"  >
		  	<span class="input-group-btn">
             <input type="submit"  class="btn btn-primary"  value="搜索" >
             </span>
		  	</div>
		  	</form>
		  	</li>
		</ol>
    </div>
     <div style="height:55px;">
     <p></p>
     </div>
    <c:forEach items="${grid.datas }" varStatus="index" var="data">
    <ul class="list-group">
    <li class="list-group-item" style="background-image:url('<woo:url value="/static/images/c.jpg"/>');">
    <a href="#">
    <p><span><img alt="" src="<woo:url value="/static/picture/${data.thingspicturename }"/>" class="img-circle" align="buttom;" width="10%" height="10%;" >&nbsp&nbsp&nbsp</span><b>用户名:admin</b><span>
    <span style="float:right;color:red;">价格：￥${data.thingsprice }</span>
    </a>   
    <p> 
    <ul id="auto-loop" class="gallery" >
              <li data-src="<woo:url value="/static/images/4.png"/>" > 
        	  <a href="#">
              <img src="<woo:url value="/static/images/4.png"/>" alt="" id="lis"  class="img-rounded" />
              </a>
              </li>
              <li data-src="<woo:url value="/static/images/2.png"/>"   > 
        	  <a href="#">
              <img src="<woo:url value="/static/images/4.png"/>" alt="" id="lis"   class="img-rounded" />
              </a>
              </li>
              <li data-src="<woo:url value="/static/images/3.png"/>" >  
        	  <a href="#">
              <img src="<woo:url value="/static/images/4.png"/>" alt="" id="lis"  class="img-rounded" />
              </a> 
              </li>
    </ul>
    </br>
    </br>
    </p>
    <p class="text-primary" style="font-family: Helvetica,Hiragino Sans GB,Microsoft Yahei,微软雅黑, Arial, sans-serif;font-size:12px;">产品名字：${data.thingsname }
    </br>
              详细信息:${data.thingsdesc }
    </p>
    <div class="btn-group btn-group-xs">
    <button type="button" class="btn btn-default" >
    <a href="<c:url value='/portal/things/detailmessage/${data.thingsId}'/>" data-id="${data.thingsId }" >
              查看详细信息
	</a></button>
    </div>
    </li>
    </ul>
     </c:forEach>
    <c:if test="${grid.datas == null}">
	<div style="height:10px;">
     <p></p>
     </div>
						<tr>
							<td ><p align="center" style="font-size:36px;"><b>暂无相关记录！</b></p></td>
						</tr>
	</c:if>

    <div id="topper" style="background-color:#FFFFCC;" >
                       <ul class="nav nav-pills" style="text-align:left;background-color:#FFFFCC;" >
	                   <li class="btn btn-default" style="margin:0px;padding: 0px; border: 0px; text-align:left; width:50%;background-color:#FFE69D;"><a href="<c:url value='/portal/things/listmessage'/>"  ><b>物品</b></a></li>
	                   <li class="btn btn-inverse" style="margin:0px;padding: 0px; border: 0px; text-align:left; width:50%;background-color:#FFE69D;" ><a href="<c:url value='/show/otherproduct'/>"><b>需求</b></a></li>
                       </ul>
    </div>
    
   <div class="base-list-wrapper" style="height:40px;">
       <div class="single-list-item" style="height:40px;">
        <div class="goods-image">
        </div>
        </p>  
       </div>
  <div id="footer" >
        <ul class="nav nav-pills" style="text-align:left;background-color:#FFFFCC;" >
	                   <li class="btn btn-warning" style="margin:0px;padding: 0px; border: 0px; text-align:left; width:33.3%;"><a href="<c:url value='/portal/map/showmap'/>" ><b><i class="glyphicon glyphicon-gift">地图</i></b></a></li>
	                   <li class="btn btn-success" style="margin:0px;padding: 0px; border: 0px; text-align:left; width:33.3%" ><a href="<c:url value='/portal/list/showlist'/>"><b><i class="glyphicon glyphicon-align-left">列表</i></b></a></li>
                       <li class="btn btn-info" style="margin:0px;padding: 0px; border: 0px; text-align:left; width:33.3%" ><a href="<c:url value='/portal/person'/>"><b><i class="glyphicon glyphicon-cog">个人信息</i></b></a></li>
        </ul>
  </div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	var ifm = document.getElementById("mainFrame");
	var height = document.documentElement.clientHeight-60 || document.body.clientHeight-60;
	$(ifm).height(height);
	window.onresize = function () {
		var ifm= document.getElementById("mainFrame");
		var height = document.documentElement.clientHeight-60 || document.body.clientHeight-60;
		$(ifm).height(height);
	}
});

$(function() { 

var oImg=document.getElementById('wrapper');
oImg.style.width='100%';
oImg.style.height=document.documentElement.clientHeight/6+'px';   
var oImg=document.getElementById('focus');
oImg.style.width='100%';
oImg.style.height=document.documentElement.clientHeight/6+'px';

var sWidth = $("#focus").width(); //获取焦点图的宽度（显示面积） 
var len = $("#focus ul li").length; //获取焦点图个数 
var index = 0; 
var picTimer; 


//以下代码添加数字按钮和按钮后的半透明条，还有上一页、下一页两个按钮 
var btn = "<div class='btnBg' id='btnBg' ></div><div class='btn' id='btn' >"; 
for(var i=0; i < len; i++) { 
btn += "<span></span>"; 
} 
btn += "</div><div class='preNext pre' id='preNext pre' ></div><div class='preNext next' id='preNext next'  ></div>"; 
$("#focus").append(btn); 
$("#focus .btnBg").css("opacity",0.9); 

var oImg=document.getElementById('btnBg');
oImg.style.width='100%';
oImg.style.height='20px';

var oImg=document.getElementById('footer');
oImg.style.width='100%'; 

var oImg=document.getElementById('topper');
oImg.style.top=document.getElementById("focus").offsetHeight+32+'px'; 

var oImg=document.getElementById('top');
oImg.style.width='100%';
oImg.style.height=document.documentElement.clientHeight/30+'px'; 

//为小按钮添加鼠标滑入事件，以显示相应的内容 
$("#focus .btn span").css("opacity",0.9).mouseenter(function() { 
index = $("#focus .btn span").index(this); 
showPics(index); 
}).eq(0).trigger("mouseenter"); 

//上一页、下一页按钮透明度处理 
$("#focus .preNext").css("opacity",0.9).hover(function() { 
$(this).stop(true,false).animate({"opacity":"0.5"},300); 
},function() { 
$(this).stop(true,false).animate({"opacity":"0.2"},300); 
}); 

//上一页按钮 
$("#focus .pre").click(function() { 
index -= 1; 
if(index == -1) {index = len - 1;} 
showPics(index); 
}); 

//下一页按钮 
$("#focus .next").click(function() { 
index += 1; 
if(index == len) {index = 0;} 
showPics(index); 
}); 



//本例为左右滚动，即所有li元素都是在同一排向左浮动，所以这里需要计算出外围ul元素的宽度 
$("#focus ul").css("width",sWidth * (len));
$("#focus ul li").css("width",sWidth);
var oImg=document.getElementById('lis');
oImg.style.width='100%';
oImg.style.height=document.documentElement.clientHeight/6+'px';
var oImg=document.getElementById('lis1');
oImg.style.width='100%';
oImg.style.height=document.documentElement.clientHeight/6+'px';
var oImg=document.getElementById('lis2');
oImg.style.width='100%';
oImg.style.height=document.documentElement.clientHeight/6+'px';
var oImg=document.getElementById('lis3');
oImg.style.width='100%';
oImg.style.height=document.documentElement.clientHeight/6+'px';
var oImg=document.getElementById('lis4');
oImg.style.width='100%';
oImg.style.height=document.documentElement.clientHeight/6+'px';


//鼠标滑上焦点图时停止自动播放，滑出时开始自动播放 
$("#focus").hover(function() { 
clearInterval(picTimer); 
},function() { 
picTimer = setInterval(function() { 
showPics(index); 
index++; 
if(index == len) {index = 0;} 
},2000); //此4000代表自动播放的间隔，单位：毫秒 
}).trigger("mouseleave"); 

//显示图片函数，根据接收的index值显示相应的内容 
function showPics(index) { //普通切换 
//var nowLeft = -index*sWidth; //根据index值计算ul元素的left值 
var nowLeft = -index*sWidth; //根据index值计算ul元素的left值
$("#focus ul").stop(true,false).animate({"left":nowLeft},500); //通过animate()调整ul元素滚动到计算出的position 
//$("#focus .btn span").removeClass("on").eq(index).addClass("on"); //为当前的按钮切换到选中的效果 
$("#focus .btn span").stop(true,false).animate({"opacity":"0.4"},300).eq(index).stop(true,false).animate({"opacity":"1"},300); //为当前的按钮切换到选中的效果 
}
}); 



(function ($) {
	
    "use strict";
    $.fn.lightGallery = function (options) {
        var defaults = {
            mode: 'slide',
            useCSS: true,
            easing: 'linear', //'cubic-bezier(0.25, 0, 0.25, 1)',//
            speed: 1000,
            closable: true,
            loop: false,
            auto: false,
            pause: 4000,
            escKey: true,
            rel: false,
            lang: {
                allPhotos: 'All photos'
            },
            exThumbImage: false,
            index: false,
            thumbnail: true,
            caption: false,
            captionLink: false,
            desc: false,
            counter: false,
            controls: true,
            hideControlOnEnd: false,
            mobileSrc: false,
            mobileSrcMaxWidth: 640,
            //touch
            swipeThreshold: 50,
            vimeoColor: 'CCCCCC',
            videoAutoplay: true,
            videoMaxWidth: 855,
            dynamic: false,
            //callbacks
            dynamicEl: [],
            onOpen: function () {},
            onSlideBefore: function () {},
            onSlideAfter: function () {},
            onSlideNext: function () {},
            onSlidePrev: function () {},
            onBeforeClose: function () {},
            onCloseAfter: function () {}
        },
            el = $(this),
            $children,
            index,
            lightGalleryOn = false,
            html = '<div id="lightGallery-outer"><div id="lightGallery-Gallery"><div id="lightGallery-slider"></div><a id="lightGallery-close" class="close"></a></div></div>',
            isTouch = document.createTouch !== undefined || ('ontouchstart' in window) || ('onmsgesturechange' in window) || navigator.msMaxTouchPoints,
            $gallery, $galleryCont, $slider, $slide, $prev, $next, prevIndex, $thumb_cont, $thumb, windowWidth, interval, usingThumb = false,
            aTiming = false,
            aSpeed = false;
        var settings = $.extend(true, {}, defaults, options);
        var lightGallery = {
            init: function () {
                el.each(function () {
                    var $this = $(this);
                    if (settings.dynamic == true) {
                        $children = settings.dynamicEl;
                        index = 0;
                        prevIndex = index;
                        setUp.init(index);
                    } else {
                        $children = $(this).children();
                        $children.click(function (e) {
                            if (settings.rel == true && $this.data('rel')) {
                                var rel = $this.data('rel');
                                $children = $('[data-rel="' + rel + '"]').children();
                            } else {
                                $children = $this.children();
                            }
                            e.preventDefault();
                            e.stopPropagation();
                            index = $children.index(this);
                            prevIndex = index;
                            setUp.init(index);
                        });
                    }
                });
            },
        };
        var setUp = {
            init: function () {
                this.start();
                this.build();
            },
            start: function () {
                this.structure();
                this.getWidth();
                this.closeSlide();
            },
            build: function () {
                this.loadContent(index);
                this.addCaption();
                this.addDesc(); //description
                this.counter();
                this.slideTo();
                this.buildThumbnail();
                this.keyPress();
                if(settings.index) {
                    this.slide(settings.index);
                }
                else {
                    this.slide(index);
                }
                this.touch();
                this.enableTouch();

                setTimeout(function () {
                    $gallery.addClass('opacity');
                }, 50);
            },
            structure: function () {
                $('body').append(html).addClass('lightGallery');
                $galleryCont = $('#lightGallery-outer');
                $gallery = $('#lightGallery-Gallery');
                $slider = $gallery.find('#lightGallery-slider');
                var slideList = '';
                if (settings.dynamic == true) {
                    for (var i = 0; i < settings.dynamicEl.length; i++) {
                        slideList += '<div class="lightGallery-slide"></div>';
                    }
                } else {
                    $children.each(function () {
                        slideList += '<div class="lightGallery-slide"></div>';
                    });
                }
                $slider.append(slideList);
                $slide = $gallery.find('.lightGallery-slide');
            },
            closeSlide: function () {
                var $this = this;
                if(settings.closable) {
                    $('.lightGallery-slide')
                        .on('click', function(event) {
                            console.log(event.target);
                            if($(event.target).is('.lightGallery-slide')) {
                                $this.destroy();
                            }
                        })
                    ;
                }
                $('#lightGallery-close').bind('click touchend', function () {
                    $this.destroy();
                });
            },
            getWidth: function () {
                var resizeWindow = function () {
                    windowWidth = $(window).width();
                };
                $(window).bind('resize.lightGallery', resizeWindow());
            },
            doCss: function () {
                var support = function () {
                    var transition = ['transition', 'MozTransition', 'WebkitTransition', 'OTransition', 'msTransition', 'KhtmlTransition'];
                    var root = document.documentElement;
                    for (var i = 0; i < transition.length; i++) {
                        if (transition[i] in root.style) {
                            //cssPrefix = transition[i].replace('Transition', '').toLowerCase();
                            //cssPrefix == 'transition' ? cssPrefix = 'transition' : cssPrefix = ('-'+cssPrefix+'-transition');
                            return true;
                        }
                    }
                };
                if (settings.useCSS && support()) {
                    return true;
                }
                return false;
            },
            enableTouch: function () {
                var $this = this;
                if (isTouch){
                    var startCoords = {},
                        endCoords = {};
                    $('body').on('touchstart.lightGallery', function(e) {
                        endCoords = e.originalEvent.targetTouches[0];
                        startCoords.pageX = e.originalEvent.targetTouches[0].pageX;
                        startCoords.pageY = e.originalEvent.targetTouches[0].pageY;
                    });
                    $('body').on('touchmove.lightGallery', function(e) {
                        var orig = e.originalEvent;
                        endCoords = orig.targetTouches[0];
                        e.preventDefault();
                    });
                    $('body').on('touchend.lightGallery', function(e) {
                        var distance = endCoords.pageX - startCoords.pageX,
                        swipeThreshold = settings.swipeThreshold;
                        if( distance >= swipeThreshold ){
                            $this.prevSlide();
                            clearInterval(interval);
                        }
                        else if( distance <= - swipeThreshold ){
                            $this.nextSlide();
                            clearInterval(interval);
                        }
                    });
                }
            },
            touch: function () {
                var xStart, xEnd;
                var $this = this;
                $('.lightGallery').bind('mousedown', function (e) {
                    e.stopPropagation();
                    e.preventDefault();
                    xStart = e.pageX;
                });
                $('.lightGallery').bind('mouseup', function (e) {
                    e.stopPropagation();
                    e.preventDefault();
                    xEnd = e.pageX;
                    if (xEnd - xStart > 20) {
                        $this.prevSlide();
                    } else if (xStart - xEnd > 20) {
                        $this.nextSlide();
                    }
                });
            },
            isVideo: function (src) {
                var youtube = src.match(/\/\/(?:www\.)?youtu(?:\.be|be\.com)\/(?:watch\?v=|embed\/)?([a-z0-9_\-]+)/i);
                var vimeo = src.match(/\/\/(?:www\.)?vimeo.com\/([0-9a-z\-_]+)/i);
                if (youtube || vimeo) {
                    return true;
                }
            },
            loadVideo: function (src, a, _id) {
                var youtube = src.match(/\/\/(?:www\.)?youtu(?:\.be|be\.com)\/(?:watch\?v=|embed\/)?([a-z0-9_\-]+)/i);
                var vimeo = src.match(/\/\/(?:www\.)?vimeo.com\/([0-9a-z\-_]+)/i);
                var video = '';
                if (youtube) {
                    if (settings.videoAutoplay === true && a === true) {
                        a = '?autoplay=1&rel=0&wmode=opaque';
                    } else {
                        a = '?wmode=opaque';
                    }
                    video = '<iframe id="video' + _id + '" width="560" height="315" src="//www.youtube.com/embed/' + youtube[1] + a + '" frameborder="0" allowfullscreen></iframe>';
                } else if (vimeo) {
                    if (settings.videoAutoplay === true && a === true) {
                        a = 'autoplay=1&amp;';
                    } else {
                        a = '';
                    }
                    video = '<iframe id="video' + _id + '" width="560" height="315"  src="http://player.vimeo.com/video/' + vimeo[1] + '?' + a + 'byline=0&amp;portrait=0&amp;color=' + settings.vimeoColor + '" frameborder="0" webkitAllowFullScreen mozallowfullscreen allowFullScreen></iframe>';
                }
                return '<div class="video_cont" style="max-width:' + settings.videoMaxWidth + 'px !important;"><div class="video">' + video + '</div></div>';
            },
            loadContent: function (index) {
                var $this = this;
                var i, j, ob, l = $children.length - index;
                var src;
                $this.autoStart();
                if (settings.mobileSrc === true && windowWidth <= settings.mobileSrcMaxWidth) {
                    if (settings.dynamic == true) {
                        src = settings.dynamicEl[0]['mobileSrc'];
                    } else {
                        src = $children.eq(index).attr('data-responsive-src');
                    }
                } else {
                    if (settings.dynamic == true) {
                        src = settings.dynamicEl[0]['src'];
                    } else {
                        src = $children.eq(index).attr('data-src');
                    }
                }
                if (!$this.isVideo(src)) {
                    $slide.eq(index).prepend('<img src="' + src + '" />');
                    ob = $('img');
                } else {
                    $slide.eq(index).prepend($this.loadVideo(src, true, index));
                    ob = $('iframe');
                    if (settings.auto && settings.videoAutoplay === true) {
                        clearInterval(interval);
                    }
                }
                if ($children.length > 1) {
                    $slide.eq(index).find(ob).on('load error', function () {
                        for (i = 0; i <= index - 1; i++) {
                            var src;
                            if (settings.mobileSrc === true && windowWidth <= settings.mobileSrcMaxWidth) {
                                if (settings.dynamic == true) {
                                    src = settings.dynamicEl[index - i - 1]['mobileSrc'];
                                } else {
                                    src = $children.eq(index - i - 1).attr('data-responsive-src');
                                }
                            } else {
                                if (settings.dynamic == true) {
                                    src = settings.dynamicEl[index - i - 1]['src'];
                                } else {
                                    src = $children.eq(index - i - 1).attr('data-src');
                                }
                            }
                            if (!$this.isVideo(src)) {
                                $slide.eq(index - i - 1).prepend('<img src="' + src + '" />');
                            } else {
                                $slide.eq(index - i - 1).prepend($this.loadVideo(src, false, index - i - 1));
                            }
                        }
                        for (j = 1; j < l; j++) {
                            var src;
                            if (settings.mobileSrc === true && windowWidth <= settings.mobileSrcMaxWidth) {
                                if (settings.dynamic == true) {
                                    src = settings.dynamicEl[index + j]['mobileSrc'];
                                } else {
                                    src = $children.eq(index + j).attr('data-responsive-src');
                                }
                            } else {
                                if (settings.dynamic == true) {
                                    src = settings.dynamicEl[index + j]['src'];
                                } else {
                                    src = $children.eq(index + j).attr('data-src');
                                }
                            }
                            if (!$this.isVideo(src)) {
                                $slide.eq(index + j).prepend('<img src="' + src + '" />');
                            } else {
                                $slide.eq(index + j).prepend($this.loadVideo(src, false, index + j));
                            }
                        }
                    });
                }
            },
            addCaption: function () {
                if (settings.caption === true) {
                    var i, title = false;
                    for (i = 0; i < $children.length; i++) {
                        if (settings.dynamic == true) {
                            title = settings.dynamicEl[i]['caption'];
                        } else {
                            title = $children.eq(i).attr('data-title');
                        }
                        if (typeof title == 'undefined' || title == null) {
                            title = 'image ' + i + '';
                        }
                        if (settings.captionLink === true) {
                            var link = $children.eq(i).attr('data-link');
                            if (typeof link !== 'undefined' && link !== '') {
                                link = link
                            } else {
                                link = '#'
                            }
                            $slide.eq(i).append('<div class="info group"><a href="' + link + '" class="title">' + title + '</a></div>');
                        } else {
                            $slide.eq(i).append('<div class="info group"><span class="title">' + title + '</span></div>');
                        }
                    }
                }
            },
            addDesc: function () {
                if (settings.desc === true) {
                    var i, description = false;
                    for (i = 0; i < $children.length; i++) {
                        if (settings.dynamic == true) {
                            description = settings.dynamicEl[i]['desc'];
                        } else {
                            description = $children.eq(i).attr('data-desc');;
                        }
                        if (typeof description == 'undefined' || description == null) {
                            description = 'image ' + i + '';
                        }
                        if (settings.caption === false) {
                            $slide.eq(i).append('<div class="info group"><span class="desc">' + description + '</span></div>');
                        } else {
                            $slide.eq(i).find('.info').append('<span class="desc">' + description + '</span>');
                        }
                    }
                }
            },
            counter: function() {
                if (settings.counter === true) {
                    var slideCount = $("#lightGallery-slider > div").length;
                    $gallery.append("<div id='lightGallery_counter'><span id='lightGallery_counter_current'></span> / <span id='lightGallery_counter_all'>"+slideCount+"</span></div>");
                }
            },
            buildThumbnail: function () {
                if (settings.thumbnail === true && $children.length > 1) {
                    var $this = this;
                    $gallery.append('<div class="thumb_cont"><div class="thumb_info"><span class="close ib"><i class="bUi-iCn-rMv-16" aria-hidden="true"></i></span></div><div class="thumb_inner"></div></div>');
                    $thumb_cont = $gallery.find('.thumb_cont');
                    $prev.after('<a class="cLthumb"></a>');
                    $gallery.find('.cLthumb').bind('click touchend', function () {
                        $thumb_cont.addClass('open');
                        if ($this.doCss() && settings.mode === 'slide') {
                            $slide.eq(index).prevAll().removeClass('nextSlide').addClass('prevSlide');
                            $slide.eq(index).nextAll().removeClass('prevSlide').addClass('nextSlide');
                        }
                    });
                    $gallery.find('.close').bind('click touchend', function () {
                        $thumb_cont.removeClass('open');
                    });
                    var thumbInfo = $gallery.find('.thumb_info');
                    var $thumb_inner = $gallery.find('.thumb_inner');
                    var thumbList = '';
                    var thumbImg;
                    if (settings.dynamic == true) {
                        for (var i = 0; i < settings.dynamicEl.length; i++) {
                            thumbImg = settings.dynamicEl[i]['thumb'];
                            thumbList += '<div class="thumb"><img src="' + thumbImg + '" /></div>';
                        }
                    } else {
                        $children.each(function () {
                            if (settings.exThumbImage === false || typeof $(this).attr(settings.exThumbImage) == 'undefined' || $(this).attr(settings.exThumbImage) == null) {
                                thumbImg = $(this).find('img').attr('src');
                            } else {
                                thumbImg = $(this).attr(settings.exThumbImage);
                            }
                            thumbList += '<div class="thumb"><img src="' + thumbImg + '" /></div>';
                        });
                    }
                    $thumb_inner.append(thumbList);
                    $thumb = $thumb_inner.find('.thumb');
                    $thumb.bind('click touchend', function () {
                        usingThumb = true;
                        var index = $(this).index();
                        $thumb.removeClass('active');
                        $(this).addClass('active');
                        $this.slide(index);
                        clearInterval(interval);
                    });
                    thumbInfo.prepend('<span class="ib count">' + settings.lang.allPhotos + ' (' + $thumb.length + ')</span>');
                }
            },
            slideTo: function () {
                var $this = this;
                if (settings.controls === true && $children.length > 1) {
                    $gallery.append('<div id="lightGallery-action"><a id="lightGallery-prev"></a><a id="lightGallery-next"></a></div>');
                    $prev = $gallery.find('#lightGallery-prev');
                    $next = $gallery.find('#lightGallery-next');
                    $prev.bind('click', function () {
                        $this.prevSlide();
                        clearInterval(interval);
                    });
                    $next.bind('click', function () {
                        $this.nextSlide();
                        clearInterval(interval);
                    });
                }
            },
            autoStart: function () {
                var $this = this;
                if (settings.auto === true) {
                    interval = setInterval(function () {
                        if (index + 1 < $children.length) {
                            index = index;
                        } else {
                            index = -1;
                        }
                        index++;
                        $this.slide(index);
                    }, settings.pause);
                }
            },
            keyPress: function () {
                var $this = this;
                $(window).bind('keyup.lightGallery', function (e) {
                    e.preventDefault();
                    e.stopPropagation();
                    if (e.keyCode === 37) {
                        $this.prevSlide();
                        clearInterval(interval);
                    }
                    if (e.keyCode === 38 && settings.thumbnail === true) {
                        if (!$thumb_cont.hasClass('open')) {
                            if ($this.doCss() && settings.mode === 'slide') {
                                $slide.eq(index).prevAll().removeClass('nextSlide').addClass('prevSlide');
                                $slide.eq(index).nextAll().removeClass('prevSlide').addClass('nextSlide');
                            }
                            $thumb_cont.addClass('open');
                        }
                    } else if (e.keyCode === 39) {
                        $this.nextSlide();
                        clearInterval(interval);
                    }
                    if (e.keyCode === 40 && settings.thumbnail === true) {
                        if ($thumb_cont.hasClass('open')) {
                            $thumb_cont.removeClass('open');
                        }
                    } else if (settings.escKey === true && e.keyCode === 27) {
                        if (settings.thumbnail === true && $thumb_cont.hasClass('open')) {
                            $thumb_cont.removeClass('open');
                        } else {
                            $this.destroy();
                        }
                    }
                });
            },
            nextSlide: function () {
                var $this = this;
                index = $slide.index($slide.eq(prevIndex));
                if (index + 1 < $children.length) {
                    index++;
                    $this.slide(index);
                } else {
                    if (settings.loop) {
                        index = 0;
                        $this.slide(index);
                    } else if (settings.mode === 'fade' && settings.thumbnail === true && $children.length > 1) {
                        $thumb_cont.addClass('open');
                    }
                }
                settings.onSlideNext.call(this);
            },
            prevSlide: function () {
                var $this = this;
                index = $slide.index($slide.eq(prevIndex));
                if (index > 0) {
                    index--;
                    $this.slide(index);
                } else {
                    if (settings.loop) {
                        index = $children.length - 1;
                        $this.slide(index);
                    } else if (settings.mode === 'fade' && settings.thumbnail === true && $children.length > 1) {
                        $thumb_cont.addClass('open');
                    }
                }
                settings.onSlidePrev.call(this);
            },
            slide: function (index) {
                if (lightGalleryOn) {
                    if (!$slider.hasClass('on')) {
                        $slider.addClass('on');
                    }
                    if (this.doCss() && settings.speed !== '') {
                        if (!$slider.hasClass('speed')) {
                            $slider.addClass('speed');
                        }
                        if (aSpeed === false) {
                            $slider.css('transition-duration', settings.speed + 'ms');
                            aSpeed = true;
                        }
                    }
                    if (this.doCss() && settings.easing !== '') {
                        if (!$slider.hasClass('timing')) {
                            $slider.addClass('timing');
                        }
                        if (aTiming === false) {
                            $slider.css('transition-timing-function', settings.easing);
                            aTiming = true;
                        }
                    }
                    settings.onSlideBefore.call(this);
                }
                if (settings.mode === 'slide') {
                    if (this.doCss() && !$slider.hasClass('slide')) {
                        $slider.addClass('slide');
                    }
                    /*                  if(this.doCss()){
                        $slider.css({ 'transform' : 'translate3d('+(-index*100)+'%, 0px, 0px)' });
                    }*/
                    if (!this.doCss() && !lightGalleryOn) {
                        $slider.css({
                            left: (-index * 100) + '%'
                        });
                        //$slide.eq(index).css('transition','none');
                    } else if (!this.doCss() && lightGalleryOn) {
                        $slider.animate({
                            left: (-index * 100) + '%'
                        }, settings.speed, settings.easing);
                    }
                } else if (settings.mode === 'fade') {
                    if (this.doCss() && !$slider.hasClass('fadeM')) {
                        $slider.addClass('fadeM');
                    } else if (!this.doCss() && !$slider.hasClass('animate')) {
                        $slider.addClass('animate');
                    }
                    if (!this.doCss() && !lightGalleryOn) {
                        $slide.fadeOut(100);
                        $slide.eq(index).fadeIn(100);
                    } else if (!this.doCss() && lightGalleryOn) {
                        $slide.eq(prevIndex).fadeOut(settings.speed, settings.easing);
                        $slide.eq(index).fadeIn(settings.speed, settings.easing);
                    }
                }
                if (index + 1 >= $children.length && settings.auto && settings.loop === false) {
                    clearInterval(interval);
                }
                $slide.eq(prevIndex).removeClass('current');
                $slide.eq(index).addClass('current');
                if (this.doCss() && settings.mode === 'slide') {
                    if (usingThumb === false) {
                        $('.prevSlide').removeClass('prevSlide');
                        $('.nextSlide').removeClass('nextSlide');
                        $slide.eq(index - 1).addClass('prevSlide');
                        $slide.eq(index + 1).addClass('nextSlide');
                    } else {
                        $slide.eq(index).prevAll().removeClass('nextSlide').addClass('prevSlide');
                        $slide.eq(index).nextAll().removeClass('prevSlide').addClass('nextSlide');
                    }
                }
                if (settings.thumbnail === true && $children.length > 1) {
                    $thumb.removeClass('active');
                    $thumb.eq(index).addClass('active');
                }
                if (settings.controls && settings.hideControlOnEnd && settings.loop === false) {
                    if (index === 0) {
                        $prev.addClass('disabled');
                    } else if (index === $children.length - 1) {
                        $next.addClass('disabled');
                    } else {
                        $prev.add($next).removeClass('disabled');
                    }
                }
                prevIndex = index;
                lightGalleryOn === false ? settings.onOpen.call(this) : settings.onSlideAfter.call(this);
                lightGalleryOn = true;
                usingThumb = false;
                if (settings.counter) {
                    $("#lightGallery_counter_current").text(index+1);
                }
            },
            destroy: function () {
                settings.onBeforeClose.call(this);
                lightGalleryOn = false;
                aTiming = false;
                aSpeed = false;
                usingThumb = false;
                clearInterval(interval);
                $('.lightGallery').off('mousedown mouseup');
                $('body').off('touchstart.lightGallery touchmove.lightGallery touchend.lightGallery');
                $(window).off('resize.lightGallery keyup.lightGallery');
                $gallery.addClass('fadeM');
                setTimeout(function () {
                    $galleryCont.remove();
                    $('body').removeClass('lightGallery');
                }, 500);
                settings.onCloseAfter.call(this);
            }
        };
        lightGallery.init();
        return this;
    };
}(jQuery));
</script>

