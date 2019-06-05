package ass3Package;

import java.io.Serializable;

public class User implements Serializable {

    private String uid;

    User(){

        //base constructor

    }

    User(String uid){

        //constructor with user id input

        this.uid = uid;
    }

    public String getUid() {

        //return the uid
        return uid;
    }

    public void setUid(String uid) {

        //set the uid

        this.uid = uid;
    }
}
