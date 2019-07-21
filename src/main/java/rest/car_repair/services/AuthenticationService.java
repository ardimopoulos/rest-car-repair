package rest.car_repair.services;

import rest.car_repair.exceptions.member.MemberNotFoundException;

public interface AuthenticationService {

    String getToken(String username, String password) throws MemberNotFoundException;
}
