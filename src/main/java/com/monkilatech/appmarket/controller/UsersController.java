package com.monkilatech.appmarket.controller;

import com.monkilatech.appmarket.domain.BodyResponse;
import com.monkilatech.appmarket.domain.UserCredential;
import com.monkilatech.appmarket.exception.ValueException;
import com.monkilatech.appmarket.model.User;
//import com.monkilatech.appmarket.repository.UsersRepository;
import com.monkilatech.appmarket.service.UsersService;

import java.util.List;
//import javax.validation.Valid;

// Importing required classes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Annotation
@RestController
@RequestMapping("/users")
public class UsersController {

    // Annotation
    @Autowired
    private UsersService usersService;

    // Read operation
    @GetMapping
    public ResponseEntity fetchAll() {

        BodyResponse bodyResponse = new BodyResponse<>();

        try {
            List<User> users = this.usersService.fetchAll();

                if(users != null)
                    return ResponseEntity.status(HttpStatus.OK).body(users);
                    
        } catch (Exception e) {
            bodyResponse.setMessage("Erreur interne");
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(bodyResponse);
    }

    // Save operation
    @PostMapping
    public ResponseEntity save(@RequestBody User user) {

        BodyResponse bodyResponse = new BodyResponse<>();

        try {
           User userSaved=this.usersService.save(user);
           if(userSaved!=null){
            return ResponseEntity.status(HttpStatus.CREATED).body(userSaved);
           }
           bodyResponse.setMessage("Echec d'enregistrement");
        }
        catch (ValueException e) {
            bodyResponse.setMessage(e.getMessage());
        }
         catch (Exception e) {
            bodyResponse.setMessage("Erreur interne");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(bodyResponse);
    }

    // Update operation
    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody User user, @PathVariable("id") Long id) {
        BodyResponse bodyResponse = new BodyResponse<>();

        try {
           User userUpdate=this.usersService.update(user,id);
           if(userUpdate!=null){
            return ResponseEntity.status(HttpStatus.CREATED).body(userUpdate);
           }
           bodyResponse.setMessage("Echec de modification");
        }
        catch (ValueException e) {
            bodyResponse.setMessage(e.getMessage());
        }
         catch (Exception e) {
            bodyResponse.setMessage("Erreur interne");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(bodyResponse);
    }

    // Delete operation
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        BodyResponse bodyResponse = new BodyResponse<>();

        try {
            Boolean userDelete=this.usersService.delete(id);

           if(userDelete.booleanValue()==true){
            return ResponseEntity.status(HttpStatus.OK).body(userDelete);
           }
           bodyResponse.setMessage("Echec de suppression");
        }
        catch (ValueException e) {
            bodyResponse.setMessage(e.getMessage());
        }
         catch (Exception e) {
            bodyResponse.setMessage("Erreur interne");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(bodyResponse);
        
    }

}
