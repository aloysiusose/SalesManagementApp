package dev.aloysius.SalesManagementSystem.Security;

import dev.aloysius.SalesManagementSystem.Domains.ApplicationUsers;
import dev.aloysius.SalesManagementSystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).map(
                AppUserDetails::new
        ).orElseThrow(()-> new UsernameNotFoundException("User with email: %s not found".formatted(username)));
    }

   protected static class AppUserDetails extends ApplicationUsers implements UserDetails{

       public AppUserDetails(ApplicationUsers users) {
           super(users);
       }

       @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return List.of( ()-> this.getRoles().name());
        }

        @Override
        public String getPassword() {
            return this.getUserPassword();
        }

        @Override
        public String getUsername() {
            return this.getEmail();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}
