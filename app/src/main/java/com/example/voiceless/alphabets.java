package com.example.voiceless;

public class alphabets {
    String URL,sign,id;
    public alphabets()
    {

    }
    public alphabets(String URL,String sign) {
        this.URL = URL;
        this.sign = sign;
    }

    public String getURL() {
        return URL;
    }

    //this can be used when URL is given from here
    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getSign() {
        return sign;
    }

    //this can be used when sign is given from here
    public void setSign(String sign) {
        this.sign = sign;
    }


}
