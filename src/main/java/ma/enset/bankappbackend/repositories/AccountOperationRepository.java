package ma.enset.bankappbackend.repositories;


import ma.enset.bankappbackend.entities.AccountOperation;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AccountOperationRepository extends JpaRepository<AccountOperation, Long> {}