CREATE DATABASE `task1` /*!40100 DEFAULT CHARACTER SET utf8 */;

CREATE TABLE `t_file` (
  `id` int(11) NOT NULL COMMENT 'Идентификатор',
  `name` varchar(255) NOT NULL COMMENT 'Название файла',
  `link_folder` int(11) NOT NULL COMMENT 'Ссылка на родительскую папку',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `fk_file2folder_idx` (`link_folder`),
  CONSTRAINT `fk_file2folder` FOREIGN KEY (`link_folder`) REFERENCES `t_folder` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Файлы в файловой системе. Имя содержит НЕ полный путь к файлу';

CREATE TABLE `t_folder` (
  `id` int(11) NOT NULL COMMENT 'Идентификатор',
  `name` varchar(255) NOT NULL COMMENT 'Название папки',
  `link_folder` int(11) DEFAULT NULL COMMENT 'Ссылка на родительскую папку',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `fk_folder2folder_idx` (`link_folder`),
  CONSTRAINT `fk_folder2folder` FOREIGN KEY (`link_folder`) REFERENCES `t_folder` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Папка в файловой системе. Имя содержит НЕ полный путь к файлу';