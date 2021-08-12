package br.com.edsonajeje.cadatroAPI.model.entities;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "cpf")})
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid e-mail")
    @Column(unique = true, nullable = false)
    private String email;

    @CPF(message = "Invalid CPF")
    @Column(unique = true, nullable = false)
    private String cpf;

    @NotBlank(message = "Birthday is required")
    private String birthday;

    public Client(String name, String email, String cpf, String birthday) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.birthday = birthday;
    }

    public Client() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

}