package com.zhongli.happycity.persistence.service;

import com.zhongli.happycity.persistence.model.PasswordResetToken;
import com.zhongli.happycity.persistence.model.User;
import com.zhongli.happycity.persistence.model.VerificationToken;
import com.zhongli.happycity.validation.EmailExistsException;

public interface IUserService {

    User registerNewUserAccount(UserDto accountDto) throws EmailExistsException;

    User getUser(String verificationToken);

    void saveRegisteredUser(User user);

    void deleteUser(User user);

    void createVerificationTokenForUser(User user, String token);

    VerificationToken getVerificationToken(String VerificationToken);

    VerificationToken generateNewVerificationToken(String token);

    void createPasswordResetTokenForUser(User user, String token);

    User findUserByEmail(String email);

    PasswordResetToken getPasswordResetToken(String token);

    User getUserByPasswordResetToken(String token);

    User getUserByID(long id);

    void changeUserPassword(User user, String password);

    boolean checkIfValidOldPassword(User user, String password);

}
