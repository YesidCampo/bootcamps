CREATE TABLE IF NOT EXISTS bootcamps ( 
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(90) NOT NULL
);

CREATE TABLE  IF NOT EXISTS bootcamp_capability (
    id SERIAL PRIMARY KEY,
    bootcamp_Id BIGINT NOT NULL,
    capability_id BIGINT NOT NULL
);