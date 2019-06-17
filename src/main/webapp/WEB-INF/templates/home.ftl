<#include "layouts/header.ftl.html">

	<!-- body part -->
	<div class="row body_style">
		<!-- left part -->
		<div class="col-sm-3">
			<nav id="left_announce_panel">
		    	<ul class="nav nav-pills nav-stacked" data-spy="affix" data-offset-top="1">
		      	<div class="panel panel-default">
		        	<div class="panel-heading navbar_clr"> <strong><i class='fas'>&#xf0a1;</i> Announcements</strong></div>
		        	<div class="panel-body">
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
		<div class="col-sm-6 middle_home">
			<h3>Welcome to Online Judge</h3>
			<img src="https://codeclan.com/wp-content/uploads/elementor/thumbs/generic-laptop-banner-1-nvqowbp6r2u1ewn5ieecuihhqja7g7fuqyq5hq6bam.jpg" class="img-rounded" alt="Cinque Terre" width=100% height="230">
			<p>Here you will find hundreds of problems. They are like the ones used during programming contests, and are available in HTML and PDF formats. You can submit your sources in a variety of languages, trying to solve any of the problems available in our database.</p>
			<p>No comfort do written conduct at prevent manners on. Celebrated contrasted discretion him sympathize her collecting occasional. Do answered bachelor occasion in of offended no concerns. Supply worthy warmth branch of no ye. Voice tried known to as my to. Though wished merits or be. Alone visit use these smart rooms ham. No waiting in on enjoyed placing it inquiry. </p>
			<p>Both rest of know draw fond post as.</p>
		</div>
		<!-- right part -->
		<div class="col-sm-3">
			<div id="right_rank_panel" class="panel panel-default">
				<div class="panel-heading navbar_clr"> <strong><i class='fas'>&#xf559;</i> Top rated</strong></div>
				<div class="panel-body">
    				<table class="table">
    					<thead>
    						<tr>
        						<th>Rank</th>
        						<th>Name</th>
        						<th>Ratings</th>
      						</tr>
    					</thead>
    					<#list 1..5 as idx>
	    					<tr>
						        <td>${idx}</td>
						        <td>Mash</td>
						        <td>${445+idx}</td>
	      					</tr>    						
    					</#list>
					</table>
			</div>
		</div>
		<!-- end part -->
	</div>
</body>
</html>