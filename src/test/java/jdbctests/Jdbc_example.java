package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;

public class Jdbc_example {
    //connection string
    String dbUrl = "jdbc:oracle:thin:@3.85.62.235:1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void test01() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement=connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from employees");


        while(resultSet.next()){
            String regionName = resultSet.getString("region_name");
            System.out.println(regionName);
        }

        //using same object to run another query
        //================
        resultSet = statement.executeQuery("Select * from countries");
        while(resultSet.next()){
            System.out.println(resultSet.getString(2));
        }

        //close connection
        resultSet.close();
        statement.close();
        connection.close();


    }

    @Test
    public void CountandNavigate() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from departments");

        //how to get how many rows is in the table
        //firstly go last row
        resultSet.last();
        //get row number by using getRow() method
        int rowCount=resultSet.getRow();
        System.out.println("rowCount = " + rowCount);

        //go back first row to get full rows
        resultSet.beforeFirst();
        while (resultSet.next()){
            System.out.println(resultSet.getString("department_name"));
        }

        //close connection
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void metadata() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        Statement statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from departments");

        //get the databases related data inside the metadata object
        DatabaseMetaData metaData=connection.getMetaData();

        System.out.println("User: " + metaData.getUserName());
        System.out.println("Database produck name: " + metaData.getDatabaseProductName());
        System.out.println("Database product version: " + metaData.getDatabaseProductVersion());
        System.out.println("Driver: " + metaData.getDriverName());
        System.out.println("Driver version: " + metaData.getDriverVersion());

        //get the resultset object metadata
        ResultSetMetaData resultSetMetaData=resultSet.getMetaData();

        System.out.println("How many column: " + resultSetMetaData.getColumnCount());

        System.out.println("Column Name: " + resultSetMetaData.getColumnName(1));
        System.out.println("Column Name: " + resultSetMetaData.getColumnName(2));
        System.out.println("Column Name: " + resultSetMetaData.getColumnName(3));

        // print all the column names dynamicially

        for(int i=1; i<=resultSetMetaData.getColumnCount(); i++){
            System.out.println("Column Name: " + resultSetMetaData.getColumnName(i));
        }

        //close connection
        resultSet.close();
        statement.close();
        connection.close();
    }

}
