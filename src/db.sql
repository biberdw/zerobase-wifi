-- sqlite

-- CREATE TABLE WIFI (
-- id INTEGER PRIMARY KEY AUTOINCREMENT,
--     control_number VARCHAR(255),
--     borough VARCHAR(255),
--     name VARCHAR(255),
--     address VARCHAR(255),
--     detailed_address VARCHAR(255),
--     floor VARCHAR(255),
--     type VARCHAR(255),
--     agency VARCHAR(255),
--     service_type VARCHAR(255),
--     net_type VARCHAR(255),
--     installation_year VARCHAR(50),
--     inout_door VARCHAR(255),
--     conn_env VARCHAR(255),
--     longitude REAL,
--     latitude REAL,
--     work_date DATETIME
-- );

-- mysql;
CREATE TABLE WIFI (
        id INT PRIMARY KEY AUTO_INCREMENT,
        control_number VARCHAR(255),
        borough VARCHAR(255),
        name VARCHAR(255),
        address VARCHAR(255),
        detailed_address VARCHAR(255),
        floor VARCHAR(255),
        type VARCHAR(255),
        agency VARCHAR(255),
        service_type VARCHAR(255),
        net_type VARCHAR(255),
        installation_year VARCHAR(50),
        inout_door VARCHAR(255),
        conn_env VARCHAR(255),
        longitude DOUBLE,
        latitude DOUBLE,
        work_date DATETIME
);

CREATE TABLE History (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    longitude DOUBLE NOT NULL,
    latitude DOUBLE NOT NULL,
    created DATETIME NOT NULL DEFAULT NOW()
);