CREATE TABLE `jar_files`(
	`jid` INT(11) NOT NULL AUTO_INCREMENT,
	`jar_data` BLOB NOT NULL,
	`jar_name` VARCHAR(50),
	PRIMARY KEY(`jid`)
);

CREATE TABLE `jar_file_entries`(
	`entry_name` VARCHAR(100) NOT NULL,
	`jid` INT(11) NOT NULL,
	PRIMARY KEY (`entry_name`),
	CONSTRAINT `jar_file_entries_jid` FOREIGN KEY (`jid`) REFERENCES `jar_files` (`jid`)
);
