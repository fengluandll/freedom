<%@page import="mx.com.televisa.derechocorporativo.modules.reports.tenecascada.TenCascRecord"%>
<%@page import="java.util.ArrayList"%>
<%@page import="mx.com.televisa.derechocorporativo.util.StringUtils"%>
<%@page import="mx.com.televisa.derechocorporativo.model.Catalog"%>
<%@page import="mx.com.televisa.derechocorporativo.model.CatalogElement"%>
<%@page import="java.util.List"%>
<%@page import="mx.com.televisa.derechocorporativo.modules.reports.tenecascada.TenenciaCascada"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="mx.com.televisa.derechocorporativo.components.JSCal"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<c:out value="${applicationBean.contextPath}"/>/js/webix/webixCustom.css" type="text/css" charset="utf-8">
<script src="<c:out value="${applicationBean.contextPath}"/>/js/webix/webix.js" type="text/javascript" charset="utf-8"></script>

<%@include file="/css/kaz-styles.jsp"%>
<script type="text/javascript" src="<c:out value="${applicationBean.contextPath}"/>/js/jquery/jquery-2.1.4.min.js"></script>
<%@include file="/jsp/components/calendar/include_calendar.jsp"%>

<script type="text/javascript" src="<c:out value="${applicationBean.contextPath}"/>/js/ajax/simpleAjaxUtil.js"></script>

<script type="text/javascript" src="<c:out value="${applicationBean.contextPath}"/>/js/d3Grapho/d3.min.js"></script>

<link rel="stylesheet" href="<c:out value="${applicationBean.contextPath}"/>/js/jquery-treetable/jquery.treetable.css" />
<link rel="stylesheet" href="<c:out value="${applicationBean.contextPath}"/>/js/jquery-treetable/jquery.treetable.theme.default.css" />
<script src="<c:out value="${applicationBean.contextPath}"/>/js/jquery-treetable/jquery.treetable.js"></script>

<script type="text/javascript" src="<c:out value="${applicationBean.contextPath}"/>/js/basicPrimitivesOrgChart/js/jquery/jquery-ui-1.10.2.custom.min.js"></script>
<link href="<c:out value="${applicationBean.contextPath}"/>/js/basicPrimitivesOrgChart/js/jquery/ui-lightness/jquery-ui-1.10.2.custom.css" media="screen" rel="stylesheet" type="text/css" />

<!-- jQuery UI Layout -->
<script type="text/javascript" src="<c:out value="${applicationBean.contextPath}"/>/js/basicPrimitivesOrgChart/jquerylayout/jquery.layout-latest.min.js"></script>
<link rel="stylesheet" type="text/css" href="<c:out value="${applicationBean.contextPath}"/>/js/basicPrimitivesOrgChart/jquerylayout/layout-default-latest.css" />

<link href="<c:out value="${applicationBean.contextPath}"/>/js/basicPrimitivesOrgChart/css/primitives.latest.css?2100" media="screen" rel="stylesheet" type="text/css" />
<script  type="text/javascript" src="<c:out value="${applicationBean.contextPath}"/>/js/basicPrimitivesOrgChart/js/primitives.min.js?2100"></script>

<script src="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/jsp/components/sweet_alert/sweetalert.css">
<%@include file="/jsp/components/session_timeout/session_timeout.jsp"%>
<%

	request.setCharacterEncoding("UTF-8");

	String empresaId = request.getParameter("empresaId");

	String paramConsolida = 	(request.getParameter("hiddenConsolida") != null) ? request.getParameter("hiddenConsolida") : "";
	String paramSegmento = 		(request.getParameter("hiddenSegmento") != null) ? request.getParameter("hiddenSegmento") : "";
	String paramClasificacion = (request.getParameter("hiddenClasificacion") != null) ? request.getParameter("hiddenClasificacion") : "";
	String paramPais = 			(request.getParameter("hiddenPais") != null) ? request.getParameter("hiddenPais") : "";
	String paramNoEmpOracle = 	(request.getParameter("hiddenNoEmpOracle") != null) ? request.getParameter("hiddenNoEmpOracle") : "";
	String paramGiro = 			(request.getParameter("hiddenGiro") != null) ? request.getParameter("hiddenGiro") : "";
	String paramPorcentaje = 			(request.getParameter("txtPorcentaje") != null) ? request.getParameter("txtPorcentaje") : "";
	String selectPorcentaje = 			(request.getParameter("selectPorcentaje") != null) ? request.getParameter("selectPorcentaje") : "";
	String selectPorcentajeCual = 			(request.getParameter("selectPorcentajeCual") != null) ? request.getParameter("selectPorcentajeCual") : "";
	String selectPorcentajeVisualizar = 			(request.getParameter("selectPorcentajeVisualizar") != null) ? request.getParameter("selectPorcentajeVisualizar") : "";
	
	
	String paramString = "";
	paramString += (request.getParameter("hiddenConsolida") != null) ? 		"&hiddenConsolida=" + request.getParameter("hiddenConsolida") : "";
	paramString += (request.getParameter("hiddenSegmento") != null) ? 		"&hiddenSegmento=" + request.getParameter("hiddenSegmento") : "";
	paramString += (request.getParameter("hiddenClasificacion") != null) ? 	"&hiddenClasificacion=" + request.getParameter("hiddenClasificacion") : "";
	paramString += (request.getParameter("hiddenPais") != null) ? 			"&hiddenPais=" + request.getParameter("hiddenPais") : "";
	paramString += (request.getParameter("hiddenNoEmpOracle") != null) ? 	"&hiddenNoEmpOracle=" + request.getParameter("hiddenNoEmpOracle") : "";
	paramString += (request.getParameter("hiddenGiro") != null) ? 			"&hiddenGiro=" + request.getParameter("hiddenGiro") : "";
	
	paramString += (request.getParameter("txtConsolida") != null) ? 	"&txtConsolida=" + request.getParameter("txtConsolida") : "";
	paramString += (request.getParameter("txtSegmento") != null) ? 		"&txtSegmento=" + request.getParameter("txtSegmento") : "";
	paramString += (request.getParameter("txtClasificacion") != null) ? "&txtClasificacion=" + request.getParameter("txtClasificacion") : "";
	paramString += (request.getParameter("txtPais") != null) ? 			"&txtPais=" + request.getParameter("txtPais") : "";
	paramString += (request.getParameter("txtNoEmpOracle") != null) ? 	"&txtNoEmpOracle=" + request.getParameter("txtNoEmpOracle") : "";
	paramString += (request.getParameter("txtGiro") != null) ? 			"&txtGiro=" + request.getParameter("txtGiro") : "";
	paramString += (request.getParameter("txtPorcentaje") != null) ? 	"&txtPorcentaje=" + request.getParameter("txtPorcentaje") : "";
	paramString += (request.getParameter("selectPorcentaje") != null) ? 	"&selectPorcentaje=" + request.getParameter("selectPorcentaje") : "";
	paramString += (request.getParameter("selectPorcentajeCual") != null) ? 	"&selectPorcentajeCual=" + request.getParameter("selectPorcentajeCual") : "";
	paramString += (request.getParameter("selectPorcentajeVisualizar") != null) ? 	"&selectPorcentajeVisualizar=" + request.getParameter("selectPorcentajeVisualizar") : "";

	String paramStringFull = paramString + ((request.getParameter("viewMode") != null) ? 	"&viewMode=" + request.getParameter("viewMode") : "");

	String viewMode = request.getParameter("viewMode");

	boolean returnFromOrgChart = (request.getParameter("returnFromOrgChart") != null && request.getParameter("returnFromOrgChart").equals("TRUE")) ? true : false;

	System.out.println("Antes if Organigrama");

	if(empresaId != null && !empresaId.equals("") && !empresaId.equals("null") && viewMode != null && viewMode.equals("organigrama")) {

%>
	<script type="text/javascript"            src="orgChartEmpresasData.jsp?empresaId=<%=empresaId%>&paramConsolida=<%=paramConsolida%>&paramSegmento=<%=paramSegmento%>&paramClasificacion=<%=paramClasificacion%>&paramPais=<%=paramPais%>&paramNoEmpOracle=<%=paramNoEmpOracle%>&paramGiro=<%=paramGiro%>&paramPorcentaje=<%=paramPorcentaje%>&selectPorcentaje=<%=selectPorcentaje%>&selectPorcentajeCual=<%=selectPorcentajeCual%>&selectPorcentajeVisualizar=<%=selectPorcentajeVisualizar%>"></script> 
<%
	}
%>
<script type="text/javascript">

	function waitBar() {
		document.getElementById('imgCapWait').style.display = '';
		document.getElementById('btnEjecutar').style.display = 'none';
	}

	function openSelectPupUp(catalogId, targetIds, targetNames, namesProperty, currentValue) {
	    var left = screen.width - ((screen.width - 300) / 2);
	    var top = (screen.height - 300) / 2;  // for 25% - devide by 4  |  for 33% - devide by 3

		newwindow=window.open('../../components/simpleSelectPupUp/simpleselect.jsp?catalogId=' + catalogId + '&targetIds=' + targetIds + '&targetNames=' + targetNames + '&currentValue=' + currentValue + '&namesProperty=' + namesProperty,
							'name',
							'height=600,width=450,toolbar=0,menubar=0,location=0,status=0,scrollbars=1,resizable=1,left=' + left + ',top=' + top);

		if (window.focus) {newwindow.focus()}

		return false;
	}

</script>

</head>
<body>
<table width="100%">
<tr>
<td colspan="3" align="center">

<script type="text/javascript">
     jQuery(document).ready(function () {
         jQuery('body').layout({
    		center__paneSelector: "#contentpanel"
		});
     });
</script>

<style type="text/css">
    .orgChartTextSize {
        font-size: 11px;
    }
</style>

<script type="text/javascript">
        var orgDiagram = null;
        var treeItems = {};

        jQuery(document).ready(function () {
            jQuery.ajaxSetup({
                cache: false
            });

            jQuery('#contentpanel').layout(
			{
			    center__paneSelector: "#centerpanel"
				//, south__paneSelector: "#southpanel"
				, south__resizable: false
				, south__closable: false
				, south__spacing_open: 0
				, south__size: 50
				, west__size: 300
				//, west__paneSelector: "#westpanel"
				, west__resizable: true
				, center__onresize: function () {
				    if (orgDiagram != null) {
				        jQuery("#centerpanel").orgDiagram("update", primitives.common.UpdateMode.Refresh);
				    }
				}
			});

            function ContainsKeyValue(obj, key, value) {
                if (obj[key] == value)
                    return { exist: true, json: obj };

                for (all in obj) {
                    if (obj[all] != null && obj[all][key] == value)
                        return { exist: true, json: obj[all] };

                    if (typeof obj[all] == "object" && obj[all] != null) {
                        var found = ContainsKeyValue(obj[all], key, value);
                        if (found.exist == true)
                            return { exist: true, json: found.json };
                    }
                }
                return { exist: false, json: null };
            }

            /* Page Fit Mode */

            var pageFitModes = jQuery("#pageFitMode");
            for (var key in primitives.common.PageFitMode) {
                var value = primitives.common.PageFitMode[key];
                //pageFitModes.append(jQuery("<br/><label><input name='pageFitMode' type='radio' value='" + value + "' " + (value == primitives.common.PageFitMode.FitToPage ? "checked" : "") + " />" +  value + "-"+ key + "</label>"));

                //
                // CUSTOMIZADO
                //
                	value = 0;
                	if(value == 0) {
                		pageFitModes.append(jQuery("<br/><label><input name='pageFitMode' type='radio' value='" + value + "' " + (value == primitives.common.PageFitMode.FitToPage ? "checked" : "") + " />Mostrar Todo</label>"));
                	}

            };

            jQuery("input:radio[name=pageFitMode]").change(function () {
                Update(jQuery("#centerpanel"), primitives.common.UpdateMode.Refresh);
            });

            /* Orientation Type */
            var orientationTypes = jQuery("#orientationType");
            for (var key in primitives.common.OrientationType) {
                var value = primitives.common.OrientationType[key];
                
                //orientationTypes.append(jQuery("<br/><label><input name='orientationType' type='radio' value='" + value + "' " + (value == primitives.common.OrientationType.Top ? "checked" : "") + " />" + value + "-" + key + "</label>"));
                
                if(value == 0 || value == 2) {
                	if(value == 0) {
	                	orientationTypes.append(jQuery("<br/><label><input name='orientationType' type='radio' value='" + value + "' " + (value == primitives.common.OrientationType.Top ? "checked" : "") + " />Horizontal</label>"));
                	}
                	
                	if(value == 2) {
	                	orientationTypes.append(jQuery("<br/><label><input name='orientationType' type='radio' value='" + value + "' " + (value == primitives.common.OrientationType.Top ? "checked" : "") + " />Vertical</label>"));
                	}
                }
                
            };

            jQuery("input:radio[name=orientationType]").change(function () {
                Update(jQuery("#centerpanel"), primitives.common.UpdateMode.Refresh);
            });

            /* Vertical Alignmnet */
            var verticalAlignments = jQuery("#verticalAlignment");
            for (var key in primitives.common.VerticalAlignmentType) {
                var value = primitives.common.VerticalAlignmentType[key];
                verticalAlignments.append(jQuery("<br/><label><input name='verticalAlignment' type='radio' value='" + value + "' " + (value == primitives.common.VerticalAlignmentType.Middle ? "checked" : "") + " />" + key + "</label>"));
            };

            jQuery("input:radio[name=verticalAlignment]").change(function () {
                Update(jQuery("#centerpanel"), primitives.common.UpdateMode.Refresh);
            });

            /* Horizontal Alignmnet */
            var horizontalAlignments = jQuery("#horizontalAlignment");
            for (var key in primitives.common.HorizontalAlignmentType) {
                var value = primitives.common.HorizontalAlignmentType[key];
                horizontalAlignments.append(jQuery("<br/><label><input name='horizontalAlignment' type='radio' value='" + value + "' " + (value == primitives.common.HorizontalAlignmentType.Center ? "checked" : "") + " />" + key + "</label>"));
            };

            jQuery("input:radio[name=horizontalAlignment]").change(function () {
                Update(jQuery("#centerpanel"), primitives.common.UpdateMode.Refresh);
            });

            /* Minimal Visibility */
            var pageFitModes = jQuery("#minimalVisibility");
            for (var key in primitives.common.Visibility) {
                var value = primitives.common.Visibility[key];
                pageFitModes.append(jQuery("<br/><label><input name='minimalVisibility' type='radio' value='" + value + "' " + (value == primitives.common.Visibility.Dot ? "checked" : "") + " />" + key + "</label>"));
            };

            jQuery("input:radio[name=minimalVisibility]").change(function () {
                Update(jQuery("#centerpanel"), primitives.common.UpdateMode.Refresh);
            });

            /* Selection Path Mode */
            var selectionPathModes = jQuery("#selectionPathMode");
            for (var key in primitives.common.SelectionPathMode) {
                var value = primitives.common.SelectionPathMode[key];
                selectionPathModes.append(jQuery("<br/><label><input name='selectionPathMode' type='radio' value='" + value + "' " + (value == primitives.common.SelectionPathMode.FullStack ? "checked" : "") + " />" + key + "</label>"));
            };

            jQuery("input:radio[name=selectionPathMode]").change(function () {
                Update(jQuery("#centerpanel"), primitives.common.UpdateMode.Refresh);
            });

            /* Leaves Placement Type */
            var leavesPlacementType = jQuery("#leavesPlacementType");
            for (var key in primitives.common.ChildrenPlacementType) {
                var value = primitives.common.ChildrenPlacementType[key];
                leavesPlacementType.append(jQuery("<br/><label><input name='leavesPlacementType' type='radio' value='" + value + "' " + (value == primitives.common.ChildrenPlacementType.Horizontal ? "checked" : "") + " />" + key + "</label>"));
            };

            jQuery("input:radio[name=leavesPlacementType]").change(function () {
                Update(jQuery("#centerpanel"), primitives.common.UpdateMode.Refresh);
            });

            /* Has Selector Check Box*/
            var hasSelectorCheckbox = jQuery("#hasSelectorCheckbox");
            for (var key in primitives.common.Enabled) {
                var value = primitives.common.Enabled[key];
                hasSelectorCheckbox.append(jQuery("<br/><label><input name='hasSelectorCheckbox' type='radio' value='" + value + "' " + (value == primitives.common.Enabled.True ? "checked" : "") + " />" + key + "</label>"));
            };

            jQuery("input:radio[name=hasSelectorCheckbox]").change(function () {
                Update(jQuery("#centerpanel"), primitives.common.UpdateMode.Refresh);
            });

            /* Has User Buttons */
            var hasButtons = jQuery("#hasButtons");
            for (var key in primitives.common.Enabled) {
                var value = primitives.common.Enabled[key];
                hasButtons.append(jQuery("<br/><label><input name='hasButtons' type='radio' value='" + value + "' " + (value == primitives.common.Enabled.Auto ? "checked" : "") + " />" + key + "</label>"));
            };

            jQuery("input:radio[name=hasButtons]").change(function () {
                Update(jQuery("#centerpanel"), primitives.common.UpdateMode.Refresh);
            });

            /* Items Group By Type */
            var arrowsDirections = jQuery("#arrowsDirection");
            for (var key in primitives.common.GroupByType) {
                var value = primitives.common.GroupByType[key];
                arrowsDirections.append(jQuery("<br/><label><input name='arrowsDirection' type='radio' value='" + value + "' " + (value == primitives.common.GroupByType.None ? "checked" : "") + " />" + key + "</label>"));
            };

            jQuery("input:radio[name=arrowsDirection]").change(function () {
                Update(jQuery("#centerpanel"), primitives.common.UpdateMode.Refresh);
            });

            /* Connector Type */
            var connectorTypes = jQuery("#connectorType");
            for (var key in primitives.common.ConnectorType) {
                var value = primitives.common.ConnectorType[key];
                connectorTypes.append(jQuery("<br/><label><input name='connectorType' type='radio' value='" + value + "' " + (value == primitives.common.ConnectorType.Squared ? "checked" : "") + " />" + key + "</label>"));
            };

            jQuery("input:radio[name=connectorType]").change(function () {
                Update(jQuery("#centerpanel"), primitives.common.UpdateMode.Refresh);
            });

            /* Connectors Elbows Type */
            var elbowTypes = jQuery("#elbowType");
            for (var key in primitives.common.ElbowType) {
                var value = primitives.common.ElbowType[key];
                elbowTypes.append(jQuery("<br/><label><input name='elbowType' type='radio' value='" + value + "' " + (value == primitives.common.ElbowType.None ? "checked" : "") + " />" + key + "</label>"));
            };

            jQuery("input:radio[name=elbowType]").change(function () {
                Update(jQuery("#centerpanel"), primitives.common.UpdateMode.Refresh);
            });

            // lineType
            var connectorShapeType = jQuery("#lineType");
            for (var key in primitives.common.LineType) {
                var value = primitives.common.LineType[key];
                connectorShapeType.append(jQuery("<br/><label><input name='lineType' type='radio' value='" + value + "' " + (value == primitives.common.LineType.Solid ? "checked" : "") + " />" + key + "</label>"));
            };

            jQuery("input:radio[name=lineType]").change(function () {
                Update(jQuery("#centerpanel"), primitives.common.UpdateMode.Refresh);
            });

            // color
            var color = jQuery("<select></select>");
            jQuery("#color").append(color);
            for (var key in primitives.common.Colors) {
                var value = primitives.common.Colors[key];
                color.append(jQuery("<option value='" + value + "' " + (value == primitives.common.Colors.Silver ? "selected" : "") + " >" + key + "</option>"));
            };

            jQuery("#color").change(function () {
                Update(jQuery("#centerpanel"), primitives.common.UpdateMode.Refresh);
            });

            // minimizedItemCornerRadius
            var minimizedItemCornerRadiuses = ["NULL", 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
            var minimizedItemCornerRadius = jQuery("<select></select>");
            jQuery("#minimizedItemCornerRadius").append(minimizedItemCornerRadius);
            for (var index = 0; index < minimizedItemCornerRadiuses.length; index += 1) {
                var value = minimizedItemCornerRadiuses[index];
                minimizedItemCornerRadius.append(jQuery("<option value='" + (value == "NULL" ? -1 : value) + "' " + (value == "NULL" ? "selected" : "") + " >" + value + "</option>"));
            };

            jQuery("#minimizedItemCornerRadius").change(function () {
                Update(jQuery("#centerpanel"), primitives.common.UpdateMode.Redraw);
            });

            // minimizedItemSize
            var minimizedItemSizes = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 14, 16, 18, 20, 30, 40];
            var minimizedItemSize = jQuery("<select></select>");
            jQuery("#minimizedItemSize").append(minimizedItemSize);
            for (var index = 0; index < minimizedItemSizes.length; index += 1) {
                var value = minimizedItemSizes[index];
                minimizedItemSize.append(jQuery("<option value='" + value + "' " + (value == 4 ? "selected" : "") + " >" + value + "</option>"));
            };

            jQuery("#minimizedItemSize").change(function () {
                Update(jQuery("#centerpanel"), primitives.common.UpdateMode.Redraw);
            });

            // highlightPadding
            var highlightPaddings = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
            var highlightPadding = jQuery("<select></select>");
            jQuery("#highlightPadding").append(highlightPadding);
            for (var index = 0; index < highlightPaddings.length; index += 1) {
                var value = highlightPaddings[index];
                highlightPadding.append(jQuery("<option value='" + value + "' " + (value == 2 ? "selected" : "") + " >" + value + "</option>"));
            };

            jQuery("#highlightPadding").change(function () {
                Update(jQuery("#centerpanel"), primitives.common.UpdateMode.Redraw);
            });

            // Intervals
            var intervalNames = ["normalLevelShift", "dotLevelShift", "lineLevelShift", "normalItemsInterval", "dotItemsInterval", "lineItemsInterval", "cousinsIntervalMultiplier"];
            var intervals = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 14, 16, 18, 20, 30, 40];
            var defaultConfig = new primitives.orgdiagram.Config();
            defaultConfig.dotItemsInterval = 2;
            for (var intervalIndex = 0; intervalIndex < intervalNames.length; intervalIndex++) {
                var intervalName = intervalNames[intervalIndex];
                var intervalSelector = jQuery("<select></select>");
                jQuery("#" + intervalName).append(intervalSelector);
                for (var index = 0; index < intervals.length; index += 1) {
                    var value = intervals[index];
                    var defaultValue = defaultConfig[intervalName];

                    intervalSelector.append(jQuery("<option value='" + value + "' " + (value == defaultValue ? "selected" : "") + " >" + value + "</option>"));
                };

                jQuery("#" + intervalName).change(function () {
                    Update(jQuery("#centerpanel"), primitives.common.UpdateMode.Refresh);
                });
            }

            // lineWidth
            var lineWidths = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
            var lineWidth = jQuery("<select></select>");
            jQuery("#lineWidth").append(lineWidth);
            for (var index = 0; index < lineWidths.length; index += 1) {
                var value = lineWidths[index];
                lineWidth.append(jQuery("<option value='" + value + "' " + (value == 1 ? "selected" : "") + " >" + value + "</option>"));
            };

            jQuery("#lineWidth").change(function () {
                Update(jQuery("#centerpanel"), primitives.common.UpdateMode.Refresh);
            });

            /* Show Labels */
            var showLabels = jQuery("#showLabels");
            for (var key in primitives.common.Enabled) {
                var value = primitives.common.Enabled[key];
                showLabels.append(jQuery("<br/><label><input name='showLabels' type='radio' value='" + value + "' " + (value == primitives.common.Enabled.Auto ? "checked" : "") + " />" + key + "</label>"));
            };

            jQuery("input:radio[name=showLabels]").change(function () {
                Update(jQuery("#centerpanel"), primitives.common.UpdateMode.Refresh);
            });

            /* Label Orientations */
            var labelOrientations = jQuery("#labelOrientation");
            for (var key in primitives.text.TextOrientationType) {
                var value = primitives.text.TextOrientationType[key];
                labelOrientations.append(jQuery("<br/><label><input name='labelOrientation' type='radio' value='" + value + "' " + (value == primitives.text.TextOrientationType.Horizontal ? "checked" : "") + " />" + key + "</label>"));
            };

            jQuery("input:radio[name=labelOrientation]").change(function () {
                Update(jQuery("#centerpanel"), primitives.common.UpdateMode.Refresh);
            });

            /* Label Placement */
            var labelPlacements = jQuery("#labelPlacement");
            for (var key in primitives.common.PlacementType) {
                var value = primitives.common.PlacementType[key];
                labelPlacements.append(jQuery("<br/><label><input name='labelPlacement' type='radio' value='" + value + "' " + (value == primitives.common.PlacementType.Top ? "checked" : "") + " />" + key + "</label>"));
            };

            jQuery("input:radio[name=labelPlacement]").change(function () {
                Update(jQuery("#centerpanel"), primitives.common.UpdateMode.Refresh);
            });

            /* Graphics Type */
            var graphicsType = jQuery("#graphicsType");
            for (var key in primitives.common.GraphicsType) {
                var value = primitives.common.GraphicsType[key];
                graphicsType.append(jQuery("<br/><label><input name='graphicsType' type='radio' value='" + value + "' " + (value == primitives.common.GraphicsType.SVG ? "checked" : "") + " />" + key + "</label>"));
            };

            jQuery("input:radio[name=graphicsType]").change(function () {
                Update(jQuery("#centerpanel"), primitives.common.UpdateMode.Recreate);
            });

            /* Setup & Run */
            Setup(jQuery("#centerpanel"));

            /* Load data */
            LoadData(jQuery("#centerpanel"));
        });

        function Setup(selector) {
            orgDiagram = selector.orgDiagram(GetOrgDiagramConfig());

            ShowGraphicsType(selector.orgDiagram("option", "actualGraphicsType"));
        }

        function LoadData(selector) {
            var index, len;
            for (index = 0, len = data.length; index < len; index += 1) {
                treeItems[data[index].id] = data[index];
            }

            /* set template for cursor item */
            data[0].templateName = "contactTemplate";

            selector.orgDiagram("option", {
                items: data,
                cursorItem: 0
            });
            selector.orgDiagram("update");
        }


        function Update(selector, updateMode) {
            selector.orgDiagram("option", GetOrgDiagramConfig());
            selector.orgDiagram("update", updateMode);

            ShowGraphicsType(selector.orgDiagram("option", "actualGraphicsType"));
        }

        function GetOrgDiagramConfig() {
            var graphicsType = parseInt(jQuery("input:radio[name=graphicsType]:checked").val(), 10);
            var pageFitMode = parseInt(jQuery("input:radio[name=pageFitMode]:checked").val(), 10);
            var orientationType = parseInt(jQuery("input:radio[name=orientationType]:checked").val(), 10);
            var minimalVisibility = parseInt(jQuery("input:radio[name=minimalVisibility]:checked").val(), 10);
            var selectionPathMode = parseInt(jQuery("input:radio[name=selectionPathMode]:checked").val(), 10);
            var leavesPlacementType = parseInt(jQuery("input:radio[name=leavesPlacementType]:checked").val(), 10);
            //var hasSelectorCheckbox = parseInt(jQuery("input:radio[name=hasSelectorCheckbox]:checked").val(), 10);
            var hasSelectorCheckbox = 2;
            

            var hasButtons = parseInt(jQuery("input:radio[name=hasButtons]:checked").val(), 10);
            var verticalAlignment = parseInt(jQuery("input:radio[name=verticalAlignment]:checked").val(), 10);
            var horizontalAlignment = parseInt(jQuery("input:radio[name=horizontalAlignment]:checked").val(), 10);
            var connectorType = parseInt(jQuery("input:radio[name=connectorType]:checked").val(), 10);
            var elbowType = parseInt(jQuery("input:radio[name=elbowType]:checked").val(), 10);
            var showLabels = parseInt(jQuery("input:radio[name=showLabels]:checked").val(), 10);
            var labelOrientation = parseInt(jQuery("input:radio[name=labelOrientation]:checked").val(), 10);
            var labelPlacement = parseInt(jQuery("input:radio[name=labelPlacement]:checked").val(), 10);
            //var color = jQuery("#color option:selected").val();
            var color = "#6A5ACD";
            
            var lineWidth = parseInt(jQuery("#lineWidth option:selected").val(), 10);
            var lineType = parseInt(jQuery("input:radio[name=lineType]:checked").val(), 10);
            var arrowsDirection = parseInt(jQuery("input:radio[name=arrowsDirection]:checked").val(), 10);


            var minimizedItemCornerRadius = parseInt(jQuery("#minimizedItemCornerRadius option:selected").val(), 10);
            minimizedItemCornerRadius = minimizedItemCornerRadius == -1 ? null : minimizedItemCornerRadius;

            var minimizedItemSize = parseInt(jQuery("#minimizedItemSize option:selected").val(), 10);
            var highlightPadding = parseInt(jQuery("#highlightPadding option:selected").val(), 10);

            var normalLevelShift = parseInt(jQuery("#normalLevelShift option:selected").val(), 10);
            var dotLevelShift = parseInt(jQuery("#dotLevelShift option:selected").val(), 10);
            var lineLevelShift = parseInt(jQuery("#lineLevelShift option:selected").val(), 10);
            var normalItemsInterval = parseInt(jQuery("#normalItemsInterval option:selected").val(), 10);
            var dotItemsInterval = parseInt(jQuery("#dotItemsInterval option:selected").val(), 10);
            var lineItemsInterval = parseInt(jQuery("#lineItemsInterval option:selected").val(), 10);
            var cousinsIntervalMultiplier = parseInt(jQuery("#cousinsIntervalMultiplier option:selected").val(), 10);

            var buttons = [];
            //buttons.push(new primitives.orgdiagram.ButtonConfig("delete", "ui-icon-close", "Delete"));
            //buttons.push(new primitives.orgdiagram.ButtonConfig("properties", "ui-icon-gear", "Info"));
            //buttons.push(new primitives.orgdiagram.ButtonConfig("add", "ui-icon-person", "Add"));

            var templates = [
                getContactTemplate(minimizedItemCornerRadius, minimizedItemSize, highlightPadding),
                getDefaultTemplate(minimizedItemCornerRadius, minimizedItemSize, highlightPadding)
            ];

            return {
                graphicsType: graphicsType,
                pageFitMode: pageFitMode,
                orientationType: orientationType,
                verticalAlignment: verticalAlignment,
                horizontalAlignment: horizontalAlignment,
                arrowsDirection: arrowsDirection,
                connectorType: connectorType,
                elbowType: elbowType,
                minimalVisibility: minimalVisibility,
                hasSelectorCheckbox: hasSelectorCheckbox,
                selectionPathMode: selectionPathMode,
                leavesPlacementType: leavesPlacementType,
                hasButtons: hasButtons,
                buttons: buttons,
                templates: templates,
                onButtonClick: onButtonClick,
                onCursorChanging: onCursorChanging,
                onCursorChanged: onCursorChanged,
                onMouseClick: onMouseClick,
                onMouseDblClick: onMouseDblClick,
                onHighlightChanging: onHighlightChanging,
                onHighlightChanged: onHighlightChanged,
                onSelectionChanged: onSelectionChanged,
                onItemRender: onTemplateRender,
                itemTitleFirstFontColor: primitives.common.Colors.White,
                itemTitleSecondFontColor: primitives.common.Colors.White,
                showLabels: showLabels,
                labelOrientation: labelOrientation,
                labelPlacement: labelPlacement,
                labelOffset: 2,
                linesType: lineType,
                linesColor: color,
                linesWidth: lineWidth,
                defaultTemplateName: "contactTemplate",
                normalLevelShift: normalLevelShift,
                dotLevelShift: dotLevelShift,
                lineLevelShift: lineLevelShift,
                normalItemsInterval: normalItemsInterval,
                dotItemsInterval: dotItemsInterval,
                lineItemsInterval: lineItemsInterval, 
                cousinsIntervalMultiplier: cousinsIntervalMultiplier
            };
        }

    
        function getDefaultTemplate(minimizedItemCornerRadius, minimizedItemSize, highlightPadding) {
            var result = new primitives.orgdiagram.TemplateConfig();
            result.name = "defaultTemplate";

            // If we don;t change anything in template all its properties assigned to default values
            // So we change only default dot size and corner radius
            result.minimizedItemSize = new primitives.common.Size(minimizedItemSize, minimizedItemSize);
            result.minimizedItemCornerRadius = minimizedItemCornerRadius;
            result.highlightPadding = new primitives.common.Thickness(highlightPadding, highlightPadding, highlightPadding, highlightPadding);

            return result;
        }
        


        function getContactTemplate(minimizedItemCornerRadius, minimizedItemSize, highlightPadding) {
            var result = new primitives.orgdiagram.TemplateConfig();
            result.name = "contactTemplate";

            //result.itemSize = new primitives.common.Size(220, 120);
            
            <% if(selectPorcentajeVisualizar.equals("Ambos") ) { %>
            result.itemSize = new primitives.common.Size(200, 80);
            <% } %>
            
            <% if(selectPorcentajeVisualizar.equals("Directo") || selectPorcentajeVisualizar.equals("Indirecto")) { %>
            result.itemSize = new primitives.common.Size(200, 70);
            <% } %>
            
            result.minimizedItemSize = new primitives.common.Size(minimizedItemSize, minimizedItemSize);
            result.minimizedItemCornerRadius = minimizedItemCornerRadius;
            result.highlightPadding = new primitives.common.Thickness(highlightPadding, highlightPadding, highlightPadding, highlightPadding);

            
            var itemTemplate = jQuery(
			  '<div class="bp-item bp-corner-all bt-item-frame">'
                + '<table>'
                + '<tr><th colspan=3><div name="empresa" class="orgChartTextSize"></div></th></tr>'
                + '<tr><td colspan=3></td></tr>'
                <% if (selectPorcentajeVisualizar != null && (selectPorcentajeVisualizar.equals("Directo") || selectPorcentajeVisualizar.equals("Ambos") ) ) { %>
                + '<tr><td width="20%"></td><td width="40%" class="orgChartTextSize">Directo:</td><td><div name="directo" class="orgChartTextSize"></td></tr>'
                <% } %>
                <% if(selectPorcentajeVisualizar != null && (selectPorcentajeVisualizar.equals("Indirecto") || selectPorcentajeVisualizar.equals("Ambos") ) ) { %>
                + '<tr><td width="20%"></td><td width="40%" class="orgChartTextSize">Indirecto:</td><td><div name="indirecto" class="orgChartTextSize"></td></tr>'
                <% } %>
                + '</table>'

			).css({
			    width: result.itemSize.width + "px",
			    height: result.itemSize.height + "px"
			}).addClass("bp-item bp-corner-all bt-item-frame");
            result.itemTemplate = itemTemplate.wrap('<div>').parent().html();

            return result;
        }

        function onTemplateRender(event, data) {
            switch (data.renderingMode) {
                case primitives.common.RenderingMode.Create:
                    data.element.find("[name=email]").click(function (e) {
                        /* Block mouse click propogation in order to avoid layout updates before server postback*/
                        primitives.common.stopPropagation(e);
                    });
                    /* Initialize widgets here */
                    break;
                case primitives.common.RenderingMode.Update:
                    /* Update widgets here */
                    break;
            }

            var itemConfig = data.context,
                itemTitleColor = itemConfig.itemTitleColor != null ? itemConfig.itemTitleColor : primitives.common.Colors.RoyalBlue;

            if (data.templateName == "contactTemplate") {
                //data.element.find("[name=photo]").attr({ "src": itemConfig.image });
                //data.element.find("[name=titleBackground]").css({ "background": itemTitleColor });
                //data.element.find("[name=email]").attr({ "href": ("mailto:" + itemConfig.email + "?Subject=Hello%20world") });

                //var fields = ["title", "description", "phone", "email"];
                var fields = ["empresa", "directo", "indirecto"];
                for (var index = 0; index < fields.length; index += 1) {
                    var field = fields[index];

                    var element = data.element.find("[name=" + field + "]");
                    if (element.text() != itemConfig[field]) {
                        element.text(itemConfig[field]);
                    }
                }
            }
        }

        function onSelectionChanged(e, data) {
            var selectedItems = jQuery("#centerpanel").orgDiagram("option", "selectedItems");
            var message = "";
            for (var index = 0; index < selectedItems.length; index += 1) {
                var itemConfig = treeItems[selectedItems[index]];
                if (message != "") {
                    message += ", ";
                }
                message += "<b>'" + itemConfig.title + "'</b>";
            }
            message += (data.parentItem != null ? " Parent item <b>'" + data.parentItem.title + "'" : "");
            //jQuery("#southpanel").empty().append("User selected following items: " + message);
        }

        function onHighlightChanging(e, data) {
            var message = (data.context != null) ? "User is hovering mouse over item <b>'" + data.context.title + "'</b>." : "";
            message += (data.parentItem != null ? " Parent item <b>'" + data.parentItem.title + "'" : "");

            //jQuery("#southpanel").empty().append(message);
        }

        function onHighlightChanged(e, data) {
            var message = (data.context != null) ? "User hovers mouse over item <b>'" + data.context.title + "'</b>." : "";
            message += (data.parentItem != null ? " Parent item <b>'" + data.parentItem.title + "'" : "");

            //jQuery("#southpanel").empty().append(message);
        }

        function onCursorChanging(e, data) {
            var message = "User is clicking on item '" + data.context.title + "'.";
            message += (data.parentItem != null ? " Parent item <b>'" + data.parentItem.title + "'" : "");

            //jQuery("#southpanel").empty().append(message);

            data.oldContext.templateName = null;
            data.context.templateName = "contactTemplate";
        }

        function onCursorChanged(e, data) {
            var message = "User clicked on item '" + data.context.title + "'.";
            message += (data.parentItem != null ? " Parent item <b>'" + data.parentItem.title + "'" : "");
            //jQuery("#southpanel").empty().append(message);
        }

        function onButtonClick(e, data) {
            var message = "User clicked <b>'" + data.name + "'</b> button for item <b>'" + data.context.title + "'</b>.";
            message += (data.parentItem != null ? " Parent item <b>'" + data.parentItem.title + "'" : "");
            //jQuery("#southpanel").empty().append(message);
        }

        function onMouseClick(e, data) {
            var message = "User clicked item <b>'" + data.context.title + "'</b>.";
            message += (data.parentItem != null ? " Parent item <b>'" + data.parentItem.title + "'" : "");
            //jQuery("#southpanel").empty().append(message);
        }

        function onMouseDblClick(e, data) {
            var message = "User double clicked item <b>'" + data.context.title + "'</b>.";
            message += (data.parentItem != null ? " Parent item <b>'" + data.parentItem.title + "'" : "");
            //jQuery("#southpanel").empty().append(message);
        }

        function ShowGraphicsType(graphicsType) {
            var result = null;

            switch (graphicsType) {
                case primitives.common.GraphicsType.SVG:
                    result = "SVG";
                    break;
                case primitives.common.GraphicsType.Canvas:
                    result = "Canvas";
                    break;
                case primitives.common.GraphicsType.VML:
                    result = "VML";
                    break;
            }
            jQuery("#actualGraphicsType").empty().append("Graphics Type: " + result);
        }

</script>
<div id="contentpanel" style="padding: 0px;background-image:url('<c:out value='${applicationBean.contextPath}'/>/img/page_background.jpg');" >

        <div id="westpanel" style="padding: 5px; margin: 0px; border-style: solid; font-size: 12px; border-color: grey; border-width: 1px; overflow: scroll; -webkit-overflow-scrolling: touch;background-image:url('<c:out value='${applicationBean.contextPath}'/>/img/page_background.jpg');">
    		<jsp:include page="/jsp/components/pages_border/open-pre-title.jsp"></jsp:include>
			<jsp:include page="/jsp/components/pages_border/open-post-title.jsp"></jsp:include>

            <br>
            <br>
            <%-- <h3>Opciones de Visualización</h3> --%>
            <%-- <p id="pageFitMode"><b>Modalidad de Ajuste</b><br></p> --%>
            <%-- <p id="orientationType"><br><b>Orientación</b><br></p>  --%>
            <p id="verticalAlignment" style="display:none">Items Vertical Alignment</p>
            <p id="horizontalAlignment" style="display:none">Items Horizontal Alignment</p>
            <p id="leavesPlacementType" style="display:none">Leaves placement</p>
            <p id="minimalVisibility" style="display:none">Minimal nodes visibility</p>
            <p id="selectionPathMode" style="display:none">Selection Path Mode</p>
            <!--<h3>Default Template Options</h3>-->
            <p id="hasButtons" style="display:none">User buttons</p>
            <p id="hasSelectorCheckbox" style="display:none">Selection check box</p>
            <!--<h3>Minimized Item (Dot, Marker)</h3>-->
            <p id="minimizedItemCornerRadius" style="display:none">Corner Radius:&nbsp;</p>
            <p id="minimizedItemSize" style="display:none">Size:&nbsp;</p>
            <p id="highlightPadding" style="display:none">Highlight Padding:&nbsp;</p>            
            <!--<h3>Vertical Intervals Between Rows</h3>-->
            <p id="normalLevelShift" style="display:none">Normal:&nbsp;</p>
            <p id="dotLevelShift" style="display:none">Dotted:&nbsp;</p>
            <p id="lineLevelShift" style="display:none">Lined:&nbsp;</p>
            <!--<h3>Horizontal Intervals Between Items in Row</h3>-->
            <p id="normalItemsInterval" style="display:none">Normal:&nbsp;</p>
            <p id="dotItemsInterval" style="display:none">Dotted:&nbsp;</p>
            <p id="lineItemsInterval" style="display:none">Lined:&nbsp;</p>
            <p id="cousinsIntervalMultiplier" style="display:none">Cousins Multiplier:&nbsp;</p>
            <!--<h3>Connectors</h3>-->
            <p id="arrowsDirection" style="display:none">Arrows Direction</p>
            <p id="connectorType" style="display:none">Connectors</p>
            <p id="elbowType" style="display:none">Connectors Elbows Type</p>
            <p id="lineType" style="display:none">Line type</p>
            
            
            <p id="color" style="display:none">Color:&nbsp;</p>
            <p id="lineWidth" style="display:none">Line width:&nbsp;</p>
            
            
            <!--<h3>Labels</h3>-->
            <p id="showLabels" style="display:none">Show Labels</p>
            <p id="labelOrientation" style="display:none">Label Orientation</p>
            <p id="labelPlacement" style="display:none">Label Placement</p>
            <!--<h3>Rendering Mode</h3>-->
            <p id="graphicsType" style="display:none">Graphics</p>
            <p id="actualGraphicsType" style="display:none"></p>
            
            <jsp:include page="/jsp/components/pages_border/close.jsp"></jsp:include>
            
        </div>


        <div id="centerpanel" style="overflow: hidden; padding: 0px; margin: 0px; border: 0px; background-image:url('<c:out value='${applicationBean.contextPath}'/>/img/page_background.jpg');">
        </div>

        <div id="southpanel">
        </div>

</div>
		</td>
	</tr>
</table>
</body>
</html>