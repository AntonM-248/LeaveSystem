package com.TechRenowned.H_R.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.sql.Date;

@AllArgsConstructor
@Getter
public class LeaveRequest implements Serializable {
    boolean approved;

    public boolean isApproved() {
        return approved;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public int getId() {
        return id;
    }

    public int getEmployeeid() {
        return employeeid;
    }

    public int getManagerid() {
        return managerid;
    }

    Date start;
    Date end;
    int id;
    int employeeid;
    int managerid;
    int numdays;
    String reason;
}
