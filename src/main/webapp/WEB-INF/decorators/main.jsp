<%
	request.setAttribute("contextName", request.getServletContext().getContextPath());
%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
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
		background: url(${contextName}/assets/img/coll.jpg);
		background-size: cover;
		background-attachment:fixed;
		background-position: center;
		background-repeat: no-repeat;
	}
	#list{
		background: rgba(80,10,100,0.4);
		color:white;
	}
	#list td,th{
		color:white;
		padding:10px;
	}
	th{
		background:black;
		padding:10px;
		font-size:20px;	
	}
	table,td,th,tr{
		transition: all 1s ease-in;
		-moz-transition: all 1s ease-in;
		-webkit-transition: all 1s ease-in;
	}
	.bunder-kiri{
		border-top-left-radius: 15px;
		border-bottom-left-radius: 15px;
		box-shadow: 0px 1px 1px 1px black; 
	}
	.bunder-kanan{
		border-top-right-radius:15px;
		border-bottom-right-radius:15px;
	}
</style>
	<decorator:head/>
</head>
<body>

	<!-- <div class="container">Header</div>-->
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">Sia</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
    <div class="navbar-nav">
      <a class="nav-item nav-link active" href="#">Beranda <span class="sr-only">(current)</span></a>
      <a class="nav-item nav-link" href="/sia/form/mahasiswa.html">Data Mahasiswa</a>
      <a class="nav-item nav-link" href="/sia/form/jurusan.html">Data Jurusan</a>
      <a class="nav-item nav-link" href="#">
			<sec:authorize access="isAuthenticated()">
      		<sec:authentication property="principal.username"/>&nbsp;|&nbsp;
			<a href="${contextName}/j_spring_security_logout">Logout</a>
			</sec:authorize>
	</a>
      <a class="nav-item nav-link disabled" href="#"></a>
    </div>
  </div>
</nav>
	<decorator:body/>
	<script>
	function loadJurusan(select){
		$.ajax({
			url: '/sia/jurusan/',
			type: 'get',
			success: function(d){
				$(select).empty();
				$(select).append("<option value=''>-- Pilih Kode Jurusan --</option>");
				$(d).each(function(index, e) {
					$(select).append("<option value='"+e.id+"'>"+e.kode+" ("+e.nama+")</option>");
				});
			}
		});
	}
	loadData();
	function simpanData(nama_elemen,jenis){
		var data = getFormData($(nama_elemen));
		var metode = 'POST';
		if(jenis!='simpan'){
			metode='PUT';
		}
		$.ajax({
			url: '/sia/mahasiswa/',
			type: metode,
			dataType: 'json',
			contentType: 'application/json',
			data:JSON.stringify(data),
			success: function(d){
				loadData();
			},
			error: function(d){
				alert('Error qaqa');
			}
		});
		$('.close').click();
	}
	
	function loadDataTable() {
		$.ajax({
			url: '/sia/mahasiswa/',
			type: 'GET',
			success: function(d) {
				$('#tabledata').DataTable( {
				    data: d,
				    columns: [
				        { data: 'nim' },
				        { data: 'nama' },
				        { data: 'kdjur' },
				        { data: 'alamat' },
				        { data: 'tanggal lahir' }
				    ]
				} );
			}
		});
	}
	
		function loadData() {
			$.ajax({
				url: '/sia/mahasiswa/',
				type: 'GET',
				beforeSend: function(){
					$("#list").hide();
				},
				success: function(d) {
					$('#list').empty();
					var table = $("<table/>");
					var thead = $("<thead/>");
					var tr = $("<tr/>");
					var th = $("<th/>");
					$(th).text("NIM");
					$(tr).append(th);
					th = $("<th/>");
					$(th).text("Nama");
					$(tr).append(th);
					th = $("<th/>");
					$(th).text("Kode Jurusan");
					$(tr).append(th);
					th = $("<th/>");
					$(th).text("Alamat");
					$(tr).append(th);
					$(thead).append(tr);
					$(table).append(thead);
					
					$(d).each(function(index, element) {
						tr = $("<tr/>");
						var td = $("<td/>");
						$(td).text(element.nim);
						$(tr).append(td);
						
						td = $("<td/>");
						$(td).text(element.nama);
						$(tr).append(td);

						td = $("<td/>");
						$(td).text(element.kdjur);
						$(tr).append(td);
						
						td = $("<td/>");
						$(td).text(element.alamat);
						$(tr).append(td);
						

						td = $("<td/>");
						$(td).html("<button class='btn btn-primary' onclick='edit("+element.id+")' data-toggle='modal' data-target='#myModal');'>Edit</button>");
						$(tr).append(td);
						
						td = $("<td/>");
						$(td).html("<button class='btn btn-danger' onclick='hapus("+element.id+");'>Delete</button>");
						$(tr).append(td);
						
						$(table).append(tr);
						tr.slideDown("slow");
						
					});
					
					$('#list').append(table);
					$('#list').slideDown("slow");
					//$('#list').show();
				},
				error: function(d) {
					console.log("error");
				}
			});
		}
		
		function edit(d){
			$.ajax({
				url: '/sia/mahasiswa/'+d,
				type: 'GET',
				success: function(i) {
					$('#form-update #id').val(i.id);					
					$('#form-update #nama').val(i.nama);
					$('#form-update #kdjur').val(i.kdjur);
					$('#form-update #nim').val(i.nim);
					$('#form-update #alamat').val(i.alamat);
					$('#form-update #tgl').val(i.tanggalLahir);
				}
			});
		}
		
		function hapus(id){
			if(confirm('Anda yakin ingin menghapus data id '+id+'?')){
				$.ajax({
					type:'delete',
					url: '/sia/mahasiswa/'+id,
					success: function(d){
						loadData();
						alert('Data dengan id '+id+' telah terhapus');
					},
					error: function(d){
						
					}
				});
			}
		}
		function cari(n){
			if(n==""){
				loadData();
			}else{
				$.ajax({
					url: '/sia/mahasiswa/search/'+n,
					type: 'GET',
					success: function(d) {
						$('#list').empty();
						var table = $("<table/>");
						var thead = $("<thead/>");
						var tr = $("<tr/>");
						var th = $("<th/>");
						$(th).text("NIM");
						$(tr).append(th);
						th = $("<th/>");
						$(th).text("Nama");
						$(tr).append(th);
						th = $("<th/>");
						$(th).text("Kode Jurusan");
						$(tr).append(th);
						th = $("<th/>");
						$(th).text("Alamat");
						$(tr).append(th);
						$(thead).append(tr);
						$(table).append(thead);
						console.log('banyaknya '+d.length);
						if(d.length===0){
							tr = $("<tr/>");
							var td = $("<td/>");
							$(td).text("Tidak ada data "+n);
							$(tr).append(td);
							console.log('0 gan '+d.length);

							$(table).append(tr);
						}
						var ii = 0;
						$(d).each(function(index, element) {
							tr = $("<tr/>");
							var td = $("<td/>");
							$(tr).hide();
							$(td).text(element.nim);
							$(tr).append(td);
							
							td = $("<td/>");
							$(td).text(element.nama);
							$(tr).append(td);
							
							td = $("<td/>");
							$(td).text(element.kdjur);
							$(tr).append(td);
							
							td = $("<td/>");
							$(td).text(element.alamat);
							$(tr).append(td);
							
	
							td = $("<td/>");
							$(td).html("<button class='btn btn-primary' onclick='edit("+element.id+")' data-toggle='modal' data-target='#myModal');'>Edit</button>");
							$(tr).append(td);
							
							td = $("<td/>");
							$(td).html("<button class='btn btn-danger' onclick='hapus("+element.id+");'>Delete</button>");
							$(tr).append(td);
							
							$(table).append(tr);
							$(tr).slideDown(ii);
							console.log(ii);
							ii+=100;
						});
						
						$('#list').append(table);

						//console.log("nyari "+n+", "+urlnya);
					},
					error: function(d) {
						console.log("error");
						loadData();
					}
				});
			}
		}
		$(document).ready(function(){
			loadJurusan("#kdjur");		
		});
	</script>
</body>
</html>