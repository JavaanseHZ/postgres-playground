package de.contract.endpoint;

import de.contract.data.repository.InsuranceContractRepository;
import de.contract.model.insurance.InsuranceContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/contract/insurance")
public class InsuranceContractRestController {

    private final InsuranceContractRepository insuranceContractRepository;

    @Autowired
    InsuranceContractRestController(InsuranceContractRepository insuranceContractRepository) {
        this.insuranceContractRepository = insuranceContractRepository;
    }

    @GetMapping
    @ResponseBody
    public List<InsuranceContract> getAll() {
        return insuranceContractRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<InsuranceContract> getById(@PathVariable String id) {
        return insuranceContractRepository.findById(UUID.fromString(id))
            .map(insuranceContract ->
                new ResponseEntity<>(insuranceContract, HttpStatus.OK)
            )
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<InsuranceContract> create(@RequestBody InsuranceContract insuranceContract) {
        insuranceContractRepository.save(insuranceContract);
        return new ResponseEntity<>(insuranceContract, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable String id, @RequestBody InsuranceContract insuranceContract) {
    //TODO Upsert?
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        return insuranceContractRepository.findById(UUID.fromString(id))
            .map(
                    foundContract -> {
                    insuranceContractRepository.delete(foundContract);
                    return new ResponseEntity(HttpStatus.OK);
                })
            .orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }
}