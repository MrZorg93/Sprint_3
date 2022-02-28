public class CourierAuthDataJson {
    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CourierAuthDataJson() {
    }

    public CourierAuthDataJson(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static CourierAuthDataJson from(CourierJson courierJson){
        return new CourierAuthDataJson(courierJson.getLogin(), courierJson.getPassword());
    }

    public String toString(){
        return ("Login: "+login+"\n"+
                "Password: " + password);
    }


}
