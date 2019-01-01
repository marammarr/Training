<%
	request.setAttribute("contextName", request.getServletContext().getContextPath());
%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<!-- main.jsp //-->
<html>
<head>
	<title>SIA - <decorator:title/></title>
	<link rel="stylesheet" href="${contextName}/assets/bootstrap/css/bootstrap.min.css">
	<script src="${contextName}/assets/jquery/jquery-3.3.1.min.js"></script>
	<script src="${contextName}/assets/bootstrap/js/bootstrap.min.js"></script>
	<script src="${contextName}/assets/js/default.js"></script>
	
	<decorator:head/>
</head>
<body>
	<decorator:body/>
</body>
</html>