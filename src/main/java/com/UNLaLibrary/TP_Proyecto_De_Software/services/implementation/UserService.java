package com.UNLaLibrary.TP_Proyecto_De_Software.services.implementation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.UNLaLibrary.TP_Proyecto_De_Software.converters.UserConverter;
import com.UNLaLibrary.TP_Proyecto_De_Software.entities.UserRole;
import com.UNLaLibrary.TP_Proyecto_De_Software.models.UserModel;
import com.UNLaLibrary.TP_Proyecto_De_Software.repositories.IUserRepository;



@Service("userService")
public class UserService implements UserDetailsService {

	@Autowired
	@Qualifier("userRepository")
	private IUserRepository userRepository;
	
	@Autowired
	@Qualifier("userConverter")
	private UserConverter userConverter;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.UNLaLibrary.TP_Proyecto_De_Software.entities.User user = userRepository.findByUsernameAndFetchUserRolesEagerly(username);
		return (UserDetails) buildUser(user, buildGrantedAuthorities(user.getUserRole()));
	}
	
	private User buildUser(com.UNLaLibrary.TP_Proyecto_De_Software.entities.User user, List<GrantedAuthority> grantedAuthorities) {
		return new User(user.getUsername(), user.getPassword(), user.isEnabled(),
						true, true, true, 
						grantedAuthorities);
	}
	
	private List<GrantedAuthority> buildGrantedAuthorities(UserRole userRole2) {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
		grantedAuthorities.add(new SimpleGrantedAuthority(userRole2.getRole()));
		return new ArrayList<GrantedAuthority>(grantedAuthorities);
	}
	
	@SuppressWarnings("unused")
	private GrantedAuthority buildGrantedAuthority(UserRole userRole2) {
		return new SimpleGrantedAuthority(userRole2.getRole());
	}
	
	public com.UNLaLibrary.TP_Proyecto_De_Software.entities.User save(com.UNLaLibrary.TP_Proyecto_De_Software.entities.User user) {	
		return userRepository.save(user);
	}
	
	public List<UserModel> getAll(){ 
		List<UserModel> usuarios = new ArrayList<>();
		for (com.UNLaLibrary.TP_Proyecto_De_Software.entities.User u: userRepository.findAll()) {
			usuarios.add(userConverter.entityToModel(u));
		}
		return usuarios;
	}
	
	
	
	/* no pude probar esto
	public boolean existe(com.UNLaLibrary.TP_Proyecto_De_Software.entities.User user) {
		com.UNLaLibrary.TP_Proyecto_De_Software.entities.User userAux = userRepository.findByUsername(user);
		if ( userAux == null) {
			return false;
		} else {
		  return true;	
		}
	}
	*/
	
	
	/* no pude probar esto
	public List<UserModel> findByUserRole(int id) {
		List<UserModel> usuarios = new ArrayList<>();
		for (com.UNLaLibrary.TP_Proyecto_De_Software.entities.User u: userRepository.findByUserRole(id)) {
			usuarios.add(userConverter.entityToModel(u));
		}
		return usuarios;
	}
	
	public String delete(int id) {
		userRepository.deleteById(id);
		return "usuario Eliminado";
	}
	
	public UserModel insertOrUpdate(UserModel userModel) {
		com.UNLaLibrary.TP_Proyecto_De_Software.entities.User user = userRepository.save(userConverter.modelToEntity(userModel));
		return userConverter.entityToModel(user);
	}
	
	public UserModel listarId(int id) {
		com.UNLaLibrary.TP_Proyecto_De_Software.entities.User user = userRepository.findById_(id);
		return userConverter.entityToModel(user);
	}
	
	public int cantidad(String username) {
		return userRepository.repetido(username);
	}
	*/
}
	
