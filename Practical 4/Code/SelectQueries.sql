CREATE FUNCTION isValidCourseCode(VARCHAR)
	RETURNS BOOLEAN AS
	$$
		DECLARE
                        cc VARCHAR;
                        sc course%rowtype;
                BEGIN
                        SELECT * FROM course
                        INTO sc
                        WHERE course.course_code = cc;

                        IF NOT FOUND THEN
                             RETURN FALSE;
                        END IF;

                        RETURN TRUE;
                END;
	$$
	LANGUAGE plpsql;

CREATE FUNCTION hasValidCourseCode(VARCHAR[])
        RETURNS BOOLEAN AS
        $$
		DECLARE
			cc VARCHAR;
			sc Course%rowtype;
                BEGIN
			FOREACH cs IN ARRAY $1
			LOOP
				SELECT * FROM Course
				INTO sc
				WHERE Course.Course_Code = cc;

				IF NOT FOUND THEN
					RETURN FALSE;
				END IF;
			END LOOP;

			RETURN TRUE;
		END;
        $$
        LANGUAGE plpsql;

CREATE FUNCTION isValidDegreeCode(VARCHAR)
        RETURNS BOOLEAN AS
        $$
                DECLARE
                        dc VARCHAR;
                        sc Degree_Program%rowtype;
                BEGIN
                        SELECT * FROM degree
                        INTO sc
                        WHERE Degree_Program.Degree_Code = dc;

                        IF NOT FOUND THEN
                             RETURN FALSE;
                        END IF;

                        RETURN TRUE;
                END;
        $$
        LANGUAGE plpsql;

CREATE FUNCTION courseCodeFrequency(VARCHAR[], VARCHAR)
        RETURNS BOOLEAN AS
        $$
                DECLARE
                        cc INTEGER;
                BEGIN
                        cc := 0;
                        FOREACH cs IN ARRAY $1
                        LOOP
                                IF cs EQUAL $2 THEN
                                        cc=cc+1;
                                END IF;
                        END LOOP;

                        RETURN cc;
                END;
        $$
        LANGUAGE plpsql;


CREATE FUNCTION check_valid_degree_code()
	RETURNS trigger AS
	$check_valid_degree$
		BEGIN
			IF isValidDegreeCode(NEW.Degree_Code) = FALSE THEN
                                RAISE EXCEPTION 'Invalid degree code';
                        END IF;

                        RETURN NEW;
		END;
	$check_valid_degree$
	LANGUAGE plpgsql;

CREATE FUNCTION check_valid_degree_code()
        RETURNS trigger AS
        $check_valid_degree$
                BEGIN
                     IF hasValidCourseCodes(NEW.courseRegistration) = FALSE THEN
                                RAISE EXCEPTION 'Invalid course regsitarion';
                     ELSIF hasDuplicateCourseCode(NEW.courseRegistration) = TRUE THEN 
                                RAISE EXCEPTION 'Duplicate course codes';
                     END IF;
                     RETURN NEW;
                END;
        $check_valid_degree$
        LANGUAGE plpgsql;

CREATE FUNCTION record_delete_undergrad()
        RETURNS trigger AS
        $$
                BEGIN
			INSERT INTO DeletedUndergrad
				SELECT OLD.*, now(), user;
                END;
        $$
        LANGUAGE plpgsql;

CREATE FUNCTION record_delete_postgrad()
        RETURNS trigger AS
        $$
                BEGIN
                        INSERT INTO DeletedPostgrad
				SELECT OLD.*, now(), user;
                END;
        $$
        LANGUAGE plpgsql;



CREATE TRIGGER check_valid_degree 
   BEFORE INSERT & UPDATE
   ON Student, Undergraduate, Postgraduate
   FOR EACH STATEMENT
       EXECUTE PROCEDURE check_valid_degree_code();

CREATE TRIGGER check_valid_course_registration 
   BEFORE INSERT & UPDATE
   ON Undergraduate
   FOR EACH STATEMENT
       EXECUTE PROCEDURE check_valid_course_codes();

CREATE TRIGGER audit_delete_undergrad
   BEFORE INSERT UPDATE
   ON Undergraduate
   FOR EACH ROW
       EXECUTE PROCEDURE record_delete_undergrad();

CREATE TRIGGER audit_delete_postgrad
   BEFORE INSERT UPDATE
   ON Postgraduate
   FOR EACH ROW
       EXECUTE PROCEDURE record_delete_postgrad();