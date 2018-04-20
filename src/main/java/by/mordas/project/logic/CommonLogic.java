package by.mordas.project.logic;

import by.mordas.project.entity.User;

public interface CommonLogic extends Logic{
    public User findUserLoginAndPassword(String login, String password) throws LogicException;
}
