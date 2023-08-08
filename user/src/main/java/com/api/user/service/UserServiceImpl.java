package com.api.user.service;

import com.api.user.dto.DadosCadastroCompleto;
import com.api.user.dto.DadosCadastroUser;
import com.api.user.exception.GlobalExceptionHandler;
import com.api.user.repository.UserRepository;
import com.api.user.model._User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository repository;
    private PasswordEncoder encoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder encoder){
        this.repository = userRepository;
        this.encoder = encoder;
    }

    public DadosCadastroUser saveUser(DadosCadastroUser user, DadosCadastroCompleto userComp) {
        if (user.getEmail().isBlank() || user.getName().isBlank() || user.getPassword().isBlank()) {
            throw new GlobalExceptionHandler.BadRequestException("user invalid");
        }
        if (repository.existsByEmail(user.getEmail())){
            throw new GlobalExceptionHandler.BadRequestException("user already exist");
        }
        if (!isValidEmail(user.getEmail())){
            throw new GlobalExceptionHandler.BadRequestException("email invalid");
        }
        if (!isValidPassword(user.getPassword())){
            throw new GlobalExceptionHandler.BadRequestException("password invalid");
        }

        _User use = new _User(null, user.getName(), user.getEmail(), user.getPassword());
        use.setPassword(encoder.encode(use.getPassword()));
        user.setPassword(use.getPassword());

        repository.save(use);

        userComp.setId(repository.findByEmail(use.getEmail()).get().getId());
        userComp.setName(repository.findByEmail(use.getEmail()).get().getName());
        userComp.setEmail(repository.findByEmail(use.getEmail()).get().getEmail());
        userComp.setPassword(repository.findByEmail(use.getEmail()).get().getPassword());

        return user;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(emailRegex);
    }

    private boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z]).{8,}$";
        return password.matches(passwordRegex);
    }

    @Override
    public DadosCadastroUser findById(UUID id) {
        DadosCadastroUser user = new DadosCadastroUser(repository.findById(id)
                .orElseThrow(() -> new GlobalExceptionHandler.NotFoundException("Invalid user ID: " + id)));

        return user;
    }

}
