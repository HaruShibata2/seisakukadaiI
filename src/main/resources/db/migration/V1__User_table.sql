CREATE TABLE `Users` (
	`id` SERIAL NOT NULL,
	`username` VARCHAR(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '教員ID',
	`email` VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  UNIQUE NOT NULL,             -- メールアドレス（ユニークにする）
	`password` VARCHAR(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'パスワード',
	`role` ENUM('admin', 'leader', 'general') DEFAULT 'general',  -- ユーザーの役割（管理者か一般か）
    `entry_date` DATE,                                -- 入社日、入学日などの組織に入った日
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- レコード作成日時
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,  -- レコード更新日時
    `department` VARCHAR(64)                          -- 部署コードPRIMARY KEY (`teacher_id`)
) ENGINE=InnoDB;
