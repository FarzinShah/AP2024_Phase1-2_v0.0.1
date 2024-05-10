package third.all.gameComponents.preGameComponent;

import java.util.HashMap;

public class Users {
    private HashMap<String,String> usernameToPasscode ;
    Users(){
        usernameToPasscode= new HashMap<>();
        usernameToPasscode.put("Farzin","1234");
    }

    public HashMap<String, String> getUsernameToPasscode() {
        return usernameToPasscode;
    }

    public void setUsernameToPasscode(HashMap<String, String> usernameToPasscode) {
        this.usernameToPasscode = usernameToPasscode;
    }
    public void addtoList(String id,String pass){
        usernameToPasscode.put(id,pass);
    }
}
