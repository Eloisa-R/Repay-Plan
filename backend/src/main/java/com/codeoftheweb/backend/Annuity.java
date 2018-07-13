package com.codeoftheweb.backend;


import org.hibernate.annotations.Columns;

import javax.persistence.*;
import java.util.*;

@Entity
public class Annuity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    private double loanAmount;
    private double nominalIR;
    private int duration;
    private Date startDate;
    private double annuity;

    @OneToMany(mappedBy = "annuityInstallment", fetch = FetchType.EAGER)
    private List<Installment> repaymentplan = new ArrayList<>();

    public Annuity(double loanAmount, double nominalIR, int duration, Date startDate) {

       this.loanAmount = loanAmount;
       this.nominalIR = nominalIR;
       this.duration = duration;
       this.startDate = startDate;
       setAnnuity();
    }

    public Annuity() {}

    public void setAnnuity() {
    // Divide the annual rate by 12 to get the monthly rate and by 100 to get the percentage in decimals
        double ratePerPeriod = this.nominalIR/1200;
        double annuityResult = this.loanAmount * (ratePerPeriod)/(1 - Math.pow(1 + ratePerPeriod, -this.duration));
        this.annuity = Math.round(annuityResult * 100.0) / 100.0;
    }

    public double getAnnuity() {
        return annuity;
    }

    public int getDuration() {
        return duration;
    }

    public Date getStartDate() {
        return startDate;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public double getNominalIR() {
        return nominalIR;
    }

    public void addInstallment(Installment installment) {
        installment.setAnnuityInstallment(this);
        repaymentplan.add(installment);
    }

    public List<Installment> getRepaymentplan() {
        return repaymentplan;
    }

}
