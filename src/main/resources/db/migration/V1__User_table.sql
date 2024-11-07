-- Users テーブルの作成
CREATE TABLE `Users` (
    `id` SERIAL NOT NULL,
    `username` VARCHAR(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名前',
    `userId` VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ユーザーID',
    `email` VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL UNIQUE COMMENT 'メールアドレス（ユニークにする）',
    `password` VARCHAR(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'パスワード',
    `role` ENUM('admin', 'leader', 'general') DEFAULT 'general' COMMENT 'ユーザーの役割（管理者か一般か）',
    `entry_date` DATE COMMENT '入社日、入学日などの組織に入った日',
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'レコード作成日時',
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'レコード更新日時',
    `department` VARCHAR(64) COMMENT '部署コード',
    PRIMARY KEY (`id`),
    UNIQUE (`userId`)  -- userId はユニーク制約を追加
) ENGINE=InnoDB;