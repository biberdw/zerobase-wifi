package com.zerobase.zerobasestudy.repository.history;

import com.zerobase.zerobasestudy.entity.history.History;
import com.zerobase.zerobasestudy.util.constutil.OrderBy;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HistoryRepositoryJdbc implements HistoryRepository {

    private final DataSource dataSource;


    public HistoryRepositoryJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public int save(Double latitude, Double longitude) {
        String sql = "INSERT INTO history (longitude, latitude, created) "
                + "VALUES (?, ?, ?)";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, longitude);
            stmt.setDouble(2, latitude);
            stmt.setString(3, LocalDateTime.now().toString());


            return stmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(conn, stmt, null);
        }

    }

    public Optional<History> findById(Long id) {
        String sql = "SELECT id, longitude, latitude, created FROM history "
                + "where id = ?";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);
            rs = stmt.executeQuery();

            if(rs.next()){
                return Optional.ofNullable(History.builder()
                        .id(rs.getLong("id"))
                        .longitude(rs.getDouble("longitude"))
                        .latitude(rs.getDouble("latitude"))
                        .created(rs.getTimestamp("created").toLocalDateTime())
                        .build());
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public int deleteById(Long id) {
        String sql = "delete from history where id = ?";

        Connection conn = null;
        PreparedStatement stmt = null;


        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setLong(1, id);


            return stmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(conn, stmt, null);
        }

    }

    public List<History> findAll(Integer limit, OrderBy orderBy) {
        String sql = "SELECT id, longitude, latitude, created FROM history";

        if(orderBy != null) sql += " ORDER BY " + orderBy.getOrderBy();
        if(limit != null && limit > 0) sql += " LIMIT "  + limit;

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;


        try{
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();


            List<History> histories = new ArrayList<>();
            while(rs.next()){
                History history = History.builder()
                        .id(rs.getLong("id"))
                        .longitude(rs.getDouble("longitude"))
                        .latitude(rs.getDouble("latitude"))
                        .created(rs.getTimestamp("created").toLocalDateTime())
                        .build();
                histories.add(history);
            }
            return histories;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(conn, stmt, rs);
        }
    }


    private void close(Connection conn, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
