package de.contract.model.insurance;

import de.contract.model.AbstractTerms;

public class InsuranceTerms extends AbstractTerms {
    private double premium;
    private String type;

    public double getPremium() {
        return premium;
    }

    public void setPremium(double premium) {
        this.premium = premium;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
