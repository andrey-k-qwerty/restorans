package graduation.raitrest.web;


import graduation.raitrest.AuthorizedUser;
import graduation.raitrest.model.AbstractBaseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtil {

    private static int id = AbstractBaseEntity.START_SEQ;

    private SecurityUtil() {
    }

    public static int authUserId() {
       // return id;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof AuthorizedUser)
           return  ((AuthorizedUser)principal).getId();
       return id; // return 0;
    }

    public static void setAuthUserId(int id) {
        SecurityUtil.id = id;
    }

//    public static int authUserCaloriesPerDay() {
//        return DEFAULT_CALORIES_PER_DAY;
//    }
}