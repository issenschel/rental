use rental;

CREATE TABLE users (
  id INT NOT NULL AUTO_INCREMENT,
  username VARCHAR(30) NOT NULL UNIQUE,
  password VARCHAR(80) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE roles (
  id tinyint NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE users_roles (
  user_id INT NOT NULL,
  role_id tinyint NOT NULL,
  PRIMARY KEY (user_id, role_id),
  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE TABLE cassettes(
	id INT NOT NULL AUTO_INCREMENT,
	name varchar(80) UNIQUE NOT NULL,
	photo varchar(80) UNIQUE,
    description VARCHAR(150) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE clients (
  id INT NOT NULL AUTO_INCREMENT,
  phone varchar(11) UNIQUE NOT NULL,
  name varchar(80) NOT NULL,
  surname varchar(80) NOT NULL,
  patronymic varchar(80),
  issued varchar(70) NOT NULL,
  issued_date date NOT NULL,
  passport_series varchar(4) NOT NULL,
  passport_number varchar(6) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE rentals(
	id INT NOT NULL AUTO_INCREMENT,
    client_id INT NOT NULL,
    cassette_id INT NOT NULL,
    status tinyint NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (client_id) REFERENCES clients (id),
    FOREIGN KEY (cassette_id) REFERENCES cassettes (id)
);