-- create user sequence and table
CREATE SEQUENCE user_sequence INCREMENT BY 1 MINVALUE 1;

CREATE TABLE IF NOT EXISTS application_users(
id BIGINT NOT NULL PRIMARY KEY DEFAULT NEXTVAL('user_sequence'), first_name VARCHAR(255),
last_name VARCHAR(255), email VARCHAR(255), password VARCHAR(355), roles VARCHAR(25)
);

-- create product sequence and table
CREATE SEQUENCE product_sequence INCREMENT BY 1 MINVALUE 1;

CREATE TABLE IF NOT EXISTS products (
    id BIGINT NOT NULL PRIMARY KEY DEFAULT NEXTVAL('product_sequence'),
    product_name VARCHAR(50) NOT NULL,
    product_description VARCHAR(255),
    product_category VARCHAR(50) NOT NULL,
    price NUMERIC NOT NULL,
    creation_date DATE,
    quantity NUMERIC
);

-- create clients sequence and table
CREATE SEQUENCE client_id_sequence INCREMENT BY 1 MINVALUE 1;

CREATE TABLE IF NOT EXISTS clients (
        id BIGINT NOT NULL PRIMARY KEY DEFAULT NEXTVAL('client_id_sequence'),
        first_name VARCHAR(255),
        last_name VARCHAR(255), email VARCHAR(255),
        mobile VARCHAR(25),
        country VARCHAR(25),
        province VARCHAR(25),
        streetName VARCHAR(25),
        unitNumber VARCHAR(25)
);

-- create sales sequence and table

CREATE SEQUENCE sales_sequence INCREMENT BY 1 MINVALUE 1;

CREATE TABLE IF NOT EXISTS sales (
            id BIGINT NOT NULL PRIMARY KEY DEFAULT NEXTVAL('sales_sequence'),
            creation_date DATE,
            sellers_id BIGINT,
            FOREIGN KEY (sellers_id) REFERENCES application_users(id),
            clients_id BIGINT,
            FOREIGN KEY (clients_id) REFERENCES clients(id)
);