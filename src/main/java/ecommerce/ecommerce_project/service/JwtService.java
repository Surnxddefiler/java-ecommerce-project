package ecommerce.ecommerce_project.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.print.attribute.standard.Severity;
import java.security.Key;
import java.security.PublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    //env variable
    @Value("${jwt.secret}")
    private String secretKey;

    public String generateToken(@NotNull @NotBlank @Email String email) {
        //claims
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .claims(claims)//adding claims
                .subject(email)//main subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))// token start
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))//token end
                .signWith(getKey())
                .compact();
    }

    //decoding secret key to sign token
    private Key getKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
    }

    public String extractEmail(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimResolver) {
    final Claims claims=extractAllClaims(token);
    return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
    }

    private SecretKey getSigningKey() {
    byte[] keyBytes=Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(keyBytes);
    }

    //validating token
    public boolean validateToken(String token, UserDetails userDetails){
        //extracting email
        final String email=extractEmail(token);
        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    //checking if token is expired
    private boolean isTokenExpired(String token){
        return extractExperationDate(token).before(new Date());
    };
    //getting experation date
    private Date extractExperationDate(String token){
        return extractClaims(token, Claims::getExpiration);
    }
}
