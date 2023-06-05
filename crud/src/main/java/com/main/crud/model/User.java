package com.main.crud.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity

@Table(name = "tab_user")
public class User {
     @Id

     @GeneratedValue(strategy =  GenerationType.IDENTITY)
     private Integer id;
 
     @Column(length = 50, nullable = false, name = "firstname")
     private String firstname;
     
     @Column(length = 50, nullable = false, name = "lastname")
     private String lastname;
     
     @Column(nullable = false, unique = true, length = 50)
     private String email;
     
     @Column(length = 20)
     private String password;
 
     private boolean enabled;
 
     public Integer getId() {
         return id;
     }
     
     public void setId(Integer id) {
         this.id = id;
     }
     
     public String getFirstname() {
         return firstname;
     }
     
     public void setFirstname(String firstname) {
         this.firstname = firstname;
     }
     
     public String getLastname() {
         return lastname;
     }
     
     public void setLastname(String lastname) {
         this.lastname = lastname;
     }
     
     public String getEmail() {
         return email;
     }
     
     public void setEmail(String email) {
         this.email = email;
     }
     
     public String getPassword() {
         return password;
     }
     
     public void setPassword(String password) {
         this.password = password;
     }
     
     public boolean isEnabled() {
         return enabled;
     }
 
     public void setEnabled(boolean enabled) {
         this.enabled = enabled;
     }
 
     @Override
     public String toString() {
         return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname 
         + ", email=" + email + ", password=" + password + "]";
     }
}
