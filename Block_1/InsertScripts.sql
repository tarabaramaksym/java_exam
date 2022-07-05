create or replace function hr.insert_client(
name_param in varchar2,
phone_param in VARCHAR2,
email_param in varchar2
)RETURN integer is
nClients_Id INTEGER; 
BEGIN
INSERT INTO hr.CLIENTS (CLIENTS_ID,NAME,MAIL,PHONE) 
values (hr.clients_sequence.nextval,name_param,email_param,phone_param) 
RETURNING CLIENTS_ID INTO nClients_Id;
return nclients_id;
END;

create or replace function hr.insert_my_logs(
action in VARCHAR2,
tbl_name in VARCHAR2,
id_row in Number,
log_info in VARCHAR2,
log_time in DATE
)RETURN integer is
id INTEGER; 
BEGIN
INSERT INTO hr.MY_LOGS
values (hr.my_logs_sequence.nextval,action,tbl_name,id_row,log_info,log_time) 
RETURNING ID INTO id;
return id;
END;


create or replace function hr.insert_products(
name in varchar2,
price in number
)RETURN integer is
id INTEGER; 
BEGIN
INSERT INTO hr.PRODUCTS (PRODUCTS_ID,NAME,PRICE) 
values (hr.products_sequence.nextval,name,price) 
RETURNING ID INTO id;
return id;
END;

create or replace function hr.insert_orders(
description in varchar2,
orders_date in VARCHAR2,
total_costs in Number,
clients_id in Number
)RETURN integer is
id INTEGER; 
BEGIN
INSERT INTO hr.ORDERS
values (hr.orders_sequence.nextval,description,orders_date,total_costs,clients_id) 
RETURNING ID INTO id;
return id;
END;


create or replace function hr.insert_orders_positions(
orders_id in number,
products_id in number,
price in number,
item_count in number
)RETURN integer is
id INTEGER; 
BEGIN
INSERT INTO hr.ORDERS_POSITIONS
values (hr.orders_positions_sequence.nextval,orders_id, products_id, price, item_count) 
RETURNING ID INTO id;
return id;
END;