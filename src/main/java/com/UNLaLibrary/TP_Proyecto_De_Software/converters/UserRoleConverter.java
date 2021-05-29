package com.UNLaLibrary.TP_Proyecto_De_Software.converters;

import org.springframework.stereotype.Component;

import com.UNLaLibrary.TP_Proyecto_De_Software.entities.UserRole;
import com.UNLaLibrary.TP_Proyecto_De_Software.models.UserRoleModel;



@Component("userRoleConverter")
public class UserRoleConverter {
	
	public UserRoleModel entityToModel(UserRole entity) {
		return new UserRoleModel(entity.getId(), entity.getRole());
	}
	
	public UserRole modelToEntity(UserRoleModel model) {
		return new UserRole(model.getId(), model.getRole());
	}
}
