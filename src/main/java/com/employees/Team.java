package com.employees;

public class Team {
    private final int employeeOneId;
    private final int employeeTwoId;
    private final int projectId;
    private final int duration;

    public Team(int employeeOneId, int employeeTwoId, int projectId, int duration) {
        this.employeeOneId = employeeOneId;
        this.employeeTwoId = employeeTwoId;
        this.projectId = projectId;
        this.duration = duration;
    }

    public int getEmployeeOneId() {
        return employeeOneId;
    }

    public int getEmployeeTwoId() {
        return employeeTwoId;
    }

    public int getProjectId() {
        return projectId;
    }

    public int getDuration() {
        return duration;
    }
}