package api.utils;

import io.restassured.http.Cookies;

public class SessionManager {

    private static Cookies cookies;
    private static boolean loggedIn = false;

    public static void setCookies(Cookies c){
        cookies = c;
        loggedIn = true; // tandai sudah login
    }

    public static Cookies getCookies(){
        return cookies;
    }

    public static boolean isLoggedIn(){
        return loggedIn;
    }

    public static void clear(){
        cookies = null;
        loggedIn = false;
    }
}
