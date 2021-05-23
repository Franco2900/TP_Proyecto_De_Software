package com.UNLaLibrary.TP_Proyecto_De_Software.repositories;

import com.UNLaLibrary.TP_Proyecto_De_Software.entities.Usuario;

public interface IUserRepository{

	public abstract Usuario findById(long id);
	public abstract Usuario findByNombre(String nombre);
}
