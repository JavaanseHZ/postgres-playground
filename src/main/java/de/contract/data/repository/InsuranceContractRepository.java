package de.contract.data.repository;

import de.contract.model.insurance.InsuranceContract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InsuranceContractRepository extends JpaRepository<InsuranceContract, UUID> {
}
