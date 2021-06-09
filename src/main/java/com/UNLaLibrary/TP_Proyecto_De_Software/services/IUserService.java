package com.UNLaLibrary.TP_Proyecto_De_Software.services;

import com.UNLaLibrary.TP_Proyecto_De_Software.exceptions.EmailAlreadyExistException;
import com.UNLaLibrary.TP_Proyecto_De_Software.exceptions.UsernameAlreadyExistException;
import com.UNLaLibrary.TP_Proyecto_De_Software.models.UserModel;

public interface IUserService {
    public void registro(UserModel userModel) throws UsernameAlreadyExistException, EmailAlreadyExistException;
    public UserModel traerUserPorUsername(String username);
}
