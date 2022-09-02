CREATE FUNCTION personFullName(f FNT)
	RETURNS TEXT AS
	$$
		SELECT f.TITLE_NAME || ' ' || f.FIRST_NAME || ' ' || f.SURNAME FROM Student
	$$
	LANGUAGE SQL;

CREATE FUNCTION ageInYears(t TIMESTAMP)
	RETURNS TEXT AS
	$$
		SELECT CAST(date_part ('year', age(t)) AS TEXT) || ' Years';
	$$
	LANGUAGE SQL;

CREATE FUNCTION isRegisteredFor(category TEXT, num SNDom )
	RETURNS BOOLEAN AS
	$$
		SELECT category = ANY(course_Registration) FROM Undergraduate WHERE Student_Number = num;
	$$
	LANGUAGE SQL;

CREATE FUNCTION isFinalYear(num SNDom )
	RETURNS BOOLEAN AS
	$$
		SELECT Number_of_Years = year_of_Study FROM Undergraduate, Degree_Program WHERE Student_Number = num;
	$$
	LANGUAGE SQL;


CREATE FUNCTION  isPartTime(num SNDom)
	RETURNS BOOLEAN AS
	$$
		SELECT category = 'Part Time' FROM Postgraduate WHERE Student_Number = num;
	$$
	LANGUAGE SQL;

CREATE FUNCTION  isFullTime(num SNDom)
	RETURNS BOOLEAN AS
	$$
		SELECT category = 'Full Time' FROM Postgraduate WHERE Student_Number = num;
	$$ LANGUAGE SQL;


SELECT Student_ID, Student_Number, personFullName(Full_Name), Age_In_Years(date_of_Birth::TIMESTAMP) FROM Student;

SELECT Student_ID, Student_Number, PersonFullName(Full_Name), degree_Code, year_of_Study, course_Registration FROM Undergraduate;

SELECT Student_ID, Student_Number, PersonFullName(Full_Name), degree_Code, year_of_Study, category, personFullName(supervisor) FROM Postgraduate;

SELECT Student_ID, Student_Number, PersonFullName(Full_Name), u.degree_Code, year_of_Study, course_Registration FROM Undergraduate u,
	Degree_Program d WHERE d.degree_code = u.degree_code
	AND isFinalYear(Student_Number) = TRUE;

SELECT Student_ID, Student_Number, personFullName(Full_Name), degree_Code, year_of_Study, course_Registration FROM Undergraduate
	WHERE isRegisteredFor('COS326', Student_Number ) = TRUE;

SELECT Student_ID, Student_Number, personFullName(Full_Name), degree_Code, year_of_Study, category, personFullName(supervisor) FROM Postgraduate
	WHERE Check_Full_Time(Student_Number) = TRUE;

SELECT Student_ID, Student_Number, personFullName(Full_Name), degree_Code, year_of_Study, category, personFullName(supervisor) FROM Postgraduate
	WHERE Check_Part_Time(Student_Number) = TRUE;




