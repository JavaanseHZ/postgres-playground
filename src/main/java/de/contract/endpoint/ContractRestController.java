package de.contract.endpoint;

import de.contract.data.repository.ContractRepository;
import de.contract.model.Contract;
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
    public List<Contract> getAll() {
        return contractRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Contract> getById(@PathVariable String id) {
        return contractRepository.findById(UUID.fromString(id))
            .map(contract ->
                new ResponseEntity<>(contract, HttpStatus.OK)
            )
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Contract> create(@RequestBody Contract contract) {
        if(contract.getId() == null) {
            contract.setId(UUID.randomUUID());
            contractRepository.save(contract);
            return new ResponseEntity<>(contract, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable String id, @RequestBody Contract contract) {
        if(contract.getId() != null) {
            return contractRepository.findById(UUID.fromString(id))
                .map(foundContract -> {
                    foundContract.setLastname(contract.getLastname());
                    foundContract.setFirstname(contract.getFirstname());
                    foundContract.setPremium(contract.getPremium());
                    foundContract.setType(contract.getType());
                    contractRepository.save(foundContract);
                    return new ResponseEntity(HttpStatus.OK);
                })
                .orElse(
                    new ResponseEntity<>(HttpStatus.NOT_FOUND)
                );
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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