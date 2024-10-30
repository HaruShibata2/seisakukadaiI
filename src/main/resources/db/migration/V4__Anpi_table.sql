CREATE TABLE anpi_confirmation (
    user_id INTEGER PRIMARY KEY AUTO_INCREMENT,  -- ユーザーID、一意で自動増加
	  earthquake_id INTEGER NOT NULL,    -- 安否確認（地震）ID（外部キーとして地震テーブルを参照する場合に利用）
    status ENUM('safe', 'in_danger', 'unknown') NOT NULL,  -- 安否状況
    confirmation_time DATETIME NOT NULL      -- 安否確認の日時
);
a