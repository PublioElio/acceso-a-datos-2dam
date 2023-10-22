package com.acdat.unit2.sax;

public class User {

    int id;
    private String firstName;
    private String lastName;
    private String occupation;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append("User{").append("id=").append(id)
                .append(", firstName=").append(firstName)
                .append(", lastName=").append(lastName)
                .append(", occupation=").append(occupation).append("}");

        return builder.toString();
    }
}
