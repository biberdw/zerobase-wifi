package com.zerobase.zerobasestudy.repository.bookmark;

import com.zerobase.zerobasestudy.entity.bookmark.WifiBookmark;
import com.zerobase.zerobasestudy.util.Sort;
import com.zerobase.zerobasestudy.util.exception.SqlException;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class WifiBookmarkRepositoryJdbc implements WifiBookmarkRepository{

    private final DataSource dataSource;

    public WifiBookmarkRepositoryJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int save(WifiBookmark wifiBookmark) {

        StringBuilder sql = buildDynamicInsertSql(wifiBookmark);

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(sql.toString());

            setDynamicParameters(wifiBookmark, stmt);
            return stmt.executeUpdate();

        } catch (SQLException cause) {
            throw new SqlException(cause.getMessage(), cause);
        } finally {
            close(conn, stmt, null);
        }
    }


    public List<WifiBookmark> findAll(Integer limit, Sort sort) {
        String sql = "SELECT wifi_name, bookmark_name, bookmark_id, wifi_id, created FROM wifi_bookmark";

        if(sort != null) sql += " ORDER BY " + sort.getOrderBy();
        if(limit != null && limit > 0) sql += " LIMIT "  + limit;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            List<WifiBookmark> wifiBookmarks = new ArrayList<>();
            while(rs.next()){
                WifiBookmark wifiBookmark = WifiBookmark.builder()
                        .wifiName(rs.getString("wifi_name"))
                        .bookmarkName(rs.getString("bookmark_name"))
                        .bookmarkId(rs.getLong("bookmark_id"))
                        .wifiId(rs.getLong("wifi_id"))
                        .created(LocalDateTime.parse(rs.getString("created"), DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                        .build();
                wifiBookmarks.add(wifiBookmark);
            }
            return wifiBookmarks;

        } catch (SQLException cause) {
            throw new SqlException(cause.getMessage(), cause);
        } finally {
            close(conn, stmt, rs);
        }
    }


    public int deleteById(Long wifiId, Long bookmarkId) {
        String sql = "delete from wifi_bookmark where wifi_id = ? AND bookmark_id = ?";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, wifiId);
            stmt.setLong(2, bookmarkId);

            return stmt.executeUpdate();


        } catch (SQLException cause) {
            cause.printStackTrace();
            throw new SqlException(cause.getMessage(), cause);
        } finally {
            close(conn, stmt, null);
        }
    }

    /** 커넥션 종료(반환) */
    private void close(Connection conn, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException cause) {
                throw new SqlException(cause.getMessage(), cause);
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException cause) {
                throw new SqlException(cause.getMessage(), cause);
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException cause) {
                throw new SqlException(cause.getMessage(), cause);
            }
        }
    }

    private static StringBuilder buildDynamicInsertSql(WifiBookmark wifiBookmark) {
        int parameterIndex = 0;
        StringBuilder sql = new StringBuilder();
        sql.append("insert into wifi_bookmark ( ");


        if(wifiBookmark.getWifiName() != null){
            sql.append(" wifi_name, ");
            parameterIndex++;
        }
        if (wifiBookmark.getBookmarkName() != null) {
            sql.append(" bookmark_name, ");
            parameterIndex++;
        }
        if (wifiBookmark.getBookmarkId() != null) {
            sql.append(" bookmark_id, ");
            parameterIndex++;
        }
        if (wifiBookmark.getWifiId() != null) {
            sql.append(" wifi_id, ");
            parameterIndex++;
        }

        sql.append(" created ) ");


        sql.append(" values ( ");
        while(parameterIndex != 0){
            sql.append(" ?, ");
            parameterIndex--;
        }

        sql.append(" ? )");

        return sql;
    }

    private static void setDynamicParameters(WifiBookmark wifiBookmark, PreparedStatement stmt) throws SQLException {
        int parameterIndex = 1;

        if (wifiBookmark.getWifiName() != null) {
            stmt.setString(parameterIndex++, wifiBookmark.getWifiName());
        }
        if (wifiBookmark.getBookmarkName() != null) {
            stmt.setString(parameterIndex++, wifiBookmark.getBookmarkName());
        }
        if (wifiBookmark.getBookmarkId() != null){
            stmt.setLong( parameterIndex++, wifiBookmark.getBookmarkId());
        }
        if (wifiBookmark.getWifiId() != null) {
            stmt.setLong( parameterIndex++, wifiBookmark.getWifiId());
        }

        stmt.setString(parameterIndex++, LocalDateTime.now().toString());


    }
}
