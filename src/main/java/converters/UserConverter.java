package converters;

import models.UsuarioModel;
import entities.Usuario;

public class UserConverter {

	//De entidad a modelo
	public UsuarioModel entityToModel(Usuario usuario) {
		return new UsuarioModel(usuario.getId(), usuario.getNombre() );
	}
	
	
	//De modelo a entidad
	public Usuario modelToEntity(UsuarioModel usuarioModel) {
		return new Usuario(usuarioModel.getId(), usuarioModel.getNombre() );
	}
	
}
