DROP TABLE VETERINARIOS IF EXISTS;

CREATE TABLE VETERINARIOS(
ID INT AUTO_INCREMENT PRIMARY KEY,
NROLICENCIA VARCHAR(50) NOT NULL,
NOMBRE VARCHAR(50) NOT NULL,
APELLIDO VARCHAR(50) NOT NULL,
ESPECIALIDAD VARCHAR(50) NOT NULL
);


INSERT INTO VETERINARIOS VALUES (DEFAULT,'22345','Eli' , 'Penia', 'Urloga');
