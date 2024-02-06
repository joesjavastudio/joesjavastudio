DROP TABLE IF EXISTS Blog;

CREATE TABLE Blog (
      id varchar(255) NOT NULL primary key,
      title varchar(255) NOT NULL,
      content varchar(255) NOT NULL
);

INSERT INTO Blog
(id, title, content)
VALUES (1,'Hello, World!','hello-world');