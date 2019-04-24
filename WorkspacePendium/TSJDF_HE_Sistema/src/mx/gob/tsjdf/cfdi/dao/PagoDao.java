package mx.gob.tsjdf.cfdi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import mx.gob.tsjdf.cfdi.bo.RecibosBo;
import mx.gob.tsjdf.cfdi.dto.CampoRubroDto;
import mx.gob.tsjdf.cfdi.dto.DescripcionPagoDto;
import mx.gob.tsjdf.cfdi.dto.PercepcionDeduccionDto;
import mx.gob.tsjdf.cfdi.dto.ReciboDto;
import mx.gob.tsjdf.db.ConnectionDB;

public class PagoDao
{
	Connection   conn ;
	ConnectionDB connectionDB;
	
  public HashMap<String, CampoRubroDto> llenaListasCampos(String tipoNomina){
	  
    HashMap<String, CampoRubroDto> hmapCampos = new HashMap<>();
    connectionDB = new ConnectionDB();
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    try{
    	if(tipoNomina.equals("tribunal")){
    		conn = connectionDB.getConexionCFDI();
    	}if(tipoNomina.equals("consejo")){
    		conn = connectionDB.getConexionConsejoCFDI();
    	}
    	
      if (conn != null){
        pstmt = conn.prepareStatement(Queries.getQueryCamposRubros());
        rs = pstmt.executeQuery();
        while (rs.next())
        {
          CampoRubroDto campoRubroDto = new CampoRubroDto();
          
          campoRubroDto.setDescripcionTsjdf(rs.getString("cme_etiqueta"));
          
          campoRubroDto.setTipoCampo(rs.getInt("cme_id_columna"));
          
          campoRubroDto.setTabla(rs.getString("table_name"));
          
          campoRubroDto.setColumnName(rs.getString("column_name"));
          
          campoRubroDto.setPerceptionClaveSat(rs.getString("p_clave_sat"));
          
          campoRubroDto.setPerceptionDescripcionSat(rs.getString("p_descripcion"));
          
          campoRubroDto.setDeduccionClaveSat(rs.getString("d_clave_sat"));
          
          campoRubroDto.setDeduccionDescripcionSat(rs.getString("d_descripcion"));
          
          campoRubroDto.setExento(rs.getInt("exento"));
          
          campoRubroDto.setTablaExento(rs.getString("tabla_exento"));
          
          campoRubroDto.setCampoExento(rs.getString("campo_exento"));
          
          campoRubroDto.setTablaGravable(rs.getString("tabla_gravable"));
          
          campoRubroDto.setCampoGravable(rs.getString("campo_gravable"));
          
          campoRubroDto.setDespliega(rs.getString("despliega"));
          
          campoRubroDto.setDespliegaConCero(rs.getString("despliega_con_0"));
          
          hmapCampos.put(rs.getString("column_name"), campoRubroDto);
        }
        hmapCampos.put("SME_DIAS_ALTA", new CampoRubroDto(0, "M4SME_AC_HR_PERIOD"));
        hmapCampos.put("SME_NUM_HE_DOB", new CampoRubroDto(0, "M4SME_AC_HR_PERIOD"));
        hmapCampos.put("SME_NUM_HE_TRIP", new CampoRubroDto(0, "M4SME_AC_HR_PERIOD"));
        
        hmapCampos.put("SME_TOTAL_DEVENGOS", new CampoRubroDto(0, "M4SME_AC_HR_PERIOD"));
        hmapCampos.put("SCO_TOT_DEDUCTIONS", new CampoRubroDto(0, "M4SCO_AC_HR_PERIOD"));
        hmapCampos.put("SCO_NET", new CampoRubroDto(0, "M4SCO_AC_HR_PERIOD"));
        
        rs.close();
        pstmt.close();
        conn.close();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      if (rs != null) {
        try
        {
          rs.close();
        }
        catch (Exception localException1) {}
      }
      if (pstmt != null) {
        try
        {
          pstmt.close();
        }
        catch (Exception localException2) {}
      }
      if (conn != null) {
        try
        {
          conn.close();
        }
        catch (Exception localException3) {}
      }
    }
    finally
    {
      if (rs != null) {
        try
        {
          rs.close();
        }
        catch (Exception localException4) {}
      }
      if (pstmt != null) {
        try
        {
          pstmt.close();
        }
        catch (Exception localException5) {}
      }
      if (conn != null) {
        try
        {
          conn.close();
        }
        catch (Exception localException6) {}
      }
    }
    return hmapCampos;
  }
  
  public void llenaConceptosRecibo(ReciboDto reciboDto, String query, String fechaPaga, HashMap<String, CampoRubroDto> hmap,String tipoNomina)
  {
    
    connectionDB = new ConnectionDB();    
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    int numRows = 0;
    try{
    	if(tipoNomina.equals("tribunal")){
    		conn = connectionDB.getConexionCFDI();
    	}if(tipoNomina.equals("consejo")){
    		conn = connectionDB.getConexionConsejoCFDI();
    	}
      if (conn != null)
      {
        pstmt = conn.prepareStatement(query);
        

        pstmt.setString(1, reciboDto.getNumeroEmpleado());
        pstmt.setInt(2, reciboDto.getIdPeriodo());
        pstmt.setString(3, fechaPaga);
        

        rs = pstmt.executeQuery();
        while (rs.next())
        {
        	Iterator it = hmap.entrySet().iterator();
          while (it.hasNext())
          {
        	  Map.Entry e = (Map.Entry)it.next();
            
            CampoRubroDto campoRubroDto = (CampoRubroDto)e.getValue();
            
            String campoDB = (String)e.getKey();
            Double importe = Double.valueOf(rs.getDouble(campoDB));
            if ((campoRubroDto.getTipoCampo() > 0) && ((importe.doubleValue() != 0.0D) || ((campoRubroDto.getDespliegaConCero() != null) && (campoRubroDto.getDespliegaConCero().equals("S")))))
            {
              PercepcionDeduccionDto pdDto = new PercepcionDeduccionDto();
              
              pdDto.setClaveTsjdf(RecibosBo.getClave(campoRubroDto.getDescripcionTsjdf()));
              
              pdDto.setDescripcionTsjdf(RecibosBo.getDescripcion(campoRubroDto.getDescripcionTsjdf()));
              
              pdDto.setExento(campoRubroDto.getExento());
              
              pdDto.setImporte(importe);
              
              pdDto.setDespliega(campoRubroDto.getDespliega());
              
              boolean despliegaConCero = campoRubroDto.getDespliegaConCero().equals("S");
              pdDto.setDespliegaConCero(despliegaConCero);
              if (campoRubroDto.getTipoCampo() == 4)
              {
                if (reciboDto.getHmapPercepciones().containsKey(campoDB))
                {
                  ((PercepcionDeduccionDto)reciboDto.getHmapPercepciones().get(campoDB)).setImporte(Double.valueOf(RecibosBo.Redondear(
                    ((PercepcionDeduccionDto)reciboDto.getHmapPercepciones().get(campoDB)).getImporte().doubleValue() + importe.doubleValue())));
                  if (campoRubroDto.getCampoExento() != null) {
                    ((PercepcionDeduccionDto)reciboDto.getHmapPercepciones().get(campoDB)).setImporteExento(Double.valueOf(RecibosBo.Redondear(
                      ((PercepcionDeduccionDto)reciboDto.getHmapPercepciones().get(campoDB)).getImporteExento().doubleValue() + rs.getDouble(campoRubroDto.getCampoExento()))));
                  }
                  if (campoRubroDto.getCampoGravable() != null) {
                    ((PercepcionDeduccionDto)reciboDto.getHmapPercepciones().get(campoDB)).setImporteGravado(Double.valueOf(RecibosBo.Redondear(
                      ((PercepcionDeduccionDto)reciboDto.getHmapPercepciones().get(campoDB)).getImporteGravado().doubleValue() + rs.getDouble(campoRubroDto.getCampoGravable()))));
                  }
                }
                else
                {
                  if (campoRubroDto.getCampoExento() != null) {
                    pdDto.setImporteExento(Double.valueOf(rs.getDouble(campoRubroDto.getCampoExento())));
                  }
                  if (campoRubroDto.getCampoGravable() != null) {
                    pdDto.setImporteGravado(Double.valueOf(rs.getDouble(campoRubroDto.getCampoGravable())));
                  }
                  pdDto.setClaveSat(campoRubroDto.getPerceptionClaveSat());
                  pdDto.setDescripcionSat(campoRubroDto.getPerceptionDescripcionSat());
                  reciboDto.getHmapPercepciones().put(campoDB, pdDto);
                }
              }
              else if (campoRubroDto.getTipoCampo() == 5) {
                if (reciboDto.getHmapDeducciones().containsKey(campoDB))
                {
                  ((PercepcionDeduccionDto)reciboDto.getHmapDeducciones().get(campoDB)).setImporte(Double.valueOf(RecibosBo.Redondear(
                    ((PercepcionDeduccionDto)reciboDto.getHmapDeducciones().get(campoDB)).getImporte().doubleValue() + importe.doubleValue())));
                  if (campoRubroDto.getCampoExento() != null) {
                    ((PercepcionDeduccionDto)reciboDto.getHmapDeducciones().get(campoDB)).setImporteExento(Double.valueOf(RecibosBo.Redondear(
                      ((PercepcionDeduccionDto)reciboDto.getHmapDeducciones().get(campoDB)).getImporteExento().doubleValue() + rs.getDouble(campoRubroDto.getCampoExento()))));
                  }
                  if (campoRubroDto.getCampoGravable() != null) {
                    ((PercepcionDeduccionDto)reciboDto.getHmapDeducciones().get(campoDB)).setImporteGravado(Double.valueOf(RecibosBo.Redondear(
                      ((PercepcionDeduccionDto)reciboDto.getHmapDeducciones().get(campoDB)).getImporteGravado().doubleValue() + rs.getDouble(campoRubroDto.getCampoGravable()))));
                  }
                }
                else
                {
                  if (campoRubroDto.getCampoExento() != null) {
                    pdDto.setImporteExento(Double.valueOf(rs.getDouble(campoRubroDto.getCampoExento())));
                  }
                  if (campoRubroDto.getCampoGravable() != null) {
                    pdDto.setImporteGravado(Double.valueOf(rs.getDouble(campoRubroDto.getCampoGravable())));
                  }
                  pdDto.setClaveSat(campoRubroDto.getDeduccionClaveSat());
                  pdDto.setDescripcionSat(campoRubroDto.getDeduccionDescripcionSat());
                  reciboDto.getHmapDeducciones().put(campoDB, pdDto);
                }
              }
            }
            else if ((importe.doubleValue() != 0.0D) && (campoRubroDto.getTipoCampo() == 0))
            {
              if (((String)e.getKey()).toString().equals("SME_DIAS_ALTA")) {
                reciboDto.setDiasPagados(reciboDto.getDiasPagados() + importe.doubleValue());
              }
              if (((String)e.getKey()).toString().equals("SME_NUM_HE_DOB")) {
                reciboDto.setHorasExtraDobles(reciboDto.getHorasExtraDobles() + importe.doubleValue());
              }
              if (((String)e.getKey()).toString().equals("SME_NUM_HE_TRIP")) {
                reciboDto.setHorasExtraTriples(reciboDto.getHorasExtraTriples() + importe.doubleValue());
              }
              if (((String)e.getKey()).toString().equals("SME_TOTAL_DEVENGOS")) {
                reciboDto.setTotalPercepciones(RecibosBo.Redondear(reciboDto.getTotalPercepciones() + importe.doubleValue()));
              }
              if (((String)e.getKey()).toString().equals("SCO_TOT_DEDUCTIONS")) {
                reciboDto.setTotalDescuentos(RecibosBo.Redondear(reciboDto.getTotalDescuentos() + importe.doubleValue()));
              }
              if (((String)e.getKey()).toString().equals("SCO_NET")) {
                reciboDto.setLiquidoCobrar(RecibosBo.Redondear(reciboDto.getLiquidoCobrar() + importe.doubleValue()));
              }
            }
          }
          numRows++;
          if (numRows == 1) {
            reciboDto.setFechaInicioPeriodo(rs.getString("fecha_inicio"));
          }
          reciboDto.setFechaFinPeriodo(rs.getString("fecha_fin"));
        }
        rs.close();
        pstmt.close();
        conn.close();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      if (rs != null) {
        try
        {
          rs.close();
        }
        catch (Exception localException1) {}
      }
      if (pstmt != null) {
        try
        {
          pstmt.close();
        }
        catch (Exception localException2) {}
      }
      if (conn != null) {
        try
        {
          conn.close();
        }
        catch (Exception localException3) {}
      }
    }
    finally
    {
      if (rs != null) {
        try
        {
          rs.close();
        }
        catch (Exception localException4) {}
      }
      if (pstmt != null) {
        try
        {
          pstmt.close();
        }
        catch (Exception localException5) {}
      }
      if (conn != null) {
        try
        {
          conn.close();
        }
        catch (Exception localException6) {}
      }
    }
  }
  
  public void llenaDatosGenerales(ReciboDto reciboDto, String fechaInicial, String fechaPaga, String metodoPago,String tipoNomina)
  {
	connectionDB = new ConnectionDB();
    
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try{
    	if(tipoNomina.equals("tribunal")){
    		conn = connectionDB.getConexionCFDI();
    	}if(tipoNomina.equals("consejo")){
    		conn = connectionDB.getConexionConsejoCFDI();
    	}
      if (conn != null)
      {
        pstmt = conn.prepareStatement(Queries.getQueryDatosGenerales());
        
        pstmt.setInt(1, reciboDto.getIdPeriodo());
        pstmt.setString(2, fechaPaga);
        pstmt.setInt(3, reciboDto.getIdPeriodo());
        pstmt.setString(4, fechaPaga);
        pstmt.setString(5, fechaPaga);
        pstmt.setString(6, fechaPaga);
        pstmt.setString(7, fechaPaga);
        pstmt.setString(8, fechaInicial);
        pstmt.setString(9, reciboDto.getNumeroEmpleado());
        
        rs = pstmt.executeQuery();
        while (rs.next())
        {
          reciboDto.setNumeroEmpleado(rs.getString("num_empleado"));
          
          reciboDto.setNombreCompleto(rs.getString("a_paterno") + " " + rs.getString("a_materno") + " " + rs.getString("nombre"));
          
          reciboDto.setCurp(rs.getString("curp"));
          
          reciboDto.setRfc(rs.getString("rfc"));
          
          reciboDto.setNumeroPlaza(rs.getString("num_plaza"));
          
          reciboDto.setNivelSalarial(rs.getString("nivel_salarial"));
          
          reciboDto.setPuesto(rs.getString("ult_puesto"));
          
          reciboDto.setSeccionSindical(rs.getString("secc_sindicato"));
          
          reciboDto.setNumArea(rs.getString("area"));
          
          reciboDto.setCodigo(RecibosBo.getCodigo(rs.getString("codigo")));
          
          reciboDto.setUniverso(RecibosBo.getUniverso(rs.getString("codigo")));
          
          reciboDto.setZp(rs.getString("zp"));
          
          reciboDto.setMensaje(rs.getString("CME_MENSAJE2") + " " + rs.getString("CME_MENSAJE3") + " " + rs.getString("CME_MENSAJE4") + " " + rs.getString("CME_MENSAJE5"));
          

          reciboDto.setCalle(rs.getString("calle"));
          
          reciboDto.setNumeroExterior(rs.getString("num_ext"));
          
          reciboDto.setNumeroInterior(rs.getString("piso") + " " + rs.getString("departamento"));
          
          reciboDto.setColonia(rs.getString("colonia"));
          
          reciboDto.setDelegacionMunicipio(rs.getString("municipio"));
          
          reciboDto.setEstado(rs.getString("estado"));
          
          reciboDto.setCodigoPostal(rs.getString("cp"));
          
          reciboDto.setClaveSatBanco(rs.getString("cve_sat"));
          
          reciboDto.setCuentaUltimosDig(RecibosBo.truncCuenta(rs.getString("sco_account_number")));
          if ((metodoPago == null) || (metodoPago.isEmpty())) {
            reciboDto.setMetodoPago(rs.getString("metodo_pago"));
          } else {
            reciboDto.setMetodoPago(metodoPago);
          }
          reciboDto.setDescripcionArea(rs.getString("descripcion_area"));
        }
        rs.close();
        pstmt.close();
        conn.close();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      if (rs != null) {
        try
        {
          rs.close();
        }
        catch (Exception localException1) {}
      }
      if (pstmt != null) {
        try
        {
          pstmt.close();
        }
        catch (Exception localException2) {}
      }
      if (conn != null) {
        try
        {
          conn.close();
        }
        catch (Exception localException3) {}
      }
    }
    finally
    {
      if (rs != null) {
        try
        {
          rs.close();
        }
        catch (Exception localException4) {}
      }
      if (pstmt != null) {
        try
        {
          pstmt.close();
        }
        catch (Exception localException5) {}
      }
      if (conn != null) {
        try
        {
          conn.close();
        }
        catch (Exception localException6) {}
      }
    }
  }
  
  public ArrayList<Integer> consultaPeriodosEmpleado(String numeroEmpleado, String fechaPago,String tipoNomina){
	  connectionDB = new ConnectionDB();
	  
      PreparedStatement pstmt = null;
      ResultSet rs = null;
    
    ArrayList<Integer> listaPeriodos = new ArrayList<>();
    try{
    	if(tipoNomina.equals("tribunal")){
    		conn = connectionDB.getConexionCFDI();
    	}if(tipoNomina.equals("consejo")){
    		conn = connectionDB.getConexionConsejoCFDI();
    	}
      if (conn != null)
      {
        pstmt = conn.prepareStatement(Queries.getQueryPeriodosEmpleado());
        
        pstmt.setString(1, numeroEmpleado);
        pstmt.setString(2, fechaPago);
        
        rs = pstmt.executeQuery();
        while (rs.next()) {
          listaPeriodos.add(Integer.valueOf(rs.getInt("sco_or_hr_period")));
        }
        rs.close();
        pstmt.close();
        conn.close();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      if (rs != null) {
        try
        {
          rs.close();
        }
        catch (Exception localException1) {}
      }
      if (pstmt != null) {
        try
        {
          pstmt.close();
        }
        catch (Exception localException2) {}
      }
      if (conn != null) {
        try
        {
          conn.close();
        }
        catch (Exception localException3) {}
      }
    }
    finally
    {
      if (rs != null) {
        try
        {
          rs.close();
        }
        catch (Exception localException4) {}
      }
      if (pstmt != null) {
        try
        {
          pstmt.close();
        }
        catch (Exception localException5) {}
      }
      if (conn != null) {
        try
        {
          conn.close();
        }
        catch (Exception localException6) {}
      }
    }
    return listaPeriodos;
  }
  
  public ArrayList<String> consultaEmpleadosPorPeriodo(String fechaPaga,String tipoNomina)
  {
	connectionDB = new ConnectionDB();
	
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    ArrayList<String> listaEmpleados = new ArrayList<>();
    try {
    	if(tipoNomina.equals("tribunal")){
    		conn = connectionDB.getConexionCFDI();
    	}if(tipoNomina.equals("consejo")){
    		conn = connectionDB.getConexionConsejoCFDI();
    	}
      if (conn != null)
      {
        pstmt = conn.prepareStatement(Queries.getQueryEmpleadosNomina());
        
        pstmt.setString(1, fechaPaga);
        
        rs = pstmt.executeQuery();
        while (rs.next()) {
          listaEmpleados.add(rs.getString("sco_id_hr"));
        }
        rs.close();
        pstmt.close();
        conn.close();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      if (rs != null) {
        try
        {
          rs.close();
        }
        catch (Exception localException1) {}
      }
      if (pstmt != null) {
        try
        {
          pstmt.close();
        }
        catch (Exception localException2) {}
      }
      if (conn != null) {
        try
        {
          conn.close();
        }
        catch (Exception localException3) {}
      }
    }
    finally
    {
      if (rs != null) {
        try
        {
          rs.close();
        }
        catch (Exception localException4) {}
      }
      if (pstmt != null) {
        try
        {
          pstmt.close();
        }
        catch (Exception localException5) {}
      }
      if (conn != null) {
        try
        {
          conn.close();
        }
        catch (Exception localException6) {}
      }
    }
    return listaEmpleados;
  }
  
  public ArrayList<DescripcionPagoDto> consultarDescripcionesPago(String anio,String tipoNomina)
  {
    ArrayList<DescripcionPagoDto> listaDescripcionesPago = new ArrayList<>();
    
    connectionDB = new ConnectionDB();
    
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try{
    	if(tipoNomina.equals("tribunal")){
    		conn = connectionDB.getConexionCFDI();
    	}if(tipoNomina.equals("consejo")){
    		conn = connectionDB.getConexionConsejoCFDI();
    	}
      if (conn != null)
      {
        pstmt = conn.prepareStatement(Queries.getQueryDescripcionPagos());
        
        pstmt.setString(1, anio);
        
        rs = pstmt.executeQuery();
        while (rs.next())
        {
          DescripcionPagoDto dto = new DescripcionPagoDto();
          
          dto.setFechaInicial(rs.getString(1));
          
          dto.setFechaFinal(rs.getString(2));
          
          dto.setFechaPaga(rs.getString(3));
          
          dto.setDiasPago(rs.getInt(4));
          
          dto.setDescripcion(rs.getString(5));
          
          listaDescripcionesPago.add(dto);
        }
        rs.close();
        pstmt.close();
        conn.close();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      if (rs != null) {
        try
        {
          rs.close();
        }
        catch (Exception localException1) {}
      }
      if (pstmt != null) {
        try
        {
          pstmt.close();
        }
        catch (Exception localException2) {}
      }
      if (conn != null) {
        try
        {
          conn.close();
        }
        catch (Exception localException3) {}
      }
    }
    finally
    {
      if (rs != null) {
        try
        {
          rs.close();
        }
        catch (Exception localException4) {}
      }
      if (pstmt != null) {
        try
        {
          pstmt.close();
        }
        catch (Exception localException5) {}
      }
      if (conn != null) {
        try
        {
          conn.close();
        }
        catch (Exception localException6) {}
      }
    }
    return listaDescripcionesPago;
  }
  
  public int consultaTotalRecibos(String fechaPaga,String tipoNomina)
  {
    int totalRecibos = 0;
    
    connectionDB = new ConnectionDB();
    
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try{
    	if(tipoNomina.equals("tribunal")){
    		conn = connectionDB.getConexionCFDI();
    	}if(tipoNomina.equals("consejo")){
    		conn = connectionDB.getConexionConsejoCFDI();
    	}
      if (conn != null)
      {
        pstmt = conn.prepareStatement(Queries.getQueryTotalRecibos());
        
        pstmt.setString(1, fechaPaga);
        
        rs = pstmt.executeQuery();
        while (rs.next()) {
          totalRecibos = rs.getInt(1);
        }
        rs.close();
        pstmt.close();
        conn.close();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      if (rs != null) {
        try
        {
          rs.close();
        }
        catch (Exception localException1) {}
      }
      if (pstmt != null) {
        try
        {
          pstmt.close();
        }
        catch (Exception localException2) {}
      }
      if (conn != null) {
        try
        {
          conn.close();
        }
        catch (Exception localException3) {}
      }
    }
    finally
    {
      if (rs != null) {
        try
        {
          rs.close();
        }
        catch (Exception localException4) {}
      }
      if (pstmt != null) {
        try
        {
          pstmt.close();
        }
        catch (Exception localException5) {}
      }
      if (conn != null) {
        try
        {
          conn.close();
        }
        catch (Exception localException6) {}
      }
    }
    return totalRecibos;
  }
  
  public DescripcionPagoDto consultaFechaEjecucionYdescripcionPago(String fechaPaga,String tipoNomina)
  {
    DescripcionPagoDto dto = new DescripcionPagoDto();
    
    connectionDB = new ConnectionDB();
    
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try{
    	if(tipoNomina.equals("tribunal")){
    		conn = connectionDB.getConexionCFDI();
    	}if(tipoNomina.equals("consejo")){
    		conn = connectionDB.getConexionConsejoCFDI();
    	}
      if (conn != null)
      {
        pstmt = conn.prepareStatement(Queries.getQueryFechaEjecucion());
        
        pstmt.setString(1, fechaPaga);
        
        rs = pstmt.executeQuery();
        while (rs.next())
        {
          dto.setFechaEjecucion(rs.getString("fecha_ejecucion"));
          
          dto.setDescripcion(rs.getString("descripcion"));
        }
        rs.close();
        pstmt.close();
        conn.close();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      if (rs != null) {
        try
        {
          rs.close();
        }
        catch (Exception localException1) {}
      }
      if (pstmt != null) {
        try
        {
          pstmt.close();
        }
        catch (Exception localException2) {}
      }
      if (conn != null) {
        try
        {
          conn.close();
        }
        catch (Exception localException3) {}
      }
    }
    finally
    {
      if (rs != null) {
        try
        {
          rs.close();
        }
        catch (Exception localException4) {}
      }
      if (pstmt != null) {
        try
        {
          pstmt.close();
        }
        catch (Exception localException5) {}
      }
      if (conn != null) {
        try
        {
          conn.close();
        }
        catch (Exception localException6) {}
      }
    }
    return dto;
  }
}
