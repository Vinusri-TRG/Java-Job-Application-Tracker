
package JobTrackerWeb;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.crypto.password.PasswordEncoder;

@Controller
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Display login page
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    // Display registration page
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    // Register a new user
    @PostMapping("/register")
    public String registerUser(
            @RequestParam String email,
            @RequestParam String password,
            Model model) {

        email = email.trim().toLowerCase();

        // Validate email
        if (email.isBlank() || !email.contains("@")) {
            model.addAttribute("error", "Enter a valid email address");
            model.addAttribute("email", email);
            return "register";
        }

        // Validate password
        if (password.length() < 8) {
            model.addAttribute("error",
                    "Password must contain at least 8 characters");
            model.addAttribute("email", email);
            return "register";
        }

        // Check duplicate email
        if (userRepository.existsByEmail(email)) {
            model.addAttribute("error", "Email already registered");
            model.addAttribute("email", email);
            return "register";
        }

        // Create new user
        User user = new User();

        user.setEmail(email);

        // Hash password using BCrypt
        user.setPassword(passwordEncoder.encode(password));

        // Save user in database
        userRepository.save(user);

        // Redirect to login page
        return "redirect:/login?registered";
    }
}
