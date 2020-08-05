package com.snapshotprojects.Bingofy.entities;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.snapshotprojects.Bingofy.enums.ApplicationUserRole;

import lombok.ToString;

@SuppressWarnings("serial")
@Entity(name = "application_user")
@Table(name = "application_user")
@ToString
public class ApplicationUser implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String password;
	private String email;
	@Enumerated(EnumType.STRING)
	private ApplicationUserRole role;
	private String uuid;/*
						 * @ElementCollection(fetch = FetchType.EAGER)
						 */
	@Column(name = "user_list")
	private HashSet<String> userList = new HashSet<>();
	private HashSet<String> grantAcessTo = new HashSet<>();
	@ElementCollection(fetch = FetchType.EAGER)
	@Column(name = "user_authorities")
	private Set<SimpleGrantedAuthority> grantedAuthorities;
	private boolean isAccountNonExpired;
	private boolean isAccountNonLocked;
	private boolean isCredentialsNonExpired;
	private boolean isEnabled;
	private String itemAttributes;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Recipe> recipe;

	public ApplicationUser(Long id, String username, String password, String email, ApplicationUserRole role,
			String uuid, HashSet<String> userList, HashSet<String> grantAcessTo,
			Set<SimpleGrantedAuthority> grantedAuthorities, boolean isAccountNonExpired, boolean isAccountNonLocked,
			boolean isCredentialsNonExpired, boolean isEnabled, String itemAttributtes, Set<Recipe> recipe) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.uuid = uuid;
		this.userList = userList;
		this.grantAcessTo = grantAcessTo;
		this.grantedAuthorities = grantedAuthorities;
		this.isAccountNonExpired = isAccountNonExpired;
		this.isAccountNonLocked = isAccountNonLocked;
		this.isCredentialsNonExpired = isCredentialsNonExpired;
		this.isEnabled = isEnabled;
		this.itemAttributes = itemAttributtes;
		this.recipe = recipe;
	}

	public ApplicationUser() {
	}

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

	public void setGrantedAuthorities(Set<SimpleGrantedAuthority> set) {
		this.grantedAuthorities = set;
	}

	@Override
	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}

	public void setAccountNonExpired(boolean isAccountNonExpired) {
		this.isAccountNonExpired = isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}

	public void setAccountNonLocked(boolean isAccountNonLocked) {
		this.isAccountNonLocked = isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean isCredentialsNonExpired) {
		this.isCredentialsNonExpired = isCredentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		System.out.println("getAuthorities called from ApplicationUser");
		return grantedAuthorities;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ApplicationUserRole getRole() {
		return role;
	}

	public void setRole(ApplicationUserRole role) {
		this.setGrantedAuthorities(role.getGrantedAuthorities());
		this.role = role;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public HashSet<String> getGrantAcessTo() {
		return grantAcessTo;
	}

	public void setGrantAcessTo(HashSet<String> grantAcessTo) {
		this.grantAcessTo = grantAcessTo;
	}

	public HashSet<String> getUserList() {
		return userList;
	}

	public void setUserList(HashSet<String> userList) {
		this.userList = userList;
	}

	public String getItemAttributtes() {
		return itemAttributes;
	}

	public void setItemAttributtes(String itemAttributtes) {
		this.itemAttributes = itemAttributtes;
	}

	@PrePersist
	public void autofill() {
		this.setUuid(UUID.randomUUID().toString());
	}

	public Set<Recipe> getRecipe() {
		return recipe;
	}

	public void setRecipe(Set<Recipe> recipe) {
		this.recipe = recipe;
	}

	/*
	 * @Override public String toString() { return "ApplicationUser [id=" + id +
	 * ", username=" + username + ", password=" + password + ", email=" + email +
	 * ", role=" + role + ", uuid=" + uuid + ", userList=" + userList +
	 * ", grantAcessTo=" + grantAcessTo + ", grantedAuthorities=" +
	 * grantedAuthorities + ", isAccountNonExpired=" + isAccountNonExpired +
	 * ", isAccountNonLocked=" + isAccountNonLocked + ", isCredentialsNonExpired=" +
	 * isCredentialsNonExpired + ", isEnabled=" + isEnabled + ", itemAttributes=" +
	 * itemAttributes + ", recipe=" + recipe + ", getId()=" + getId() +
	 * ", getUsername()=" + getUsername() + ", getPassword()=" + getPassword() +
	 * ", isAccountNonExpired()=" + isAccountNonExpired() +
	 * ", isAccountNonLocked()=" + isAccountNonLocked() +
	 * ", isCredentialsNonExpired()=" + isCredentialsNonExpired() + ", isEnabled()="
	 * + isEnabled() + ", getAuthorities()=" + getAuthorities() + ", getEmail()=" +
	 * getEmail() + ", getRole()=" + getRole() + ", getUuid()=" + getUuid() +
	 * ", getGrantAcessTo()=" + getGrantAcessTo() + ", getUserList()=" +
	 * getUserList() + ", getItemAttributtes()=" + getItemAttributtes() +
	 * ", getRecipe()=" + getRecipe() + ", getClass()=" + getClass() +
	 * ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]"; }
	 */

}
