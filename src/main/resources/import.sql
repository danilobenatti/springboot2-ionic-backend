insert into tbl_category (col_name) values ('Informática'), ('Escritório'), ('Cama mesa e banho'), ('Eletrônicos'), ('Jardinagem'), ('Decoração'), ('Perfumaria');

insert into tbl_product(col_name, col_unit_price) values ('Computador', 2000.00), ('Impressora', 800.00), ('Mouse', 80.00), ('Mesa de escritório', 300.00), ('Toalha', 50.00), ('Colcha', 200.00), ('TV true color', 1200.00), ('Roçadeira', 800.00), ('Abajour', 100.00), ('Pendente', 180.00), ('Shampoo', 90.00);

insert into tbl_product__category(id_product, id_category) values(1, 1), (1, 4), (2, 1), (2, 2), (2, 4), (3, 1), (3, 4), (4, 2), (5, 3), (6, 3), (7, 4), (8, 5), (9, 6), (10, 6), (11, 7);

insert into tbl_estate(col_name, col_uf) values('Minas Gerais', 'MG'), ('São Paulo', 'SP');

insert into tbl_city(col_name, col_id_estate) values('Uberlândia', 1), ('São Paulo', 2), ('Campinas', 2);

insert into tbl_client(col_name, col_email, col_cpfcnpj, col_type) values ('Maria Silva', 'danilobenatti@hotmail.com', '15716283047', 1);

insert into tbl_phone(client_id_client, col_phonenumber) values (1,'27363323'), (1,'93838393');

insert into tbl_address(col_street, col_number, col_complement, col_district, col_zipcode, id_client, id_city) values ('Rua Flores', '300', 'Apto 303', 'Jardim', '38220834', 1, 1), ('Avenida Matos', '105', 'Sala 800', 'Centro', '38777012', 1, 2);

insert into tbl_order(col_instant, id_client, id_deliveryaddress) values ('2017-09-30 10:32', 1, 1);
insert into tbl_payment(id_order, col_status) values (1, 2);
insert into tbl_payment__creditcard(id_order, col_number_installments) values (1, 6);

insert into tbl_order(col_instant, id_client, id_deliveryaddress) values ('2017-10-10 19:35', 1, 2);
insert into tbl_payment(id_order,col_status) values (2, 1);
insert into tbl_payment__slipbank(id_order, col_pay_day, col_expiration_date) values (2, null, '2017-10-20 00:00');

insert into tbl_ordered__item(col_discount, col_quantity, col_product_price, id_order, id_product) values (0.0, 1, 2000.00, 1, 1);
insert into tbl_ordered__item(col_discount, col_quantity, col_product_price, id_order, id_product) values (0.0, 2, 80.00, 1, 3);
insert into tbl_ordered__item(col_discount, col_quantity, col_product_price, id_order, id_product) values (100.0, 1, 800.00, 2, 2);
