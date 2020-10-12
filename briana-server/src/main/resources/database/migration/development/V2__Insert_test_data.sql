INSERT INTO organizations (id, name, description, currency)
VALUES (1, 'test-organization-1', 'org.test.description-1', 'USD'),
       (2, 'test-organization-2', 'org.test.description-2', 'EUR');

INSERT INTO positions (id, name, description, organization_id)
VALUES (3, 'accountant', 'simple-accountant', 1),
       (4, 'administrator', 'simple-administrator', 1),
       (5, 'root', 'simple-root:org1', 1),
       (6, 'root', 'simple-root:org2', 2);

INSERT INTO positions_permissions(position_id,  permission)
VALUES (3, 'product:read'),
       (4, 'product:read'),
       (4, 'product:write'),
       (5, 'product:read'),
       (5, 'product:write'),
       (5, 'employee:read'),
       (5, 'employee:write'),
       (6, 'product:read'),
       (6, 'product:write'),
       (6, 'employee:read'),
       (6, 'employee:write');

INSERT INTO addresses (id, country, city, postcode)
VALUES (7, 'France', 'Paris', '324-122-7'),
       (8, 'Germany', 'Berlin', '324-122-8'),
       (9, 'Russia', 'Moscow', '324-122-9'),
       (10, 'Poland', 'Warsaw', '324-122-10');

INSERT INTO employees (id, name, phone, email, enabled, description, address_id,
                       password, photo_path, position_id, organization_id)
VALUES (11, 'test-employee-11', '566-77-11', 'test-11@gmail.com', true, 'test-description', 8,
        '$2y$10$52.OFyDMdNBL6pc8ud3mF.o/ZwISHJZXWWNeqizy4juQO/O6X2CPi', 'path', 3, 1),

       (12, 'test-employee-12', '566-77-12', 'test-12@gmail.com', true, 'test-description', 8,
        '$2y$10$52.OFyDMdNBL6pc8ud3mF.o/ZwISHJZXWWNeqizy4juQO/O6X2CPi', 'path', 4, 1),

       (13, 'test-employee-13', '566-77-13', 'test-13@gmail.com', true, 'test-description', 9,
        '$2y$10$52.OFyDMdNBL6pc8ud3mF.o/ZwISHJZXWWNeqizy4juQO/O6X2CPi', 'path', 5, 1),

       (14, 'test-employee-14', '566-77-14', 'test-14@gmail.com', false, 'test-description', 10,
        '$2y$10$52.OFyDMdNBL6pc8ud3mF.o/ZwISHJZXWWNeqizy4juQO/O6X2CPi', 'path', 6, 2);

INSERT INTO clients (id, name, phone, email, description, address_id, organization_id)
VALUES (15, 'test-client-15', '566-77-15', 'test-15@gmail.com', 'desc:org1', 9, 1),
       (16, 'test-client-16', '566-77-16', 'test-16@gmail.com', 'desc:org1', 8, 1),
       (17, 'test-client-17', '566-77-17', 'test-17@gmail.com', 'desc:org2', 10, 2);

INSERT INTO categories (id, name, description, image_path, organization_id)
VALUES (18, 'test-category-18', 'test-description:18', 'path/to/image/18', 1),
       (19, 'test-category-19', 'test-description:19', 'path/to/image/19', 2),
       (20, 'test-category-20', 'test-description:20', 'path/to/image/20', 2);

INSERT INTO products(id, name, description, price, image_path, category_id, organization_id)
VALUES (21, 'laptop', 'description:21', 210, 'path/21', 18, 1),
       (22, 'laptop', 'description:22', 220, 'path/22', 19, 2),
       (23, 'pencil', 'description:23', 230, 'path/23', 20, 2);

INSERT INTO orders(id, organization_id)
VALUES (24, 1),
       (25, 1);

INSERT INTO orders_products(order_id, product_id)
VALUES (24, 21),
       (25, 22),
       (25, 23);

INSERT INTO clients_orders(client_id, order_id)
VALUES (15, 24),
       (16, 25);

INSERT INTO events(id, message, type,  publisher_id, organization_id)
VALUES (26, 'product:21', 'ADD', 13, 1),
       (27, 'employee:13', 'UPDATE', 13, 1),
       (28, 'product:22', 'DELETE', 14, 2);



