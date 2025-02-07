CREATE TABLE `Stockpile` (
    `id` SERIAL NOT NULL,
    `item_name` VARCHAR(255) NOT NULL COMMENT 'アイテム名',
    `quantity` INT NOT NULL COMMENT '数量',
    `location` VARCHAR(255) NOT NULL COMMENT '場所',
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '作成日時',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='備蓄情報テーブル';
