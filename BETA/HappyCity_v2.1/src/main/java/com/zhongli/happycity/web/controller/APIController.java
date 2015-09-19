package com.zhongli.happycity.web.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhongli.happycity.persistence.model.User;
import com.zhongli.happycity.web.util.GenericResponse;

@RestController
@RequestMapping(value = "/admin")
@Secured("ROLE_ADMIN")
public class APIController {

	@RequestMapping("/greeting")
	public GenericResponse greeting(@AuthenticationPrincipal User user) {
//		User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(user);
		return new GenericResponse("Hello " + user.getLastName() + "!");
	}

}
