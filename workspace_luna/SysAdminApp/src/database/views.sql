-- Vista de usuarios
CREATE VIEW users_v AS
SELECT user_id, username
     , password
     , first_name
     , last_name
     , concat(first_name, ' ', last_name) full_name
     , profile_id
	 , segment1 profile_code
     , profile_priv_fn(profile_id) profile_name
	 , photo
     , start_date
	 , end_date 
     , segment2 
     , segment3 
     , segment4 
     , segment5 
     , segment6 
     , segment7 
     , segment8 
     , segment9
FROM users 
WHERE ifnull(start_date, DATE_ADD(sysdate(), INTERVAL -1 DAY)) <= sysdate() 
  AND ifnull(end_date, DATE_ADD(sysdate(), INTERVAL 1 DAY)) >= sysdate();
  
CREATE VIEW profile_v AS
SELECT lookup_id profile_id, lookup_code name, description 
  FROM lookups 
 WHERE lookup_type = 'SYSUSR' 
   AND enabled_flag = 'Y'
   AND ifnull(start_date, DATE_ADD(sysdate(), INTERVAL -1 DAY)) <= sysdate() 
   AND ifnull(end_date, DATE_ADD(sysdate(), INTERVAL 1 DAY)) >= sysdate();
   
CREATE VIEW customers_v AS
SELECT customer_id
     , first_name
     , last_name
     , business_name
     , rfc
     , address_line1
     , address_line2
     , address_line3
     , city
     , state
     , country
     , postal_code
     , segment1
     , segment2
     , segment3
     , segment4
     , segment5
     , segment6
     , segment7
     , segment8
     , segment9
     , email
     , area_code_phone
     , phone
     , start_date
     , end_date
     , born_date
FROM customers
WHERE ifnull(start_date, DATE_ADD(sysdate(), INTERVAL -1 DAY)) <= sysdate() 
  AND ifnull(end_date, DATE_ADD(sysdate(), INTERVAL 1 DAY)) >= sysdate();

  
CREATE VIEW suppliers_v AS
SELECT vendor_id
     , first_name
     , last_name
     , business_name
     , rfc
     , address_line1
     , address_line2
     , address_line3
     , city
     , state
     , country
     , postal_code
     , segment1
     , segment2
     , segment3
     , segment4
     , segment5
     , segment6
     , segment7
     , segment8
     , segment9
     , email
     , area_code_phone
     , phone
     , start_date
     , end_date
     , born_date
FROM suppliers
WHERE ifnull(start_date, DATE_ADD(sysdate(), INTERVAL -1 DAY)) <= sysdate() 
  AND ifnull(end_date, DATE_ADD(sysdate(), INTERVAL 1 DAY)) >= sysdate();
  
CREATE VIEW items_v AS 
SELECT item_id
     , organization_id
     , name
     , description
     , type
     , barcode
     , enabled_flag
     , start_date
     , end_date
     , segment_context
     , segment1
     , segment2
     , segment3
     , segment4
     , segment5
     , segment6
     , segment7
     , segment8
     , segment9
FROM items
WHERE ifnull(start_date, DATE_ADD(sysdate(), INTERVAL -1 DAY)) <= sysdate() 
  AND ifnull(end_date, DATE_ADD(sysdate(), INTERVAL 1 DAY)) >= sysdate();