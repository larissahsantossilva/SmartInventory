CREATE TABLE product (
	id bigint PRIMARY KEY auto_increment,
	name varchar(50),
	quantity int ,
	description TEXT
	
);

INSERT INTO product (name, quantity, description) VALUES (
	'Arroz',
	 2,
	'Tio João, 2kg'
);


INSERT INTO product (name, quantity, description) VALUES (
	'Feijão',
	 4,
	'Camil, 1kg'
);


INSERT INTO product (name, quantity, description) VALUES (
	'Açúcar',
	 1,
	'União, 1kg'
);