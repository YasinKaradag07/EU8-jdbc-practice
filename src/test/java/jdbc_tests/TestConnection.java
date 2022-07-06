package jdbc_tests;

import java.sql.*;

public class TestConnection {

    public static void main(String[] args) throws SQLException {

        String dbUrl = "jdbc:oracle:thin:@100.26.201.240:1521:XE";
        String dbUsername = "hr";
        String dbPassword = "hr";

        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(("SELECT * FROM regions"));

        //once you set up connection default pointer looks for 0
        //next()====> move pointer to first row
        resultSet.next();

        //getting information with column name
        System.out.println(resultSet.getString("region_name"));

        //getting information with column index(starts from 1)
        System.out.println(resultSet.getString(2));

        //1-Europe
        //2-Americas
        System.out.println(resultSet.getInt(1)+" - "+resultSet.getString(2));
        resultSet.next(); //moving to the next row
        System.out.println(resultSet.getInt(1)+" - "+resultSet.getString(2));

        resultSet.next();
        System.out.println(resultSet.getInt(1)+" - "+resultSet.getString(2));

        /*
        printing all rows
        while(resultSet.next()){
            System.out.println(resultSet.getInt(1)+" - "+resultSet.getString(2));
        }
*/
        //close connection in reverse order
        resultSet.close();
        statement.close();
        connection.close();




    }
}
