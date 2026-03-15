CREATE TABLE candidates (
    id VARCHAR(36) PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE curriculums (
    candidate_id VARCHAR(36) PRIMARY KEY,
    file_name VARCHAR(255) NOT NULL,
    content_type VARCHAR(100) NOT NULL,
    size_bytes BIGINT NOT NULL,
    file_key VARCHAR(255) NOT NULL,
    FOREIGN KEY (candidate_id) REFERENCES candidates(id) ON DELETE CASCADE
);

CREATE TABLE social_links (
    candidate_id VARCHAR(36) NOT NULL,
    url VARCHAR(255) NOT NULL,
    type VARCHAR(50) NOT NULL,
    PRIMARY KEY (candidate_id, type),
    FOREIGN KEY (candidate_id) REFERENCES candidates(id) ON DELETE CASCADE
);
