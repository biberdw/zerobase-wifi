package com.zerobase.zerobasestudy.util.constutil;

import java.time.LocalDateTime;

public abstract class DatabaseConst {

    public static final String URL = "jdbc:sqlite:/Users/DONGWOO/study/zerobase.db";
//    public static final String URL = "jdbc:mysql://localhost:3306/zerobase";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "A123456a#";
    public static final String DATASOURCE = "dataSource";
//    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DRIVER = "org.sqlite.JDBC";



    //Sort를 위한 db 컬럼명
    //bookmark
    public static final String BOOKMARK_ID = "bookmark_id";
    public static final String NAME = "name";
    public static final String SEQUENCE_NUM = "sequence_num";
    public static final String CREATED = "created";
    private static final String MODIFIED = "modified";

    //wifiBookmark
    public static final String WIFI_NAME = "wifi_name";
    public static final String BOOKMARK_NAME = "bookmark_name";

    //history
    public static final String HISTORY_ID = "history_id";
    public static final String LONGITUDE = "longitude";
    public static final String LATITUDE = "latitude";

    //wifi
    public static final String WIFI_ID = "wifi_id";
    public static final String CONTROL_NUMBER = "control_number";
    public static final String BOROUGH = "borough";
    public static final String ADDRESS = "address";
    public static final String DETAILED_ADDRESS = "detailed_address";
    public static final String FLOOR = "floor";
    public static final String TYPE = "type";
    public static final String AGENCY = "agency";
    public static final String SERVICE_TYPE = "service_type";
    public static final String NET_TYPE = "net_type";
    public static final String INSTALLATION_YEAR = "installation_year";
    public static final String INOUT_DOOR = "inout_door";
    public static final String CONN_ENV = "conn_env";
    public static final String WORK_DATE = "work_date";






}
