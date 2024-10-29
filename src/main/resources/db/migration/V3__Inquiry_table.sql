CREATE TABLE inquiries (
    inquiry_id INT AUTO_INCREMENT PRIMARY KEY,  -- 自動インクリメントされる問い合わせID（プライマリキー）
    user_id INT NOT NULL,                -- ユーザーID（外部キーとして使用することを想定）
    name VARCHAR(255) NOT NULL,          -- ユーザー名や問い合わせ名
    inquiry_content TEXT NOT NULL,       -- 問い合わせの内容
    inquiry_description TEXT,            -- 問い合わせに関する説明や補足情報
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- 問い合わせが作成された日時
);
