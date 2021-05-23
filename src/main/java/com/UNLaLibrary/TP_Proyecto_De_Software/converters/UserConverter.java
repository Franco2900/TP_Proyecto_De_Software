package com.UNLaLibrary.TP_Proyecto_De_Software.converters;

import com.UNLaLibrary.TP_Proyecto_De_Software.entities.Usuario;
import com.UNLaLibrary.TP_Proyecto_De_Software.models.UsuarioModel;

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
