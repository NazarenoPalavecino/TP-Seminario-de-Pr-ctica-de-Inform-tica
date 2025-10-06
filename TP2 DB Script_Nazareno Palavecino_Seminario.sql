-- Creación de la Base de Datos `Torneo_JiuJitsu_DB` -- 
CREATE DATABASE IF NOT EXISTS `Torneo_JiuJitsu_DB`;
USE `Torneo_JiuJitsu_DB` ;

-- Creación de las tablas --
CREATE TABLE IF NOT EXISTS `Torneo_JiuJitsu_DB`.`competidor` (
	`dni` INT(10) NOT NULL,
	`nombre` VARCHAR(50) NOT NULL,
	`apellido` VARCHAR(50) NOT NULL,
    `edad` INT(3) NOT NULL,
    `sexo`VARCHAR(10) NOT NULL,
    `peso` DECIMAL(6,3) NOT NULL,
    `cinturón` VARCHAR(10) NOT NULL,
    `escuela` VARCHAR(10) NOT NULL,
	PRIMARY KEY (`dni`));
    
CREATE TABLE IF NOT EXISTS `Torneo_JiuJitsu_DB`.`llave` (
	`id_llave` INT(10) NOT NULL,
    `categoría_sexo`VARCHAR(10) NOT NULL,
    `categoría_peso` VARCHAR(20) NOT NULL,
    `categoría_cinturón` VARCHAR(10) NOT NULL,
	PRIMARY KEY (`id_llave`));
    
CREATE TABLE IF NOT EXISTS `Torneo_JiuJitsu_DB`.`encuentro` (
	`id_encuentro` INT NOT NULL,
    `id_llave` INT NOT NULL,
	PRIMARY KEY (`id_encuentro`),
    FOREIGN KEY (`id_llave`) REFERENCES `Torneo_JiuJitsu_DB`.`llave` (`id_llave`));
    
CREATE TABLE IF NOT EXISTS `Torneo_JiuJitsu_DB`.`competidor_encuentro` (
    `id_competidor_encuentro` INT NOT NULL,
    `id_encuentro` INT NOT NULL,
	`dni_competidor` INT(10) NOT NULL,
    `puntos` INT(2),
    `ventajas` INT(2),
    `faltas` INT(2),
    `finalización` BOOLEAN,
	PRIMARY KEY (`id_competidor_encuentro`),
    FOREIGN KEY (`id_encuentro`) REFERENCES `Torneo_JiuJitsu_DB`.`encuentro` (`id_encuentro`),
    FOREIGN KEY (`dni_competidor`) REFERENCES `Torneo_JiuJitsu_DB`.`competidor` (`dni`));
    
-- Inserción de datos --
INSERT INTO `torneo_jiujitsu_db`.`competidor` (`dni`, `nombre`, `apellido`, `edad`, `sexo`, 
`peso`, `cinturón`, `escuela`) 
VALUES 
	('41111111', 'Juan', 'Juarez', '19', 'masculino', '77', 'blanco', 'Gracie'),
    ('42222222', 'Pedro', 'Pomez', '20', 'masculino', '77.3', 'blanco', 'Kimura'),
    ('43333333', 'Marcos', 'Marquez', '21', 'masculino', '76.5', 'blanco', 'Valdez'),
	('44444444', 'Gastón', 'Gutierrez', '22', 'masculino', '76.25', 'blanco', 'Ibarra');

INSERT INTO `torneo_jiujitsu_db`.`llave` (`id_llave`, `categoría_sexo`, `categoría_peso`, 
`categoría_cinturón`) 
VALUES ('01', 'masculino', 'mediano', 'blanco');

INSERT INTO `torneo_jiujitsu_db`.`encuentro` (`id_encuentro`, `id_llave`) 
VALUES 
	('01', '01'),
    ('02', '01'),
    ('03', '01');

INSERT INTO `torneo_jiujitsu_db`.`competidor_encuentro` (`id_competidor_encuentro`, 
`id_encuentro`, `dni_competidor`, `puntos`, `ventajas`, `faltas`, `finalización`) 
VALUES 
	('01', '01', '41111111', '5', '1', '0', '0'),
    ('02', '01', '42222222', '2', '0', '0', '0'),
    ('03', '02', '43333333', '8', '0', '0', '1'),
    ('04', '02', '44444444', '3', '0', '0', '0'),
    ('05', '03', '41111111', '0', '0', '0', '0'),
    ('06', '03', '43333333', '5', '1', '0', '0');

-- Consulta de datos --
SELECT
	LL.id_llave AS 'Llave n°',
    LL.categoría_peso AS 'Categoría',
    EN.id_encuentro AS 'Encuentro n°',
    CO.nombre AS "Nombre",
    CE.puntos AS 'Puntos'
FROM
    llave AS LL,
    encuentro AS EN,
    competidor AS CO,
    competidor_encuentro AS CE
WHERE
    LL.id_llave = EN.id_llave
    AND EN.id_encuentro = CE.id_encuentro
    AND CE.dni_competidor = CO.dni;

SET SQL_SAFE_UPDATES = 0;

-- Borrado de datos --
DELETE FROM competidor_encuentro;
DELETE FROM encuentro;
DELETE FROM llave;
DELETE FROM competidor;

SELECT * FROM competidor_encuentro;
SELECT * FROM encuentro;
SELECT * FROM llave;
SELECT * FROM competidor;

SET SQL_SAFE_UPDATES = 1;