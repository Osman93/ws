package com.hoaxify.ws.user;

import com.fasterxml.jackson.annotation.JsonView;
import com.hoaxify.ws.error.ApiError;
import com.hoaxify.ws.shared.GenericResponse;
import com.hoaxify.ws.shared.Views;
import com.hoaxify.ws.user.vm.UserVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping("api/1.0/users")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user){
        userService.save(user);
        return ResponseEntity.ok(new GenericResponse("user created"));
    }

    @CrossOrigin
    @GetMapping("api/1.0/users")
    Page<UserVM> getUsers(Pageable page){
        return userService.getUsers(page).map(UserVM::new);
    }

  /*  @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleValidationError(MethodArgumentNotValidException exception){
        ApiError error = new ApiError(400,"Validation errors","api/1.0/users");
        Map<String,String> validationErrors = new HashMap<>();

        for(FieldError fieldError : exception.getBindingResult().getFieldErrors()){
            String key = fieldError.getField();
            String message = fieldError.getField() + " " + fieldError.getDefaultMessage();
            validationErrors.put(key,message);
        }
        error.setValidationErrors(validationErrors);
        return error;
    }*/
}
