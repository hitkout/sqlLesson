package ru.osminkin.sqlesson.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.osminkin.sqlesson.model.basicDb.User;
import ru.osminkin.sqlesson.repository.basicDb.UserRepository;

@Service
public class AuthenticatedUserService {
    private final UserRepository userRepository;

    public AuthenticatedUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean hasId(long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userRepository.findByEmail(currentPrincipalName).orElseThrow(()->new UsernameNotFoundException(""));
        return user.getId().equals(id);
    }
}
