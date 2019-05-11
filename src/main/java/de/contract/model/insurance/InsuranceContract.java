package de.contract.model.insurance;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import com.fasterxml.jackson.databind.ser.std.UUIDSerializer;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import de.contract.model.client.Client;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name= "contracts")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class InsuranceContract implements Serializable {

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private InsuranceTerms terms;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private Client client;

    @Basic(optional = false)
    @Column(name = "ts", insertable = false, updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ts;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "uuid", columnDefinition = "UUID")
    @Type(type = "pg-uuid")
    @JsonSerialize(using= UUIDSerializer.class)
    @JsonDeserialize(using= UUIDDeserializer.class)
    private UUID id;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public InsuranceTerms getTerms() {
        return terms;
    }

    public void setTerms(InsuranceTerms terms) {
        this.terms = terms;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
