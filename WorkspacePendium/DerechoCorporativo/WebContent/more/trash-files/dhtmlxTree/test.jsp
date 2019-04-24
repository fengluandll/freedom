

<html>
<head>



<link rel="STYLESHEET" type="text/css"
	href="<%= request.getContextPath() %>/js/dhtmlxTree/codebase/dhtmlxtree.css">
<script
	src="<%= request.getContextPath() %>/js/dhtmlxTree/codebase/dhtmlxcommon.js"></script>
<script
	src="<%= request.getContextPath() %>/js/dhtmlxTree/codebase/dhtmlxtree.js"></script>

</head>
<body>

	<div id="treeboxbox_tree"
		style="width: 250px; height: 218px; background-color: #f5f5f5; border: 1px solid Silver;; overflow: auto;" />

	<script>

		function treeOnSelect(id) {

			var fileNm = getFile(id)||"";

			alert(fileNm);

	    	//doLog("Item " + tree.getItemText(id) + " was selected");
		};

		function doOnClick(id)
		{   
			var fileNm = getFile(id)||"";

			alert(fileNm);
			
			/*if(fileNm!="")
			{
				if(!tree.getUserData(id,"type") && tree.getUserData(id,"popup")=='N'){
					//parent.frames["contentFrame"].document.location.reload();
					parent.frames["contentFrame"].document.location = "<ww:url value='/'/>" + fileNm  
				}
				else
					window.open(fileNm)
			}*/
		}
		
		function getFile(id){
			
			/*while(!tree.getUserData(id,"action") && tree.getLevel(id)>0){
				id = tree.getParentId(id)
			}
			return tree.getUserData(id,"action");
			*/

			alert(id);
			alert(tree.getUserData(id,"im2"));
			
			while(!tree.getUserData(id,"im2") && tree.getLevel(id)>0){
				id = tree.getParentId(id);
			}
			return tree.getUserData(id,"im2");
			
		}
		
		
		tree = new dhtmlXTreeObject({
		    skin: "dhx_skyblue",
		    parent: "treeboxbox_tree",
		    image_path: "<%= request.getContextPath() %>/js/dhtmlxTree/codebase/imgs/csh_bluebooks/",
		    checkbox: false,
		    xml: "menu_sample.xml"
		}); 

		tree.setOnClickHandler(treeOnSelect);
		//tree.setOnDblClickHandler(treeOnSelect);
		
	</script>




</body>
</html>