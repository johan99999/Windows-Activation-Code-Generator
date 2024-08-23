package windowsactivationcodegenerator.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import windowsactivationcodegenerator.app.model.*;
import windowsactivationcodegenerator.app.repository.Windows10Repository;
import windowsactivationcodegenerator.app.repository.Windows11Repository;
import windowsactivationcodegenerator.app.service.Windows10Service;
import windowsactivationcodegenerator.app.service.Windows11Service;

@RestController
public class Windows11Controller {

    @Autowired
    private Windows11Repository windows11Repository;

    @Autowired
    private Windows11Service windows11Service;


    @PostMapping(path = "/api/windows_11/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<Windows11Response> add(@RequestBody AddWin11CodeRequest request){
        Windows11Response response = windows11Service.add(request);
        return ApiResponse.<Windows11Response>builder().data(response).build();
    }

    @PatchMapping(path = "/api/windows_11/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<Windows11Response> update(@PathVariable("id") Integer id, @RequestBody UpdateWin11CodeRequest request){
        Windows11Response response = windows11Service.update(id, request);
        return ApiResponse.<Windows11Response>builder().data(response).build();
    }

    @DeleteMapping(path = "/api/windows_11/{id}")
    public ApiResponse<String> delete (@PathVariable("id") Integer id){
         windows11Service.delete(id);
         return ApiResponse.<String>builder().data("OK").build();
    }
}
