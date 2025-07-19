
CREATE TABLE cart (
                       card_id       UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                       user_id       UUID NOT NULL,
                       product_code  TEXT NOT NULL,
                       created_date  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                       constraint fk_user FOREIGN KEY (user_id) REFERENCES users(user_id),
                       constraint fk_product FOREIGN KEY (product_code) REFERENCES products(product_code)
);



ALTER TABLE products
    ADD shop_id UUID NOT NULL;

ALTER TABLE products
    ADD CONSTRAINT fk_shop FOREIGN KEY (shop_id) REFERENCES outlets(shop_id);