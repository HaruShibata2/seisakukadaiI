-- Posts テーブルの作成
CREATE TABLE `Posts` (
    `post_id` INT AUTO_INCREMENT NOT NULL COMMENT '投稿ID',  -- コメントをカラム定義の最後に配置
    `user_id` VARCHAR(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ユーザーID',  -- CHARACTER SET と COLLATE を適切な位置に配置
    `title` VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'タイトル',
    `description` TEXT NOT NULL COMMENT '説明',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '作成日時',
    PRIMARY KEY (`post_id`),
    FOREIGN KEY (`user_id`) REFERENCES `Users`(`user_id`)  -- user_id を Users テーブルの userId に関連付け
) ENGINE=InnoDB;