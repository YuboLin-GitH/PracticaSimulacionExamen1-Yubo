DROP DATABASE IF EXISTS CentroMedico;
CREATE DATABASE CentroMedico;
Use CentroMedico;


CREATE TABLE IF NOT EXISTS Pacientes(
    idPaciente int unsigned auto_increment NOT NULL primary key,
    dni varchar(9),
    nombre varchar(30),
    password varchar(64),
    direccion varchar(100),
    telefono varchar(9)
    )ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=1;


CREATE TABLE IF NOT EXISTS Especialidades (
      idEspecialidad int unsigned auto_increment primary key,
      nombreEspecialidad varchar(45)
    );




INSERT INTO Pacientes  VALUES
       (1,"12345678A","David",SHA2("david",256),"c/ AAA", 611222333),
       (2,"34564546B","Angel",SHA2("angel",256),"c/ BBB", 611512183),
       (3,"62145448C","Lucia",SHA2("lucia",256),"c/ CCC", 611224013),
       (4,"91321654D","Martina",SHA2("martina",256),"c/ DDD", 618434555),
       (5,"51248345E","Sofia",SHA2("sofia",256),"c/ EEE", 649161161),
       (6,"84345876F","Hugo",SHA2("hugo",256), "c/ FFF", 616713488),
       (7,"81431548G","Leo",SHA2("leo",256),"c/ GGG", 668453178),
       (8,"11501548H","Daniel",SHA2("daniel",256),"c/ HHH", 691246578);



INSERT INTO Especialidades VALUES
       (1, "Cirujía"),
       (2, "Neurología"),
       (3, "Nefrología"),
       (4, "Digestivo"),
       (5, "Unidad de dolor"),
       (6, "Neumología"),
       (7, "Cardiología"),
       (8, "Pediatría"),
       (9, "Oftalmología"),
       (10, "Radiología");


CREATE TABLE Citas (
       idCita int unsigned auto_increment primary key,
       fechaCita Date,
       idEspecialidad Int unsigned,
       idPaciente Int unsigned,
       FOREIGN KEY (idEspecialidad) REFERENCES Especialidades(idEspecialidad),
       FOREIGN KEY (idPaciente) REFERENCES Pacientes(idPaciente)
);


INSERT INTO Citas VALUES
      (1, "2025-11-12",1,1),
      (2, "2025-11-13",5,1),
      (3, "2025-11-12",7,2),
      (4, "2025-11-12",4,3),
      (5, "2025-11-12",2,3),
      (6, "2025-11-12",9,3),
      (7, "2025-11-12",6,4);