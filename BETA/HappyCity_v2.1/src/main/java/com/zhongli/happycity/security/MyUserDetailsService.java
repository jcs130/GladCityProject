package com.zhongli.happycity.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.zhongli.happycity.persistence.dao.RoleRepository;
import com.zhongli.happycity.persistence.dao.UserRepository;
import com.zhongli.happycity.persistence.model.Privilege;
import com.zhongli.happycity.persistence.model.Role;
import com.zhongli.happycity.persistence.model.User;

@Service("userDetailsService")
@Transactional
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private LoginAttemptService loginAttemptService;

	@Autowired
	private HttpServletRequest request;

	public MyUserDetailsService() {
		super();
	}

	// API

	@Override
	public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
		final String ip = request.getRemoteAddr();
		if (loginAttemptService.isBlocked(ip)) {
			throw new RuntimeException("blocked");
		}

		try {
			final User user = userRepository.findByEmail(email);
			if (user == null) {
				System.out.println("No this user, as a GUEST.");
				return new User(email, "", Arrays.asList(roleRepository.findByName("ROLE_GUEST")));
			}
			//显示现在的用户信息
			user.setAuthorities(getAuthorities(user.getRoles()));
			System.out.println("直接或取得信息："+user);
			System.out.println(user.getPassword());
//			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
//					user.isEnabled(), true, true, true, getAuthorities(user.getRoles()));
			return user;
		} catch (final Exception e) {
			System.out.println("找不到用户");
			throw new RuntimeException(e);
		}
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

	/**
	 * 获得权限
	 * 
	 * @param privileges
	 * @return
	 */
	private final List<GrantedAuthority> getGrantedAuthorities_Privileges(final List<String> privileges) {
		final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (final String privilege : privileges) {
			authorities.add(new SimpleGrantedAuthority(privilege));
		}
		return authorities;
	}

	/**
	 * 获得角色
	 * 
	 * @param roles
	 * @return
	 */
	private final List<GrantedAuthority> getGrantedAuthorities_Roles(final Collection<Role> roles) {
		final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (final Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return authorities;
	}

}
