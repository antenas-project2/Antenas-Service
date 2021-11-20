package br.gov.sp.fatec.user.repository;

import br.gov.sp.fatec.user.domain.User;
import br.gov.sp.fatec.user.dto.PendingUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByNameContainsIgnoreCase(String name);

    List<User> findAllByActive(Boolean active);

    @Query("SELECT new br.gov.sp.fatec.user.dto.PendingUser(u.id, u.name, u.email, u.active, u.archived) FROM User u " +
           "WHERE u.active = 0")
    List<PendingUser> findAllPendingAndArchivedUsers();

    @Query(
            "SELECT new br.gov.sp.fatec.user.dto.PendingUser(u.id, u.name, u.email, u.active, u.archived) FROM User u " +
            "INNER JOIN u.authorizations a " +
            "WHERE a.id IN (10, 9) " +
            "AND u.active = 0"
    )
    List<PendingUser> findAllPendingAndArchivedStudentsAndTeachers();

    User findByEmailAndPassword(String email, String password);

    User findByEmail(String email);
}
