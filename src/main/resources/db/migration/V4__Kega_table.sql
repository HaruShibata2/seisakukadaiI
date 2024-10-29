CREATE TABLE kega (
    user_id INTEGER NOT NULL,                     -- ユーザーID
    date_of_injury DATE ,             -- 怪我した日付
    severity_level VARCHAR(50) NOT NULL,      -- 怪我の程度 (例: 軽傷, 重傷)
    injury_type VARCHAR(100) ,        -- 怪我の種類 (例: 切り傷, 骨折)
    location VARCHAR(100) ,           -- 怪我した場 (例: 手, 足)
    treated BOOLEAN DEFAULT FALSE,            -- 治療済みかどうか (TRUE: 治療済み, FALSE: 未治療)
    PRIMARY KEY (user_id, date_of_injury)     -- 複合キー: ユーザーIDと怪我の日付の組み合わせで一意
);