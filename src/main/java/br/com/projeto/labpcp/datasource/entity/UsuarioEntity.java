package br.com.projeto.labpcp.datasource.entity;

import br.com.projeto.labpcp.controller.dto.request.LoginRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;

@Data
@Entity
@Table(name = "usuario")
public class UsuarioEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //TODO: Pular o id=1 manualmente
    private Long id;

    @Column(name = "login", nullable = false, length = 50, unique = true)
    private String login;

    @Column(name = "senha", nullable = false)
    @JsonIgnore
    private String senha;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_papel")
    private PapelEntity papel;


    public boolean senhaValida(
            LoginRequest loginRequest,
            BCryptPasswordEncoder bCryptEncoder
    ) {
        return bCryptEncoder.matches(
                loginRequest.senha(),
                this.senha
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.getSenha();
    }

    @Override
    public String getUsername() {
        return this.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
