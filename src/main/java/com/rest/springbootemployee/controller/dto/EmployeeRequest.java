package com.rest.springbootemployee.controller.dto;

public class EmployeeRequest {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    private String gender;
    private Integer salary;

    public EmployeeRequest(String name, Integer age, String gender, Integer salary){

        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
    }
}