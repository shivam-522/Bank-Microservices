package com.shiva.accounts.repository;

import com.shiva.accounts.entity.Accounts;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface AccountsRepository extends JpaRepository<Accounts,Long> {

    Optional<Accounts> findByCustomerId(Long customerId);

    @Transactional /** Modifing annotation tell spring that this method is going to change the data in DB and we need to mentioned
     Transactional annotation because Modifing annotation executed under Transactional Annotation whenver partial modification happned it roleback the changes whenever any cproblem occur in between**/
    @Modifying /** Whenever we are trying to delete or modifying the dataBase by custom method. We need to mention two annotations on the abstract method **/
    void deleteByCustomerId(Long customerId);/** With this my spring data jpa will generate the delete query behind the scene**/

}
