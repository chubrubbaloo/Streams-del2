package com.company;

public class Employee {
    private int id;
    private String name;
    private int salary;
    Occupation occupation;
    boolean isHiredConsultant;

    enum  Occupation{
        STOCKHOLM,
        GÖTEBORG,
        MALMÖ
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", occupation=" + occupation +
                ", isHiredConsultant=" + isHiredConsultant +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }

    public void setHiredConsultant(boolean hiredConsultant) {
        isHiredConsultant = hiredConsultant;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public Occupation getOccupation() {
        return occupation;
    }

    public boolean isHiredConsultant() {
        return isHiredConsultant;
    }

    public Employee(int id, String name, int salary, Occupation occupation, boolean isHiredConsultant) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.occupation = occupation;
        this.isHiredConsultant = isHiredConsultant;
    }
}


