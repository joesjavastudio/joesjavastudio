DROP TABLE IF EXISTS Subscriber;

CREATE TABLE Subscriber (
      id varchar(255) NOT NULL primary key,
      name varchar(255) NOT NULL
);

INSERT INTO Subscriber
(id, name)
VALUES (1,'Joe');