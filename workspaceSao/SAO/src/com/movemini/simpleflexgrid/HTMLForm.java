package com.movemini.simpleflexgrid;

import java.util.ArrayList;

import com.movemini.data.Record;
import com.movemini.simpleflexgrid.components.SelectList;
import com.movemini.simpleflexgrid.components.SelectList2;;

public class HTMLForm {


	public static final String CULUMN_PATTERN_1 = "col-md-6 col-sm-6 col-xs-12";



	public static String getFormHtml(Record record, ArrayList<HTMLTableColumn> columns, String additionalAttributes) {

		StringBuilder sb = new StringBuilder();

		//sb.append("<table width='" + tableWidth + "' " + additionalAttributes + ">");
		//sb.append("	<tr>");

		int c = 0;

		for (HTMLTableColumn column : columns) {
			//sb.append("		<th class='tableHeader'>" + column.getHeaderText() + "</th>");

			c++;

			if(c%2 == 0) {
				sb.append("<div style='background-color:#EEEEEE'>");
			} else {

				sb.append("<div>");
			}


			////////////////<div class="col-md-6 col-sm-6 col-xs-12" style="padding-left: 0px;">

			//align='right'
			sb.append("<div class=\"col-md-6 col-sm-6 col-xs-12\">");
			sb.append("<label for=\"text\" style=\"display: inline-block;\">" + column.getHeaderText() + "</label> ");
			sb.append("</div>");
			////////////////</div>


			//StringBuilder recordStringBuilder = new StringBuilder();

			//String cssClass = (rowIndex % 2 == 0) ? "tableRow2" : "";

			//recordStringBuilder.append("		<tr class='" + cssClass + "'>");

			//int colIndex = 0;

			///for (HTMLTableColumn column : columns) {
				/*
				String columnAlign = columnAligns[colIndex];
				String columnType = columnTypes[colIndex];
				String columnEvent = columnEvents[colIndex];*/

				//recordStringBuilder.append("			<td align='" + column.getAlign() + "'>");

				/*if(col.getHtmlText() != null) {

					sb.append(col.getHtmlText()
							.replace("KEY", record.get(flexListPage.getKeyColumn()))
							.replace("CONTEXT_PATH", request.getServletContext().getContextPath())
							);
				} else {

					sb.append(record.getString(col.getName()));
				}*/

			sb.append("<div class=\"col-md-6 col-sm-6 col-xs-12\">");


				if(column.getType().equals("label")) {

					//sb.append(record.getString(column.getCode()));
					//sb.append(column.getJsCode().replace("THIS_VALUE", record.getString(column.getCode())));

					sb.append(record.getString(column.getCode()));


				} else if(column.getType().equals("text"))  {

					//placeholder=\"" + column.getHeaderText() + " \" required

					sb.append("<input type='text' id='" + column.getCode() + "' value='" + record.getString(column.getCode()) + "' "
							 +
							 column.getJsCode() + column.getAttributes() + " class=\"form-control\">"); //


				} else if(column.getType().equals("textBig"))  {

					//placeholder=\"" + column.getHeaderText() + " \" required

					sb.append("<input type='text' style='width:500px' id='" + column.getCode() + "' value='" + record.getString(column.getCode()) + "' "
							 +
							 column.getJsCode() + column.getAttributes() + " class=\"form-control\">"); //




				} else if(column.getType().equals("richtext"))  {


					sb.append("<div id='" + column.getCode() + "' " + column.getJsCode() + " " + column.getAttributes() + "  >" + record.getString(column.getCode()) + "</div>");
					sb.append("<div id='" + column.getCode() + "sm' >"+ record.getString(column.getCode()) + "</div>");
					sb.append("<script> document.addEventListener('DOMContentLoaded',function(){ $('#"+column.getCode()+"sm').summernote({ toolbar: [  ['style', ['bold', 'italic', 'underline', 'clear']], ['font', ['strikethrough', 'superscript', 'subscript']], ['fontsize', ['fontsize']], ['color', ['color']], ['para', ['ul', 'ol', 'paragraph']], ['height', ['height']] ] ,  fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New']});");
					sb.append(" $('#" + column.getCode() + "').hide();");
					sb.append(" $('#"+column.getCode()+"sm').on('summernote.change', function(we, contents, $editable) { var markup = $('#"+column.getCode()+"sm').summernote('code'); $('#"+column.getCode()+"').html(markup); $('#" + column.getCode() + "').change();  }); ");
					sb.append("  });</script>");

				}else if(column.getType().equals("textarea"))  {

					sb.append("<textarea id='" + column.getCode() + "' " + column.getJsCode() + " " + column.getAttributes() + ">" + record.getString(column.getCode()) + "</textarea>");


				} else if(column.getType().equals("date"))  {

						//size='8'
					sb.append("<input type='date'  id='" + column.getCode() + "' value='" + record.getString(column.getCode()) + "' " + column.getJsCode() + "class=\"form-control\" >"); //style=\"width: 300px\"

				} else if(column.getType().equals("time"))  {

						//recordStringBuilder.append("<input type='time' size='4' id='" + column.getCode() + "' value='" + record.getString(column.getCode()) + "' " + column.getJsCode() + ">");

				} else if(column.getType().equals("smallTextNonZero"))  {

					/**
					 * Especifico de  Foodsol
					 *
					 * */
					/*String str = record.getString(column.getCode());

					if(str.equals("0")) {

						str = "";
					}

					recordStringBuilder.append("<input type='text' size='2' value='" + str + "' " + column.getJsCode() + ">");*/

				} else if(column.getType().equals("checkbox"))  {

					String checked = (record.getString(column.getCode()).equals("1"))? "checked=checked" : "";

					sb.append("<input type='checkbox' id='"+  column.getCode()  +"' class='form-control' value='" + record.getString(column.getCode()) + "' " + checked + " " + column.getJsCode() + ">");

				} else if(column.getType().equals("select"))  {


						//
						// YEAH
						//


						//String checked = (record.getString(column.getCode()).equals("1"))? "checked=checked" : "";

						String currVal = record.getString(column.getCode());
						String selectHTML = SelectList.getSelectList(column, currVal, column.getAttributes());

						sb.append(selectHTML);


				} else if(column.getType().equals("select2"))  {


					String currVal = record.getString(column.getCode());
					String selectHTML = SelectList2.getSelectList(column, currVal, column.getAttributes());

					sb.append(selectHTML);





				} else if(column.getType().equals("link")) {

					//recordStringBuilder.append("<a " + column.getJsCode().replace(column.getCode(), record.getString(column.getCode())) + ">" +  column.getAttributes()[0] + "</a>");

				} else if(column.getType().equals("custom"))  {

					//recordStringBuilder.append(column.getJsCode().replace("KEY", record.getId()));

				}



				//recordStringBuilder.append("			</td>");

				//colIndex++;
			//}

			//recordStringBuilder.append("		</tr>");


			//String sb =

			//sb.append(recordStringBuilderFixed);


			//rowIndex++;




				sb.append("</div>");


				sb.append("</div>");

		}

		//sb.append("	</tr>");

		//int rowIndex = 1;

		//for (Record record : records) {

		//}
		//sb.append("</table>");

		return sb.toString().replace("KEY", record.getId());


	}






	public static String getFormHtml(ArrayList<HTMLTableColumn> columns, String additionalAttributes) {

		StringBuilder sb = new StringBuilder();

		//sb.append("<table width='" + tableWidth + "' " + additionalAttributes + ">");
		//sb.append("	<tr>");

		int c = 0;

		for (HTMLTableColumn column : columns) {
			//sb.append("		<th class='tableHeader'>" + column.getHeaderText() + "</th>");

			c++;

			if(c%2 == 0) {
				sb.append("<div style='background-color:#EEEEEE'>");
			} else {

				sb.append("<div>");
			}


			////////////////<div class="col-md-6 col-sm-6 col-xs-12" style="padding-left: 0px;">

			//align='right'
			 if(!column.getType().equals("hidden")){
				sb.append("<div class=\"col-md-6 col-sm-6 col-xs-12\">");
				sb.append("<label for=\"text\" style=\"display: inline-block;\">" + column.getHeaderText() + "</label> ");
				sb.append("</div>");
			}
			////////////////</div>


			//StringBuilder recordStringBuilder = new StringBuilder();

			//String cssClass = (rowIndex % 2 == 0) ? "tableRow2" : "";

			//recordStringBuilder.append("		<tr class='" + cssClass + "'>");

			//int colIndex = 0;

			///for (HTMLTableColumn column : columns) {
				/*
				String columnAlign = columnAligns[colIndex];
				String columnType = columnTypes[colIndex];
				String columnEvent = columnEvents[colIndex];*/

				//recordStringBuilder.append("			<td align='" + column.getAlign() + "'>");

				/*if(col.getHtmlText() != null) {

					sb.append(col.getHtmlText()
							.replace("KEY", record.get(flexListPage.getKeyColumn()))
							.replace("CONTEXT_PATH", request.getServletContext().getContextPath())
							);
				} else {

					sb.append(record.getString(col.getName()));
				}*/

			sb.append("<div class=\"col-md-6 col-sm-6 col-xs-12\">");


				if(column.getType().equals("label")) {

					//sb.append(record.getString(column.getCode()));
					//sb.append(column.getJsCode().replace("THIS_VALUE", record.getString(column.getCode())));


					sb.append("");

				} else if(column.getType().equals("text"))  {

					//placeholder=\"" + column.getHeaderText() + " \" required

					sb.append("<input type='text' id='" + column.getCode() + "'  "
							 +
							 column.getJsCode() + column.getAttributes() + " class=\"form-control\">"); //


				} else if(column.getType().equals("hidden"))  {

					//placeholder=\"" + column.getHeaderText() + " \" required

					sb.append("<input type='hidden' id='" + column.getCode() + "'  "
							 +
							 column.getJsCode() + column.getAttributes() + " class=\"form-control\">"); //


				} else if(column.getType().equals("textarea"))  {

					sb.append("<textarea id='" + column.getCode() + "' " + column.getJsCode() + " " + column.getAttributes() + "></textarea>");


				} else if(column.getType().equals("date"))  {

						//size='8'
					sb.append("<input type='date' id='" + column.getCode() + "' " + column.getJsCode() + "name='" + column.getCode() + "' " + "class=\"form-control\" style=\"width: 300px\">");

				} else if(column.getType().equals("time"))  {

						//recordStringBuilder.append("<input type='time' size='4' id='" + column.getCode() + "' value='" + record.getString(column.getCode()) + "' " + column.getJsCode() + ">");

				} else if(column.getType().equals("smallTextNonZero"))  {

					/**
					 * Especifico de  Foodsol
					 *
					 * */
					/*String str = record.getString(column.getCode());

					if(str.equals("0")) {

						str = "";
					}

					recordStringBuilder.append("<input type='text' size='2' value='" + str + "' " + column.getJsCode() + ">");*/

				} else if(column.getType().equals("checkbox"))  {

					/*String checked = (record.getString(column.getCode()).equals("1"))? "checked=checked" : "";

					recordStringBuilder.append("<input type='checkbox' value='" + record.getString(column.getCode()) + "' " + checked + " " + column.getJsCode() + ">");*/

				} else if(column.getType().equals("select"))  {


						//
						// YEAH
						//


						//String checked = (record.getString(column.getCode()).equals("1"))? "checked=checked" : "";

						//String currVal = record.getString(column.getCode());
						String currVal = "";
						String selectHTML = SelectList.getSelectList(column, currVal);

						sb.append(selectHTML);


				} else if(column.getType().equals("select2"))  {

					String currVal = "";
					String selectHTML = SelectList2.getSelectList(column, currVal);

					sb.append(selectHTML);




				} else if(column.getType().equals("link")) {

					//recordStringBuilder.append("<a " + column.getJsCode().replace(column.getCode(), record.getString(column.getCode())) + ">" +  column.getAttributes()[0] + "</a>");

				} else if(column.getType().equals("custom"))  {

					//recordStringBuilder.append(column.getJsCode().replace("KEY", record.getId()));

				}else if(column.getType().equals("dateYear"))  {
					sb.append("<input type='number' placeholder='YYYY' min='2017' max='2100' id='" + column.getCode() + "' " + column.getJsCode() + "name='" + column.getCode() + "' " + "class=\"form-control\" style=\"width: 200px\">");
				}
				
				
				


				//recordStringBuilder.append("			</td>");

				//colIndex++;
			//}

			//recordStringBuilder.append("		</tr>");


			//String sb =

			//sb.append(recordStringBuilderFixed);


			//rowIndex++;




				sb.append("</div>");


				sb.append("</div>");

		}

		//sb.append("	</tr>");

		//int rowIndex = 1;

		//for (Record record : records) {

		//}
		//sb.append("</table>");
//
//		return sb.toString().replace("KEY", record.getId());


		return sb.toString();


	}




}
