package ru.kerporation.entities;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "loan_id")
    private String loanId;

    @NotNull
    @Column(name = "term")
    private int term;

    @NotNull
    @Column(name = "amount")
    private int amount;

    @Column(name = "country")
    private String countryCode;

    @Column(name = "added")
    private Date added;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;


    public Loan(int term, int amount) {
        this.term = term;
        this.amount = amount;
    }
}