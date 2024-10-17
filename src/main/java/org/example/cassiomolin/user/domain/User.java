package org.example.cassiomolin.user.domain;


import org.example.cassiomolin.security.domain.Authority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Persistence model that represents a user.
 *
 * @author cassiomolin
 */
@Entity
@Table(name="user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;


    @Column(name="person")
    private Long person;

   
	@Column(name = "username",unique = true, nullable = false)
    private String username;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name="active" ,nullable = false)
    private boolean active;

    @Column(name="userGroup")
    private Long userGroup;

    @ElementCollection(targetClass = Authority.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Column(name = "authority")
    private Set<Authority> authorities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public Long getUserGroup() {
	return userGroup;
    }

    public void setUserGroup(Long userGroup) {
        this.userGroup = userGroup;
    }

	public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public Long getPerson() {
        return person;
    }

    public void setPerson(Long person) {
        this.person = person;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username != null ? username.equals(user.username) : user.username == null;
    }

    @Override
    public int hashCode() {
        return username != null ? username.hashCode() : 0;
    }
}
