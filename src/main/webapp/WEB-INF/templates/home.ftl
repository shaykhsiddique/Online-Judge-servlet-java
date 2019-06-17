<#include "layouts/header.ftl">

	<!-- body part -->
	<div class="row">
		<!-- left part -->
		<div class="col-sm-3">
			<nav id="left_announce_panel">
		    	<ul class="nav nav-pills nav-stacked" data-spy="affix" data-offset-top="1">
		      	<div class="panel panel-default">
		        	<div class="panel-heading navbar_clr"> <strong><i class='fas'>&#xf0a1;</i> Announcements</strong></div>
		        	<div class="panel-body" id="left_panelbody">
		            	<li><a href="#section1"><i class="fa">&#xf064;</i> Notice 1</a></li>
		            	<li><a href="#section2"><i class="fa">&#xf064;</i> Notice 2</a></li>
		            	<li><a href="#section3"><i class="fa">&#xf064;</i> Notice 3</a></li>
		            	<li><a href="#section4"><i class="fa">&#xf064;</i> Notice 4</a></li>
		         	</div>
		      	</div>
		      </ul>
		    </nav>
		</div>
		<!-- middle part -->
	</div>

<div class="container">
	<div class="alert alert-success">
    	<strong>Success!</strong> This alert box could indicate a successful or positive action.
  	</div>
</div>
<h1>${message} using freemarker</h1>
</body>
</html>