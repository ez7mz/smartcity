package ma.smartcity.smartcity.appuser;

import lombok.AllArgsConstructor;
import ma.smartcity.smartcity.registration.token.ConfirmationToken;
import ma.smartcity.smartcity.registration.token.ConfirmationTokenService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "user with email %s not found!";

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email))
        );
    }

    public String signUpUser(AppUser appUser){
        boolean userExist = appUserRepository.findByEmail(appUser.getEmail()).isPresent();

        if (userExist){
            throw new IllegalStateException("email already taken! Try another one...");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);

        appUserRepository.save(appUser);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        // TODO : Sending token in main

        return token;
    }

    public AppUser findUserByEmail(String email)
    {
        Optional<AppUser> optionalAppUser = appUserRepository.findByEmail(email);
        return optionalAppUser.orElse(null);
    }

    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }

    public void deleteUser(Long id) {
        appUserRepository.deleteById(id);
    }
    public void saveUser(AppUser appUser){
        appUserRepository.save(appUser);
    }

    public List<AppUser> getUsers(){
        return appUserRepository.findAll();
    }

    public void deleteAppUserById(Long id){
        appUserRepository.deleteById(id);
    }
}
