@Service
public class JobDetailService {

   private final RestTemplate restTemplate;

   public JobDetailService(RestTemplate restTemplate) {
       this.restTemplate = restTemplate;
   }

   public JobDetail getJobDetail(String jobId) {
       String url = String.format("http://dev3.dansmultipro.co.id/api/recruitment/positions/%s", jobId);
       ResponseEntity<JobDetail> response = restTemplate.getForEntity(url, JobDetail.class);
       return response.getBody();
   }
}
