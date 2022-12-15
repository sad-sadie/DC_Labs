/*DROP TABLE groups CASCADE;
DROP TABLE students CASCADE;

CREATE TABLE groups (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(64),
                        course INTEGER
);

CREATE TABLE students (
                          id SERIAL PRIMARY KEY,
                          first_name VARCHAR(64),
                          last_name VARCHAR(64),
                          group_id INTEGER REFERENCES groups ON DELETE CASCADE
);

*/

DELETE
FROM groups
WHERE id =7;