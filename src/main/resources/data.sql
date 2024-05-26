CREATE TABLE users (
    id INT AUTO_INCREMENT,
    name VARCHAR(20),
    surname VARCHAR(20),
    PRIMARY KEY (id)
);

CREATE TABLE feedbacks (
    id INT AUTO_INCREMENT,
    title VARCHAR(5000),
    description VARCHAR(5000),
    user_id BIGINT,
    rating INT,
    FOREIGN KEY (user_id) REFERENCES users(id),
    PRIMARY KEY (id)
);

CREATE TABLE comments (
    id INT AUTO_INCREMENT,
    text VARCHAR(5000),
    feedback_id BIGINT,
    user_id BIGINT,
    FOREIGN KEY (feedback_id) REFERENCES feedbacks(id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    PRIMARY KEY (id)
);

-- Users
INSERT INTO users (name, surname) VALUES ('Jovan', 'Tone');
INSERT INTO users (name, surname) VALUES ('Vangel', 'Tone');
INSERT INTO users (name, surname) VALUES ('Prohor', 'Muchev');
INSERT INTO users (name, surname) VALUES ('Tristan', 'Beason');

-- Feedbacks (with comments)
INSERT INTO feedbacks (title, description, user_id, rating) VALUES ('mario', 'he jumps on goombas', 2, 10);
INSERT INTO feedbacks (title, description, user_id, rating) VALUES ('thor', 'his hammer is heavy', 1, 6);
INSERT INTO feedbacks (title, description, user_id, rating) VALUES ('spiderman', 'webslinger', 3, 8);
-- Feedbacks (without comments)
INSERT INTO feedbacks (title, description, user_id, rating) VALUES ('batman', 'vengeance', 2, 10);
INSERT INTO feedbacks (title, description, user_id, rating) VALUES ('oppenheimer', 'explosion', 3, 7);

-- Comments
INSERT INTO comments (text, feedback_id, user_id) VALUES ('great movie', 1, 1);
INSERT INTO comments (text, feedback_id, user_id) VALUES ('bad cgi', 2, 1);
INSERT INTO comments (text, feedback_id, user_id) VALUES ('very nostalgic', 3, 3);
