package com.tarashor.siteparser.saver;

import com.tarashor.siteparser.models.BorderPassStatistic;
import com.tarashor.siteparser.models.BorderPassStatisticItem;

import java.sql.*;
import java.util.Properties;

/**
 * Created by Taras on 02.02.2017.
 */
public class DBSaver implements Saver {
    private String server;
    private String user;
    private String password;

    public DBSaver(String server, String user, String password){
        this.server = server;
        this.user = user;
        this.password = password;
    }

    public void save(BorderPassStatistic statistic) {
        try {
            Connection con = getConnection();

            PreparedStatement ps = con.prepareStatement(insertStatisticItemStringMYSQL);
            Timestamp timestamp = new Timestamp(statistic.getDate().getTime());

            for (BorderPassStatisticItem item : statistic.getStatisticItems()){
                ps.setString(1, item.getName());
                ps.setInt(2, item.getCarsCountOnBorder());
                ps.setInt(3, item.getCarsCountBeforeBorder());
                ps.setTimestamp(4, timestamp);
                ps.addBatch();
                ps.clearParameters();
            }

            int[] results = ps.executeBatch();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static final String CONNECTION_STRING_TEMPLATE = "jdbc:mysql://%s:3306/borderstat";


    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(getConnectionString(), getConnectionProperties());
    }

    private Properties getConnectionProperties() {
        Properties properties = new Properties();
        properties.setProperty("user", user);
        properties.setProperty("password", password);
        properties.setProperty("useUnicode","true");
        properties.setProperty("characterEncoding","UTF-8");
        return properties;
    }

    private String getConnectionString() {
        return String.format(CONNECTION_STRING_TEMPLATE, server);

    }

    private static final String insertStatisticItemStringMYSQL = "replace into stat (pass_name, car_count_on, car_count_before, date) values (?, ?, ?, ?)";
}
