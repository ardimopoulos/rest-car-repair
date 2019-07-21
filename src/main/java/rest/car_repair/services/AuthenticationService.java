package rest.car_repair.services;

import rest.car_repair.exceptions.security.AuthenticationTokenException;

public interface AuthenticationService {

    String getToken(String username, String password) throws AuthenticationTokenException;
}
