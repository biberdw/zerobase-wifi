-- sqlite

DROP TABLE IF EXISTS `WIFI_BOOKMARK`;
DROP TABLE IF EXISTS `WIFI`;
DROP TABLE IF EXISTS `HISTORY`;
DROP TABLE IF EXISTS `BOOKMARK`;

PRAGMA foreign_keys = ON;
CREATE TABLE WIFI (
        wifi_id INTEGER PRIMARY KEY,
        control_number TEXT,
        borough TEXT,
        name TEXT,
        address TEXT,
        detailed_address TEXT,
        floor TEXT,
        type TEXT,
        agency TEXT,
        service_type TEXT,
        net_type TEXT,
        installation_year TEXT,
        inout_door TEXT,
        conn_env TEXT,
        longitude REAL,
        latitude REAL,
        work_date DATETIME
);

CREATE TABLE HISTORY (
    history_id INTEGER PRIMARY KEY,
    longitude REAL NOT NULL,
    latitude REAL NOT NULL,
    created DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE BOOKMARK (
    bookmark_id INTEGER PRIMARY KEY,
    name TEXT NOT NULL,
    sequence_num INTEGER NOT NULL,
    created DATETIME DEFAULT CURRENT_TIMESTAMP,
    modified DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE WIFI_BOOKMARK (
    wifi_name TEXT NOT NULL,
    bookmark_name TEXT NOT NULL,
    bookmark_id INTEGER NOT NULL,
    wifi_id INTEGER NOT NULL,
    created DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (bookmark_id, wifi_id),
    FOREIGN KEY (bookmark_id) REFERENCES bookmark( bookmark_id ) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (wifi_id) REFERENCES wifi( wifi_id) ON UPDATE CASCADE ON DELETE CASCADE
);




-- mysql;
-- DROP TABLE IF EXISTS `WIFI_BOOKMARK`;
-- DROP TABLE IF EXISTS `WIFI`;
-- DROP TABLE IF EXISTS `HISTORY`;
-- DROP TABLE IF EXISTS `BOOKMARK`;
--
--
-- CREATE TABLE WIFI (
--         wifi_id BIGINT AUTO_INCREMENT,
--         control_number VARCHAR(255),
--         borough VARCHAR(255),
--         name VARCHAR(255),
--         address VARCHAR(255),
--         detailed_address VARCHAR(255),
--         floor VARCHAR(255),
--         type VARCHAR(255),
--         agency VARCHAR(255),
--         service_type VARCHAR(255),
--         net_type VARCHAR(255),
--         installation_year VARCHAR(50),
--         inout_door VARCHAR(255),
--         conn_env VARCHAR(255),
--         longitude DOUBLE,
--         latitude DOUBLE,
--         work_date DATETIME,
--         CONSTRAINT wifi__wifi_id__pk PRIMARY KEY (wifi_id)
--
--
-- );
--
-- CREATE TABLE HISTORY (
--     history_id BIGINT AUTO_INCREMENT,
--     longitude DOUBLE NOT NULL,
--     latitude DOUBLE NOT NULL,
--     created DATETIME NOT NULL DEFAULT NOW(),
--
--     CONSTRAINT history__history_id__pk PRIMARY KEY (history_id),
--     CONSTRAINT history__longitude__notnull CHECK ( longitude is not null ),
--     CONSTRAINT history__latitude__notnull CHECK ( latitude is not null),
--     CONSTRAINT history__created__notnull CHECK ( created is not null )
--
-- );
--
-- CREATE TABLE BOOKMARK (
--     bookmark_id BIGINT AUTO_INCREMENT,
--     name VARCHAR(50) NOT NULL,
--     sequence_num INT NOT NULL,
--     created DATETIME DEFAULT NOW(),
--     modified DATETIME DEFAULT NOW(),
--
--     CONSTRAINT bookmark__bookmark_id__pk PRIMARY KEY ( bookmark_id),
--     CONSTRAINT bookmark__name__notnull CHECK ( name is not null ),
--     CONSTRAINT bookmark__sequence_num__notnull CHECK ( sequence_num is not null ),
--     CONSTRAINT bookmark__created__notnull CHECK ( created is not null ),
--     CONSTRAINT bookmark__modified__notnull CHECK ( modified is not null )
--
-- );
--
--
-- CREATE TABLE WIFI_BOOKMARK (
--     wifi_bookmark_id BIGINT AUTO_INCREMENT,
--     wifi_name VARCHAR(255) NOT NULL,
--     bookmark_name VARCHAR(255) NOT NULL,
--     bookmark_id BIGINT NOT NULL,
--     wifi_id BIGINT NOT NULL,
--     created DATETIME DEFAULT NOW() NOT NULL,
--
--     CONSTRAINT wifi_bookmark__pk PRIMARY KEY (bookmark_id, wifi_id),
--     CONSTRAINT wifi_bookmark__wifi_name__notnull CHECK ( wifi_name is not null ),
--     CONSTRAINT wifi_bookmark__bookmark_name__notnull CHECK ( bookmark_name is not null ),
--     CONSTRAINT wifi_bookmark__bookmark_id__fk FOREIGN KEY (bookmark_id) REFERENCES bookmark( bookmark_id ),
--     CONSTRAINT wifi_bookmark__wifi_id__fk FOREIGN KEY (wifi_id) REFERENCES wifi( wifi_id),
--     CONSTRAINT wifi_bookmark__created__notnull CHECK ( created is not null ),
--     CONSTRAINT wifi_bookmark__wifi_bookmark_id__unique UNIQUE (wifi_bookmark_id)
-- );