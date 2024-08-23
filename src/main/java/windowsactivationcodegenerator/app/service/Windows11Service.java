package windowsactivationcodegenerator.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import windowsactivationcodegenerator.app.entity.Windows10;
import windowsactivationcodegenerator.app.entity.Windows11;
import windowsactivationcodegenerator.app.model.*;
import windowsactivationcodegenerator.app.repository.Windows10Repository;
import windowsactivationcodegenerator.app.repository.Windows11Repository;

import java.time.LocalDateTime;

@Service
public class Windows11Service {

    @Autowired
    private Windows11Repository windows11Repository;

    @Autowired
    private ValidationService validationService;

//    @Transactional
//    public Windows7Response add() {
//        return add(null);
//    }

    @Transactional
    public Windows11Response add(AddWin11CodeRequest request) {
        validationService.validate(request);

        if (windows11Repository.existsByActivationCode(request.getActivationCode())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Activation Code Already Exists");
        }

        Windows11 windows11 = new Windows11();
        windows11.setVersion(request.getVersion());
        windows11.setActivationCode(request.getActivationCode());
        windows11.setCreatedAt(LocalDateTime.now());
        windows11Repository.save(windows11);

        return toResponse(windows11);
    }

    @Transactional
    public Windows11Response update(Integer id, UpdateWin11CodeRequest request) {
        validationService.validate(request);

        Windows11 update = windows11Repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Activation Code with the ID doesn't exist"));

        update.setActivationCode(request.getActivationCode());
        update.setVersion(request.getVersion());
        update.setCreatedAt(LocalDateTime.now());
        windows11Repository.save(update);

        return toResponse(update);
    }

    @Transactional
    public void delete(Integer id) {
        Windows11 windows11 = windows11Repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Activation Code with the ID doesn't exist"));
        windows11Repository.delete(windows11);
    }

    private Windows11Response toResponse(Windows11 windows11) {
        return Windows11Response.builder()
                .id(windows11.getId())
                .version(windows11.getVersion())
                .activationCode(windows11.getActivationCode())
                .createdAt(windows11.getCreatedAt())
                .build();
    }
}
