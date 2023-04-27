package com.zerobase.zerobasestudy.repository.wifi;

import com.zerobase.zerobasestudy.dto.wifi.WifiDto;
import com.zerobase.zerobasestudy.entity.history.History;
import com.zerobase.zerobasestudy.entity.wifi.Wifi;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WifiRepositoryJdbc implements WifiRepository {

    private final DataSource dataSource;

    public WifiRepositoryJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int count() {
        String sql = "select count(id) from wifi";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            if(rs.next()){
              return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(conn, stmt, rs);
        }


        return 0;
    }


    public int save(List<Wifi> wifiList) {
        String sql = "insert into wifi "
                + "(control_number, borough, name, address, detailed_address, floor, type, agency, "
                + "service_type, net_type, installation_year, inout_door, conn_env, longitude, latitude, work_date) "
                + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(sql);

            for(Wifi wifi : wifiList){

                stmt.setString(1, wifi.getControlNumber());
                stmt.setString(2, wifi.getBorough());
                stmt.setString(3, wifi.getName());
                stmt.setString(4, wifi.getAddress());
                stmt.setString(5, wifi.getDetailedAddress());
                stmt.setString(6, wifi.getFloor());
                stmt.setString(7, wifi.getType());
                stmt.setString(8, wifi.getAgency());
                stmt.setString(9, wifi.getServiceType());
                stmt.setString(10, wifi.getNetType());
                stmt.setString(11, wifi.getInstallationYear());
                stmt.setString(12, wifi.getInoutDoor());
                stmt.setString(13, wifi.getConnEnv());
                stmt.setDouble(14, wifi.getLongitude());
                stmt.setDouble(15, wifi.getLatitude());
                stmt.setString(16, wifi.getWorkDate().toString());

                stmt.addBatch();
            }

            int[] result = stmt.executeBatch();
            if(result.length == 0){
                return -1;
            }
            System.out.println(result.length);
            return result.length;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(conn, stmt, null);
        }

    }


    public void deleteAll() {
        String sql = "delete from wifi";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(conn, stmt, null);
        }
    }


    public List<WifiDto.Response> findAllByLatAndLng(Double latitude, Double longitude, Integer limit) {
        String sql = "select " +
                "control_number, borough, name, address, detailed_address, floor, type, agency, service_type, net_type, installation_year, inout_door, conn_env, longitude, latitude, work_date, " +
                "(6371 * acos(cos(radians(?)) * cos(radians(latitude)) * cos(radians(longitude) - radians(?)) + sin(radians(?)) * sin(radians(latitude)))) AS distance" +
                " FROM wifi " +
                "ORDER BY distance LIMIT ?";



        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setDouble(1, latitude);
            stmt.setDouble(2, longitude);
            stmt.setDouble(3, latitude);
            stmt.setInt(4, limit);


            rs = stmt.executeQuery();

            List<WifiDto.Response> list = new ArrayList<>();
            while(rs.next()){
                WifiDto.Response dto = new WifiDto.Response();

                dto.setDistance(rs.getString("distance"));
                dto.setControlNumber(rs.getString("control_number"));
                dto.setBorough(rs.getString("borough"));
                dto.setName(rs.getString("name"));
                dto.setAddress(rs.getString("address"));
                dto.setDetailedAddress(rs.getString("detailed_address"));
                dto.setFloor(rs.getString("floor"));
                dto.setType(rs.getString("type"));
                dto.setAgency(rs.getString("agency"));
                dto.setServiceType(rs.getString("service_type"));
                dto.setNetType(rs.getString("net_type"));
                dto.setInstallationYear(rs.getString("installation_year"));
                dto.setInoutDoor(rs.getString("inout_door"));
                dto.setConnEnv(rs.getString("conn_env"));
                dto.setLongitude(rs.getDouble("longitude"));
                dto.setLatitude(rs.getDouble("latitude"));
                dto.setWorkDate(rs.getString("work_date"));
                list.add(dto);
            }

            return list;

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
