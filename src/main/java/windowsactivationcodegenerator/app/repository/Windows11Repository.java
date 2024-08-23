package windowsactivationcodegenerator.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import windowsactivationcodegenerator.app.entity.Windows10;
import windowsactivationcodegenerator.app.entity.Windows11;

import java.util.Optional;

public interface Windows11Repository extends JpaRepository<Windows11, Integer> {

    Optional<Windows11> findByActivationCode(String activationCode);
    Boolean  existsByActivationCode(String activationCode);
}
