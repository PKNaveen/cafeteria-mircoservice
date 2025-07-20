-- Truncate tables in dependency order
TRUNCATE TABLE
    cart,
    user_roles,
    products,
    users,
    master_roles,
    outlets,
    flyway_schema_history
    RESTART IDENTITY CASCADE;

-- Insert sample users
INSERT INTO users (user_name, user_email, image_url)
VALUES
    ('Alice Smith', 'alice@example.com', 'https://images.com/alice.jpg'),
    ('Bob Johnson', 'bob@example.com', 'https://images.com/bob.jpg'),
    ('Carol White', 'carol@example.com', 'https://images.com/carol.jpg'),
    ('David Lee', 'david@example.com', 'https://images.com/david.jpg'),
    ('Eva Adams', 'eva@example.com', 'https://images.com/eva.jpg');

-- Insert sample outlets
INSERT INTO outlets (shop_name, shop_location, shop_rating)
VALUES
    ('TechHub', 'Bangalore', '4.7'),
    ('StyleStreet', 'Mumbai', '4.5'),
    ('GadgetWorld', 'Delhi', '4.6'),
    ('FoodFiesta', 'Chennai', '4.4'),
    ('BookBarn', 'Kolkata', '4.8');

-- Insert sample master roles
INSERT INTO master_roles (role_name)
VALUES
    ('Admin'),
    ('Customer'),
    ('Vendor'),
    ('Support'),
    ('Manager');

-- Map user roles (assuming 1-1 mapping just for sample)
INSERT INTO user_roles (user_id, role_id)
SELECT u.user_id, r.role_id
FROM (
         SELECT user_id, ROW_NUMBER() OVER () AS rn FROM users LIMIT 5
     ) u
         JOIN (
    SELECT role_id, ROW_NUMBER() OVER () AS rn FROM master_roles LIMIT 5
) r ON u.rn = r.rn;

-- Insert sample flyway schema history
INSERT INTO flyway_schema_history (installed_rank, version, description, type, script, checksum, installed_by, execution_time, success)
VALUES
    (1, '1', 'init schema', 'SQL', 'V1__init_schema.sql', 123456, 'admin', 120, true),
    (2, '2', 'add users', 'SQL', 'V2__add_users.sql', 234567, 'admin', 90, true),
    (3, '3', 'add products', 'SQL', 'V3__add_products.sql', 345678, 'admin', 100, true),
    (4, '4', 'add cart', 'SQL', 'V4__add_cart.sql', 456789, 'admin', 80, true),
    (5, '5', 'add roles', 'SQL', 'V5__add_roles.sql', 567890, 'admin', 95, true);

-- Insert sample products (15 items) mapped to existing shop_ids
INSERT INTO products (product_code, product_name, product_category, product_price, product_rating, product_description, product_imageurl, shop_id)
SELECT
    p.product_code,
    p.product_name,
    p.product_category,
    p.product_price,
    p.product_rating,
    p.product_description,
    p.product_imageurl,
    o.shop_id
FROM (
         VALUES
             ('P1001', 'Laptop Pro', 'Electronics', '79999', '4.5', 'High-performance laptop', 'https://img.com/laptop1.jpg'),
             ('P1002', 'Smartphone X', 'Electronics', '49999', '4.6', 'Latest smartphone model', 'https://img.com/phone1.jpg'),
             ('P1003', 'Bluetooth Speaker', 'Accessories', '2999', '4.2', 'Portable Bluetooth speaker', 'https://img.com/speaker.jpg'),
             ('P1004', 'Wireless Mouse', 'Accessories', '999', '4.1', 'Ergonomic wireless mouse', 'https://img.com/mouse.jpg'),
             ('P1005', 'Gaming Keyboard', 'Accessories', '2499', '4.3', 'Mechanical gaming keyboard', 'https://img.com/keyboard.jpg'),
             ('P1006', 'Running Shoes', 'Footwear', '3499', '4.4', 'Comfortable running shoes', 'https://img.com/shoes.jpg'),
             ('P1007', 'Leather Wallet', 'Fashion', '799', '4.0', 'Genuine leather wallet', 'https://img.com/wallet.jpg'),
             ('P1008', 'Backpack Pro', 'Travel', '1999', '4.2', 'Spacious travel backpack', 'https://img.com/backpack.jpg'),
             ('P1009', 'Smartwatch Lite', 'Electronics', '2999', '4.1', 'Affordable smartwatch', 'https://img.com/smartwatch.jpg'),
             ('P1010', 'Fitness Band', 'Electronics', '1999', '4.0', 'Fitness tracker band', 'https://img.com/fitnessband.jpg'),
             ('P1011', 'Noise Cancelling Headphones', 'Accessories', '5999', '4.5', 'High-quality headphones', 'https://img.com/headphones.jpg'),
             ('P1012', 'Tablet Max', 'Electronics', '25999', '4.3', 'Latest gen tablet', 'https://img.com/tablet.jpg'),
             ('P1013', 'Digital Camera', 'Electronics', '45999', '4.6', 'High-res digital camera', 'https://img.com/camera.jpg'),
             ('P1014', 'LED Desk Lamp', 'Home Decor', '899', '4.4', 'Adjustable LED lamp', 'https://img.com/desk_lamp.jpg'),
             ('P1015', 'Electric Kettle', 'Kitchen', '1299', '4.2', 'Fast boiling kettle', 'https://img.com/kettle.jpg')
     ) AS p(product_code, product_name, product_category, product_price, product_rating, product_description, product_imageurl),
     LATERAL (
         SELECT shop_id FROM outlets ORDER BY RANDOM() LIMIT 1
         ) AS o;

-- Insert sample cart entries
INSERT INTO cart (user_id, product_code)
SELECT
    u.user_id,
    p.product_code
FROM
    (SELECT user_id FROM users LIMIT 5) u,
    (SELECT product_code FROM products ORDER BY RANDOM() LIMIT 5) p
LIMIT 5;
