package com.redditb.reddit.security;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.time.Instant;
import java.util.Date;

import javax.annotation.PostConstruct;

import com.redditb.reddit.exceptions.SpringRedditException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class JwtProvider {

    private KeyStore keyStore;

    @Value("${jwt.expiration.time}")
    private Long jwtExpirationInMillis;

    @PostConstruct //Read about this annotation
    public void init() {
        try {
            keyStore = KeyStore.getInstance("JKS"); // we want ot work with jks type 
            InputStream resourceAsStream = getClass().getResourceAsStream("/springjwt.jks"); //start getting the data from jks files
            keyStore.load(resourceAsStream, "password".toCharArray()); // load given we pass the correct password
        } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) { // handle exceptions
            throw new SpringRedditException("Exception occurred while loading keystore", e);
        }
    }

    
    public String generateToken(Authentication authentication){

        User principal = (User)authentication.getPrincipal(); // This is purely theory based.
        return Jwts.builder()
                .setSubject(principal.getUsername()) // body
                .signWith(getPrivateKey()) // signed it with our private key
                .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis)))
                .compact(); // converted to string
    }

    // This is used for refresh token in security context goes empty
    public String generateTokenWithUserName(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(Date.from(Instant.now()))
                .signWith(getPrivateKey())
                .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis)))
                .compact();
    }

    // Returning the private Key
    private PrivateKey getPrivateKey() {
        try {
            return (PrivateKey) keyStore.getKey("springjwt", "password".toCharArray()); // returing private key
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
            throw new SpringRedditException("Exception occured while retrieving public key from keystore", e);
        }
    }

    public boolean validateToken(String jwt) {
        System.out.println("Here JwtPRovider validating token"); //2
        Jwts.parser().setSigningKey(getPublickey()).parseClaimsJws(jwt);
        return true;
    }
    private PublicKey getPublickey() {
        System.out.println("Here Inside getPublicKey"); //3
        try {
            return keyStore.getCertificate("springjwt").getPublicKey();
        } catch (KeyStoreException e) {
            throw new SpringRedditException("Exception occured while " +
                    "retrieving public key from keystore", e);
        }
    }
    public String getUsernameFromJwt(String token) {
        System.out.println("Here inside getUsernameFromJwt"); //4
        Claims claims = Jwts.parser()
                .setSigningKey(getPublickey())
                .parseClaimsJws(token)
                .getBody();
        System.out.println("Claims.getsub:"+ claims.getSubject()); //jwtProvider is working fine.
        return claims.getSubject();
    }

    public Long getJwtExpirationInMillis() {
        return jwtExpirationInMillis;
    }
}
