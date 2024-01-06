package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Employee {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "FNAME")
    private String firstName;
    @Column(name = "LNAME")
    private String lastName;

    public Employee() {
    }

    public String getfFirstName() {
        return firstName;
    }

    public void setfFirstName(String fName) {
        this.firstName = fName;
    }

    public String getlLastName() {
        return lastName;
    }

    public void setlLastName(String lName) {
        this.lastName = lName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
