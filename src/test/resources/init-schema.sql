DROP TABLE IF EXISTS `user`;
CREATE TABLE `dicomcloud`.`user` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(64) NOT NULL DEFAULT '',
  `password` VARCHAR(128) NOT NULL DEFAULT '',
  `name` VARCHAR(45) NOT NULL DEFAULT '' COMMENT '用户名的名称',
  `salt` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '密码的盐',
  `head_url` VARCHAR(256) NOT NULL DEFAULT '' COMMENT '用户头像的url地址',
  `status` INT NOT NULL COMMENT '身份：医生0，病人1，管理员2',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = '用户表';

DROP TABLE IF EXISTS `dicomfile`;
CREATE TABLE `dicomcloud`.`dicomfile` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `dicom_name` VARCHAR(128) NOT NULL,
  `mapping_name` VARCHAR(128) NOT NULL,
  `modality` VARCHAR(10) NOT NULL,
  `file_size` VARCHAR(32) NOT NULL COMMENT '文件的大小',
  `study_date` DATETIME NOT NULL,
  `sop_uid` VARCHAR(128) NOT NULL,
  `series_uid` VARCHAR(128) NOT NULL,
  `study_uid` VARCHAR(128) NOT NULL,
  `patient_uid` VARCHAR(128) NOT NULL,
  `created_date` DATETIME NOT NULL,
  `ishdfs` INT NOT NULL COMMENT '记录文件是否存在HDFS中',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

DROP TABLE IF EXISTS `studyfile`;
CREATE TABLE `dicomcloud`.`studyfile` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `folder_name` VARCHAR(64) NOT NULL,
  `modality` VARCHAR(11) NOT NULL,
  `study_uid` VARCHAR(256) NOT NULL,
  `study_file_size` VARCHAR(32) NOT NULL,
  `study_date` DATETIME NOT NULL,
  `create_date` DATETIME NOT NULL,
  `ishdfs` INT NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `user_index` (`user_id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;
