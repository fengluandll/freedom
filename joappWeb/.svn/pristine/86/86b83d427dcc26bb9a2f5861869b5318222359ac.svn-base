/**
 * @Jose de Jesus Argumedo Quiroz
 */
package mx.javaonline.daos;

import java.sql.Connection;

import mx.javaonline.model.ConectionWrapper;

import org.apache.log4j.Logger;

public class TopicCarouselDAO {


	private org.apache.log4j.Logger logger = Logger.getLogger(TopicCarouselDAO.class);
	ConectionWrapper conectionWrapper;
	Connection con;
	
	public TopicCarouselDAO() {
		conectionWrapper = new ConectionWrapper();
	}
	
	public void dameVideosTopic(){
		String sql       =  "SELECT id_video,\n" +
				"    id_topic,\n" +
				"    nom_video,\n" +
				"    link_video,\n" +
				"    segment1,\n" +
				"    segment2,\n" +
				"    segment3,\n" +
				"    segment4,\n" +
				"    segment5,\n" +
				"    segment6,\n" +
				"    segment7,\n" +
				"    segment8,\n" +
				"    creation_date,\n" +
				"    last_update_date\n" +
				"FROM videos_tab\n" +
				"WHERE id_topic = ?";
	}

}
