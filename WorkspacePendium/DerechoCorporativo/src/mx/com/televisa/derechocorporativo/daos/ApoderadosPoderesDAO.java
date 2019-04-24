package mx.com.televisa.derechocorporativo.daos;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import mx.com.televisa.derechocorporativo.bean.ApoderadosPoderesBean;
import mx.com.televisa.derechocorporativo.bean.PoderesBean;
import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;
import mx.com.televisa.derechocorporativo.data.packages.DERCORP_APODERADOS_PODERES_PKG;
import mx.com.televisa.derechocorporativo.modules.captura.Catalogo;
import mx.com.televisa.derechocorporativo.modules.captura.CatalogoValor;
import mx.com.televisa.derechocorporativo.modules.flextabs.FlexColumn;

public class ApoderadosPoderesDAO {
	private CallableStatement callState;
	private ResultSet resultSet;

	public ApoderadosPoderesDAO(){
		
	}

	public List<PoderesBean> getPoderes(int tIdEmpresa){
		
		List<PoderesBean> listPoderes = new ArrayList<PoderesBean>();
		PoderesBean poderesBean;
		ConnectionWrapper tConnectionWrapper = null;
		try {
			tConnectionWrapper = new ConnectionWrapper();
			callState = tConnectionWrapper.prepareCall(DERCORP_APODERADOS_PODERES_PKG.GET_PODERES_GENERALES_PR);
			callState.registerOutParameter(1, OracleTypes.CURSOR);
			callState.setInt(2, tIdEmpresa);
			callState.execute();

			resultSet = ((OracleCallableStatement) callState).getCursor(1);

			while(resultSet.next()){
				poderesBean = new PoderesBean(
								 resultSet.getInt("ID_PODER")
								,resultSet.getInt("ID_EMPRESA")
								,resultSet.getString("IN_TIPO_PODER")
								,resultSet.getInt("IND_DELEGADO_POR")
								,resultSet.getString("FEC_FECHA")
								,resultSet.getString("FEC_HORA")
								,resultSet.getString("IND_TIPO_DOCUMENTO")
								,resultSet.getString("DES_ESCRITURA")
								,resultSet.getString("FEC_OTORGAMIENTO_INSTR")
								,resultSet.getString("NUM_DOCUMENTUM_INSTR")
								,resultSet.getString("IND_REQUIERE_PROTO")
								,resultSet.getString("IND_REQUIERE_INSCR_RPPC")
								,resultSet.getString("NOM_SEMAFORO")
								,resultSet.getInt("NUM_LICENCIADO")
								,resultSet.getString("NOM_NOTARIO_PUBLICO")
								,resultSet.getInt("NUM_DE")
								,resultSet.getString("DES_SUPLENCIA_ASOCIADO")
								,resultSet.getString("NUM_INSCRITA_REGISTRO_PUBLICO")
								,resultSet.getString("FEC_REGISTRO")
								,resultSet.getString("NUM_FOLIO_MERC")
								,resultSet.getString("DES_OTROS_DATOS_REGISTRO")
								,resultSet.getString("IND_MEMO")
								,resultSet.getInt("NUM_SOLICITADO_POR")
								,resultSet.getString("FEC_DOCUMENTO_MEMO")
								,resultSet.getString("FEC_RECIBIDO_MEMO")
								,resultSet.getString("NUM_FOLIO")
								,resultSet.getString("NUM_DOCUMENTUM_MEMO")
								,resultSet.getString("IND_DOC_ENTREGA")
								,resultSet.getString("FEC_DOCUMENTO_ENTREGA")
								,resultSet.getString("FEC_RECIBIDO_ENTREGA")
								,resultSet.getString("NUM_DOCUMENTUM_ENTREGA")
								,resultSet.getString("IND_OTROS")
								,resultSet.getString("FEC_DOCUMENTO_OTROS")
								,resultSet.getString("FEC_RECIBIDO_OTROS")
								,resultSet.getString("NUM_DOCUMENTUM_OTROS")
								,resultSet.getString("IND_APLICA_STATUS")
								,resultSet.getString("NOM_SEMAFORO_STATUS")
								,resultSet.getString("FEC_PROG_ENTREGA_STATUS")
								,resultSet.getString("IND_REDACTADA")
								,resultSet.getInt("NUM_RESP_REDACTADA")
								,resultSet.getString("FEC_CUMPLIMIENTO_REDACTADA")
								,resultSet.getString("IND_REVISION_GERENTE")
								,resultSet.getInt("NUM_RESP_GERENTE")
								,resultSet.getString("FEC_CUMPLIMIENTO_GERENTE")
								,resultSet.getString("IND_CORRECCIONES")
								,resultSet.getInt("NUM_RESP_CORRECCIONES")
								,resultSet.getString("FEC_CUMPLIMIENTO_CORRECCIONES")
								,resultSet.getString("IND_AUT_DIRECCION")
								,resultSet.getInt("NUM_RESP_AUT")
								,resultSet.getString("FEC_CUMPLIMIENTO_AUT")
								,resultSet.getString("IND_FIRMAS")
								,resultSet.getInt("NUM_RESP_FIRMAS")
								,resultSet.getString("FEC_CUMPLIMIENTO_FIRMAS")
								,resultSet.getString("IND_ENTREGADA")
								,resultSet.getInt("NUM_RESP_ENTREGADA")
								,resultSet.getString("FEC_CUMPLIMIENTO_ENTREGADA")
								,resultSet.getString("NUM_ENVIADA_NOTARIA")
								,resultSet.getString("FEC_ENVIO_NOTARIA")
								,resultSet.getString("IND_PODER_ASUNTO")
								,resultSet.getString("IND_TIPO_ARMADO"));
				listPoderes.add(poderesBean);
			}

		} catch (Exception e) {
            e.printStackTrace();
		}finally{
			tConnectionWrapper.close();
		}

		return listPoderes;
	}
	
	public static String getSelectList(String pValue, int pCatval) {
		
		ConnectionWrapper luConnect = null;
		ResultSet 		  luRs      = null;
		StringBuilder sb 			= new StringBuilder();
		PreparedStatement psmt 		= null;
		String lsSqlCatalogoValor = "SELECT * "
						           + "FROM DERCORP_ADD_CAMPO_CAT_VAL_TAB "
						           + "WHERE 1=1 "
						           + "AND ID_CATALOGO = ?\n"
						           + "ORDER BY UPPER(APP_COMMON_PKG.SIN_ACENTOS_FN(VAL_CAT_VAL))" ;
				

		try {
			luConnect = new ConnectionWrapper();
			psmt = luConnect.prepareStatement(lsSqlCatalogoValor);
			psmt.setInt(1, pCatval);
			luRs = psmt.executeQuery();
			//sb.append("<option value='0'>---Seleccione---</option>");
			while(luRs.next()) {					 
					 sb.append("<option value='" + luRs.getString("ID_CATALOGO_VALOR") + "' " +"title='"+luRs.getString("VAL_CAT_VAL")+"'");
	
					 if (luRs.getString("ID_CATALOGO_VALOR").equals(pValue)){
						 sb.append("selected");
					 }
	
					 sb.append(" >" + luRs.getString("VAL_CAT_VAL") + "</option>");
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				luRs.close();
				psmt.close();
				luConnect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		sb.append("</select>");
		

		return sb.toString();
	}

}
