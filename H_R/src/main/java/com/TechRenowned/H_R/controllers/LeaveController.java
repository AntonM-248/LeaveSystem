package com.TechRenowned.H_R.controllers;

import com.TechRenowned.H_R.entities.LeaveRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@CrossOrigin
@RestController
public class LeaveController {
    String url = "jdbc:postgresql://localhost:5432/TechRenowned";
    String user = "postgres";
    String pass = "admin";

    @PostMapping("/requestleave/{start}/{end}/{empid}/{reason}")
    public void requestLeave(@PathVariable("empid") int empid,  @PathVariable("reason") String reason,
            @PathVariable("start") Date start, @PathVariable("end") Date end){
        String sql = "insert into public.leaveapplications (approved, \"start\", \"end\", employeeid, reason)" +
                " values (?,?,?,?, ?)";
        try(Connection conn = DriverManager.getConnection(url, user, pass);
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBoolean(1, false);
            ps.setDate(2, start);
            ps.setDate(3, end);
            ps.setInt(4, empid);
            ps.setString(5, reason);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/getleaverequests")
    public ResponseEntity<List<LeaveRequest>> getLeaveRequests(){
        String sql = "select * from public.leaveapplications where managerid is null";
        try(Connection con = DriverManager.getConnection(url, user, pass);
                PreparedStatement ps = con.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            List<LeaveRequest> lrList = new ArrayList<>();
            while(rs.next()){
                LeaveRequest lr = new LeaveRequest(rs.getBoolean(1), rs.getDate(2),
                        rs.getDate(3), rs.getInt(4), rs.getInt(5),
                        rs.getInt(6), rs.getInt(7), rs.getString(8));
                lrList.add(lr);
            }
            return ResponseEntity.ok(lrList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PutMapping("/respondleave/{approved}/{managerid}/{id}")
    public void respondLeave(@PathVariable("approved") boolean approved,
            @PathVariable("managerid") int managerid, @PathVariable("id") int id){
        String sql = "UPDATE public.leaveapplications " +
                "  SET approved=?, managerid=? " +
                "  WHERE id = ?";
        try(Connection con = DriverManager.getConnection(url, user, pass);
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setBoolean(1, approved);
            ps.setInt(2, managerid);
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
