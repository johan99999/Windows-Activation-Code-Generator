package windowsactivationcodegenerator.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import windowsactivationcodegenerator.app.entity.Windows10;
import windowsactivationcodegenerator.app.entity.Windows8;
import windowsactivationcodegenerator.app.model.*;
import windowsactivationcodegenerator.app.repository.Windows10Repository;
import windowsactivationcodegenerator.app.repository.Windows8Repository;

import java.time.LocalDateTime;

@Service
public class Windows10Service {

    @Autowired
    private Windows10Repository windows10Repository;

    @Autowired
    private ValidationService validationService;

//    @Transactional
//    public Windows7Response add() {
//        return add(null);
//    }

    @Transactional
    public Windows10Response add(AddWin10CodeRequest request) {
        validationService.validate(request);

        if (windows10Repository.existsByActivationCode(request.getActivationCode())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Activation Code Already Exists");
        }

        Windows10 windows10 = new Windows10();
        windows10.setVersion(request.getVersion());
        windows10.setActivationCode(request.getActivationCode());
        windows10.setCreatedAt(LocalDateTime.now());
        windows10Repository.save(windows10);

        return toResponse(windows10);
    }

    @Transactional
    public Windows10Response update(Integer id, UpdateWin10CodeRequest request) {
        validationService.validate(request);

        Windows10 update = windows10Repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Activation Code with the ID doesn't exist"));

        update.setActivationCode(request.getActivationCode());
        update.setVersion(request.getVersion());
        update.setCreatedAt(LocalDateTime.now());
        windows10Repository.save(update);

        return toResponse(update);
    }

    @Transactional
    public void delete(Integer id) {
        Windows10 windows10 = windows10Repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Activation Code with the ID doesn't exist"));
        windows10Repository.delete(windows10);
    }

    private Windows10Response toResponse(Windows10 windows10) {
        return Windows10Response.builder()
                .id(windows10.getId())
                .version(windows10.getVersion())
                .activationCode(windows10.getActivationCode())
                .createdAt(windows10.getCreatedAt())
                .build();
    }
}
