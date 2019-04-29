/**
 * @Jose de Jesus Argumedo Quiroz
 */
package mx.javaonline.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import mx.javaonline.beans.CoursesBean;
import mx.javaonline.carousel.beans.ObjetivoCursoBean;
import mx.javaonline.model.ConectionWrapper;

public class CursosDAO {

	private org.apache.log4j.Logger logger = Logger.getLogger(CursosDAO.class);
	ConectionWrapper 	conectionWrapper;
	Connection 			con;
	
	public CursosDAO() {
	
	}
	
	public List<CoursesBean> dameCurso() {
		conectionWrapper 		= new ConectionWrapper();
		ResultSet rs			= null;
		CallableStatement cst 	= null;
		CoursesBean	couseBean;
		List<CoursesBean> listCursos = new ArrayList<>();
		String error = null;
		try {
			con = conectionWrapper.getConexion();
			cst = con.prepareCall("{CALL CURSOS_DISPONIBLES_PR(?)}");
			cst.registerOutParameter(1, Types.VARCHAR);
			rs = cst.executeQuery();
			while(rs.next()) {
				couseBean = new CoursesBean();
				couseBean.setCourseId(rs.getInt("COURSE_ID"));
				couseBean.setDescripcion(rs.getString("DESCRIPCION"));
				couseBean.setCourseName(rs.getString("COURSE_NAME"));
				couseBean.setDuracion(rs.getString("DURACION"));
				couseBean.setProfesor(rs.getString("PROFESOR"));
				couseBean.setImg(rs.getString("IMG"));
				couseBean.setInversionTotal(rs.getString("INVERSION_TOTAL"));
				couseBean.setRownum(rs.getInt("rownum"));
				listCursos.add(couseBean);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				cst.close();
				rs.close();
				con.close();
			} catch (SQLException er) {
				logger.error(er);
			}
		}
		return listCursos;
	}

	/**
	 * Metodo que obtiene los cirsos contratados
	 * @return
	 */
	public List<CoursesBean> getCursosContratados(int idStudent) {
		conectionWrapper 		= new ConectionWrapper();
		ResultSet rs			= null;
		CallableStatement cst 	= null;
		CoursesBean	couseBean;
		List<CoursesBean> listCursos = new ArrayList<>();
		String error = null;
		try {
			con = conectionWrapper.getConexion();
			cst = con.prepareCall("{CALL CURSOS_CONTRATADOS_PR(?,?)}");
			cst.registerOutParameter(1, Types.VARCHAR);
			cst.setInt(2, idStudent);
			rs = cst.executeQuery();
			while(rs.next()) {
				couseBean = new CoursesBean();
				couseBean.setCourseId(rs.getInt("COURSE_ID"));
				couseBean.setDescripcion(rs.getString("DESCRIPCION"));
				couseBean.setCourseName(rs.getString("COURSE_NAME"));
				couseBean.setDuracion(rs.getString("DURACION"));
				couseBean.setProfesor(rs.getString("PROFESOR"));
				couseBean.setImg(rs.getString("IMG"));
				couseBean.setInversionTotal(rs.getString("INVERSION_TOTAL"));
				couseBean.setRownum(rs.getInt("rownum"));
				listCursos.add(couseBean);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				cst.close();
				rs.close();
				con.close();
			} catch (SQLException er) {
				logger.error(er);
			}
		}
		return listCursos;
	}
	
	public List<ObjetivoCursoBean> dameObjetivos(int courseId) {
		conectionWrapper 		= new ConectionWrapper();
		ResultSet rs			= null;
		CallableStatement cst 	= null;
		ObjetivoCursoBean	objetivoCursoBean;
		List<ObjetivoCursoBean> listObjetivos = new ArrayList<>();
		String error = null;
		try {
			con = conectionWrapper.getConexion();
			cst = con.prepareCall("{CALL GET_OBJETIVOS_CURSO_PR(?,?)}");
			cst.setInt(1, courseId);
			cst.registerOutParameter(2, Types.VARCHAR);
			rs = cst.executeQuery();
			while(rs.next()) {
				objetivoCursoBean = new ObjetivoCursoBean();
				objetivoCursoBean.setIdObjetivo(rs.getInt("id_objetivo"));
				objetivoCursoBean.setCourseId(rs.getInt("COURSE_ID"));
				objetivoCursoBean.setDescripcion(rs.getString("DESCRIPCION"));
				listObjetivos.add(objetivoCursoBean);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				cst.close();
				rs.close();
				con.close();
			} catch (SQLException er) {
				logger.error(er);
			}
		}
		return listObjetivos;
	}

}
