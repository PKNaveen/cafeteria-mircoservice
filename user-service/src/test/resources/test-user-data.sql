-- Write User sample data here

TRUNCATE TABLE
    users
    RESTART IDENTITY CASCADE;

INSERT INTO users (user_id, user_name, user_email, image_url, created_date)
VALUES ('11111111-1111-1111-1111-111111111111', 'Alice Johnson', 'alice.johnson@example.com', 'https://example.com/images/alice.jpg', NOW());
