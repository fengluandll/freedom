package mx.com.televisa.divfilmica.beans;

public class LogBean {
    
    private String codError;
    private String reglaEssbase;
    private String tipoError;
    private boolean huboError;
    private String nomProceso;
    private String descError;
    
    public LogBean() {
        
    }


    
    public String getCodError() {
        return codError;
    }

    public void setCodError(String codError) {
        this.codError = codError;
    }

    public String getReglaEssbase() {
        return reglaEssbase;
    }

    public void setReglaEssbase(String reglaEssbase) {
        this.reglaEssbase = reglaEssbase;
    }

    public String getTipoError() {
        return tipoError;
    }

    public void setTipoError(String tipoError) {
        this.tipoError = tipoError;
    }

    public boolean isHuboError() {
        return huboError;
    }

    public void setHuboError(boolean huboError) {
        this.huboError = huboError;
    }

    public String getNomProceso() {
        return nomProceso;
    }

    public void setNomProceso(String nomProceso) {
        this.nomProceso = nomProceso;
    }

    public String getDescError() {
        return descError;
    }

    public void setDescError(String descError) {
        this.descError = descError;
    }
}
