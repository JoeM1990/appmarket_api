package com.monkilatech.appmarket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.monkilatech.appmarket.exception.ValueException;
import com.monkilatech.appmarket.model.User;
import com.monkilatech.appmarket.repository.UsersRepository;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public User save(User user) throws Exception {

        if (user == null)
            throw new ValueException("Impossible de créer un utilisateur avec comme valeur null");

        if (user.getUsername() == null)
            throw new ValueException("Veuillez renseigner le username");

        User userChecked = this.usersRepository.findByUsername(user.getUsername());
        if (userChecked != null)
            throw new ValueException(String.format("le username %s est déjà utilisé", user.getUsername()));

        if (user.getPassword() == null)
            throw new ValueException("Veuillez renseigner le mot de passe");

        if (user.getPassword().length() < 8)
            throw new ValueException("le mot de passe doit contenir 8 caractères");

        String pwdEncrypted = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(pwdEncrypted);

        return this.usersRepository.save(user);
    }

    @Override
    public User login(String username, String password) throws Exception {

        User user = this.usersRepository.findByUsername(username);
        if (user == null)
            throw new ValueException(String.format("le profil portant le username %s n'existe", username));

        boolean isValid = new BCryptPasswordEncoder().matches(password, user.getPassword());
        if (!isValid)
            throw new ValueException("le mot de passe est incorrect");

        user.setPassword(null);
        return user;
    }

    @Override
    public List<User> fetchAll() throws Exception {

        List<User> usersGet=this.usersRepository.findAll();

        if(usersGet == null)
            throw new ValueException("Erreur interne");

        return this.usersRepository.findAll();
    }

    @Override
    public User update(User user, Long id) throws Exception {

        User userCheck=this.usersRepository.findById(id).get();
        
        if (userCheck == null)
            throw new ValueException(String.format("Impossible de créer un utilisateur avec comme valeur null"));
        
        if (user == null)
            throw new ValueException(String.format("le profil portant l id %s n'existe", id));

        userCheck.setNom(user.getNom());
        userCheck.setPassword(user.getPassword());
        userCheck.setPhoneNumber(user.getPhoneNumber());
        userCheck.setEmail(user.getEmail());
        userCheck.setUsername(user.getUsername());
        userCheck.setPhoto(user.getPhoto());
        userCheck.setTypeCompte(user.getTypeCompte());
        

        return this.usersRepository.save(userCheck);
    }

    @Override
    public boolean delete(long id) throws Exception {

        User user = this.usersRepository.findById(id);
        if (user == null)
            throw new ValueException(String.format("le profil portant l'id %d n'existe pas", id));
        this.usersRepository.delete(user);

        User checkUser = this.usersRepository.findById(id);
        if (checkUser != null)
            return false;
        return true;

    }

}
