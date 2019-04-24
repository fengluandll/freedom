package com.movemini.simpleflexgrid;

import java.util.ArrayList;

import com.movemini.config.HardCodeConstants;
import com.movemini.data.Record;
import com.movemini.simpleflexgrid.components.SelectList;
import com.movemini.simpleflexgrid.components.SelectList2;
import com.movemini.simpleflexgrid.components.SelectList3;
import com.movemini.simpleflexgrid.components.SelectListSmall;
import com.movemini.util.StringUtils;;

public class HTMLTable {


	public static String getHtml(ArrayList<Record> records, ArrayList<HTMLTableColumn> columns, String tableWidth, String additionalAttributes) {
	
		StringBuilder sb = new StringBuilder();
		
		sb.append("<table width='" + tableWidth + "' " + additionalAttributes + ">");
		sb.append("<thead>	<tr>");
		
		for (HTMLTableColumn column : columns) {
			sb.append("		<th class='tableHeader'>" + column.getHeaderText() + "</th>");
		}
		
		sb.append("	</tr></thead>");
		
		int rowIndex = 1;
		sb.append("<tbody>");
		for (Record record : records) {
			
			StringBuilder recordStringBuilder = new StringBuilder();
			
			String cssClass = (rowIndex % 2 == 0) ? "tableRow2" : ""; 
			
			recordStringBuilder.append("		<tr class='" + cssClass + "'>");
			
			int colIndex = 0;
			
			for (HTMLTableColumn column : columns) {
				/*
				String columnAlign = columnAligns[colIndex];
				String columnType = columnTypes[colIndex];
				String columnEvent = columnEvents[colIndex];*/
				
				recordStringBuilder.append("			<td align='" + column.getAlign() + "'>");
			
				
				recordStringBuilder.append("<div id='div-" + column.getCode() + "-" + record.getId() + "'>");
				
				
				
				/*if(col.getHtmlText() != null) {
					
					sb.append(col.getHtmlText()
							.replace("KEY", record.get(flexListPage.getKeyColumn()))
							.replace("CONTEXT_PATH", request.getServletContext().getContextPath())
							);	
				} else {
					
					sb.append(record.getString(col.getName()));	
				}*/
				
				if(column.getType().equals("label")) {
				
					recordStringBuilder.append(record.getString(column.getCode()));
					recordStringBuilder.append(column.getJsCode().replace("THIS_VALUE", record.getString(column.getCode())));
					
				} else if(column.getType().equals("text") || column.getType().equals("number"))  {
					
					recordStringBuilder.append("<input type='" + column.getType() + "' id='" + column.getCode() + "' value='" + record.getString(column.getCode()) + "' " + column.getJsCode() + " " + column.getAttributes() + ">");
				
					
				} else if(column.getType().equals("text4") || column.getType().equals("text5") || column.getType().equals("text6") || column.getType().equals("text7") || column.getType().equals("text8"))  {
					
					
					int size = Integer.parseInt(column.getType().replaceAll("text","")); 
					//String colType = StringUtils.removeLastChar(column.getType());
					
					recordStringBuilder.append("<input type='text' size='" + size + "' id='" + column.getCode() + "' value='" + record.getString(column.getCode()) + "' " + column.getJsCode() + ">");
					
					
					
					
				} else if(column.getType().equals("text5"))  {
					
					
					//String colType = StringUtils.removeLastChar(column.getType());
					
					recordStringBuilder.append("<input type='text' size='5' id='" + column.getCode() + "' value='" + record.getString(column.getCode()) + "' " + column.getJsCode() + ">");
					
					
					
				} else if(column.getType().equals("text7"))  {
					
					
					//String colType = StringUtils.removeLastChar(column.getType());
					
					recordStringBuilder.append("<input type='text' size='7' id='" + column.getCode() + "' value='" + record.getString(column.getCode()) + "' " + column.getJsCode() + ">");
					
					
					
				} else if(column.getType().equals("textarea"))  {
					
					
					//String colType = StringUtils.removeLastChar(column.getType());
					
					recordStringBuilder.append("<textarea style='width:100%;' rows='2' id='" + column.getCode() + "' " + column.getJsCode() + ">" + record.getString(column.getCode()) + "</textarea>");
					

				} else if(column.getType().equals("textarea350"))  {
					
					
					//String colType = StringUtils.removeLastChar(column.getType());
					
					recordStringBuilder.append("<textarea style='width:350px;' rows='2' id='" + column.getCode() + "' " + column.getJsCode() + ">" + record.getString(column.getCode()) + "</textarea>");
				
				} else if(column.getType().equals("textarea250"))  {
					
					
					//String colType = StringUtils.removeLastChar(column.getType());
					
					recordStringBuilder.append("<textarea style='width:250px;' rows='2' id='" + column.getCode() + "' " + column.getJsCode() + ">" + record.getString(column.getCode()) + "</textarea>");
					


				} else if(column.getType().equals("textarea150"))  {
					
					
					//String colType = StringUtils.removeLastChar(column.getType());
					
					recordStringBuilder.append("<textarea style='width:150px;' rows='2' id='" + column.getCode() + "' " + column.getJsCode() + ">" + record.getString(column.getCode()) + "</textarea>");
					
					
				} else if(column.getType().equals("number4"))  {
					
					recordStringBuilder.append("<input type='number' size='40px' id='" + column.getCode() + "' value='" + record.getString(column.getCode()) + "' " + column.getJsCode() + ">");
					
					
					//width:50px;
					
						
				//} else if(column.getType().equals("text4") || column.getType().equals("number4"))  {
					
					
					//String colType = StringUtils.removeLastChar(column.getType());
					
					//recordStringBuilder.append("<input type='" + colType + "' size='4' id='" + column.getCode() + "' value='" + record.getString(column.getCode()) + "' " + column.getJsCode() + ">");
					
					
					
					//width:50px;
					
					
					
				} else if(column.getType().equals("select"))  {
					
					
					//
					// YEAH
					//
				
				
					//String checked = (record.getString(column.getCode()).equals("1"))? "checked=checked" : "";
					
					String currVal = record.getString(column.getCode());
					String selectHTML = SelectList.getSelectList(column, currVal, column.getAttributes());
					
					recordStringBuilder.append(selectHTML);
					
					
					
					
					
				} else if(column.getType().equals("select2"))  {
					

					String currVal = record.getString(column.getCode());
					String selectHTML = SelectList2.getSelectList(column, currVal, column.getAttributes());
					
					recordStringBuilder.append(selectHTML);
									
				} else if(column.getType().equals("select3"))  {
					
				
					String currVal = record.getString(column.getCode());
					String selectHTML = SelectList3.getSelectList(column, currVal, column.getAttributes());
					
					recordStringBuilder.append(selectHTML);
	
					
				} else if(column.getType().equals("select_small"))  {
					
					
					//
					// YEAH
					//
				
				
					//String checked = (record.getString(column.getCode()).equals("1"))? "checked=checked" : "";
					
					String currVal = record.getString(column.getCode());
					String selectHTML = SelectListSmall.getSelectList(column, currVal, column.getAttributes());
					
					recordStringBuilder.append(selectHTML);
					
					
				} else if(column.getType().equals("date"))  {
						
						recordStringBuilder.append("<input type='date' size='8' id='" + column.getCode() + "' value='" + record.getString(column.getCode()) + "' " + column.getJsCode() + ">");
				
				} else if(column.getType().equals("time"))  {
							
						recordStringBuilder.append("<input type='time' size='4' id='" + column.getCode() + "' value='" + record.getString(column.getCode()) + "' " + column.getJsCode() + ">");
						
				} else if(column.getType().equals("smallTextNonZero"))  {
					
					/**
					 * Especifico de  Foodsol 
					 * 
					 * */
					String str = record.getString(column.getCode());
					
					if(str.equals("0")) {
						
						str = "";
					}
					
					recordStringBuilder.append("<input type='text' size='2' value='" + str + "' " + column.getJsCode() + ">");
					
				} else if(column.getType().equals("checkbox"))  {
					
					String checked = (record.getString(column.getCode()).equals("1"))? "checked=checked" : "";
					
					recordStringBuilder.append("<input type='checkbox' value='" + record.getString(column.getCode()) + "' " + checked + " " + column.getJsCode() + ">");
				
				} else if(column.getType().equals("link")) {
					
					recordStringBuilder.append("<a " + column.getJsCode().replace(column.getCode(), record.getString(column.getCode())) + ">" +  column.getAttributes() + "</a>");
					
				} else if(column.getType().equals("custom"))  {
					
					recordStringBuilder.append(column.getJsCode().replace("KEY", record.getId()));
					
				}  else if(column.getType().equals("USER_AVATAR"))  {
					
					String userId = record.getString(column.getCode());
					
					recordStringBuilder.append("<img id='profileImg1' src='/" + HardCodeConstants.CONTEXT_PATH + "/ProfilePhotoServlet?idUser=" + userId + "' width='60px' height='60px' style='width:60px;height:60px;' class='img-circle profile_img'>");
				}
				
				
				
				
				
				
				
				
				recordStringBuilder.append("</div>");
				
				
				recordStringBuilder.append("			</td>");
				
				colIndex++;
			}
			
			recordStringBuilder.append("		</tr>");

			
			String recordStringBuilderFixed = recordStringBuilder.toString().replace("KEY", record.getId());
			
			sb.append(recordStringBuilderFixed);
			
			
			rowIndex++;
		}
		sb.append("</tbody>");
		sb.append("</table>");

		return sb.toString();
	
		
	}
}
