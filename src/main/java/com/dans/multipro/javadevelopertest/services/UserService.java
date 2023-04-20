@Service
public class UserService implements UserDetailsService {

   private final UserRepository userRepository;

   @Autowired
   public UserService(UserRepository userRepository) {
       this.userRepository = userRepository;
   }

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       return userRepository.findByUsername(username)
               .orElseThrow(() -> new UsernameNotFoundException("User not found"));
   }
}
