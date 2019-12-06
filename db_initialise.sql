DROP TABLE HIBERNATE.ROLES CASCADE CONSTRAINTS;
CREATE TABLE HIBERNATE.ROLES(ID NUMBER(19) NOT NULL, DESCRIPTION  VARCHAR2(255 CHAR), NAME VARCHAR2(255 CHAR));
ALTER TABLE HIBERNATE.ROLES ADD (PRIMARY KEY (ID) ENABLE VALIDATE);

DROP TABLE HIBERNATE.TASKS CASCADE CONSTRAINTS;
CREATE TABLE HIBERNATE.TASKS
(
  ID           NUMBER(19)                       NOT NULL,
  CAPTION      VARCHAR2(255 CHAR),
  DESCRIPTION  VARCHAR2(255 CHAR),
  STATUS       VARCHAR2(255 CHAR),
  ASSIGNED_ID  NUMBER(19),
  OWNER_ID     NUMBER(19)
);
ALTER TABLE HIBERNATE.TASKS ADD (PRIMARY KEY (ID) USING INDEX);
  
ALTER TABLE HIBERNATE.TASKS ADD (
  CONSTRAINT FK_OWNER FOREIGN KEY (OWNER_ID) 
  REFERENCES HIBERNATE.USERS (ID)
  ENABLE VALIDATE,
  CONSTRAINT FK_ASSIGNED FOREIGN KEY (ASSIGNED_ID) 
  REFERENCES HIBERNATE.USERS (ID)
  ENABLE VALIDATE);
  
DROP TABLE HIBERNATE.USERS CASCADE CONSTRAINTS;
CREATE TABLE HIBERNATE.USERS
(
  ID            NUMBER(19)                      NOT NULL,
  CAN_EXECUTE   NUMBER(1),
  CAN_INITIATE  NUMBER(1),
  PASSWORD      VARCHAR2(255 CHAR),
  USERNAME      VARCHAR2(255 CHAR)
);
ALTER TABLE HIBERNATE.USERS ADD (PRIMARY KEY (ID) USING INDEX);

DROP TABLE HIBERNATE.USER_ROLES CASCADE CONSTRAINTS;

CREATE TABLE HIBERNATE.USER_ROLES
(
  USER_ID  NUMBER(19)                           NOT NULL,
  ROLE_ID  NUMBER(19)                           NOT NULL
);

ALTER TABLE HIBERNATE.USER_ROLES ADD (
  PRIMARY KEY
  (USER_ID, ROLE_ID)
  USING INDEX);
ALTER TABLE HIBERNATE.USER_ROLES ADD (
  CONSTRAINT FK_ROLE 
  FOREIGN KEY (ROLE_ID) 
  REFERENCES HIBERNATE.ROLES (ID)
  ENABLE VALIDATE,
  CONSTRAINT FK_USER 
  FOREIGN KEY (USER_ID) 
  REFERENCES HIBERNATE.USERS (ID)
  ENABLE VALIDATE);
  
DROP SEQUENCE HIBERNATE.S_ROLES;

CREATE SEQUENCE HIBERNATE.S_ROLES
  START WITH 1
  MAXVALUE 9999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  CACHE 20
  NOORDER;
  
DROP SEQUENCE HIBERNATE.S_TASKS;

CREATE SEQUENCE HIBERNATE.S_TASKS
  START WITH 1
  MAXVALUE 9999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  CACHE 20
  NOORDER;

DROP SEQUENCE HIBERNATE.S_USERS;

CREATE SEQUENCE HIBERNATE.S_USERS
  START WITH 1
  MAXVALUE 9999999999999999999999999999
  MINVALUE 1
  NOCYCLE
  CACHE 20
  NOORDER;

--select * from hibernate.roles;
insert into hibernate.roles(id, name, description) values
  (hibernate.s_roles.nextval, 'Admin', 'System administrator');
insert into hibernate.roles(id, name, description) values
  (hibernate.s_roles.nextval, 'User', 'Users');;

--select * from hibernate.users;
insert into hibernate.users(id, username, password, can_initiate, can_execute) values
  (hibernate.s_users.nextval, 'Admin', 'password', 1, 1);
insert into hibernate.users(id, username, password, can_initiate, can_execute) values
  (hibernate.s_users.nextval, 'Manager', 'password', 1, 0);
insert into hibernate.users(id, username, password, can_initiate, can_execute) values
  (hibernate.s_users.nextval, 'Worker', 'password', 0, 1);
insert into hibernate.users(id, username, password, can_initiate, can_execute) values
  (hibernate.s_users.nextval, 'Elecrician', 'password', 0, 1);
insert into hibernate.users(id, username, password, can_initiate, can_execute) values
  (hibernate.s_users.nextval, 'Visitor', 'password', 0, 0);
--select * from hibernate.user_roles;
insert into hibernate.user_roles(user_id, role_id) values (1, 1);
insert into hibernate.user_roles(user_id, role_id) values (2, 2);
insert into hibernate.user_roles(user_id, role_id) values (3, 2);
insert into hibernate.user_roles(user_id, role_id) values (4, 2);
insert into hibernate.user_roles(user_id, role_id) values (5, 2);
--select * from hibernate.tasks;
insert into hibernate.tasks(id, caption, description, status, owner_id) 
  values (hibernate.s_tasks.nextval, 'Change light bulb', 'Problems with light at room 42', 'CREATED', 1);
  
commit;
/