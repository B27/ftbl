package baikal.web.footballapp.model;

class LoginData {
    private String login;
    private String password;
    private String type;

    public LoginData(String login, String password, String type) {
        this.login = login;
        this.password = password;
        this.type = type;
    }

    /**
     *
     * @return
     * The login
     */
    public String getLogin() {
        return login;
    }

    /**
     *
     * @param login
     * The email
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     *
     * @return
     * The password
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     * The password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     *
     * @return
     * The type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     * The password
     */
    public void setType(String type) {
        this.type = type;
    }

}
