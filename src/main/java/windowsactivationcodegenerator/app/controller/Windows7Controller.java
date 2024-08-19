package windowsactivationcodegenerator.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.transform.impl.AddDelegateTransformer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import windowsactivationcodegenerator.app.entity.Windows7;
import windowsactivationcodegenerator.app.model.AddWin7CodeRequest;
import windowsactivationcodegenerator.app.model.ApiResponse;
import windowsactivationcodegenerator.app.model.UpdateWin7CodeRequest;
import windowsactivationcodegenerator.app.model.Windows7Response;
import windowsactivationcodegenerator.app.repository.Windows7Repository;
import windowsactivationcodegenerator.app.service.Windows7Service;

@RestController
public class Windows7Controller {

    @Autowired
    private Windows7Repository windows7Repository;

    @Autowired
    private Windows7Service windows7Service;


    @PostMapping(path = "/api/windows_7/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<Windows7Response> add(@RequestBody AddWin7CodeRequest request){
        Windows7Response response = windows7Service.add(request);
        return ApiResponse.<Windows7Response>builder().data(response).build();
    }

    @PatchMapping(path = "/api/windows_7/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<Windows7Response> update(@PathVariable("id") Integer id, @RequestBody UpdateWin7CodeRequest request){


        Windows7Response response = windows7Service.update(id, request);
        return ApiResponse.<Windows7Response>builder().data(response).build();
    }
}
