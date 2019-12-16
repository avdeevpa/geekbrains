package com.geekbrains.gwt.common.entities;

import com.geekbrains.gwt.common.dtos.RoleDTO;
import com.geekbrains.gwt.common.dtos.UserDTO;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_users_id")
    @SequenceGenerator(name = "s_users_id", sequenceName = "s_users", allocationSize = 1)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name="can_initiate")
    private boolean canInitiate;

    @Column(name="can_execute")
    private boolean canExecute;

    @Transient
    private String passwordConfirm;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isCanInitiate() {
        return canInitiate;
    }

    public void setCanInitiate(boolean canInitiate) {
        this.canInitiate = canInitiate;
    }

    public boolean isCanExecute() {
        return canExecute;
    }

    public void setCanExecute(boolean canExecute) {
        this.canExecute = canExecute;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public User() {
    }

    public UserDTO toDto() {
        return new UserDTO(
          this.id,
          this.username,
          this.roles != null ? this.roles.stream().map(temp -> {
              RoleDTO obj = new RoleDTO(temp.getId(), temp.getName(), temp.getDescription());
              return obj;
          }).collect(Collectors.toSet()) : Collections.emptySet()
        );
    }
}
