package by.mordas.project.command;

public enum CommandType {
    SHOW_ALL_USERS(ClientType.ADMIN),
    FIND_SPECIALITY(ClientType.ADMIN),
    FIND_USER_SUBJECTS(ClientType.ADMIN),
    ADD_FACULTY(ClientType.ADMIN),
    DELETE_FACULTY(ClientType.ADMIN),
    FIND_ALL_FACULTY(ClientType.ADMIN),
    ADD_SPECIALITY(ClientType.ADMIN),
    FIND_USER_BY_ID(ClientType.ADMIN),
    DELETE_SPECIALITY(ClientType.ADMIN),
    SHOW_ALL_SPECIALITIES(ClientType.ADMIN),
    SHOW_ALL_USERS_REGISTER_ON_SPECIALITY(ClientType.ADMIN),
    UPDATE_FACULTY(ClientType.ADMIN),
    UPDATE_SPECIALITY(ClientType.ADMIN),
    UPDATE_SPECIALITY_REGISTER_DATE(ClientType.ADMIN),
    SHOW_ACCEPTED_USERS(ClientType.ADMIN),
    GO_TO_DELETE_FACULTY_PAGE(ClientType.ADMIN),
    GO_TO_DELETE_SPECIALITY_PAGE(ClientType.ADMIN),
    GO_TO_UPDATE_SPECIALITY_PAGE(ClientType.ADMIN),
    GO_TO_UPDATE_FACULTY_PAGE(ClientType.ADMIN),
    GO_TO_FIND_USER_BY_ID_PAGE(ClientType.ADMIN),
    GO_TO_CHOOSE_FACULTY_AND_SPECIALITY_PAGE(ClientType.ADMIN),
    FIND_SPECIALITIES_FOR_CHOOSE_FACULTY(ClientType.ADMIN),
    GO_TO_ADD_FACULTY_PAGE(ClientType.ADMIN),
    GO_TO_ADD_SPECIALITY_PAGE(ClientType.ADMIN),
    GO_TO_FIND_ALL_FACULTY_PAGE(ClientType.ADMIN),
    SHOW_RESULT_OF_ADMISSION_COMMITTEE(ClientType.ADMIN),
    NEXT_FIND_USERS_PAGE(ClientType.ADMIN),
    PREVIOUS_FIND_USERS_PAGE(ClientType.ADMIN),

    LOGIN(ClientType.GUEST),
    UPDATE_LOCALE(ClientType.GUEST),
    USER_REGISTRATION(ClientType.GUEST),
    GO_TO_MAIN_PAGE(ClientType.GUEST),
    LOG_OUT(ClientType.GUEST),

    SHOW_ALL_FACULTY(ClientType.USER),
    CHANGE_USER_PASSWORD(ClientType.USER),
    CHOOSE_SPECIALITY(ClientType.USER),
    REGISTER_ON_SPECIALITY(ClientType.USER),
    SHOW_USER_STATUS(ClientType.USER),
    GO_TO_REGISTER_FACULTY_PAGE(ClientType.USER),
    GO_TO_CHANGE_PASSWORD_PAGE(ClientType.USER),
    GO_TO_USER_DATA_PAGE(ClientType.USER),
    GO_TO_USER_STATUS_PAGE(ClientType.USER),
    CANCEL_REGISTRATION(ClientType.USER),
    GO_TO_CANCEL_REGISTRATION_PAGE(ClientType.USER);

    CommandType(ClientType type) {
    this.type=type;
    }
    private ClientType type;

    public ClientType getType() {
        return type;
    }
}
