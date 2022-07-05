CREATE TABLE hr.MY_USER
(
  ID INTEGER NOT NULL 
, USER_NAME VARCHAR2(100 CHAR) NOT NULL 
, USER_PASS VARCHAR2(200) NOT NULL 
, CONSTRAINT ID_MYUSER_PK PRIMARY KEY 
  (
    ID 
  )
  ENABLE 
);

Create sequence hr.MY_USER_SEQUENCE
start with 1
increment by 1
minvalue 1
maxvalue 10000;

create or replace function hr.INSERT_MY_USER(
username in varchar2,
userpass in varchar2
)RETURN integer is
nId INTEGER; 
BEGIN
INSERT INTO hr.MY_USER (ID,USER_NAME,USER_PASS) 
values (hr.MY_USER_SEQUENCE.nextval,username,userpass) 
RETURNING ID INTO nId;
return nId;
END;



CREATE TABLE hr.USER_INFO
(
  ID INTEGER NOT NULL 
, ID_USER NUMBER NOT NULL
, EMAIL VARCHAR2(100 CHAR) NOT NULL
, PHONE VARCHAR2(100 CHAR) NOT NULL
, USER_RULE Number NOT NULL
, CONSTRAINT USER_INFO_PK PRIMARY KEY 
  (
   ID 
  )
, CONSTRAINT ID_USER_FK
    FOREIGN KEY (ID_USER)
    REFERENCES hr.MY_USER(ID)
    
  ENABLE 
);

Create sequence hr.USER_INFO_SEQUENCE
start with 1
increment by 1
minvalue 1
maxvalue 10000;

create or replace function hr.INSERT_USER_INFO(
iduser in number,
email in varchar2,
phone in varchar2,
userrule in number
)RETURN integer is
nId INTEGER; 
BEGIN
INSERT INTO hr.USER_INFO (ID,ID_USER,EMAIL,PHONE,USER_RULE) 
values (hr.USER_INFO_SEQUENCE.nextval,iduser,email,phone,userrule) 
RETURNING ID INTO nId;
return nId;
END;

 select user0_.ID, user0_.USER_PASS, user0_.USER_NAME as USER_NAME3_0_ from HR.HR.MY_USER user0_





