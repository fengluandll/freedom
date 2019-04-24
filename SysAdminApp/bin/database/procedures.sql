/*
 * Ejemplo de procedimiento
 * 
 * DELIMITER $$

CREATE DEFINER=`kaz`@`%` PROCEDURE `InsertAlumnos_pr`(IN `id_alumno` INT, IN `apellidos` VARCHAR(50), IN `nombre` VARCHAR(50), IN `curso` INT, IN `id_asignatura` INT, IN `cursada` VARCHAR(1),OUT inOutParam VARCHAR(200))
    DETERMINISTIC
BEGIN
DECLARE EXIT HANDLER FOR SQLEXCEPTION 
ROLLBACK;
SET inOutParam = SQLEXCEPTION.message;
START TRANSACTION;

INSERT INTO alumnos_asignaturas (id_alumno, id_asignatura, cursada) VALUES (id_alumno, id_asignatura, cursada);
INSERT INTO alumnos (id_alumno, apellidos, nombre,curso) VALUES (id_alumno,apellidos,nombre,curso);
commit;



END
 * 
 */

/* Funciones de login y cambio de password de usuario */
DELIMITER $$
CREATE PROCEDURE `change_password_pr`(p_user_id INT, p_username VARCHAR(240), p_password_old VARCHAR(240), p_password_new VARCHAR(240), out p_out varchar(240))
BEGIN 
	
	SET SQL_SAFE_UPDATES=0;

	UPDATE users 
       SET password = md5(p_password_new)
         , last_update_date = sysdate()
         , last_update_by   = p_user_id
     WHERE username = p_username
       AND password = md5(p_password_old);

	SET p_out = 'OK';
END
$$
DELIMITER ;

/* Funciones de informacion de usuarios */
DELIMITER $$
CREATE PROCEDURE `user_insert_pr`(p_username VARCHAR(10), 
 								  p_first_name VARCHAR(50),
								  p_last_name VARCHAR(100),
	                              p_password VARCHAR(240),
								  p_profile_id INT,
								  p_photo    VARCHAR(60),
								  p_start_date DATETIME,
								  p_end_date DATETIME,
								  p_segment1 VARCHAR(60),
								  p_segment2 VARCHAR(60),
								  p_segment3 VARCHAR(60),
								  p_segment4 VARCHAR(60),
								  p_segment5 VARCHAR(60),
								  p_segment6 VARCHAR(60),
								  p_segment7 VARCHAR(60),
								  p_segment8 VARCHAR(60),
								  p_segment9 VARCHAR(60), 
								  out p_out varchar(240))
BEGIN 
	
	SET SQL_SAFE_UPDATES=0;
	
	INSERT INTO users (username, 
	                   first_name, 
	                   last_name, 
	                   password, 
	                   profile_id, 
	                   photo, 
	                   start_date, 
	                   end_date, 
	                   segment1, 
	                   segment2, 
	                   segment3, 
	                   segment4, 
	                   segment5, 
	                   segment6, 
	                   segment7, 
	                   segment8, 
	                   segment9, 
	                   creation_date, 
	                   created_by)
	VALUES            (p_username, 
	                   p_first_name, 
	                   p_last_name, 
	                   md5(p_password), 
	                   p_profile_id, 
	                   p_photo, 
	                   p_start_date, 
	                   p_end_date, 
	                   p_segment1, 
	                   p_segment2, 
	                   p_segment3, 
	                   p_segment4, 
	                   p_segment5, 
	                   p_segment6, 
	                   p_segment7, 
	                   p_segment8, 
	                   p_segment9, 
	                   sysdate(), 
	                   1001);

	SET p_out = 'OK';
END
$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `user_update_pr`(p_user_id INT, 
                                  p_first_name VARCHAR(50),
								  p_last_name VARCHAR(100),
								  p_profile_id INT,
								  p_photo    VARCHAR(60),
								  p_end_date DATETIME,
								  p_segment1 VARCHAR(60),
								  p_segment2 VARCHAR(60),
								  p_segment3 VARCHAR(60),
								  p_segment4 VARCHAR(60),
								  p_segment5 VARCHAR(60),
								  p_segment6 VARCHAR(60),
								  p_segment7 VARCHAR(60),
								  p_segment8 VARCHAR(60),
								  p_segment9 VARCHAR(60),
								  out p_out varchar(240))
BEGIN 
	
	SET SQL_SAFE_UPDATES=0;

	UPDATE users 
       SET first_name = p_first_name
         , last_name  = p_last_name
         , profile_id = p_profile_id
         , photo	  = p_photo
         , end_date   = p_end_date
         , segment1   = p_segment1
         , segment2   = p_segment2
         , segment3   = p_segment3
         , segment4   = p_segment4
         , segment5   = p_segment5
         , segment6   = p_segment6
         , segment7   = p_segment7
         , segment8   = p_segment8
         , segment9   = p_segment9
         , last_update_date = sysdate()
         , last_update_by   = p_user_id
     WHERE user_id  = p_user_id;

	SET p_out = 'OK';
END
$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `user_delete_pr`(p_user_id INT, out p_out varchar(240))
BEGIN 
	
	SET SQL_SAFE_UPDATES=0;

	UPDATE users 
       SET end_date = DATE_ADD(sysdate(), INTERVAL -1 DAY)
         , last_update_date = sysdate()
         , last_update_by   = p_user_id
     WHERE user_id  = p_user_id;

	SET p_out = 'OK';
END
$$
DELIMITER ;

/* Funciones de informacion de  clientes */
DELIMITER $$
CREATE PROCEDURE `customer_insert_pr`(p_first_name 		VARCHAR(50),
									  p_last_name 		VARCHAR(100), 
									  p_business_name 	VARCHAR(240), 
									  p_rfc 			VARCHAR(20),
									  p_address_line1 	VARCHAR(240),
									  p_address_line2 	VARCHAR(240),
									  p_address_line3 	VARCHAR(240),
									  p_city 			VARCHAR(45),
									  p_state 			VARCHAR(45),
									  p_country 		VARCHAR(45),
									  p_postal_code 	VARCHAR(10),
									  p_segment1 		VARCHAR(45),
									  p_segment2 		VARCHAR(45),
									  p_segment3 		VARCHAR(45),
									  p_segment4 		VARCHAR(45),
									  p_segment5 		VARCHAR(45),
									  p_segment6        VARCHAR(45),
									  p_segment7        VARCHAR(45),
									  p_segment8        VARCHAR(45),
									  p_segment9        VARCHAR(45),          
									  p_email           VARCHAR(240),
	         						  p_area_code_phone VARCHAR(4),
	         						  p_phone           VARCHAR(20),
	         						  p_start_date 		DATETIME, 
	         						  p_end_date 		DATETIME, 
	         						  p_born_date 		DATETIME,
									  p_org_id   		INT,
	         						  p_user_id         INT,
									  out p_out varchar(240))
BEGIN 
	
	SET SQL_SAFE_UPDATES=0;
	
	INSERT INTO customers (first_name, 
		                   last_name,  
						   business_name, 
						   rfc,
						   address_line1,
						   address_line2,
						   address_line3,
						   city,
						   state,
						   country,
						   postal_code,
		                   segment1, 
		                   segment2, 
		                   segment3, 
		                   segment4, 
		                   segment5, 
		                   segment6, 
		                   segment7, 
		                   segment8, 
		                   segment9,           
						   email,
 						   area_code_phone,
 						   phone,
 						   start_date, 
 						   end_date, 
 						   born_date,
		                   creation_date, 
		                   created_by,
		                   org_id)
	VALUES            	  (p_first_name, 
		                   p_last_name, 
						   p_business_name, 
						   p_rfc,
						   p_address_line1,
						   p_address_line2,
						   p_address_line3,
						   p_city,
						   p_state,
						   p_country,
						   p_postal_code,
		                   p_segment1, 
		                   p_segment2, 
		                   p_segment3, 
		                   p_segment4, 
		                   p_segment5, 
		                   p_segment6, 
		                   p_segment7, 
		                   p_segment8, 
		                   p_segment9,       
						   p_email,
 						   p_area_code_phone,
 						   p_phone,
 						   p_start_date, 
 						   p_end_date, 
 						   p_born_date,
		                   sysdate(), 
		                   p_user_id,
		                   p_org_id);

	SET p_out = 'OK';
END
$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `customer_update_pr`(p_customer_id 	INT, 
	                                  p_first_name      VARCHAR(50),
									  p_last_name 		VARCHAR(100), 
									  p_business_name 	VARCHAR(240), 
									  p_rfc 			VARCHAR(20),
									  p_address_line1 	VARCHAR(240),
									  p_address_line2 	VARCHAR(240),
									  p_address_line3 	VARCHAR(240),
									  p_city 			VARCHAR(45),
									  p_state 			VARCHAR(45),
									  p_country 		VARCHAR(45),
									  p_postal_code 	VARCHAR(10),
									  p_segment1 		VARCHAR(45),
									  p_segment2 		VARCHAR(45),
									  p_segment3 		VARCHAR(45),
									  p_segment4 		VARCHAR(45),
									  p_segment5 		VARCHAR(45),
									  p_segment6        VARCHAR(45),
									  p_segment7        VARCHAR(45),
									  p_segment8        VARCHAR(45),
									  p_segment9        VARCHAR(45),          
									  p_email           VARCHAR(240),
	         						  p_area_code_phone VARCHAR(4),
	         						  p_phone           VARCHAR(20),
	         						  p_end_date 		DATETIME, 
	         						  p_born_date 		DATETIME,
	         						  p_user_id         INT,
									  out p_out 		VARCHAR(240))
BEGIN 
	
	SET SQL_SAFE_UPDATES=0;

	UPDATE customers 
       SET first_name       = p_first_name
         , last_name        = p_last_name
         , business_name    = p_business_name
         , rfc              = p_rfc
         , address_line1    = p_address_line1
         , address_line2    = p_address_line2
         , address_line3    = p_address_line3
         , city             = p_city
         , state            = p_state
         , country          = p_country
         , postal_code      = p_postal_code
         , segment1         = p_segment1
         , segment2         = p_segment2
         , segment3         = p_segment3
         , segment4         = p_segment4
         , segment5         = p_segment5
         , segment6         = p_segment6
         , segment7         = p_segment7
         , segment8         = p_segment8
         , segment9         = p_segment9
         , email            = p_email
         , area_code_phone  = p_area_code_phone
         , phone            = p_phone
         , end_date         = p_end_date
         , born_date        = p_born_date
         , last_update_date = sysdate()
         , last_update_by   = p_user_id
     WHERE customer_id      = p_customer_id;

	SET p_out = 'OK';
END
$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `customer_delete_pr`(p_customer_id INT, p_user_id INT, out p_out varchar(240))
BEGIN 
	
	SET SQL_SAFE_UPDATES=0;

	UPDATE customers 
       SET end_date = DATE_ADD(sysdate(), INTERVAL -1 DAY)
         , last_update_date = sysdate()
         , last_update_by   = p_user_id
     WHERE customer_id  = p_customer_id;

	SET p_out = 'OK';
END
$$
DELIMITER ;

/* Procedimientos de informacion de  proveedores */
DELIMITER $$
CREATE PROCEDURE `supplier_insert_pr`(p_first_name 		VARCHAR(50),
									  p_last_name 		VARCHAR(100), 
									  p_business_name 	VARCHAR(240), 
									  p_rfc 			VARCHAR(20),
									  p_address_line1 	VARCHAR(240),
									  p_address_line2 	VARCHAR(240),
									  p_address_line3 	VARCHAR(240),
									  p_city 			VARCHAR(45),
									  p_state 			VARCHAR(45),
									  p_country 		VARCHAR(45),
									  p_postal_code 	VARCHAR(10),
									  p_segment1 		VARCHAR(45),
									  p_segment2 		VARCHAR(45),
									  p_segment3 		VARCHAR(45),
									  p_segment4 		VARCHAR(45),
									  p_segment5 		VARCHAR(45),
									  p_segment6        VARCHAR(45),
									  p_segment7        VARCHAR(45),
									  p_segment8        VARCHAR(45),
									  p_segment9        VARCHAR(45),          
									  p_email           VARCHAR(240),
	         						  p_area_code_phone VARCHAR(4),
	         						  p_phone           VARCHAR(20),
	         						  p_start_date 		DATETIME, 
	         						  p_end_date 		DATETIME, 
	         						  p_born_date 		DATETIME,
									  p_org_id   		INT,
	         						  p_user_id         INT,
									  out p_out varchar(240))
BEGIN 
	
	SET SQL_SAFE_UPDATES=0;
	
	INSERT INTO suppliers (first_name, 
		                   last_name,  
						   business_name, 
						   rfc,
						   address_line1,
						   address_line2,
						   address_line3,
						   city,
						   state,
						   country,
						   postal_code,
		                   segment1, 
		                   segment2, 
		                   segment3, 
		                   segment4, 
		                   segment5, 
		                   segment6, 
		                   segment7, 
		                   segment8, 
		                   segment9,           
						   email,
 						   area_code_phone,
 						   phone,
 						   start_date, 
 						   end_date, 
 						   born_date,
		                   creation_date, 
		                   created_by,
		                   org_id)
	VALUES            	  (p_first_name, 
		                   p_last_name, 
						   p_business_name, 
						   p_rfc,
						   p_address_line1,
						   p_address_line2,
						   p_address_line3,
						   p_city,
						   p_state,
						   p_country,
						   p_postal_code,
		                   p_segment1, 
		                   p_segment2, 
		                   p_segment3, 
		                   p_segment4, 
		                   p_segment5, 
		                   p_segment6, 
		                   p_segment7, 
		                   p_segment8, 
		                   p_segment9,       
						   p_email,
 						   p_area_code_phone,
 						   p_phone,
 						   p_start_date, 
 						   p_end_date, 
 						   p_born_date,
		                   sysdate(), 
		                   p_user_id,
		                   p_org_id);

	SET p_out = 'OK';
END
$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `supplier_update_pr`(p_vendor_id 	INT, 
	                                  p_first_name      VARCHAR(50),
									  p_last_name 		VARCHAR(100), 
									  p_business_name 	VARCHAR(240), 
									  p_rfc 			VARCHAR(20),
									  p_address_line1 	VARCHAR(240),
									  p_address_line2 	VARCHAR(240),
									  p_address_line3 	VARCHAR(240),
									  p_city 			VARCHAR(45),
									  p_state 			VARCHAR(45),
									  p_country 		VARCHAR(45),
									  p_postal_code 	VARCHAR(10),
									  p_segment1 		VARCHAR(45),
									  p_segment2 		VARCHAR(45),
									  p_segment3 		VARCHAR(45),
									  p_segment4 		VARCHAR(45),
									  p_segment5 		VARCHAR(45),
									  p_segment6        VARCHAR(45),
									  p_segment7        VARCHAR(45),
									  p_segment8        VARCHAR(45),
									  p_segment9        VARCHAR(45),          
									  p_email           VARCHAR(240),
	         						  p_area_code_phone VARCHAR(4),
	         						  p_phone           VARCHAR(20),
	         						  p_end_date 		DATETIME, 
	         						  p_born_date 		DATETIME,
	         						  p_user_id         INT,
									  out p_out 		VARCHAR(240))
BEGIN 
	
	SET SQL_SAFE_UPDATES=0;

	UPDATE suppliers 
       SET first_name       = p_first_name
         , last_name        = p_last_name
         , business_name    = p_business_name
         , rfc              = p_rfc
         , address_line1    = p_address_line1
         , address_line2    = p_address_line2
         , address_line3    = p_address_line3
         , city             = p_city
         , state            = p_state
         , country          = p_country
         , postal_code      = p_postal_code
         , segment1         = p_segment1
         , segment2         = p_segment2
         , segment3         = p_segment3
         , segment4         = p_segment4
         , segment5         = p_segment5
         , segment6         = p_segment6
         , segment7         = p_segment7
         , segment8         = p_segment8
         , segment9         = p_segment9
         , email            = p_email
         , area_code_phone  = p_area_code_phone
         , phone            = p_phone
         , end_date         = p_end_date
         , born_date        = p_born_date
         , last_update_date = sysdate()
         , last_update_by   = p_user_id
     WHERE vendor_id      = p_vendor_id;

	SET p_out = 'OK';
END
$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `supplier_delete_pr`(p_vendor_id INT, p_user_id INT, out p_out varchar(240))
BEGIN 
	
	SET SQL_SAFE_UPDATES=0;

	UPDATE suppliers 
       SET end_date = DATE_ADD(sysdate(), INTERVAL -1 DAY)
         , last_update_date = sysdate()
         , last_update_by   = p_user_id
     WHERE vendor_id  = p_vendor_id;

	SET p_out = 'OK';
END
$$
DELIMITER ;

/* Procedimientos de informacion de  items */
DELIMITER $$
CREATE PROCEDURE `item_insert_pr`(p_organization_id INT,
	                                  p_name      		VARCHAR(50),
									  p_description 	VARCHAR(100), 
									  p_type 			VARCHAR(240), 
									  p_barcode 		VARCHAR(20),
									  p_enabled_flag    VARCHAR(1),
									  p_start_date 		DATE,
									  p_end_date 		DATE,
									  p_segment_context VARCHAR(240),
									  p_segment1 		VARCHAR(45),
									  p_segment2 		VARCHAR(45),
									  p_segment3 		VARCHAR(45),
									  p_segment4 		VARCHAR(45),
									  p_segment5 		VARCHAR(45),
									  p_segment6        VARCHAR(45),
									  p_segment7        VARCHAR(45),
									  p_segment8        VARCHAR(45),
									  p_segment9        VARCHAR(45),
	         						  p_user_id         INT,
									  p_org_id   		INT,
									  out p_out varchar(240))
BEGIN 
	
	SET SQL_SAFE_UPDATES=0;
	
	INSERT INTO items (organization_id, 
	                       name, 
	                       description, 
	                       type, 
	                       barcode, 
	                       enabled_flag, 
	                       start_date, 
	                       end_date, 
	                       segment_context, 
	                       segment1, 
	                       segment2, 
	                       segment3, 
	                       segment4, 
	                       segment5, 
	                       segment6, 
	                       segment7, 
	                       segment8, 
	                       segment9,
		                   creation_date, 
		                   created_by,
		                   org_id)
	VALUES            	  (p_organization_id, 
	                       p_name, 
	                       p_description, 
	                       p_type, 
	                       p_barcode, 
	                       p_enabled_flag, 
	                       p_start_date, 
	                       p_end_date, 
	                       p_segment_context,
		                   p_segment1, 
		                   p_segment2, 
		                   p_segment3, 
		                   p_segment4, 
		                   p_segment5, 
		                   p_segment6, 
		                   p_segment7, 
		                   p_segment8, 
		                   p_segment9,  
		                   sysdate(), 
		                   p_user_id,
		                   p_org_id);

	SET p_out = 'OK';
END
$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `item_update_pr`(p_item_id INT, p_organization_id INT,
	                                  p_name      		VARCHAR(50),
									  p_description 	VARCHAR(100), 
									  p_type 			VARCHAR(240), 
									  p_barcode 		VARCHAR(20),
									  p_enabled_flag    VARCHAR(1),
									  p_end_date 		DATE,
									  p_segment_context VARCHAR(240),
									  p_segment1 		VARCHAR(45),
									  p_segment2 		VARCHAR(45),
									  p_segment3 		VARCHAR(45),
									  p_segment4 		VARCHAR(45),
									  p_segment5 		VARCHAR(45),
									  p_segment6        VARCHAR(45),
									  p_segment7        VARCHAR(45),
									  p_segment8        VARCHAR(45),
									  p_segment9        VARCHAR(45), 
	         						  p_user_id         INT,
									  out p_out 		VARCHAR(240))
BEGIN 
	
	SET SQL_SAFE_UPDATES=0;

	UPDATE items 
       SET name       = p_name
         , description      = p_description
         , type             = p_type
         , barcode          = p_barcode
         , enabled_flag     = p_enabled_flag
         , end_date         = p_end_date
         , segment_context  = p_segment_context
         , segment1         = p_segment1
         , segment2         = p_segment2
         , segment3         = p_segment3
         , segment4         = p_segment4
         , segment5         = p_segment5
         , segment6         = p_segment6
         , segment7         = p_segment7
         , segment8         = p_segment8
         , segment9         = p_segment9
         , last_update_date = sysdate()
         , last_update_by   = p_user_id
     WHERE item_id  = p_item_id
       AND organization_id = p_organization_id;

	SET p_out = 'OK';
END
$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `item_delete_pr`(p_item_id INT, p_organization_id INT, p_user_id INT, out p_out varchar(240))
BEGIN 
	
	SET SQL_SAFE_UPDATES=0;

	UPDATE items 
       SET end_date = DATE_ADD(sysdate(), INTERVAL -1 DAY)
         , last_update_date = sysdate()
         , last_update_by   = p_user_id
         , enabled_flag     = 'N'
     WHERE item_id  = p_item_id
       AND organization_id = p_organization_id;

	SET p_out = 'OK';
END
$$
DELIMITER ;