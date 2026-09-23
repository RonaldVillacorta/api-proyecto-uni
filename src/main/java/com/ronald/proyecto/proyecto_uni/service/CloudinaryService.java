package com.ronald.proyecto.proyecto_uni.service;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class CloudinaryService {

    private static final Logger log = LoggerFactory.getLogger(CloudinaryService.class);

    private final Cloudinary cloudinary;

    public CloudinaryService(
            @Value("${cloudinary.cloud-name:}") String cloudName,
            @Value("${cloudinary.api-key:}") String apiKey,
            @Value("${cloudinary.api-secret:}") String apiSecret) {
        
        if (cloudName != null && !cloudName.isBlank() && apiKey != null && !apiKey.isBlank()) {
            this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                    "cloud_name", cloudName,
                    "api_key", apiKey,
                    "api_secret", apiSecret,
                    "secure", true
            ));
        } else {
            this.cloudinary = null;
            log.warn("Cloudinary no está configurado (variables vacías). Se usará fallback simulado.");
        }
    }

    public Map<String, Object> subirComprobante(MultipartFile file) throws IOException {
        if (cloudinary == null) {
            log.info("Cloudinary inactivo: simulando guardado de comprobante {}", file.getOriginalFilename());
            return Map.of(
                    "secure_url", "https://via.placeholder.com/600x800.png?text=Comprobante+Yape+Simulado",
                    "public_id", "simulated_" + System.currentTimeMillis()
            );
        }

        Map<String, Object> params = ObjectUtils.asMap(
                "folder", "proyecto_uni/comprobantes_yape",
                "resource_type", "image"
        );

        @SuppressWarnings("unchecked")
        Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), params);
        return uploadResult;
    }

    public Map<String, Object> subirDocumento(MultipartFile file, String subcarpeta) throws IOException {
        if (cloudinary == null) {
            log.info("Cloudinary inactivo: simulando guardado de documento {}", file.getOriginalFilename());
            return Map.of(
                    "secure_url", "https://via.placeholder.com/600x800.png?text=Documento+SBS+Subido",
                    "public_id", "simulated_" + System.currentTimeMillis()
            );
        }

        Map<String, Object> params = ObjectUtils.asMap(
                "folder", "proyecto_uni/" + subcarpeta,
                "resource_type", "auto"
        );

        @SuppressWarnings("unchecked")
        Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), params);
        return uploadResult;
    }
}
