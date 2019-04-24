package mx.gob.tsjdf.cfdi.dto;

import java.util.ArrayList;

import mx.gob.tsjdf.cfdi.bo.ValidacionBo;

public class ErroresDatosEmpleadosDto
{
  private String numeroEmpleado;
  private int periodo;
  private ArrayList<ErrorDatoEmpleadoDto> errores;
  private int totalErrores;
  private int totalEmpleados;
  private int recibosValidos;
  private int recibosInvalidos;
  
  
  public ErroresDatosEmpleadosDto() {}
  
  public ErroresDatosEmpleadosDto(String numeroEmpleado, ErrorDatoEmpleadoDto dto)
  {
    this.errores = new ArrayList<>();
    this.numeroEmpleado = numeroEmpleado;
    this.periodo = 0;
    this.errores.add(dto);
  }
  
  public String getNumeroEmpleado()
  {
    return this.numeroEmpleado;
  }
  
  public void setNumeroEmpleado(String numeroEmpleado)
  {
    this.numeroEmpleado = numeroEmpleado;
  }
  
  public int getPeriodo()
  {
    return this.periodo;
  }
  
  public void setPeriodo(int numeroRecibo)
  {
    this.periodo = numeroRecibo;
  }
  
  public ArrayList<ErrorDatoEmpleadoDto> getErrores()
  {
    return this.errores;
  }
  
  public void setErrores(ArrayList<ErrorDatoEmpleadoDto> errores)
  {
    this.errores = errores;
  }

public int getTotalErrores() {
	return totalErrores;
}

public void setTotalErrores(int totalErrores) {
	this.totalErrores = totalErrores;
}

public int getTotalEmpleados() {
	return totalEmpleados;
}

public void setTotalEmpleados(int totalEmpleados) {
	this.totalEmpleados = totalEmpleados;
}

public int getRecibosValidos() {
	return recibosValidos;
}

public void setRecibosValidos(int recibosValidos) {
	this.recibosValidos = recibosValidos;
}

public int getRecibosInvalidos() {
	return recibosInvalidos;
}

public void setRecibosInvalidos(int recibosInvalidos) {
	this.recibosInvalidos = recibosInvalidos;
}
  
}
