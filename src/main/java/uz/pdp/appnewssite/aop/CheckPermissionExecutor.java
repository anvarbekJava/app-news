package uz.pdp.appnewssite.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.pdp.appnewssite.entity.Users;
import uz.pdp.appnewssite.exseptions.ForbiddenException;

@Component
@Aspect
public class CheckPermissionExecutor {
    @Before(value = "@annotation(checkPermission)")
    public void checkPermissionMethod(CheckPermission checkPermission){
        Users users = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean exists = false;
        for (GrantedAuthority authority : users.getAuthorities()) {
            if (authority.getAuthority().equals(checkPermission.huquq())){
                exists =true;
                break;
            }
        }
        if(!exists)
            throw new ForbiddenException(checkPermission.huquq(), "Ruxsat yoq");
    }
}
