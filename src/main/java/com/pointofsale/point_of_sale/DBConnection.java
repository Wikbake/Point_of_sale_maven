/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pointofsale.point_of_sale;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DBConnection class provides communication between database and application. For now it is going to return
 * mocked values as is in <code>ResultSet</code>. Moreover it is set to cooperate with PostgreSQL by default.
 * Example id's to enter:
 *      123
 *      456
 *      789
 *      01234567890123
 * With them even though excepton will be thrown the application will work correctly.
 */

public class DBConnection {

    private static Connection dbConnection;

    static void getDBConncetion() {
        try {

        Class.forName("org.postgresql.Driver");
        dbConnection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Point_of_sale", "postgres", "postgres");

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static ResultSet executeQuery(String query) throws SQLException {
        /*
        *
        * You can use this in case of real database.
        *
        return dbConnection.createStatement().executeQuery(query);
         */

        ResultSet rs1 = MockResultSet.create(new String[] {"id", "name", "cost"},
                new Object[][] {
                        {123L, "Kajzerka", 0.20}
                });
        ResultSet rs2 = MockResultSet.create(new String[] {"id", "name", "cost"},
                new Object[][] {
                        {456L, "Woda", 2.99}
                });
        ResultSet rs3 = MockResultSet.create(new String[] {"id", "name", "cost"},
                new Object[][] {
                        {789L, "Lizak", 0.59}
                });
        ResultSet rs4 = MockResultSet.create(new String[] {"id", "name", "cost"},
                new Object[][] {
                        {1234567890123L, "Mleko", 3.99}
                });
        ResultSet emptyRs = MockResultSet.create(new String[] { "col1" }, new Object[][] {});

        if (Double.parseDouble(query) == 123L)
            return rs1;
        else if (Double.parseDouble(query) == 456L)
            return rs2;
        else if (Double.parseDouble(query) == 789L)
            return rs3;
        else if (Double.parseDouble(query) == 1234567890123L)
            return rs4;
        else
            return emptyRs;
    }
}
