package com.UNLaLibrary.TP_Proyecto_De_Software.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.UNLaLibrary.TP_Proyecto_De_Software.entities.UserRole;



@Repository("userRoleRepository")
public interface IUserRoleRepository extends JpaRepository<UserRole, Serializable>{

	@SuppressWarnings("unchecked")
	public UserRole save(UserRole role);
	
	public abstract UserRole findById(int id);
	
	@Query(nativeQuery=true,value="Select * from userRole r where r.role=(:role)")
	public abstract List<UserRole> findByRole(String role);
	
	@Query(nativeQuery=true,value="Select count(*) from userRole r where r.role=(:role)")
	public int repetido(String role);

}
