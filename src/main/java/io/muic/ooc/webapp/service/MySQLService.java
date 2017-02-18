package io.muic.ooc.webapp.service;

import com.ja.security.PasswordHash;
import com.mysql.jdbc.exceptions.MySQLDataException;

import java.sql.*;
import java.util.ArrayList;

import static io.muic.ooc.webapp.Webapp.tracker;

public class MySQLService {

    enum TestTableColumns {
        id, username,password;
    }

    private final String jdbcDriverStr;
    private final String jdbcURL;

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    public static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
    public static final String MYSQL_URL = "jdbc:mysql://localhost:3306/database_name";

    public MySQLService(String jdbcDriverStr, String jdbcURL) {
        this.jdbcDriverStr = jdbcDriverStr;
        this.jdbcURL = jdbcURL;
    }


    public void writeData(String username, String password)  {
        try {
            Class.forName(jdbcDriverStr);

            connection = DriverManager.getConnection(jdbcURL + "?useSSL=false", "root", "1234");

            preparedStatement = connection.prepareStatement("insert into test (username,password) values (?,?);");
            preparedStatement.setString(1,username);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
        }catch (SQLException m){
            m.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }


    }
    public void updateDB(String uid, String usr){
        try {

            connection = DriverManager.getConnection(jdbcURL + "?useSSL=false", "root", "1234");

            preparedStatement = this.connection.prepareStatement("update database_name.test SET username = ? where id = '" + uid + "'  ");
            preparedStatement.setString(1,usr);
            preparedStatement.executeUpdate();
            System.out.println("la");

        }catch (SQLException s){
            s.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<String[]> readData() throws Exception {
        ArrayList<String[]> rs = new ArrayList<>();
        try {
            Class.forName(jdbcDriverStr);
//            connection = DriverManager.getConnection(jdbcURL);
            connection = DriverManager.getConnection(jdbcURL + "?useSSL=false", "root", "1234");
            statement = connection.createStatement();

            resultSet = statement.executeQuery("select * from test;");
            rs = getResultSet(resultSet);

        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            close();
            return rs;
        }
    }
//

    private ArrayList<String[]> getResultSet(ResultSet resultSet) throws Exception {
        ArrayList<String[]> result = new ArrayList<>();
        while (resultSet.next()) {
            Integer id = resultSet.getInt(TestTableColumns.id.toString());
            String usr = resultSet.getString(TestTableColumns.username.toString());
            String pass = resultSet.getString(TestTableColumns.password.toString());
            String[] temp = {usr,pass};
            result.add(temp);
        }
        return result;
    }

    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
        }
    }

    public boolean isInDatabase(String check) throws Exception{
        for (String[] x :readData()){
            if (x[0].equals(check)){
                System.out.println(x[0].equals(check));
                return true;
            }
        }
        return false;

    }
    public void deleteDB(String username){


        try {
            Class.forName(jdbcDriverStr);
            connection = DriverManager.getConnection(jdbcURL + "?useSSL=false", "root", "1234");
            statement = connection.createStatement();

            statement.execute("delete from test where username = '" + username + "'  ");
//            statement.executeUpdate();

        }catch (SQLException s){
            s.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }




    public boolean isValidInput(String[] input)throws Exception{
        ArrayList<String[]> currentDB = readData();
        for(int i = 0; i< currentDB.size(); i++){
            String[] currentRecord = currentDB.get(i);
            if(currentRecord[0].equals(input[0])){

                return new PasswordHash().validatePassword(input[1],currentRecord[1]);
            }
        }
        return false;

    }


}
