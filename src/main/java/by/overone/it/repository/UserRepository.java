package by.overone.it.repository;

import by.overone.it.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, String> {

    @Query("from User where username =:username")
    User getUserByUsername(@Param("username") String username);

    @Query("select name, telephoneNumber from User where carNumber =:carNumber")
    User getUserByCarNumber(@Param("carNumber") String carNumber);

    @Query("select username from User where username =:username")
    String getUsername(@Param("username") String username);

}
