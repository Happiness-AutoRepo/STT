package com.happiness.springboot.utilities;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

public class SecurityUtil {

    private Logger logger = Logger.getLogger(getClass().getName());
    private static int id = 0;

    private SecurityUtil() {
    }

    public static int authUserId() {
        return id;
    }

    public static void setAuthUserId(HttpServletRequest request) {

        if (request.getUserPrincipal().getName().equals("Marat")) {
            SecurityUtil.id = 2;;
        } else {
            SecurityUtil.id = 1;
        }
    }
}