CREATE SEQUENCE briana_sequence START WITH 1000;

CREATE TABLE addresses (
    id       INTEGER PRIMARY KEY DEFAULT nextval('briana_sequence'),
    country  VARCHAR NOT NULL,
    city     VARCHAR NOT NULL,
    postcode VARCHAR NOT NULL
);

CREATE TABLE organizations (
   id          INTEGER PRIMARY KEY DEFAULT nextval('briana_sequence'),
   name        VARCHAR NOT NULL,
   description VARCHAR,
   currency    VARCHAR NOT NULL
);

CREATE TABLE clients (
    id              INTEGER PRIMARY KEY DEFAULT nextval('briana_sequence'),
    name            VARCHAR NOT NULL,
    phone           VARCHAR NOT NULL,
    email           VARCHAR NOT NULL,
    description     VARCHAR,
    address_id      INTEGER NOT NULL,
    created         TIMESTAMP DEFAULT now()::timestamp(0) NOT NULL,
    organization_id INTEGER NOT NULL,
    FOREIGN KEY (organization_id) REFERENCES organizations(id),
    FOREIGN KEY (address_id) REFERENCES addresses(id)
);

CREATE INDEX clients_unique_email_phone_idx ON clients(email, phone);

CREATE TABLE positions (
    id              INTEGER PRIMARY KEY DEFAULT nextval('briana_sequence'),
    name            VARCHAR NOT NULL,
    description     VARCHAR,
    organization_id INTEGER NOT NULL,
    FOREIGN KEY (organization_id) REFERENCES organizations(id)
);

CREATE TABLE employees (
    id              INTEGER PRIMARY KEY DEFAULT nextval('briana_sequence'),
    name            VARCHAR NOT NULL,
    phone           VARCHAR NOT NULL,
    email           VARCHAR NOT NULL,
    registered      TIMESTAMP DEFAULT now()::timestamp(0) NOT NULL,
    enabled         BOOLEAN DEFAULT TRUE,
    description     VARCHAR,
    address_id      INTEGER,
    password        VARCHAR NOT NULL,
    photo_path      VARCHAR NOT NULL,
    position_id     INTEGER NOT NULL,
    organization_id INTEGER NOT NULL,
    FOREIGN KEY (position_id) REFERENCES positions(id),
    FOREIGN KEY (organization_id) REFERENCES organizations(id),
    FOREIGN KEY (address_id) REFERENCES addresses(id)
);

CREATE UNIQUE INDEX employees_unique_email_phone_idx ON clients(email, phone);

CREATE TABLE events (
    id              INTEGER PRIMARY KEY DEFAULT nextval('briana_sequence'),
    message         VARCHAR NOT NULL,
    type            VARCHAR(255) NOT NULL,
    published       TIMESTAMP DEFAULT now()::timestamp(0) NOT NULL,
    publisher_id    INTEGER NOT NULL,
    organization_id INTEGER NOT NULL,
    FOREIGN KEY (organization_id) REFERENCES organizations(id),
    FOREIGN KEY (publisher_id) REFERENCES employees(id)
);

CREATE TABLE positions_permissions (
    position_id   INTEGER NOT NULL,
    permission    VARCHAR NOT NULL,
    FOREIGN KEY (position_id) REFERENCES positions(id),
    CONSTRAINT unique_position_id_permission UNIQUE (position_id, permission)
);

CREATE TABLE categories (
    id              INTEGER PRIMARY KEY DEFAULT nextval('briana_sequence'),
    name            VARCHAR NOT NULL,
    description     VARCHAR,
    image_path      VARCHAR NOT NULL,
    organization_id INTEGER NOT NULL,
    FOREIGN KEY (organization_id) REFERENCES organizations(id)
);

CREATE UNIQUE INDEX categories_unique_name_idx ON categories(name);

CREATE TABLE products (
    id              INTEGER PRIMARY KEY DEFAULT nextval('briana_sequence'),
    name            VARCHAR NOT NULL,
    description     VARCHAR,
    price           NUMERIC(15, 2) NOT NULL,
    image_path      VARCHAR NOT NULL,
    category_id     INTEGER NOT NULL,
    organization_id INTEGER NOT NULL,
    FOREIGN KEY (organization_id) REFERENCES organizations(id),
    FOREIGN KEY (category_id) REFERENCES categories(id)
);

CREATE UNIQUE INDEX products_unique_name_organization_id_idx ON products(name, organization_id);

CREATE TABLE orders (
    id              INTEGER PRIMARY KEY DEFAULT nextval('briana_sequence'),
    created         TIMESTAMP DEFAULT now()::timestamp(0) NOT NULL,
    organization_id INTEGER NOT NULL,
    FOREIGN KEY (organization_id) REFERENCES organizations(id)
);

CREATE TABLE orders_products (
    order_id   INTEGER NOT NULL,
    product_id INTEGER NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE clients_orders (
    client_id INTEGER NOT NULL,
    order_id  INTEGER NOT NULL,
    FOREIGN KEY (client_id) REFERENCES clients(id),
    FOREIGN KEY (order_id) REFERENCES orders(id)
);
