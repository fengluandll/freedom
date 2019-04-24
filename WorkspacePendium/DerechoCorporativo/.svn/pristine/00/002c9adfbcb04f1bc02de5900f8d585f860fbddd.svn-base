<html>
<head>


<script type="text/javascript"
	src="<%= request.getContextPath() %>/js/jquery/jquery-1.4.3.min.js"></script>

<style type="text/css">
.slideDownbox,.slideUpbox,.slideTogglebox {
	float: left;
	padding: 8px;
	margin: 16px;
	border: 1px solid red;
	width: 200px;
	height: 50px;
	background-color: #000000;
	color: white;
}

.clear {
	clear: both;
}
</style>

</head>

<body>

	<h1>jQuery slideUp(), slideDown() and slideToggle() example</h1>


	<div class="clear">
		<h3>slideDown() example</h3>

		<div class="slideDownbox">Click me - slideDown()</div>

		<div class="slideDownbox">Click me - slideDown()</div>
	</div>

	<div class="clear">
		<h3>slideUp() example</h3>

		<div class="slideUpbox">Click me - slideUp()</div>

		<div class="slideUpbox">Click me - slideUp()</div>
	</div>

	<div class="clear">
		<h3>slideToggle() example</h3>
		<button id=slideToggle>slideToggle()</button>
		<br />
		<div class="slideTogglebox">slideToggle()</div>

		<div class="slideTogglebox">slideToggle()</div>

	</div>

	<br />
	<br />

	<div class="clear">
		<button id=reset>Reset</button>
	</div>

	<script type="text/javascript">
 
$(".slideDownbox").click(function () {
   $(this).hide().slideDown('slow');
});
 
$(".slideUpbox").click(function () {
   $(this).slideUp(2000);
});
 
$("#slideToggle").click(function () {
   $('.slideTogglebox').slideToggle();
});
 
$("#reset").click(function(){
	location.reload();
});
</script>

</body>
</html>