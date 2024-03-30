package dev.aloysius.SalesManagementSystem.Security;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import dev.aloysius.SalesManagementSystem.Domains.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final ApplicationUserDetailsService userDetailsService;

    @Bean
    public AuthenticationProvider provider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthoritiesClaimName("claim");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        httpSecurity.cors(
                AbstractHttpConfigurer::disable //for postman testing
        ).csrf(
                AbstractHttpConfigurer::disable //for postman testing
        );

        httpSecurity.authorizeHttpRequests(auth ->{
            auth.requestMatchers(HttpMethod.POST,"/api/v1/user/**").permitAll();
            auth.requestMatchers(HttpMethod.DELETE).hasAuthority("SCOPE"+Roles.ADMIN.name());
            auth.requestMatchers("/api/v1/products/*").hasAnyAuthority("SCOPE_SELLER", "SCOPE_ADMIN");
            auth.requestMatchers("/api/v1/client/*").hasAnyAuthority("SCOPE"+Roles.ADMIN.name(),"SCOPE"+Roles.SELLER.name());
            auth.requestMatchers("/api/v1/sales/*").hasAnyAuthority("SCOPE"+Roles.ADMIN.name(),"SCOPE"+Roles.SELLER.name());
            auth.anyRequest().authenticated();

        });
        httpSecurity.oauth2ResourceServer(oauth2 -> oauth2.jwt(
                jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())
        ));
        httpSecurity.authenticationProvider(provider());
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();

        //return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    @Bean
    public KeyPair keyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator kg = KeyPairGenerator.getInstance("RSA");
        kg.initialize(2048);
        return kg.generateKeyPair();
    }

    @Bean
    public JwtDecoder jwtDecoder() throws NoSuchAlgorithmException {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair().getPublic();
        return NimbusJwtDecoder.withPublicKey(publicKey).build();
    }
    @Bean
    public JwtEncoder jwtEncoder() throws NoSuchAlgorithmException {
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair().getPrivate();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair().getPublic();

        RSAKey keySet =  new RSAKey.Builder(publicKey).privateKey(privateKey).build();

        JWKSource<SecurityContext> jwkSource = new ImmutableJWKSet<>(new JWKSet(keySet));

        return new NimbusJwtEncoder(jwkSource);

    }
}
