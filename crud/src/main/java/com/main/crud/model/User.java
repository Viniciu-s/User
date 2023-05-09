package com.main.crud.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Indica que esta classe representa uma entidade no banco de dados
@Entity

// Define a tabela que será mapeada para essa entidade
@Table(name = "tab_user")
public class User {
     // Define a chave primária da tabela
     @Id

     // Define a estratégia de geração de valores para a chave primária
     @GeneratedValue(strategy =  GenerationType.IDENTITY)
     private Integer id;
 
     // Define as colunas da tabela mapeadas para os atributos da classe
     @Column(length = 50, nullable = false, name = "firstname")
     private String firstname;
     
     @Column(length = 50, nullable = false, name = "lastname")
     private String lastname;
     
     @Column(nullable = false, unique = true, length = 50)
     private String email;
     
     @Column(length = 20)
     private String password;
 
     private boolean enabled;
 
     // Métodos de acesso para os atributos
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
 
     // Sobrescreve o método toString() para exibir informações úteis do objeto
     @Override
     public String toString() {
         return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname 
         + ", email=" + email + ", password=" + password + "]";
     }
}
