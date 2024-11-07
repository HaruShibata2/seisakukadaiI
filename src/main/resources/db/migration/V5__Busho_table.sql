CREATE TABLE `Bushos` (
    `busho_id` INT AUTO_INCREMENT NOT NULL COMMENT '部署ID',  -- AUTO_INCREMENTはbusho_idのみ
    `comp_id` INT NOT NULL COMMENT '企業ID',                   -- AUTO_INCREMENT は不要
    `user_id` VARCHAR(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'ユーザーID',  -- user_idに対してCHARACTER SETとCOLLATEを指定
    PRIMARY KEY (`busho_id`),
    FOREIGN KEY (`comp_id`) REFERENCES `Company`(`comp_id`),  -- 企業テーブルへの外部キー、`comp_id` を参照
    FOREIGN KEY (`user_id`) REFERENCES `Users`(`userId`)  -- user_id を Users テーブルの userId に関連付け
) ENGINE=InnoDB;
