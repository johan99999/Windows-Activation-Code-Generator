package windowsactivationcodegenerator.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import windowsactivationcodegenerator.app.entity.Windows7;

import java.util.Optional;

public interface Windows7Repository extends JpaRepository<Windows7, Integer> {

    Optional<Windows7> findByActivationCode(String activationCode);
    Boolean  existsByActivationCode(String activationCode);
}
