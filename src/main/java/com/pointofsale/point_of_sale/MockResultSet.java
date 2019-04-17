/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pointofsale.point_of_sale;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * This is a class which only task is to mock <code>ResultSet</code>. It is important because of a lack of possibility
 * of using real database.
 */

public class MockResultSet {

    private final Map<String, Integer> columnIndices;
    private final Object[][] data;
    private int rowIndex;

    private MockResultSet(final String[] columnNames,
                          final Object[][] data) {
        // create a map of column name to column index
        this.columnIndices = IntStream.range(0, columnNames.length)
                .boxed()
                .collect(Collectors.toMap(
                        k -> columnNames[k],
                        Function.identity(),
                        (a, b) ->
                        { throw new RuntimeException("Duplicate column " + a); },
                        LinkedHashMap::new
                ));
        this.data = data;
        this.rowIndex = -1;
    }

    private ResultSet buildMock() throws SQLException {
        final ResultSet rs = mock(ResultSet.class);

        // mock rs.next()
        doAnswer(invocation -> {
            rowIndex++;
            return rowIndex < data.length;
        }).when(rs).next();

        // mock rs.getString(columnName)
        doAnswer(invocation -> {
            final String columnName = invocation.getArgumentAt(0, String.class);
            final int columnIndex = columnIndices.get(columnName);
            return data[rowIndex][columnIndex];
        }).when(rs).getString(anyString());

        // mock rs.getInt(columnName)
        doAnswer(invocation -> {
            final String columnName = invocation.getArgumentAt(0, String.class);
            final int columnIndex = columnIndices.get(columnName);
            return (Integer) data[rowIndex][columnIndex];
        }).when(rs).getInt(anyString());

        // mock rs.getLong(columnName)
        doAnswer(invocation -> {
            final String columnName = invocation.getArgumentAt(0, String.class);
            final int columnIndex = columnIndices.get(columnName);
            return  (Long) data[rowIndex][columnIndex];
        }).when(rs).getLong(anyString());

        // mock rs.getDouble(columnName)
        doAnswer(invocation -> {
            final String columnName = invocation.getArgumentAt(0, String.class);
            final int columnIndex = columnIndices.get(columnName);
            return (Double) data[rowIndex][columnIndex];
        }).when(rs).getDouble(anyString());

        // mock rs.getObject(columnIndex)
        doAnswer(invocation -> {
            final int index = invocation.getArgumentAt(0, Integer.class);
            return data[rowIndex][index - 1];
        }).when(rs).getObject(anyInt());

        final ResultSetMetaData rsmd = mock(ResultSetMetaData.class);

        // mock rsmd.getColumnCount()
        doReturn(columnIndices.size()).when(rsmd).getColumnCount();

        // mock rs.getMetaData()
        doReturn(rsmd).when(rs).getMetaData();

        return rs;
    }

    /**
     * Creates the mock ResultSet.
     *
     * @param columnNames the names of the columns
     * @param data
     * @return a mocked ResultSet
     * @throws SQLException
     */
    public static ResultSet create(
            final String[] columnNames,
            final Object[][] data)
            throws SQLException {
        return new MockResultSet(columnNames, data).buildMock();
    }
}
