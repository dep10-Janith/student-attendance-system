CREATE TABLE IF NOT EXISTS Student
(
    id   VARCHAR(20) PRIMARY KEY,
    name VARCHAR(100) NOT NULL

);
# INSERT INTO Student (id, name)
# VALUES ('nima', 'Nimal');

CREATE TABLE IF NOT EXISTS Attendance
(
    Student_id VARCHAR(20)       NOT NULL,
    id         INT PRIMARY KEY AUTO_INCREMENT,
    Status     ENUM ('IN','OUT') NOT NULL,
    Stamp      DATETIME          NOT NULL,
    CONSTRAINT fk_student_attendance FOREIGN KEY (Student_id) REFERENCES Student (id)

);
# INSERT INTO Attendance (id, Status, Stamp)
# VALUES (20, 'IN', '2020-10-15 10:15:10');

CREATE TABLE IF NOT EXISTS Picture
(
    Student_id VARCHAR(20) PRIMARY KEY,
    picture    MEDIUMBLOB NOT NULL,
    CONSTRAINT fk_student_picture FOREIGN KEY (Student_id) REFERENCES Student (id)
);


CREATE TABLE IF NOT EXISTS User
(
    username  VARCHAR(50) PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    password  VARCHAR(100) NOT NULL
);
# INSERT INTO User (username, full_name, password)
# VALUES ('Admin', 'Administartor', '12345');