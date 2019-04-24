-- Insert a la tabla de users

INSERT INTO sysapps.users (user_id, username, first_name, last_name, profile_id, start_date, creation_date, created_by, password, segment1) VALUES ('1001', 'admin', 'Admin', 'Administrador Sistema', '1', sysdate(),sysdate(),-1, md5('SysAmin'), 'USRADMIN');
INSERT INTO sysapps.users (user_id, username, first_name, last_name, profile_id, start_date, creation_date, created_by, password, segment1) VALUES ('1002', 'nelsomar', 'Nelso', 'Fernandez', '1', sysdate(),sysdate(),-1, md5('Nelsomar23'), 'USRADMIN');
INSERT INTO sysapps.users (user_id, username, first_name, last_name, profile_id, start_date, creation_date, created_by, password, segment1) VALUES ('1003', 'omar', 'Omar', 'Morales', '2', sysdate(),sysdate(),-1, md5('123'), 'USRUSER');

-- Insert a la tabla de lookups
INSERT INTO sysapps.lookups (lookup_id, lookup_code, lookup_type, name, description, enabled_flag, start_date, creation_date, created_by) VALUES ('1001', 'USRADMIN', 'SYSUSR', 'ADMIN', 'Administrador del sistema', 'Y', sysdate(), sysdate(), -1);
INSERT INTO sysapps.lookups (lookup_id, lookup_code, lookup_type, name, description, enabled_flag, start_date, creation_date, created_by) VALUES ('1002', 'USRUSER', 'SYSUSR', 'USUARIO', 'Usuario del sistema', 'Y', sysdate(), sysdate(), -1);
