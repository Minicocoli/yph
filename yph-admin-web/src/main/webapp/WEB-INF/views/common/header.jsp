<%@ page language="java"  pageEncoding="UTF-8"%>
<%@page import="com.yunpinhui.global.conf.BaseConfig"%>
<%@page import="com.yunpinhui.global.conf.Config"%>
<%@page import="com.jianjiao8.common.util.RequestUtil"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragrma","no-cache");
response.setDateHeader("Expires",0);
//上下文路径
String path = request.getContextPath();
path = "/".equals(path)?"":path;
request.setAttribute("path",path);
request.setAttribute("sourcePath",path+"/admin/");
int port = request.getServerPort();
String portStr = port==80?"":":"+port;
String address = request.getScheme()+"://"+request.getServerName()+portStr+path;

//上下文路径
request.setAttribute("uploadServer",Config.uploadServer);
request.setAttribute("iisServer",Config.iisServer);
request.setAttribute("uploadCallBack",Config.uploadCallBack.replace("[path]",address));
request.setAttribute("baseWxAppId",BaseConfig.base_appid);
request.setAttribute("homeUrl",Config.home_address);
request.setAttribute("mpDir",BaseConfig.dir_mp);
request.setAttribute("wxServerApi",Config.wxServerApi);
request.setAttribute("version",BaseConfig.version);
%>
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
	
