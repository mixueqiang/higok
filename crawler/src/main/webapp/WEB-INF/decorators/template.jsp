<!DOCTYPE HTML><%@ page language="java" pageEncoding="UTF-8"%><%@ include file="/WEB-INF/commons/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><d:title default="Higok.com Crawler" /></title>
<link rel="stylesheet" href="<c:url value="/css/style.css" />" />
<link rel="stylesheet" href="<c:url value="/bootstrap/css/bootstrap.css"/>" />
<link rel="stylesheet" href="<c:url value="/bootstrap/css/bootstrap-responsive.css"/>" />
<script src="<c:url value="/js/jquery-1.7.2.min.js" />"></script>
<script src="<c:url value="/bootstrap/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/js/hiogk-crawler.js" />"></script>
<d:head />
<script>
  $(function() {
  });
</script>
</head>
<body>
  <div id="site-body"><%@ include file="/WEB-INF/commons/header.jsp"%>
    <div id="main">
      <div id="message"></div>
      <d:body />
    </div><%@ include file="/WEB-INF/commons/footer.jsp"%>
  </div>
</body>
</html>