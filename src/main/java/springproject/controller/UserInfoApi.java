package springproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserInfoApi {
    @GetMapping("/username")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<?> testUser(Principal principal, Authentication auth){
        /*  Principal or Authentication can be used to get the name of
            the currently logged in user.
         */
        // return ResponseEntity.ok(auth.getName())
        return ResponseEntity.ok(auth.getName());
    }
}
