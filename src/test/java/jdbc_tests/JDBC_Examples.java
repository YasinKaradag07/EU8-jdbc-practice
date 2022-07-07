package jdbc_tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class JDBC_Examples {

    String dbUrl = "jdbc:oracle:thin:@100.26.201.240:1521:XE";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @DisplayName("ResultSet Methods")
    @Test
    public void test1() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(("SELECT * FROM departments"));

        // move to first row
        //resultSet.next();
        //System.out.println(resultSet.getString(2));

        // display departments table in 10 - Administration _ 200 - 1700 format
        while(resultSet.next()){
            System.out.println(resultSet.getInt(1)+" - "+resultSet.getString(2)+" - "
                    +resultSet.getInt(3)+" - "+resultSet.getInt(4));
        }

        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void test2() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery(("SELECT * FROM departments"));

        // how to find how many rows we have for the query
        // move to last row
        resultSet.last();
        // get the row count
        int rowCount = resultSet.getRow();
        System.out.println(rowCount);

        // print all 2. column information
        resultSet.beforeFirst(); // moving pointer to the first row
        while(resultSet.next()){
            System.out.println(resultSet.getString(2));
        }


        resultSet.close();
        statement.close();
        connection.close();

    }

    @Test
    public void test3() throws SQLException {

        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery(("SELECT * FROM departments"));

        DatabaseMetaData dbMeatData = connection.getMetaData();

        System.out.println("dbMeatData.getUserName() = " + dbMeatData.getUserName());
        System.out.println("dbMeatData.getDatabaseProductName() = " + dbMeatData.getDatabaseProductName());
        System.out.println("dbMeatData.getDatabaseProductVersion() = " + dbMeatData.getDatabaseProductVersion());
        System.out.println("dbMeatData.getDriverName() = " + dbMeatData.getDriverName());
        System.out.println("dbMeatData.getDriverVersion() = " + dbMeatData.getDriverVersion());

        // get resultsetmetadata
        ResultSetMetaData rsMetaData = resultSet.getMetaData();

        // how many columns we have?
        int colCount = rsMetaData.getColumnCount();
        System.out.println(colCount);

        // getting column names
        System.out.println("rsMetaData.getColumnName() = " + rsMetaData.getColumnName(1));
        System.out.println("rsMetaData.getColumnName() = " + rsMetaData.getColumnName(2));

        // print all the column names dynamically
        int colCounts = rsMetaData.getColumnCount();
        for(int i=1; i<=colCounts; i++){
            System.out.println(rsMetaData.getColumnName(i));
        }


        resultSet.close();
        statement.close();
        connection.close();

    }





}
