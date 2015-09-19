package com.zhongli.happycity.spring;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.zhongli.happycity.persistence.dao.PrivilegeRepository;
import com.zhongli.happycity.persistence.dao.RoleRepository;
import com.zhongli.happycity.persistence.dao.UserRepository;
import com.zhongli.happycity.persistence.model.Privilege;
import com.zhongli.happycity.persistence.model.Role;
import com.zhongli.happycity.persistence.model.User;

@Component
@TransactionConfiguration(transactionManager="userAccountTransactionManager")
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	private boolean alreadySetup = false;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PrivilegeRepository privilegeRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// API

	@Override
	@Transactional
	// 程序初始化时的数据
	public void onApplicationEvent(final ContextRefreshedEvent event) {
		if (alreadySetup) {
			return;
		}

		// == create initial privileges
		// 基本权限
		final Privilege basicPrivilege = createPrivilegeIfNotFound("BASIC_PRIVILEGE");
		// 标注数据的权限
		final Privilege markPrivilege = createPrivilegeIfNotFound("MARK_PRIVILEGE");
		// 后台管理的权限
		final Privilege managePrivilege = createPrivilegeIfNotFound("MANAGE_PRIVILEGE");
		// 获取数据的权限
		final Privilege getDataPrivilege = createPrivilegeIfNotFound("GET_DATA_PRIVILEGE");
		// 添加公共监听区域的权限
		final Privilege addPublicAreaPrivilege = createPrivilegeIfNotFound("ADD_PUBLIC_AREA_PRIVILEGE");
		// 添加私有监听区域的权限
		final Privilege addPrivateAreaPrivilege = createPrivilegeIfNotFound("ADD_PRIVATE_AREA_PRIVILEGE");
		// == create initial roles
		final List<Privilege> adminPrivileges = Arrays.asList(markPrivilege, managePrivilege, addPublicAreaPrivilege);
		// 管理员角色
		createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
		// 研究人员角色
		createRoleIfNotFound("ROLE_REACHER", Arrays.asList(basicPrivilege, getDataPrivilege, markPrivilege,
				addPublicAreaPrivilege, addPrivateAreaPrivilege));
		// 学生角色
		createRoleIfNotFound("ROLE_STUDENT", Arrays.asList(basicPrivilege, markPrivilege, addPublicAreaPrivilege));
		// 普通用户角色
		createRoleIfNotFound("ROLE_USER", Arrays.asList(basicPrivilege, markPrivilege));
		// 游客角色
		createRoleIfNotFound("ROLE_GUEST", null);

		// ==添加初始的账号
//		// 添加默认的管理员用户
//		createUserIfNotFound("admin@admin.com", "admin", "admin", "admin",
//				Arrays.asList(roleRepository.findByName("ROLE_ADMIN")));
//		// 添加默认的学生员用户
//		createUserIfNotFound("student@student.com", "student", "student", "student",
//				Arrays.asList(roleRepository.findByName("ROLE_USER"), roleRepository.findByName("ROLE_STUDENT")));
//		// 添加默认的研究人员用户
//		createUserIfNotFound("reacher@reacher.com", "reacher", "reacher", "reacher",
//				Arrays.asList(roleRepository.findByName("ROLE_USER"), roleRepository.findByName("ROLE_REACHER")));
//		// 添加默认的普通用户
//		createUserIfNotFound("user@user.com", "user", "user", "user",
//				Arrays.asList(roleRepository.findByName("ROLE_USER")));

		alreadySetup = true;
	}

	@Transactional
	private final Privilege createPrivilegeIfNotFound(final String name) {
		Privilege privilege = privilegeRepository.findByName(name);
		if (privilege == null) {
			privilege = new Privilege(name);
			privilegeRepository.save(privilege);
		}
		return privilege;
	}

	@Transactional
	private final Role createRoleIfNotFound(final String name, final Collection<Privilege> privileges) {
		Role role = roleRepository.findByName(name);
		if (role == null) {
			role = new Role(name);
			role.setPrivileges(privileges);
			roleRepository.save(role);
		} else {
			System.out.println("Role" + name + "exist");
		}
		return role;
	}

	@Transactional
	private final User createUserIfNotFound(final String email, final String firstName, final String lastName,
			final String password, List<Role> roles) {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			user = new User(email, passwordEncoder.encode(password), roles);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setEnabled(true);
			userRepository.save(user);
		} else {
			System.out.println(user.getEmail() + " is already exits.");
		}
		return user;
	}


}