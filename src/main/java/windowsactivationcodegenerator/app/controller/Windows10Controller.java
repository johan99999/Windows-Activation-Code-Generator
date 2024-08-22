package windowsactivationcodegenerator.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import windowsactivationcodegenerator.app.model.*;
import windowsactivationcodegenerator.app.repository.Windows10Repository;
import windowsactivationcodegenerator.app.repository.Windows8Repository;
import windowsactivationcodegenerator.app.service.Windows10Service;
import windowsactivationcodegenerator.app.service.Windows8Service;

@RestController
public class Windows10Controller {

    @Autowired
    private Windows10Repository windows10Repository;

    @Autowired
    private Windows10Service windows10Service;


    @PostMapping(path = "/api/windows_10/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<Windows10Response> add(@RequestBody AddWin10CodeRequest request){
        Windows10Response response = windows10Service.add(request);
        return ApiResponse.<Windows10Response>builder().data(response).build();
    }

    @PatchMapping(path = "/api/windows_10/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<Windows10Response> update(@PathVariable("id") Integer id, @RequestBody UpdateWin10CodeRequest request){
        Windows10Response response = windows10Service.update(id, request);
        return ApiResponse.<Windows10Response>builder().data(response).build();
    }

    @DeleteMapping(path = "/api/windows_10/{id}")
    public ApiResponse<String> delete (@PathVariable("id") Integer id){
         windows10Service.delete(id);
         return ApiResponse.<String>builder().data("OK").build();
    }
}
