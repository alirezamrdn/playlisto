DROP TABLE IF EXISTS tbl_play_list_item;
DROP TABLE IF EXISTS tbl_play_list;

CREATE TABLE IF NOT EXISTS tbl_play_list (
  id INT AUTO_INCREMENT PRIMARY KEY,
  user_id INT NOT NULL,
  name VARCHAR(256) NOT NULL,
  description VARCHAR(1024) NULL
);

CREATE TABLE IF NOT EXISTS tbl_play_list_item (
  id INT AUTO_INCREMENT PRIMARY KEY,
  play_list_id INT NOT NULL,
  track_id VARCHAR(256) NOT NULL
);

--ALTER TABLE tbl_play_list_item ADD CONSTRAINT play_list_fk FOREIGN KEY (play_list_id) REFERENCES tbl_play_list(id);
