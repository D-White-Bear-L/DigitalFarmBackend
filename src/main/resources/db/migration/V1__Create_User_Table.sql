CREATE TABLE IF NOT EXISTS `User` (
  `user_id` INT AUTO_INCREMENT PRIMARY KEY,
  `username` VARCHAR(50) NOT NULL UNIQUE,
  `password` VARCHAR(100) NOT NULL,
  `real_name` VARCHAR(50),
  `role` VARCHAR(20) NOT NULL DEFAULT 'user',
  `avatar_url` VARCHAR(255),
  `gender` VARCHAR(10),
  `birthday` DATE,
  `department` VARCHAR(50),
  `position` VARCHAR(50),
  `phone` VARCHAR(20),
  `email` VARCHAR(100) UNIQUE,
  `address` VARCHAR(255),
  `bio` TEXT,
  `last_login` DATETIME,
  `login_ip` VARCHAR(50),
  `status` VARCHAR(20) NOT NULL DEFAULT 'active',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 创建索引
CREATE INDEX idx_user_username ON `User` (`username`);
CREATE INDEX idx_user_email ON `User` (`email`);
CREATE INDEX idx_user_phone ON `User` (`phone`);
