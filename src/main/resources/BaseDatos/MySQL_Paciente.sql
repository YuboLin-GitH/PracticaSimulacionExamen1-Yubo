DROP DATABASE IF EXISTS CentroMedico;
CREATE DATABASE CentroMedico;
Use CentroMedico;


CREATE TABLE IF NOT EXISTS Pacientes(
    idPaciente int unsigned auto_increment primary key,
    dni varchar(9),
    Pass varchar(256),
    Nombre varchar(100),
    Direccion varchar(100),
    Telefono varchar(9)
    )ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 AUTO_INCREMENT=1;


CREATE TABLE IF NOT EXISTS Especialidades (
      idEspecialidad int unsigned auto_increment primary key,
      nombreEspecialidad varchar(45)
    );




INSERT INTO Pacientes  VALUES
       (1, "12345678A", SHA2("pass1",256), "Juan Pedro Gutierrez Soriano", "C/Manzana nº1", "123456789"),
       (2, "23456789B", SHA2("pass2",256), "Pedro García López", "C/Cereza nº2", "234567891"),
       (3, "34567891C", SHA2("pass3",256), "Juan Gómez Alario", "C/Sandía nº3", "345678912"),
       (4, "45678912D", SHA2("pass4",256), "María Molinero Sánchez", "C/Melón nº4", "456789123"),
       (5, "56789123E", SHA2("pass5",256), "Ana Martín López", "C/Plátano nº5", "567891234"),
       (6, "67891234F", SHA2("pass6",256), "Ángel Hernández García", "C/Pera nº6", "678912345"),
       (7, "78912345G", SHA2("pass7",256), "Sandra Garrido Gómez", "C/Naranja nº7", "789123456");


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