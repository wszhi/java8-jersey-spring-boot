CREATE TABLE `ANN` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `BNN` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `ANN_BNN`(
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bnn_id` bigint(20) NOT NULL,
  `ann_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX (`bnn_id`),
  FOREIGN KEY (`bnn_id`) REFERENCES BNN(`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  INDEX (`ann_id`),
  FOREIGN KEY (`ann_id`) REFERENCES ANN(`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- insert into gaia.ANN (`id`,`name`) values (1,'Atest');
-- insert into gaia.BNN (`id`,`name`) values (1,'Btest');
-- insert into gaia.ANN_BNN (`bnn_id`,`ann_id`) values (1,1);
-- insert into gaia.ANN (`name`) values ('Atest2');
-- insert into gaia.BNN (`name`) values ('Btest2');
-- insert into gaia.ANN_BNN (`bnn_id`,`ann_id`) values (1,2);
-- insert into gaia.ANN_BNN (`bnn_id`,`ann_id`) values (2,2);