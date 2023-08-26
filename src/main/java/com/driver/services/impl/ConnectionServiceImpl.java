package com.driver.services.impl;

import com.driver.model.*;
import com.driver.repository.ConnectionRepository;
import com.driver.repository.ServiceProviderRepository;
import com.driver.repository.UserRepository;
import com.driver.services.ConnectionService;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConnectionServiceImpl implements ConnectionService {
    @Autowired
    UserRepository userRepository2;
    @Autowired
    ServiceProviderRepository serviceProviderRepository2;
    @Autowired
    ConnectionRepository connectionRepository2;

    @Override
    public User connect(int userId, String countryName) throws Exception{
        User user = userRepository2.findById(userId).get();
        if (user.getConnected()) throw new Exception("AlreadyConnected");
        else if (user.getCountry().getCountryName().equals(countryName)) return user;
        List<ServiceProvider> serviceProviderList = serviceProviderRepository2.findAll();
        for (ServiceProvider s :
                 serviceProviderList) {
            List<Country> countries = s.getCountryList();
            for (Country country : countries){
                if (country.getCountryName().equals(country)){
                    user.setConnected(true);
                    Connection connection = new Connection(userId,user,s);
                    connectionRepository2.save(connection);
                    country.setUser(user);
                     user.setMaskedIp("updatedCountryCode.serviceProviderId.userId");
                     s.getUserList().add(user);
                     user.getConnectionList().add(connection);
                     s.getConnectionList().add(connection);
                     return userRepository2.save(user);
                }
            }
        }
        throw new Exception("Unable to connect");

    }
    @Override
    public User disconnect(int userId) throws Exception {
        User user = userRepository2.findById(userId).get();
        if (!user.getConnected()) throw new Exception("Already disconnected");
        user.getServiceProviderList().remove(user.getServiceProviderList().size() -1);
        user.getConnectionList().remove(user.getConnectionList().size() -1);
        user.setMaskedIp(null);
        user.setConnected(false);
       return userRepository2.save(user);

    }
    @Override
    public User communicate(int senderId, int receiverId) throws Exception {
        User sender = userRepository2.findById(senderId).get();
        User receiver = userRepository2.findById(receiverId).get();
        if (!sender.getCountry().equals(receiver.getCountry())){
            List<ServiceProvider> serviceProviderList = serviceProviderRepository2.findByUserUserId(senderId);
            for (ServiceProvider service :
                    serviceProviderList) {
                List<Country> countryList = service.getCountryList();
                for (Country c :
                        countryList) {
                    if (c.getCountryName().equals(receiver.getCountry().getCountryName())) {
                        Connection connection = new Connection(sender,service);
                        sender.getConnectionList().add(connection);
                        service.getConnectionList().add(connection);
                        connectionRepository2.save(connection);
                        Connection receiverConnection = new Connection(receiver,service);
                        receiver.getConnectionList().add(receiverConnection);
                        connectionRepository2.save(connection);
                        userRepository2.save(sender);
                        userRepository2.save(receiver);
                    }
                    }
            }
        }
        return sender;
    }
}
