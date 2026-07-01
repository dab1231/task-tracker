--liquibase formatted sql

--changeset dab1:001-create-users-table
CREATE TABLE users(
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
)