import org.apache.commons.lang3.RandomStringUtils;

public class CourierJson {
    private String login;
    private String password;
    private String firstName;

    public CourierJson(String password, String firstName) {
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }



    public CourierJson(){

    }
    public CourierJson(String login, String password, String firstName){
        this.firstName = firstName;
        this.login = login;
        this.password = password;
    }

    public static CourierJson randomCourier(int loginLength, int passwordLength, int firstNameLengths){
        String login = RandomStringUtils.randomAlphabetic(loginLength);
        String password = RandomStringUtils.randomAlphanumeric(passwordLength);
        String firstName = RandomStringUtils.randomAlphabetic(firstNameLengths);
        return new CourierJson(login, password, firstName);
    }


    @Override
    public String toString(){
        return("Courier: \n   login : "+login+"\n   password : "+password+"\n   firstName : "+firstName);
    }


}
