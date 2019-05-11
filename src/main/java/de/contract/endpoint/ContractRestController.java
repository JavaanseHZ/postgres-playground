package de.contract.endpoint;

import de.contract.data.repository.ContractRepository;
import de.contract.model.AbstractContract;
import de.contract.model.insurance.InsuranceContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/contract")
public class ContractRestController {

    private final ContractRepository contractRepository;

    @Autowired
    ContractRestController(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    @GetMapping
    @ResponseBody
    public List<AbstractContract> getAll() {
        return contractRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AbstractContract> getById(@PathVariable String id) {
        return contractRepository.findById(UUID.fromString(id))
            .map(abstractContract ->
                new ResponseEntity<>(abstractContract, HttpStatus.OK)
            )
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<AbstractContract> create(@RequestBody AbstractContract abstractContract) {
        contractRepository.save(abstractContract);
        return new ResponseEntity<>(abstractContract, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable String id, @RequestBody InsuranceContract insuranceContract) {
    //TODO Upsert?
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        return contractRepository.findById(UUID.fromString(id))
            .map(
                    foundContract -> {
                    contractRepository.delete(foundContract);
                    return new ResponseEntity(HttpStatus.OK);
                })
            .orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }
}