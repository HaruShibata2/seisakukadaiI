CREATE TABLE `Company` (
    `comp_id` INT AUTO_INCREMENT NOT NULL COMMENT '会社ID',  -- コメントをカラム定義の最後に配置
    `name` VARCHAR(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL, -- 企業名
    `address` VARCHAR(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,     -- 住所
    PRIMARY KEY (`comp_id`)                          -- 主キー設定
) ENGINE=InnoDB;
