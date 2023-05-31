package raf.webProgramiranje.services.implementations;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.apache.commons.codec.digest.DigestUtils;
import raf.webProgramiranje.entities.User;
import raf.webProgramiranje.exceptions.UserInactiveException;
import raf.webProgramiranje.repositories.specifications.UserRepository;
import raf.webProgramiranje.services.UserService;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

public class UserServiceImpl implements UserService {
    @Inject
    private UserRepository userRepository;
    @Override
    public User addUser(User user) {
        return userRepository.addUser(user);
    }

    @Override
    public List<User> getAllUsers() {
       return userRepository.getAllUsers();
    }
    @Override
    public User getUser(Integer id){
        return userRepository.getUser(id);
    }
    public User changeUserStatus(User user){
        return userRepository.changeUserStatus(user);
    }



    public String login(String email, String password)
    {
        String hashedPassword = DigestUtils.sha256Hex(password);

        User user = this.userRepository.findUser(email);
        if (user == null || !user.getPassword().equals(hashedPassword)) {
            return null;
        }
        if(!user.isUserStatus()){
            throw new UserInactiveException("User with email:"+email+" is inactive, check with admin");
        }

        Date issuedAt = new Date();
        Date expiresAt = new Date(issuedAt.getTime() + 24*60*60*1000); // One day

        Algorithm algorithm = Algorithm.HMAC256("secret");

        // JWT-om mozete bezbedono poslati informacije na FE
        // Tako sto sve sto zelite da posaljete zapakujete u claims mapu
        return JWT.create()
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .withSubject(email)
                .withClaim("userType", user.getUserType())
                .withClaim("firstName",user.getFirstName())
                .withClaim("id",user.getId())
                .sign(algorithm);
    }

    public boolean isAuthorized(String token){
        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        DecodedJWT jwt = verifier.verify(token);
        System.out.println(token);
        String email = jwt.getSubject();
        jwt.getClaim("userType").asString();

        User user = this.userRepository.findUser(email);

        if (user == null){
            return false;
        }

        return true;
    }

    @Override
    public boolean isAuthorizedAdmin(String token) {
        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        System.out.println(token);
        DecodedJWT jwt = verifier.verify(token);

        String email = jwt.getSubject();
       Integer userType= jwt.getClaim("userType").asInt();

        User user = this.userRepository.findUser(email);

        return user != null && userType != 1;
    }


    @Override
    public User changeUser(User user) {
        return userRepository.changeUser(user);
    }
}
