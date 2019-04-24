/* Pruebas de las funciones de Usuarios */
Select `sysapps`.`user_insert_fn` ('mary', 'MARY', 'FLORES','mary', 1002,null, DATE_ADD(sysdate(), INTERVAL -1 DAY), DATE_ADD(sysdate(), INTERVAL 12 MONTH),
                                   null, null, null, null, null, null, null, null, null);

select `sysapps`.`user_update_fn`(1004, 'ROSE MARY', 'FLORES SOLIS', 1002, null, null, 'USRUSER', 
                                  null, null, null, null, null, null, null, null);

SELECT `sysapps`.`user_delete_fn`(1004);

/* Pruebas de las funciones de Clientes */
SELECT `sysapps`.`customer_insert_fn`('Nelso Omar', 'Fernandez Morales', 'SISE Mexico', 'MESI130108IJ6', 
       'Tepeji No 76 Int 6', 'Col. Roma Sur', 'Deleg. Cuauhtemoc', 'Mexico D.F.', 'Distrito Federal', 
	   'Mexico', '06760', null, null, null, null, null, null, null, null, null, 
       'recursoshumanos@sisisemexico.com.mx', null, '(55) 4360 5630', DATE_ADD(sysdate(), INTERVAL -1 DAY), 
       DATE_ADD(sysdate(), INTERVAL 365 DAY), DATE_ADD(sysdate(), INTERVAL -365 DAY), -1, 1002);

SELECT `sysapps`.`customer_insert_fn`('Rose Mary', 'Folres Solis', 'SISE Mexico', 'MESI130108IJ6', 
       'Tepeji No 76 Int 6', 'Col. Roma Sur', 'Deleg. Cuauhtemoc', 'Mexico D.F.', 'Distrito Federal', 
	   'Mexico', '06760', null, null, null, null, null, null, null, null, null, 
       'recursoshumanos@sisisemexico.com.mx', null, '(55) 4360 5630', DATE_ADD(sysdate(), INTERVAL -1 DAY), 
       DATE_ADD(sysdate(), INTERVAL 365 DAY), DATE_ADD(sysdate(), INTERVAL -365 DAY), -1, 1002);

SELECT `sysapps`.`customer_update_fn`(10002, 'Rose Mary', 'Folres Solis', 'SISE Mexico', 'MESI130108IJ6', 
       'Tepeji No 76 Int 6', 'Col. Roma Sur', 'Deleg. Cuauhtemoc', 'Mexico D.F.', 'Distrito Federal', 
	   'Mexico', '06760', null, null, null, null, null, null, null, 'El cliente fue modificado:', 
       UPPER(DATE_FORMAT(SYSDATE(),'%d-%b-%Y')),  
       'recursoshumanos@sisisemexico.com.mx', null, '(55) 4360 5630', 
       DATE_ADD(sysdate(), INTERVAL 365 DAY), DATE_ADD(sysdate(), INTERVAL -365 DAY), 1001);

SELECT `sysapps`.`customer_delete_fn`(10002, 1003);

/* Pruebas de las funciones de Proveedores */
SELECT `sysapps`.`supplier_insert_fn`('Nelso Omar', 'Fernandez Morales', 'SISE Mexico', 'MESI130108IJ6', 
       'Tepeji No 76 Int 6', 'Col. Roma Sur', 'Deleg. Cuauhtemoc', 'Mexico D.F.', 'Distrito Federal', 
	   'Mexico', '06760', null, null, null, null, null, null, null, null, null, 
       'recursoshumanos@sisisemexico.com.mx', null, '(55) 4360 5630', DATE_ADD(sysdate(), INTERVAL -1 DAY), 
       DATE_ADD(sysdate(), INTERVAL 365 DAY), DATE_ADD(sysdate(), INTERVAL -365 DAY), -1, 1002);

SELECT `sysapps`.`supplier_insert_fn`('Rose Mary', 'Folres Solis', 'SISE Mexico', 'MESI130108IJ6', 
       'Tepeji No 76 Int 6', 'Col. Roma Sur', 'Deleg. Cuauhtemoc', 'Mexico D.F.', 'Distrito Federal', 
	   'Mexico', '06760', null, null, null, null, null, null, null, null, null, 
       'recursoshumanos@sisisemexico.com.mx', null, '(55) 4360 5630', DATE_ADD(sysdate(), INTERVAL -1 DAY), 
       DATE_ADD(sysdate(), INTERVAL 365 DAY), DATE_ADD(sysdate(), INTERVAL -365 DAY), -1, 1002);

SELECT `sysapps`.`supplier_update_fn`(10002, 'Rose Mary', 'Folres Solis', 'SISE Mexico', 'MESI130108IJ6', 
       'Tepeji No 76 Int 6', 'Col. Roma Sur', 'Deleg. Cuauhtemoc', 'Mexico D.F.', 'Distrito Federal', 
	   'Mexico', '06760', null, null, null, null, null, null, null, 'El cliente fue modificado:', 
       UPPER(DATE_FORMAT(SYSDATE(),'%d-%b-%Y')),  
       'recursoshumanos@sisisemexico.com.mx', null, '(55) 4360 5630', 
       DATE_ADD(sysdate(), INTERVAL 365 DAY), DATE_ADD(sysdate(), INTERVAL -365 DAY), 1001);

SELECT `sysapps`.`supplier_delete_fn`(10002, 1003);

/* Pruebas de las funciones de Items */
SELECT `sysapps`.`item_insert_fn`(101, 'Microfono', 'Microfono Inhalambrico', 'Utilerias', "", 'Y', DATE_ADD(sysdate(), INTERVAL -1 DAY), DATE_ADD(sysdate(), INTERVAL 365 DAY), "", "MIC1023I", "", "", "", "", "", "", "", "", 1001, -1);

SELECT `sysapps`.`item_update_fn`(103, 101, 'Microfono', 'Microfono Inhalambrico', 'Utilerias', "", 'Y', DATE_ADD(sysdate(), INTERVAL 365 DAY), "", "MIC1023I", "", "", "", "", "", "", "", "", 1002);

SELECT `sysapps`.`item_delete_fn`(103, 101, 1003);



