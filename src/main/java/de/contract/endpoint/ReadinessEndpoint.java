package de.contract.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Endpoint(id="readiness")
@Component
public class ReadinessEndpoint {

    private ResponseEntity response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    private static Map<String, String> readyStatus = Collections.singletonMap("status", "ok");

    public ReadinessEndpoint() {
        ScheduledExecutorService scheduled =
                Executors.newSingleThreadScheduledExecutor();
        scheduled.schedule(() -> {
            response = new ResponseEntity<>(readyStatus, HttpStatus.OK);
        }, 20, TimeUnit.SECONDS);

    }

    @ReadOperation
    public ResponseEntity readiness(){
        return response;
    }
}