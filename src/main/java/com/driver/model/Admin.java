package com.driver.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table
public class Admin {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer Id;
    private String userName;
    private String  password;

    public Admin(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    List<ServiceProvider> serviceProviderList = new ArrayList<>();

    public Admin() {
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ServiceProvider> getServiceProviderList() {
        return serviceProviderList;
    }

    public void setServiceProviderList(List<ServiceProvider> serviceProviderList) {
        this.serviceProviderList = serviceProviderList;
    }

    public Admin(Integer id, String userName, String password, List<ServiceProvider> serviceProviderList) {
        Id = id;
        this.userName = userName;
        this.password = password;
        this.serviceProviderList = serviceProviderList;
    }
}
