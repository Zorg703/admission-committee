package by.mordas.project.command;

/***
 Author: Sergei Mordas
 Date: 18.05.2018
 ***/
/**
 * Client type ( access level)
 */
public enum ClientType {

    GUEST(1),USER(2),ADMIN(3);
    int access;
    ClientType(int access){
        this.access=access;
    }
}
