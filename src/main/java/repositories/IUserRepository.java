package repositories;

import entities.Usuario;

public interface IUserRepository{

	public abstract Usuario findById(long id);
	public abstract Usuario findByNombre(String nombre);
}
