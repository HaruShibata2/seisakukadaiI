CREATE TABLE Language (
    language_id INTEGER PRIMARY KEY AUTO_INCREMENT,  -- 各言語を一意に識別するID
    language_code VARCHAR(2) NOT NULL,           -- ISO 639-1 言語コード (例: 'en', 'ja')
    language_name VARCHAR(255) NOT NULL,         -- 言語の表示名 (例: 'English', '日本語')
    locale_code VARCHAR(5) NOT NULL,             -- ロケールコード (例: 'en_US', 'ja_JP')
    text_direction ENUM('LTR', 'RTL') DEFAULT 'LTR', -- テキストの方向（左から右 'LTR' か右から左 'RTL'）
    flag_url VARCHAR(255),                       -- 言語や国に対応するフラグアイコンのURL
    is_default BOOLEAN DEFAULT FALSE,            -- デフォルト言語かどうかのフラグ
    is_active BOOLEAN DEFAULT TRUE               -- 言語がアクティブかどうか（非アクティブにすることで使用を制限）
);
a