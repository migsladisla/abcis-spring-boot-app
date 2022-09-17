package gateway;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status-check")
public class StatusController {
    @GetMapping("/health")
    public ResponseEntity<String> getHealthCheckStatus() {
        return new ResponseEntity<String>("UP", HttpStatus.valueOf(200));
    }
}
