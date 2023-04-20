@RestController
@RequestMapping("/api")
public class JobDetailController {

   private final JobDetailService jobDetailService;
   private final JwtUtil jwtUtil;

   @Autowired
   public JobDetailController(JobDetailService jobDetailService, JwtUtil jwtUtil) {
       this.jobDetailService = jobDetailService;
       this.jwtUtil = jwtUtil;
   }

   @GetMapping("/jobs/{jobId}")
   public ResponseEntity<JobDetail> getJobDetail(@PathVariable String jobId, @RequestHeader("Authorization") String authorizationHeader) {
       String token = authorizationHeader.substring(7);
       String username = jwtUtil.extractUsername(token);

       if (!jwtUtil.validateToken(token, new User(username, "", new ArrayList<>()))) {
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
       }

       JobDetail jobDetail = jobDetailService.getJobDetail(jobId);
       return ResponseEntity.ok(jobDetail);
   }
}
