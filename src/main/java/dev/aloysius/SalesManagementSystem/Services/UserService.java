package dev.aloysius.SalesManagementSystem.Services;

import dev.aloysius.SalesManagementSystem.CustomExceptions.UserAlreadyExistsException;
import dev.aloysius.SalesManagementSystem.Domains.ApplicationUsers;
import dev.aloysius.SalesManagementSystem.Domains.UserAuthentication;
import dev.aloysius.SalesManagementSystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final AuthenticationProvider provider;
    private final JwtEncoder encoder;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public String authenticateUser(UserAuthentication userAuthentication) {
        System.out.println(userAuthentication);
        Authentication authenticate = provider.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userAuthentication.email(), userAuthentication.password()
                )
        );
        System.out.println(userAuthentication);
        SecurityContext emptyContext = SecurityContextHolder.createEmptyContext();
        emptyContext.setAuthentication(authenticate);

        return generateToken(authenticate);
    }
    public void registerUsers(ApplicationUsers users) throws UserAlreadyExistsException {
        System.out.println(users.toString());
        if (userRepository.findByEmail(users.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User with email: %s already exist".formatted(users.getEmail()));
        }
        System.out.println(users.getUserPassword());
        users.setUserPassword(passwordEncoder.encode(users.getUserPassword()));
        userRepository.save(users);

    }

    private String generateToken(Authentication authenticate) {

        String claim = authenticate.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));

        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .subject(authenticate.getName())
                .issuer("self")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plus(1, ChronoUnit.HOURS))
                .claim("claim", claim)
                .build();

        return encoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }


}
