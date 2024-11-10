package com.app.shop.jwt;

import com.app.shop.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.util.Objects;

@Component
public class JwtDecoderCustom implements JwtDecoder {
    IUserService userService;
    private NimbusJwtDecoder nimbusJwtDecoder = null;

    @Value("${jwt.signerKey}")
    private String signerKey;

    @Autowired
    public JwtDecoderCustom(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public Jwt decode(String token) throws JwtException {
        userService.introspect(token);
        
        if (Objects.isNull(nimbusJwtDecoder)) {
            SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(), "HS256");
            nimbusJwtDecoder = NimbusJwtDecoder.withSecretKey(secretKeySpec)
                    .macAlgorithm(MacAlgorithm.HS256)
                    .build();
        }
        return nimbusJwtDecoder.decode(token);
    }
}
