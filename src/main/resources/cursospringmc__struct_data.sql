
    create table tbl_address (
       id_address  serial not null,
        col_complement varchar(45),
        col_district varchar(80) not null,
        col_number varchar(25) not null,
        col_street varchar(150) not null,
        col_zipcode varchar(10),
        id_city int4 not null,
        id_client int4 not null,
        primary key (id_address)
    );

    create table tbl_category (
       id_category  serial not null,
        col_name varchar(45) not null,
        primary key (id_category)
    );

    create table tbl_city (
       id_city  serial not null,
        col_name varchar(85) not null,
        col_id_estate int4 not null,
        primary key (id_city)
    );

    create table tbl_client (
       id_client  serial not null,
        col_cpfcnpj varchar(255),
        col_email varchar(255) not null,
        col_name varchar(255) not null,
        col_type int4,
        primary key (id_client)
    );

    create table tbl_estate (
       id_estate  serial not null,
        col_name varchar(45) not null,
        col_uf varchar(2) not null,
        primary key (id_estate)
    );

    create table tbl_order (
       id_order  serial not null,
        col_instant timestamp,
        id_client int4 not null,
        id_deliveryaddress int4 not null,
        primary key (id_order)
    );

    create table tbl_ordered__item (
       col_discount float8,
        col_product_price float8,
        col_quantity int4,
        id_order int4 not null,
        id_product int4 not null,
        primary key (id_order, id_product)
    );

    create table tbl_payment (
       id_order int4 not null,
        col_status int4 not null,
        primary key (id_order)
    );

    create table tbl_payment__creditcard (
       col_number_installments int4,
        id_order int4 not null,
        primary key (id_order)
    );

    create table tbl_payment__slipbank (
       col_expiration_date timestamp,
        col_pay_day timestamp,
        id_order int4 not null,
        primary key (id_order)
    );

    create table tbl_phone (
       cliente_id_client int4 not null,
        col_phonenumber varchar(20) not null,
        primary key (cliente_id_client, col_phonenumber)
    );

    create table tbl_product (
       id_product  serial not null,
        col_name varchar(150) not null,
        col_unit_price float8,
        primary key (id_product)
    );

    create table tbl_product__category (
       id_product int4 not null,
        id_category int4 not null
    );

    alter table tbl_category 
       add constraint uk_category__name unique (col_name);

    alter table tbl_city 
       add constraint uk_city__name_idstate unique (col_name, col_id_estate);

    alter table tbl_client 
       add constraint uk_client__email unique (col_email);

    alter table tbl_client 
       add constraint uk_client__cpfcnpj unique (col_cpfcnpj);

    alter table tbl_estate 
       add constraint uk_estate__name unique (col_name);

    alter table tbl_estate 
       add constraint uk_estate__uf unique (col_uf);

    alter table tbl_phone 
       add constraint uk_phone__number unique (col_phonenumber);

    alter table tbl_product 
       add constraint uk_product__name unique (col_name);

    alter table tbl_product__category 
       add constraint uk_idproduct__idcategory unique (id_product, id_category);

    alter table tbl_address 
       add constraint fk_address__idcity 
       foreign key (id_city) 
       references tbl_city(id_city) 
       on delete cascade;

    alter table tbl_address 
       add constraint fk_address__idclient 
       foreign key (id_client) 
       references tbl_client(id_client) 
       on delete cascade;

    alter table tbl_city 
       add constraint fk_city__idestate 
       foreign key (col_id_estate) 
       references tbl_estate(id_estate) 
       on delete cascade;

    alter table tbl_order 
       add constraint fk_order__idclient 
       foreign key (id_client) 
       references tbl_client(id_client) 
       on delete cascade;

    alter table tbl_order 
       add constraint fk_order__idaddress 
       foreign key (id_deliveryaddress) 
       references tbl_address(id_address) 
       on delete cascade;

    alter table tbl_ordered__item 
       add constraint fk_orderitem__idorder 
       foreign key (id_order) 
       references tbl_order(id_order) 
       on delete cascade;

    alter table tbl_ordered__item 
       add constraint fk_orderitem__idproduct 
       foreign key (id_product) 
       references tbl_product(id_product) 
       on delete cascade;

    alter table tbl_payment 
       add constraint FKk07flg5qe7xmst2q0crtebyfv 
       foreign key (id_order) 
       references tbl_order;

    alter table tbl_payment__creditcard 
       add constraint FK3ewcow86hl73a9j0kaf6s12n4 
       foreign key (id_order) 
       references tbl_payment;

    alter table tbl_payment__slipbank 
       add constraint FKjhg3hxeu1bvfjolysp8skto4s 
       foreign key (id_order) 
       references tbl_payment;

    alter table tbl_phone 
       add constraint fk_phone__idclient 
       foreign key (cliente_id_client) 
       references tbl_client(id_client) 
       on delete cascade;

    alter table tbl_product__category 
       add constraint fk_product__idcategory 
       foreign key (id_category) 
       references tbl_category(id_category);

    alter table tbl_product__category 
       add constraint fk_product__idproduct 
       foreign key (id_product) 
       references tbl_product(id_product) 
       on delete cascade;
insert into tbl_category (col_name) values ('Informática'), ('Escritório'), ('Cama mesa e banho'), ('Eletrônicos'), ('Jardinagem'), ('Decoração'), ('Perfumaria');
insert into tbl_product(col_name, col_unit_price) values ('Computador', 2000.00), ('Impressora', 800.00), ('Mouse', 80.00), ('Mesa de escritório', 300.00), ('Toalha', 50.00), ('Colcha', 200.00), ('TV true color', 1200.00), ('Roçadeira', 800.00), ('Abajour', 100.00), ('Pendente', 180.00), ('Shampoo', 90.00);
insert into tbl_product__category(id_product, id_category) values(1, 1), (1, 4), (2, 1), (2, 2), (2, 4), (3, 1), (3, 4), (4, 2), (5, 3), (6, 3), (7, 4), (8, 5), (9, 6), (10, 6), (11, 7);
insert into tbl_estate(col_name, col_uf) values('Minas Gerais', 'MG'), ('São Paulo', 'SP');
insert into tbl_city(col_name, col_id_estate) values('Uberlândia', 1), ('São Paulo', 2), ('Campinas', 2);
insert into tbl_client(col_name, col_email, col_cpfcnpj, col_type) values ('Maria Silva', 'danilobenatti@hotmail.com', '15716283047', 1);
insert into tbl_phone(cliente_id_client, col_phonenumber) values (1,'27363323'), (1,'93838393');
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
