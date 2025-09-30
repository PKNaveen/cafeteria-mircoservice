-- Write User sample data here


CREATE TABLE users (
                       user_id       UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                       user_name     TEXT NOT NULL,
                       user_email    TEXT NOT NULL UNIQUE,
                       image_url     TEXT NOT NULL,
                       created_date  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO users (user_id, user_name, user_email, image_url, created_date)
VALUES ('11111111-1111-1111-1111-111111111111', 'Alice Johnson', 'alice.johnson@example.com', 'https://example.com/images/alice.jpg', NOW());
