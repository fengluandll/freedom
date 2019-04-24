// jQuery Alert Dialogs Plugin
//
// Version 1.1
//
// Cory S.N. LaViska
// A Beautiful Site (http://abeautifulsite.net/)
// 14 May 2009
//
// Visit http://abeautifulsite.net/notebook/87 for more information
//
// Usage:
//		jAlert( message, [title, callback] )
//		jConfirm( message, [title, callback] )
//		jPrompt( message, [value, title, callback] )
// 
// History:
//
//		1.00 - Released (29 December 2008)
//
//		1.01 - Fixed bug where unbinding would destroy all resize events
//
// License:
// 
// This plugin is dual-licensed under the GNU General Public License and the MIT License and
// is copyright 2008 A Beautiful Site, LLC. 
//
(function($) {
	
	$.alerts = {
		
		// These properties can be read/written by accessing $.alerts.propertyName from your scripts at any time
		
		verticalOffset: -75,                // vertical offset of the dialog from center screen, in pixels
		horizontalOffset: 0,                // horizontal offset of the dialog from center screen, in pixels/
		repositionOnResize: true,           // re-centers the dialog on window resize
		overlayOpacity: .5,                // transparency level of overlay
		opacity:0.5,
		overlayColor: '#CCC',               // base color of overlay
		draggable: false,                    // make the dialogs draggable (requires UI Draggables plugin)
		okButton: '&nbsp;Aceptar&nbsp;',    // text for the OK button
		cancelButton: '&nbsp;Cancelar&nbsp;', // text for the Cancel button
		okButton2: '&nbsp;Aprobada&nbsp;',    // text for the OK button
		cancelButton2: '&nbsp;No Aprobada&nbsp;', // text for the Cancel button
		dialogClass: null,                  // if specified, this class will be applied to all dialogs
		
		// Public methods
		
		alert: function(message, title, callback) {
			if( title == null ) title = 'Alert';
			$.alerts._show(title, message, null, 'alert',false,false, function(result) {
				if( callback ) callback(result);
			});
		},
		
		confirm: function(message, title, callback) {
			if( title == null ) title = 'Confirm';
			$.alerts._show(title, message, null, 'confirm',false,false, function(result) {
				if( callback ) callback(result);
			});
		},
		
		confirm2: function(message, title, callback) {
			if( title == null ) title = 'Confirm';
			$.alerts._show(title, message, null, 'confirm2',false,false, function(result) {
				if( callback ) callback(result);
			});
		},
			
		prompt: function(message, value, title, callback) {
			if( title == null ) title = 'Prompt';
			$.alerts._show(title, message, value, 'prompt',false,false, function(result) {
				if( callback ) callback(result);
			});
		},
		
		/*Attach: function(message, title, callback) {
			if( title == null ) title = 'Attach File';
			$.alerts._show(title, message, null, 'attach', function(result, Name, Description, AttachFile) {
				if( callback ) callback(result, Name, Description, AttachFile);
			});
		},*/
		
        Attach: function(message, title, mostrarTitulo, mostrarDescripcion, callback) {
			if( title == null ) title = 'Attach File';
			$.alerts._show(title, message, null, 'attach',mostrarTitulo, mostrarDescripcion, function(result, Name, Description, AttachFile) {
				if( callback ) callback(result, Name, Description, AttachFile);
			});
		},
		
		// Private methods
		
		_Validate : function(msg){
		    var value;
		    var mensaje = '';
		    var isOK   = true;
            switch (msg)
            {
                case "TITULO":
                    value = $("#popup_prompt").val();
                    break;
                case "DESCRIPCION":
                    value = $("#AttachFile_Description").val();
                    break;
                case "FILE":
                    value = $("#AttachFile_File").val();
            }
            if (value == null || value == "" )
            {
                isOK = false;
                if (msg=="FILE")
                    mensaje = "Debe seleccionar un archivo." ;
                else
                    mensaje = "Debe especificar \"" + msg + "\" para el archivo."; 
            }
            else
            {
                if (msg=="FILE")
                {
                    ext = value.split('.');
                    value = ext[ext.length-1];
                    /*if (value != "xls" && value != "csv")
                    {
                        isOK = false;
                        mensaje = "Debe seleccionar un archivo válido.";
                    }*/
                }
            }
            if (!isOK)
            {
                $("#AttachFile_MsgFrm_msg").html( mensaje );
                $("#AttachFile_MsgFrm").show('slow');
            }
            
            var xOut = eval( '('+'{"fx":function(){ $("#AttachFile_MsgFrm_msg").html( "" ); $("#AttachFile_MsgFrm").fadeOut("slow"); }}'+')' );
    
            setTimeout( xOut.fx,10000 );  
            return isOK;
		},
		
		_show: function(title, msg, value, type,mostrarTitulo,mostrarDescripcion, callback) {
			
			$.alerts._hide();
			$.alerts._overlay('show');
			
			$("BODY").append(
			  '<div id="popup_container" style="font-family: Arial, sans-serif; font-size: 12px; min-width: 300px; max-width: 600px; background: #FFF; border: solid 5px #999; color: #000; -moz-border-radius: 5px; -webkit-border-radius: 5px; border-radius: 5px;">' +
			    '<h1 id="popup_title" style="font-size: 14px; font-weight: bold; text-align: center; line-height: 1.75em; color: #666; background: #CCC url(images/title.gif) top repeat-x; border: solid 1px #FFF; border-bottom: solid 1px #999; cursor: default; padding: 0em; margin: 0em;"></h1>' +
			    '<div id="popup_content" style="background: 16px 16px no-repeat url(./images/info.gif); padding: 1em 1.75em; margin: 0em;"> <br />' +
			      '<div id="popup_message"></div><br />' +
				'</div>' +
			  '</div>');
			
			if( $.alerts.dialogClass ) $("#popup_container").addClass($.alerts.dialogClass);
			
			// IE6 Fix
			var pos = ($.browser.msie && parseInt($.browser.version) <= 6 ) ? 'absolute' : 'fixed'; 
			
			$("#popup_container").css({
				position: pos,
				zIndex: 99999,
				padding: 0,
				margin: 0
			});
			
			$("#popup_title").text(title);
			$("#popup_content").addClass(type);
			$("#popup_message").text(msg);
			$("#popup_message").html( $("#popup_message").text().replace(/\n/g, '<br />') );
			
			$("#popup_container").css({
				minWidth: $("#popup_container").outerWidth(),
				maxWidth: $("#popup_container").outerWidth()
			});
			
			$.alerts._reposition();
			$.alerts._maintainPosition(true);
			
			switch( type ) {
				case 'alert':
					$("#popup_message").after('<br /><div id="popup_panel" align="center"><input type="button" style=" font-size:12px; font-family: Verdana, Arial, Helvetica, sans-serif; text-align:center; color:black; background:Silver; border:0px; width:80px; height:19px; cursor:pointer;" value="' + $.alerts.okButton + '" id="popup_ok" /></div>');
					$("#popup_ok").click( function() {
						$.alerts._hide();
						callback(true);
					});
					$("#popup_ok").focus().keypress( function(e) {
						if( e.keyCode == 13 || e.keyCode == 27 ) $("#popup_ok").trigger('click');
					});
				break;
				case 'confirm':
					$("#popup_message").after('<br /><div id="popup_panel" align="center"><input type="button" style=" font-size:12px; font-family: Verdana, Arial, Helvetica, sans-serif; text-align:center; color:black; background:Silver; border:0px; width:80px; height:19px; cursor:pointer;" value="' + $.alerts.okButton + '" id="popup_ok" /> <input type="button" style=" font-size:12px; font-family: Verdana, Arial, Helvetica, sans-serif; text-align:center;  color:black; background:Silver; border:0px; width:80px; height:19px; cursor:pointer;" value="' + $.alerts.cancelButton + '" id="popup_cancel" /></div>');
					$("#popup_ok").click( function() {
						$.alerts._hide();
						if( callback ) callback(true);
					});
					$("#popup_cancel").click( function() {
						$.alerts._hide();
						if( callback ) callback(false);
					});
					$("#popup_ok").focus();
					$("#popup_ok, #popup_cancel").keypress( function(e) {
						if( e.keyCode == 13 ) $("#popup_ok").trigger('click');
						if( e.keyCode == 27 ) $("#popup_cancel").trigger('click');
					});
				break;
				case 'confirm2':
					$("#popup_message").after('<br /><div id="popup_panel" align="center"><input type="button" style=" font-size:12px; font-family: Verdana, Arial, Helvetica, sans-serif; text-align:center; color:black; background:Silver; border:0px; width:90px; height:19px; cursor:pointer;" value="' + $.alerts.okButton2 + '" id="popup_ok" /> <input type="button" style=" font-size:12px; font-family: Verdana, Arial, Helvetica, sans-serif; text-align:center;  color:black; background:Silver; border:0px; width:90px; height:19px; cursor:pointer;" value="' + $.alerts.cancelButton2 + '" id="popup_cancel" /></div>');
					$("#popup_ok").click( function() {
						$.alerts._hide();
						if( callback ) callback(true);
					});
					$("#popup_cancel").click( function() {
						$.alerts._hide();
						if( callback ) callback(false);
					});
					$("#popup_ok").focus();
					$("#popup_ok, #popup_cancel").keypress( function(e) {
						if( e.keyCode == 13 ) $("#popup_ok").trigger('click');
						if( e.keyCode == 27 ) $("#popup_cancel").trigger('click');
					});
				break;
				case 'prompt':
					$("#popup_message").append('<br /><input type="text" size="30" id="popup_prompt" />').after('<div id="popup_panel"><input type="button" style=" font-size:12px; font-family: Verdana, Arial, Helvetica, sans-serif; text-align:center; color:black; background:Silver; border:0px; width:80px; height:19px; cursor:pointer;" value="' + $.alerts.okButton + '" id="popup_ok" /> <input type="button" value="' + $.alerts.cancelButton + '" id="popup_cancel" /></div>');
					$("#popup_prompt").width( $("#popup_message").width() );
					$("#popup_ok").click( function() {
						var val = $("#popup_prompt").val();
						$.alerts._hide();
						if( callback ) callback( val );
					});
					$("#popup_cancel").click( function() {
						$.alerts._hide();
						if( callback ) callback( null );
					});
					$("#popup_prompt, #popup_ok, #popup_cancel").keypress( function(e) {
						if( e.keyCode == 13 ) $("#popup_ok").trigger('click');
						if( e.keyCode == 27 ) $("#popup_cancel").trigger('click');
					});
					if( value ) $("#popup_prompt").val(value);
					$("#popup_prompt").focus().select();
				break;
				case 'attach':
				    var PopupContenedor='';
				    
				        PopupContenedor += '<br />';
				        PopupContenedor += '<div id="popup_panel" align="center">';
				        PopupContenedor += '<table>';
				        if(mostrarTitulo)
				        {
				            PopupContenedor += '<tr>';
				            PopupContenedor += '<td align="left">T&iacute;tulo: </td>';
				            PopupContenedor += '<td style="width: 200px">';
				            PopupContenedor += '<input type="text" size="100" class="Box" id="popup_prompt" />';
				            PopupContenedor += '</td>';
				            PopupContenedor += '</tr>';
				        }
				        if(mostrarDescripcion)
				        {
				            PopupContenedor += '<tr>';
				            PopupContenedor += '<td align="left">Descripci&oacute;n:</td>';
				            PopupContenedor += '<td style="width: 200px">';
				            PopupContenedor += '<input id="AttachFile_Description" class="Box" size="100" />';
				            PopupContenedor += '</td>';
				            PopupContenedor += '</tr>';
				        }
				        PopupContenedor += '<tr><td align="left">Archivo:</td><td style="width: 200px"><input id="AttachFile_File" type="file" name="AttachFile_File"  class="Box" size="100"></td></tr><tr><td colspan="2"><div id="AttachFile_MsgFrm" style="display:none; width: 300px" class="ui-state-error ui-corner-all"><p><span class="ui-icon ui-icon-alert" style="float: left; margin-right: 0.3em;"></span><strong>Imposible subir el archivo:</strong><span id="AttachFile_MsgFrm_msg"></span></p></div><div id="AttachFile_Load" style="text-align:left; display:none">uploading <img src="./images/ajax-loader-div.gif"/></div></td></tr></table></br><input type="button" style=" font-size:12px; font-family: Verdana, Arial, Helvetica, sans-serif; text-align:center; color:black; background:Silver; border:0px; width:80px; height:19px; cursor:pointer;" value="' + $.alerts.okButton + '" id="popup_ok" /> <input type="button" style=" font-size:12px; font-family: Verdana, Arial, Helvetica, sans-serif; text-align:center;  color:black; background:Silver; border:0px; width:80px; height:19px; cursor:pointer;" value="' + $.alerts.cancelButton + '" id="popup_cancel" /></div>';
				        
				        $("#popup_message").after(PopupContenedor);
				        
					$("#popup_ok").click( function() {
					if(mostrarTitulo){
                        var Name = $("#popup_prompt").val();
                        }
                        if(mostrarDescripcion){
                        var Description = $("#AttachFile_Description").val();
                        }
                        var AttachFile = $("#AttachFile_File").val();
                        $( "#AttachFile_File"  ).ajaxStart(function(){
			                $("#AttachFile_Load").show();
		                    }).ajaxComplete(function(){
			                $("#AttachFile_Load").hide();
		                });
		                if(mostrarTitulo){
					    if (!$.alerts._Validate("TITULO")) return;
					    }
					    if(mostrarDescripcion){
					    if (!$.alerts._Validate('DESCRIPCION')) return;
					    }
                        if (!$.alerts._Validate('FILE')) return;
						if( callback ) 
						{
						    callback(true, Name, Description, AttachFile);
						    $.alerts._hide();
						}
						
					});
				    $("#popup_cancel").click( function() {
						$.alerts._hide();
						if( callback ) callback(false);
					});
					$("#popup_ok").focus();
					$("#popup_ok, #popup_cancel").keypress( function(e) {
						if( e.keyCode == 13 ) $("#popup_ok").trigger('click');
						if( e.keyCode == 27 ) $("#popup_cancel").trigger('click');
					});
					
				
				break;
			}
			
			// Make draggable
			if( $.alerts.draggable ) {
				try {
					$("#popup_container").draggable({ handle: $("#popup_title") });
					$("#popup_title").css({ cursor: 'move' });
				} catch(e) { /* requires jQuery UI draggables */ }
			}
		},
		
		_hide: function() {
			$("#popup_container").remove();
			$.alerts._overlay('hide');
			$.alerts._maintainPosition(false);
		},
		
		_overlay: function(status) {
			switch( status ) {
				case 'show':
					$.alerts._overlay('hide');
					$("BODY").append('<div id="popup_overlay"></div>');
					$("#popup_overlay").css({
						position: 'absolute',
						zIndex: 99998,
						top: '0px',
						left: '0px',
						width: '100%',
						height: $(document).height(),
						background: $.alerts.overlayColor,
						opacity: $.alerts.overlayOpacity
					});
				break;
				case 'hide':
					$("#popup_overlay").remove();
				break;
			}
		},
		
		_reposition: function() {
			var top = (($(window).height() / 2) - ($("#popup_container").outerHeight() / 2)) + $.alerts.verticalOffset;
			var left = (($(window).width() / 2) - ($("#popup_container").outerWidth() / 2)) + $.alerts.horizontalOffset;
			if( top < 0 ) top = 0;
			if( left < 0 ) left = 0;
			
			// IE6 fix
			if( $.browser.msie && parseInt($.browser.version) <= 6 ) top = top + $(window).scrollTop();
			
			$("#popup_container").css({
				top: top + 'px',
				left: left + 'px'
			});
			$("#popup_overlay").height( $(document).height() );
		},
		
		_maintainPosition: function(status) {
			if( $.alerts.repositionOnResize ) {
				switch(status) {
					case true:
						$(window).bind('resize', $.alerts._reposition);
					break;
					case false:
						$(window).unbind('resize', $.alerts._reposition);
					break;
				}
			}
		}
		
	}
	
	// Shortuct functions
	jAlert = function(message, title, callback) {
		$.alerts.alert(message, title, callback);
	}
	
	jConfirm = function(message, title, callback) {
		$.alerts.confirm(message, title, callback);
	};
	
	jConfirm2 = function(message, title, callback) {
		$.alerts.confirm2(message, title, callback);
	};
		
	jPrompt = function(message, value, title, callback) {
		$.alerts.prompt(message, value, title, callback);
	};
	
    jAttach = function(message, title, mostrarTitulo,mostrarDescripcion, callback) {
		$.alerts.Attach(message, title, mostrarTitulo, mostrarDescripcion, callback);
	};
	
})(jQuery);