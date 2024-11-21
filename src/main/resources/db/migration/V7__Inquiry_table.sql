CREATE TABLE `inquiry` (
    `inquiry_id` INT AUTO_INCREMENT COMMENT '問い合わせID',
    `user_id` VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ユーザーID',
    `username` VARCHAR(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ユーザー名',
    `inquiry_content` VARCHAR(256) NOT NULL COMMENT '問い合わせ内容',
    `inquiry_description` VARCHAR(256) COMMENT '問い合わせ説明',
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '作成日時',
    PRIMARY KEY (`inquiry_id`),
    CONSTRAINT `fk_inquiry_user_id` FOREIGN KEY (`user_id`) REFERENCES `Users`(`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_inquiry_username` FOREIGN KEY (`username`) REFERENCES `Users`(`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
