/*tables for all problems*/
$(document).ready(function() {
	$('#problem_tables').DataTable();
});
// For demo to fit into DataTables site builder...
$('#problem_tables').removeClass('display').addClass(
		'table table-striped table-bordered');


// For demo to fit into DataTables site builder...
$('#submissions_tables').removeClass('display').addClass(
		'table table-striped table-bordered');



/* tables for contest problems */
$(document).ready(function() {
	$('#contest_problems').DataTable({
		searching : false,
		paging : false,
		info : false,
		order : [ [ 3, "desc" ] ]
	});
});
// For demo to fit into DataTables site builder...
$('#contest_problems').removeClass('display').addClass(
		'table table-striped table-bordered');




// ck editor
ClassicEditor
.create( document.querySelector( '#ck_edit_field' ) )
.then( editor => {
        console.log( editor );
} )
.catch( error => {
        console.error( error );
} );
