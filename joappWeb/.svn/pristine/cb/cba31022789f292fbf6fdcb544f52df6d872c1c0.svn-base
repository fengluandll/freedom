/**
 * @Jose de Jesus Argumedo Quiroz
 */
package mx.javaonline.admin.beans;

import java.util.Iterator;
import java.util.Map;

import mx.javaonline.daos.GraficasCursoDAO;

import org.apache.log4j.Logger;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;



public class GraficaCursosBean {

	
	private CartesianChartModel categoryModel; 
	GraficasCursoDAO graficasCursoDAO;
	private org.apache.log4j.Logger logger = Logger.getLogger(GraficaCursosBean.class);
	
	public GraficaCursosBean() {
		//createCategoryModel();  
		
	}
	/*
	private void createCategoryModel() { 
		graficasCursoDAO = new GraficasCursoDAO();
		Map<String,CursoPorcenBean> mapPorTot = graficasCursoDAO.porcTotalCurso();
		Iterator<String> claves = mapPorTot.keySet().iterator();
		
		
		categoryModel = new CartesianChartModel();  
		ChartSeries chart = new ChartSeries();  
		chart.setLabel("Mis cursos");  
        
		while(claves.hasNext()){
			String cursos = claves.next();
			CursoPorcenBean cursoPorcenBean = mapPorTot.get(cursos);
			logger.info(cursoPorcenBean.getCourseName());
			logger.info(cursoPorcenBean.getPorcentaje());
			logger.info(cursoPorcenBean.getTotUnits());
			chart.set(cursoPorcenBean.getCourseName(), cursoPorcenBean.getTotUnits());  
		}
		 categoryModel.addSeries(chart); 
        
    }
*/
	public int getPorTotal(int courseId){
		graficasCursoDAO = new GraficasCursoDAO();
		return graficasCursoDAO.porcTotalCurso(courseId);
	}
	public CartesianChartModel getCategoryModel() {
		return categoryModel;
	}

	public void setCategoryModel(CartesianChartModel categoryModel) {
		this.categoryModel = categoryModel;
	}  

	
}
