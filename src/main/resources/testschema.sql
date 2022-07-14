DROP TABLE IF EXISTS `waiter`;

CREATE TABLE `waiter` (
	`id` BIGINT AUTO_INCREMENT,
	`firstname` VARCHAR(255) NOT NULL,
	`last_name` VARCHAR(255) NOT NULL,
	`email` VARCHAR(255) UNIQUE NOT NULL,
	PRIMARY KEY(`id`)
);