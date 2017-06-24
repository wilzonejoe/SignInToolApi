/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestClient.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author wilzone
 */
@XmlRootElement
public class User implements Serializable{
    private String id;
    @NotNull( message = "User's first name must not be null")
    private String firstName;
    @NotNull( message = "User's last name must not be null")
    private String lastName;
    private Date signInTime;
    private Date signOutTime;
    private String phoneNumber;

    public User(){}

    public User(String firstName, String lastName, Date signInTime, Date signOutTime, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.signInTime = signInTime;
        this.signOutTime = signOutTime;
        this.phoneNumber = phoneNumber;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Date getSignInTime() {
        return signInTime;
    }

    public void setSignInTime(Date signInTime) {
        this.signInTime = signInTime;
    }

    public Date getSignOutTime() {
        return signOutTime;
    }

    public void setSignOutTime(Date signOutTime) {
        this.signOutTime = signOutTime;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.firstName);
        hash = 17 * hash + Objects.hashCode(this.lastName);
        hash = 17 * hash + Objects.hashCode(this.signInTime);
        hash = 17 * hash + Objects.hashCode(this.signOutTime);
        hash = 17 * hash + Objects.hashCode(this.phoneNumber);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.signOutTime, other.signOutTime)) {
            return false;
        }
        if (!Objects.equals(this.phoneNumber, other.phoneNumber)) {
            return false;
        }
        if (!Objects.equals(this.signInTime, other.signInTime)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", signInTime=" + signInTime + ", signOutTime=" + signOutTime + ", phoneNumber=" + phoneNumber + '}';
    }
}
