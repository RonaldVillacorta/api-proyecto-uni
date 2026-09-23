package com.ronald.proyecto.proyecto_uni.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ronald.proyecto.proyecto_uni.models.UserRequest;
import com.ronald.proyecto.proyecto_uni.service.UserService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private com.ronald.proyecto.proyecto_uni.service.ApiPeruService apiPeruService;

    @GetMapping("/consulta-dni/{dni}")
    public ResponseEntity<?> consultarDni(@PathVariable String dni) {
        return ResponseEntity.ok(apiPeruService.consultarDni(dni));
    }

    @GetMapping("/consulta-ruc/{ruc}")
    public ResponseEntity<?> consultarRuc(@PathVariable String ruc) {
        return ResponseEntity.ok(apiPeruService.consultarRuc(ruc));
    }

    @GetMapping()
    public ResponseEntity<Object> findAll() {
            return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id) {
            return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<Object> save(@Valid @RequestBody UserRequest userRequest) {
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> actualizarUsuario(@Valid @RequestBody UserRequest userRequest, @PathVariable Integer id) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.actualizarPagina(userRequest, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {userService.deleteById(id); 
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getCurrentUserProfile(Authentication authentication) {
        return ResponseEntity.ok(userService.getUserProfile(authentication));
    }

    @Autowired
    private com.ronald.proyecto.proyecto_uni.service.SbsDocumentAiService sbsDocumentAiService;

    @Autowired
    private com.ronald.proyecto.proyecto_uni.repository.UserRepository userRepository;

    @PostMapping("/profile/reporte-sbs")
    public ResponseEntity<?> subirReporteSbs(
            @org.springframework.web.bind.annotation.RequestParam("file") org.springframework.web.multipart.MultipartFile file,
            Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(java.util.Map.of("error", "No autenticado"));
        }
        String email = authentication.getName();
        java.util.Optional<com.ronald.proyecto.proyecto_uni.entity.User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(java.util.Map.of("error", "Usuario no encontrado"));
        }

        try {
            com.ronald.proyecto.proyecto_uni.dto.SbsAnalysisResultDTO resultado = 
                    sbsDocumentAiService.procesarReporteSbs(file, userOpt.get());
            return ResponseEntity.ok(resultado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(java.util.Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(java.util.Map.of("error", "Error al procesar el reporte SBS con IA: " + e.getMessage()));
        }
    }
}

