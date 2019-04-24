package mx.com.televisa.derechocorporativo.modules.reports.ecs;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.data.packages.DERCORP_CONSULTA_PKG;
import mx.com.televisa.derechocorporativo.modules.captura.EmpresaValues;
import mx.com.televisa.derechocorporativo.modules.captura.Pagina;

public class ReporteECSeccion {
	
	ConnectionWrapper tuConnectionWrapper;
	final static Logger log = Logger.getLogger(ReporteECSeccion.class);
	
	public ReporteECSeccion(){
		
	}
	
	
	
public String toHTML(String lstIdEmpresa, Pagina pagina) throws Exception {
        
	    tuConnectionWrapper = new ConnectionWrapper();
		StringBuilder         luStringBuilder = new StringBuilder();
		

//		log.info("Inicio Reporte de Estructura de Capital Social ");
        ArrayList<ReporteECSubseccion> laSubSecciones = null;
        
        laSubSecciones  = getSubSeccionesConInfo(tuConnectionWrapper, lstIdEmpresa);
      

        int liIdTable = 0;
        String nomEmpresa = EmpresaValues.getNomEmpresa(lstIdEmpresa);
        luStringBuilder.append("<strong><span style='font-size:22px;'>"+nomEmpresa+"</span></strong>");
        //luStringBuilder.append("<table  width='100%' border='0' id='tbl_principal'>");
        //luStringBuilder.append("<tr>");
        //luStringBuilder.append("<td>");
        for (ReporteECSubseccion luSubSeccion : laSubSecciones) {
        	String hideText = "";
			
	            luStringBuilder.append("<table width='100%' border='0' id='tbl_stat_"+liIdTable+"' name='tbl_stat_"+liIdTable+"'>");
	            
					//Fila que determina las posiciones de los campos
		            luStringBuilder.append("<tr>");
		            luStringBuilder.append("<td width='17%'></td>");
		            luStringBuilder.append("<td width='8%'></td>");
		            luStringBuilder.append("<td width='8%'></td>");
		            luStringBuilder.append("<td width='17%'></td>");
		            luStringBuilder.append("<td width='17%'></td>");
		            luStringBuilder.append("<td width='8%'></td>");
		            luStringBuilder.append("<td width='8%'></td>");
		            luStringBuilder.append("<td width='17%'></td>");
		            luStringBuilder.append("</tr>");
	            
	
		            luStringBuilder.append("<tr>");
		            luStringBuilder.append("<td colspan='8'>");
		            luStringBuilder.append((luSubSeccion.desSubSeccion != null)? luSubSeccion.desSubSeccion: "");
		            luStringBuilder.append("</td>");
		            luStringBuilder.append("</tr>");
	            
	            luStringBuilder.append(luSubSeccion.toHTML(tuConnectionWrapper, pagina,lstIdEmpresa));
	            
	
	            luStringBuilder.append("<tr><td>&nbsp;</td><td>&nbsp;</td></tr>	");
	
//	            luStringBuilder.append("</table>");
//	            luStringBuilder.append("</td>");
//	            luStringBuilder.append("</tr>");
	            
	            liIdTable++;
			/*	 if(laSubSecciones.size() > liIdTable){
					 luStringBuilder.append("<tr><td>&nbsp;</td></tr>");
				            }*/
			
		}
        luStringBuilder.append("</td>");
        luStringBuilder.append("</tr>");
        luStringBuilder.append("</table>");
        ConnectionWrapper.closeAll(tuConnectionWrapper);
        log.info("Termino Reporte de Estructura de Capital Social ");
		return luStringBuilder.toString();

	}

public ArrayList<ReporteECSubseccion> getSubSeccionesConInfo(ConnectionWrapper tuConnectionWrapper, String idEmpresa) {
	
    ArrayList<ReporteECSubseccion> laSubSecciones      = new ArrayList<ReporteECSubseccion>();
	ResultSet             luResultSet         = null;
	CallableStatement     luCallableStatement = null;

	try {
		luCallableStatement = tuConnectionWrapper.prepareCall(DERCORP_CONSULTA_PKG.GET_SUB_SECCIONES_CON_INFO_PR);
		
		luCallableStatement.setInt(1, 19);
		luCallableStatement.setString(2, idEmpresa);
		
		luCallableStatement.registerOutParameter(3, OracleTypes.CURSOR);
		luCallableStatement.execute();
		luResultSet = ((OracleCallableStatement)luCallableStatement).getCursor(3);

		while (luResultSet.next()) {
			laSubSecciones.add(new ReporteECSubseccion(luResultSet, luResultSet.getMetaData()));
		}

	} catch (Exception ex) {
		// TODO - Pendiente - Gestion de Excepciones
		ex.printStackTrace();

	} finally {
		ConnectionWrapper.closeAll(luResultSet, luCallableStatement);

		/*try{
			set.close();
			psmt.close();
		} catch (Exception ex){ 
			ex.printStackTrace();
		}*/

	}

	return laSubSecciones;
}



}
