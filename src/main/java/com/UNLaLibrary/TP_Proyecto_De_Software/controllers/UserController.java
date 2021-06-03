package com.UNLaLibrary.TP_Proyecto_De_Software.controllers;

import javax.validation.Valid;

import com.UNLaLibrary.TP_Proyecto_De_Software.exceptions.EmailAlreadyExistException;
import com.UNLaLibrary.TP_Proyecto_De_Software.exceptions.UsernameAlreadyExistException;
import com.UNLaLibrary.TP_Proyecto_De_Software.models.UserModel;
import com.UNLaLibrary.TP_Proyecto_De_Software.services.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
	@Autowired
	private IUserService userService;

	@GetMapping("/login")
	public String login(Model model,
					@RequestParam(name="error", required=false) String error, 
					@RequestParam(name="logout", required=false) String logout) {
		model.addAttribute("error",error);
		model.addAttribute("logout",logout);
		return "user/login";
	}
		
	@GetMapping("/logout")
	public String logout(Model model) {
		return "user/logout";
	}
	
	@GetMapping("/loginsuccess")
	public String loginCheck() {
		return "redirect:/listadoDepartamentos";
	}

	@GetMapping("/registro")
	public String registro(Model model, @RequestParam(name="error", required=false) String error){
		model.addAttribute("error", error);
		model.addAttribute("userModel", new UserModel());
		return "user/registro";
	}

	@PostMapping("/registro")
	public String registro(Model model, @Valid UserModel userModel, BindingResult bindingResult,
							@RequestParam("confirmPassword") String confirmPassword){
		if(bindingResult.hasErrors()){
            model.addAttribute("userModel", userModel);
            return "user/registro";
        }

		try{
			userService.registro(userModel);
		}
		catch (UsernameAlreadyExistException e){
			bindingResult.rejectValue("username", "userModel.username","Ya existe una cuenta con este usuario.");
			model.addAttribute("userModel", userModel);
			return "user/registro";
		}
		catch (EmailAlreadyExistException e){
			bindingResult.rejectValue("email", "userModel.email","Ya existe una cuenta con este email.");
			model.addAttribute("userModel", userModel);
			return "user/registro";
		}
		if(!userModel.getPassword().equals(confirmPassword)){
			bindingResult.rejectValue("password", "userModel.password", "Las contraseñas no coinciden");
			model.addAttribute("userModel", userModel);
			return "user/registro";
		}

		return "redirect:/registrosuccess";
	}

	@GetMapping("/registrosuccess")
	public String registroCheck() {
		return "user/registrosuccess";
	}
}
