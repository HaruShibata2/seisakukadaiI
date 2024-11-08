CREATE TABLE `SafetyChecks` (
    `safe_id` INT AUTO_INCREMENT NOT NULL COMMENT 'セーフID',  -- コメントをカラム定義の最後に配置
    `user_id` VARCHAR(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ユーザーID',  -- user_idに対してCHARACTER SETとCOLLATEを指定
    `status` ENUM('safe', 'injured') NOT NULL COMMENT '安否状態',
    `checked_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '安否確認日時',
    PRIMARY KEY (`safe_id`),
    FOREIGN KEY (`user_id`) REFERENCES `Users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;