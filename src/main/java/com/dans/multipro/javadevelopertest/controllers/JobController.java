@RestController
@RequestMapping("/api")
public class JobController {

   private final JobService jobService;
   private final JwtUtil jwtUtil;

   @Autowired
   public JobController(JobService jobService, JwtUtil jwtUtil) {
       this.jobService = jobService;
       this.jwtUtil = jwtUtil;
   }

   @GetMapping("/jobs")
   public ResponseEntity<List<Job>> getJobs(@RequestHeader("Authorization") String authorizationHeader) {
       String token = authorizationHeader.substring(7);
       String username = jwtUtil.extractUsername(token);

       if (!jwtUtil.validateToken(token, new User(username, "", new ArrayList<>()))) {
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
       }

       List<Job> jobs = jobService.getJobs();
       return ResponseEntity.ok(jobs);
   }
}
