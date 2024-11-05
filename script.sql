CREATE TABLE `USERS` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `username` VARCHAR(255) UNIQUE,
  `email` VARCHAR(255) UNIQUE,
  `password` VARCHAR(255),
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE `TOPICS` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(255) UNIQUE,
  `description` TEXT,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE `POSTS` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `title` VARCHAR(255),
  `content` TEXT,
  `author` INT,
  `topic` INT,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE `COMMENTS` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `content` TEXT,
  `post` INT,
  `author` INT,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE `SUBSCRIPTIONS` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `user` INT,
  `topic` INT,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

ALTER TABLE `POSTS` ADD FOREIGN KEY (`author`) REFERENCES `USERS` (`id`);
ALTER TABLE `POSTS` ADD FOREIGN KEY (`topic`) REFERENCES `TOPICS` (`id`);
ALTER TABLE `COMMENTS` ADD FOREIGN KEY (`post`) REFERENCES `POSTS` (`id`);
ALTER TABLE `COMMENTS` ADD FOREIGN KEY (`author`) REFERENCES `USERS` (`id`);
ALTER TABLE `SUBSCRIPTIONS` ADD FOREIGN KEY (`user`) REFERENCES `USERS` (`id`);
ALTER TABLE `SUBSCRIPTIONS` ADD FOREIGN KEY (`topic`) REFERENCES `TOPICS` (`id`);

-- password: Test123!
INSERT INTO `USERS` (`username`, `email`, `password`) VALUES
('john', 'john@john.com', '$2y$10$SdZqlBiNA9l3ETtT4BIKcOTWUzzKN6t.joyt3kRdmWC4PQmM0kNX2'),
('jane', 'jane@jane.com', '$2y$10$SdZqlBiNA9l3ETtT4BIKcOTWUzzKN6t.joyt3kRdmWC4PQmM0kNX2');

INSERT INTO `TOPICS` (`name`, `description`) VALUES
('Python', 'Discussions about Python'),
('Java', 'Discussions about Java'),
('DevOps', 'Discussions about DevOps');

INSERT INTO `POSTS` (`title`, `content`, `author`, `topic`) VALUES
('Article 1', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', 0, 0),
('Article 2', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', 1, 0),
('Article 3', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', 0, 1),
('Article 4', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', 1, 1),
('Article 5', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', 0, 2),
('Article 6', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.', 1, 2);

INSERT INTO `COMMENTS` (`content`, `post`, `author`) VALUES
('Great tips! Really helpful for beginners.', 1, 2),
('Paris is amazing! Did you visit the Louvre?', 2, 1),
('Thanks for sharing these recipes!', 3, 2);

INSERT INTO `SUBSCRIPTIONS` (`user`, `topic`) VALUES
(0, 0),
(0, 1),
(1, 1),
(1, 2);
