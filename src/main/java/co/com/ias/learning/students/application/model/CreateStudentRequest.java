package co.com.ias.learning.students.application.model;

import co.com.ias.learning.students.application.commons.operation.ApplicationRequest;

import java.util.Objects;

public class CreateStudentRequest implements ApplicationRequest {
    private String name;
    private String lastName;
    private String idNumber;
    private String idType;

    public CreateStudentRequest() {
    }

    public CreateStudentRequest(String name, String lastName, String idNumber, String idType) {
        this.name = name;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.idType = idType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateStudentRequest that = (CreateStudentRequest) o;
        return Objects.equals(name, that.name) && Objects.equals(lastName, that.lastName) && Objects.equals(idNumber, that.idNumber) && Objects.equals(idType, that.idType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, idNumber, idType);
    }

    @Override
    public String toString() {
        return "CreateStudentRequest{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", idType='" + idType + '\'' +
                '}';
    }
}
