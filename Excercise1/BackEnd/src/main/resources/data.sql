CREATE TABLE user_credential (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL
);


INSERT INTO user_credential (username, password) VALUES ('username',  'password');
INSERT INTO user_credential (username, password) VALUES ('pedro',  '123');
INSERT INTO user_credential (username, password) VALUES ('pablo',  '456');
INSERT INTO user_credential (username, password) VALUES ('test',  'test123');