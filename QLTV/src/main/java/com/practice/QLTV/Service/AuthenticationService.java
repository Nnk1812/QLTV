package com.practice.QLTV.Service;


import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.practice.QLTV.DTO.Request.AuthenticationRequest;
import com.practice.QLTV.DTO.Response.AuthenticationResponse;
import com.practice.QLTV.Entity.Role;
import com.practice.QLTV.Entity.User;
import com.practice.QLTV.Repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    private UserRepository userRepository;
    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SINGER_KEY;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var username = userRepository.findByUserName(request.getUserName()).orElseThrow(()->
                new RuntimeException("Username Not exists"));
        if(!request.getPassWord().equals(username.getPassWord())){
            throw new RuntimeException("Wrong password");
        }
        var token = generatetooken(username);
        return AuthenticationResponse.builder()
                .token(token)
                .authenticed(true)
                .build();

    }

    private String generatetooken (User user){
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUserName())
                .issuer("bean.com")
                .issueTime(new Date())
                .claim("roles",buildRoles(user))
                .expirationTime( new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ) )
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        try{
            jwsObject.sign(new MACSigner(SINGER_KEY.getBytes()));
            return jwsObject.serialize();
        }catch (JOSEException e)
        {
            log.error("cannot create token", e);
            throw new RuntimeException(e);
        }
    }
    private String buildRoles(User user) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (user.getUserRole() != null) {
            stringJoiner.add("ROLE_" + user.getUserRole().getRoleName());
        }
        return stringJoiner.toString();
    }

}
