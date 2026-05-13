CREATE TABLE candidate(candidate_id serial PRIMARY KEY, first_name varchar(50) NOT NULL, last_name varchar(50) NOT NULL, grade varchar(50), experience double precision, current_salary numeric(10, 2), create_date timestamp without time zone DEFAULT now());

CREATE TABLE event(event_id uuid PRIMARY KEY, candidate_id integer, message text NOT NULL, is_sent boolean DEFAULT false, create_date timestamp without time zone DEFAULT now());

ALTER TABLE event ADD CONSTRAINT fk_candidate FOREIGN KEY(candidate_id) REFERENCES candidate(candidate_id);
