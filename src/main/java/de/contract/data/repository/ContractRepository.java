package de.contract.data.repository;

import de.contract.model.AbstractContract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContractRepository extends JpaRepository<AbstractContract, UUID> {
}
