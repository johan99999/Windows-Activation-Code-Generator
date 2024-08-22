package windowsactivationcodegenerator.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import windowsactivationcodegenerator.app.entity.Windows10;
import windowsactivationcodegenerator.app.entity.Windows8;

import java.util.Optional;

public interface Windows10Repository extends JpaRepository<Windows10, Integer> {

    Optional<Windows10> findByActivationCode(String activationCode);
    Boolean  existsByActivationCode(String activationCode);
}
