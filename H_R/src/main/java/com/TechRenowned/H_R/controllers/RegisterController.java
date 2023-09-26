package com.TechRenowned.H_R.controllers;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
@CrossOrigin
@RestController
public class RegisterController {

    String url = "jdbc:postgresql://localhost:5432/TechRenowned";
    String user = "postgres";
    String pass = "admin";
    @GetMapping("getemployees")
    public JSONPObject getEmployees(){
        try(Connection connection = DriverManager.getConnection(url, user, pass);
                Statement statement = connection.createStatement();) {
            String selectSql = "select * from public.employee order by id asc";
            ResultSet resultSet = statement.executeQuery(selectSql);
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String userName = resultSet.getString("userName");
                String password = resultSet.getString("password");
                System.out.println(id + " " + userName + " " + password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("getempcount/{username}/{password}")
    public long getEmployeeCount(@PathVariable("username") String username,
                                 @PathVariable("password") String password){
        String selectSql = "select count(*) from public.employee where username = ? and password = ?";
        try(Connection connection = DriverManager.getConnection(url, user, pass);
            PreparedStatement statement = connection.prepareStatement(selectSql,
                    Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getLong("count");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (long) 0;
    }

    @GetMapping("getmancount/{username}/{password}")
    public long getManagerCount(@PathVariable("username") String username,
                                @PathVariable("password") String password){
        String selectSql = "select count(*) from public.manager where username = ? and password = ?";
        try(Connection connection = DriverManager.getConnection(url, user, pass);
            PreparedStatement statement = connection.prepareStatement(selectSql,
                    Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getLong("count");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (long) 0;
    }

    @GetMapping("getemp/{username}/{password}")
    public ResponseEntity<Object> getEmployeeJson(@PathVariable("username") String username,
                                                  @PathVariable("password") String password){
        String selectSql = "select * from public.employee where username = ? and password = ?";
        try(Connection connection = DriverManager.getConnection(url, user, pass);
            PreparedStatement statement = connection.prepareStatement(selectSql,
                    Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            Map<String, String> map = new HashMap<>();
            map.put("id", String.valueOf(resultSet.getInt("id")));
            map.put("username", resultSet.getString("userName"));
            map.put("password", resultSet.getString("password"));
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("getman/{username}/{password}")
    public ResponseEntity<Object> getManagerJson(@PathVariable("username") String username,
                                                  @PathVariable("password") String password){
        String selectSql = "select * from public.manager where username = ? and password = ?";
        try(Connection connection = DriverManager.getConnection(url, user, pass);
            PreparedStatement statement = connection.prepareStatement(selectSql,
                    Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            Map<String, String> map = new HashMap<>();
            map.put("id", String.valueOf(resultSet.getInt("id")));
            map.put("username", resultSet.getString("userName"));
            map.put("password", resultSet.getString("password"));
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("regemp/{username}/{password}")
    public void regemp(@PathVariable("username") String userName,
                             @PathVariable("password") String password){
        String SQL = "INSERT INTO public.employee(\"username\",password) "
            + "VALUES(?,?)";
        try(Connection connection = DriverManager.getConnection(url, user, pass);
            PreparedStatement pstmt = connection.prepareStatement(SQL,
                    Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, userName);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("regman/{username}/{password}")
    public void regman(@PathVariable("username") String userName,
                             @PathVariable("password") String password){
        String SQL = "INSERT INTO public.manager(\"username\",password) "
                + "VALUES(?,?)";
        try(Connection connection = DriverManager.getConnection(url, user, pass);
            PreparedStatement pstmt = connection.prepareStatement(SQL,
                    Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, userName);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
