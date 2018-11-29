package com.Hotel.io.Project.domain.security;


import com.Hotel.io.Project.domain.User;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="user_role")
public class UserRole implements Serializable
{
    private static long serialVersionUID=890345L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userRoleId;


    public UserRole()
    {

    }
    public UserRole(User user, Role role)
    {
        this.user =user;
        this.role=role;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static void setSerialVersionUID(long serialVersionUID) {
        UserRole.serialVersionUID = serialVersionUID;
    }

    public long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
