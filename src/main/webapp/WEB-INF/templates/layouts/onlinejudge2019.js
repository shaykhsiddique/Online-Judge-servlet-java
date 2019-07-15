/*tables for all problems*/
$(document).ready(function() {
	$('#problem_tables').DataTable();
});
// For demo to fit into DataTables site builder...
$('#problem_tables').removeClass('display').addClass(
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

// timer count down codes

var countDownDate = new Date("July 19, 2019 15:37:25").getTime();

// Update the count down every 1 second
var x = setInterval(function() {

	// Get today's date and time
	var now = new Date().getTime();

	// Find the distance between now and the count down date
	var distance = countDownDate - now;

	// Time calculations for days, hours, minutes and seconds
	var days = Math.floor(distance / (1000 * 60 * 60 * 24));
	var hours = Math.floor((distance % (1000 * 60 * 60 * 24))
			/ (1000 * 60 * 60));
	var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
	var seconds = Math.floor((distance % (1000 * 60)) / 1000);

	// Output the result in an element with id="demo"
	if (days.toString().length < 2)
		days = "0" + days;
	if (hours.toString().length < 2)
		hours = "0" + hours;
	if (minutes.toString().length < 2)
		minutes = "0" + minutes;
	if (seconds.toString().length < 2)
		seconds = "0" + seconds;
	document.getElementById("days").innerHTML = days;
	document.getElementById("hours").innerHTML = hours;
	document.getElementById("minutes").innerHTML = minutes;
	document.getElementById("seconds").innerHTML = seconds;

	// If the count down is over, write some text
	if (distance < 0) {
		clearInterval(x);
		document.getElementById("timer").innerHTML = "Times Up.";
	}
}, 1000);
