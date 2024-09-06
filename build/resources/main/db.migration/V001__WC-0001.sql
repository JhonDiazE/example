CREATE TABLE users (
                       id UUID PRIMARY KEY,
                       name VARCHAR(255),
                       email VARCHAR(255) UNIQUE,
                       password VARCHAR(255),
                       token VARCHAR(255),
                       created TIMESTAMP,
                       modified TIMESTAMP,
                       last_login TIMESTAMP,
                       isactive BOOLEAN
);

CREATE TABLE phones (
                        id UUID PRIMARY KEY,
                        number VARCHAR(20),
                        citycode VARCHAR(10),
                        countrycode VARCHAR(10),
                        user_id UUID,
                        FOREIGN KEY (user_id) REFERENCES users(id)
);
