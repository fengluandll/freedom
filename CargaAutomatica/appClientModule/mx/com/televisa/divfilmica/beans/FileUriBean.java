package mx.com.televisa.divfilmica.beans;

public class FileUriBean {
    
    private String reglaCarga;
    private String subproceso;
    private String nomArchivoUri;
    
    public FileUriBean() {
        
    }


    public String getReglaCarga() {
        return reglaCarga;
    }

    public void setReglaCarga(String reglaCarga) {
        this.reglaCarga = reglaCarga;
    }

    public String getSubproceso() {
        return subproceso;
    }

    public void setSubproceso(String subproceso) {
        this.subproceso = subproceso;
    }

    public String getNomArchivoUri() {
        return nomArchivoUri;
    }

    public void setNomArchivoUri(String nomArchivoUri) {
        this.nomArchivoUri = nomArchivoUri;
    }
}
