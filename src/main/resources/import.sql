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