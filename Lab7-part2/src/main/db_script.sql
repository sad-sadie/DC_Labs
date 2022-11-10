DROP TABLE groups CASCADE;
DROP TABLE students CASCADE;


CREATE TABLE groups (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(64),
                        course integer
);
CREATE TABLE students (
                          id SERIAL PRIMARY KEY,
                          first_name VARCHAR(64),
                          last_name VARCHAR(64),
                          group_id integer references groups on delete cascade
);



/*INSERT INTO users VALUES (DEFAULT, 'ivanov');
INSERT INTO users VALUES (DEFAULT, 'petrov');
INSERT INTO teams VALUES (DEFAULT, 'teamA');
INSERT INTO teams VALUES (DEFAULT, 'teamB');
INSERT INTO teams VALUES (DEFAULT, 'teamC');
INSERT INTO users_teams VALUES (1, 1);
INSERT INTO users_teams VALUES (2, 1);


SELECT * FROM users;
SELECT * FROM teams;
SELECT * FROM users_teams;*/