/* Funciones de login y cambio de password de usuario */
DELIMITER $$
CREATE FUNCTION `profile_priv_fn`(p_profile_id INT)
RETURNS VARCHAR(240)
BEGIN 
	DECLARE lstReturn VARCHAR(50) DEFAULT 'Error';
	SELECT description 
      INTO lstReturn
	  FROM lookups
	 WHERE lookup_type = 'SYSUSR'
	   AND lookup_id = p_profile_id;
	RETURN lstReturn;
END
$$
DELIMITER ;

DELIMITER $$

CREATE DEFINER=`sysapps`@`localhost` FUNCTION `change_password_fn`(p_user_id INT, p_username VARCHAR(240), p_password_old VARCHAR(240), p_password_new VARCHAR(240)) RETURNS varchar(240) CHARSET utf8
BEGIN 
	DECLARE lstReturn VARCHAR(50) DEFAULT 'Error';
	CALL change_password_pr(p_user_id, p_username, p_password_old, p_password_new, lstReturn);
	
	IF (lstReturn ='OK') THEN
		SET lstReturn = '1';
	END IF;

	RETURN lstReturn;
END
$$
DELIMITER ;

/* Funciones de informacion de usuarios */
DELIMITER $$
CREATE FUNCTION  `user_insert_fn`(p_username VARCHAR(10), 
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
								  p_segment9 VARCHAR(60))
RETURNS VARCHAR(240)
BEGIN 
	DECLARE lstReturn VARCHAR(50) DEFAULT 'Error';
	CALL user_insert_pr(p_username, p_first_name, p_last_name, p_password, p_profile_id, p_photo, 
	                    p_start_date, p_end_date, p_segment1, p_segment2, p_segment3, p_segment4, 
	                    p_segment5, p_segment6, p_segment7, p_segment8, p_segment9, lstReturn);
	RETURN lstReturn;
END
$$
DELIMITER ;

DELIMITER $$
CREATE FUNCTION  `user_update_fn`(p_user_id INT, 
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
								  p_segment9 VARCHAR(60))
RETURNS VARCHAR(240)
BEGIN 
	DECLARE lstReturn VARCHAR(50) DEFAULT 'Error';
	CALL user_update_pr(p_user_id, p_first_name, p_last_name, p_profile_id, p_photo, p_end_date, 
	                    p_segment1, p_segment2, p_segment3, p_segment4, p_segment5, p_segment6, 
	                    p_segment7, p_segment8, p_segment9, lstReturn);
	RETURN lstReturn;
END
$$
DELIMITER ;

DELIMITER $$
CREATE FUNCTION `user_delete_fn`(p_user_id INT)
RETURNS VARCHAR(240)
BEGIN 
	DECLARE lstReturn VARCHAR(50) DEFAULT 'Error';
	CALL user_delete_pr(p_user_id, lstReturn);
	RETURN lstReturn;
END
$$
DELIMITER ;

/* Funciones de informacion de clientes */
DELIMITER $$
CREATE FUNCTION  `customer_insert_fn`(p_first_name 		VARCHAR(50),
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
	         						  p_user_id         INT)
RETURNS VARCHAR(240)
BEGIN 
	DECLARE lstReturn VARCHAR(50) DEFAULT 'Error';
	CALL customer_insert_pr(p_first_name, p_last_name, p_business_name, p_rfc,
						   p_address_line1, p_address_line2, p_address_line3,
						   p_city, p_state, p_country, p_postal_code, p_segment1, 
		                   p_segment2, p_segment3,  p_segment4,  p_segment5, p_segment6, 
		                   p_segment7, p_segment8, p_segment9, p_email, p_area_code_phone,
 						   p_phone, p_start_date, p_end_date, p_born_date, p_org_id,
 						   p_user_id, lstReturn);
	RETURN lstReturn;
END
$$
DELIMITER ;

DELIMITER $$
CREATE FUNCTION  `customer_update_fn`(p_customer_id 	INT, 
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
	         						  p_user_id         INT)
RETURNS VARCHAR(240)
BEGIN 
	DECLARE lstReturn VARCHAR(50) DEFAULT 'Error';
	CALL customer_update_pr(p_customer_id, p_first_name, p_last_name,  p_business_name, p_rfc, 
	                        p_address_line1, p_address_line2, p_address_line3, p_city, p_state, 
	                        p_country, p_postal_code, p_segment1, p_segment2, p_segment3, p_segment4, 
	                        p_segment5, p_segment6, p_segment7, p_segment8, p_segment9, p_email, 
	                        p_area_code_phone, p_phone, p_end_date, p_born_date, p_user_id, lstReturn);
	RETURN lstReturn;
END
$$
DELIMITER ;

DELIMITER $$
CREATE FUNCTION `customer_delete_fn`(p_customer_id INT, p_user_id INT)
RETURNS VARCHAR(240)
BEGIN 
	DECLARE lstReturn VARCHAR(50) DEFAULT 'Error';
	CALL customer_delete_pr(p_customer_id, p_user_id, lstReturn);
	RETURN lstReturn;
END
$$
DELIMITER ;

/* Funciones de informacion de Proveedores */
DELIMITER $$
CREATE FUNCTION  `supplier_insert_fn`(p_first_name 		VARCHAR(50),
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
	         						  p_user_id         INT)
RETURNS VARCHAR(240)
BEGIN 
	DECLARE lstReturn VARCHAR(50) DEFAULT 'Error';
	CALL supplier_insert_pr(p_first_name, p_last_name, p_business_name, p_rfc,
						   p_address_line1, p_address_line2, p_address_line3,
						   p_city, p_state, p_country, p_postal_code, p_segment1, 
		                   p_segment2, p_segment3,  p_segment4,  p_segment5, p_segment6, 
		                   p_segment7, p_segment8, p_segment9, p_email, p_area_code_phone,
 						   p_phone, p_start_date, p_end_date, p_born_date, p_org_id,
 						   p_user_id, lstReturn);
	RETURN lstReturn;
END
$$
DELIMITER ;

DELIMITER $$
CREATE FUNCTION  `supplier_update_fn`(p_vendor_id 	INT, 
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
	         						  p_user_id         INT)
RETURNS VARCHAR(240)
BEGIN 
	DECLARE lstReturn VARCHAR(50) DEFAULT 'Error';
	CALL supplier_update_pr(p_vendor_id, p_first_name, p_last_name,  p_business_name, p_rfc, 
	                        p_address_line1, p_address_line2, p_address_line3, p_city, p_state, 
	                        p_country, p_postal_code, p_segment1, p_segment2, p_segment3, p_segment4, 
	                        p_segment5, p_segment6, p_segment7, p_segment8, p_segment9, p_email, 
	                        p_area_code_phone, p_phone, p_end_date, p_born_date, p_user_id, lstReturn);
	RETURN lstReturn;
END
$$
DELIMITER ;

DELIMITER $$
CREATE FUNCTION `supplier_delete_fn`(p_vendor_id INT, p_user_id INT)
RETURNS VARCHAR(240)
BEGIN 
	DECLARE lstReturn VARCHAR(50) DEFAULT 'Error';
	CALL supplier_delete_pr(p_vendor_id, p_user_id, lstReturn);
	RETURN lstReturn;
END
$$
DELIMITER ;

/* Funciones de informacion de Items */
DELIMITER $$
CREATE FUNCTION  `item_insert_fn`(p_organization_id INT,
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
									  p_org_id   		INT)
RETURNS VARCHAR(240)
BEGIN 
	DECLARE lstReturn VARCHAR(50) DEFAULT 'Error';
	CALL item_insert_pr(p_organization_id, p_name,  p_description, p_type, 
	                        p_barcode, p_enabled_flag, p_start_date, p_end_date, p_segment_context, 
	                        p_segment1, p_segment2, p_segment3, p_segment4, p_segment5, p_segment6, 
	                        p_segment7, p_segment8, p_segment9, p_user_id, p_org_id, lstReturn);
	RETURN lstReturn;
END
$$
DELIMITER ;

DELIMITER $$
CREATE FUNCTION  `item_update_fn`(p_item_id INT, p_organization_id INT,
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
	         						  p_user_id         INT)
RETURNS VARCHAR(240)
BEGIN 
	DECLARE lstReturn VARCHAR(50) DEFAULT 'Error';
	CALL item_update_pr(p_item_id, p_organization_id, p_name,  p_description, p_type, 
	                        p_barcode, p_enabled_flag, p_end_date, p_segment_context, 
	                        p_segment1, p_segment2, p_segment3, p_segment4, 
	                        p_segment5, p_segment6, p_segment7, p_segment8, p_segment9, p_user_id, lstReturn);
	RETURN lstReturn;
END
$$
DELIMITER ;

DELIMITER $$
CREATE FUNCTION `item_delete_fn`(p_item_id INT, p_organization_id INT, p_user_id INT)
RETURNS VARCHAR(240)
BEGIN 
	DECLARE lstReturn VARCHAR(50) DEFAULT 'Error';
	CALL item_delete_pr(p_item_id, p_organization_id, p_user_id, lstReturn);
	RETURN lstReturn;
END
$$
DELIMITER ;