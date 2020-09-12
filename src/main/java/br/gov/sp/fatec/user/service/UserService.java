package br.gov.sp.fatec.user.service;

import br.gov.sp.fatec.user.domain.User;

import java.util.List;

public interface UserService {

    User addUser (String login, String password, String authorizationName);

    List<User> search(String login);

    User findByEmail(String email);

    User search(Long id);

    List<User> all();

    User findById(Long id);

    User getLoggedInfo();

    User save(User user);

    User getUserLoggedIn();
}
