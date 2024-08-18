package windowsactivationcodegenerator.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import windowsactivationcodegenerator.app.entity.Windows7;
import windowsactivationcodegenerator.app.model.AddWin7CodeRequest;
import windowsactivationcodegenerator.app.model.Windows7Response;
import windowsactivationcodegenerator.app.repository.Windows7Repository;

import java.time.LocalDateTime;

@Service
public class Windows7Service {

    @Autowired
    private Windows7Repository windows7Repository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public Windows7Response add() {
        return add(null);
    }

    @Transactional
    public Windows7Response add(AddWin7CodeRequest request) {
        validationService.validate(request);

        if (windows7Repository.existsByActivationCode(request.getActivationCode())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Activation Code Already Exists");
        }

        Windows7 windows7 = new Windows7();
        windows7.setVersion(request.getVersion());
        windows7.setActivationCode(request.getActivationCode());
        windows7.setCreatedAt(LocalDateTime.now());
        windows7Repository.save(windows7);

        return toResponse(windows7);
    }

    private Windows7Response toResponse(Windows7 windows7) {
        return Windows7Response.builder()
                .id(windows7.getId())
                .version(windows7.getVersion())
                .activationCode(windows7.getActivationCode())
                .createdAt(windows7.getCreatedAt())
                .build();
    }
}
