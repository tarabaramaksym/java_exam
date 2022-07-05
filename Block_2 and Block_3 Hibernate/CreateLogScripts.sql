CREATE TABLE hr.MAIL_LOG
(
  ID INTEGER NOT NULL 
, EMAIL VARCHAR2(100 CHAR) NOT NULL 
, MESSAGE_TEXT VARCHAR2(200) NOT NULL 
, CONSTRAINT ID_PK PRIMARY KEY 
  (
    ID 
  )
  ENABLE 
);

Create sequence hr.MAIL_LOG_SEQUENCE
start with 1
increment by 1
minvalue 1
maxvalue 10000;

create or replace function hr.INSERT_MAIL_LOG(
email_param in varchar2,
message_param in varchar2
)RETURN integer is
nMailLog_Id INTEGER; 
BEGIN
INSERT INTO hr.MAIL_LOG (ID,EMAIL,MESSAGE_TEXT) 
values (hr.MAIL_LOG_SEQUENCE.nextval,email_param,message_param) 
RETURNING ID INTO nMailLog_Id;
return nMailLog_Id;
END;

