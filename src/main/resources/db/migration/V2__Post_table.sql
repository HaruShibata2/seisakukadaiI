CREATE TABLE posts (
    post_id INTEGER PRIMARY KEY AUTO_INCREMENT, -- 投稿ID
    user_id INTEGER,                            -- ユーザー番号（ユーザーテーブルから持ってくる）
    title VARCHAR(255) NOT NULL,                -- タイトル
    description TEXT NOT NULL,                  -- 説明
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP, -- 投稿日時
    FOREIGN KEY (user_id) REFERENCES users(user_id) -- 外部キー
);
a