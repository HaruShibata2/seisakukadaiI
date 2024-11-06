CREATE TABLE inquiry (
    inquiry_id INTEGER AUTO_INCREMENT PRIMARY KEY,
    user_id INTEGER NOT NULL,
    name VARCHAR(255) NOT NULL,
    inquiry_content TEXT NOT NULL,
    inquiry_description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
