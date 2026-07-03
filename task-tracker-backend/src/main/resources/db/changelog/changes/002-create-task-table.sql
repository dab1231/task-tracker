--liquibase formatted sql

--changeset dab1:002-create-task-table
CREATE TABLE tasks
(
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users(id) NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    status VARCHAR(20) NOT NULL DEFAULT 'NOT_DONE',
    completed_at TIMESTAMP
)