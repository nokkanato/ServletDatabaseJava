package io.muic.ooc.webapp.service;

import java.sql.*;
import java.util.ArrayList;

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

    public MySQLService(String jdbcDriverStr, String jdbcURL) {
        this.jdbcDriverStr = jdbcDriverStr;
        this.jdbcURL = jdbcURL;
    }

    public void writeData(String username, String password) throws Exception{
        preparedStatement = connection.prepareStatement("insert into test.test_table values (default ,?,?)");
        preparedStatement.setString(1,username);
        preparedStatement.setString(2, password);
        preparedStatement.executeUpdate();

    }

    public ArrayList<String[]> readData() throws Exception {
        ArrayList<String[]> rs = new ArrayList<>();
        try {
            Class.forName(jdbcDriverStr);
//            connection = DriverManager.getConnection(jdbcURL);
            connection = DriverManager.getConnection(jdbcURL + "?useSSL=false", "root", "");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from test.test_table;");
            rs = getResultSet(resultSet);

        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            close();
            return rs;
        }
    }

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

    public boolean isValidInput(String[] input)throws Exception{
        ArrayList<String[]> currentDB = readData();
        for(int i = 0; i< currentDB.size(); i++){
            String[] currentRecord = currentDB.get(i);
            if(currentRecord[0].equals(input[0]) && currentRecord[1].equals(input[1])){
                return true;
            }
        }
        return false;

    }
}
