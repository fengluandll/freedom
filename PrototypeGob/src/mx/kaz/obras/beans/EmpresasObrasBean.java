package mx.kaz.obras.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class EmpresasObrasBean implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private int    idEmpPrpuesta;
	private int    idEmpBuild;
	private String nomEmpresa;
	private String repLegal;
	private BigDecimal montoOfertado;
	
	public EmpresasObrasBean(){
		
	}

	public String getNomEmpresa() {
		return nomEmpresa;
	}

	public void setNomEmpresa(String nomEmpresa) {
		this.nomEmpresa = nomEmpresa;
	}

	public String getRepLegal() {
		return repLegal;
	}

	public void setRepLegal(String repLegal) {
		this.repLegal = repLegal;
	}

	public BigDecimal getMontoOfertado() {
		return montoOfertado;
	}

	public void setMontoOfertado(BigDecimal montoOfertado) {
		this.montoOfertado = montoOfertado;
	}

	public int getIdEmpPrpuesta() {
		return idEmpPrpuesta;
	}

	public void setIdEmpPrpuesta(int idEmpPrpuesta) {
		this.idEmpPrpuesta = idEmpPrpuesta;
	}

	public int getIdEmpBuild() {
		return idEmpBuild;
	}

	public void setIdEmpBuild(int idEmpBuild) {
		this.idEmpBuild = idEmpBuild;
	}
	
	

}
