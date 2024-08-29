package dio.java_desenvolvimento_com_ia.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import dio.java_desenvolvimento_com_ia.model.User;

public interface UserRepo extends JpaRepository<User, Long>{

    boolean existsByAccountNumber(String accountNumber);

    boolean existsByCardNumber(String cardNumber);
}
