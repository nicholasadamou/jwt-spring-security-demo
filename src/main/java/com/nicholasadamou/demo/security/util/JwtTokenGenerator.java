package com.nicholasadamou.demo.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import com.nicholasadamou.demo.security.transfer.User;

/**
 * convenience class to generate a token for testing your requests.
 * Make sure the used secret here matches the one in your application.yml.
 *
 * @author Nicholas Adamou
 */
public class JwtTokenGenerator {

    /**
     * Generates a JWT token containing username as subject, and userId and role as additional claims. These properties are taken from the specified
     * User object. Tokens validity is infinite.
     *
     * @param user the user for which the token will be generated
     * @return the JWT token
     */
    public static String generateToken(User user, String secret) {
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("userId", user.getId() + "");
        claims.put("role", user.getRole());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        User user = new User();
        user.setId(123L);
        user.setUsername("nicholas");
        user.setRole("admin");

        System.out.println("**************************************\n\n" + generateToken(user, "my-very-secret-key") + "\n\n**************************************");
    }
}
