package windowsactivationcodegenerator.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import windowsactivationcodegenerator.app.entity.Windows7;
import windowsactivationcodegenerator.app.entity.Windows8;
import windowsactivationcodegenerator.app.model.*;
import windowsactivationcodegenerator.app.repository.Windows7Repository;
import windowsactivationcodegenerator.app.repository.Windows8Repository;

import java.time.LocalDateTime;

@Service
public class Windows8Service {

    @Autowired
    private Windows8Repository windows8Repository;

    @Autowired
    private ValidationService validationService;

//    @Transactional
//    public Windows7Response add() {
//        return add(null);
//    }

    @Transactional
    public Windows8Response add(AddWin8CodeRequest request) {
        validationService.validate(request);

        if (windows8Repository.existsByActivationCode(request.getActivationCode())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Activation Code Already Exists");
        }

        Windows8 windows8 = new Windows8();
        windows8.setVersion(request.getVersion());
        windows8.setActivationCode(request.getActivationCode());
        windows8.setCreatedAt(LocalDateTime.now());
        windows8Repository.save(windows8);

        return toResponse(windows8);
    }

    @Transactional
    public Windows8Response update(Integer id, UpdateWin8CodeRequest request) {
        validationService.validate(request);

        Windows8 update = windows8Repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Activation Code with the ID doesn't exist"));

        update.setActivationCode(request.getActivationCode());
        update.setVersion(request.getVersion());
        update.setCreatedAt(LocalDateTime.now());
        windows8Repository.save(update);

        return toResponse(update);
    }

    @Transactional
    public void delete(Integer id) {
        Windows8 windows8 = windows8Repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Activation Code with the ID doesn't exist"));
        windows8Repository.delete(windows8);
    }

    private Windows8Response toResponse(Windows8 windows8) {
        return Windows8Response.builder()
                .id(windows8.getId())
                .version(windows8.getVersion())
                .activationCode(windows8.getActivationCode())
                .createdAt(windows8.getCreatedAt())
                .build();
    }
}
