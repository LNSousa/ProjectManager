package com.lsousa.projectmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.lsousa.projectmanager.models.LoginUser;
import com.lsousa.projectmanager.models.User;
import com.lsousa.projectmanager.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String index(Model model, HttpSession session) {
		if(session.getAttribute("userId") != null) {
			return "redirect:/dashboard";
		}
		
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "index.jsp";
	}

	@PostMapping("/register")
	public String create(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model, HttpSession session) {
		User registeredUser = userService.register(newUser,result);
		
		if(result.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			return "index.jsp";
		} else {
			session.setAttribute("userId", registeredUser.getId());
			return "redirect:/dashboard";
		}
	}
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model, HttpSession session) {
		User loginUser = userService.login(newLogin, result);
		
		if (result.hasErrors()) {
			model.addAttribute("newUser", new User());
			return "index.jsp";
		} else {			
			session.setAttribute("userId", loginUser.getId());
			return "redirect:/dashboard";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
