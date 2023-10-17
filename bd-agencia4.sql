create database agencia;
use agencia;

create table clientes(
id_cliente int auto_increment,
nome_cliente varchar(20) not null,
data_nasc_cliente date not null,
email_cliente varchar(40) not null,
primary key (id_cliente)
)default charset = utf8mb4;

create table voos(
id_voo int auto_increment,
empresa_voo enum('LATAM', 'Azul', 'Gol'), 
origem_voo varchar(40),
destino_voo varchar(40),
aeroporto_origem varchar(50),
aeroporto_destino varchar(50),
hora_decolagem varchar(10),
hora_pouso varchar(10),
saida_voo date,
chegada_voo date,
preco_voo real,
primary key(id_voo)
)default charset = utf8mb4;

create table hoteis(
id_hotel int auto_increment,
nome varchar(50),
preco_diaria real,
cidade_hotel varchar(40),
bairro_hotel varchar(40),
rua_hotel varchar(40),
primary key(id_hotel)
)default charset = utf8mb4;

create table pacotes(
id_pacote int auto_increment,
id_voo_ida int,
id_voo_volta int,
id_hotel int,
destino_pacote varchar(40),
origem_pacote varchar(40),
data_ida_pacote date,
data_volta_pacote date,
preco_pacote real,
dias_pacote tinyint,
primary key(id_pacote),
foreign key(id_voo_ida) references voos(id_voo),
foreign key(id_voo_volta) references voos(id_voo),
foreign key(id_hotel) references hoteis(id_hotel)
)default charset = utf8mb4;

DELIMITER //
CREATE TRIGGER calcular_preco_pacote
BEFORE INSERT ON pacotes
FOR EACH ROW
BEGIN
    DECLARE preco_voo_ida REAL;
    DECLARE preco_voo_volta REAL;
    DECLARE preco_diaria_hotel REAL;

    -- Obter o preço do voo de ida
    SELECT preco_voo INTO preco_voo_ida FROM voos WHERE id_voo = NEW.id_voo_ida;

    -- Obter o preço do voo de volta
    SELECT preco_voo INTO preco_voo_volta FROM voos WHERE id_voo = NEW.id_voo_volta;

    -- Obter o preço da diária do hotel
    SELECT preco_diaria INTO preco_diaria_hotel FROM hoteis WHERE id_hotel = NEW.id_hotel;

    -- Calcular o preço do pacote
    SET NEW.preco_pacote = preco_voo_ida + preco_voo_volta + ((DATEDIFF(NEW.data_volta_pacote, NEW.data_ida_pacote) + 1) * preco_diaria_hotel);
END;
//
DELIMITER ;

DELIMITER //
CREATE TRIGGER calcular_dias_pacote
BEFORE INSERT ON pacotes
FOR EACH ROW
BEGIN
    -- Calcular o preço do pacote
    SET NEW.dias_pacote = DATEDIFF(NEW.data_volta_pacote, NEW.data_ida_pacote) + 1;
END;
//
DELIMITER ;

create table compras(
id_compra int auto_increment,
id_pacote int,
id_cliente int,
data_compra date,
tipo_pagamento enum('Crédito', 'Débito', 'PIX', 'Boleto'),
parcelas tinyint,
valor_compra real,
primary key(id_compra),
foreign key(id_pacote) references pacotes(id_pacote),
foreign key(id_cliente) references clientes(id_cliente)
)default charset = utf8mb4;


SELECT 
	p.*,
	h.*,
	vi.*,
	vv.*,
    p.id_pacote AS pacote_id, 
    h.id_hotel AS hotel_id, 
    vi.id_voo AS voo_ida_id, 
    vv.id_voo AS voo_volta_id
FROM 
    pacotes AS p
    JOIN hoteis AS h ON p.id_hotel = h.id_hotel
    JOIN voos AS vi ON p.id_voo_ida = vi.id_voo
    JOIN voos AS vv ON p.id_voo_volta = vv.id_voo;
    
