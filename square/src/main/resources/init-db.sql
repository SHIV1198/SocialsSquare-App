SET timezone = 'UTC';

CREATE TABLE test_posts (
    id SERIAL PRIMARY KEY,
    user_id VARCHAR(255),
    content TEXT
);
