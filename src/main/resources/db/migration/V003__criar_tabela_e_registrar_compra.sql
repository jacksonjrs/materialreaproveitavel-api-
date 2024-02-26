CREATE TABLE compra (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	sub_total DECIMAL(10,2) NOT NULL,
	taxa_frete DECIMAL(10,2) NOT NULL,
	valor_total DECIMAL(10,2) NOT NULL,
	data_criacao DATE NOT NULL,
	data_confirmacao DATE,
	data_entrega DATE,
	data_cancelamento DATE,	
	status VARCHAR(20) NOT NULL,
	id_material BIGINT(20) NOT NULL,
	id_comprador BIGINT(20) NOT NULL,
	FOREIGN KEY (id_material) REFERENCES material(id),
	FOREIGN KEY (id_comprador) REFERENCES pessoa(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO compra (sub_total, taxa_frete, valor_total, data_criacao, data_confirmacao, data_entrega, data_cancelamento, status,  id_material, id_comprador) values (20.00, 20.00, 40.00,'2017-06-10', '2017-06-10', '2017-06-10', null, 'ENTREGUE', 1, 3);
INSERT INTO compra (sub_total, taxa_frete, valor_total, data_criacao, data_confirmacao, data_entrega, data_cancelamento, status,  id_material, id_comprador) values (40.00, 20.00, 60.00,'2017-02-10', '2017-02-10', '2017-02-10', null, 'ENTREGUE', 2, 3);
INSERT INTO compra (sub_total, taxa_frete, valor_total, data_criacao, data_confirmacao, data_entrega, data_cancelamento, status,  id_material, id_comprador) values (25.00, 20.00, 45.00,'2017-06-10', '2017-06-10', '2017-06-10', null, 'ENTREGUE', 3, 2);
INSERT INTO compra (sub_total, taxa_frete, valor_total, data_criacao, data_confirmacao, data_entrega, data_cancelamento, status,  id_material, id_comprador) values (250.35, 20.00, 275.35,'2017-02-10', '2017-02-10', '2017-02-10', null, 'ENTREGUE', 3, 1);
INSERT INTO compra (sub_total, taxa_frete, valor_total, data_criacao, data_confirmacao, data_entrega, data_cancelamento, status,  id_material, id_comprador) values (300.15, 20.00, 320.15,'2017-06-10', '2017-06-10','2017-06-10', null, 'ENTREGUE', 3, 3);
INSERT INTO compra (sub_total, taxa_frete, valor_total, data_criacao, data_confirmacao, data_entrega, data_cancelamento, status,  id_material, id_comprador) values (485.20, 20.00, 505.20, '2017-03-10', '2017-03-10','2017-03-10', null, 'ENTREGUE', 4, 1);
INSERT INTO compra (sub_total, taxa_frete, valor_total, data_criacao, data_confirmacao, data_entrega, data_cancelamento, status,  id_material, id_comprador) values (100.00, 20.00, 120.00,'2017-06-10', null,  null, '2017-06-10', 'CANCELADO', 1, 1);
INSERT INTO compra (sub_total, taxa_frete, valor_total, data_criacao, data_confirmacao, data_entrega, data_cancelamento, status,  id_material, id_comprador) values (250.90, 20.00, 270.90, '2017-03-10', null, null, '2017-06-10', 'CANCELADO', 4, 3);
INSERT INTO compra (sub_total, taxa_frete, valor_total, data_criacao, data_confirmacao, data_entrega, data_cancelamento, status,  id_material, id_comprador) values (58.00, 20.00, 78.00,'2017-06-10', null, null, '2017-06-10', 'CANCELADO', 3, 4);
INSERT INTO compra (sub_total, taxa_frete, valor_total, data_criacao, data_confirmacao, data_entrega, data_cancelamento, status,  id_material, id_comprador) values (58.25, 20.00, 78.25,'2017-04-10', '2017-04-10', null, null,  'CONFIRMADO', 3, 4);
INSERT INTO compra (sub_total, taxa_frete, valor_total, data_criacao, data_confirmacao, data_entrega, data_cancelamento, status,  id_material, id_comprador) values (150.68, 20.00, 170.68,'2017-06-10', '2017-06-10', null, null, 'CONFIRMADO', 1, 2);
INSERT INTO compra (sub_total, taxa_frete, valor_total, data_criacao, data_confirmacao, data_entrega, data_cancelamento, status,  id_material, id_comprador) values (458.63, 20.00, 478.63,'2017-04-10', '2017-04-10', null, null, 'CONFIRMADO', 4, 1);
INSERT INTO compra (sub_total, taxa_frete, valor_total, data_criacao, data_confirmacao, data_entrega, data_cancelamento, status,  id_material, id_comprador) values (580.65, 20.00, 600.65,'2017-06-10', null, null, null, 'CRIADO', 4, 4);
INSERT INTO compra (sub_total, taxa_frete, valor_total, data_criacao, data_confirmacao, data_entrega, data_cancelamento, status,  id_material, id_comprador) values (200.00, 20.00, 220.00,'2017-04-10', null, null, null, 'CRIADO', 4, 1);
INSERT INTO compra (sub_total, taxa_frete, valor_total, data_criacao, data_confirmacao, data_entrega, data_cancelamento, status,  id_material, id_comprador) values (150.00, 20.00, 170.00,'2017-06-10', null, null, null, 'CRIADO', 4, 2);