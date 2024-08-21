package windowsactivationcodegenerator.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import windowsactivationcodegenerator.app.entity.Windows8;
import windowsactivationcodegenerator.app.model.*;
import windowsactivationcodegenerator.app.repository.Windows7Repository;
import windowsactivationcodegenerator.app.repository.Windows8Repository;
import windowsactivationcodegenerator.app.service.Windows7Service;
import windowsactivationcodegenerator.app.service.Windows8Service;

@RestController
public class Windows8Controller {

    @Autowired
    private Windows8Repository windows8Repository;

    @Autowired
    private Windows8Service windows8Service;


    @PostMapping(path = "/api/windows_8/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<Windows8Response> add(@RequestBody AddWin8CodeRequest request){
        Windows8Response response = windows8Service.add(request);
        return ApiResponse.<Windows8Response>builder().data(response).build();
    }

    @PatchMapping(path = "/api/windows_8/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<Windows8Response> update(@PathVariable("id") Integer id, @RequestBody UpdateWin8CodeRequest request){
        Windows8Response response = windows8Service.update(id, request);
        return ApiResponse.<Windows8Response>builder().data(response).build();
    }

    @DeleteMapping(path = "/api/windows_8/{id}")
    public ApiResponse<String> delete (@PathVariable("id") Integer id){
         windows8Service.delete(id);
         return ApiResponse.<String>builder().data("OK").build();
    }
}
