package de.contract.endpoint;

import de.contract.data.repository.RentalContractRepository;
import de.contract.model.insurance.InsuranceContract;
import de.contract.model.rental.RentalContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/contract/rental")
public class RentalContractRestController {

    private final RentalContractRepository rentalContractRepository;

    @Autowired
    RentalContractRestController(RentalContractRepository rentalContractRepository) {
        this.rentalContractRepository = rentalContractRepository;
    }

    @GetMapping
    @ResponseBody
    public List<RentalContract> getAll() {
        return rentalContractRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RentalContract> getById(@PathVariable String id) {
        return rentalContractRepository.findById(UUID.fromString(id))
            .map(rentalContract ->
                new ResponseEntity<>(rentalContract, HttpStatus.OK)
            )
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<RentalContract> create(@RequestBody RentalContract rentalContract) {
        rentalContractRepository.save(rentalContract);
        return new ResponseEntity<>(rentalContract, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable String id, @RequestBody InsuranceContract insuranceContract) {
    //TODO Upsert?
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        return rentalContractRepository.findById(UUID.fromString(id))
            .map(
                    foundContract -> {
                    rentalContractRepository.delete(foundContract);
                    return new ResponseEntity(HttpStatus.OK);
                })
            .orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }
}