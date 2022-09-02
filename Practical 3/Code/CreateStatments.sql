CREATE DOMAIN SNDom As TEXT
CHECK(LENGTH(VALUE) = 6);

CREATE SEQUENCE SEQSID START 0;
CREATE SEQUENCE SEQDPID START 0;
CREATE SEQUENCE SEQCSID START 0;

CREATE TYPE FNT AS (TITLE TEXT, FIRST_NAME TEXT, SURNAME TEXT);

CREATE TABLE Course(
	Course_ID INTEGER PRIMARY KEY,
	Course_Code TEXT,
	Course_Name TEXT,
	Department TEXT,
	Credits INTEGER
);
CREATE TABLE Degree_Program(
	Degree_ID INTEGER PRIMARY KEY,
	Degree_Code TEXT,
	Degree_Name TEXT,
	Number_of_Years INTEGER,
	Faculty TEXT
);

CREATE TABLE Student (
	Student_ID INTEGER PRIMARY KEY,
	Full_Name FNT,
	Student_Number SNDom,
	date_of_Birth DATE,
	degree_Code TEXT,
	year_of_Study INTEGER
);

CREATE TABLE Undergraduate(
	course_Registration TEXT[]
)	INHERITS(Student);

CREATE TABLE Postgraduate(
	supervisor FNT,
	category TEXT
)	INHERITS(Student);




