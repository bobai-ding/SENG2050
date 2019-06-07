
DROP DATABASE IF EXISTS will_simi_lachlan;
CREATE DATABASE will_simi_lachlan;
USE will_simi_lachlan;

CREATE TABLE tomcat_users (
	user_name varchar(20) NOT NULL PRIMARY KEY,
	password varchar(32) NOT NULL,

	firstName varchar(32),
	lastName varchar(32),
	email varchar(32),
	phoneNum int
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
INSERT INTO tomcat_users (user_name, password, firstName, lastName, email, phoneNum) VALUES ('user1', 'user1', 'user1_firstName', 'user1_lastName', 'user1_email', 1111111);
INSERT INTO tomcat_users (user_name, password, firstName, lastName, email, phoneNum) VALUES ('user2', 'user2', 'user2_firstName', 'user2_lastName', 'user2_email', 2222222);
INSERT INTO tomcat_users (user_name, password, firstName, lastName, email, phoneNum) VALUES ('user3', 'user3', 'user3_firstName', 'user3_lastName', 'user3_email', 3333333);
INSERT INTO tomcat_users (user_name, password, firstName, lastName, email, phoneNum) VALUES ('user4', 'user4', 'user4_firstName', 'user4_lastName', 'user4_email', 4444444);

INSERT INTO tomcat_users_roles (user_name, role_name) VALUES ('staff', 'staff');
INSERT INTO tomcat_users_roles (user_name, role_name) VALUES ('user1', 'user');
INSERT INTO tomcat_users_roles (user_name, role_name) VALUES ('user2', 'user');
INSERT INTO tomcat_users_roles (user_name, role_name) VALUES ('user3', 'user');
INSERT INTO tomcat_users_roles (user_name, role_name) VALUES ('user4', 'user');
COMMIT;