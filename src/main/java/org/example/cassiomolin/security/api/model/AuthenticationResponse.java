package org.example.cassiomolin.security.api.model;

public class AuthenticationResponse {
    private String token;
    private String authorities;
    private Boolean passwordChanged;

    public AuthenticationResponse(String token) {

    }

    public AuthenticationResponse(String token, String authorities) {
        this.token = token;
        this.authorities = authorities;
    }

    public AuthenticationResponse(String token, String authorities, Boolean passwordChanged) {
        this.token = token;
        this.authorities = authorities;
        this.passwordChanged = passwordChanged;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public Boolean getPasswordChanged() {
        return passwordChanged;
    }

    public void setPasswordChanged(Boolean passwordChanged) {
        this.passwordChanged = passwordChanged;
    }
}
