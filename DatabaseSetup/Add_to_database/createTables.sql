DROP DATABASE IF EXISTS will_simi_lachlan;
CREATE DATABASE will_simi_lachlan;
USE will_simi_lachlan;

CREATE TABLE tomcat_users (
	user_name varchar(20) NOT NULL PRIMARY KEY,
	password varchar(32) NOT NULL
);
CREATE TABLE tomcat_roles (
	role_name varchar(20) NOT NULL PRIMARY KEY
);
CREATE TABLE tomcat_users_roles (
	user_name varchar(20) NOT NULL,
	role_name varchar(20) NOT NULL,
	PRIMARY KEY (user_name, role_name),
	CONSTRAINT tomcat_users_roles_foreign_key_1
		FOREIGN KEY (user_name) REFERENCES tomcat_users (user_name),
	CONSTRAINT tomcat_users_roles_foreign_key_2
		FOREIGN KEY (role_name) REFERENCES tomcat_roles (role_name)
);

INSERT INTO tomcat_roles (role_name) VALUES ('staff');
INSERT INTO tomcat_roles (role_name) VALUES ('user');

INSERT INTO tomcat_users (user_name, password) VALUES ('staff', 'staff');
INSERT INTO tomcat_users (user_name, password) VALUES ('user', 'user');

INSERT INTO tomcat_users_roles (user_name, role_name) VALUES ('staff', 'staff');
INSERT INTO tomcat_users_roles (user_name, role_name) VALUES ('user', 'user');
COMMIT;