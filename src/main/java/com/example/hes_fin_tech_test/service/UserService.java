package com.example.hes_fin_tech_test.service;

import com.example.hes_fin_tech_test.dao.UserRepository;
import com.example.hes_fin_tech_test.domain.Role;
import com.example.hes_fin_tech_test.domain.Status;
import com.example.hes_fin_tech_test.domain.UserAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount user = findByUsername(username);
        if (user==null){
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    /*search by role and username with pagination*/
    public Page<UserAccount> findAllUsersByUsernameAndRole(Pageable pageable, String username, String role) {
        return userRepository.findAllUsersByUsernameAndRole(pageable, username, role);
    }
    
    public UserAccount findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public Optional<UserAccount> findById(Long id){
        return userRepository.findById(id);
    }

    public void save(UserAccount userAccount){
        userRepository.save(userAccount);
    }

    public void encryptPassword(Long id){
        userRepository.encryptPassword(id);
    }

    /*update with encrypt*/
    public void updateUser(String role, String status, UserAccount userAccount, UserAccount userFromDB) {
        userAccount.setCreatedAt(userFromDB.getCreatedAt());
        userAccount.setRole(Role.valueOf(role));
        userAccount.setStatus(Status.valueOf(status));
        save(userAccount);
        encryptPassword(userAccount.getId());
    }

    /*save with encrypt*/
    public void createUser(String role, String status, UserAccount userAccount) {
        userAccount.setCreatedAt(new Date());
        userAccount.setRole(Role.valueOf(role));
        userAccount.setStatus(Status.valueOf(status));
        save(userAccount);
        encryptPassword(userAccount.getId());
    }


}
