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
	<style>
		body{
			background:black;
		}
		.carousel-item img{
			width:100%;
			
		}
	</style>
	<decorator:head/>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">Sia</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
    <div class="navbar-nav">
      <a class="nav-item nav-link active" href="/sia/home.html">Beranda <span class="sr-only">(current)</span></a>
      <a class="nav-item nav-link" href="/sia/form/mahasiswa.html">Data Mahasiswa</a>
            <a class="nav-item nav-link" href="/sia/form/jurusan.html">Data Jurusan</a>
      <a class="nav-item nav-link" href="/sia/j_spring_security_logout">Logout</a>
      <a class="nav-item nav-link disabled" href="#"></a>
    </div>
  </div>
</nav>
	<decorator:body/>
</body>
</html>