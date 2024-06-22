CREATE TABLE projectk_user (
    id              SERIAL PRIMARY KEY,
    version         INT       DEFAULT 1     NOT NULL,
    created_on      TIMESTAMP DEFAULT now() NOT NULL,
    modified_on     TIMESTAMP DEFAULT now() NOT NULL,
    created_by      VARCHAR(255)            NOT NULL,
    modified_by     VARCHAR(255)            NOT NULL,
    username        VARCHAR(255)            NOT NULL,
    first_name      VARCHAR(255) NULL,
    last_name       VARCHAR(255) NULL,
    status          VARCHAR(1) NOT NULL
);

CREATE UNIQUE INDEX idx_projectk_user_username ON projectk_user (username);

INSERT INTO projectk_user(id, created_by, modified_by, username, first_name, last_name, status)
VALUES
    (1, 'bootstrap', 'bootstrap', 'admin.test@softwarestrategies.io', 'Admin', 'Smith', 'A'),
    (2, 'bootstrap', 'bootstrap', 'user1.test@softwarestrategies.io', 'User1', 'Jones', 'A'),
    (3, 'bootstrap', 'bootstrap', 'user2.test@softwarestrategies.io', 'User2', 'Jones', 'A');

CREATE TABLE project (
    id              SERIAL PRIMARY KEY,
    version         INT DEFAULT 1 NOT NULL,
    created_on      TIMESTAMP DEFAULT now() NOT NULL,
    modified_on     TIMESTAMP DEFAULT now() NOT NULL,
    created_by      VARCHAR(255) NOT NULL,
    modified_by     VARCHAR(255) NOT NULL,
    user_id         int not null,
    name            VARCHAR(50) NOT NULL,
    description     VARCHAR(255) NOT NULL,
    status          VARCHAR(1) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES projectk_user (id)
);

INSERT INTO project(id, created_by, modified_by, user_id, name, description, status)
VALUES
    (1, 'bootstrap', 'bootstrap', 2, 'project 1-1', 'First test project', 'A'),
    (2, 'bootstrap', 'bootstrap', 2, 'project 1-2', 'Second test project', 'A');