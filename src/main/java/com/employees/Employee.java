package com.employees;

import java.time.LocalDate;

public record Employee(int employeeId, int projectId, LocalDate dateFrom, LocalDate dateTo) {
}