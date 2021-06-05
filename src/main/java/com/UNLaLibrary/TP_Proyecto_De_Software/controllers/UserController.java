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

	@GetMapping("/registro") // Agrega un usuario vacio para el formulario
	public String registro(Model model){
		model.addAttribute("userModel", new UserModel());
		return "user/registro";
	}

	@PostMapping("/registro") // Valida que todos los datos sean correctos
	public String registro(Model model, @Valid UserModel userModel, BindingResult bindingResult,
							@RequestParam("confirmPassword") String confirmPassword){
		// Revisa si hay un campo nulo y vuelve al registro
		if(bindingResult.hasErrors()){
            model.addAttribute("userModel", userModel);
            return "user/registro";
        }

		try{
			userService.registro(userModel);
		} 
		// Error si ya existe el nombre de usuario
		catch (UsernameAlreadyExistException e){
			bindingResult.rejectValue("username", "userModel.username","Ya existe una cuenta con este usuario.");
			model.addAttribute("userModel", userModel);
			return "user/registro";
		} 
		// Error si el email ya esta en uso
		catch (EmailAlreadyExistException e){
			bindingResult.rejectValue("email", "userModel.email","Ya existe una cuenta con este email.");
			model.addAttribute("userModel", userModel);
			return "user/registro";
		}
		// Chequea que las contraseñas sean iguales
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
