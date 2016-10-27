<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri='http://www.springframework.org/security/tags' prefix='security'%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@	taglib uri="/WEB-INF/classes/woo.tld" prefix="woo"%>

<style>
#footer{  
   position: fixed;  
   bottom: 0px; 
   left:0;   
   height: 35px; 
   z-index:999;  
   text-align:center;
   margin:0px auto;
   border:0;
   padding:0;
   background-color:#666;
   width:600px;
}
</style>

<div id="tex" class="panel panel-default">
<h1 style="text-align:center;"><b><i>使用说明</i></b></h1>
<textarea id="tex1">
1.请用户在使用前先查看法律声明，以免发生不必要的利益纠纷，用户进入网站后请先完善个人资料。
2.本网站导航栏分为地图界面和列表栏界面和个人信息界面。
3.地图界面为物品或者需求存放的位置，点击地图中的圆圈可进行手机定位，搜索栏可搜索地区位置(不可搜索物品信息！)，地图里显示的租字体为其他用户或自己出租的物品，需字体为其他用户或本人发布的需求，点击其中一个字体则弹出简略信息框，点击简略信息框里的[详细信息]即可查看本物品或需求的详细信息。
4.列表栏首页展示八个最新发布的物品，可在搜索栏搜索物品，点击物品按钮，进入物品列表栏，物品列表栏的排序以物品发布的最久时间排序，即最久的排在前方，最新的排在后方；点击需求按钮，进入需求栏列表，需求栏列表排序与物品列表栏排序相同。
5.在列表栏的搜索中，输入汉字或英文即搜索物品(需求)的名字，输入整数(如10)即搜索最长可租借的时间，输入数字后带小数点(如10.5)即搜索物品租一天的费用。
6。用户在点击出租物品后，弹出的出租物品页面，物品名字即产品名字，物品地方为物品存放的地方，物品价格为数字，物品最长租用时间即你发布的物品其他最长可租借多少时间，经纬度必填（点击地图即可），上传图片中，第一张为地图简略信息框显示的图片(非常重要，如没有则显示图片丢失的样式图)。
7.用户如见到自己合需求的物品，点击我要租借，等待卖家确认租借.如卖家确认租借后，物品不在显示在地图或列表栏中，在正在交易导航栏中可以见到物品的卖家和租价的详细信息，方便私下交易，如卖家不租借，则不影响物品在地图或列表栏的位置。
8.需求租借类似物品租借，详情请看第七条。
9.用户在更新自己资料后，如上传新头像后，请在个人资料导航中更新一下页面(可更新图片信息)。
</textarea>
<p class="date">2016年9月4日</p>
</div>

 <div id="footer" >
        <ul class="nav nav-pills" style="text-align:left;background-color:#FFFFCC;" >
	                    <li class="btn btn-info" style="margin:0px;padding: 0px; border: 0px; text-align:left; width:33.3%;border-radius:0px;"><a href="<c:url value='/portal/map/showmap'/>" style="color:#000000" ><i class="glyphicon glyphicon-gift">地图</i></a></li>
	                   <li class="btn btn-info" style="margin:0px;padding: 0px; border: 0px; text-align:left; width:33.3%;border-radius:0px;" ><a href="<c:url value='/portal/list/showlist'/>" style="color:#000000"><b><i class="glyphicon glyphicon-align-left">列表</i></b></a></li>
                       <li class="btn btn-info" style="margin:0px;padding: 0px; border: 0px; text-align:left; width:33.3%;border-radius:0px;" ><a href="<c:url value='/portal/person'/>" style="color:#000000"><b><i class="glyphicon glyphicon-cog">个人信息</i></b></a></li>
	                       </ul>
  </div>
  
  <script>
  $(document).ready(function(){
		var oImg=document.getElementById('tex');
		oImg.style.width='100%';
		oImg.style.height='100%';
		var oImg=document.getElementById('tex1');
		oImg.style.width='100%';
		oImg.style.height=document.documentElement.clientHeight+'px'; 
		var oImg=document.getElementById('footer');
		oImg.style.width='100%';
	});
  </script>