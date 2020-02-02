package jdbctests;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        //connection string
        String dbUrl = "jdbc:oracle:thin:@3.85.62.235:1521:xe";
        String dbUsername = "hr";
        String dbPassword = "hr";
        //create connection to database
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);

        //create statement
        Statement statement=connection.createStatement();

        //run query and get the result in the result object
        ResultSet resultSet = statement.executeQuery("select * from employees");

        //move the pointer to next row
        //resultSet.next();
        //System.out.println("resultSet = " + resultSet.getString("region_name"));

        while (resultSet.next()){
            System.out.println(resultSet.getString(2) + "--" +
                    resultSet.getString(3) + "--" +
                    resultSet.getString("salary"));
        }

        //close connection
        resultSet.close();
        statement.close();
        connection.close();


    }
}
