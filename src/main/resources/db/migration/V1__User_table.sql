CREATE TABLE Users (
    user_id INTEGER PRIMARY KEY,                        -- ユーザー番号（社員番号、学生番号など）
    name VARCHAR(50) NOT NULL,                      -- 名前
    email VARCHAR(100) UNIQUE NOT NULL,             -- メールアドレス（ユニークにする）
    password VARCHAR(1026) NOT NULL,                -- パスワード（セキュアに保存、ハッシュ化）
    role ENUM('admin', 'leader', 'general') DEFAULT 'general',  -- ユーザーの役割（管理者か一般か）
    entry_date DATE,                                -- 入社日、入学日などの組織に入った日
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- レコード作成日時
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,  -- レコード更新日時
    department VARCHAR(64)                          -- 部署コード
);
