create table material (
	id bigint not null auto_increment,
	nome varchar(80) not null,
	descricao text not null,
	preco decimal(10,2) not null,
	ativo tinyint(1) not null,
	
	primary key (id)
) engine=InnoDB default charset=utf8;


INSERT INTO material (nome, descricao, preco, ativo) values ('Areia', '50 kg de areia', 30.00, true);
INSERT INTO material (nome, descricao, preco, ativo) values ('Tinta', '2 galões de tinta', 25.00, true);
INSERT INTO material (nome, descricao, preco, ativo) values ('Madeira', 'madeiras para concreto', 40.00, true);
INSERT INTO material (nome, descricao, preco, ativo) values ('Ferros', 'Ferros para lajes', 100.00, true);
