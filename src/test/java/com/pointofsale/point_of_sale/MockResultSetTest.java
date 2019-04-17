/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pointofsale.point_of_sale;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

/**
 * Simple tests of <code>MockResultSet</code>.
 */

public class MockResultSetTest {

    private ResultSet rs;
    private ResultSet emptyRs;

    @Before
    public void setUp() throws SQLException {
        rs = MockResultSet.create(new String[] {"id", "name", "cost"},
                new Object[][] {
                        {123L, "Kajzerka", 0.20},
                        {456L, "Woda", 2.99},
                        {789L, "Lizak", 0.59},
                        {1234567890123L, "Mleko", 3.99}
                });
        emptyRs = MockResultSet.create(new String[] { "col1" }, new Object[][] {});
    }

    @Test
    public void testNext() throws SQLException {
        assertFalse(emptyRs.next());
        assertTrue(rs.next());
        assertTrue(rs.next());
        assertTrue(rs.next());
        assertTrue(rs.next());
        assertFalse(rs.next());
    }

    @Test
    public void testGetLong() throws SQLException {
        rs.next();
        assertEquals(rs.getLong("id"), 123L);
        rs.next();
        assertEquals(rs.getLong("id"), 456L);
        rs.next();
        assertEquals(rs.getLong("id"), 789L);
        rs.next();
        assertEquals(rs.getLong("id"), 1234567890123L);
    }

    @Test
    public void testGetString() throws SQLException {
        rs.next();
        assertEquals(rs.getString("name"), "Kajzerka");
        rs.next();
        assertEquals(rs.getString("name"), "Woda");
        rs.next();
        assertEquals(rs.getString("name"), "Lizak");
        rs.next();
        assertEquals(rs.getString("name"), "Mleko");
    }

    @Test
    public void testGetDouble() throws SQLException {
        rs.next();
        assertEquals(rs.getDouble("cost"), 0.20, 0.01);
        rs.next();
        assertEquals(rs.getDouble("cost"), 2.99, 0.01);
        rs.next();
        assertEquals(rs.getDouble("cost"), 0.59, 0.01);
        rs.next();
        assertEquals(rs.getDouble("cost"), 3.99, 0.01);
    }
}
