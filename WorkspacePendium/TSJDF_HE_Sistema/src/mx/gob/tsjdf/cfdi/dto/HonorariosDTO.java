package mx.gob.tsjdf.cfdi.dto;


import java.io.Serializable;
import java.util.List;

public class HonorariosDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String totPercep;
	private String total;
	private String metodopago;
	private String clavedep;
	private String rfc;
	private String nombre;
	private int cantidad;
	private String descripcion;
	private String valorunita;
	private String importe;
	private String numemplead;
	private String curp;
	private int tiporegime;
	private String fechapago;
	private String fechainici;
	private String fechafinal;
	private int numdiaspag;
	private String departamen;
	private String puesto;
	private String periodicid;
	private String totPergra;
	private String totPerexe;
	private String importeisr;
	private String zonaPagadora;
	private String codigo;
	private String nivel;
	private String universo;
	private String plaza;
	private String seccionSindical;
	private String liquidacionPago;
	private String unidad;
	private String descuento;
	private String tipoContrato;
	private String tipoJornada;
	private List<HonorariosPercepDto> listPercepciones;
	
	
	
	
	public String getTipoContrato() {
		return tipoContrato;
	}
	public void setTipoContrato(String tipoContrato) {
		this.tipoContrato = tipoContrato;
	}
	public String getTipoJornada() {
		return tipoJornada;
	}
	public void setTipoJornada(String tipoJornada) {
		this.tipoJornada = tipoJornada;
	}
	public String getDescuento() {
		return descuento;
	}
	public void setDescuento(String descuento) {
		this.descuento = descuento;
	}
	public List<HonorariosPercepDto> getListPercepciones() {
		return listPercepciones;
	}
	public void setListPercepciones(List<HonorariosPercepDto> listPercepciones) {
		this.listPercepciones = listPercepciones;
	}
	public String getUnidad() {
		return unidad;
	}
	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
	public String getTotPercep() {
		return totPercep;
	}
	public void setTotPercep(String totPercep) {
		this.totPercep = totPercep;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getMetodopago() {
		return metodopago;
	}
	public void setMetodopago(String metodopago) {
		this.metodopago = metodopago;
	}
	public String getClavedep() {
		return clavedep;
	}
	public void setClavedep(String clavedep) {
		this.clavedep = clavedep;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getValorunita() {
		return valorunita;
	}
	public void setValorunita(String valorunita) {
		this.valorunita = valorunita;
	}
	public String getImporte() {
		return importe;
	}
	public void setImporte(String importe) {
		this.importe = importe;
	}
	public String getNumemplead() {
		return numemplead;
	}
	public void setNumemplead(String numemplead) {
		this.numemplead = numemplead;
	}
	public String getCurp() {
		return curp;
	}
	public void setCurp(String curp) {
		this.curp = curp;
	}
	public int getTiporegime() {
		return tiporegime;
	}
	public void setTiporegime(int tiporegime) {
		this.tiporegime = tiporegime;
	}
	public String getFechapago() {
		return fechapago;
	}
	public void setFechapago(String fechapago) {
		this.fechapago = fechapago;
	}
	public String getFechainici() {
		return fechainici;
	}
	public void setFechainici(String fechainici) {
		this.fechainici = fechainici;
	}
	public String getFechafinal() {
		return fechafinal;
	}
	public void setFechafinal(String fechafinal) {
		this.fechafinal = fechafinal;
	}
	public int getNumdiaspag() {
		return numdiaspag;
	}
	public void setNumdiaspag(int numdiaspag) {
		this.numdiaspag = numdiaspag;
	}
	public String getDepartamen() {
		return departamen;
	}
	public void setDepartamen(String departamen) {
		this.departamen = departamen;
	}
	public String getPuesto() {
		return puesto;
	}
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	public String getPeriodicid() {
		return periodicid;
	}
	public void setPeriodicid(String periodicid) {
		this.periodicid = periodicid;
	}
	public String getTotPergra() {
		return totPergra;
	}
	public void setTotPergra(String totPergra) {
		this.totPergra = totPergra;
	}
	public String getTotPerexe() {
		return totPerexe;
	}
	public void setTotPerexe(String totPerexe) {
		this.totPerexe = totPerexe;
	}
	public String getImporteisr() {
		return importeisr;
	}
	public void setImporteisr(String importeisr) {
		this.importeisr = importeisr;
	}
	public String getZonaPagadora() {
		return zonaPagadora;
	}
	public void setZonaPagadora(String zonaPagadora) {
		this.zonaPagadora = zonaPagadora;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNivel() {
		return nivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	public String getUniverso() {
		return universo;
	}
	public void setUniverso(String universo) {
		this.universo = universo;
	}
	public String getPlaza() {
		return plaza;
	}
	public void setPlaza(String plaza) {
		this.plaza = plaza;
	}
	public String getSeccionSindical() {
		return seccionSindical;
	}
	public void setSeccionSindical(String seccionSindical) {
		this.seccionSindical = seccionSindical;
	}
	public String getLiquidacionPago() {
		return liquidacionPago;
	}
	public void setLiquidacionPago(String liquidacionPago) {
		this.liquidacionPago = liquidacionPago;
	}
	
	

}
