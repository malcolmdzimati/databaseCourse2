INSERT INTO Degree_Program(Degree_ID, Degree_Code, Degree_Name, Number_of_Years, Faculty)
VALUES(nextval('SEQDID'), 'BSc', 'Bachelor of Science', 3, 'EBIT');
INSERT INTO Degree_Program(Degree_ID, Degree_Code, Degree_Name, Number_of_Years, Faculty)
VALUES(nextval('SEQDID'), 'BIT', 'Bachelor of IT', 4, 'EBIT');
INSERT INTO Degree_Program(Degree_ID, Degree_Code, Degree_Name, Number_of_Years, Faculty)
VALUES(nextval('SEQDPID'), 'PhD', 'Philosophiae Doctor', 5, 'EBIT');


INSERT INTO Course(Course_ID, Course_Code, Course_Name, Department, Credits)
VALUES (nextval('SEQCSID'), 'COS301','Software Engineering', 'Computer Science', 40);
INSERT INTO Course(Course_ID, Course_Code, Course_Name, Department, Credits)
VALUES (nextval('SEQCSID'), 'COS326','Database Systems', 'Computer Science', 20);
INSERT INTO Course(Course_ID, Course_Code, Course_Name, Department, Credits)
VALUES (nextval('SEQCSID'), 'MTH301','Discrete Mathematics', 'Mathematics', 15);
INSERT INTO Course(Course_ID, Course_Code, Course_Name, Department, Credits)
VALUES (nextval('SEQCSID'), 'PHL301','Logical', 'Philosophy', 15);

SET datestyle = "DMY";
INSERT INTO Undergraduate(Student_ID, Full_Name, Student_Number, date_of_Birth, degree_Code, year_of_Study, course_Registration)
VALUES(nextval('SEQSID'),ROW('Miss', 'Gaynor','Mpofu'),'140010','10-01-1996', 'BSc', 3, '{COS301, COS326, MTH301}');
INSERT INTO Undergraduate(Student_ID, Full_Name, Student_Number, date_of_Birth, degree_Code, year_of_Study, course_Registration)
VALUES(nextval('SEQSID'),ROW('Mr', 'Monga','Tshuma'),'140015','25-05-1995', 'BSc', 3, '{COS301, PHL301, MTH301}');
INSERT INTO Undergraduate(Student_ID, Full_Name, Student_Number, date_of_Birth, degree_Code, year_of_Study, course_Registration)
VALUES(nextval('SEQSID'),ROW('Mrs', 'Mulalo','Gadeba'),'131120','30-01-1995', 'BIT', 3, '{COS301, COS326, PHL301}');
INSERT INTO Undergraduate(Student_ID, Full_Name, Student_Number, date_of_Birth, degree_Code, year_of_Study, course_Registration)
VALUES(nextval('SEQSID'),ROW('Miss', 'Karen','Dzimati'),'131140','20-02-1996', 'BIT', 4, '{COS301, COS326, MTH301 ,PHL301}');

INSERT INTO Postgraduate(Student_ID, Full_Name, Student_Number, date_of_Birth, degree_Code, year_of_Study,supervisor, category)
VALUES(nextval('SEQSID'),ROW('Miss', 'Tlholo','Koma'),'101122','15-06-1987', 'PhD', 2, ROW('Dr', 'Daniel', 'James'), 'Full-Time' );
INSERT INTO Postgraduate(Student_ID, Full_Name, Student_Number, date_of_Birth, degree_Code, year_of_Study,supervisor, category)
VALUES(nextval('SEQSID'),ROW('Mr', 'Mugove','Dzimati'),'121101','27-04-1985', 'PhD', 3, ROW('Prof', 'Ishe', 'Goat'), 'Part-Time' );


