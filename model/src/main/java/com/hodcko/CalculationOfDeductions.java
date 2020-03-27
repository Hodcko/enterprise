package com.hodcko;

public class CalculationOfDeductions {
    private double salary;
    private double fszn;
    private double insurance;
    private double incomeTax;
    private double unoinDeductions;
    private double amountToBePaid;


    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setFszn(double percent) {
        this.fszn = ((salary * percent)/100);
    }

    public void setInsurance(double percent) {
        this.insurance = ((salary * percent)/100);
    }

    public void setIncomeTax(double percent) {
        this.incomeTax = ((salary * percent)/100);
    }

    public void setUnoinDeductions(double percent) {
        this.unoinDeductions = ((salary * percent)/100);
    }

    public void setAmountToBePaid() {
        this.amountToBePaid = (this.salary - getFszn() - getIncomeTax() - getInsurance() - getUnoinDeductions());
    }

    public double getFszn() {
        return fszn;
    }

    public double getInsurance() {
        return insurance;
    }

    public double getIncomeTax() {
        return incomeTax;
    }

    public double getUnoinDeductions() {
        return unoinDeductions;
    }

    public double getAmountToBePaid() {
        return amountToBePaid;
    }
}
