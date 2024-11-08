CREATE TABLE `inquiry` (
    `inquiry_id` INT AUTO_INCREMENT PRIMARY KEY,
    `user_id` VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ユーザーID',
    `name` VARCHAR(256) NOT NULL,
    `inquiry_content` VARCHAR(256) NOT NULL,
    `inquiry_description` VARCHAR(256),
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (`user_id`) REFERENCES `Users`(`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;