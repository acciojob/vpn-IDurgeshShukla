package com.driver.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class ServiceProvider {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer Id;
    private  String name;
    @OneToMany(mappedBy = "serviceProvider", cascade = CascadeType.ALL)
    private List<Connection> connectionList = new ArrayList<>();
    @ManyToOne
    @JoinColumn
    private Admin admin;
   @ManyToMany(mappedBy = "serviceProviderList", cascade = CascadeType.ALL)
   private List<User> userList = new ArrayList<>();
   @OneToMany(mappedBy = "serviceProvider", cascade =  CascadeType.ALL)
   private List<Country> countryList = new ArrayList<>();

    public List<Country> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<Country> countryList) {
        this.countryList = countryList;
    }

    public List<Connection> getConnectionList() {
        return connectionList;
    }

    public void setConnectionList(List<Connection> connectionList) {
        this.connectionList = connectionList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public ServiceProvider(Integer id, String name, List<Connection> connectionList, Admin admin, List<User> userList) {
        Id = id;
        this.name = name;
        this.connectionList = connectionList;
        this.admin = admin;
        this.userList = userList;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public ServiceProvider() {
    }

    public ServiceProvider(Integer id, String name, Admin admin) {
        Id = id;
        this.name = name;
        this.admin = admin;
    }
}
