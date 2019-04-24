package mx.com.televisa.derechocorporativo.servlets;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import mx.com.televisa.derechocorporativo.bean.SessionBean;



import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import mx.com.televisa.derechocorporativo.bean.GenericBean;
import mx.com.televisa.derechocorporativo.bean.GenericDataBean;
import mx.com.televisa.derechocorporativo.daos.MngDataPoderes;
import mx.com.televisa.derechocorporativo.util.FacesUtils;

/**
 * Servlet implementation class Abc_PoderesGenerales
 */
@WebServlet(description = "Altas bajas cambios y consulta de Apoderados", urlPatterns = { "/Abc_PoderesGenerales" })
public class Abc_PoderesGenerales extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(Abc_PoderesGenerales.class);
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Abc_PoderesGenerales() {
        super();
        // TODO Auto-generated constructor stub
    }
        
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected SessionBean sessionBean;
	
	public void processRequest(HttpServletRequest request,HttpServletResponse response) throws IOException{
		this.request = request;
		this.response = response;
		String json = "";
         Object rst = null;         
         String action = "";
         Gson gson = new Gson();
         PrintWriter out = response.getWriter();
         try
         {
             //String NetIdentity = "LA\\pvidal1";   
        	 HttpSession session = request.getSession();
        	 sessionBean = (SessionBean)session.getAttribute("sessionBean");
             action = request.getParameter("action");
             switch(action){
             	case "getCATALOGOS": rst = getCATALOGOS(action); break;
             	case "toJsonTest": rst = toJsonTest(action); break;             
             	case "newEscritura": rst = newEscritura(action); break;
             	case "updEscritura": rst = updEscritura(action); break;
             	case "cpyEscritura": rst = cpyEscritura(action); break;
             	case "getDetailsEscritura": rst = getDetailsEscritura(action); break;
             	case "getEscrituraPoder": rst = getEscrituraPoder(action); break;
             	case "deleteEscrituraPoder": rst = deleteEscrituraPoder(action); break;
             	             	
             	case "addDocumentums": rst = addDocumentums(action); break;
             	case "getDocumentums": rst = getDocumentums(action); break;
             	
             	case "addOtorgaPoder": rst = addOtorgaPoder(action); break;
             	case "getOtorgaPoder": rst = getOtorgaPoder(action); break;
             	
             	case "addApoderado": rst = addApoderado(action); break;
             	case "getApoderados": rst = getApoderados(action); break;
             	
             	case "getListEscRev": rst = getListEscRev(action); break;
             
             	case "newCatalogoPoder": rst = newCatalogoPoder(action); break;
             	case "updCatalogoPoder": rst = updCatalogoPoder(action); break;
             	case "deleteCatalogoPoder": rst = deleteCatalogoPoder(action); break;             	
             	case "getCatalogosPoderes": rst = getCatalogosPoderes(action); break;
             	
             	case "consultarEscrituras": rst = consultarEscrituras(action); break;
             	case "cargarHtmlConsultaPoderes": rst =  cargarHtmlConsultaPoderes(action); break;
             	case "cargarHtmlDetallePoderes": rst = cargarHtmlDetallePoderes(action);break;              	             
             	
             	case "execProcess": rst = execProcess(action);break; 
             }
             
             json = gson.toJson(rst);             

             out.print(new String(json.getBytes("UTF-8"),"ISO-8859-1"));

         }catch(Exception ex){
        	 json = gson.toJson(ex);
        	 out.print(json);
        	 logger.error(ex.getMessage());        	 
         }             
    }
		
	
	
	public Object newEscritura(String action)throws Exception{
		//try(Reader reader = new InputStreamReader(JsonToJava.class.getResourceAsStream("/Server1.json"), "UTF-8")){
            Gson gson = new GsonBuilder().create();
            GenericBean headEsc = gson.fromJson(request.getParameter("HeadEscritura"), GenericBean.class);
            GenericDataBean listPoderes = gson.fromJson(request.getParameter("ListPoderes"), GenericDataBean.class);
            List<GenericDataBean> listApododerados = gson.fromJson(request.getParameter("ListApoderados"),ArrayList.class);
            
            GenericDataBean listDocs = gson.fromJson(request.getParameter("ListDocumentos"), GenericDataBean.class);
            
            int idEsc = MngDataPoderes.insertESCRITURA_PODER(headEsc);
            
            for(int i=0; i < listPoderes.beans.size(); i++){
            	GenericBean<Object> poder = listPoderes.get(i);
            	poder.setProperty("id_ep_fk",idEsc);
            	int idPoder = MngDataPoderes.insertOTORGA_PODER(poder);
            	poder.setProperty("id_opoder_ep_pk",idPoder);                         	
            	GenericDataBean listApoderados = gson.fromJson(request.getParameter("ListApoderados"+i), GenericDataBean.class);
            	for(int a=0; a < listApoderados.beans.size(); a++){
                	GenericBean<Object> apoder = listApoderados.get(a);
                	apoder.setProperty("id_opoder_ep_fk",idPoder);
                	apoder.setProperty("id_ep_fk",idEsc);       
                	//apoder.setProperty("id_empl_fk",);
                	//apoder.setProperty("desc_nom_empl",);
                	String num_user_str = request.getParameter("ID_Usuario_Actual");
                	num_user_str=num_user_str.replace("\"", "");
                    int num_user = 0;
                    if(num_user_str != null && !num_user_str.equals("")){
                    	num_user = (int)Double.parseDouble( num_user_str );
                    }
                	apoder.setProperty("num_created_by",num_user );
                	int idApoder = MngDataPoderes.insertAPODERADO(apoder);
                	apoder.setProperty("id_apod_ep_pk",idApoder);
            	}
            	try{
            		GenericDataBean listFacultades = gson.fromJson(request.getParameter("Facultades"+i), GenericDataBean.class);
            		for(int b=0; b < listFacultades.beans.size(); b++){
                    	GenericBean<Object> facult = listFacultades.get(b);
                    	facult.setProperty("id_opoder_ep_fk",idPoder);
                    	facult.setProperty("id_ep_fk",idEsc);       
                    	//apoder.setProperty("id_empl_fk",);
                    	//apoder.setProperty("desc_nom_empl",);
                    	facult.setProperty("num_created_by", request.getParameter("ID_Usuario_Actual"));
                    	int idFac = MngDataPoderes.insertFACULTADES(facult);
                    	if(Boolean.parseBoolean( facult.getProperty("MANCOMUNADO").toString())){
                    		GenericDataBean listMancomunados = gson.fromJson(request.getParameter("ListMancomunados"+i+"_"+b), GenericDataBean.class);
                    		for(int m=0; m<listMancomunados.beans.size();m++){
                    			GenericBean<Object> mancomunado = listMancomunados.get(m);
                            	mancomunado.setProperty("id_opoder_ep_fk",idPoder);
                            	mancomunado.setProperty("id_ep_fk",idEsc);  
                            	mancomunado.setProperty("ind_tipoapoderado",2);  
                            	mancomunado.setProperty("desc_tipoapoderado","MANCOMUNADO");  
                            	mancomunado.setProperty("id_empl_fk",mancomunado.getProperty("id"));
                            	mancomunado.setProperty("desc_nom_empl",mancomunado.getProperty("value"));
                            	mancomunado.setProperty("num_created_by", request.getParameter("ID_Usuario_Actual"));
                            	mancomunado.setProperty("des_grupo", "MANCOMUNADO");
                            	mancomunado.setProperty("id_grupo_fk", idFac);
                            	mancomunado.setProperty("ind_aprevoca", 0);
                            	mancomunado.setProperty("desc_revoca", "");
                            	mancomunado.setProperty("ind_status", 1);
                            	int idMancomunado = MngDataPoderes.insertAPODERADO(mancomunado);
                            	mancomunado.setProperty("id_apod_ep_pk",idMancomunado);
                    		}
                    	}
                    	facult.setProperty("id_fac_ep_pk",idFac);
                	}
            		
            	}catch(Exception ex){
            		 logger.error(ex.getMessage());        
            	}
            }
            
            for(int i=0; i < listDocs.beans.size(); i++){
            	GenericBean<Object> doc = listDocs.get(i);
            	doc.setProperty("id_ep_fk",idEsc);
            	int idDoc = MngDataPoderes.insertDOCUMENTUMS(doc);
            	doc.setProperty("id_doc_ep_pk",idDoc);
            }
            String idEmp = headEsc.getProperty("id_empresa").toString();
            String tipo = headEsc.getProperty("ind_tipo_escritura").toString();
            return MngDataPoderes.queryESCRITURA_PODER(Integer.parseInt(idEmp), tipo);
           	
	}
	
	
	public Object getListEscRev(String action)throws Exception{
		
		String idCia = request.getParameter("IdCia");
		//******** Carga registros de Escritura Poder Tipo Poder General "PG" :4
				GenericDataBean listEP2 = MngDataPoderes.queryESCRITURA_PODER(Integer.parseInt(idCia), "PG");
				
			
			//******** Carga registros de Revocaciones "ER" :5
				GenericDataBean listER = MngDataPoderes.queryESCRITURA_PODER(Integer.parseInt(idCia), "ER" );
				GenericDataBean listPE = MngDataPoderes.queryESCRITURA_PODER(Integer.parseInt(idCia), "PE" );
				
				GenericDataBean listEP = MngDataPoderes.queryESCRITURA_PODER(Integer.parseInt(idCia), "PG");
				
				listEP.beans = new ArrayList<Object[]>();
				for(int i =0; i<listEP2.beans.size();i++){
					Object idEsc =  listEP2.get(i).getProperty("des_escritura");
					if(idEsc != null)
						listEP.beans.add(listEP2.beans.get(i));
				}
				for(int i =0; i<listER.beans.size();i++){
					Object idEsc =  listER.get(i).getProperty("des_escritura");
					if(idEsc != null){
						boolean exists = false;
						for(int l=0; l<listEP.beans.size();l++){
							Object idEscIn =  listEP.get(l).getProperty("des_escritura");
							if(idEscIn.toString().equals(idEsc.toString())){
								exists = true;
								break;
							}						
						}
						if(!exists)
							listEP.beans.add(listER.beans.get(i));
					}
				}
				for(int i =0; i<listPE.beans.size();i++){
					Object idEsc =  listPE.get(i).getProperty("des_escritura");
					if(idEsc != null){
						boolean exists = false;
						for(int l=0; l<listEP.beans.size();l++){
							Object idEscIn =  listEP.get(l).getProperty("des_escritura");
							if(idEscIn.toString().equals(idEsc.toString())){
								exists = true;
								break;
							}						
						}
						if(!exists)
							listEP.beans.add(listPE.beans.get(i));
					}
				}
				
				return listEP;
	}
	
	
	public Object newCatalogoPoder(String action)throws Exception{
            Gson gson = new GsonBuilder().create();
            GenericBean catalogoPoder = gson.fromJson(request.getParameter("CatalogoPoder"), GenericBean.class);                                   
            int idCatalogo = gson.fromJson(request.getParameter("ID_Catalogo"), Integer.class);        
            catalogoPoder.setProperty("id_catalogo", idCatalogo);
            int idUsuarioActual = gson.fromJson(request.getParameter("ID_Usuario_Actual"), Integer.class);        
            catalogoPoder.setProperty("num_created_by", idUsuarioActual);
            GenericDataBean catalogosDePoderes = new GenericDataBean();
            
            try{
            	MngDataPoderes.insertCatalogoPoder(catalogoPoder);
            	catalogosDePoderes = MngDataPoderes.queryCatalogosDePoderes((String)catalogoPoder.getProperty("ind_podertipo"));
            }catch(Exception ex){
            	logger.error(ex.getMessage());                    	
            	catalogosDePoderes = null;
            }
            return catalogosDePoderes;                     	
	}
	
	public Object updCatalogoPoder(String action)throws Exception{
        Gson gson = new GsonBuilder().create();
        GenericBean catalogoPoder = gson.fromJson(request.getParameter("CatalogoPoder"), GenericBean.class); 
        int idUsuarioActual = gson.fromJson(request.getParameter("ID_Usuario_Actual"), Integer.class);        
        catalogoPoder.setProperty("num_last_updated_by", idUsuarioActual);
        GenericDataBean catalogosDePoderes = new GenericDataBean();
        try{
        	MngDataPoderes.updateCatalogoPoder(catalogoPoder);
        	catalogosDePoderes = MngDataPoderes.queryCatalogosDePoderes((String)catalogoPoder.getProperty("ind_podertipo"));
        }catch(Exception ex){
        	logger.error(ex.getMessage());        
        	catalogosDePoderes = null;
        }
        return catalogosDePoderes;                     	
	}
	
	public Object deleteCatalogoPoder(String action)throws Exception{		
		Gson gson = new GsonBuilder().create();
		int idCatalogoPoder =  gson.fromJson(request.getParameter("ID_Catalogo_Poder"), Integer.class);
		int idUsuarioActual = gson.fromJson(request.getParameter("ID_Usuario_Actual"), Integer.class);        
        				
		Object resultado = MngDataPoderes.deleteCatalogoPoder(idCatalogoPoder, idUsuarioActual);
		
		return resultado; 
	}
	
	public Object getCatalogosPoderes(String action)throws Exception{
		Gson gson = new GsonBuilder().create();
		GenericDataBean detalleDelPoder = new GenericDataBean();
		try{
			String tipoCatalogoPoder = gson.fromJson(request.getParameter("Tipo_Catalogo_Poder"), String.class);
			String nombreCatalogoPoder = gson.fromJson(request.getParameter("Nombre_Catalogo_Poder"), String.class);
			detalleDelPoder = MngDataPoderes.queryCatalogosDePoderes(tipoCatalogoPoder, nombreCatalogoPoder);        
        }
        catch(Exception ex){
        	detalleDelPoder = null;
        	 logger.error(ex.getMessage());        
        }
        return detalleDelPoder;
	}
			
	public Object cpyEscritura(String action)throws Exception{
		Gson gson = new GsonBuilder().create();
		GenericBean headEsc = gson.fromJson(request.getParameter("HeadEscritura"), GenericBean.class);
		List<Object> cias = gson.fromJson(request.getParameter("CompanyList"), ArrayList.class);
		String type = request.getParameter("CopyType");
		String mode = request.getParameter("CopyMode");
		String modeType = request.getParameter("ModeType");
		
		boolean Head = Boolean.parseBoolean(request.getParameter("Head"));
		boolean Status = Boolean.parseBoolean(request.getParameter("Status"));
		boolean Docs = Boolean.parseBoolean(request.getParameter("Docs"));
		boolean Poderes = Boolean.parseBoolean(request.getParameter("Poderes"));
				
		String num_user_str = request.getParameter("ID_Usuario_Actual");
    	num_user_str=num_user_str.replace("\"", "");
        int num_user = 0;
        if(num_user_str != null && !num_user_str.equals("")){
        	num_user = (int)Double.parseDouble( num_user_str );
        }
        headEsc.setProperty("num_created_by",num_user );
		
		int idEsc = 0;
		String tipoEsc = headEsc.getProperty("ind_tipo_escritura").toString();
		
		
		switch(type){
			case "Import": 
				switch(mode){
					case "Convert": 
						headEsc.setProperty("desc_apoderados","");
						headEsc.setProperty("desc_asunto","*");
						headEsc.setProperty("des_caracteristicas","*");
						headEsc.setProperty("ind_tipo_escritura",modeType);
						if(modeType.equals("ER") || modeType.equals("CP")){
							headEsc.setProperty("des_escritura","");
							headEsc.setProperty("num_documentum_instr","");
						}
						idEsc = MngDataPoderes.insertESCRITURA_PODER(headEsc);
						break; 
					case "Copy": 
						headEsc.setProperty("des_escritura","");
						headEsc.setProperty("num_documentum_instr","");
						if(modeType.equals(tipoEsc)){
							
							int ID_Esc = (int)Double.parseDouble(headEsc.getProperty("id_ep_pk").toString());
							if(!Head){
								headEsc.setProperty("ind_delegado_por","");
								headEsc.setProperty("fec_fecha","");
								headEsc.setProperty("fec_hora","");
								headEsc.setProperty("ind_requiere_proto","");
								headEsc.setProperty("ind_requiere_inscr_rppc","");
								headEsc.setProperty("des_escritura","");
								headEsc.setProperty("num_documentum_instr","");
								headEsc.setProperty("fec_otorgamiento_instr","");
								headEsc.setProperty("num_licenciado","");
								headEsc.setProperty("des_suplencia_asociado","");
								headEsc.setProperty("fec_registro","");
								headEsc.setProperty("num_folio_merc","");
								headEsc.setProperty("des_otros_datos_registro","");
								headEsc.setProperty("ind_status_esc","");
								headEsc.setProperty("ind_status_rppc","");
								headEsc.setProperty("num_insc_regpub","");
							}
							if(!Status){
								headEsc.setProperty("ind_ok","");
								headEsc.setProperty("fec_pe","");
								headEsc.setProperty("ind_status_ac","");
								headEsc.setProperty("id_red_resp","");
								headEsc.setProperty("des_rep_resp","");
								headEsc.setProperty("fec_rep","");
								headEsc.setProperty("id_reg_resp","");
								headEsc.setProperty("des_reg_resp","");
								headEsc.setProperty("fec_reg","");
								headEsc.setProperty("id_cor_resp","");
								headEsc.setProperty("des_cor_resp","");
								headEsc.setProperty("fec_cor","");
								headEsc.setProperty("id_aut_resp","");
								headEsc.setProperty("des_aut_resp","");
								headEsc.setProperty("fec_aut","");
								headEsc.setProperty("id_fir_resp","");
								headEsc.setProperty("des_fir_resp","");
								headEsc.setProperty("fec_fir","");
								headEsc.setProperty("fec_ent","");
								headEsc.setProperty("id_ent_resp","");
								headEsc.setProperty("des_ent_resp","");
								headEsc.setProperty("ind_aplica_status","");
							}							
							if(!Docs){
								headEsc.setProperty("fec_ent","");
								headEsc.setProperty("id_sol_doc","");
								headEsc.setProperty("id_sol_resp","");
								headEsc.setProperty("des_sol_resp","");
								headEsc.setProperty("fec_sol","");
								headEsc.setProperty("fec_sol_rec","");
								headEsc.setProperty("des_sol_folio","");
								headEsc.setProperty("id_ent_doc","");
								headEsc.setProperty("fec_ent_doc","");
								headEsc.setProperty("fec_ent_rec","");

							}
							if(!Poderes){
								headEsc.setProperty("desc_apoderados","");
								headEsc.setProperty("desc_asunto","*");
								headEsc.setProperty("des_caracteristicas","*");
							}else{							
								/* Remplazar apendices */
				            	String desc_apoderados = headEsc.getProperty("desc_apoderados").toString();
				            	int apendice=1;
				            	while( desc_apoderados.indexOf("<label style=\"color:red;\">")>=0 ){
				            		desc_apoderados = desc_apoderados.replace("<label style=\"color:red;\">"+apendice+"</label>", "");
				            		desc_apoderados = desc_apoderados.replace("<label style=\"color:red;\">V</label>", "");
				            		apendice++;
				            	}
				            	headEsc.setProperty("desc_apoderados",desc_apoderados);
							}
							idEsc = MngDataPoderes.insertESCRITURA_PODER(headEsc);	
							
							if(Docs){							
							
								GenericDataBean listDocs = MngDataPoderes.queryDOCUMENTUMS(ID_Esc);
								for(int d=0; d < listDocs.beans.size(); d++){
					            	GenericBean<Object> doc = listDocs.get(d);
					            	doc.setProperty("id_ep_fk",idEsc);
					            	int idDoc = MngDataPoderes.insertDOCUMENTUMS(doc);
					            	doc.setProperty("id_doc_ep_pk",idDoc);
					            }
							}	
							
							if(Poderes){
						        GenericDataBean listPoderes = MngDataPoderes.queryOTORGA_PODER(ID_Esc);
						        
						        for(int p=0; p < listPoderes.beans.size(); p++){
					            	GenericBean<Object> poder = listPoderes.get(p);
					            	poder.setProperty("id_ep_fk",idEsc);
					            	
					            	/* Remplazar apendices */
					            	String desc_apoderados = poder.getProperty("desc_apoderados").toString();
					            	int apendice=1;
					            	while( desc_apoderados.indexOf("<label style=\"color:red;\">")>=0 ){
					            		desc_apoderados = desc_apoderados.replace("<label style=\"color:red;\">"+apendice+"</label>", "");
					            		desc_apoderados = desc_apoderados.replace("<label style=\"color:red;\">V</label>", "");
					            		apendice++;
					            	}
					            	poder.setProperty("desc_apoderados",desc_apoderados);
					            	
					            	int idPoder = MngDataPoderes.insertOTORGA_PODER(poder);
					            	int Id_Poder = (int)Double.parseDouble(poder.getProperty("id_opoder_ep_pk").toString());
					            	poder.setProperty("id_opoder_ep_pk",idPoder);                         	
					            	//GenericDataBean listApoderados = gson.fromJson(request.getParameter("ListApoderados"+i), GenericDataBean.class);
					            	GenericDataBean listApoderados = MngDataPoderes.queryAPODERADO(Id_Poder); 
					            	for(int a=0; a < listApoderados.beans.size(); a++){
					                	GenericBean<Object> apoder = listApoderados.get(a);
					                	apoder.setProperty("id_opoder_ep_fk",idPoder);
					                	apoder.setProperty("id_ep_fk",idEsc);
					                	apoder.setProperty("num_created_by",num_user );
					                	apoder.setProperty("ind_status","1");
					                	apoder.setProperty("ind_aprevoca",null);
					                	apoder.setProperty("desc_revoca",null);					                	

					                	int idApoder = MngDataPoderes.insertAPODERADO(apoder);					                	
					                	apoder.setProperty("id_apod_ep_pk",idApoder);
					            	}
					            	try{
					            		GenericDataBean listFacultades = MngDataPoderes.queryFACULTADES(Id_Poder);  
					            		for(int b=0; b < listFacultades.beans.size(); b++){
					                    	GenericBean<Object> facult = listFacultades.get(b);
					                    	facult.setProperty("id_opoder_ep_fk",idPoder);
					                    	facult.setProperty("id_ep_fk",idEsc);       
					                    	
					                    	facult.setProperty("num_created_by",num_user );
					                    	facult.setProperty("mancomunado",false);
					                    	int idFac = MngDataPoderes.insertFACULTADES(facult);
					                    	int id_fac_ep_pk = (int)Double.parseDouble(facult.getProperty("id_fac_ep_pk").toString());
					                    	/*Boolean manco = false;
					                    	try{
					                    		manco = Boolean.parseBoolean( facult.getProperty("MANCOMUNADO").toString());
					                    	}catch(Exception e){
					                    		manco = (int)Double.parseDouble(facult.getProperty("MANCOMUNADO").toString())==1;
					                    	}
					                    	if(manco){
					                    		GenericDataBean listMancomunados = MngDataPoderes.queryAPODERADO(id_fac_ep_pk, 1); 
					                    		for(int m=0; m<listMancomunados.beans.size();m++){
					                    			GenericBean<Object> mancomunado = listMancomunados.get(m);
					                            	mancomunado.setProperty("id_opoder_ep_fk",idPoder);
					                            	mancomunado.setProperty("id_ep_fk",idEsc);  
					                            	mancomunado.setProperty("ind_tipoapoderado",2);  
					                            	mancomunado.setProperty("desc_tipoapoderado","MANCOMUNADO");  
					                            	mancomunado.setProperty("id_empl_fk",mancomunado.getProperty("id"));
					                            	mancomunado.setProperty("desc_nom_empl",mancomunado.getProperty("value"));
					                            	mancomunado.setProperty("num_created_by", request.getParameter("ID_Usuario_Actual"));
					                            	mancomunado.setProperty("des_grupo", "MANCOMUNADO");
					                            	mancomunado.setProperty("id_grupo_fk", idFac);
					                            	mancomunado.setProperty("ind_aprevoca", 0);
					                            	mancomunado.setProperty("desc_revoca", "");
					                            	mancomunado.setProperty("ind_status", 1);
					                            	int idMancomunado = MngDataPoderes.insertAPODERADO(mancomunado);
					                            	mancomunado.setProperty("id_apod_ep_pk",idMancomunado);
					                    		}
					                    	}*/
					                    	facult.setProperty("id_apod_ep_pk",idFac);
					                	}
					            	}catch(Exception ex){
					            		 logger.error(ex.getMessage());        
					            	}
					            }
							}
						}else{
							headEsc.setProperty("desc_apoderados","");
							headEsc.setProperty("desc_asunto","*");
							headEsc.setProperty("des_caracteristicas","*");
							headEsc.setProperty("ind_tipo_escritura",modeType);
							idEsc = MngDataPoderes.insertESCRITURA_PODER(headEsc);
						}
						
												
						break;
				}
				
				
				break;
			case "Export": 				
				if(modeType.equals(tipoEsc)){					
					int ID_Esc = (int)Double.parseDouble(headEsc.getProperty("id_ep_pk").toString());
					for(int i=0;i<cias.size();i++){
						int cia = (int)Double.parseDouble(cias.get(i).toString());
						headEsc.setProperty("des_escritura","");
						headEsc.setProperty("num_documentum_instr","");
						headEsc.setProperty("id_empresa",cia);
						if(!Head){
							headEsc.setProperty("ind_delegado_por","");
							headEsc.setProperty("fec_fecha","");
							headEsc.setProperty("fec_hora","");
							headEsc.setProperty("ind_requiere_proto","");
							headEsc.setProperty("ind_requiere_inscr_rppc","");
							headEsc.setProperty("des_escritura","");
							headEsc.setProperty("num_documentum_instr","");
							headEsc.setProperty("fec_otorgamiento_instr","");
							headEsc.setProperty("num_licenciado","");
							headEsc.setProperty("des_suplencia_asociado","");
							headEsc.setProperty("fec_registro","");
							headEsc.setProperty("num_folio_merc","");
							headEsc.setProperty("des_otros_datos_registro","");
							headEsc.setProperty("ind_status_esc","");
							headEsc.setProperty("ind_status_rppc","");
							headEsc.setProperty("num_insc_regpub","");
						}
						if(!Status){
							headEsc.setProperty("ind_ok","");
							headEsc.setProperty("fec_pe","");
							headEsc.setProperty("ind_status_ac","");
							headEsc.setProperty("id_red_resp","");
							headEsc.setProperty("des_rep_resp","");
							headEsc.setProperty("fec_rep","");
							headEsc.setProperty("id_reg_resp","");
							headEsc.setProperty("des_reg_resp","");
							headEsc.setProperty("fec_reg","");
							headEsc.setProperty("id_cor_resp","");
							headEsc.setProperty("des_cor_resp","");
							headEsc.setProperty("fec_cor","");
							headEsc.setProperty("id_aut_resp","");
							headEsc.setProperty("des_aut_resp","");
							headEsc.setProperty("fec_aut","");
							headEsc.setProperty("id_fir_resp","");
							headEsc.setProperty("des_fir_resp","");
							headEsc.setProperty("fec_fir","");
							headEsc.setProperty("fec_ent","");
							headEsc.setProperty("id_ent_resp","");
							headEsc.setProperty("des_ent_resp","");
							headEsc.setProperty("ind_aplica_status","");
						}							
						if(!Docs){
							headEsc.setProperty("fec_ent","");
							headEsc.setProperty("id_sol_doc","");
							headEsc.setProperty("id_sol_resp","");
							headEsc.setProperty("des_sol_resp","");
							headEsc.setProperty("fec_sol","");
							headEsc.setProperty("fec_sol_rec","");
							headEsc.setProperty("des_sol_folio","");
							headEsc.setProperty("id_ent_doc","");
							headEsc.setProperty("fec_ent_doc","");
							headEsc.setProperty("fec_ent_rec","");

						}
						if(!Poderes){
							headEsc.setProperty("desc_apoderados","");
							headEsc.setProperty("desc_asunto","*");
							headEsc.setProperty("des_caracteristicas","*");
						}else{
							/* Remplazar apendices */
			            	String desc_apoderados = headEsc.getProperty("desc_apoderados").toString();
			            	int apendice=1;
			            	while( desc_apoderados.indexOf("<label style=\"color:red;\">")>=0 ){
			            		desc_apoderados = desc_apoderados.replace("<label style=\"color:red;\">"+apendice+"</label>", "");
			            		desc_apoderados = desc_apoderados.replace("<label style=\"color:red;\">V</label>", "");
			            		apendice++;
			            	}
			            	headEsc.setProperty("desc_apoderados",desc_apoderados);
						}
						idEsc = MngDataPoderes.insertESCRITURA_PODER(headEsc);	
						
						if(Docs){						
							GenericDataBean listDocs = MngDataPoderes.queryDOCUMENTUMS(ID_Esc);
							for(int d=0; d < listDocs.beans.size(); d++){
				            	GenericBean<Object> doc = listDocs.get(d);
				            	doc.setProperty("id_ep_fk",idEsc);
				            	int idDoc = MngDataPoderes.insertDOCUMENTUMS(doc);
				            	doc.setProperty("id_doc_ep_pk",idDoc);
				            }
						}	 
						
						if(Poderes){
							GenericDataBean listPoderes = MngDataPoderes.queryOTORGA_PODER(ID_Esc);
					        
					        for(int p=0; p < listPoderes.beans.size(); p++){
				            	GenericBean<Object> poder = listPoderes.get(p);
				            	poder.setProperty("id_ep_fk",idEsc);
				            	int Id_Poder = (int)Double.parseDouble(poder.getProperty("id_opoder_ep_pk").toString());
				            	
				            	/* Remplazar apendices */
				            	String desc_apoderados = poder.getProperty("desc_apoderados").toString();
				            	int apendice=1;
				            	while( desc_apoderados.indexOf("<label style=\"color:red;\">")>=0 ){
				            		desc_apoderados = desc_apoderados.replace("<label style=\"color:red;\">"+apendice+"</label>", "");
				            		desc_apoderados = desc_apoderados.replace("<label style=\"color:red;\">V</label>", "");
				            		apendice++;
				            	}
				            	poder.setProperty("desc_apoderados",desc_apoderados);
				            	
				            	int idPoder = MngDataPoderes.insertOTORGA_PODER(poder);
				            	             	
				            	
				            	GenericDataBean listApoderados = MngDataPoderes.queryAPODERADO(Id_Poder); 
				            	for(int a=0; a < listApoderados.beans.size(); a++){
				                	GenericBean<Object> apoder = listApoderados.get(a);
				                	apoder.setProperty("id_opoder_ep_fk",idPoder);
				                	apoder.setProperty("id_ep_fk",idEsc);
				                	apoder.setProperty("num_created_by",num_user );
				                	apoder.setProperty("ind_status","1");
				                	apoder.setProperty("ind_aprevoca",null);
				                	apoder.setProperty("desc_revoca",null);	
				                	
				                	int idApoder = MngDataPoderes.insertAPODERADO(apoder);				                	
				                	apoder.setProperty("id_apod_ep_pk",idApoder);
				            	}
				            	try{
				            		GenericDataBean listFacultades = MngDataPoderes.queryFACULTADES(Id_Poder);  
				            		for(int b=0; b < listFacultades.beans.size(); b++){
				                    	GenericBean<Object> facult = listFacultades.get(b);
				                    	facult.setProperty("id_opoder_ep_fk",idPoder);
				                    	facult.setProperty("id_ep_fk",idEsc);       
				                    	
				                    	facult.setProperty("num_created_by",num_user );
				                    	facult.setProperty("mancomunado",false);
				                    	
				                    	int idFac = MngDataPoderes.insertFACULTADES(facult);
				                    	
				                    	/*int id_fac_ep_pk = (int)Double.parseDouble(facult.getProperty("id_fac_ep_pk").toString());
				                    	Boolean manco = false;
				                    	try{
				                    		manco = Boolean.parseBoolean( facult.getProperty("MANCOMUNADO").toString());
				                    	}catch(Exception e){
				                    		manco = (int)Double.parseDouble(facult.getProperty("MANCOMUNADO").toString())==1;
				                    	}
				                    	if(manco){
				                    		GenericDataBean listMancomunados = MngDataPoderes.queryAPODERADO(id_fac_ep_pk, 1); 
				                    		for(int m=0; m<listMancomunados.beans.size();m++){
				                    			GenericBean<Object> mancomunado = listMancomunados.get(m);
				                            	mancomunado.setProperty("id_opoder_ep_fk",idPoder);
				                            	mancomunado.setProperty("id_ep_fk",idEsc);  
				                            	mancomunado.setProperty("ind_tipoapoderado",2);  
				                            	mancomunado.setProperty("desc_tipoapoderado","MANCOMUNADO");  
				                            	mancomunado.setProperty("id_empl_fk",mancomunado.getProperty("id"));
				                            	mancomunado.setProperty("desc_nom_empl",mancomunado.getProperty("value"));
				                            	mancomunado.setProperty("num_created_by", request.getParameter("ID_Usuario_Actual"));
				                            	mancomunado.setProperty("des_grupo", "MANCOMUNADO");
				                            	mancomunado.setProperty("id_grupo_fk", idFac);
				                            	mancomunado.setProperty("ind_aprevoca", 0);
				                            	mancomunado.setProperty("desc_revoca", "");
				                            	mancomunado.setProperty("ind_status", 1);
				                            	int idMancomunado = MngDataPoderes.insertAPODERADO(mancomunado);
				                            	mancomunado.setProperty("id_apod_ep_pk",idMancomunado);
				                    		}
				                    	}*/
				                    	
				                    	
				                    	facult.setProperty("id_apod_ep_pk",idFac);
				                	}
				            	}catch(Exception ex){
				            		logger.error(ex.getMessage());        
				            	}
				            	
				            	
				            }
						}
					}
				}else{
					headEsc.setProperty("desc_apoderados","");
					headEsc.setProperty("desc_asunto","*");
					headEsc.setProperty("des_caracteristicas","*");
					headEsc.setProperty("ind_tipo_escritura",modeType);
					for(int i=0;i<cias.size();i++){
						int cia = (int)Double.parseDouble(cias.get(i).toString());
						headEsc.setProperty("id_empresa",cia);
						idEsc = MngDataPoderes.insertESCRITURA_PODER(headEsc);			
					}
				}
				
				break;
		}
		
		
			
			String idEmp = headEsc.getProperty("id_empresa").toString();
			int idCIA = 0;
			idEmp=idEmp.replace("\"", "");	        
	        if(idEmp != null && !idEmp.equals(""))
	        	idCIA = (int)Double.parseDouble( idEmp );
	        if(type.equals("Export"))
	        	return new Object[]{"Export",MngDataPoderes.queryESCRITURA_PODER(idCIA, modeType)};
	        else
	        	return new Object[]{modeType,MngDataPoderes.queryESCRITURA_PODER(idCIA, modeType)};
		
	}
		
	public Object updEscritura(String action)throws Exception{
		//try(Reader reader = new InputStreamReader(JsonToJava.class.getResourceAsStream("/Server1.json"), "UTF-8")){
            Gson gson = new GsonBuilder().create();
            //String hEsc = URLDecoder.decode(request.getParameter("HeadEscritura"));
           // GenericBean headEsc = gson.fromJson(hEsc, GenericBean.class);
            GenericBean headEsc = gson.fromJson(request.getParameter("HeadEscritura"), GenericBean.class);
            GenericDataBean listPoderes = gson.fromJson(request.getParameter("ListPoderes"), GenericDataBean.class);
            List<GenericDataBean> listApododerados = gson.fromJson(request.getParameter("ListApoderados"),ArrayList.class);
            GenericDataBean listRevocados = gson.fromJson(request.getParameter("ListRevocados"),GenericDataBean.class);

            GenericDataBean listDocs = gson.fromJson(request.getParameter("ListDocumentos"), GenericDataBean.class);
            
            MngDataPoderes.updateESCRITURA_PODER(headEsc);
            String dataId = headEsc.getProperty("id_ep_pk").toString();
            int idEsc = 0;
            if(dataId != null && !dataId.equals("")){
            	idEsc = (int)Double.parseDouble( dataId );
            }
            for(int i=0; i < listPoderes.beans.size(); i++){
            	GenericBean<Object> poder = listPoderes.get(i);
            	poder.setProperty("id_ep_fk",idEsc);
            	int idPoder = MngDataPoderes.insertOTORGA_PODER(poder);
            	poder.setProperty("id_opoder_ep_pk",idPoder);                         	
            	GenericDataBean listApoderados = gson.fromJson(request.getParameter("ListApoderados"+i), GenericDataBean.class);
            	for(int a=0; a < listApoderados.beans.size(); a++){
                	GenericBean<Object> apoder = listApoderados.get(a);
                	apoder.setProperty("id_opoder_ep_fk",idPoder);
                	apoder.setProperty("id_ep_fk",idEsc);       
                	//apoder.setProperty("id_empl_fk",);
                	//apoder.setProperty("desc_nom_empl",);
                	
                	String num_user_str = request.getParameter("ID_Usuario_Actual");
                	num_user_str=num_user_str.replace("\"", "");
                    int num_user = 0;
                    if(num_user_str != null && !num_user_str.equals("")){
                    	num_user = (int)Double.parseDouble( num_user_str );
                    }
                    apoder.setProperty("num_created_by", num_user);
                	int idApoder = MngDataPoderes.insertAPODERADO(apoder);
                	if((int)Double.parseDouble(apoder.getProperty("ind_status").toString())==2){
                		int ID_Apod = (int)Double.parseDouble(apoder.getProperty("id_apod_ep_pk").toString());
                		for(int r=0;r<listRevocados.beans.size();r++){
                			GenericBean<Object> rev = listRevocados.get(r);
                			if((int)Double.parseDouble(rev.getProperty("id_apod_ep_fk").toString())==ID_Apod){
                				rev.setProperty("id_opoder_ep_fk",idPoder);
                				rev.setProperty("id_ep_fk",idEsc);
                				rev.setProperty("id_apod_ep_fk",idApoder);
                				MngDataPoderes.insertREVOCA(rev);
                			}
                		}
                	}
                	apoder.setProperty("id_apod_ep_pk",idApoder);
            	}
            	try{
            		GenericDataBean listFacultades = gson.fromJson(request.getParameter("Facultades"+i), GenericDataBean.class);
            		for(int b=0; b < listFacultades.beans.size(); b++){
                    	GenericBean<Object> facult = listFacultades.get(b);
                    	facult.setProperty("id_opoder_ep_fk",idPoder);
                    	facult.setProperty("id_ep_fk",idEsc);       
                    	//apoder.setProperty("id_empl_fk",);
                    	//apoder.setProperty("desc_nom_empl",);
                    	facult.setProperty("num_created_by", request.getParameter("ID_Usuario_Actual"));
                    	int idFac = MngDataPoderes.insertFACULTADES(facult);
                    	
                    	boolean tieneMancomunados = false;
                    	String manco = facult.getProperty("MANCOMUNADO").toString();
                    	if(manco.equals("true")||manco.equals("false")){
                    		tieneMancomunados = Boolean.parseBoolean( manco);
                    	}else{
                    		int dmanco = (int)Double.parseDouble(manco);
                    		tieneMancomunados = dmanco == 1;
                    	}
                    	
                    	if(tieneMancomunados){
                    		GenericDataBean listMancomunados = gson.fromJson(request.getParameter("ListMancomunados"+i+"_"+b), GenericDataBean.class);
                    		for(int m=0; m<listMancomunados.beans.size();m++){
                    			GenericBean<Object> mancomunado = listMancomunados.get(m);
                            	mancomunado.setProperty("id_opoder_ep_fk",idPoder);
                            	mancomunado.setProperty("id_ep_fk",idEsc);  
                            	mancomunado.setProperty("ind_tipoapoderado",2);  
                            	mancomunado.setProperty("desc_tipoapoderado","MANCOMUNADO");  
                            	mancomunado.setProperty("id_empl_fk",mancomunado.getProperty("id"));
                            	mancomunado.setProperty("desc_nom_empl",mancomunado.getProperty("value"));
                            	mancomunado.setProperty("num_created_by", request.getParameter("ID_Usuario_Actual"));
                            	mancomunado.setProperty("des_grupo", "MANCOMUNADO");
                            	mancomunado.setProperty("id_grupo_fk", idFac);
                            	mancomunado.setProperty("ind_aprevoca", 0);
                            	mancomunado.setProperty("desc_revoca", "");
                            	mancomunado.setProperty("ind_status", 1);
                            	int idMancomunado = MngDataPoderes.insertAPODERADO(mancomunado);
                            	mancomunado.setProperty("id_apod_ep_pk",idMancomunado);
                    		}
                    	}
                    	facult.setProperty("id_fac_ep_pk",idFac);
                	}
            	}catch(Exception ex){
            		 logger.error(ex.getMessage());        
            	}
            }
            
            
            
            for(int i=0; i < listDocs.beans.size(); i++){
            	GenericBean<Object> doc = listDocs.get(i);
            	doc.setProperty("id_ep_fk",idEsc);
            	int idDoc = MngDataPoderes.insertDOCUMENTUMS(doc);
            	doc.setProperty("id_doc_ep_pk",idDoc);
            }
            
            String idEmp = headEsc.getProperty("id_empresa").toString();
            String tipo = headEsc.getProperty("ind_tipo_escritura").toString();
            return MngDataPoderes.queryESCRITURA_PODER(Integer.parseInt(idEmp), tipo);
	}
	
	public Object getDetailsEscritura(String action)throws Exception{
		List<Object> details = new ArrayList<Object>();
        int ID_Esc = Integer.parseInt(request.getParameter("ID_Escritura")); 
        
        details.add(MngDataPoderes.queryDOCUMENTUMS(ID_Esc));
        GenericDataBean op = MngDataPoderes.queryOTORGA_PODER(ID_Esc);
        
        //Revocar
       try{
	        for(int i=0; i < op.beans.size(); i++){
	        	GenericBean<Object> bean = op.get(i);
	        	int idpoder = (int)Double.parseDouble( bean.getProperty("id_opoder_ep_pk").toString() );
	        	GenericDataBean list = MngDataPoderes.queryAPODERADO(idpoder);        	
	        	bean.setProperty("Apoderados", list);	        	  
	        	GenericDataBean listFac = MngDataPoderes.queryFACULTADES(idpoder);       	
	        	bean.setProperty("Facultades", listFac);
	        	for(int f=0;f<listFac.beans.size();f++){
	        		GenericBean<Object> fac = listFac.get(f);
	        		if((int)Double.parseDouble(fac.getProperty("MANCOMUNADO").toString())!=1)
	        			continue;
	        		int idFac = (int)Double.parseDouble(fac.getProperty("id_fac_ep_pk").toString());
	        		GenericDataBean listMancomunados = MngDataPoderes.queryAPODERADO(idFac, 1); 
	        		fac.setProperty("listMancomunados",listMancomunados);
	        	}
	        	
	        }
        
        details.add(op);
        GenericDataBean rev = MngDataPoderes.queryREVOCA(ID_Esc);
        details.add(rev);
       }
       catch(Exception ex){
    	   logger.error(ex.getMessage());        
       }
        return details;
	}
	
	public Object getEscrituraPoder(String action)throws Exception{
		List<Object> escrituras = new ArrayList<Object>();
		int idEmpresa = Integer.parseInt(request.getParameter("ID_Empresa"));
		String tipoDeEscritura = request.getParameter("Tipo_De_Escritura");
		String paramDeBusqueda = request.getParameter("Busqueda");
		escrituras.add(MngDataPoderes.queryESCRITURA_PODER(idEmpresa, tipoDeEscritura, paramDeBusqueda));
		return escrituras;
	}
	
	public Object consultarEscrituras(String action)throws Exception{
		List<Object> escrituras = new ArrayList<Object>();
		int idEmpresa = Integer.parseInt(request.getParameter("ID_Empresa"));
		String tipoDeEscritura = request.getParameter("Tipo_De_Escritura");
		String paramDeBusqueda = request.getParameter("Busqueda");
		
		switch(tipoDeEscritura){
			case "PG": escrituras.add(MngDataPoderes.query_PODERES_GENERALES_PR(idEmpresa, paramDeBusqueda)); break;
			case "PE": escrituras.add(MngDataPoderes.query_PODERES_ESPECIALES_PR(idEmpresa, paramDeBusqueda )); break;
			case "CP": escrituras.add(MngDataPoderes.query_PODERES_CARTA_PODER_PR(idEmpresa, paramDeBusqueda )); break;			 			
			case "ER": escrituras.add(MngDataPoderes.query_REVOCACIONES_PR(idEmpresa, paramDeBusqueda )); break;
		}
				
		return escrituras;
	}
	
		
	public Object deleteEscrituraPoder(String action)throws Exception{		
		int idEscrituraPoder =  Integer.parseInt(request.getParameter("ID_Escritura_Poder"));
		int idUsuarioActual =  Integer.parseInt(request.getParameter("ID_Usuario_Actual"));	
		int idEmpresa = Integer.parseInt(request.getParameter("ID_Empresa"));
		String tipoDeEscritura = request.getParameter("Tipo_De_Escritura");
		Object resultado = MngDataPoderes.deleteESCRITURA_PODER(idEscrituraPoder, idUsuarioActual);
		MngDataPoderes.deleteOTORGA_PODER(idEscrituraPoder, idUsuarioActual);
		MngDataPoderes.deleteAPODERADO(idEscrituraPoder, idUsuarioActual);
		MngDataPoderes.deleteDOCUMENTUMS(idEscrituraPoder, idUsuarioActual);
		return resultado; 
	}
	
	public Object addDocumentums(String action)throws Exception{
		//try(Reader reader = new InputStreamReader(JsonToJava.class.getResourceAsStream("/Server1.json"), "UTF-8")){
            Gson gson = new GsonBuilder().create();
            
            GenericDataBean listDocs = gson.fromJson(request.getParameter("ListDocumentos"), GenericDataBean.class);
            List<Integer> docsIds = new ArrayList<Integer>(); 
            
            for(int i=0; i < listDocs.beans.size(); i++){
            	GenericBean bean = listDocs.get(i);
            	int idDoc = MngDataPoderes.insertDOCUMENTUMS(bean);
            	docsIds.add(idDoc);
            }
            
            return docsIds;		
	}
	
	
	
	public Object getDocumentums(String action)throws Exception{		
            int ID_Esc = Integer.parseInt(request.getParameter("ID_Escritura")); 
            return MngDataPoderes.queryDOCUMENTUMS(ID_Esc);	
	}
	
	public Object addOtorgaPoder(String action)throws Exception{
		//try(Reader reader = new InputStreamReader(JsonToJava.class.getResourceAsStream("/Server1.json"), "UTF-8")){
            Gson gson = new GsonBuilder().create();
            
            GenericDataBean listPoderes = gson.fromJson(request.getParameter("ListPoderes"), GenericDataBean.class);
            List<Integer> poderesIds = new ArrayList<Integer>(); 
            
            for(int i=0; i < listPoderes.beans.size(); i++){
            	GenericBean bean = listPoderes.get(i);
            	int idPoder = MngDataPoderes.insertOTORGA_PODER(bean);
            	poderesIds.add(idPoder);
            }
            
            return poderesIds;		
	}
	
	public Object getApoderados(String action)throws Exception{		
        int ID_Esc = Integer.parseInt(request.getParameter("ID_Escritura")); 
        return MngDataPoderes.queryOTORGA_PODER(ID_Esc);	
	}
	
	public Object addApoderado(String action)throws Exception{
		//try(Reader reader = new InputStreamReader(JsonToJava.class.getResourceAsStream("/Server1.json"), "UTF-8")){
            Gson gson = new GsonBuilder().create();
            
            GenericDataBean listApoderados = gson.fromJson(request.getParameter("ListApoderados"), GenericDataBean.class);
            List<Integer> apoderesIds = new ArrayList<Integer>(); 
            
            for(int i=0; i < listApoderados.beans.size(); i++){
            	GenericBean bean = listApoderados.get(i);
            	int idApoder = MngDataPoderes.insertAPODERADO(bean);
            	apoderesIds.add(idApoder);
            }
            
            return apoderesIds;		
	}
	
	public Object getOtorgaPoder(String action)throws Exception{		
        int ID_Esc = Integer.parseInt(request.getParameter("ID_Escritura")); 
        return MngDataPoderes.queryOTORGA_PODER(ID_Esc);	
	}
	
	public Object toJsonTest(String action){
		//try(Reader reader = new InputStreamReader(JsonToJava.class.getResourceAsStream("/Server1.json"), "UTF-8")){
            Gson gson = new GsonBuilder().create();
            GenericDataBean p = gson.fromJson(request.getParameter("Bean"), GenericDataBean.class);
            
            return p;		
	}
	/**
	 * 
	 * @param action
	 * @return
	 * @throws Exception
	 * 
	 * @AJAX 
	 * 		int Id Identificador del catalogo a consultar
	 * 		string action="getCATALOGOS"
	 * 		return JSON lista de elementos del catalogo
	 */
	
	public Object getCATALOGOS(String action) throws Exception{		
		int id = Integer.parseInt( request.getParameter("Id") );
		return MngDataPoderes.queryCATALOGOS(id);		
	}
	
	public Object cargarHtmlConsultaPoderes(String action) throws Exception{		
		String htmlPG = request.getParameter("htmlPG")==null?"":request.getParameter("htmlPG");
		htmlPG = URLDecoder.decode(htmlPG);
		String htmlPE = request.getParameter("htmlPE")==null?"":request.getParameter("htmlPE");
		htmlPE = URLDecoder.decode(htmlPE);
		String htmlCP = request.getParameter("htmlCP")==null?"":request.getParameter("htmlCP");
		htmlCP = URLDecoder.decode(htmlCP);
		String htmlER = request.getParameter("htmlER")==null?"":request.getParameter("htmlER");
		htmlER = URLDecoder.decode(htmlER);
		ServletContext aplicacion= request.getServletContext();
		if(htmlPG!=null)
			aplicacion.setAttribute("htmlPG", htmlPG);		
		if(htmlPE!=null)
			aplicacion.setAttribute("htmlPE", htmlPE);
		if(htmlCP!=null)
			aplicacion.setAttribute("htmlCP", htmlCP);			
		if(htmlER!=null)
			aplicacion.setAttribute("htmlER", htmlER);
		return new Object(); 
	}
	
	public Object cargarHtmlDetallePoderes(String action) throws Exception{		
		String htmlDetalle = request.getParameter("htmlDetalle")==null?"":request.getParameter("htmlDetalle");
		htmlDetalle = URLDecoder.decode(htmlDetalle);
		ServletContext aplicacion= request.getServletContext();
		aplicacion.removeAttribute("htmlDetalle");		
		aplicacion.setAttribute("htmlDetalle", htmlDetalle);				
		return new Object();
	}	
	
	public Object execProcess(String action) throws Exception{		
		MngDataPoderes.execCHANGE_STATUS_VIGENCIA();				
		return new Object();
	}
	
}