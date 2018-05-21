package by.mordas.project.service.impl.todelete;

import by.mordas.project.entity.User;
import by.mordas.project.service.LogicException;

import java.util.HashMap;

public interface CommonLogic extends Logic{
    User findUserLoginAndPassword(String login, String password) throws LogicException;

    HashMap<String,String> registerUser(HashMap<String, String> parameters) throws LogicException;
}
