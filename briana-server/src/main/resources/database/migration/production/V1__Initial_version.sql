CREATE SEQUENCE briana_sequence START WITH 1000;

CREATE TABLE addresses (
    id       INTEGER PRIMARY KEY DEFAULT nextval('briana_sequence'),
    country  VARCHAR NOT NULL,
    city     VARCHAR NOT NULL,
    postcode VARCHAR NOT NULL
);

CREATE TABLE clients (
    id          INTEGER PRIMARY KEY DEFAULT nextval('briana_sequence'),
    name        VARCHAR NOT NULL,
    phone       VARCHAR NOT NULL,
    email       VARCHAR NOT NULL,
    description VARCHAR NOT NULL,
    address_id  INTEGER NOT NULL,
    created     TIMESTAMP DEFAULT now()::timestamp(0) NOT NULL,
    FOREIGN KEY (address_id) REFERENCES addresses(id)
);

CREATE TABLE organizations (
   id          INTEGER PRIMARY KEY DEFAULT nextval('briana_sequence'),
   name        VARCHAR NOT NULL,
   description VARCHAR NOT NULL,
   currency    VARCHAR NOT NULL
);

CREATE INDEX clients_unique_email_phone_idx ON clients(email, phone);

CREATE TABLE positions (
    id          INTEGER PRIMARY KEY DEFAULT nextval('briana_sequence'),
    name        VARCHAR NOT NULL,
    description VARCHAR NOT NULL
);

CREATE TABLE employees (
    id          INTEGER PRIMARY KEY DEFAULT nextval('briana_sequence'),
    name        VARCHAR NOT NULL,
    phone       VARCHAR NOT NULL,
    email       VARCHAR NOT NULL,
    registered  TIMESTAMP DEFAULT now()::timestamp(0) NOT NULL,
    enabled     BOOLEAN DEFAULT TRUE,
    description VARCHAR NOT NULL,
    password    VARCHAR NOT NULL,
    photo_path  VARCHAR NOT NULL,
    position_id INTEGER NOT NULL,
    organization_id INTEGER NOT NULL,
    FOREIGN KEY (position_id) REFERENCES positions(id),
    FOREIGN KEY (organization_id) REFERENCES organizations(id)
);

CREATE UNIQUE INDEX employees_unique_email_phone_idx ON clients(email, phone);

CREATE TABLE permissions (
    id   INTEGER PRIMARY KEY DEFAULT nextval('briana_sequence'),
    name VARCHAR NOT NULL
);

CREATE TABLE positions_permissions (
    position_id   INTEGER NOT NULL,
    permission_id INTEGER NOT NULL,
    FOREIGN KEY (position_id) REFERENCES positions(id),
    FOREIGN KEY (permission_id) REFERENCES permissions(id)
);

CREATE TABLE categories (
    id          INTEGER PRIMARY KEY DEFAULT nextval('briana_sequence'),
    name        VARCHAR NOT NULL,
    description VARCHAR NOT NULL,
    image_path  VARCHAR NOT NULL
);

CREATE UNIQUE INDEX categories_unique_name_idx ON categories(name);

CREATE TABLE products (
    id          INTEGER PRIMARY KEY DEFAULT nextval('briana_sequence'),
    name        VARCHAR NOT NULL,
    description VARCHAR NOT NULL,
    price       NUMERIC(15, 2) NOT NULL,
    image_path  VARCHAR NOT NULL,
    category_id INTEGER NOT NULL,
    FOREIGN KEY (category_id) REFERENCES categories(id)
);

CREATE UNIQUE INDEX products_unique_name_idx ON products(name);

CREATE TABLE orders (
    id        INTEGER PRIMARY KEY DEFAULT nextval('briana_sequence'),
    created   TIMESTAMP DEFAULT now()::timestamp(0) NOT NULL,
    client_id INTEGER NOT NULL,
    FOREIGN KEY (client_id) REFERENCES clients(id)
);

CREATE TABLE clients_orders (
    client_id INTEGER NOT NULL,
    order_id  INTEGER NOT NULL,
    FOREIGN KEY (client_id) REFERENCES clients(id),
    FOREIGN KEY (order_id) REFERENCES orders(id)
);