package com.employees;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Controller {
    @FXML
    private BorderPane borderPane;
    @FXML
    public TableView<Team> bestTeamTable;

    @FXML
    public void loadTeams(){
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Team Common Projects");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV", "*.csv"));
        File file = chooser.showOpenDialog(borderPane.getScene().getWindow());

        ArrayList<Employee> employees = readEmployeesFromFile(file);
        ArrayList<Team> teams = getTeams(employees);

        sortTeams(teams);

        Team team = getMaxOverlapTeam(teams);
        int employeeOneId = team.getEmployeeOneId();
        int employeeTwoId = team.getEmployeeTwoId();

        System.out.println("Employees who have worked together on common projects for the longest period of time: " +
                "\nEmployee #1 ID: " + team.getEmployeeOneId() +
                "\nEmployee #2 ID: " + team.getEmployeeTwoId() +
                "\nTime together: " + team.getDuration() + " days.");

        final ObservableList<Team> data = FXCollections.observableArrayList();
        for(Team listTeam : teams){
            if (listTeam.getEmployeeOneId() == employeeOneId && listTeam.getEmployeeTwoId() == employeeTwoId && listTeam.getDuration() != 0)
                data.add(listTeam);
        }

        bestTeamTable.setItems(data);
    }

    public static ArrayList<Employee> readEmployeesFromFile(File file){
        ArrayList<Employee> employees = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new FileReader(file));
            while(scanner.hasNext()){
                String employeeRow = scanner.nextLine();
                String[] employeeArray = employeeRow.split(", ");

                if(Objects.equals(employeeArray[3], "NULL"))
                    employeeArray[3] = LocalDate.now().toString();

                Employee employee = new Employee(Integer.parseInt(employeeArray[0]),
                        Integer.parseInt(employeeArray[1]),
                        LocalDate.parse(employeeArray[2]),
                        LocalDate.parse(employeeArray[3]));

                employees.add(employee);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return employees;
    }

    public static ArrayList<Team> getTeams(ArrayList<Employee> employees){
        ArrayList<Team> teams =new ArrayList<>();

        for(int i = 0; i < employees.size() - 1; i++){
            for(int j = i + 1; j < employees.size(); j++){
                if(employees.get(i).projectId() == employees.get(j).projectId()){
                    LocalDate startDateBoth = employees.get(i).dateFrom().isAfter(employees.get(j).dateFrom())
                            ? employees.get(i).dateFrom()
                            : employees.get(j).dateFrom();

                    LocalDate endDateBoth = employees.get(i).dateTo().isBefore(employees.get(j).dateTo())
                            ? employees.get(i).dateTo()
                            : employees.get(j).dateTo();

                    int period = (int) ChronoUnit.DAYS.between(startDateBoth, endDateBoth);

                    if (period > 0)
                        teams.add(new Team(employees.get(i).employeeId(), employees.get(j).employeeId(), employees.get(i).projectId(), period));
                }
            }
        }

        return teams;
    }

    public static void sortTeams(ArrayList<Team> teams){
        teams.sort((o1, o2) -> {
            if(o1.getDuration() < o2.getDuration())
                return 1;
            else if(o1.getDuration() > o2.getDuration())
                return -1;

            return 0;
        });
    }

    public static Team getMaxOverlapTeam(ArrayList<Team> teams){
        ArrayList<Team> overlapTeam = new ArrayList<>();

        for(int i = 0; i < teams.size() - 1; i++){
            int overlap = teams.get(i).getDuration();
            for(int j = i + 1; j < teams.size(); j++ ){
                if(teams.get(i).getEmployeeOneId() == teams.get(j).getEmployeeOneId() &&
                        teams.get(i).getEmployeeTwoId() == teams.get(j).getEmployeeTwoId() &&
                        teams.get(i).getProjectId() != teams.get(j).getProjectId()){

                    overlap += teams.get(j).getDuration();

                }
            }
            overlapTeam.add(new Team(teams.get(i).getEmployeeOneId(), teams.get(i).getEmployeeTwoId(), teams.get(i).getProjectId(), overlap));
        }

        return overlapTeam.get(0);
    }
}