CREATE TABLE `Safetychecks` (
    `safe_id` INT AUTO_INCREMENT NOT NULL COMMENT 'セーフID',
    `username` VARCHAR(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ユーザー名（外部キー）',
    `status` ENUM('安全', '怪我') NOT NULL COMMENT '安否状態',
    `checked_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '安否確認日時',
    PRIMARY KEY (`safe_id`),
    FOREIGN KEY (`username`) REFERENCES `Users`(`username`) ON DELETE CASCADE
) ENGINE=InnoDB;