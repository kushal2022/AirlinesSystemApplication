package edu.miu.edu.cs544.airlinereservationsystem.services;

import edu.miu.edu.cs544.airlinereservationsystem.database.dao.AdminRepository;
import edu.miu.edu.cs544.airlinereservationsystem.database.dao.AgentRepository;
import edu.miu.edu.cs544.airlinereservationsystem.database.dao.PassengerRepository;
import edu.miu.edu.cs544.airlinereservationsystem.database.dto.*;
import edu.miu.edu.cs544.airlinereservationsystem.model.AddressRequest;
import edu.miu.edu.cs544.airlinereservationsystem.model.RegisterRequest;
import edu.miu.edu.cs544.airlinereservationsystem.model.Role;
import edu.miu.edu.cs544.airlinereservationsystem.model.AuthenticationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${security.oauth2.client.clientId}")
    private String clientId;

    @Value("${security.oauth2.client.clientSecret}")
    private String clientSecret;

    @Value("${spring-boot-server.name}")
    private String serverName;

    private static final String REGISTER_URL = "/register";

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    AgentRepository agentRepository;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    PassengerService passengerService;

    @Autowired
    AgentService agentService;

    @Autowired
    AdminService adminService;


    @Override
    public String createUser(RegisterRequest user) {
        String response = createUserOnAuthServer(user);
        if (response != null) {
            storeUserInformation(user);
        }
        return response;
    }

    private void storeUserInformation(RegisterRequest user) {
        AddressRequest addresses = user.getAddress();
        Address address = new Address();
        address.setStreet(addresses.getStreet());
        address.setCity(addresses.getCity());
        address.setState(addresses.getState());
        address.setZip(addresses.getZip());

        if (Role.PASSENGER.equals(user.getRole())) {
            Passenger passenger = new Passenger();

            passenger.setAddresses(address);
            passenger.setFirstName(user.getFirstName());
            passenger.setLastName(user.getLastName());
            passenger.setDob(user.getDob());
            passenger.setEmail(user.getEmail());
            passenger.setUsername(user.getUsername());
            passenger.setPassword(user.getPassword());
            passenger.setRole(user.getRole().toString());

            passengerService.addPassenger(passenger);
        }

        if (Role.AGENT.equals(user.getRole())) {
            Agent agent = new Agent();
            agent.setAddresses(address);
            agent.setFirstName(user.getFirstName());
            agent.setLastName(user.getLastName());
            agent.setDob(user.getDob());
            agent.setEmail(user.getEmail());
            agent.setUsername(user.getUsername());
            agent.setPassword(user.getPassword());
            agent.setRole(user.getRole().toString());
            agentService.addAgent(agent);
        }

        if (Role.ADMIN.equals(user.getRole())) {
            Admin admin = new Admin();
            admin.setAddresses(address);
            admin.setFirstName(user.getFirstName());
            admin.setLastName(user.getLastName());
            admin.setDob(user.getDob());
            admin.setEmail(user.getEmail());
            admin.setUsername(user.getUsername());
            admin.setPassword(user.getPassword());
            admin.setRole(user.getRole().toString());
            adminService.addAdmin(admin);
        }
    }

    private String createUserOnAuthServer(RegisterRequest user) {
        AuthenticationRequest requestBody = new AuthenticationRequest(user.getUsername(), user.getPassword(), user.getRole());
        String url = getBaseServiceUrl() + REGISTER_URL;
        HttpEntity<AuthenticationRequest> entity = new HttpEntity<>(requestBody);
        String response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();
        log.info(response);
        return response;
    }


    private String getBaseServiceUrl() {
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serverName);
        serviceInstances.forEach(System.out::println);
        return serviceInstances.get(0).getUri().toString();
    }

    private HttpEntity<Object> createHttpEntity(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setBasicAuth(clientId, clientSecret);
        return new HttpEntity<>(headers);
    }
}
