package com.ronald.proyecto.proyecto_uni.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ronald.proyecto.proyecto_uni.models.UserIsAdmin;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


@Entity
@Table(name = "user")
public class User implements UserIsAdmin { // El userIsAdmin para los roles para que no haya problema en el save y
                                           // update

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @NotBlank
    private String name;

    @Column(name = "lastname")
    @NotBlank
    private String lastname;

    @Column(name = "dni")
    @NotBlank()
    @Pattern(regexp = "^[0-9]{8}$", message = "El DNI debe tener 8 dígitos numéricos")
    private String dni;

    @Column(name = "phone")
    @Pattern(regexp = "^$|^[0-9]{9}$", message = "El teléfono debe tener 9 dígitos numéricos")
    private String phone;

    /* PASO 3 TWILIO */
    @Column(name = "sms_code")
    private String smsCode;

    @Column(name = "sms_code_expiry")
    private LocalDateTime smsCodeExpiry;

    @Column(name = "sms_verified")
    private boolean smsVerified = false;

    @Column(name = "temp_token")
    private String tempToken;

    @Column(name = "address")
    private String address;

    @Column(name = "email", unique = true/* , nullable = false */)
    /* @NotBlank
    @Email */
    private String email;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean admin;

    @Column(name = "password"/* , nullable = false */)
    /* @NotBlank */
    private String password;

    @Column(name = "estado")
    private boolean estado;

    @ManyToMany
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"), uniqueConstraints = @UniqueConstraint(columnNames = {
            "user_id", "role_id" }))
    List<Role> roles;

    @Column(name = "limite_credito")
    private Double limiteCredito = 500.0;

    @Column(name = "sbs_calificacion")
    private String sbsCalificacion;

    @Column(name = "sbs_deuda_total")
    private Double sbsDeudaTotal;

    @Column(name = "sbs_entidades", length = 1000)
    private String sbsEntidades;

    @Column(name = "sbs_score")
    private Integer sbsScore;

    @Column(name = "sbs_semaforo")
    private String sbsSemaforo;

    @Column(name = "sbs_fecha_evaluacion")
    private LocalDateTime sbsFechaEvaluacion;

    @Column(name = "sbs_documento_url", length = 500)
    private String sbsDocumentoUrl;

    @Column(name = "ingreso_mensual")
    private Double ingresoMensual;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public LocalDateTime getSmsCodeExpiry() {
        return smsCodeExpiry;
    }

    public void setSmsCodeExpiry(LocalDateTime smsCodeExpiry) {
        this.smsCodeExpiry = smsCodeExpiry;
    }

    public boolean isSmsVerified() {
        return smsVerified;
    }

    public void setSmsVerified(boolean smsVerified) {
        this.smsVerified = smsVerified;
    }

    public String getTempToken() {
        return tempToken;
    }

    public void setTempToken(String tempToken) {
        this.tempToken = tempToken;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Double getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(Double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public String getSbsCalificacion() {
        return sbsCalificacion;
    }

    public void setSbsCalificacion(String sbsCalificacion) {
        this.sbsCalificacion = sbsCalificacion;
    }

    public Double getSbsDeudaTotal() {
        return sbsDeudaTotal;
    }

    public void setSbsDeudaTotal(Double sbsDeudaTotal) {
        this.sbsDeudaTotal = sbsDeudaTotal;
    }

    public String getSbsEntidades() {
        return sbsEntidades;
    }

    public void setSbsEntidades(String sbsEntidades) {
        this.sbsEntidades = sbsEntidades;
    }

    public Integer getSbsScore() {
        return sbsScore;
    }

    public void setSbsScore(Integer sbsScore) {
        this.sbsScore = sbsScore;
    }

    public String getSbsSemaforo() {
        return sbsSemaforo;
    }

    public void setSbsSemaforo(String sbsSemaforo) {
        this.sbsSemaforo = sbsSemaforo;
    }

    public LocalDateTime getSbsFechaEvaluacion() {
        return sbsFechaEvaluacion;
    }

    public void setSbsFechaEvaluacion(LocalDateTime sbsFechaEvaluacion) {
        this.sbsFechaEvaluacion = sbsFechaEvaluacion;
    }

    public String getSbsDocumentoUrl() {
        return sbsDocumentoUrl;
    }

    public void setSbsDocumentoUrl(String sbsDocumentoUrl) {
        this.sbsDocumentoUrl = sbsDocumentoUrl;
    }

    public Double getIngresoMensual() {
        return ingresoMensual;
    }

    public void setIngresoMensual(Double ingresoMensual) {
        this.ingresoMensual = ingresoMensual;
    }

}
