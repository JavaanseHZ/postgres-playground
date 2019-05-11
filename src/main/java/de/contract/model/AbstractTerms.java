package de.contract.model;

import java.time.LocalDate;

public class AbstractTerms {

    protected LocalDate duration;

    public LocalDate getDuration() {
        return duration;
    }

    public void setDuration(LocalDate duration) {
        this.duration = duration;
    }

}
