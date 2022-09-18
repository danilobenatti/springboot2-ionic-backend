insert into tbl_category (col_name) values ('Informática');
insert into tbl_category (col_name) values ('Escritório');

insert into tbl_product(col_name, col_price) values ('Computador', 2000.00);
insert into tbl_product(col_name, col_price) values ('Impressora', 800.00);
insert into tbl_product(col_name, col_price) values ('Mouse', 80.00);

insert into tbl_product__category(id_product, id_category) values(1, 1);
insert into tbl_product__category(id_product, id_category) values(2, 1);
insert into tbl_product__category(id_product, id_category) values(2, 2);
insert into tbl_product__category(id_product, id_category) values(3, 1);

insert into tbl_estate(col_name, col_uf) values('Minas Gerais', 'MG');
insert into tbl_estate(col_name, col_uf) values('São Paulo', 'SP');

insert into tbl_city(col_name, col_id_estate) values('Uberlândia', 1);
insert into tbl_city(col_name, col_id_estate) values('São Paulo', 2);
insert into tbl_city(col_name, col_id_estate) values('Campinas', 2);

insert into tbl_client(col_name, col_email, col_cpfoucnpj, col_clienttype) values ('Maria Silva', 'maria@email.com', '36378912377', 1);
insert into tbl_phone(cliente_id_client, col_phonenumber) values (1,'27363323');
insert into tbl_phone(cliente_id_client, col_phonenumber) values (1,'93838393');

insert into tbl_address(col_street, col_number, col_complement, col_district, col_zipcode, id_client, id_city) values ('Rua Flores', '300', 'Apto 303', 'Jardim', '38220834', 1, 1);
insert into tbl_address(col_street, col_number, col_complement, col_district, col_zipcode, id_client, id_city) values ('Avenida Matos', '105', 'Sala 800', 'Centro', '38777012', 1, 2);