package com.zhongli.happycity.persistence.service;

import java.util.Arrays;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.zhongli.happycity.persistence.dao.PasswordResetTokenRepository;
import com.zhongli.happycity.persistence.dao.RoleRepository;
import com.zhongli.happycity.persistence.dao.UserRepository;
import com.zhongli.happycity.persistence.dao.VerificationTokenRepository;
import com.zhongli.happycity.persistence.model.PasswordResetToken;
import com.zhongli.happycity.persistence.model.User;
import com.zhongli.happycity.persistence.model.VerificationToken;
import com.zhongli.happycity.validation.EmailExistsException;

@Service
@Transactional
public class UserService implements IUserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private VerificationTokenRepository tokenRepository;

    @Autowired
    private PasswordResetTokenRepository passwordTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    // API

    @Override
    public User registerNewUserAccount(final UserDto accountDto) throws EmailExistsException {
        if (emailExist(accountDto.getEmail())) {
            throw new EmailExistsException("There is an account with that email adress: " + accountDto.getEmail());
        }
        final User user = new User(accountDto.getEmail(),passwordEncoder.encode(accountDto.getPassword()),Arrays.asList(roleRepository.findByName("ROLE_USER")));

        user.setFirstName(accountDto.getFirstName());
        user.setLastName(accountDto.getLastName());
        return repository.save(user);
    }

    @Override
    public User getUser(final String verificationToken) {
        final User user = tokenRepository.findByToken(verificationToken).getUser();
        return user;
    }

    @Override
    public VerificationToken getVerificationToken(final String VerificationToken) {
        return tokenRepository.findByToken(VerificationToken);
    }

    @Override
    public void saveRegisteredUser(final User user) {
        repository.save(user);
    }

    @Override
    public void deleteUser(final User user) {
        repository.delete(user);
    }

    @Override
    public void createVerificationTokenForUser(final User user, final String token) {
        final VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }

    @Override
    public VerificationToken generateNewVerificationToken(final String existingVerificationToken) {
        VerificationToken vToken = tokenRepository.findByToken(existingVerificationToken);
        vToken.updateToken(UUID.randomUUID().toString());
        vToken = tokenRepository.save(vToken);
        return vToken;
    }

    @Override
    public void createPasswordResetTokenForUser(final User user, final String token) {
        final PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordTokenRepository.save(myToken);
    }

    @Override
    public User findUserByEmail(final String email) {
        return repository.findByEmail(email);
    }

    @Override
    public PasswordResetToken getPasswordResetToken(final String token) {
        return passwordTokenRepository.findByToken(token);
    }

    @Override
    public User getUserByPasswordResetToken(final String token) {
        return passwordTokenRepository.findByToken(token).getUser();
    }

    @Override
    public User getUserByID(final long id) {
        return repository.findOne(id);
    }

    @Override
    public void changeUserPassword(final User user, final String password) {
        user.setPassword(passwordEncoder.encode(password));
        repository.save(user);
    }

    @Override
    public boolean checkIfValidOldPassword(final User user, final String oldPassword) {
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }

    private boolean emailExist(final String email) {
        final User user = repository.findByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }

}