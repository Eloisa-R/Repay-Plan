package com.codeoftheweb.backend;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
public class Installment {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    int index;
    double annuity;
    Date startDate;
    double loanAmount;
    double interest;
    double principal;
    double remainPrinc;
    double nominalIR;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="installment")
    private Annuity annuityInstallment;


    public Installment(int index, double inputAnnuity, Date inputdate, double inpInitPrinc, double inputNominalIR) {
        this.startDate = inputdate;
        this.index = index;
        this.annuity = inputAnnuity;
        this.loanAmount = inpInitPrinc;
        this.nominalIR = inputNominalIR;
        calcInterest();
        calcPrincipal();
        calcAnnuity();
        calcRemainPrinc();
        calcStartDate();
    }

    public Installment() {

    }

    public void calcStartDate() {
        Calendar myCal = Calendar.getInstance();
        myCal.setTime(this.startDate);
        myCal.add(Calendar.MONTH, + this.index);
        this.startDate = myCal.getTime();

    }

    public void calcInterest(){
        this.interest = Math.round((((this.nominalIR * 30 * this.loanAmount) / 360) / 100) * 100.0) / 100.0;
    }

    public void calcPrincipal(){
        this.principal = this.annuity - this.interest > this.loanAmount? this.loanAmount: Math.round((this.annuity - this.interest) * 100.0) / 100.0;
    }

    public void calcAnnuity(){
        this.annuity = this.annuity - this.interest > this.loanAmount? Math.round((this.principal + this.interest) * 100.0) / 100.0: this.annuity;
    }

    public void calcRemainPrinc(){
        this.remainPrinc = Math.round((this.loanAmount - this.principal) * 100.0) / 100.0;

    }

    public void setPrincipal(double principal) {
        this.principal = principal;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public double getRemainPrinc() {
        return remainPrinc;
    }

    public void setAnnuityInstallment(Annuity annuityInstallment) {
        this.annuityInstallment = annuityInstallment;
    }

    public int getIndex() {
        return index;
    }

    public double getAnnuity() {
        return annuity;
    }

    public Date getStartDate() {
        return startDate;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public double getInterest() {
        return interest;
    }

    public double getPrincipal() {
        return principal;
    }

    public double getNominalIR() {
        return nominalIR;
    }
}
