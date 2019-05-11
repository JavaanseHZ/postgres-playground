package de.contract.model;

import java.io.Serializable;
import java.time.LocalDate;

public class AbstractTerms implements Serializable {

    protected LocalDate duration;

    public LocalDate getDuration() {
        return duration;
    }

    public void setDuration(LocalDate duration) {
        this.duration = duration;
    }

}
