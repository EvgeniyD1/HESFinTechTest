package com.example.hes_fin_tech_test.service;

import com.example.hes_fin_tech_test.dao.UserRepository;
import com.example.hes_fin_tech_test.domain.Role;
import com.example.hes_fin_tech_test.domain.Status;
import com.example.hes_fin_tech_test.domain.UserAccount;
import com.example.hes_fin_tech_test.domain.UserAccountDTO;
import com.example.hes_fin_tech_test.domain.UserAccountMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
    public Page<UserAccountDTO> findAllUsersByUsernameAndRole(Pageable pageable, String username, String role) {
        return userRepository.findAllUsersByUsernameAndRole(pageable, username, role)
                .map(UserAccountMapper.USER_ACCOUNT_MAPPER::userAccountToUserAccountDTO);
    }
    
    public UserAccount findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public Optional<UserAccountDTO> findById(Long id){
        return userRepository.findById(id).map(UserAccountMapper.USER_ACCOUNT_MAPPER::userAccountToUserAccountDTO);
    }

    public void save(UserAccount userAccount){
        userRepository.save(userAccount);
    }

    /*update with encrypt*/
    public void updateUser(String role, String status, UserAccountDTO userAccount, UserAccountDTO userFromDB) {
        userAccount.setCreatedAt(userFromDB.getCreatedAt());
        userAccount.setRole(Role.valueOf(role));
        userAccount.setStatus(Status.valueOf(status));
        UserAccount account = UserAccountMapper.USER_ACCOUNT_MAPPER.userAccountDTOToUserAccount(userAccount);
        account.setPassword(passwordEncoder.encode(userAccount.getPassword()));
        save(account);
    }

    /*save with encrypt*/
    public void createUser(String role, String status, UserAccountDTO userAccount) {
        userAccount.setCreatedAt(new Date());
        userAccount.setRole(Role.valueOf(role));
        userAccount.setStatus(Status.valueOf(status));
        UserAccount account = UserAccountMapper.USER_ACCOUNT_MAPPER.userAccountDTOToUserAccount(userAccount);
        account.setPassword(passwordEncoder.encode(userAccount.getPassword()));
        save(account);
    }


}
