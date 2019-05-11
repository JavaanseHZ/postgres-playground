package de.contract.data.repository;

import de.contract.model.rental.RentalContract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RentalContractRepository extends JpaRepository<RentalContract, UUID> {
}
