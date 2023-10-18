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

-- Calucula o preço do pacote somando os voos com quantidades de diárias baseada na quantidade de dias do pacote
DELIMITER //
CREATE TRIGGER calcular_preco_pacote
BEFORE INSERT ON pacotes
FOR EACH ROW
BEGIN
    DECLARE preco_voo_ida REAL;
    DECLARE preco_voo_volta REAL;
    DECLARE preco_diaria_hotel REAL;

    SELECT preco_voo INTO preco_voo_ida FROM voos WHERE id_voo = NEW.id_voo_ida;

    SELECT preco_voo INTO preco_voo_volta FROM voos WHERE id_voo = NEW.id_voo_volta;

    SELECT preco_diaria INTO preco_diaria_hotel FROM hoteis WHERE id_hotel = NEW.id_hotel;

    SET NEW.preco_pacote = preco_voo_ida + preco_voo_volta + ((DATEDIFF(NEW.data_volta_pacote, NEW.data_ida_pacote) + 1) * preco_diaria_hotel);
END;
//
DELIMITER ;

-- Calucula os dias do pacote subtraindo a volta menos a ida
DELIMITER //
CREATE TRIGGER calcular_dias_pacote
BEFORE INSERT ON pacotes
FOR EACH ROW
BEGIN
    SET NEW.dias_pacote = DATEDIFF(NEW.data_volta_pacote, NEW.data_ida_pacote) + 1;
END;
//
DELIMITER ;

-- Atualiza o preço do pacote caso o id voo seja alterado utilizando o preço do voo do id atualizado e tambem o id hotel
DELIMITER //
CREATE TRIGGER atualizar_preco_pacote_voos
BEFORE UPDATE ON pacotes
FOR EACH ROW
BEGIN
    DECLARE preco_voo_ida REAL;
    DECLARE preco_voo_volta REAL;
    DECLARE preco_diaria_hotel REAL;
    
    SELECT preco_voo INTO preco_voo_ida FROM voos WHERE id_voo = NEW.id_voo_ida;
    
    SELECT preco_voo INTO preco_voo_volta FROM voos WHERE id_voo = NEW.id_voo_volta;
    
    SELECT preco_diaria INTO preco_diaria_hotel FROM hoteis WHERE id_hotel = NEW.id_hotel;
    
    -- Calcular o novo preço do pacote
    SET NEW.preco_pacote = preco_voo_ida + preco_voo_volta + ((DATEDIFF(NEW.data_volta_pacote, NEW.data_ida_pacote) + 1) * preco_diaria_hotel);
END;
//
DELIMITER ;

-- Atualizar os dias dos pacotes quando as datas de ida ou de volta forem atualizadas
DELIMITER //
CREATE TRIGGER atualizar_dias_pacote
BEFORE UPDATE ON pacotes
FOR EACH ROW
BEGIN
    -- Calcular dias do pacote
    SET NEW.dias_pacote = DATEDIFF(NEW.data_volta_pacote, NEW.data_ida_pacote) + 1;
END;
//
DELIMITER ;

-- Atualizar o preço do pacote na tabela pacotes quando o preço de um voo referenciado em pacotes for atualizado na tabela voos
DELIMITER //
CREATE TRIGGER atualizar_preco_pacote_voo
AFTER UPDATE ON voos
FOR EACH ROW
BEGIN
    UPDATE pacotes AS p
    SET p.preco_pacote = (
        (SELECT preco_voo FROM voos WHERE id_voo = p.id_voo_ida) +
        (SELECT preco_voo FROM voos WHERE id_voo = p.id_voo_volta) +
        ((DATEDIFF(p.data_volta_pacote, p.data_ida_pacote) + 1) * (SELECT preco_diaria FROM hoteis WHERE id_hotel = p.id_hotel))
    )
    WHERE p.id_voo_ida = NEW.id_voo OR p.id_voo_volta = NEW.id_voo;
END;
//
DELIMITER ;

-- Atualizar o preço do pacote na tabela pacotes quando o preço da diária de um hotel referenciado em pacotes for atualizado na tabela hoteis
DELIMITER //
CREATE TRIGGER atualizar_preco_pacote_diaria_hotel
AFTER UPDATE ON hoteis
FOR EACH ROW
BEGIN
    UPDATE pacotes AS p
    SET p.preco_pacote = (
        (SELECT preco_voo FROM voos WHERE id_voo = p.id_voo_ida) +
        (SELECT preco_voo FROM voos WHERE id_voo = p.id_voo_volta) +
        ((DATEDIFF(p.data_volta_pacote, p.data_ida_pacote) + 1) * (SELECT preco_diaria FROM hoteis WHERE id_hotel = p.id_hotel))
    )
    WHERE p.id_hotel = NEW.id_hotel;
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

-- Define o valor da coluna valor_compra na tabela compra com o valor da coluna preco_pacote da tabela pacotes
DELIMITER //
CREATE TRIGGER calcular_valor_compra
BEFORE INSERT ON compras
FOR EACH ROW
BEGIN
    DECLARE preco_compra_total REAL;

    SELECT preco_pacote INTO preco_compra_total FROM pacotes WHERE id_pacote = NEW.id_pacote;

    SET NEW.valor_compra = preco_compra_total;
END;
//
DELIMITER ;

-- Atualiza o preço da compra quando o id_pacote for atualizado paro preco_pacote refenrente ao novo id
DELIMITER //
CREATE TRIGGER atualizar_valor_compra
BEFORE UPDATE ON compras
FOR EACH ROW
BEGIN
    DECLARE preco_pacote_total REAL;
    
    SELECT preco_pacote INTO preco_pacote_total FROM pacotes WHERE id_pacote = NEW.id_pacote;
    
    SET NEW.valor_compra = preco_pacote_total;
END;
//
DELIMITER ;

-- Atualiza o preço da compra quando o preco_pacote for atualizado na tabela pacotes
DELIMITER //
CREATE TRIGGER atualizar_valor_compra_pacote
AFTER UPDATE ON pacotes
FOR EACH ROW
BEGIN
    UPDATE compras AS c
    SET c.valor_compra = (
        SELECT preco_pacote FROM pacotes WHERE id_pacote = c.id_pacote
    )
    WHERE c.id_pacote = NEW.id_pacote;
END;
//
DELIMITER ;

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
    
SELECT 
    c.*,
    co.*,
	p.*,
	h.*,
	vi.*,
	vv.*
FROM 
    compras AS co
    JOIN clientes AS c ON co.id_cliente = c.id_cliente
    JOIN pacotes AS p ON co.id_pacote = p.id_pacote
    JOIN hoteis AS h ON p.id_hotel = h.id_hotel
    JOIN voos AS vi ON p.id_voo_ida = vi.id_voo
    JOIN voos AS vv ON p.id_voo_volta = vv.id_voo;

-- INSERT INTO `agencia`.`clientes` (`nome_cliente`, `data_nasc_cliente`, `email_cliente`) VALUES ('Camila Sousa', '1997-04-30', 'camila@email.com');
-- INSERT INTO `agencia`.`clientes` (`nome_cliente`, `data_nasc_cliente`, `email_cliente`) VALUES ('Gabriel Oliveira', '1998-02-28', 'gabriel@email.com');
-- INSERT INTO `agencia`.`clientes` (`nome_cliente`, `data_nasc_cliente`, `email_cliente`) VALUES ('Alana Silva', '1992-06-15', 'alana@email.com');
-- INSERT INTO `agencia`.`clientes` (`nome_cliente`, `data_nasc_cliente`, `email_cliente`) VALUES ('Lucia Nunes', '2001-10-01', 'lucia@email.com');

-- INSERT INTO `agencia`.`hoteis` (`nome`, `preco_diaria`, `cidade_hotel`, `bairro_hotel`, `rua_hotel`) VALUES ('Recanto','129.30', 'Rio de Janeiro', 'Copacabana', 'Edmundo Lins');
-- INSERT INTO `agencia`.`hoteis` (`nome`, `preco_diaria`, `cidade_hotel`, `bairro_hotel`, `rua_hotel`) VALUES ('Pousada Mritimar','259.60', 'Maragogi', 'Centro', 'Jangadeiros');
-- INSERT INTO `agencia`.`hoteis` (`nome`, `preco_diaria`, `cidade_hotel`, `bairro_hotel`, `rua_hotel`) VALUES ('Orlanova','233.50', 'Arraial do Cabo', 'Centro', 'Av. Marina do Cabo');
-- INSERT INTO `agencia`.`hoteis` (`nome`, `preco_diaria`, `cidade_hotel`, `bairro_hotel`, `rua_hotel`) VALUES ('Hotel Lírio','259.60', 'Carolina', 'Centro', 'Av. Adalberto Oliveira');

-- INSERT INTO `agencia`.`voos` (`empresa_voo`, `origem_voo`, `destino_voo`, `aeroporto_origem`, `aeroporto_destino`, `hora_decolagem`, `hora_pouso`, `saida_voo`, `chegada_voo`, `preco_voo`) VALUES ('Gol', 'Belo Horizonte - MG', 'Rio de Janeiro - RJ', 'Internacional de Confins - Tancredo Neves', 'Internacional do Rio de Janeiro - Galeão', '12:30', '13:35', '2024-01-30', '2024-01-30', '450.00');
-- INSERT INTO `agencia`.`voos` (`empresa_voo`, `origem_voo`, `destino_voo`, `aeroporto_origem`, `aeroporto_destino`, `hora_decolagem`, `hora_pouso`, `saida_voo`, `chegada_voo`, `preco_voo`) VALUES ('LATAM', 'Rio de Janeiro - RJ', 'Belo Horizonte - MG', 'Internacional do Rio de Janeiro - Galeão', 'Internacional de Confins - Tancredo Neves', '08:45', '09:55', '2024-02-08', '2024-02-08', '523.00');
-- INSERT INTO `agencia`.`voos` (`empresa_voo`, `origem_voo`, `destino_voo`, `aeroporto_origem`, `aeroporto_destino`, `hora_decolagem`, `hora_pouso`, `saida_voo`, `chegada_voo`, `preco_voo`) VALUES ('Azul', 'São Paulo - SP', 'Recife - PE', 'Internacional de Garulhos', 'Internacional do Recife/Guararapes', '20:00', '22:55', '2024-02-01', '2024-02-01', '753.00');
-- INSERT INTO `agencia`.`voos` (`empresa_voo`, `origem_voo`, `destino_voo`, `aeroporto_origem`, `aeroporto_destino`, `hora_decolagem`, `hora_pouso`, `saida_voo`, `chegada_voo`, `preco_voo`) VALUES ('Azul', 'Recife - PE', 'São Paulo - SP', 'Internacional do Recife/Guararapes', 'Internacional de Garulhos', '15:20', '18:05', '2024-02-15', '2024-02-15', '685.00');

-- INSERT INTO `agencia`.`pacotes` (`id_voo_ida`, `id_voo_volta`, `id_hotel`, `destino_pacote`, `origem_pacote`, `data_ida_pacote`, `data_volta_pacote`) VALUES ('1', '2', '1', 'Rio de Janeiro - RJ', 'Belo Horizonte - MG', '2024-01-30', '2024-02-08');
-- INSERT INTO `agencia`.`pacotes` (`id_voo_ida`, `id_voo_volta`, `id_hotel`, `destino_pacote`, `origem_pacote`, `data_ida_pacote`, `data_volta_pacote`) VALUES ('5', '6', '6', 'São Paulo - SP', 'Recife - PE', '2024-02-01', '2024-02-15');
-- INSERT INTO `agencia`.`pacotes` (`id_voo_ida`, `id_voo_volta`, `id_hotel`, `destino_pacote`, `origem_pacote`, `data_ida_pacote`, `data_volta_pacote`) VALUES ('8', '7', '1', 'Belo Horizonte - MG', 'Rio de Janeiro - RJ', '2024-01-30', '2024-02-08');

-- INSERT INTO `agencia`.`compras` (`id_pacote`, `id_cliente`, `data_compra`, `tipo_pagamento`, `parcelas`) VALUES ('5', '5', '2023-09-30', 'PIX', '1');
-- INSERT INTO `agencia`.`compras` (`id_pacote`, `id_cliente`, `data_compra`, `tipo_pagamento`, `parcelas`) VALUES ('4', '6', '2023-10-10', 'Crédito', '6');

