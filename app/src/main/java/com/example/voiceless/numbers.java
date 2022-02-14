
package com.example.voiceless;

public class numbers {
    String URL, id;
    long sign;

    public numbers()
    {

    }
    public numbers(String URL,long sign) {
        this.URL = URL;
        this.sign = sign;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public long getSign() {
        return sign;
    }

    public void setSign(long sign) {
        this.sign = sign;
    }


}
