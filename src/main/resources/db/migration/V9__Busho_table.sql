CREATE TABLE busho (
    busho_id INTEGER PRIMARY KEY,    -- 部署の識別子
    comp_id INTEGER NOT NULL,      -- 企業の識別子
    user_id INTEGER NOT NULL,        -- ユーザーの識別子
    FOREIGN KEY (comp_id) REFERENCES company(comp_id),  -- 企業テーブルへの外部キー
    FOREIGN KEY (user_id) REFERENCES Users(user_id)     -- ユーザーテーブルへの外部キー
);
 
 