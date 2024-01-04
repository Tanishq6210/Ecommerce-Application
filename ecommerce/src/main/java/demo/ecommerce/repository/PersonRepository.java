package demo.ecommerce.repository;

import demo.ecommerce.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("SELECT p FROM Person p WHERE p.user_name = :user_name")
    public Person findByUserName(String user_name) ;
}
