package ecommerce.ecommerce_project.config;

import ecommerce.ecommerce_project.service.JwtService;
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
    private final UserDetailsService userDetailsService;


    public JwtFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    //overriding filter internal object (logic for filtering)
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
// we will receive a bearer token
        String authHeader=request.getHeader("Authorization");//getting authorization header
        String token=null;
        String email=null;
        if (authHeader!=null && authHeader.startsWith("Bearer ")){
            //grabbing token
            token=authHeader.substring(7);
            //creating logic decoding for email
            email=jwtService.extractEmail(token);
        }

        //checking if email exists and if user is already authenticated
        if (email!=null && SecurityContextHolder.getContext().getAuthentication()==null){

            //getting userDetails
            UserDetails userDetails=userDetailsService.loadUserByUsername(email);

            //validation
            if (jwtService.validateToken(token, userDetails)){

                //working with the next filter
                UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
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