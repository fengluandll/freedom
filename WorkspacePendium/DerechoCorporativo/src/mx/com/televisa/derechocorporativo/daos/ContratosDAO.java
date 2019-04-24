package mx.com.televisa.derechocorporativo.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import mx.com.televisa.derechocorporativo.data.ConnectionWrapper;

public class ContratosDAO {
	private ResultSet resultSet;
	private PreparedStatement psmt;

	public ContratosDAO(){

		resultSet = null;
	}

	public String getCelebradoEntrePartes(int tiIdMetaRow, ConnectionWrapper connectionWrapper){
        StringBuilder lsCelebradoEntrePartes = new StringBuilder(
            "SELECT APP_COMMON_PKG.GET_CELEBRADO_ENTRE_PARTES_FN("+tiIdMetaRow+") FROM DUAL"
        );
        StringBuilder lsSocAcc = new StringBuilder();

		try {
			psmt = connectionWrapper.prepareStatement(lsCelebradoEntrePartes.toString());
			resultSet = psmt.executeQuery();

			while(resultSet.next()){
				lsSocAcc.append(resultSet.getString(1));
			}

		} catch (Exception e) {
				e.printStackTrace();
		}

		return lsSocAcc.toString();

	}

}