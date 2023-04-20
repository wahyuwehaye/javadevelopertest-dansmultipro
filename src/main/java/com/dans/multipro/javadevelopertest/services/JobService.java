@Service
public class JobService {

   private final RestTemplate restTemplate;

   public JobService(RestTemplate restTemplate) {
       this.restTemplate = restTemplate;
   }

   public List<Job> getJobs() {
       ResponseEntity<Job[]> response = restTemplate.getForEntity("http://dev3.dansmultipro.co.id/api/recruitment/positions.json", Job[].class);
       return Arrays.asList(response.getBody());
   }
}
