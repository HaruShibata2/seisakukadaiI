CREATE TABLE safety_responses (
    user_id INTEGER NOT NULL,          -- ユーザーID（外部キーとしてユーザーテーブルを参照する場合に利用）
    earthquake_id INTEGER NOT NULL,    -- 安否確認（地震）ID（外部キーとして地震テーブルを参照する場合に利用）
    answer VARCHAR(50) NOT NULL,   -- 安否回答（例：無事、けがあり、安否不明など）
    PRIMARY KEY (user_id, earthquake_id)  -- 複合主キー
);
 
 a