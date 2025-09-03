//package authentication;
//
//import authentication.dtos.JwtResponse;
//import authentication.dtos.LoginRequest;
//import authentication.dtos.RegisterRequest;
//import edu.matrix.co.services.authentication.AuthenticationService;
//import exceptions.EduMatrixGenericException;
//
//import java.util.stream.Collectors;
//
//public class AuthenticationServiceImpl implements AuthenticationService {
//    @Override
//    public void register(RegisterRequest req) {
//
//    }
//
//    @Override
//    public JwtResponse login(LoginRequest req) {
//        var u = userRepository.findByEmail(req.email).orElseThrow(() -> new EduMatrixGenericException("Invalid credentials"));
//        if (!encoder.matches(req.password, u.getPassword())) {
//            throw new EduMatrixGenericException("Invalid credentials");
//        }
//        var roles = u.getRoles().stream().map(Enum::name).collect(Collectors.toList());
//        String token = jwt.generateJwt(u.getEmail(), roles);
//        return new JwtResponse(token, u.getEmail(), roles);
//    }
//}
