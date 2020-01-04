package com.example.financialapplication.db.entity;

import java.util.Calendar;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "expense_table")
public class ExpenseEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String periodicity;
    private Calendar date;
    private boolean historicSet; // will be used to determine, when the date has passed, if this row should contain historic data.


    // Home/Utilities
    @ColumnInfo(name = "Cable/Satellite Services")
    private double cableSatellite;

    @ColumnInfo(name = "Home Improvement")
    private double homeImprovement;

    @ColumnInfo(name = "Home Maintenance")
    private double homeMaintenance;

    @ColumnInfo(name = "Mortgages")
    private double mortgages;

    @ColumnInfo(name = "Rent")
    private double rent;

    @ColumnInfo(name = "Telephone Services")
    private double telephoneServices;

    @ColumnInfo(name = "Utilities")
    private double utilities;


    // Transportation
    @ColumnInfo(name = "Automotive Expenses")
    private double automotive;

    @ColumnInfo(name = "Gasoline/Fuel")
    private double gasFuel;

    @ColumnInfo(name = "Car Payments")
    private double carPayments;

    @ColumnInfo(name = "Public Transportation")
    private double publicTransportation;


    // Groceries
    @ColumnInfo(name = "Groceries")
    private double groceries;


    // Personal/Family Care
    @ColumnInfo(name = "Child/Dependent Expenses")
    private double childDependent;

    @ColumnInfo(name = "Personal Care")
    private double personalCare;

    @ColumnInfo(name = "Pets/Pet Care")
    private double petCare;


    // Health
    @ColumnInfo(name = "Healthcare/Medical")
    private double healthcareMedical;

    @ColumnInfo(name = "Fitness/Health Club Membership")
    private double fitnessHealthClubMembership;

    @ColumnInfo(name = "Health Insurance")
    private double healthInsurance;


    // Insurance
    @ColumnInfo(name = "Insurance")
    private double insurance;


    // Restaurants/Dining
    @ColumnInfo(name = "Restaurants/Dining")
    private double restaurants;


    // Shopping/Entertainment
    @ColumnInfo(name = "Electronics")
    private double electronics;

    @ColumnInfo(name = "Clothing/Shoes")
    private double clotingShoes;

    @ColumnInfo(name = "Entertainment")
    private double entertainment;

    @ColumnInfo(name = "General Merchandise")
    private double generalMerchandise;

    @ColumnInfo(name = "Gifts")
    private double gifts;

    @ColumnInfo(name = "Hobbies")
    private double hobbies;

    @ColumnInfo(name = "Online Services")
    private double onlineServices;


    // Travel -- this probably just gets expensed at user's whim
    @ColumnInfo(name = "Travel")
    private double travel;

    // Cash/Checks/Misc.
    @ColumnInfo(name = "ATM/Cash Withdrawals")
    private double ATMCashWithdrawals;

    @ColumnInfo(name = "Checks")
    private double checks;

    @ColumnInfo(name = "Other Bills")
    private double otherBills;

    @ColumnInfo(name = "Other Expenses")
    private double otherExpenses;


    // Giving
    @ColumnInfo(name = "Charitable Giving")
    private double charitableGiving;


    // Business Expenses
    @ColumnInfo(name = "Advertising")
    private double advertising;

    @ColumnInfo(name = "Business Miscellaneous")
    private double businessMisc;

    @ColumnInfo(name = "Dues and Subscriptions")
    private double duesSubscriptions;

    @ColumnInfo(name = "Office Maintenance")
    private double officeMaintenance;

    @ColumnInfo(name = "Office Supplies")
    private double officeSupplies;

    @ColumnInfo(name = "Postage and Shipping")
    private double postageShipping;

    @ColumnInfo(name = "Printing")
    private double printing;

    @ColumnInfo(name = "Wages Paid")
    private double wagesPaid;


    // Education
    @ColumnInfo(name = "Education")
    private double education;


    // Finance
    @ColumnInfo(name = "Loans")
    private double loans;

    @ColumnInfo(name = "Service Charges/Fees")
    private double serviceChargesFees;

    @ColumnInfo(name = "Taxes")
    private double taxes;

    @ColumnInfo(name = "Credit Card Payments")
    private double creditCardPayments;



    public ExpenseEntity(Calendar date, String periodicity) {
        this.date = date;
        this.periodicity = periodicity;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public double getCableSatellite() {
        return cableSatellite;
    }

    public void setCableSatellite(double cableSatellite) {
        this.cableSatellite = cableSatellite;
    }

    public double getHomeImprovement() {
        return homeImprovement;
    }

    public void setHomeImprovement(double homeImprovement) {
        this.homeImprovement = homeImprovement;
    }

    public double getHomeMaintenance() {
        return homeMaintenance;
    }

    public void setHomeMaintenance(double homeMaintenance) {
        this.homeMaintenance = homeMaintenance;
    }

    public double getMortgages() {
        return mortgages;
    }

    public void setMortgages(double mortgages) {
        this.mortgages = mortgages;
    }

    public double getRent() {
        return rent;
    }

    public void setRent(double rent) {
        this.rent = rent;
    }

    public double getTelephoneServices() {
        return telephoneServices;
    }

    public void setTelephoneServices(double telephoneServices) {
        this.telephoneServices = telephoneServices;
    }

    public double getUtilities() {
        return utilities;
    }

    public void setUtilities(double utilities) {
        this.utilities = utilities;
    }

    public double getAutomotive() {
        return automotive;
    }

    public void setAutomotive(double automotive) {
        this.automotive = automotive;
    }

    public double getGasFuel() {
        return gasFuel;
    }

    public void setGasFuel(double gasFuel) {
        this.gasFuel = gasFuel;
    }

    public double getCarPayments() {
        return carPayments;
    }

    public void setCarPayments(double carPayments) {
        this.carPayments = carPayments;
    }

    public double getPublicTransportation() {
        return publicTransportation;
    }

    public void setPublicTransportation(double publicTransportation) {
        this.publicTransportation = publicTransportation;
    }

    public double getGroceries() {
        return groceries;
    }

    public void setGroceries(double groceries) {
        this.groceries = groceries;
    }

    public double getChildDependent() {
        return childDependent;
    }

    public void setChildDependent(double childDependent) {
        this.childDependent = childDependent;
    }

    public double getPersonalCare() {
        return personalCare;
    }

    public void setPersonalCare(double personalCare) {
        this.personalCare = personalCare;
    }

    public double getPetCare() {
        return petCare;
    }

    public void setPetCare(double petCare) {
        this.petCare = petCare;
    }

    public double getHealthcareMedical() {
        return healthcareMedical;
    }

    public void setHealthcareMedical(double healthcareMedical) {
        this.healthcareMedical = healthcareMedical;
    }

    public double getFitnessHealthClubMembership() {
        return fitnessHealthClubMembership;
    }

    public void setFitnessHealthClubMembership(double fitnessHealthClubMembership) {
        this.fitnessHealthClubMembership = fitnessHealthClubMembership;
    }

    public double getHealthInsurance() {
        return healthInsurance;
    }

    public void setHealthInsurance(double healthInsurance) {
        this.healthInsurance = healthInsurance;
    }

    public double getInsurance() {
        return insurance;
    }

    public void setInsurance(double insurance) {
        this.insurance = insurance;
    }

    public double getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(double restaurants) {
        this.restaurants = restaurants;
    }

    public double getElectronics() {
        return electronics;
    }

    public void setElectronics(double electronics) {
        this.electronics = electronics;
    }

    public double getClotingShoes() {
        return clotingShoes;
    }

    public void setClotingShoes(double clotingShoes) {
        this.clotingShoes = clotingShoes;
    }

    public double getEntertainment() {
        return entertainment;
    }

    public void setEntertainment(double entertainment) {
        this.entertainment = entertainment;
    }

    public double getGeneralMerchandise() {
        return generalMerchandise;
    }

    public void setGeneralMerchandise(double generalMerchandise) {
        this.generalMerchandise = generalMerchandise;
    }

    public double getGifts() {
        return gifts;
    }

    public void setGifts(double gifts) {
        this.gifts = gifts;
    }

    public double getHobbies() {
        return hobbies;
    }

    public void setHobbies(double hobbies) {
        this.hobbies = hobbies;
    }

    public double getOnlineServices() {
        return onlineServices;
    }

    public void setOnlineServices(double onlineServices) {
        this.onlineServices = onlineServices;
    }

    public double getTravel() {
        return travel;
    }

    public void setTravel(double travel) {
        this.travel = travel;
    }

    public double getATMCashWithdrawals() {
        return ATMCashWithdrawals;
    }

    public void setATMCashWithdrawals(double ATMCashWithdrawals) {
        this.ATMCashWithdrawals = ATMCashWithdrawals;
    }

    public double getChecks() {
        return checks;
    }

    public void setChecks(double checks) {
        this.checks = checks;
    }

    public double getOtherBills() {
        return otherBills;
    }

    public void setOtherBills(double otherBills) {
        this.otherBills = otherBills;
    }

    public double getOtherExpenses() {
        return otherExpenses;
    }

    public void setOtherExpenses(double otherExpenses) {
        this.otherExpenses = otherExpenses;
    }

    public double getCharitableGiving() {
        return charitableGiving;
    }

    public void setCharitableGiving(double charitableGiving) {
        this.charitableGiving = charitableGiving;
    }

    public double getAdvertising() {
        return advertising;
    }

    public void setAdvertising(double advertising) {
        this.advertising = advertising;
    }

    public double getBusinessMisc() {
        return businessMisc;
    }

    public void setBusinessMisc(double businessMisc) {
        this.businessMisc = businessMisc;
    }

    public double getDuesSubscriptions() {
        return duesSubscriptions;
    }

    public void setDuesSubscriptions(double duesSubscriptions) {
        this.duesSubscriptions = duesSubscriptions;
    }

    public double getOfficeMaintenance() {
        return officeMaintenance;
    }

    public void setOfficeMaintenance(double officeMaintenance) {
        this.officeMaintenance = officeMaintenance;
    }

    public double getOfficeSupplies() {
        return officeSupplies;
    }

    public void setOfficeSupplies(double officeSupplies) {
        this.officeSupplies = officeSupplies;
    }

    public double getPostageShipping() {
        return postageShipping;
    }

    public void setPostageShipping(double postageShipping) {
        this.postageShipping = postageShipping;
    }

    public double getPrinting() {
        return printing;
    }

    public void setPrinting(double printing) {
        this.printing = printing;
    }

    public double getWagesPaid() {
        return wagesPaid;
    }

    public void setWagesPaid(double wagesPaid) {
        this.wagesPaid = wagesPaid;
    }

    public double getEducation() {
        return education;
    }

    public void setEducation(double education) {
        this.education = education;
    }

    public double getLoans() {
        return loans;
    }

    public void setLoans(double loans) {
        this.loans = loans;
    }

    public double getServiceChargesFees() {
        return serviceChargesFees;
    }

    public void setServiceChargesFees(double serviceChargesFees) {
        this.serviceChargesFees = serviceChargesFees;
    }

    public double getTaxes() {
        return taxes;
    }

    public void setTaxes(double taxes) {
        this.taxes = taxes;
    }

    public double getCreditCardPayments() {
        return creditCardPayments;
    }

    public void setCreditCardPayments(double creditCardPayments) {
        this.creditCardPayments = creditCardPayments;
    }

    public boolean isHistoricSet() {
        return historicSet;
    }

    public void setHistoricSet(boolean historicSet) {
        this.historicSet = historicSet;
    }
}
