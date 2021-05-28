package com.UNLaLibrary.TP_Proyecto_De_Software.services.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.UNLaLibrary.TP_Proyecto_De_Software.converters.UserRoleConverter;
import com.UNLaLibrary.TP_Proyecto_De_Software.entities.UserRole;
import com.UNLaLibrary.TP_Proyecto_De_Software.models.UserRoleModel;
import com.UNLaLibrary.TP_Proyecto_De_Software.repositories.IUserRoleRepository;



@Service("userRoleService")
public class UserRoleService implements UserDetailsService{

	@Autowired
	@Qualifier("userRoleConverter")
	private UserRoleConverter userRoleConverter;
	
	@Autowired
	@Qualifier("userRoleRepository")
	private IUserRoleRepository userRoleRepository;	
	
	public List<UserRoleModel> getAll() {
		List<UserRoleModel> roles = new ArrayList<>();
		for (com.UNLaLibrary.TP_Proyecto_De_Software.entities.UserRole r: userRoleRepository.findAll()) {
			roles.add(userRoleConverter.entityToModel(r));
		}
		return roles;
	}
	
	public UserRole insertOrUpdate(UserRole role) {		
		UserRole roleAux = userRoleRepository.save(role);
		return roleAux;
	}
	
	public UserRoleModel insertOrUpdate(UserRoleModel userRoleModel) {
		UserRole role = userRoleRepository.save(userRoleConverter.modelToEntity(userRoleModel));
		return userRoleConverter.entityToModel(role);
	}
	
	public UserRoleModel listarId(int id) {
		UserRole rol = userRoleRepository.findById(id);
		return userRoleConverter.entityToModel(rol);
	}
	
	
	public UserRoleModel update(UserRoleModel userRoleModel) {
		UserRole role = userRoleConverter.modelToEntity(listarId(userRoleModel.getId()));
		//UserRole role = userRoleRepository.findById(userRoleModel.getId());
		role.setRole(userRoleModel.getRole());
		userRoleRepository.save(role);
		return userRoleConverter.entityToModel(role);
	}
	
	public String delete(int id) {
		userRoleRepository.deleteById(id);
		return "perfil Eliminado";
	}
	
	public int cantidad(String role) {
		return userRoleRepository.repetido(role);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return null;
	}
	
	
	
}
