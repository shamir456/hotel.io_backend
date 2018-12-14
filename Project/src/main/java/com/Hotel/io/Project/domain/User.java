package com.Hotel.io.Project.domain;

import com.Hotel.io.Project.domain.security.Authority;
import com.Hotel.io.Project.domain.security.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

// implement user deatails help to get more functionality
@Entity
public class User implements UserDetails, Serializable
{
    private static final long serialVersionUID = 902783495L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id",nullable = false,updatable = false)
    private Long id;


    @NotBlank
    @Column(unique = true)
    @Size(min = 1, max = 100)
    private String username;


    private String password;

    private String firstname;

    private String lastname;

    @NotBlank
    private String email;

    private String phone;
    private boolean enabled =true;



    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnore// used to ignore this set as entity will be serialized for Rest json
    private Set<UserRole> userRole =new HashSet<>();

//    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy="user", orphanRemoval = true)
//    private Set<Hotel> hotels = new HashSet<Hotel>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities= new HashSet<>();
        userRole.forEach(ur -> authorities.add(new Authority(ur.getRole().getName())));//user role is defined for all users here
        return authorities;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<UserRole> getUserRole() {
        return userRole;
    }

    public void setUserRole(Set<UserRole> userRole) {
        this.userRole = userRole;
    }
}
