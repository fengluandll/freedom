package mx.gob.tsjdf.bean;

public class MenuBean {
	Integer id_menu;
	String nombre, descripcion, url, segment1, segment2, segment3, segment4, segment5, segment6, segment7, segment8, segment9;
	public Integer getId_menu() {
		return id_menu;
	}
	public void setId_menu(Integer id_menu) {
		this.id_menu = id_menu;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSegment1() {
		return segment1;
	}
	public void setSegment1(String segment1) {
		this.segment1 = segment1;
	}
	public String getSegment2() {
		return segment2;
	}
	public void setSegment2(String segment2) {
		this.segment2 = segment2;
	}
	public String getSegment3() {
		return segment3;
	}
	public void setSegment3(String segment3) {
		this.segment3 = segment3;
	}
	public String getSegment4() {
		return segment4;
	}
	public void setSegment4(String segment4) {
		this.segment4 = segment4;
	}
	public String getSegment5() {
		return segment5;
	}
	public void setSegment5(String segment5) {
		this.segment5 = segment5;
	}
	public String getSegment6() {
		return segment6;
	}
	public void setSegment6(String segment6) {
		this.segment6 = segment6;
	}
	public String getSegment7() {
		return segment7;
	}
	public void setSegment7(String segment7) {
		this.segment7 = segment7;
	}
	public String getSegment8() {
		return segment8;
	}
	public void setSegment8(String segment8) {
		this.segment8 = segment8;
	}
	public String getSegment9() {
		return segment9;
	}
	public void setSegment9(String segment9) {
		this.segment9 = segment9;
	}
	public MenuBean() {
		super();
	}
	public MenuBean(Integer id_menu, String nombre, String descripcion, String url, String segment1, String segment2,
			String segment3, String segment4, String segment5, String segment6, String segment7, String segment8,
			String segment9) {
		super();
		this.id_menu = id_menu;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.url = url;
		this.segment1 = segment1;
		this.segment2 = segment2;
		this.segment3 = segment3;
		this.segment4 = segment4;
		this.segment5 = segment5;
		this.segment6 = segment6;
		this.segment7 = segment7;
		this.segment8 = segment8;
		this.segment9 = segment9;
	}
	public MenuBean(Integer id_menu, String nombre, String url) {
		super();
		this.id_menu = id_menu;
		this.nombre = nombre;
		this.url = url;
	}
}
