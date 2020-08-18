package com.altimetrik.rest.api.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserModel implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String username;

	private String password;

	private Collection<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<GrantedAuthority> getGrantedAuthoritiesList() {
		return grantedAuthoritiesList;
	}

	public void setGrantedAuthoritiesList(Collection<GrantedAuthority> grantedAuthoritiesList) {
		this.grantedAuthoritiesList = grantedAuthoritiesList;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public UserModel() {

	}

	public UserModel(User user) {
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.grantedAuthoritiesList = Arrays.stream(user.getRoles().split(",")).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return grantedAuthoritiesList;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
