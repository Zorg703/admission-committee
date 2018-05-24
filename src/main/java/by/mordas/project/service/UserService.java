package by.mordas.project.service;

import by.mordas.project.entity.Speciality;
import by.mordas.project.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    Optional<List<User>> findAllUser() throws LogicException;

    Optional<User> findUserById(String userId) throws LogicException;

    Optional<List<User>> findUsersRegisterOnSpeciality(String id) throws LogicException;

    Optional<List<User>> findAllAcceptedUsersOnSpeciality(String specialityId) throws LogicException;

    Map<String,String> changePassword(Long userId, String password1, String password2 ) throws LogicException;

    User setUserSpeciality(User user,Long specialityId,HashMap<String,String> parameters) throws LogicException;

    boolean isAccepted(Speciality speciality, User user) throws LogicException;

    User canceledUserRegistration(User user) throws LogicException;

    Optional<User> findUserLoginAndPassword(String login, String password) throws LogicException;

    Map<String,String> registerUser(Map<String, String> parameters) throws LogicException;

    Optional<List<User>> findUserLimitCount(String count) throws LogicException;
}
