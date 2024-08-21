package windowsactivationcodegenerator.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import windowsactivationcodegenerator.app.entity.Windows7;
import windowsactivationcodegenerator.app.entity.Windows8;

import java.util.Optional;

public interface Windows8Repository extends JpaRepository<Windows8, Integer> {

    Optional<Windows8> findByActivationCode(String activationCode);
    Boolean  existsByActivationCode(String activationCode);
}
