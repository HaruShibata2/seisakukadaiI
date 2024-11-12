-- Posts テーブルの作成
CREATE TABLE `Posts` (
    `post_id` INT AUTO_INCREMENT NOT NULL COMMENT '投稿ID',  -- 投稿ID、AUTO_INCREMENTで一意に生成される
    `user_id` VARCHAR(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ユーザーID',  -- UserModelのuser_id
    `username` VARCHAR(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ユーザー名',
    `title` VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'タイトル',
    `description` TEXT NOT NULL COMMENT '説明',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '作成日時',  -- 修正: created_at カラム
    PRIMARY KEY (`post_id`),
    -- 外部キー制約を設定、ユーザー名とユーザーIDに基づいてUsersテーブルと関連
    FOREIGN KEY (`username`) REFERENCES `Users`(`username`) ON DELETE CASCADE,  
    FOREIGN KEY (`user_id`) REFERENCES `Users`(`user_id`) ON DELETE CASCADE 
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
