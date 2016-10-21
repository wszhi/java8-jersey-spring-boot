CREATE TABLE `A1N` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `B1N` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `a1nid` bigint(20) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX (`a1nid`),
  FOREIGN KEY (`a1nid`) REFERENCES A1N(`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- insert into gaia.A1N (`name`) values ('test');
-- insert into gaia.B1N (`a1nid`,`name`) values (1,'Btest');