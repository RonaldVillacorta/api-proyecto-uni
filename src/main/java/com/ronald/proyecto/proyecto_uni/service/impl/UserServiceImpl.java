package com.ronald.proyecto.proyecto_uni.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ronald.proyecto.proyecto_uni.entity.Role;
import com.ronald.proyecto.proyecto_uni.entity.User;
import com.ronald.proyecto.proyecto_uni.models.UserIsAdmin;
import com.ronald.proyecto.proyecto_uni.models.UserRequest;
import com.ronald.proyecto.proyecto_uni.repository.RoleRepository;
import com.ronald.proyecto.proyecto_uni.repository.UserRepository;
import com.ronald.proyecto.proyecto_uni.service.UserService;

import jakarta.persistence.EntityNotFoundException;


@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
            RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {

        List<User> users = userRepository.findAll();
        return users;
    }

/*     @Transactional(readOnly = true)
    @Override
    public Page<User> findAll(Pageable pageable) {
        return this.userRepository.findAll(pageable);
    } */

    @Transactional(readOnly = true)
    @Override
    public User findById(Integer id) {

        if (id == null) {
            throw new IllegalArgumentException("El id no puede ser nulo");
        }

        User userExist = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No existe el usuario con el id: " + id));
        return userExist;
    }

    @Transactional
    @Override
    public User save(UserRequest userRequest) {

        String emailName = userRequest.getName().toLowerCase().replaceAll("\\s+", "");
        String emailLastname = userRequest.getLastname().toLowerCase().replaceAll("\\s+", "");
        String generatedEmail = String.format("%s.%s@empresa.com", emailName, emailLastname);
        
        if (userRepository.findByEmail(generatedEmail).isPresent()) {
            Random random = new Random();
            int randomNum = random.nextInt(1000);
            generatedEmail = String.format("%s.%s%d@empresa.com", emailName, emailLastname, randomNum);
        }
        
        String generatedPassword = userRequest.getDni();
        
        User userSave = new User();
        userSave.setName(userRequest.getName());
        userSave.setLastname(userRequest.getLastname());
        userSave.setDni(userRequest.getDni());
        userSave.setPhone(userRequest.getPhone());
        userSave.setAddress(userRequest.getAddress());
        userSave.setEmail(generatedEmail);
        if (userRequest.getIngresoMensual() != null) {
            userSave.setIngresoMensual(userRequest.getIngresoMensual());
        }

        List<Role> roles = getRoles(userRequest);
        userSave.setRoles(roles);
        userSave.setPassword(passwordEncoder.encode(generatedPassword));
        return userRepository.save(userSave);
    }

    @Transactional
    @Override
    public User actualizarPagina(UserRequest userRequest, Integer id) {

        if (id == null) {
            throw new IllegalArgumentException("El id no puede ser null");
        }

        Optional<User> userExist = userRepository.findById(id);

        if (!userExist.isPresent()) {
            throw new EntityNotFoundException("El usuario no existe");
        }

        User userActualizado = userExist.get();

        userActualizado.setName(userRequest.getName());
        userActualizado.setLastname(userRequest.getLastname());
        userActualizado.setDni(userRequest.getDni());
        userActualizado.setPhone(userRequest.getPhone());
        userActualizado.setAddress(userRequest.getAddress());
        if (userRequest.getIngresoMensual() != null) {
            userActualizado.setIngresoMensual(userRequest.getIngresoMensual());
        }

        List<Role> roles = getRoles(userRequest);

        userActualizado.setRoles(roles);
        return userRepository.save(userActualizado);
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {

        if (id == null) {
            throw new IllegalArgumentException("El id no puede ser nulo");
        }

        Optional<User> userExist = userRepository.findById(id);

        if (userExist.isEmpty()) {
            throw new EntityNotFoundException("No existe un usuario con id: " + id);
        }

        User userToggle = userExist.get();
        userToggle.setEstado(!userToggle.isEstado());
        userRepository.save(userToggle);
    }


    private List<Role> getRoles(UserIsAdmin user) {
        List<Role> roles = new ArrayList<>();
        Optional<Role> roleOptionalUser = roleRepository.findByName("ROLE_USER");
        roleOptionalUser.ifPresent(roles::add);

        if (user.isAdmin()) {
            Optional<Role> roleOptionalAdmin = roleRepository.findByName("ROLE_ADMIN");
            roleOptionalAdmin.ifPresent(roles::add);
        }
        return roles;
    }


    public User getUserProfile(Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        return user;
    }

}
