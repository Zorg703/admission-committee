package by.mordas.project.command;

public enum ClientType {

    GUEST(1),USER(2),ADMIN(3);
    int access;
    ClientType(int access){
        this.access=access;
    }
}
