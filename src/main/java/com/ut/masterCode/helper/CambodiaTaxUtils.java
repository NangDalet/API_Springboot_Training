package com.ut.masterCode.helper;

public class CambodiaTaxUtils {

    //? Return USD
    public static Float SalaryTaxCalculateUSD(float totalSalary, double exchangeRate, long taxExemptionForFamily) {
        double taxAmount;

        totalSalary = new Float(String.format("%.2f" , totalSalary));
        double condition;
        // calculate salary include with tax and family tax
        if (taxExemptionForFamily > 0) {
            condition = (totalSalary * exchangeRate) - (taxExemptionForFamily * 150000);
        } else {
            condition = (totalSalary * exchangeRate);
        }

        condition = new Float(String.format("%.0f" , condition));
        // Formula of tax in Cambodia
       double totalTaxPaid = 0;

        if (condition > 0 && condition < 1500000) {
            totalTaxPaid = 0;
        }
        if (condition > 1500001 && condition < 2000001) {
            totalTaxPaid = (condition * 0.05) - 75000;
        }
        if (condition > 2000001 && condition < 8500000) {
            totalTaxPaid = (condition * 0.10) - 175000;
        }
        if (condition > 8500001 && condition < 12500001) {
            totalTaxPaid = (condition * 0.15) - 600000;
        }
        if (condition > 12500000) {
            totalTaxPaid = (condition * 0.20) - 1225000;
        }

        // Exchange Riels to USD
        taxAmount = (new Float(String.format("%.0f", totalTaxPaid)) / exchangeRate);
        return new Float(String.format("%.2f", taxAmount));
    }


    //? Return KHR
    public static Float SalaryTaxCalculateVersionKHR(float totalSalaryKHR, long taxExemptionForFamily) {
        totalSalaryKHR = new Float(String.format("%.2f" , totalSalaryKHR));
        double condition;
        // calculate salary include with tax and family tax
        if (taxExemptionForFamily > 0) {
            condition = (totalSalaryKHR) - (taxExemptionForFamily * 150000);
        } else {
            condition = (totalSalaryKHR);
        }

        condition = new Float(String.format("%.0f" , condition));
        // Formula of tax in Cambodia
        double totalTaxPaid = 0;

        if (condition > 0 && condition < 1500000) {
            totalTaxPaid = 0;
        }
        if (condition > 1500001 && condition < 2000001) {
            totalTaxPaid = (condition * 0.05) - 75000;
        }
        if (condition > 2000001 && condition < 8500000) {
            totalTaxPaid = (condition * 0.10) - 175000;
        }
        if (condition > 8500001 && condition < 12500001) {
            totalTaxPaid = (condition * 0.15) - 600000;
        }
        if (condition > 12500000) {
            totalTaxPaid = (condition * 0.20) - 1225000;
        }


        return new Float(String.format("%.2f", totalTaxPaid));
    }

}
