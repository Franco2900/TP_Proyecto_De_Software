package converters;

import org.springframework.stereotype.Component;

import entities.UserRole;
import models.UserRoleModel;



@Component("userRoleConverter")
public class UserRoleConverter {
	
	public UserRoleModel entityToModel(UserRole entity) {
		return new UserRoleModel(entity.getId(), entity.getRole());
	}
	
	public UserRole modelToEntity(UserRoleModel model) {
		return new UserRole(model.getId(), model.getRole());
	}
}
