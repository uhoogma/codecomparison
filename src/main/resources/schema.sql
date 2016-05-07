CREATE TABLE PERSON (
   id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
   givenName VARCHAR(255) NOT NULL,
   sureName VARCHAR(255) NOT NULL,
   code VARCHAR(255) NOT NULL,
   customerType VARCHAR(255) NULL 
);


CREATE TABLE PHONE (
   id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
   number VARCHAR(255) NOT NULL,
   phoneType VARCHAR(255) NOT NULL
);

CREATE TABLE PERSON_PHONE (
   person_id BIGINT NOT NULL,
   phones_id BIGINT NOT NULL
);

INSERT INTO PERSON (givenName, sureName, code, customerType)
  VALUES ('Jane' ,'Doe', '123', null);
INSERT INTO PERSON ( givenName, sureName, code, customerType)
  VALUES ('John' ,'Doe','456', 'customerType.private');
INSERT INTO PERSON ( givenName, sureName, code, customerType)
  VALUES ('Mad' ,'Max','789', 'customerType.corporate');

-- codecomparison
CREATE TABLE ROUND(
   id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
   task_id BIGINT NULL,
   year VARCHAR(9) NULL,
   semester VARCHAR(20) NULL,
   subject VARCHAR(6) NULL,
   roundName VARCHAR(100) NULL,
   url int NULL,
   FOREIGN KEY (task_id) 
        REFERENCES task(id)
        ON DELETE CASCADE
);

CREATE TABLE TASK(
   id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
   taskName VARCHAR(50) NULL,
   active boolean NULL,
   creationTime Timestamp NOT NULL,
   lastSyncTime Timestamp NULL
);

CREATE TABLE ATTEMPT(
   id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
   round_id BIGINT NULL,
   student_id BIGINT NULL,
   code TEXT NULL,
   codeAcquired boolean,
   isBoilerPlate boolean
);

CREATE TABLE STUDENT(
   id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
   moodleId int not NULL,
   fullName VARCHAR(100)
);

CREATE TABLE SavedComparison(
   id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
   task_id BIGINT not NULL,
   version_id BIGINT not NULL,
   firstStudentId BIGINT not NULL,
   secondStudentId BIGINT not NULL,
   firstAttemptId BIGINT not NULL,
   secondAttemptId BIGINT not NULL,
   t int not null,
   k int not null,
   comparisonResult DOUBLE not null
);

CREATE TABLE AbstractedCode(
   id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
   attempt_id BIGINT not NULL,
   version_id BIGINT not NULL,
   abstractedCode TEXT NULL
);

CREATE TABLE Hash(
   id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
   abstractedCode_id BIGINT not NULL,
   version_id BIGINT not NULL,
   hashPosition int NULL,
   hashValue int NULL
);

CREATE TABLE Version(
   id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
   abstractionVersionId int not NULL,
   SimilarityVersionId int not NULL
);