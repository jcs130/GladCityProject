package com.zhongli.happycity.persistence.model;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class User implements UserDetails, CredentialsContainer {

	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

	// ~ Instance fields
	// ================================================================================================
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String email;
	@Column(length = 60)
	private String password;
	private String firstName;
	private String lastName;
	@Transient
	private Set<GrantedAuthority> authorities;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	private Date created_on;
//	private String nickName;
	@ManyToMany
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id") , inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id") )
	private Collection<Role> roles;

	private boolean tokenExpired;

	// ~ Constructors
	// ===================================================================================================
	public User() {
		this.enabled = false;
		this.accountNonExpired = true;
		this.credentialsNonExpired = true;
		this.accountNonLocked = true;
		this.tokenExpired = false;
	}

	public User(String email, String password, List<Role> roles) {
		if (((email == null) || "".equals(email)) || (password == null)) {
			throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
		}
		this.created_on = new Date();
		this.email = email;
		this.password = password;
		this.setRoles(roles);
		this.enabled = false;
		this.accountNonExpired = true;
		this.credentialsNonExpired = true;
		this.accountNonLocked = true;
		this.tokenExpired = false;
		this.authorities = getAuthorities(roles);
		// this.authorities = Collections
		// .unmodifiableSet(sortAuthorities(getGrantedAuthorities_Roles_and_Privileges(roles)));
	}

	// ~ Methods
	// ========================================================================================================
	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String username) {
		this.email = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(final Collection<Role> roles) {
		this.roles = roles;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(final boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isTokenExpired() {
		return tokenExpired;
	}

	public void setTokenExpired(final boolean expired) {
		this.tokenExpired = expired;
	}

	public Collection<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public String getUsername() {
		return email;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void eraseCredentials() {
		password = null;
	}

	public Date getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}

	// private static SortedSet<GrantedAuthority> sortAuthorities(Collection<?
	// extends GrantedAuthority> authorities) {
	// Assert.notNull(authorities, "Cannot pass a null GrantedAuthority
	// collection");
	// // Ensure array iteration order is predictable (as per
	// // UserDetails.getAuthorities() contract and SEC-717)
	// SortedSet<GrantedAuthority> sortedAuthorities = new
	// TreeSet<GrantedAuthority>(new AuthorityComparator());
	//
	// for (GrantedAuthority grantedAuthority : authorities) {
	// Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain
	// any null elements");
	// sortedAuthorities.add(grantedAuthority);
	// }
	//
	// return sortedAuthorities;
	// }

	// private static class AuthorityComparator implements
	// Comparator<GrantedAuthority>, Serializable {
	// private static final long serialVersionUID =
	// SpringSecurityCoreVersion.SERIAL_VERSION_UID;
	//
	// public int compare(GrantedAuthority g1, GrantedAuthority g2) {
	// // Neither should ever be null as each entry is checked before
	// // adding it to the set.
	// // If the authority is null, it is a custom authority and should
	// // precede others.
	// if (g2.getAuthority() == null) {
	// return -1;
	// }
	//
	// if (g1.getAuthority() == null) {
	// return 1;
	// }
	//
	// return g1.getAuthority().compareTo(g2.getAuthority());
	// }
	// }

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final User user = (User) obj;
		if (!email.toLowerCase().equals(user.email.toLowerCase())) {
			return false;
		}
		return true;
	}

	/**
	 * Returns the hashcode of the {@code username}.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password= [Protected]" + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", authorities=" + authorities + ", accountNonExpired=" + accountNonExpired
				+ ", accountNonLocked=" + accountNonLocked + ", credentialsNonExpired=" + credentialsNonExpired
				+ ", enabled=" + enabled + ", created_on=" + created_on + ", roles=" + roles + ", tokenExpired="
				+ tokenExpired + "]";
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void setAuthorities(Set<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	/**
	 * 选择用户详细信息
	 * 
	 * @param roles
	 * @return
	 */
	public final Set<GrantedAuthority> getAuthorities(final Collection<Role> roles) {
		return getGrantedAuthorities_Roles_and_Privileges(roles);
	}

	private final Set<String> getPrivileges(final Collection<Role> roles) {
		final Set<String> privileges = new HashSet<String>();
		final Set<Privilege> collection = new HashSet<Privilege>();
		for (final Role role : roles) {
			collection.addAll(role.getPrivileges());
		}
		for (final Privilege item : collection) {
			privileges.add(item.getName());
		}
		return privileges;
	}

	/**
	 * 同时获得角色和权限
	 * 
	 * @param roles
	 * @return
	 */
	private final Set<GrantedAuthority> getGrantedAuthorities_Roles_and_Privileges(final Collection<Role> roles) {
		final Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		for (final Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		Set<String> privileges = getPrivileges(roles);
		for (final String privilege : privileges) {
			authorities.add(new SimpleGrantedAuthority(privilege));
		}
		return authorities;
	}

//	public String getNickName() {
//		return nickName;
//	}
//
//	public void setNickName(String nickName) {
//		this.nickName = nickName;
//	}

	// /**
	// * 获得权限
	// *
	// * @param privileges
	// * @return
	// */
	// private final List<GrantedAuthority>
	// getGrantedAuthorities_Privileges(final List<String> privileges) {
	// final List<GrantedAuthority> authorities = new
	// ArrayList<GrantedAuthority>();
	// for (final String privilege : privileges) {
	// authorities.add(new SimpleGrantedAuthority(privilege));
	// }
	// return authorities;
	// }
	//
	// /**
	// * 获得角色
	// *
	// * @param roles
	// * @return
	// */
	// private final List<GrantedAuthority> getGrantedAuthorities_Roles(final
	// Collection<Role> roles) {
	// final List<GrantedAuthority> authorities = new
	// ArrayList<GrantedAuthority>();
	// for (final Role role : roles) {
	// authorities.add(new SimpleGrantedAuthority(role.getName()));
	// }
	// return authorities;
	// }
}
