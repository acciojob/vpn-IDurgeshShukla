package com.driver.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer Id;
    private String userName;
    private String password;

    private String originalIp;
    private String maskedIp;

    public List<ServiceProvider> getServiceProviderList() {
        return serviceProviderList;
    }

    public void setServiceProviderList(List<ServiceProvider> serviceProviderList) {
        this.serviceProviderList = serviceProviderList;
    }

    public List<Connection> getConnectionList() {
        return connectionList;
    }

    public void setConnectionList(List<Connection> connectionList) {
        this.connectionList = connectionList;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    private Boolean connected;

    @ManyToMany
    @JoinColumn
    private List<ServiceProvider> serviceProviderList = new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
   private List<Connection> connectionList = new ArrayList<>();
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Country country;

    public User(String userName, String password, Country country) {
        this.userName = userName;
        this.password = password;
        this.country = country;
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

    public String getOriginalIp() {
        return originalIp;
    }

    public void setOriginalIp(String originalIp) {
        this.originalIp = originalIp;
    }

    public String getMaskedIp() {
        return maskedIp;
    }

    public void setMaskedIp(String maskedIp) {
        this.maskedIp = maskedIp;
    }

    public Boolean getConnected() {
        return connected;
    }

    public void setConnected(Boolean connected) {
        this.connected = connected;
    }

    public User() {
    }

    public User(Integer id, String userName, String password, String originalIp, String maskedIp, Boolean connected, List<ServiceProvider> serviceProviderList, List<Connection> connectionList, Country country) {
        Id = id;
        this.userName = userName;
        this.password = password;
        this.originalIp = originalIp;
        this.maskedIp = maskedIp;
        this.connected = connected;
        this.serviceProviderList = serviceProviderList;
        this.connectionList = connectionList;
        this.country = country;
    }
}
