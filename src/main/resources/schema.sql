-- codecomparison

CREATE TABLE TASK(
   id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
   taskName VARCHAR(50) NULL,
   active boolean NULL,
   creationTime Timestamp NOT NULL,
   lastSyncTime Timestamp NULL,
   t int NOT NULL,
   k int NOT NULL
);

CREATE TABLE ROUND(
   id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
   task_id BIGINT NULL,
   year VARCHAR(9) NULL,
   semester VARCHAR(20) NULL,
   subject VARCHAR(6) NULL,
   roundName VARCHAR(100) NULL,
   url int NULL
);

CREATE TABLE VERSION(
   id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
   abstractionVersionId int NOT NULL,
   similarityVersionId int NOT NULL,
   defaultT int NOT NULL,
   defaultK int NOT NULL
);

CREATE TABLE SAVEDCOMPARISON(
   id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
   task_id BIGINT NOT NULL,
   version_id BIGINT NOT NULL,
   firstStudentId int NOT NULL,
   secondStudentId int NOT NULL,
   firstAttemptId BIGINT NOT NULL,
   secondAttemptId BIGINT NOT NULL,
   firstToSecondResult DOUBLE NOT NULL,
   secondToFirstResult DOUBLE NOT NULL,
   firstToSecondIsInfinite boolean NULL,
   secondToFirstIsInfinite boolean NULL
);

CREATE TABLE ATTEMPT(
   id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
   moodleId int NOT NULL,
   round_id BIGINT NULL,
   task_id BIGINT NULL,
   student_id int NULL,
   fileName VARCHAR(100),
   code TEXT NULL,
   codeAcquired boolean,
   isBoilerplate boolean
);

CREATE TABLE STUDENT(
   id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
   moodleId int NOT NULL,
   fullName VARCHAR(100)
);

CREATE TABLE ABSTRACTEDCODE(
   id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
   task_id BIGINT NOT NULL,
   attempt_id BIGINT not NULL,
   version_id BIGINT not NULL,
   abstractedCode TEXT NULL
);

-- codecomparison constraints

ALTER TABLE STUDENT ADD INDEX (moodleId);
ALTER TABLE ATTEMPT ADD INDEX (moodleId);
-- ALTER TABLE SAVEDCOMPARISON ADD INDEX (firstAttemptId);
ALTER TABLE SAVEDCOMPARISON ADD INDEX (secondAttemptId);
-- ALTER TABLE SAVEDCOMPARISON ADD INDEX (firstStudentId);
-- ALTER TABLE SAVEDCOMPARISON ADD INDEX (secondStudentId);

ALTER TABLE ROUND 
    ADD CONSTRAINT FK_round_task FOREIGN KEY(task_id)
    REFERENCES TASK (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE;
    
ALTER TABLE SAVEDCOMPARISON 
    ADD CONSTRAINT FK_savedcomparison_task FOREIGN KEY(task_id)
    REFERENCES TASK (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE;
    
ALTER TABLE ATTEMPT 
    ADD CONSTRAINT FK_attempt_round FOREIGN KEY(round_id)
    REFERENCES ROUND (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE;
    
ALTER TABLE ATTEMPT 
    ADD CONSTRAINT FK_attempt_task FOREIGN KEY(task_id)
    REFERENCES TASK (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE;
    
ALTER TABLE SAVEDCOMPARISON 
    ADD CONSTRAINT FK_savedcomparison_first_student FOREIGN KEY(firstStudentId)
    REFERENCES STUDENT (moodleId)
    ON DELETE CASCADE
    ON UPDATE CASCADE;
    
ALTER TABLE SAVEDCOMPARISON 
    ADD CONSTRAINT FK_savedcomparison_second_student FOREIGN KEY(secondStudentId)
    REFERENCES STUDENT (moodleId)
    ON DELETE CASCADE
    ON UPDATE CASCADE;
    
ALTER TABLE ATTEMPT 
    ADD CONSTRAINT FK_attempt_student FOREIGN KEY(student_id)
    REFERENCES STUDENT (moodleId)
    ON DELETE CASCADE
    ON UPDATE CASCADE;
    
ALTER TABLE SAVEDCOMPARISON 
    ADD CONSTRAINT FK_savedcomparison_first_attempt FOREIGN KEY(firstAttemptId)
    REFERENCES ATTEMPT (moodleId)
    ON DELETE CASCADE
    ON UPDATE CASCADE;
    
ALTER TABLE SAVEDCOMPARISON 
    ADD CONSTRAINT FK_savedcomparison_second_attempt FOREIGN KEY(secondAttemptId)
    REFERENCES ATTEMPT (moodleId)
    ON DELETE CASCADE
    ON UPDATE CASCADE;
    
ALTER TABLE ABSTRACTEDCODE 
    ADD CONSTRAINT FK_abstractedcode_attempt FOREIGN KEY(attempt_id)
    REFERENCES ATTEMPT (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE;
    
ALTER TABLE SAVEDCOMPARISON 
    ADD CONSTRAINT FK_savedcomparison_version FOREIGN KEY(version_id)
    REFERENCES VERSION (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE;
    
ALTER TABLE ABSTRACTEDCODE 
    ADD CONSTRAINT FK_abstractedcode_version FOREIGN KEY(version_id)
    REFERENCES VERSION (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE;
    
-- data
insert into Version (abstractionVersionId,SimilarityVersionId,defaultK,defaultT) values(1,1,13,39);
