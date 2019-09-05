DROP TABLE IF EXISTS vet_specialties;
DROP TABLE IF EXISTS vets;
DROP TABLE IF EXISTS specialties;
DROP TABLE IF EXISTS visits;
DROP TABLE IF EXISTS pets;
DROP TABLE IF EXISTS types;
DROP TABLE IF EXISTS owners;

CREATE EXTENSION IF NOT EXISTS citext;

CREATE TABLE vets
(
    id         SERIAL PRIMARY KEY,
    first_name VARCHAR(30),
    last_name  VARCHAR(30)
);
CREATE INDEX vets_last_name ON vets (last_name);

CREATE TABLE specialties
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(80)
);
CREATE INDEX specialties_name ON specialties (name);

CREATE TABLE vet_specialties
(
    vet_id       INTEGER NOT NULL,
    specialty_id INTEGER NOT NULL
);
ALTER TABLE vet_specialties
    ADD CONSTRAINT fk_vet_specialties_vets FOREIGN KEY (vet_id) REFERENCES vets (id);
ALTER TABLE vet_specialties
    ADD CONSTRAINT fk_vet_specialties_specialties FOREIGN KEY (specialty_id) REFERENCES specialties (id);

CREATE TABLE types
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(80)
);
CREATE INDEX types_name ON types (name);

CREATE TABLE owners
(
    id         SERIAL PRIMARY KEY,
    first_name VARCHAR(30),
    last_name  CITEXT,
    address    VARCHAR(255),
    city       VARCHAR(80),
    telephone  VARCHAR(20)
);
CREATE INDEX owners_last_name ON owners (last_name);

CREATE TABLE pets
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(30),
    birth_date DATE,
    type_id    INTEGER NOT NULL,
    owner_id   INTEGER NOT NULL
);
ALTER TABLE pets
    ADD CONSTRAINT fk_pets_owners FOREIGN KEY (owner_id) REFERENCES owners (id);
ALTER TABLE pets
    ADD CONSTRAINT fk_pets_types FOREIGN KEY (type_id) REFERENCES types (id);
CREATE INDEX pets_name ON pets (name);

CREATE TABLE visits
(
    id          SERIAL PRIMARY KEY,
    pet_id      INTEGER NOT NULL,
    visit_date  DATE,
    description VARCHAR(255)
);
ALTER TABLE visits
    ADD CONSTRAINT fk_visits_pets FOREIGN KEY (pet_id) REFERENCES pets (id);
CREATE INDEX visits_pet_id ON visits (pet_id);


INSERT INTO vets
VALUES (default, 'James', 'Carter');
INSERT INTO vets
VALUES (default, 'Helen', 'Leary');
INSERT INTO vets
VALUES (default, 'Linda', 'Douglas');
INSERT INTO vets
VALUES (default, 'Rafael', 'Ortega');
INSERT INTO vets
VALUES (default, 'Henry', 'Stevens');
INSERT INTO vets
VALUES (default, 'Sharon', 'Jenkins');

INSERT INTO specialties
VALUES (default, 'radiology');
INSERT INTO specialties
VALUES (default, 'surgery');
INSERT INTO specialties
VALUES (default, 'dentistry');

INSERT INTO vet_specialties
VALUES (2, 1);
INSERT INTO vet_specialties
VALUES (3, 2);
INSERT INTO vet_specialties
VALUES (3, 3);
INSERT INTO vet_specialties
VALUES (4, 2);
INSERT INTO vet_specialties
VALUES (5, 1);

INSERT INTO types
VALUES (default, 'cat');
INSERT INTO types
VALUES (default, 'dog');
INSERT INTO types
VALUES (default, 'lizard');
INSERT INTO types
VALUES (default, 'snake');
INSERT INTO types
VALUES (default, 'bird');
INSERT INTO types
VALUES (default, 'hamster');

INSERT INTO owners
VALUES (default, 'George', 'Franklin', '110 W. Liberty St.', 'Madison', '6085551023');
INSERT INTO owners
VALUES (default, 'Betty', 'Davis', '638 Cardinal Ave.', 'Sun Prairie', '6085551749');
INSERT INTO owners
VALUES (default, 'Eduardo', 'Rodriquez', '2693 Commerce St.', 'McFarland', '6085558763');
INSERT INTO owners
VALUES (default, 'Harold', 'Davis', '563 Friendly St.', 'Windsor', '6085553198');
INSERT INTO owners
VALUES (default, 'Peter', 'McTavish', '2387 S. Fair Way', 'Madison', '6085552765');
INSERT INTO owners
VALUES (default, 'Jean', 'Coleman', '105 N. Lake St.', 'Monona', '6085552654');
INSERT INTO owners
VALUES (default, 'Jeff', 'Black', '1450 Oak Blvd.', 'Monona', '6085555387');
INSERT INTO owners
VALUES (default, 'Maria', 'Escobito', '345 Maple St.', 'Madison', '6085557683');
INSERT INTO owners
VALUES (default, 'David', 'Schroeder', '2749 Blackhawk Trail', 'Madison', '6085559435');
INSERT INTO owners
VALUES (default, 'Carlos', 'Estaban', '2335 Independence La.', 'Waunakee', '6085555487');

INSERT INTO pets
VALUES (default, 'Leo', '2010-09-07', 1, 1);
INSERT INTO pets
VALUES (default, 'Basil', '2012-08-06', 6, 2);
INSERT INTO pets
VALUES (default, 'Rosy', '2011-04-17', 2, 3);
INSERT INTO pets
VALUES (default, 'Jewel', '2010-03-07', 2, 3);
INSERT INTO pets
VALUES (default, 'Iggy', '2010-11-30', 3, 4);
INSERT INTO pets
VALUES (default, 'George', '2010-01-20', 4, 5);
INSERT INTO pets
VALUES (default, 'Samantha', '2012-09-04', 1, 6);
INSERT INTO pets
VALUES (default, 'Max', '2012-09-04', 1, 6);
INSERT INTO pets
VALUES (default, 'Lucky', '2011-08-06', 5, 7);
INSERT INTO pets
VALUES (default, 'Mulligan', '2007-02-24', 2, 8);
INSERT INTO pets
VALUES (default, 'Freddy', '2010-03-09', 5, 9);
INSERT INTO pets
VALUES (default, 'Lucky', '2010-06-24', 2, 10);
INSERT INTO pets
VALUES (default, 'Sly', '2012-06-08', 1, 10);

INSERT INTO visits
VALUES (default, 7, '2013-01-01', 'rabies shot');
INSERT INTO visits
VALUES (default, 8, '2013-01-02', 'rabies shot');
INSERT INTO visits
VALUES (default, 8, '2013-01-03', 'neutered');
INSERT INTO visits
VALUES (default, 7, '2013-01-04', 'spayed');
