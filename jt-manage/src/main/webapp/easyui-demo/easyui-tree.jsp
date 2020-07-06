<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/js/jquery-easyui-1.4.1/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="/js/jquery-easyui-1.4.1/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="/css/jt.css" />
<script type="text/javascript" src="/js/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript" src="/js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
</head>
<body class="easyui-layout">

   <div data-options="region:'west',title:'菜单',split:true" style="width:10%;">
   
  	<ul class="easyui-tree">
  	
  	<li>
  	<span>商品管理</span>
	  	<ul>
	  		<li>商品查询</li>
	  		<li>商品新增</li>
	  		<li>
	  			<span>我是三级biaoqian</span>
	  			<ul>
	  				<li>一</li>
	  				<li>一</li>
	  				<li>一</li>
	  			</ul>
	  		</li>
	  	</ul>
  	</li>
  	
  	
  	<li>
  	<span>内容管理</span>
  	<ul>
  		<li>内容新增</li>
  	</ul>
  	</li>
  	</ul>
   
   </div>
   <div data-options="region:'center',title:'首页'"></div>

</body>

</html>