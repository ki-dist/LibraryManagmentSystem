package org.example.cassiomolin.user.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import org.example.cassiomolin.security.domain.Authority;

import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * API model for returning user details.
 *
 * @author cassiomolin
 */
@JsonInclude(Include.NON_NULL)
public class QueryUserResult {

    private Long id;
    private Long customer;
    private String username;
    private Set<Authority> authorities;
    private Boolean active;

    public QueryUserResult() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

	public Long getCustomer() {
		return customer;
	}

	public void setCustomer(Long customer) {
		this.customer = customer;
	}
}