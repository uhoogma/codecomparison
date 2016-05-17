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
   firstStudentId BIGINT NOT NULL,
   secondStudentId BIGINT NOT NULL,
   firstAttemptId BIGINT NOT NULL,
   secondAttemptId BIGINT NOT NULL,
   firstToSecondResult DOUBLE NOT NULL,
   secondToFirstResult DOUBLE NOT NULL,
   firstToSecondIsInfinite boolean NULL,
   secondToFirstIsInfinite boolean NULL
);

CREATE TABLE ATTEMPT(
   id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
   moodleId BIGINT NOT NULL,
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
   attempt_id BIGINT not NULL,
   version_id BIGINT not NULL,
   abstractedCode TEXT NULL
);

-- codecomparison constraints

ALTER TABLE STUDENT ADD INDEX (moodleId);

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
    REFERENCES STUDENT (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE;
    
ALTER TABLE SAVEDCOMPARISON 
    ADD CONSTRAINT FK_savedcomparison_second_student FOREIGN KEY(secondStudentId)
    REFERENCES STUDENT (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE;
    
ALTER TABLE ATTEMPT 
    ADD CONSTRAINT FK_attempt_student FOREIGN KEY(student_id)
    REFERENCES STUDENT (moodleId)
    ON DELETE CASCADE
    ON UPDATE CASCADE;
    
ALTER TABLE SAVEDCOMPARISON 
    ADD CONSTRAINT FK_savedcomparison_first_attempt FOREIGN KEY(firstAttemptId)
    REFERENCES ATTEMPT (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE;
    
ALTER TABLE SAVEDCOMPARISON 
    ADD CONSTRAINT FK_savedcomparison_second_attempt FOREIGN KEY(secondAttemptId)
    REFERENCES ATTEMPT (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE;
    
ALTER TABLE ABSTRACTEDCODE 
    ADD CONSTRAINT FK_abstractedcode_student FOREIGN KEY(attempt_id)
    REFERENCES STUDENT (id)
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
insert into VERSION (abstractionVersionId,SimilarityVersionId,defaultK,defaultT) values(1,1,13,39);
