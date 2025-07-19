-- FILE TO CREATE TABLES AND RELATIONS V1.1
-- If there are any further changes V1.2 and so on will be used to alter these

CREATE TABLE users (
                       user_id       UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                       user_name     TEXT NOT NULL,
                       user_email    TEXT NOT NULL UNIQUE,
                       image_url     TEXT NOT NULL,
                       created_date  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE outlets (
                         shop_id        UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                         shop_name      TEXT NOT NULL,
                         shop_location  TEXT NOT NULL UNIQUE,
                         shop_rating    TEXT NOT NULL,
                         created_date   TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE master_roles (
                              role_id    UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                              role_name  TEXT NOT NULL UNIQUE
);

CREATE TABLE user_roles (
                            user_id  UUID PRIMARY KEY,
                            role_id  UUID NOT NULL,

                            CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(user_id),
                            CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES master_roles(role_id)
);
