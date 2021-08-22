//package sample;

public class Calculation {
    private double loanAmount;			//amountOfLoan
    private int numOfYears;				//compound
    private double interestRate;		//interestRate
    private double months;
    private double monthlyCompound;

    public double monthlyCompound() {
        int yearsToMonths = (this.numOfYears * 12);
        setMonths(yearsToMonths);
        double interest = ( (this.interestRate / 12) / 100 );
        double numerator = ( interest * this.getLoanAmount() );
        //double denominator = (1 / ((1 + interest)^24));
        double denominator =  1 - (1 / (Math.pow((1 + interest), yearsToMonths)));
        double calculate = ( numerator / denominator );
        setMonthlyCompound(calculate);
        return calculate;	 //8.34/(1-((1+0.00417)^-24))
    }

    public double totalPayment() {
        double var1 = (double) getMonths();
        double var2 = getMonthlyCompound();
        return (var1 * var2);
    }

    public double getMonthlyCompound() {
        return monthlyCompound;
    }

    public void setMonthlyCompound(double compound) {
        this.monthlyCompound = compound;
    }

    public void setMonths(double months) {
        this.months = months;
    }

    public double getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public int getNumOfYears() {
        return numOfYears;
    }

    public void setNumOfYears(int numOfYears) {
        this.numOfYears = numOfYears;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

}
