<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div id="wistia_prifdcj1ky" class="wistia_embed" style="width:240px;height:188px;">&nbsp;</div>
<script charset="ISO-8859-1" src="//fast.wistia.com/assets/external/E-v1.js"></script>
<script>
wistiaEmbed = Wistia.embed("prifdcj1ky", {
  videoFoam: true
});
wistiaEmbed.ready( function() {
	  var all_cookies = document.cookie.split(';'), // gets the value of the cookies on the page
	    cookie_str = "resume_video=",
	    resume_cookie = does_resume_cookie_exist(all_cookies);


	  function does_resume_cookie_exist(cookie_arr) {
	    var i, curr_string, found;
	      for (i = 0; i < cookie_arr.length; i++) {
	      curr_string = cookie_arr[i];
	        if (curr_string.substring(0,5) === cookie_str.substring(0,5)) {
	          // we've found it!
	          found = curr_string;
	          break;
	        }
	      }
	    return found;
	  }

	  function set_cookie_time(t) {
		  alert("Tiempo: "+t.toString());
		 
	    document.cookie = cookie_str + t.toString(); // this takes the time (t) and sets the cookie with that time  
	  }

	  if (resume_cookie) {
	    num = resume_cookie.split('=')[1];
	    start_time = parseInt(num);
//	    alert("Tiempo en que se quedo "+start_time);
	    wistiaEmbed.time(start_time).play(); // plays the video at the specific time defined in the cookie upon return to the page
	  } else {
	    set_cookie_time(0);  // places a cookie on the visitor
	    wistiaEmbed.play();  // starts the video from the beginning
	  }
	  wistiaEmbed.bind("timechange", function(t) { // on timechange, reset cookie
	    set_cookie_time(t);
	  });
	  wistiaEmbed.bind("end", function() { // if person has watched the entire video, sets the video to beginning upon retun
	    set_cookie_time(0);
	    alert("The video ended!");
	  });
	});
</script>	
</body>
</html>