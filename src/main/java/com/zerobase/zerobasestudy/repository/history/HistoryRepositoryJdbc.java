package com.zerobase.zerobasestudy.repository.history;

import com.zerobase.zerobasestudy.entity.history.History;
import com.zerobase.zerobasestudy.util.ConnectionSyncManager;
import com.zerobase.zerobasestudy.util.Sort;
import com.zerobase.zerobasestudy.util.exception.SqlException;
import lombok.RequiredArgsConstructor;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class HistoryRepositoryJdbc implements HistoryRepository {


    /** 히스토리 등록 */
    public int save(History history) {
        String sql = "INSERT INTO history (longitude, latitude, created) "
                + "VALUES (?, ?, ?)";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, history.getLongitude());
            stmt.setDouble(2, history.getLatitude());
            stmt.setString(3, history.getCreated().toString());


            return stmt.executeUpdate();


        } catch (SQLException cause) {
            throw new SqlException(cause.getMessage(), cause);
        } finally {
            release(conn, stmt, null);

        }

    }


    /** 히스토리 단건 조회 */
    public Optional<History> findById(Long id) {
        String sql = "SELECT history_id, longitude, latitude, created FROM history "
                + "where history_id = ?";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);
            rs = stmt.executeQuery();

            if(rs.next()){
                return Optional.ofNullable(History.builder()
                        .id(rs.getLong("history_id"))
                        .longitude(rs.getDouble("longitude"))
                        .latitude(rs.getDouble("latitude"))
                        .created(LocalDateTime.parse(rs.getString("created"), DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                        .build());
            }

            return Optional.empty();

        } catch (SQLException cause) {
            throw new SqlException(cause.getMessage(), cause);
        } finally {
            release(conn, stmt, rs);
        }
    }

    /** 히스토리 전체 조회 */
    public List<History> findAll(Integer limit, Sort sort) {
        String sql = "SELECT history_id, longitude, latitude, created FROM history";

        if(sort != null) sql += " ORDER BY " + sort.getOrderBy();
        if(limit != null && limit > 0) sql += " LIMIT "  + limit;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;


        try{
            conn = getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();


            List<History> histories = new ArrayList<>();
            while(rs.next()){
                History history = History.builder()
                        .id(rs.getLong("history_id"))
                        .longitude(rs.getDouble("longitude"))
                        .latitude(rs.getDouble("latitude"))
                        .created(LocalDateTime.parse(rs.getString("created"), DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                        .build();
                histories.add(history);
            }
            return histories;
        } catch (SQLException cause) {
            throw new SqlException(cause.getMessage(), cause);
        } finally {
            release(conn, stmt, rs);
        }
    }

    /** 히스토리 단건 삭제 */
    public int deleteById(Long id) {
        String sql = "delete from history where history_id = ?";

        Connection conn = null;
        PreparedStatement stmt = null;


        try {
            conn = getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);


            return stmt.executeUpdate();


        } catch (SQLException cause) {
            throw new SqlException(cause.getMessage(), cause);
        } finally {
            release(conn, stmt, null);

        }

    }


    private Connection getConnection() throws SQLException {
        return ConnectionSyncManager.getConnection();
    }

    /** 커넥션 종료(반환) */
    private void release(Connection conn, Statement stmt, ResultSet rs) {
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
        ConnectionSyncManager.release(conn);
    }
}
