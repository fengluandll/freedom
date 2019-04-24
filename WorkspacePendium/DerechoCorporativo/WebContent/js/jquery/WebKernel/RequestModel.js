﻿var ClassRequestModel = function () {
    var _this = this;
    this.LastTimeTransaction = new Date();

    var handler = function (action) {
        return 'iJJCouncilProjects/HandlerAction.ashx?action=' + action;
    }

    var requestConfig = { dataType: 'json'
	                , type: 'POST'
	                , complete: function (xhttp, text) {

	                }
			        , error: function (e) {
			            alert(e);
			        }
    };
    
    var request = function (action, args, config) {

        for (var pty in requestConfig)
            if (config[pty] == null)
                config[pty] = requestConfig[pty];

        LastTimeTransaction = new Date();

        if(config.engaged==null || config.engaged)
        	Util.loading(true,1,config.engaged==null?"":config.engaged.msg==null?"":config.engaged.msg);
        
        $.ajax({
            dataType: config.dataType,
            success: config.response,            
            complete:  function(){
            	Util.loading(true,0,"");            	
            },
            error: config.error,
            url: action,
            data: args,
            type: config.type
        });
    }

    this.request = request;
}

var Request = new ClassRequestModel();

