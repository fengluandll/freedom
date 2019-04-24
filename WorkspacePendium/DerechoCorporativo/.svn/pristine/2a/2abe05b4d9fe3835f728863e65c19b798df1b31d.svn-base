<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script src='<c:out value="${applicationBean.contextPath}"/>/js/purr/jquery.purr.js'></script>

<script type="text/javascript">
    
    //alert('include purr');
    
    function showGrowl(title, message) {
        
        showGrowl(title, message, 2000);
    }
    
    function showGrowl(title, message, customRemoveTimer) {
        
        jQuery.noConflict();
        
        //alert('OK');
        
        var img = '<c:out value="${applicationBean.contextPath}"/>/js/purr/info.png';
        
        var notice = '<div class="notice">'
                            + '<div class="notice-body">' 
                                    + '<img src="' + img + '" alt="" />'
                                    + '<h3>' + title + '</h3>'
                                    + '<p style="color:#ffffff">' + message + '</p>'
                            + '</div>'
                            + '<div class="notice-bottom">'
                            + '</div>'
                    + '</div>';
            
            
            //$( notice ).purr();
            
            //jQuery( "notice" ).purr();
            
    
            
          $( notice ).purr(
                  {
                    usingTransparentPNG: true,
                    removeTimer : customRemoveTimer
                  }
          );
        
        
        
        /*
       PARAMETERS
            fadeInSpeed
            int - default: 500 
            The duration of the fade in animation in miliseconds.
            fadeOutSpeed
            int - default: 500 
            The duration of the fade out animationin miliseconds.
            removeTimer
            int - default: 4000 
            The timeout, in miliseconds, before the notice is removed once it is the top non-sticky notice in the list.
            isSticky
            bool - default: false 
            Whether the notice should fade out on its own or wait to be manually closed.
            usingTransparentPNG
            bool - default: false 
            Whether or not the notice is using transparent .png images in its styling.
    
         */
        
        
        
       
            //$ = jQuery; //undo .conflict(); 
        
    }
    
    /*
    /*
    
   		$( document ).ready( function ()
			{
				$( '.show-example' ).click( function () 
					{
						var notice = '<div class="notice">'
								  + '<div class="notice-body">' 
									  + '<img src='<c:out value="${applicationBean.contextPath}"/>/js/purr/info.png' alt="" />'
									  + '<h3>Purr Example</h3>'
									  + '<p>This a normal Purr. It will fade out on its own.</p>'
								  + '</div>'
								  + '<div class="notice-bottom">'
								  + '</div>'
							  + '</div>';
							  
						$( notice ).purr(
							{
								usingTransparentPNG: true
							}
						);
						
						return false;
					}
				);
				
				$( '.show-sticky' ).click( function () 
					{
						var notice = '<div class="notice">'
								  + '<div class="notice-body">' 
									  + '<img src="./purr-example/info.png" alt="" />'
									  + '<h3>"Sticky" Purr Example</h3>'
									  + '<p>This a "sticky" Purr. It will not fade out on its own. You must close it manually.</p>'
								  + '</div>'
								  + '<div class="notice-bottom">'
								  + '</div>'
							  + '</div>';
							  
						$( notice ).purr(
							{
								usingTransparentPNG: true,
								isSticky: true
							}
						);
						
						return false;
					}
				);
			}
		);
            */
   	</script>
	
	<style type="text/css">
                /*
		body {
			margin: 0;
			padding: 0;
			font-family: Georgia;
			font-size: 0.9em;
			line-height: 1.4em;
		}
		*/
                
		#example {
			position: relative;
			width: 500px;
			padding: 20px;
		}
		/*
		p {
			margin: 7px 0 0 0;
		}*/
		
		#purr-container {
			position: fixed;
			top: 0;
			right: 0;
		}
		
		.notice {
			position: relative;
			width: 324px;
		}
		.notice .close	{
                        position: absolute; 
                        top: 12px; 
                        right: 12px; 
                        display: block; 
                        width: 18px; 
                        height: 17px; 
                        text-indent: -9999px; 
                        background: url('<c:out value="${applicationBean.contextPath}"/>/js/purr/purrClose.png') no-repeat 0 10px;
                }
		
		.notice-body {
			min-height: 50px;
			padding: 22px 22px 0 22px;
			background: url('<c:out value="${applicationBean.contextPath}"/>/js/purr/purrTop.png') no-repeat left top;
			color: #f9f9f9;
		}
			.notice-body img	{width: 50px; margin: 0 10px 0 0; float: left;}
			.notice-body h3	{margin: 0; font-size: 14px;}
			.notice-body p		{margin: 5px 0 0 60px; font-size: 12px; line-height: 1.4em;}
		
		.notice-bottom {
			height: 22px;
			background: url('<c:out value="${applicationBean.contextPath}"/>/js/purr/purrBottom.png') no-repeat left top;
		}
	</style>