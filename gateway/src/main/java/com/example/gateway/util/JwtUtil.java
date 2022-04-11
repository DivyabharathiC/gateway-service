package com.example.gateway.util;

import com.example.gateway.exception.JwtTokenMalformedException;
import com.example.gateway.exception.JwtTokenMissingException;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;



@Component
public class JwtUtil {

    private static Logger log = LoggerFactory.getLogger(JwtUtil.class);

    @Value("${jwt.secret}")
    private String jwtSecret;

    public Claims getClaims(final String token){
        try {
            return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

        } catch (Exception e) {
            log.info(String.format("%s => %s",e.getMessage() , e));


        }
        return null ;
    }

    public void validateToken(final String token) throws JwtTokenMalformedException, JwtTokenMissingException {
        try {

            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);


        } catch (SignatureException ex) {

            throw new JwtTokenMalformedException("Invalid JWT signature");
        } catch (MalformedJwtException ex) {

            throw new JwtTokenMalformedException("Invalid JWT token");
        } catch (ExpiredJwtException ex) {

            throw new JwtTokenMalformedException("Expired JWT token");
        } catch (UnsupportedJwtException ex) {

            throw new JwtTokenMalformedException("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {

            throw new JwtTokenMissingException("JWT claims string is empty.");
        }
    }

}