package de.contract.model.rental;

import de.contract.model.AbstractTerms;
import de.contract.model.address.Address;

public class RentalTerms extends AbstractTerms {
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
