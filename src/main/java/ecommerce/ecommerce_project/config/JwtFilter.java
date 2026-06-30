package ecommerce.ecommerce_project.config;

import ecommerce.ecommerce_project.service.JwtService;
import ecommerce.ecommerce_project.userDetails.CustomUserDetails;
import ecommerce.ecommerce_project.userDetails.MyUserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter  extends OncePerRequestFilter {//making jwt once per request filter, every request will trigger this filter


    //jwt service for token validation
    private final JwtService jwtService;
//function to get user details
    private final MyUserDetailService myUserDetailService;


    public JwtFilter(JwtService jwtService, MyUserDetailService myUserDetailService) {
        this.jwtService = jwtService;
        this.myUserDetailService = myUserDetailService;
    }

    //overriding filter internal object (logic for filtering)
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
// we will receive a bearer token
        String authHeader=request.getHeader("Authorization");//getting authorization header
        String token=null;
        String userId=null;
        if (authHeader!=null && authHeader.startsWith("Bearer ")){
            //grabbing token
            token=authHeader.substring(7);
            //creating logic decoding for email
            userId=jwtService.extractUserId(token);
        }

        //checking if email exists and if user is already authenticated
        if (userId!=null && SecurityContextHolder.getContext().getAuthentication()==null){

            //getting userDetails
            CustomUserDetails customUserDetails=myUserDetailService.loadUserByUsername(userId);

            //validation
            if (jwtService.validateToken(token, customUserDetails)){

                //working with the next filter
                UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(
                        customUserDetails,
                        null,
                        customUserDetails.getAuthorities()
                );
                //setting to auth token request
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        //passing filter in chain
        filterChain.doFilter(request, response);
    }
}