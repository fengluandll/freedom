package mx.gob.tsjdf.cfdi.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import mx.gob.tsjdf.cfdi.dto.CampoRubroDto;

public class Queries
{
  private static final String QUERY_CAMPOS_RUBROS = "select  c2.cme_etiqueta, c1.cme_id_columna, i.table_name, i.column_name ,                 p.cve_sat p_clave_sat, p.descripcion p_descripcion, d.cve_sat d_clave_sat, d.descripcion d_descripcion, e.exento, \t\t\t\t    e.tabla_exento, campo_exento, e.tabla_gravable, campo_gravable , e.despliega , despliega_con_0  from information_schema.columns i  inner join M4CME_COLUMNAS c1     on i.column_name = c1.id_item     and c1.cme_id_columna in (4,5)     and c1.id_item is not null     and c1.cme_id_reporte = 001  inner join M4CME_COLUMNAS c2     on  c1.cme_id_fila = c2.cme_id_fila     and c1.cme_id_reporte = c2.cme_id_reporte     and c2.cme_etiqueta is not null     and c2.CME_ID_FILA not in (1200,9000,9100)  left join pnetcfdi.dbo.EQUIVALENCIA_TSJDF_SAT e    on e.cve_tsjdf = RTRIM(SUBSTRING(c2.cme_etiqueta,1, CHARINDEX(' ', c2.cme_etiqueta ) - 1))  left join pnetcfdi.dbo.sat_percepciones p     on p.cve_sat = e.cve_sat    and c1.cme_id_columna = e.tipo     and e.tipo = 4  left join pnetcfdi.dbo.sat_deducciones d     on d.cve_sat = e.cve_sat     and c1.cme_id_columna = e.tipo     and e.tipo = 5  where (i.table_name like 'M4SME_AC_HR_PERIOD' or i.table_name like 'M4CME_AC_HR_PERIOD')  and i.data_type like 'numeric' ";
  private static final String QUERY_DATOS_GENERALES = "select distinct sme.SCO_DT_START_SLICE, cme.SCO_ID_HR num_empleado, p.std_n_fam_name_1 a_paterno,           p.std_n_maiden_name a_materno,           p.std_n_first_name nombre, p.std_ssn curp, p.std_ss_number rfc,           cme.CME_ID_POSITION num_plaza, sme.SAR_ID_CONVENIO nivel_salarial, pos.sco_nm_positionesp ult_puesto,           cme.CME_ID_SEC_SIND secc_sindicato, cme.CME_ID_WUNIT area,           conv.cme_clv_puesto codigo , sme.SME_ID_WORK_CENTER zp,           CME_MENSAJE2, CME_MENSAJE3, CME_MENSAJE4, CME_MENSAJE5,           d.STD_ADDRESS_LINE_1 calle, d.ssp_num_via num_ext, d.std_address_line_2 piso, d.std_address_line_3 departamento,           col.std_n_geo_placesp colonia, mun.std_n_sub_geo_esp municipio, edo.std_n_geo_divesp estado, d.std_zip_code cp, \t\t\t b.cve_sat, pb.sco_account_number,          catfp.soc_nm_paym_typesp metodo_pago, ca.std_n_work_unitesp descripcion_area from STD_PERSON P       inner join M4CME_AC_HR_PERIOD cme              on cme.SCO_ID_HR = p.std_id_PERSON              and cme.SCO_OR_HR_PERIOD = ?              and cme.SCO_DT_PAY = convert(datetime, ? , 103)       inner join M4SME_AC_HR_PERIOD sme              on sme.SCO_ID_HR = p.std_id_PERSON              and sme.SCO_OR_HR_PERIOD = ?              and sme.SCO_DT_PAY = convert(datetime, ? , 103)              and sme.SCO_DT_START_SLICE = cme.SCO_DT_START_SLICE        left join M4SCO_POSITION pos \t\t\t\ton pos.sco_id_position = cme.CME_ID_POSITION        left join m4cme_h_job_conv conv              on conv.sar_id_convenio = sme.SAR_ID_CONVENIO \t\t\t    and conv.std_id_job_code = sme.SCO_ID_JOB_CODE       left join std_address d              on d.std_id_person = p.std_id_PERSON              and std_dt_end > convert(datetime, ? , 103)              and std_dt_start < convert(datetime, ? , 103)       left join std_geo_place col           \ton col.std_id_geo_place = d.std_id_geo_place              and col.std_id_sub_geo_div = d.std_id_sub_geo_div       left join STD_SUB_GEO_DIV mun              on mun.std_id_sub_geo_div = d.std_id_sub_geo_div \t\t left join std_geo_div edo              on edo.STD_ID_COUNTRY = d.STD_ID_COUNTRY              and edo.STD_ID_GEO_DIV = d.std_id_geo_div \t\t left join m4sco_person_bank pb              on pb.sco_id_person = p.std_id_PERSON              and pb.sco_or_account = (select max(sco_or_account) from m4sco_person_bank where sco_id_person = p.std_id_PERSON )       left join pnetcfdi.dbo.EQUIVALENCIA_BANCOS b              on b.CVE_TSJDF = pb.sco_id_bank_branch \t\t left join M4SCO_PAYMENT_DATA fp              on fp.SCO_ID_HR = p.std_id_PERSON              and fp.SCO_OR_HR_PERIOD = cme.SCO_OR_HR_PERIOD              and fp.SCO_DT_START <= convert(datetime, ? , 103)              and fp.SCO_DT_END >= convert(datetime, ? , 126)        left join M4SCO_PAYMENT_TYPE catfp              on catfp.SCO_ID_PAYM_TYPE = fp.SCO_ID_PAYM_TYPE       left join std_WORK_UNIT ca              on ca.std_id_work_unit = cme.CME_ID_WUNIT  where p.std_id_PERSON = ?  order by sme.SCO_DT_START_SLICE ";
  
  public static String getQueryCamposRubros()
  {
    return "select  c2.cme_etiqueta, c1.cme_id_columna, i.table_name, i.column_name ,                 p.cve_sat p_clave_sat, p.descripcion p_descripcion, d.cve_sat d_clave_sat, d.descripcion d_descripcion, e.exento, \t\t\t\t    e.tabla_exento, campo_exento, e.tabla_gravable, campo_gravable , e.despliega , despliega_con_0  from information_schema.columns i  inner join M4CME_COLUMNAS c1     on i.column_name = c1.id_item     and c1.cme_id_columna in (4,5)     and c1.id_item is not null     and c1.cme_id_reporte = 001  inner join M4CME_COLUMNAS c2     on  c1.cme_id_fila = c2.cme_id_fila     and c1.cme_id_reporte = c2.cme_id_reporte     and c2.cme_etiqueta is not null     and c2.CME_ID_FILA not in (1200,9000,9100)  left join pnetcfdi.dbo.EQUIVALENCIA_TSJDF_SAT e    on e.cve_tsjdf = RTRIM(SUBSTRING(c2.cme_etiqueta,1, CHARINDEX(' ', c2.cme_etiqueta ) - 1))  left join pnetcfdi.dbo.sat_percepciones p     on p.cve_sat = e.cve_sat    and c1.cme_id_columna = e.tipo     and e.tipo = 4  left join pnetcfdi.dbo.sat_deducciones d     on d.cve_sat = e.cve_sat     and c1.cme_id_columna = e.tipo     and e.tipo = 5  where (i.table_name like 'M4SME_AC_HR_PERIOD' or i.table_name like 'M4CME_AC_HR_PERIOD')  and i.data_type like 'numeric' ";
  }
  
  public static String getQueryDatosGenerales()
  {
    return "select distinct sme.SCO_DT_START_SLICE, cme.SCO_ID_HR num_empleado, p.std_n_fam_name_1 a_paterno,           p.std_n_maiden_name a_materno,           p.std_n_first_name nombre, p.std_ssn curp, p.std_ss_number rfc,           cme.CME_ID_POSITION num_plaza, sme.SAR_ID_CONVENIO nivel_salarial, pos.sco_nm_positionesp ult_puesto,           cme.CME_ID_SEC_SIND secc_sindicato, cme.CME_ID_WUNIT area,           conv.cme_clv_puesto codigo , sme.SME_ID_WORK_CENTER zp,           CME_MENSAJE2, CME_MENSAJE3, CME_MENSAJE4, CME_MENSAJE5,           d.STD_ADDRESS_LINE_1 calle, d.ssp_num_via num_ext, d.std_address_line_2 piso, d.std_address_line_3 departamento,           col.std_n_geo_placesp colonia, mun.std_n_sub_geo_esp municipio, edo.std_n_geo_divesp estado, d.std_zip_code cp, \t\t\t b.cve_sat, pb.sco_account_number,          catfp.soc_nm_paym_typesp metodo_pago, ca.std_n_work_unitesp descripcion_area from STD_PERSON P       inner join M4CME_AC_HR_PERIOD cme              on cme.SCO_ID_HR = p.std_id_PERSON              and cme.SCO_OR_HR_PERIOD = ?              and cme.SCO_DT_PAY = convert(datetime, ? , 103)       inner join M4SME_AC_HR_PERIOD sme              on sme.SCO_ID_HR = p.std_id_PERSON              and sme.SCO_OR_HR_PERIOD = ?              and sme.SCO_DT_PAY = convert(datetime, ? , 103)              and sme.SCO_DT_START_SLICE = cme.SCO_DT_START_SLICE        left join M4SCO_POSITION pos \t\t\t\ton pos.sco_id_position = cme.CME_ID_POSITION        left join m4cme_h_job_conv conv              on conv.sar_id_convenio = sme.SAR_ID_CONVENIO \t\t\t    and conv.std_id_job_code = sme.SCO_ID_JOB_CODE       left join std_address d              on d.std_id_person = p.std_id_PERSON              and std_dt_end > convert(datetime, ? , 103)              and std_dt_start < convert(datetime, ? , 103)       left join std_geo_place col           \ton col.std_id_geo_place = d.std_id_geo_place              and col.std_id_sub_geo_div = d.std_id_sub_geo_div       left join STD_SUB_GEO_DIV mun              on mun.std_id_sub_geo_div = d.std_id_sub_geo_div \t\t left join std_geo_div edo              on edo.STD_ID_COUNTRY = d.STD_ID_COUNTRY              and edo.STD_ID_GEO_DIV = d.std_id_geo_div \t\t left join m4sco_person_bank pb              on pb.sco_id_person = p.std_id_PERSON              and pb.sco_or_account = (select max(sco_or_account) from m4sco_person_bank where sco_id_person = p.std_id_PERSON )       left join pnetcfdi.dbo.EQUIVALENCIA_BANCOS b              on b.CVE_TSJDF = pb.sco_id_bank_branch \t\t left join M4SCO_PAYMENT_DATA fp              on fp.SCO_ID_HR = p.std_id_PERSON              and fp.SCO_OR_HR_PERIOD = cme.SCO_OR_HR_PERIOD              and fp.SCO_DT_START <= convert(datetime, ? , 103)              and fp.SCO_DT_END >= convert(datetime, ? , 126)        left join M4SCO_PAYMENT_TYPE catfp              on catfp.SCO_ID_PAYM_TYPE = fp.SCO_ID_PAYM_TYPE       left join std_WORK_UNIT ca              on ca.std_id_work_unit = cme.CME_ID_WUNIT  where p.std_id_PERSON = ?  order by sme.SCO_DT_START_SLICE ";
  }
  
  public static String getQueryAcumulado(HashMap<String, CampoRubroDto> hmapCampos)
  {
    StringBuffer query = new StringBuffer("select sme.SCO_ID_HR ");
    Set set = hmapCampos.entrySet();
    Iterator i = set.iterator();
    while (i.hasNext())
    {
    	java.util.Map.Entry me = (java.util.Map.Entry)i.next();
      if (((CampoRubroDto)me.getValue()).getTabla().equals("M4SME_AC_HR_PERIOD"))
      {
        query.append(", sme.");
        query.append((String)me.getKey());
      }
      else if (((CampoRubroDto)me.getValue()).getTabla().equals("M4CME_AC_HR_PERIOD"))
      {
        query.append(", cme.");
        query.append((String)me.getKey());
      }
      if ((((CampoRubroDto)me.getValue()).getTablaExento() != null) && (((CampoRubroDto)me.getValue()).getCampoExento() != null)) {
        if (((CampoRubroDto)me.getValue()).getTablaExento().equals("M4SME_AC_HR_PERIOD"))
        {
          query.append(", sme.");
          query.append(((CampoRubroDto)me.getValue()).getCampoExento());
        }
        else if (((CampoRubroDto)me.getValue()).getTablaExento().equals("M4CME_AC_HR_PERIOD"))
        {
          query.append(", cme.");
          query.append(((CampoRubroDto)me.getValue()).getCampoExento());
        }
      }
      if ((((CampoRubroDto)me.getValue()).getTablaGravable() != null) && (((CampoRubroDto)me.getValue()).getCampoGravable() != null)) {
        if (((CampoRubroDto)me.getValue()).getTablaGravable().equals("M4SME_AC_HR_PERIOD"))
        {
          query.append(", sme.");
          query.append(((CampoRubroDto)me.getValue()).getCampoGravable());
        }
        else if (((CampoRubroDto)me.getValue()).getTablaGravable().equals("M4CME_AC_HR_PERIOD"))
        {
          query.append(", cme.");
          query.append(((CampoRubroDto)me.getValue()).getCampoGravable());
        }
      }
    }
    query.append(" , sme.SME_TOTAL_DEVENGOS, sco.SCO_TOT_DEDUCTIONS , sco.SCO_NET ");
    
    query.append(" , Convert(varchar(10),sco.SCO_DT_START_SLICE,126) fecha_inicio, Convert(varchar(10),sco.SCO_DT_END_SLICE,126) fecha_fin ");
    query.append(" from M4SME_AC_HR_PERIOD sme, M4CME_AC_HR_PERIOD cme, M4SCO_AC_HR_PERIOD sco");
    query.append(" where sme.SCO_ID_HR = cme.SCO_ID_HR ");
    query.append(" and sme.SCO_OR_HR_PERIOD = cme.SCO_OR_HR_PERIOD ");
    query.append(" and sme.SCO_DT_PAY = cme.SCO_DT_PAY ");
    query.append(" and sme.SCO_DT_START_SLICE = cme.SCO_DT_START_SLICE ");
    query.append(" and sco.SCO_ID_HR = cme.SCO_ID_HR ");
    query.append(" and sco.SCO_OR_HR_PERIOD = cme.SCO_OR_HR_PERIOD ");
    query.append(" and sco.SCO_DT_PAY = cme.SCO_DT_PAY ");
    query.append(" and sco.SCO_DT_START_SLICE = cme.SCO_DT_START_SLICE ");
    query.append(" and sme.SCO_ID_HR = ? ");
    query.append(" and sme.sco_or_hr_period = ? ");
    query.append(" and sme.SCO_DT_PAY = convert(datetime, ? , 103) ");
    query.append(" order by fecha_inicio, fecha_fin ");
    return query.toString();
  }
  
  private static String QUERY_PAGOS_EMPLEADO = "select Convert(varchar(10),SCO_DT_PAY,103) fecha_pago, pd.SCO_NM_PAYESP descripcion, Convert(varchar(10),SCO_DT_START,103) fecha_inicial, Convert(varchar(10),SCO_DATE_END,103) fecha_final from M4CME_AC_HR_PERIOD pe , M4SCO_HT_PAYS pd where pe.SCO_DT_PAY = pd.sco_dt_accrued and upper(SCO_NM_PAYESP) not like '%NO VALIDA%' and  upper(SCO_NM_PAYESP) not like '%CANCELADA%' and  upper(SCO_NM_PAYESP) not like '%COMPLEMENTARIA%' and sco_id_hr = ? And year(SCO_DT_PAY) = ? union select Convert(varchar(10),SCO_DT_PAY,103) fecha_pago, pd.SCO_NM_PAYESP descripcion, Convert(varchar(10),SCO_DT_START,103) fecha_inicial, Convert(varchar(10),SCO_DATE_END,103) fecha_final from M4SME_AC_HR_PERIOD pe, M4SCO_HT_PAYS pd where pe.SCO_DT_PAY = pd.sco_dt_accrued and upper(SCO_NM_PAYESP) not like '%NO VALIDA%' and upper(SCO_NM_PAYESP) not like '%CANCELADA%' and  upper(SCO_NM_PAYESP) not like '%COMPLEMENTARIA%' and sco_id_hr = ? And year(SCO_DT_PAY) = ? ";
  private static final String QUERY_PERIODOS_EMPLEADO = "select distinct  sco_or_hr_period from M4CME_AC_HR_PERIOD \t\twhere sco_id_hr = ?      and sco_dt_pay = convert(datetime, ? , 103) ";
  
  public static String getQueryPagosEmpleado()
  {
    return QUERY_PAGOS_EMPLEADO;
  }
  
  public static String getQueryPeriodosEmpleado()
  {
    return "select distinct  sco_or_hr_period from M4CME_AC_HR_PERIOD \t\twhere sco_id_hr = ?      and sco_dt_pay = convert(datetime, ? , 103) ";
  }
  
  //private static String QUERY_EMPLEADOS_NOMINA = "select distinct  sco_id_hr from M4CME_AC_HR_PERIOD where sco_dt_pay = convert(datetime, ? , 103) and sco_id_hr IN (0041606)   ";
  private static String QUERY_EMPLEADOS_NOMINA = "select distinct  sco_id_hr from M4CME_AC_HR_PERIOD where sco_dt_pay = convert(datetime, ? , 103)  ";
  private static final String QUERY_DESCRIPCION_PAGOS = "select Convert(varchar(10),SCO_DT_START,103) fecha_inicial, Convert(varchar(10),SCO_DATE_END,103) fecha_final,     Convert(varchar(10),sco_dt_accrued,103) fecha_pago, SCO_DAYS_NUMBER dias_pagados, SCO_NM_PAYESP descripcion  from M4SCO_HT_PAYS  WHERE year(SCO_DT_START) = ? ";
  private static final String QUERY_EMPLEADOS_DOBLES = "select sco_id_hr, count(sco_or_hr_period)  from (     select distinct  sco_id_hr, sco_or_hr_period     from M4CME_AC_HR_PERIOD where           sco_dt_pay = convert(datetime, ? , 103)     ) a group by sco_id_hr having count(sco_or_hr_period) > 1 ";
  private static final String QUERY_TOTAL_RECIBOS = "select count(*) from (     select distinct  sco_id_hr, sco_or_hr_period     from M4CME_AC_HR_PERIOD where           sco_dt_pay = convert(datetime, ? , 103)     ) a  ";
  private static final String QUERY_FECHA_EJECUCION = "select Convert(varchar(10),sco_dt_run_day,126) fecha_ejecucion,  SCO_NM_PAYESP descripcion \tfrom M4SCO_HT_PAYS \tWHERE sco_dt_accrued = convert(datetime, ? , 103)";
  
  public static String getQueryEmpleadosNomina()
  {
    return QUERY_EMPLEADOS_NOMINA;
  }
  
  public static String getQueryDescripcionPagos()
  {
    return "select Convert(varchar(10),SCO_DT_START,103) fecha_inicial, Convert(varchar(10),SCO_DATE_END,103) fecha_final,     Convert(varchar(10),sco_dt_accrued,103) fecha_pago, SCO_DAYS_NUMBER dias_pagados, SCO_NM_PAYESP descripcion  from M4SCO_HT_PAYS  WHERE year(SCO_DT_START) = ? ";
  }
  
  public static String getQueryEmpleadosDobles()
  {
    return "select sco_id_hr, count(sco_or_hr_period)  from (     select distinct  sco_id_hr, sco_or_hr_period     from M4CME_AC_HR_PERIOD where           sco_dt_pay = convert(datetime, ? , 103)     ) a group by sco_id_hr having count(sco_or_hr_period) > 1 ";
  }
  
  public static String getQueryTotalRecibos()
  {
    return "select count(*) from (     select distinct  sco_id_hr, sco_or_hr_period     from M4CME_AC_HR_PERIOD where           sco_dt_pay = convert(datetime, ? , 103)     ) a  ";
  }
  
  public static String getQueryFechaEjecucion()
  {
    return "select Convert(varchar(10),sco_dt_run_day,126) fecha_ejecucion,  SCO_NM_PAYESP descripcion \tfrom M4SCO_HT_PAYS \tWHERE sco_dt_accrued = convert(datetime, ? , 103)";
  }
}
