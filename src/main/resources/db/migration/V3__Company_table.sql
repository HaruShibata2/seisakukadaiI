CREATE TABLE `Company` (
    `id` SERIAL,                                -- 自動インクリメントのID
    `name` VARCHAR(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL, -- 企業名
    `address` VARCHAR(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,     -- 住所
    PRIMARY KEY (`id`)                          -- 主キー設定
) ENGINE=InnoDB;
