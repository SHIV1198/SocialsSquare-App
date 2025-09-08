CREATE TABLE test_posts (
    id INTEGER PRIMARY KEY,
    user_id VARCHAR(255),
    content TEXT
);

-- Create a sequence for auto-incrementing the id field
CREATE SEQUENCE test_posts_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- Set the id column to use the sequence by default
--ALTER TABLE test_posts ALTER COLUMN id SET DEFAULT nextval('test_posts_id_seq');