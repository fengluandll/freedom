package mx.com.televisa.derechocorporativo.daos;

import java.util.ArrayList;
import java.util.List;
 


import org.apache.log4j.Logger;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import mx.com.televisa.derechocorporativo.bean.GenericBean;
import mx.com.televisa.derechocorporativo.bean.GenericDataBean;

public class MngDataPoderes {
	protected static String PKG = "DERCORP_PODERES_PKG";
	protected static String PKG_CONSULTA = "PENDIUM_CON_PODERES_PKG";
	protected static String PKG_REPORTES = "PENDIUM_REPORTES_PODERES_PKG";
	final static Logger logger = Logger.getLogger(MngDataPoderes.class);
	
	public static GenericDataBean queryCATALOGOS(int id) throws Exception
    {
        List<SqlParameter> args = new ArrayList<SqlParameter>();
        args.add( DAOModel.createParam("porcRSResultado", OracleTypes.CURSOR) );
        args.add( DAOModel.createParam("pinID_CATALOGO", OracleTypes.INTEGER, id) );
        
        return DAOModel.executeSpQueryGDB(PKG,"QUERY_CATALOGOS_PR", args);
    }
	
	public static GenericDataBean queryESCRITURA_PODER(int idEmpresa,String type) throws Exception
    {
        List<SqlParameter> args = new ArrayList<SqlParameter>();
        args.add( DAOModel.createParam("porcRSResultado", OracleTypes.CURSOR) );
        args.add( DAOModel.createParam("pinid_empresa", OracleTypes.NUMBER, idEmpresa) );
        args.add( DAOModel.createParam("pstind_tipo_escritura", OracleTypes.VARCHAR, type) );
        
        return DAOModel.executeSpQueryGDB(PKG,"QUERY_ESCRITURA_PODER_PR", args);
    }
	
	public static GenericDataBean queryESCRITURA_PODER(int idEmpresa, String type, String paramDeBusqueda) throws Exception
    {
        List<SqlParameter> args = new ArrayList<SqlParameter>();
        args.add( DAOModel.createParam("porcRSResultado", OracleTypes.CURSOR) );
        args.add( DAOModel.createParam("pinid_empresa", OracleTypes.NUMBER, idEmpresa) );
        args.add( DAOModel.createParam("pstind_tipo_escritura", OracleTypes.VARCHAR, type) );
        args.add( DAOModel.createParam("pstdesc_busqueda", OracleTypes.VARCHAR, paramDeBusqueda));
                      
        return DAOModel.executeSpQueryGDB(PKG, "QUERY_ESCRITURA_PODER_PR", args);
    }
	
	public static GenericDataBean queryESCRITURA_PODER(int id_escritura) throws Exception
    {
        List<SqlParameter> args = new ArrayList<SqlParameter>();
        args.add( DAOModel.createParam("porcRSResultado", OracleTypes.CURSOR) );
        args.add( DAOModel.createParam("pinid_escritura", OracleTypes.NUMBER, id_escritura) );
                      
        return DAOModel.executeSpQueryGDB(PKG, "QUERY_ESCRITURA_PODER_PR", args);
    }
	
	public static int insertESCRITURA_PODER(GenericBean bean) throws Exception
    {
        List<SqlParameter> args = new ArrayList<SqlParameter>();        
        args.add( DAOModel.createParam("pinid_empresa", OracleTypes.NUMBER, bean.getProperty("id_empresa")) );
        args.add( DAOModel.createParam("pstind_tipo_escritura", OracleTypes.VARCHAR,  bean.getProperty("ind_tipo_escritura")) );
        args.add( DAOModel.createParam("pinnum_created_by", OracleTypes.NUMBER, bean.getProperty("num_created_by")) );
        args.add( DAOModel.createParam("pinind_delegado_por", OracleTypes.INTEGER,  bean.getProperty("ind_delegado_por")) );
        args.add( DAOModel.createParam("pstfec_fecha", OracleTypes.VARCHAR,  bean.getProperty("fec_fecha")) );
        args.add( DAOModel.createParam("pstfec_hora", OracleTypes.VARCHAR,  bean.getProperty("fec_hora")) );
        args.add( DAOModel.createParam("pinind_requiere_proto", OracleTypes.INTEGER,  bean.getProperty("ind_requiere_proto")) );
        args.add( DAOModel.createParam("pinind_requiere_inscr_rppc", OracleTypes.INTEGER,  bean.getProperty("ind_requiere_inscr_rppc")) );
        args.add( DAOModel.createParam("pstdes_escritura", OracleTypes.VARCHAR,  bean.getProperty("des_escritura")) );
        args.add( DAOModel.createParam("pstnum_documentum_instr", OracleTypes.VARCHAR,  bean.getProperty("num_documentum_instr")) );
        args.add( DAOModel.createParam("pstfec_otorgamiento_instr", OracleTypes.VARCHAR,  bean.getProperty("fec_otorgamiento_instr")) );
        args.add( DAOModel.createParam("pstnum_licenciado", OracleTypes.VARCHAR,  bean.getProperty("num_licenciado")) );
        args.add( DAOModel.createParam("pstdes_suplencia_asociado", OracleTypes.VARCHAR,  bean.getProperty("des_suplencia_asociado")) );
        args.add( DAOModel.createParam("pstfec_registro", OracleTypes.VARCHAR,  bean.getProperty("fec_registro")) );
        args.add( DAOModel.createParam("pstnum_folio_merc", OracleTypes.VARCHAR,  bean.getProperty("num_folio_merc")) );
        args.add( DAOModel.createParam("pstdes_otros_datos_registro", OracleTypes.VARCHAR,  bean.getProperty("des_otros_datos_registro")) );
        String desc_apod = bean.getProperty("desc_apoderados").toString();
        int lenght = desc_apod.length();
        args.add( DAOModel.createParam("pstdesc_apoderados", OracleTypes.CLOB,  desc_apod) );
        String desc_asunto = bean.getProperty("desc_asunto").toString();
        int lenghtdesc_asunto = desc_asunto.length();
        desc_asunto = desc_asunto.length() > 2000 ? desc_asunto.substring(0, 1990)+"..." : desc_asunto;
        args.add( DAOModel.createParam("pstdesc_asunto", OracleTypes.VARCHAR,  desc_asunto) );
        //args.add( DAOModel.createParam("pstdesc_asunto", OracleTypes.VARCHAR,  bean.getProperty("desc_asunto")) );
        args.add( DAOModel.createParam("pstdes_revoca", OracleTypes.VARCHAR,  bean.getProperty("des_revoca")) );
        
        args.add( DAOModel.createParam("pinind_ok", OracleTypes.NUMBER,  bean.getProperty("ind_ok")) );
        args.add( DAOModel.createParam("pstfec_pe", OracleTypes.VARCHAR,  bean.getProperty("fec_pe")) );
        args.add( DAOModel.createParam("pstind_status_ac", OracleTypes.VARCHAR,  bean.getProperty("ind_status_ac")) );
        args.add( DAOModel.createParam("pinid_red_resp", OracleTypes.NUMBER,  bean.getProperty("id_red_resp")) );
        args.add( DAOModel.createParam("pstdes_rep_resp", OracleTypes.VARCHAR,  bean.getProperty("des_rep_resp")) );
        args.add( DAOModel.createParam("pstfec_rep", OracleTypes.VARCHAR,  bean.getProperty("fec_rep")) );
        args.add( DAOModel.createParam("pinid_reg_resp", OracleTypes.NUMBER,  bean.getProperty("id_reg_resp")) );
        args.add( DAOModel.createParam("pstdes_reg_resp", OracleTypes.VARCHAR,  bean.getProperty("des_reg_resp")) );
        args.add( DAOModel.createParam("pstfec_reg", OracleTypes.VARCHAR,  bean.getProperty("fec_reg")) );
        args.add( DAOModel.createParam("pinid_cor_resp", OracleTypes.NUMBER,  bean.getProperty("id_cor_resp")) );
        args.add( DAOModel.createParam("pstdes_cor_resp", OracleTypes.VARCHAR,  bean.getProperty("des_cor_resp")) );
        args.add( DAOModel.createParam("pstfec_cor", OracleTypes.VARCHAR,  bean.getProperty("fec_cor")) );
        args.add( DAOModel.createParam("pinid_aut_resp", OracleTypes.NUMBER,  bean.getProperty("id_aut_resp")) );
        args.add( DAOModel.createParam("pstdes_aut_resp", OracleTypes.VARCHAR,  bean.getProperty("des_aut_resp")) );
        args.add( DAOModel.createParam("pstfec_aut", OracleTypes.VARCHAR,  bean.getProperty("fec_aut")) );
        args.add( DAOModel.createParam("pinid_fir_resp", OracleTypes.NUMBER,  bean.getProperty("id_fir_resp")) );
        args.add( DAOModel.createParam("pstdes_fir_resp", OracleTypes.VARCHAR,  bean.getProperty("des_fir_resp")) );
        args.add( DAOModel.createParam("pstfec_fir", OracleTypes.VARCHAR,  bean.getProperty("fec_fir")) );
        args.add( DAOModel.createParam("pinid_ent_resp", OracleTypes.NUMBER,  bean.getProperty("id_ent_resp")) );
        args.add( DAOModel.createParam("pstdes_ent_resp", OracleTypes.VARCHAR,  bean.getProperty("des_ent_resp")) );
        args.add( DAOModel.createParam("pstfec_ent", OracleTypes.VARCHAR,  bean.getProperty("fec_ent")) );
        args.add( DAOModel.createParam("pstid_sol_doc", OracleTypes.VARCHAR,  bean.getProperty("id_sol_doc")) );
        args.add( DAOModel.createParam("pinid_sol_resp", OracleTypes.NUMBER,  bean.getProperty("id_sol_resp")) );
        args.add( DAOModel.createParam("pstdes_sol_resp", OracleTypes.VARCHAR,  bean.getProperty("des_sol_resp")) );
        args.add( DAOModel.createParam("pstfec_sol", OracleTypes.VARCHAR,  bean.getProperty("fec_sol")) );
        args.add( DAOModel.createParam("pstfec_sol_rec", OracleTypes.VARCHAR,  bean.getProperty("fec_sol_rec")) );
        args.add( DAOModel.createParam("pstdes_sol_folio", OracleTypes.VARCHAR,  bean.getProperty("des_sol_folio")) );
        args.add( DAOModel.createParam("pstid_ent_doc", OracleTypes.VARCHAR,  bean.getProperty("id_ent_doc")) );
        args.add( DAOModel.createParam("pstfec_ent_doc", OracleTypes.VARCHAR,  bean.getProperty("fec_ent_doc")) );
        args.add( DAOModel.createParam("pstfec_ent_rec", OracleTypes.VARCHAR,  bean.getProperty("fec_ent_rec")) );
        args.add( DAOModel.createParam("pinnum_insc_regpub", OracleTypes.NUMBER,  bean.getProperty("num_insc_regpub")) );
        args.add( DAOModel.createParam("pstdes_insc_regpub", OracleTypes.VARCHAR,  bean.getProperty("des_insc_regpub")) );
        args.add( DAOModel.createParam("pstdes_caracteristicas", OracleTypes.VARCHAR,  bean.getProperty("des_caracteristicas")) );
        args.add( DAOModel.createParam("pinind_aplica_status", OracleTypes.VARCHAR,  bean.getProperty("ind_aplica_status")) );
        
        args.add( DAOModel.createParam("pinID_EP", OracleTypes.INTEGER));
        args.add( DAOModel.createParam("pstOuterror",OracleTypes.VARCHAR));
        
        List<Object> results = DAOModel.executeSp(PKG,"INSERT_ESCRITURA_PODER_PR", args);
        
        return (int)results.get(0); 
        
    }
	
	public static void updateESCRITURA_PODER(GenericBean bean) throws Exception
    {
        List<SqlParameter> args = new ArrayList<SqlParameter>();        
        args.add( DAOModel.createParam("pinid_ep_pk", OracleTypes.NUMBER, bean.getProperty("id_ep_pk")) );
        args.add( DAOModel.createParam("pstind_tipo_escritura", OracleTypes.VARCHAR,  bean.getProperty("ind_tipo_escritura")) );
        args.add( DAOModel.createParam("pinnum_created_by", OracleTypes.NUMBER, bean.getProperty("num_created_by")) );
        args.add( DAOModel.createParam("pinind_delegado_por", OracleTypes.INTEGER,  bean.getProperty("ind_delegado_por")) );
        args.add( DAOModel.createParam("pstfec_fecha", OracleTypes.VARCHAR,  bean.getProperty("fec_fecha")) );
        args.add( DAOModel.createParam("pstfec_hora", OracleTypes.VARCHAR,  bean.getProperty("fec_hora")) );
        args.add( DAOModel.createParam("pinind_requiere_proto", OracleTypes.INTEGER,  bean.getProperty("ind_requiere_proto")) );
        args.add( DAOModel.createParam("pinind_requiere_inscr_rppc", OracleTypes.INTEGER,  bean.getProperty("ind_requiere_inscr_rppc")) );
        args.add( DAOModel.createParam("pstdes_escritura", OracleTypes.VARCHAR,  bean.getProperty("des_escritura")) );
        args.add( DAOModel.createParam("pstnum_documentum_instr", OracleTypes.VARCHAR,  bean.getProperty("num_documentum_instr")) );
        args.add( DAOModel.createParam("pstfec_otorgamiento_instr", OracleTypes.VARCHAR,  bean.getProperty("fec_otorgamiento_instr")) );
        args.add( DAOModel.createParam("pstnum_licenciado", OracleTypes.VARCHAR,  bean.getProperty("num_licenciado")) );
        args.add( DAOModel.createParam("pstdes_suplencia_asociado", OracleTypes.VARCHAR,  bean.getProperty("des_suplencia_asociado")) );
        args.add( DAOModel.createParam("pstfec_registro", OracleTypes.VARCHAR,  bean.getProperty("fec_registro")) );
        args.add( DAOModel.createParam("pstnum_folio_merc", OracleTypes.VARCHAR,  bean.getProperty("num_folio_merc")) );
        args.add( DAOModel.createParam("pstdes_otros_datos_registro", OracleTypes.VARCHAR,  bean.getProperty("des_otros_datos_registro")) );
        args.add( DAOModel.createParam("pstind_status_esc", OracleTypes.VARCHAR,  bean.getProperty("ind_status_esc")) );
        args.add( DAOModel.createParam("pstind_status_rppc", OracleTypes.VARCHAR,  bean.getProperty("ind_status_rppc")) );        
        String desc_apod = bean.getProperty("desc_apoderados").toString();
        int lenght = desc_apod.length();
        args.add( DAOModel.createParam("pstdesc_apoderados", OracleTypes.CLOB,  desc_apod) );
        //args.add( DAOModel.createParam("pstdesc_apoderados", OracleTypes.VARCHAR,  bean.getProperty("desc_apoderados")) );
        String desc_asunto = bean.getProperty("desc_asunto").toString();
        int lenghtdesc_asunto = desc_asunto.length();
        desc_asunto = desc_asunto.length() > 2000 ? desc_asunto.substring(0, 1990)+"..." : desc_asunto;
        lenghtdesc_asunto = desc_asunto.length();
        args.add( DAOModel.createParam("pstdesc_asunto", OracleTypes.VARCHAR,  desc_asunto) );
        //args.add( DAOModel.createParam("pstdesc_asunto", OracleTypes.VARCHAR,  bean.getProperty("desc_asunto")) );
        args.add( DAOModel.createParam("pstdes_revoca", OracleTypes.VARCHAR,  bean.getProperty("des_revoca")) );
        args.add( DAOModel.createParam("pinind_ok", OracleTypes.NUMBER,  bean.getProperty("ind_ok")) );
        args.add( DAOModel.createParam("pstfec_pe", OracleTypes.VARCHAR,  bean.getProperty("fec_pe")) );
        args.add( DAOModel.createParam("pinind_status_ac", OracleTypes.NUMBER,  bean.getProperty("ind_status_ac")) );
        args.add( DAOModel.createParam("pinid_red_resp", OracleTypes.NUMBER,  bean.getProperty("id_red_resp")) );
        args.add( DAOModel.createParam("pstdes_rep_resp", OracleTypes.VARCHAR,  bean.getProperty("des_rep_resp")) );
        args.add( DAOModel.createParam("pstfec_rep", OracleTypes.VARCHAR,  bean.getProperty("fec_rep")) );
        args.add( DAOModel.createParam("pinid_reg_resp", OracleTypes.NUMBER,  bean.getProperty("id_reg_resp")) );
        args.add( DAOModel.createParam("pstdes_reg_resp", OracleTypes.VARCHAR,  bean.getProperty("des_reg_resp")) );
        args.add( DAOModel.createParam("pstfec_reg", OracleTypes.VARCHAR,  bean.getProperty("fec_reg")) );
        args.add( DAOModel.createParam("pinid_cor_resp", OracleTypes.NUMBER,  bean.getProperty("id_cor_resp")) );
        args.add( DAOModel.createParam("pstdes_cor_resp", OracleTypes.VARCHAR,  bean.getProperty("des_cor_resp")) );
        args.add( DAOModel.createParam("pstfec_cor", OracleTypes.VARCHAR,  bean.getProperty("fec_cor")) );
        args.add( DAOModel.createParam("pinid_aut_resp", OracleTypes.NUMBER,  bean.getProperty("id_aut_resp")) );
        args.add( DAOModel.createParam("pstdes_aut_resp", OracleTypes.VARCHAR,  bean.getProperty("des_aut_resp")) );
        args.add( DAOModel.createParam("pstfec_aut", OracleTypes.VARCHAR,  bean.getProperty("fec_aut")) );
        args.add( DAOModel.createParam("pinid_fir_resp", OracleTypes.NUMBER,  bean.getProperty("id_fir_resp")) );
        args.add( DAOModel.createParam("pstdes_fir_resp", OracleTypes.VARCHAR,  bean.getProperty("des_fir_resp")) );
        args.add( DAOModel.createParam("pstfec_fir", OracleTypes.VARCHAR,  bean.getProperty("fec_fir")) );
        args.add( DAOModel.createParam("pinid_ent_resp", OracleTypes.NUMBER,  bean.getProperty("id_ent_resp")) );
        args.add( DAOModel.createParam("pstdes_ent_resp", OracleTypes.VARCHAR,  bean.getProperty("des_ent_resp")) );
        args.add( DAOModel.createParam("pstfec_ent", OracleTypes.VARCHAR,  bean.getProperty("fec_ent")) );
        args.add( DAOModel.createParam("pinid_sol_doc", OracleTypes.NUMBER,  bean.getProperty("id_sol_doc")) );
        args.add( DAOModel.createParam("pinid_sol_resp", OracleTypes.NUMBER,  bean.getProperty("id_sol_resp")) );
        args.add( DAOModel.createParam("pstdes_sol_resp", OracleTypes.VARCHAR,  bean.getProperty("des_sol_resp")) );
        args.add( DAOModel.createParam("pstfec_sol", OracleTypes.VARCHAR,  bean.getProperty("fec_sol")) );
        args.add( DAOModel.createParam("pstfec_sol_rec", OracleTypes.VARCHAR,  bean.getProperty("fec_sol_rec")) );
        args.add( DAOModel.createParam("pstdes_sol_folio", OracleTypes.VARCHAR,  bean.getProperty("des_sol_folio")) );
        args.add( DAOModel.createParam("pinid_ent_doc", OracleTypes.NUMBER,  bean.getProperty("id_ent_doc")) );
        args.add( DAOModel.createParam("pstfec_ent_doc", OracleTypes.VARCHAR,  bean.getProperty("fec_ent_doc")) );
        args.add( DAOModel.createParam("pstfec_ent_rec", OracleTypes.VARCHAR,  bean.getProperty("fec_ent_rec")) );
        args.add( DAOModel.createParam("pinnum_insc_regpub", OracleTypes.VARCHAR,  bean.getProperty("num_insc_regpub")) );
        args.add( DAOModel.createParam("pstdes_insc_regpub", OracleTypes.VARCHAR,  bean.getProperty("des_insc_regpub")) );
        args.add( DAOModel.createParam("pstdes_caracteristicas", OracleTypes.VARCHAR,  bean.getProperty("des_caracteristicas")) );
        args.add( DAOModel.createParam("pinind_aplica_status", OracleTypes.VARCHAR,  bean.getProperty("ind_aplica_status")) );
        
        args.add( DAOModel.createParam("pstOuterror",OracleTypes.VARCHAR));
        
        List<Object> results = DAOModel.executeSp(PKG,"UPDATE_ESCRITURA_PODER_PR", args);
        
        results = results;
    }
	
	public static int deleteESCRITURA_PODER(int idEscrituraPoder, int idUsuarioActual) throws Exception
    {
        List<SqlParameter> args = new ArrayList<SqlParameter>();
        args.add( DAOModel.createParam("pstResultado", OracleTypes.VARCHAR) );        
        args.add( DAOModel.createParam("pinid_ep_pk", OracleTypes.NUMBER, idEscrituraPoder) );
        args.add( DAOModel.createParam("psinnum_last_updated_by", OracleTypes.NUMBER, idUsuarioActual));
         
        return (DAOModel.executeSp(PKG, "DELETE_ESCRITURA_PODER_PR", args)==null) ? 1:0;
    }
	
	public static int deleteOTORGA_PODER(int idEscrituraPoder, int idUsuarioActual) throws Exception
    {
        List<SqlParameter> args = new ArrayList<SqlParameter>();
        args.add( DAOModel.createParam("pstResultado", OracleTypes.VARCHAR) );        
        args.add( DAOModel.createParam("pinid_ep_fk", OracleTypes.NUMBER, idEscrituraPoder) );
        args.add( DAOModel.createParam("psinnum_last_updated_by", OracleTypes.NUMBER, idUsuarioActual));
                      
        return (DAOModel.executeSp(PKG, "DELETE_OTORGAPODER_EP_PR", args)==null) ? 1:0;
    }
	
	public static int deleteAPODERADO(int idEscrituraPoder, int idUsuarioActual) throws Exception
    {
        List<SqlParameter> args = new ArrayList<SqlParameter>();
        args.add( DAOModel.createParam("pinid_ep_fk", OracleTypes.NUMBER, idEscrituraPoder) );
        args.add( DAOModel.createParam("psinnum_last_updated_by", OracleTypes.NUMBER, idUsuarioActual));
        args.add( DAOModel.createParam("pstOuterror", OracleTypes.VARCHAR) );                        
                      
        return (DAOModel.executeSp(PKG, "DELETE_APODERADO_EP_PR", args)==null) ? 1:0;
    }
		
	public static int insertDOCUMENTUMS(GenericBean bean) throws Exception
    {
        List<SqlParameter> args = new ArrayList<SqlParameter>();        
        args.add( DAOModel.createParam("pinid_ep_fk", OracleTypes.NUMBER, bean.getProperty("id_ep_fk")) );
        args.add( DAOModel.createParam("pstdesc_title", OracleTypes.VARCHAR, bean.getProperty("desc_title")) );
        args.add( DAOModel.createParam("pstid_documentcve", OracleTypes.VARCHAR, bean.getProperty("id_documentcve")) );
        args.add( DAOModel.createParam("pstfec_rec", OracleTypes.VARCHAR, bean.getProperty("fec_rec")) );
        args.add( DAOModel.createParam("pstfec_ent", OracleTypes.VARCHAR, bean.getProperty("fec_ent")) );
        args.add( DAOModel.createParam("pinID_EP", OracleTypes.INTEGER));
        args.add( DAOModel.createParam("pstOuterror",OracleTypes.VARCHAR));
        
        List<Object> results = DAOModel.executeSp(PKG,"INSERT_DOCUMENTUMS_EP_PR", args);
        
        return (int)results.get(0);
    }
	
	public static GenericDataBean queryDOCUMENTUMS(int idEscritura) throws Exception
    {
        List<SqlParameter> args = new ArrayList<SqlParameter>();
        args.add( DAOModel.createParam("porcRSResultado", OracleTypes.CURSOR) );
        args.add( DAOModel.createParam("pinid_ep_fk", OracleTypes.NUMBER, idEscritura) );
       
        
        return DAOModel.executeSpQueryGDB(PKG,"QUERY_DOCUMENTUMS_EP_PR", args);
    }
	
	public static GenericDataBean execCHANGE_STATUS_VIGENCIA() throws Exception
    {
           
        return DAOModel.executeSpQueryGDB("PENDIUM_REV_AUT_POD_PKG","CHANGE_STATUS_VIGENCIA_PR", null);
    }
	
	public static int deleteDOCUMENTUMS(int idEscrituraPoder, int idUsuarioActual) throws Exception
    {
        List<SqlParameter> args = new ArrayList<SqlParameter>();
        args.add( DAOModel.createParam("pinid_ep_fk", OracleTypes.NUMBER, idEscrituraPoder) );
        args.add( DAOModel.createParam("psinnum_last_updated_by", OracleTypes.NUMBER, idUsuarioActual));
        args.add( DAOModel.createParam("pstOuterror", OracleTypes.VARCHAR) );                        
                      
        return (DAOModel.executeSp(PKG, "DELETE_DOCUMENTUMS_EP_PR", args)==null) ? 1:0;
    }
	
	public static int insertOTORGA_PODER(GenericBean bean) throws Exception
    {
        List<SqlParameter> args = new ArrayList<SqlParameter>();        
        args.add( DAOModel.createParam("pinId_EP_FK", OracleTypes.NUMBER, bean.getProperty("id_ep_fk")) );
        args.add( DAOModel.createParam("pinnum_PoderTipo", OracleTypes.NUMBER, bean.getProperty("num_podertipo")) );
        args.add( DAOModel.createParam("pstdes_PoderTipo", OracleTypes.VARCHAR, bean.getProperty("des_podertipo")) );
        args.add( DAOModel.createParam("pinnum_VigenciaTipo", OracleTypes.NUMBER, bean.getProperty("num_vigenciatipo")) );
        args.add( DAOModel.createParam("pstdes_VigenciaTipo", OracleTypes.VARCHAR, bean.getProperty("des_vigenciatipo")) );
        args.add( DAOModel.createParam("pinnum_VigenciaTiempo", OracleTypes.NUMBER, bean.getProperty("num_vigenciatiempo")) );
        args.add( DAOModel.createParam("pstfec_VigenciaInicio", OracleTypes.VARCHAR, bean.getProperty("fec_vigenciainicio")) );
        args.add( DAOModel.createParam("pstfec_VigenciaFin", OracleTypes.VARCHAR, bean.getProperty("fec_vigenciafin")) );
        args.add( DAOModel.createParam("pstdesc_Caracteristicas", OracleTypes.VARCHAR, bean.getProperty("desc_caracteristicas")) );
        args.add( DAOModel.createParam("pstdesc_descripcion", OracleTypes.VARCHAR, bean.getProperty("desc_descripcion")) );
        args.add( DAOModel.createParam("pstdesc_apoderados", OracleTypes.CLOB, bean.getProperty("desc_apoderados")) );        
        args.add( DAOModel.createParam("pstdesc_ActosDominio", OracleTypes.CLOB, bean.getProperty("desc_actosdominio")) );
        args.add( DAOModel.createParam("pstdesc_ActosAdmon", OracleTypes.CLOB, bean.getProperty("desc_actosadmon")) );
        args.add( DAOModel.createParam("pstdesc_tituloscredito", OracleTypes.CLOB, bean.getProperty("desc_tituloscredito")) );
        args.add( DAOModel.createParam("pstdesc_PleitosCobranza", OracleTypes.CLOB, bean.getProperty("desc_pleitoscobranza")) );
        args.add( DAOModel.createParam("pstdesc_Revocados", OracleTypes.VARCHAR, bean.getProperty("desc_revocados")) );
        args.add( DAOModel.createParam("pinnum_order", OracleTypes.VARCHAR, bean.getProperty("num_order")) );
        args.add( DAOModel.createParam("pstdesc_vigencia", OracleTypes.VARCHAR, bean.getProperty("desc_vigencia")) );
        args.add( DAOModel.createParam("pstdes_poder", OracleTypes.CLOB,  bean.getProperty("des_poder")) ); 
        
        args.add( DAOModel.createParam("pinid_opoder_ep_pk", OracleTypes.INTEGER));
        args.add( DAOModel.createParam("pstOuterror",OracleTypes.VARCHAR));
        
        List<Object> results = DAOModel.executeSp(PKG,"INSERT_OTORGAPODER_EP_PR", args);
        
        return (int)results.get(0);
    }
	
	public static GenericDataBean queryOTORGA_PODER(int idEscritura) throws Exception
    {
        List<SqlParameter> args = new ArrayList<SqlParameter>();
        args.add( DAOModel.createParam("porcRSResultado", OracleTypes.CURSOR) );
        args.add( DAOModel.createParam("pinid_ep_fk", OracleTypes.NUMBER, idEscritura) );
                      
        return DAOModel.executeSpQueryGDB(PKG,"QUERY_OTORGAPODER_EP_PR", args);
    }
		
	public static int insertAPODERADO(GenericBean bean) throws Exception
    {
        List<SqlParameter> args = new ArrayList<SqlParameter>();        
        args.add( DAOModel.createParam("pinid_opoder_ep_fk", OracleTypes.NUMBER, bean.getProperty("id_opoder_ep_fk")) );
        args.add( DAOModel.createParam("pinid_ep_fk", OracleTypes.NUMBER, bean.getProperty("id_ep_fk")) );
        args.add( DAOModel.createParam("pinid_empl_fk", OracleTypes.NUMBER, bean.getProperty("id_empl_fk")) );
        args.add( DAOModel.createParam("pstdesc_nom_empl", OracleTypes.VARCHAR, bean.getProperty("desc_nom_empl")) );
        args.add( DAOModel.createParam("pinind_tipoapoderado", OracleTypes.NUMBER, bean.getProperty("ind_tipoapoderado")) );
        args.add( DAOModel.createParam("pstdesc_tipoapoderado", OracleTypes.VARCHAR, bean.getProperty("desc_tipoapoderado")) );        
        args.add( DAOModel.createParam("pinnum_created_by", OracleTypes.NUMBER, bean.getProperty("num_created_by")) );
        args.add( DAOModel.createParam("pstdes_grupo", OracleTypes.VARCHAR, bean.getProperty("des_grupo")) );        
        args.add( DAOModel.createParam("pinid_grupo_fk", OracleTypes.NUMBER, bean.getProperty("id_grupo_fk")) );
        args.add( DAOModel.createParam("pstind_aprevoca", OracleTypes.VARCHAR, bean.getProperty("ind_aprevoca")) );
        args.add( DAOModel.createParam("pstdesc_revoca", OracleTypes.VARCHAR, bean.getProperty("desc_revoca")) );        
        args.add( DAOModel.createParam("pinind_status", OracleTypes.NUMBER, bean.getProperty("ind_status")) );
                
        args.add( DAOModel.createParam("pinid_apod_ep", OracleTypes.INTEGER));
        args.add( DAOModel.createParam("pstOuterror",OracleTypes.VARCHAR));
               
        List<Object> results = DAOModel.executeSp(PKG,"INSERT_APODERADO_EP_PR", args);
        
        return (int)results.get(0);
    }
	
	public static int insertREVOCA(GenericBean bean) throws Exception
    {
        List<SqlParameter> args = new ArrayList<SqlParameter>();        
        args.add( DAOModel.createParam("pinid_opoder_ep_fk", OracleTypes.NUMBER, bean.getProperty("id_opoder_ep_fk")) );
        args.add( DAOModel.createParam("pinid_ep_fk", OracleTypes.NUMBER, bean.getProperty("id_ep_fk")) );
        args.add( DAOModel.createParam("pinid_apod_ep_fk", OracleTypes.NUMBER, bean.getProperty("id_apod_ep_fk")) );
        args.add( DAOModel.createParam("pinind_razonrevoca", OracleTypes.NUMBER, bean.getProperty("ind_razonrevoca")) );
        args.add( DAOModel.createParam("pstdes_razonrevoca", OracleTypes.VARCHAR, bean.getProperty("des_razonrevoca")) );
        args.add( DAOModel.createParam("pinid_escriturarevoca_fk", OracleTypes.VARCHAR, bean.getProperty("id_escriturarevoca_fk")) );        
        args.add( DAOModel.createParam("pinid_documentumrevoca", OracleTypes.VARCHAR, bean.getProperty("id_documentumrevoca")) );
        args.add( DAOModel.createParam("pstfec_revoca", OracleTypes.VARCHAR, bean.getProperty("fec_revoca")) );        
        args.add( DAOModel.createParam("pstdes_textorevoca", OracleTypes.VARCHAR, bean.getProperty("des_textorevoca")) );
        args.add( DAOModel.createParam("pstdesc_apendicerevoca", OracleTypes.VARCHAR, bean.getProperty("desc_apendicerevoca")) );
               
        args.add( DAOModel.createParam("pinid_revoca_ep", OracleTypes.INTEGER));
        args.add( DAOModel.createParam("pstOuterror",OracleTypes.VARCHAR));
               
        List<Object> results = DAOModel.executeSp(PKG,"INSERT_REVOCA_EP_PR", args);
        
        return (int)results.get(0);
    }
	
	public static GenericDataBean queryREVOCA(int idEscritura) throws Exception
    {
        List<SqlParameter> args = new ArrayList<SqlParameter>();
        args.add( DAOModel.createParam("porcRSResultado", OracleTypes.CURSOR) );
        args.add( DAOModel.createParam("pinid_ep", OracleTypes.NUMBER, idEscritura) );
       
        
        return DAOModel.executeSpQueryGDB(PKG,"QUERY_REVOCA_EP_PR", args);
    }
	
	public static GenericDataBean queryAPODERADO(int idpoder) throws Exception
    {
        List<SqlParameter> args = new ArrayList<SqlParameter>();
        args.add( DAOModel.createParam("porcRSResultado", OracleTypes.CURSOR) );
        args.add( DAOModel.createParam("pinid_opoder_ep", OracleTypes.NUMBER, idpoder) );
       
        
        return DAOModel.executeSpQueryGDB(PKG,"QUERY_APODERADO_EP_PR", args);
    }
	
	public static GenericDataBean queryAPODERADO(int idpoder, int sonMancomunados) throws Exception
    {
        List<SqlParameter> args = new ArrayList<SqlParameter>();
        args.add( DAOModel.createParam("porcRSResultado", OracleTypes.CURSOR) );
        args.add( DAOModel.createParam("pinind_sonmancomunados", OracleTypes.NUMBER, sonMancomunados) );
        args.add( DAOModel.createParam("pinid_opoder_ep", OracleTypes.NUMBER, idpoder) );
       
        
        return DAOModel.executeSpQueryGDB(PKG,"QUERY_APODERADO_EP_PR", args);
    }
	
	public static GenericDataBean queryFACULTADES(int idpoder) throws Exception
    {
        List<SqlParameter> args = new ArrayList<SqlParameter>();
        args.add( DAOModel.createParam("porcRSResultado", OracleTypes.CURSOR) );
        args.add( DAOModel.createParam("pinid_opoder_ep", OracleTypes.NUMBER, idpoder) );
       
        
        return DAOModel.executeSpQueryGDB(PKG,"QUERY_FACULTADES_EP_PR", args);
    }
	
	public static int insertFACULTADES(GenericBean bean) throws Exception
    {
        List<SqlParameter> args = new ArrayList<SqlParameter>();        
        args.add( DAOModel.createParam("pinid_opoder_ep_fk", OracleTypes.NUMBER, bean.getProperty("id_opoder_ep_fk")) );
        args.add( DAOModel.createParam("pinid_ep_fk", OracleTypes.NUMBER, bean.getProperty("id_ep_fk")) );
        args.add( DAOModel.createParam("pinind_tipo", OracleTypes.NUMBER, bean.getProperty("ind_tipo")) );
        args.add( DAOModel.createParam("pstdes_tipo", OracleTypes.VARCHAR, bean.getProperty("des_tipo")) );
        args.add( DAOModel.createParam("pinind_delegable", OracleTypes.VARCHAR, bean.getProperty("ind_delegable")) );
        args.add( DAOModel.createParam("pinind_individual", OracleTypes.VARCHAR, bean.getProperty("ind_individual")) );        
        args.add( DAOModel.createParam("pstCaracteristicas", OracleTypes.VARCHAR, bean.getProperty("caracteristicas")) );
        
        args.add( DAOModel.createParam("pinMancomunado", OracleTypes.VARCHAR, bean.getProperty("mancomunado")) );        
        args.add( DAOModel.createParam("pstdes_formae", OracleTypes.VARCHAR, bean.getProperty("des_formae")) );
                
        
        args.add( DAOModel.createParam("id_fac_ep_pk", OracleTypes.INTEGER));
        args.add( DAOModel.createParam("pstOuterror",OracleTypes.VARCHAR));
               
        List<Object> results = DAOModel.executeSp(PKG,"INSERT_FACULTADES_EP_PR", args);
        
        return (int)results.get(0);
    }
	
	public static void insertCatalogoPoder(GenericBean bean) throws Exception
    {
        List<SqlParameter> args = new ArrayList<SqlParameter>();        
        args.add( DAOModel.createParam("pstdes_podertipo", OracleTypes.VARCHAR, bean.getProperty("des_podertipo")) );      
        args.add( DAOModel.createParam("pstind_podertipo", OracleTypes.VARCHAR, bean.getProperty("ind_podertipo")) );
        args.add( DAOModel.createParam("pstdes_descripcion", OracleTypes.VARCHAR, bean.getProperty("des_descripcion")) );
        args.add( DAOModel.createParam("pinind_tiene_ad", OracleTypes.NUMBER, bean.getProperty("ind_tiene_ad")) );
        args.add( DAOModel.createParam("pstind_ad_delegable", OracleTypes.VARCHAR, bean.getProperty("ind_ad_delegable")) );        
        args.add( DAOModel.createParam("pstind_ad_individual", OracleTypes.VARCHAR, bean.getProperty("ind_ad_individual")) );
        args.add( DAOModel.createParam("pstdes_ad_formaejercerlo", OracleTypes.VARCHAR, bean.getProperty("des_ad_formaejercerlo")) );        
        args.add( DAOModel.createParam("pstdes_ad_caracteristicas", OracleTypes.VARCHAR, bean.getProperty("des_ad_caracteristicas")) );
        args.add( DAOModel.createParam("pstdes_actosdominio", OracleTypes.VARCHAR, bean.getProperty("des_actosdominio")) );
        args.add( DAOModel.createParam("pinind_tiene_aa", OracleTypes.NUMBER, bean.getProperty("ind_tiene_aa")) );
        args.add( DAOModel.createParam("pstind_aa_delegable", OracleTypes.VARCHAR, bean.getProperty("ind_aa_delegable")) );
        args.add( DAOModel.createParam("pstind_aa_individual", OracleTypes.VARCHAR, bean.getProperty("ind_aa_individual")) );
        args.add( DAOModel.createParam("pstdes_aa_formaejercerlo", OracleTypes.VARCHAR, bean.getProperty("des_aa_formaejercerlo")) );
        args.add( DAOModel.createParam("pstdes_aa_caracteristicas", OracleTypes.VARCHAR, bean.getProperty("des_aa_caracteristicas")) );
        args.add( DAOModel.createParam("pstdes_actosadmon", OracleTypes.VARCHAR, bean.getProperty("des_actosadmon")) );
        args.add( DAOModel.createParam("pinind_tiene_tc", OracleTypes.NUMBER, bean.getProperty("ind_tiene_tc")) );
        args.add( DAOModel.createParam("pstind_tc_delegable", OracleTypes.VARCHAR, bean.getProperty("ind_tc_delegable")) );
        args.add( DAOModel.createParam("pstind_tc_individual", OracleTypes.VARCHAR, bean.getProperty("ind_tc_individual")) );
        args.add( DAOModel.createParam("pstdes_tc_formaejercerlo", OracleTypes.VARCHAR, bean.getProperty("des_tc_formaejercerlo")) );
        args.add( DAOModel.createParam("pstdes_tc_caracteristicas", OracleTypes.VARCHAR, bean.getProperty("des_tc_caracteristicas")) );
        args.add( DAOModel.createParam("pstdes_titulosdecreditos", OracleTypes.VARCHAR, bean.getProperty("des_titulosdecreditos")) );
        args.add( DAOModel.createParam("pinind_tiene_pc", OracleTypes.NUMBER, bean.getProperty("ind_tiene_pc")) );
        args.add( DAOModel.createParam("pstind_pc_delegable", OracleTypes.VARCHAR, bean.getProperty("ind_pc_delegable")) );
        args.add( DAOModel.createParam("pstind_pc_individual", OracleTypes.VARCHAR, bean.getProperty("ind_pc_individual")) );
        args.add( DAOModel.createParam("pstdes_pc_formaejercerlo", OracleTypes.VARCHAR, bean.getProperty("des_pc_formaejercerlo")) );
        args.add( DAOModel.createParam("pstdes_pc_caracteristicas", OracleTypes.VARCHAR, bean.getProperty("des_pc_caracteristicas")) );
        args.add( DAOModel.createParam("pstdes_pleitoscobranzas", OracleTypes.VARCHAR, bean.getProperty("des_pleitoscobranzas")) );
        args.add( DAOModel.createParam("pstind_pe_delegable", OracleTypes.VARCHAR, bean.getProperty("ind_pe_delegable")) );
        args.add( DAOModel.createParam("pstind_pe_individual", OracleTypes.VARCHAR, bean.getProperty("ind_pe_individual")) );
        args.add( DAOModel.createParam("pstdes_pe_formaejercerlo", OracleTypes.VARCHAR, bean.getProperty("des_pe_formaejercerlo")) );
        args.add( DAOModel.createParam("pstdes_pe_caracteristicas", OracleTypes.VARCHAR, bean.getProperty("des_pe_caracteristicas")) );
        args.add( DAOModel.createParam("pstdes_facultades", OracleTypes.VARCHAR, bean.getProperty("des_facultades")) );
        args.add( DAOModel.createParam("pinnum_created_by", OracleTypes.NUMBER, bean.getProperty("num_created_by")) );
                
        args.add( DAOModel.createParam("pinid_poder_pk", OracleTypes.NUMBER));        
        
        args.add( DAOModel.createParam("pinid_catalogo", OracleTypes.NUMBER, bean.getProperty("id_catalogo")));
        
        args.add( DAOModel.createParam("pstOuterror",OracleTypes.VARCHAR));
               
        DAOModel.executeSp(PKG,"INSERT_CATALOGO_PODERES_PR", args);
                
    }

	public static void updateCatalogoPoder(GenericBean bean) throws Exception
    {
        List<SqlParameter> args = new ArrayList<SqlParameter>();        
        args.add( DAOModel.createParam("pinid_poder_pk", OracleTypes.NUMBER,  bean.getProperty("id_poder_pk")) );
        args.add( DAOModel.createParam("pstdes_podertipo", OracleTypes.VARCHAR, bean.getProperty("des_podertipo")) );        
        args.add( DAOModel.createParam("pstind_podertipo", OracleTypes.VARCHAR, bean.getProperty("ind_podertipo")) );
        args.add( DAOModel.createParam("pstdes_descripcion", OracleTypes.VARCHAR, bean.getProperty("des_descripcion")) );
        args.add( DAOModel.createParam("pinind_tiene_ad", OracleTypes.NUMBER, bean.getProperty("ind_tiene_ad")) );
        args.add( DAOModel.createParam("pstind_ad_delegable", OracleTypes.VARCHAR, bean.getProperty("ind_ad_delegable")) );        
        args.add( DAOModel.createParam("pstind_ad_individual", OracleTypes.VARCHAR, bean.getProperty("ind_ad_individual")) );
        args.add( DAOModel.createParam("pstdes_ad_formaejercerlo", OracleTypes.VARCHAR, bean.getProperty("des_ad_formaejercerlo")) );        
        args.add( DAOModel.createParam("pstdes_ad_caracteristicas", OracleTypes.VARCHAR, bean.getProperty("des_ad_caracteristicas")) );
        args.add( DAOModel.createParam("pstdes_actosdominio", OracleTypes.VARCHAR, bean.getProperty("des_actosdominio")) );
        args.add( DAOModel.createParam("pinind_tiene_aa", OracleTypes.NUMBER, bean.getProperty("ind_tiene_aa")) );
        args.add( DAOModel.createParam("pstind_aa_delegable", OracleTypes.VARCHAR, bean.getProperty("ind_aa_delegable")) );
        args.add( DAOModel.createParam("pstind_aa_individual", OracleTypes.VARCHAR, bean.getProperty("ind_aa_individual")) );
        args.add( DAOModel.createParam("pstdes_aa_formaejercerlo", OracleTypes.VARCHAR, bean.getProperty("des_aa_formaejercerlo")) );
        args.add( DAOModel.createParam("pstdes_aa_caracteristicas", OracleTypes.VARCHAR, bean.getProperty("des_aa_caracteristicas")) );
        args.add( DAOModel.createParam("pstdes_actosadmon", OracleTypes.VARCHAR, bean.getProperty("des_actosadmon")) );
        args.add( DAOModel.createParam("pinind_tiene_tc", OracleTypes.NUMBER, bean.getProperty("ind_tiene_tc")) );
        args.add( DAOModel.createParam("pstind_tc_delegable", OracleTypes.VARCHAR, bean.getProperty("ind_tc_delegable")) );
        args.add( DAOModel.createParam("pstind_tc_individual", OracleTypes.VARCHAR, bean.getProperty("ind_tc_individual")) );
        args.add( DAOModel.createParam("pstdes_tc_formaejercerlo", OracleTypes.VARCHAR, bean.getProperty("des_tc_formaejercerlo")) );
        args.add( DAOModel.createParam("pstdes_tc_caracteristicas", OracleTypes.VARCHAR, bean.getProperty("des_tc_caracteristicas")) );
        args.add( DAOModel.createParam("pstdes_titulosdecreditos", OracleTypes.VARCHAR, bean.getProperty("des_titulosdecreditos")) );
        args.add( DAOModel.createParam("pinind_tiene_pc", OracleTypes.NUMBER, bean.getProperty("ind_tiene_pc")) );
        args.add( DAOModel.createParam("pstind_pc_delegable", OracleTypes.VARCHAR, bean.getProperty("ind_pc_delegable")) );
        args.add( DAOModel.createParam("pstind_pc_individual", OracleTypes.VARCHAR, bean.getProperty("ind_pc_individual")) );
        args.add( DAOModel.createParam("pstdes_pc_formaejercerlo", OracleTypes.VARCHAR, bean.getProperty("des_pc_formaejercerlo")) );
        args.add( DAOModel.createParam("pstdes_pc_caracteristicas", OracleTypes.VARCHAR, bean.getProperty("des_pc_caracteristicas")) );
        args.add( DAOModel.createParam("pstdes_pleitoscobranzas", OracleTypes.VARCHAR, bean.getProperty("des_pleitoscobranzas")) );
        args.add( DAOModel.createParam("pstind_pe_delegable", OracleTypes.VARCHAR, bean.getProperty("ind_pe_delegable")) );
        args.add( DAOModel.createParam("pstind_pe_individual", OracleTypes.VARCHAR, bean.getProperty("ind_pe_individual")) );
        args.add( DAOModel.createParam("pstdes_pe_formaejercerlo", OracleTypes.VARCHAR, bean.getProperty("des_pe_formaejercerlo")) );
        args.add( DAOModel.createParam("pstdes_pe_caracteristicas", OracleTypes.VARCHAR, bean.getProperty("des_pe_caracteristicas")) );
        args.add( DAOModel.createParam("pstdes_facultades", OracleTypes.VARCHAR, bean.getProperty("des_facultades")) );
        args.add( DAOModel.createParam("pinnum_last_updated_by", OracleTypes.NUMBER, bean.getProperty("num_last_updated_by")) );
                
        args.add( DAOModel.createParam("pstOuterror",OracleTypes.VARCHAR));
               
        DAOModel.executeSp(PKG,"UPDATE_CATALOGO_PODERES_PR", args);
                
    }

	public static boolean deleteCatalogoPoder(int idCatalogoPoder, int idUsuarioActual) throws Exception
    {
        List<SqlParameter> args = new ArrayList<SqlParameter>();        
        
        args.add( DAOModel.createParam("pinid_poder_pk", OracleTypes.NUMBER, idCatalogoPoder) );
        args.add( DAOModel.createParam("pinnum_last_updated_by", OracleTypes.NUMBER, idUsuarioActual) );                
        args.add( DAOModel.createParam("pstOuterror",OracleTypes.VARCHAR));
                               
        return (((DAOModel.executeSp(PKG, "DELETE_CATALOGO_PODERES_PR", args).get(0))==null) ? true:false);
    }
	
	public static GenericDataBean queryCatalogoPoder(double idCatalogo) throws Exception
    {
        List<SqlParameter> args = new ArrayList<SqlParameter>();        
        
        args.add( DAOModel.createParam("porcRSResultado", OracleTypes.CURSOR) );
        
        args.add( DAOModel.createParam("pinnum_podertipo", OracleTypes.NUMBER, idCatalogo) );                      
               
        return DAOModel.executeSpQueryGDB(PKG,"QUERY_CATALOGO_PODERES_PR", args);
    }
	
	public static GenericDataBean queryCatalogosDePoderes(String tipoCatalogo) throws Exception
    {
        List<SqlParameter> args = new ArrayList<SqlParameter>();        
        
        args.add( DAOModel.createParam("porcRSResultado", OracleTypes.CURSOR) );
        
        args.add( DAOModel.createParam("pstind_podertipo", OracleTypes.VARCHAR, tipoCatalogo) );                  
               
        return DAOModel.executeSpQueryGDB(PKG,"QUERY_CATALOGO_PODERES_PR", args);
    }
	
	public static GenericDataBean queryCatalogosDePoderes(String tipoCatalogo, String nombreCatalogo) throws Exception
    {
        List<SqlParameter> args = new ArrayList<SqlParameter>();        
        
        args.add( DAOModel.createParam("porcRSResultado", OracleTypes.CURSOR) );
        
        args.add( DAOModel.createParam("pstind_podertipo", OracleTypes.VARCHAR, tipoCatalogo) ); 
        
        args.add( DAOModel.createParam("pstdes_podertipo", OracleTypes.VARCHAR, nombreCatalogo) ); 
               
        return DAOModel.executeSpQueryGDB(PKG,"QUERY_CATALOGO_PODERES_PR", args);
    }
	
	public static GenericDataBean queryCONSULTA_ESC_PODER_PR(int idEmpresa, String paramDeBusqueda) throws Exception
    {
        List<SqlParameter> args = new ArrayList<SqlParameter>();
        args.add( DAOModel.createParam("porcRSResultado", OracleTypes.CURSOR) );
        args.add( DAOModel.createParam("pinid_empresa", OracleTypes.NUMBER, idEmpresa) );        
        args.add( DAOModel.createParam("pstdesc_busqueda", OracleTypes.VARCHAR, paramDeBusqueda));
                      
        return DAOModel.executeSpQueryGDB(PKG_CONSULTA, "CONSULTA_ESC_PODER_PR", args);
    }
	
	public static GenericDataBean process_PODERES_GENERALES_PR(int idEmpresa) throws Exception
    {
        List<SqlParameter> args = new ArrayList<SqlParameter>();
        args.add( DAOModel.createParam("porcRSResultado", OracleTypes.CURSOR) );        
        args.add( DAOModel.createParam("pinid_empresa", OracleTypes.NUMBER, idEmpresa) );                                
        return DAOModel.executeSpQueryGDB(PKG_CONSULTA, "CONSULTA_ESC_PODER_PR", args);
    }
	
	public static GenericDataBean query_PODERES_GENERALES_PR(int idEmpresa, String paramDeBusqueda) throws Exception
    {
        List<SqlParameter> args = new ArrayList<SqlParameter>();
        args.add( DAOModel.createParam("porcRSResultado", OracleTypes.CURSOR) );
        args.add( DAOModel.createParam("pinid_empresa", OracleTypes.NUMBER, idEmpresa) );
        args.add( DAOModel.createParam("pstdesc_busqueda", OracleTypes.VARCHAR, paramDeBusqueda));                              
        return DAOModel.executeSpQueryGDB(PKG_CONSULTA, "CONSULTA_ESC_PODER_PR", args);
    }
	
	public static GenericDataBean query_PODERES_ESPECIALES_PR(int idEmpresa, String paramDeBusqueda) throws Exception
    {
        List<SqlParameter> args = new ArrayList<SqlParameter>();
        args.add( DAOModel.createParam("porcRSResultado", OracleTypes.CURSOR) );
        args.add( DAOModel.createParam("pstdesc_busqueda", OracleTypes.VARCHAR, paramDeBusqueda));
        args.add( DAOModel.createParam("pinid_empresa", OracleTypes.NUMBER, idEmpresa) );                
        args.add( DAOModel.createParam("pstOuterror",OracleTypes.VARCHAR));        
        return DAOModel.executeSpQueryGDB(PKG_CONSULTA, "QUERY_PODERES_ESPECIALES_PR", args);
    }
	
	public static GenericDataBean query_REVOCACIONES_PR(int idEmpresa, String paramDeBusqueda) throws Exception
    {
        List<SqlParameter> args = new ArrayList<SqlParameter>();
        args.add( DAOModel.createParam("porcRSResultado", OracleTypes.CURSOR) );
        args.add( DAOModel.createParam("pstdesc_busqueda", OracleTypes.VARCHAR, paramDeBusqueda));
        args.add( DAOModel.createParam("pinid_empresa", OracleTypes.NUMBER, idEmpresa) );                
        args.add( DAOModel.createParam("pstOuterror",OracleTypes.VARCHAR));        
        return DAOModel.executeSpQueryGDB(PKG_CONSULTA, "QUERY_REVOCACIONES_PR", args);
    }
	
	public static GenericDataBean query_PODERES_POR_APODERADOS( String pstdes_empresas
			, String pstdes_tipopoder
			, String pstdes_apoderados
			, String pstdes_poder
			, String pstdes_escritura) throws Exception
    {
        List<SqlParameter> args = new ArrayList<SqlParameter>();
        args.add( DAOModel.createParam("porcRSResultado", OracleTypes.CURSOR) );
        args.add( DAOModel.createParam("pstdes_empresas", OracleTypes.VARCHAR, pstdes_empresas));
        args.add( DAOModel.createParam("pstdes_tipopoder", OracleTypes.VARCHAR, pstdes_tipopoder));
        args.add( DAOModel.createParam("pstdes_apoderados", OracleTypes.VARCHAR, pstdes_apoderados));
        args.add( DAOModel.createParam("pstdes_poder", OracleTypes.VARCHAR, pstdes_poder));
        args.add( DAOModel.createParam("pstdes_escritura", OracleTypes.VARCHAR, pstdes_escritura));     
        
        return DAOModel.executeSpQueryGDB(PKG_REPORTES, "QUERY_APODERADOS_PG", args);
    }
	
	public static GenericDataBean query_PODERES_POR_ESCRITURA( String pstdes_empresas
			, String pstdes_tipopoder
			, String pstdes_apoderados
			, String pstdes_poder
			, String pstdes_escritura) throws Exception
    {
        List<SqlParameter> args = new ArrayList<SqlParameter>();
        args.add( DAOModel.createParam("porcRSResultado", OracleTypes.CURSOR) );
        args.add( DAOModel.createParam("pstdes_empresas", OracleTypes.VARCHAR, pstdes_empresas));
        args.add( DAOModel.createParam("pstdes_tipopoder", OracleTypes.VARCHAR, pstdes_tipopoder));
        args.add( DAOModel.createParam("pstdes_apoderados", OracleTypes.VARCHAR, pstdes_apoderados));
        args.add( DAOModel.createParam("pstdes_poder", OracleTypes.VARCHAR, pstdes_poder));
        args.add( DAOModel.createParam("pstdes_escritura", OracleTypes.VARCHAR, pstdes_escritura));     
        
        return DAOModel.executeSpQueryGDB(PKG_REPORTES, "QUERY_PODERES_POR_ESCRITURA_PG", args);
    }
	
	public static GenericDataBean query_PODERES_POR_ASUNTO( String pstdes_empresas
			, String pstdes_tipopoder
			, String pstdes_apoderados
			, String pstdes_grupoapoderados
			, String pstdes_poder
			, String pstdes_escritura) throws Exception
    {
        List<SqlParameter> args = new ArrayList<SqlParameter>();
        args.add( DAOModel.createParam("porcRSResultado", OracleTypes.CURSOR) );
        args.add( DAOModel.createParam("pstdes_empresas", OracleTypes.VARCHAR, pstdes_empresas));
        args.add( DAOModel.createParam("pstdes_tipopoder", OracleTypes.VARCHAR, pstdes_tipopoder));
        args.add( DAOModel.createParam("pstdes_apoderados", OracleTypes.VARCHAR, pstdes_apoderados));
        args.add( DAOModel.createParam("pstdes_grupoapoderados", OracleTypes.VARCHAR, pstdes_grupoapoderados));
        args.add( DAOModel.createParam("pstdes_poder", OracleTypes.VARCHAR, pstdes_poder));
        args.add( DAOModel.createParam("pstdes_escritura", OracleTypes.VARCHAR, pstdes_escritura));     
        
        return DAOModel.executeSpQueryGDB(PKG_REPORTES, "query_PODERES_POR_ASUNTO", args);
    }
	
	public static GenericDataBean query_PODERES_POR_FACULTAD(String pstdes_empresas
			, String pstdes_tipopoder
			, String pstdes_apoderados
			, String pstdes_poder
			, String pstdes_escritura
			, String pstAD
			, String pstAA
			, String pstPC
			, String pstTC) throws Exception{
		List<SqlParameter> args = new ArrayList<SqlParameter>();
		args.add( DAOModel.createParam("porcRSResultado", OracleTypes.CURSOR) );
		args.add( DAOModel.createParam("pstdes_empresas", OracleTypes.VARCHAR, pstdes_empresas));
        args.add( DAOModel.createParam("pstdes_tipopoder", OracleTypes.VARCHAR, pstdes_tipopoder));
        args.add( DAOModel.createParam("pstdes_apoderados", OracleTypes.VARCHAR, pstdes_apoderados));        
        args.add( DAOModel.createParam("pstdes_poder", OracleTypes.VARCHAR, pstdes_poder));
        args.add( DAOModel.createParam("pstdes_escritura", OracleTypes.VARCHAR, pstdes_escritura));  
        args.add( DAOModel.createParam("pstAD", OracleTypes.VARCHAR, pstAD));
        args.add( DAOModel.createParam("pstAA", OracleTypes.VARCHAR, pstAA));
        args.add( DAOModel.createParam("pstPC", OracleTypes.VARCHAR, pstPC));
        args.add( DAOModel.createParam("pstTC", OracleTypes.VARCHAR, pstTC));       
        GenericDataBean result = new GenericDataBean();        
        try {
        	result = DAOModel.executeSpQueryGDB(PKG_REPORTES, "QUERY_PODERES_POR_FACULTAD", args);
		} catch (Exception ex) {		
			logger.error(ex.getMessage());        
		}        
        return result;
	}
	
	public static GenericDataBean query_PODERES_POR_FACULTAD_ESP (String pstdes_empresas
			, String pstdes_tipopoder
			, String pstdes_apoderados
			, String pstdes_poder
			, String pstdes_escritura) throws Exception
    {
        List<SqlParameter> args = new ArrayList<SqlParameter>();
        args.add( DAOModel.createParam("porcRSResultado", OracleTypes.CURSOR) );
        args.add( DAOModel.createParam("pstdes_empresas", OracleTypes.VARCHAR, pstdes_empresas));
        args.add( DAOModel.createParam("pstdes_tipopoder", OracleTypes.VARCHAR, pstdes_tipopoder));
        args.add( DAOModel.createParam("pstdes_apoderados", OracleTypes.VARCHAR, pstdes_apoderados));
        args.add( DAOModel.createParam("pstdes_poder", OracleTypes.VARCHAR, pstdes_poder));
        args.add( DAOModel.createParam("pstdes_escritura", OracleTypes.VARCHAR, pstdes_escritura));     
       
        return DAOModel.executeSpQueryGDB(PKG_REPORTES, "QUERY_PODERES_POR_FACULTAD_ESP", args);
    }
	
	public static GenericDataBean query_REVOCACIONES(int pstin_id_opoder_fk){
		List<SqlParameter> args = new ArrayList<SqlParameter>();
		args.add( DAOModel.createParam("porcRSResultado", OracleTypes.CURSOR) );
        args.add( DAOModel.createParam("pstin_id_opoder_fk", OracleTypes.NUMBER, pstin_id_opoder_fk));             
        GenericDataBean result = new GenericDataBean();        
        try {
        	result = DAOModel.executeSpQueryGDB(PKG_REPORTES, "QUERY_REVOCACIONES", args);
		} catch (Exception ex) {		
			logger.error(ex.getMessage());        
		}        
        return result;
	}
	
	public static GenericDataBean query_REVOCACIONES(){
		List<SqlParameter> args = new ArrayList<SqlParameter>();
		args.add( DAOModel.createParam("porcRSResultado", OracleTypes.CURSOR) );                     
        GenericDataBean result = new GenericDataBean();        
        try {
        	result = DAOModel.executeSpQueryGDB(PKG_REPORTES, "QUERY_REVOCACIONES", args);
		} catch (Exception ex) {		
			logger.error(ex.getMessage());        
		}        
        return result;
	}
	
	public static GenericDataBean query_ESCRITURAS( String pstdes_empresas
			, String pstdes_tipopoder
			, String pstdes_apoderados
			, String pstdes_poder
			, String pstdes_escritura) throws Exception
    {
        List<SqlParameter> args = new ArrayList<SqlParameter>();
        args.add( DAOModel.createParam("porcRSResultado", OracleTypes.CURSOR) );
        args.add( DAOModel.createParam("pstdes_empresas", OracleTypes.VARCHAR, pstdes_empresas));
        args.add( DAOModel.createParam("pstdes_tipopoder", OracleTypes.VARCHAR, pstdes_tipopoder));
        args.add( DAOModel.createParam("pstdes_apoderados", OracleTypes.VARCHAR, pstdes_apoderados));
        args.add( DAOModel.createParam("pstdes_poder", OracleTypes.VARCHAR, pstdes_poder));
        args.add( DAOModel.createParam("pstdes_escritura", OracleTypes.VARCHAR, pstdes_escritura));     
        
        return DAOModel.executeSpQueryGDB(PKG_REPORTES, "QUERY_ESCRITURAS", args);
    }
	
	public static GenericDataBean query_PODERES_POR_EMPRESA (String pstdes_empresas
			, String pstdes_tipopoder
			, String pstdes_apoderados
			, String pstdes_poder
			, String pstdes_escritura) throws Exception
    {
        List<SqlParameter> args = new ArrayList<SqlParameter>();
        args.add( DAOModel.createParam("porcRSResultado", OracleTypes.CURSOR) );
        args.add( DAOModel.createParam("pstdes_empresas", OracleTypes.VARCHAR, pstdes_empresas));
        args.add( DAOModel.createParam("pstdes_tipopoder", OracleTypes.VARCHAR, pstdes_tipopoder));
        args.add( DAOModel.createParam("pstdes_apoderados", OracleTypes.VARCHAR, pstdes_apoderados));
        args.add( DAOModel.createParam("pstdes_poder", OracleTypes.VARCHAR, pstdes_poder));
        args.add( DAOModel.createParam("pstdes_escritura", OracleTypes.VARCHAR, pstdes_escritura));     
        
        return DAOModel.executeSpQueryGDB(PKG_REPORTES, "QUERY_PODERES_POR_EMPRESA", args);
    }
	
	public static GenericDataBean query_PODERES_CARTA_PODER_PR(int idEmpresa, String paramDeBusqueda) throws Exception
    {
		List<SqlParameter> args = new ArrayList<SqlParameter>();
        args.add( DAOModel.createParam("porcRSResultado", OracleTypes.CURSOR) );
        args.add( DAOModel.createParam("pstdesc_busqueda", OracleTypes.VARCHAR, paramDeBusqueda));
        args.add( DAOModel.createParam("pinid_empresa", OracleTypes.NUMBER, idEmpresa) );                
        args.add( DAOModel.createParam("pstOuterror",OracleTypes.VARCHAR));
                      
        return DAOModel.executeSpQueryGDB(PKG_CONSULTA, "QUERY_PODERES_CARTA_PODER_PR", args);
    }
	
	public static GenericDataBean queryOTORGA_PODER_PG(int idEscritura) throws Exception
    {
        List<SqlParameter> args = new ArrayList<SqlParameter>();
        args.add( DAOModel.createParam("porcRSResultado", OracleTypes.CURSOR) );
        args.add( DAOModel.createParam("pinid_ep_fk", OracleTypes.NUMBER, idEscritura) );
                      
        return DAOModel.executeSpQueryGDB(PKG_CONSULTA,"QUERY_OTORGA_PODER_PG", args);
    }
	
	public static GenericDataBean query_PODERES_POR_TIPO_DE_PODER (String pstdes_empresas
			, String pstdes_tipopoder
			, String pstdes_apoderados
			, String pstdes_poder
			, String pstdes_escritura) throws Exception
    {
        List<SqlParameter> args = new ArrayList<SqlParameter>();
        args.add( DAOModel.createParam("porcRSResultado", OracleTypes.CURSOR) );
        args.add( DAOModel.createParam("pstdes_empresas", OracleTypes.VARCHAR, pstdes_empresas));
        args.add( DAOModel.createParam("pstdes_tipopoder", OracleTypes.VARCHAR, pstdes_tipopoder));
        args.add( DAOModel.createParam("pstdes_apoderados", OracleTypes.VARCHAR, pstdes_apoderados));
        args.add( DAOModel.createParam("pstdes_poder", OracleTypes.VARCHAR, pstdes_poder));
        args.add( DAOModel.createParam("pstdes_escritura", OracleTypes.VARCHAR, pstdes_escritura));     
        
        return DAOModel.executeSpQueryGDB(PKG_REPORTES, "QUERY_PODERES_TIPO_PODER", args);
    }
	
	public static GenericDataBean query_PODERES_POR_VIGENCIA (String pstdes_empresas
			, String pstdes_fecha_desde
			, String pstdes_fecha_hasta
			, String pstdes_tipopoder
			, String pstdes_apoderados
			, String pstdes_poder
			, String pstdes_escritura) throws Exception
    {
        List<SqlParameter> args = new ArrayList<SqlParameter>();
        args.add( DAOModel.createParam("porcRSResultado", OracleTypes.CURSOR) );
        args.add( DAOModel.createParam("pstdes_empresas", OracleTypes.VARCHAR, pstdes_empresas));
        args.add( DAOModel.createParam("pstdes_fecha_desde", OracleTypes.VARCHAR, pstdes_fecha_desde));
        args.add( DAOModel.createParam("pstdes_fecha_hasta", OracleTypes.VARCHAR, pstdes_fecha_hasta));        
        args.add( DAOModel.createParam("pstdes_tipopoder", OracleTypes.VARCHAR, pstdes_tipopoder));
        args.add( DAOModel.createParam("pstdes_apoderados", OracleTypes.VARCHAR, pstdes_apoderados));
        args.add( DAOModel.createParam("pstdes_poder", OracleTypes.VARCHAR, pstdes_poder));
        args.add( DAOModel.createParam("pstdes_escritura", OracleTypes.VARCHAR, pstdes_escritura));     
        
        return DAOModel.executeSpQueryGDB(PKG_REPORTES, "QUERY_PODERES_POR_VIGENCIA", args);
    }
}