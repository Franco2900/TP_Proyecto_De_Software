package com.UNLaLibrary.TP_Proyecto_De_Software.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.UNLaLibrary.TP_Proyecto_De_Software.entities.User;
import com.UNLaLibrary.TP_Proyecto_De_Software.models.UserModel;




@Component("userConverter")
public class UserConverter {
	
	@Autowired
	@Qualifier("userConverter")
	private UserConverter userConverter;
	
	@Autowired
	@Qualifier("userRoleConverter")
	private UserRoleConverter userRoleConverter;
	
		
	public UserModel entityToModel(User entity) {
		return new UserModel(entity.getId(), entity.getEmail(), entity.getUsername(), entity.getPassword(), entity.isEnabled(), userRoleConverter.entityToModel(entity.getUserRole()));
	}
	
	public User modelToEntity(UserModel model) {
		return new User(model.getId(), model.getEmail(), model.getUsername(), model.getPassword(), model.isEnabled(), userRoleConverter.modelToEntity(model.getUserRole()));
	}
}
