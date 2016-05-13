ALTER TABLE ROUND DROP FOREIGN KEY FK_round_task;
ALTER TABLE SAVEDCOMPARISON DROP FOREIGN KEY FK_savedcomparison_task;
ALTER TABLE ATTEMPT DROP FOREIGN KEY FK_attempt_round;
ALTER TABLE ATTEMPT DROP FOREIGN KEY FK_attempt_task;
ALTER TABLE SAVEDCOMPARISON DROP FOREIGN KEY FK_savedcomparison_first_student;
ALTER TABLE SAVEDCOMPARISON DROP FOREIGN KEY FK_savedcomparison_second_student;
ALTER TABLE ATTEMPT DROP FOREIGN KEY FK_attempt_student;
ALTER TABLE SAVEDCOMPARISON DROP FOREIGN KEY FK_savedcomparison_first_attempt;
ALTER TABLE SAVEDCOMPARISON DROP FOREIGN KEY FK_savedcomparison_second_attempt;
ALTER TABLE ABSTRACTEDCODE DROP FOREIGN KEY FK_abstractedcode_student;
ALTER TABLE SAVEDCOMPARISON DROP FOREIGN KEY FK_savedcomparison_version;
ALTER TABLE ABSTRACTEDCODE DROP FOREIGN KEY FK_abstractedcode_version;

DROP TABLE IF EXISTS ABSTRACTEDCODE;
DROP TABLE IF EXISTS STUDENT;
DROP TABLE IF EXISTS ATTEMPT;
DROP TABLE IF EXISTS SAVEDCOMPARISON;
DROP TABLE IF EXISTS VERSION;
DROP TABLE IF EXISTS ROUND;
DROP TABLE IF EXISTS TASK;
