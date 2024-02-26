create table material (
	id bigint not null auto_increment,
	nome varchar(80) not null,
	descricao text not null,
	preco decimal(10,2) not null,
	id_vendedor BIGINT(20) NOT NULL,
	ativo tinyint(1) not null,	
	primary key (id),
	FOREIGN KEY (id_vendedor) REFERENCES pessoa(id)
) engine=InnoDB default charset=utf8;


INSERT INTO material (nome, descricao, preco, id_vendedor, ativo) values ('Areia', '50 kg de areia', 30.00, 1, true);
INSERT INTO material (nome, descricao, preco, id_vendedor, ativo) values ('Tinta', '2 galões de tinta', 25.00, 2, true);
INSERT INTO material (nome, descricao, preco, id_vendedor, ativo) values ('Madeira', 'madeiras para concreto', 40.00, 4, true);
INSERT INTO material (nome, descricao, preco, id_vendedor, ativo) values ('Ferros', 'Ferros para lajes', 100.00, 3, true);
