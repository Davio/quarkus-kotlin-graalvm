DROP TABLE IF EXISTS movies;

CREATE TABLE movies
(
    id    SERIAL PRIMARY KEY,
    title VARCHAR,
    year  CHAR(4)
);
CREATE INDEX movies_title ON movies (title);
CREATE INDEX movies_year ON movies (year);
