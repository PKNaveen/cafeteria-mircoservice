CREATE TABLE inventory (
                        inventory_id             UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                        product_id               UUID NOT NULL,
                        stock_quantity           INTEGER NOT NULL,
                        last_update_date         TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        CONSTRAINT fk_item       FOREIGN KEY (product_id) references products(id)

);
