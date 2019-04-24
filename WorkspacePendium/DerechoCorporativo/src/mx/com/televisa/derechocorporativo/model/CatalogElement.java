package mx.com.televisa.derechocorporativo.model;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class CatalogElement {


    private int id;
    private String name;

    public CatalogElement() {
		// TODO Auto-generated constructor stub
	}
    
    public CatalogElement(ResultSet set) throws Exception {

        this.id = set.getInt(1);
        this.name = set.getString(2);
	}
    
    public void fillObject(ResultSet set, ResultSetMetaData metaData) throws Exception {
        
        this.id = set.getInt(1);
        this.name = set.getString(2);
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
