package br.gov.sp.fatec.user.service;

import br.gov.sp.fatec.user.domain.User;

public interface UserService {

    User findByEmail(String email);

    User search(Long id);

    User findById(Long id);

    User getLoggedInfo();

    User save(User user);

    User getUserLoggedIn();

    User activate(String b64);
}
