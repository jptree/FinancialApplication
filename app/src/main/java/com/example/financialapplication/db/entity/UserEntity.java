package com.example.financialapplication.db.entity;

import java.util.Calendar;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_information_table")
public class UserEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String firstName;
    private String lastName;
    private int Age;
    private Calendar dayOfBirth;
    private boolean isLoggedIn;
    private boolean hasDebt;
    // do relation with generic debt type; create n debts for what user specifies.
    private boolean hasPaidResidence;  // determines whether the user pays for living in a residence
    // do relation with generic house type; create n residences for what user specifies.
    private boolean isMarried;
    private int quantityKids;  // quantity of total kids; potentially pay alimony for some kids here?
    private int quantityKidsDependent;  // quantity of kids that you directly support in household
    private float annualIncome;
    // do relation with generic income type; create n income sources for what user specifies.
    private String region;
    private int zipCode;
    private boolean isEmployed;
    private boolean has401kPlan;
    private boolean hasPensionPlan;
    private boolean hasInsurancePolicy;
    // do relation with generic insurance type; create n insurance policies for what user specifies.
    private boolean hasInvestments;
    // do relation with generic investment type; create n investment accounts for what user specifies.

    public UserEntity(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public Calendar getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(Calendar dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public boolean isHasDebt() {
        return hasDebt;
    }

    public void setHasDebt(boolean hasDebt) {
        this.hasDebt = hasDebt;
    }

    public boolean isHasPaidResidence() {
        return hasPaidResidence;
    }

    public void setHasPaidResidence(boolean hasPaidResidence) {
        this.hasPaidResidence = hasPaidResidence;
    }

    public boolean isMarried() {
        return isMarried;
    }

    public void setMarried(boolean married) {
        isMarried = married;
    }

    public int getQuantityKids() {
        return quantityKids;
    }

    public void setQuantityKids(int quantityKids) {
        this.quantityKids = quantityKids;
    }

    public int getQuantityKidsDependent() {
        return quantityKidsDependent;
    }

    public void setQuantityKidsDependent(int quantityKidsDependent) {
        this.quantityKidsDependent = quantityKidsDependent;
    }

    public float getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(float annualIncome) {
        this.annualIncome = annualIncome;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public boolean isEmployed() {
        return isEmployed;
    }

    public void setEmployed(boolean employed) {
        isEmployed = employed;
    }

    public boolean isHas401kPlan() {
        return has401kPlan;
    }

    public void setHas401kPlan(boolean has401kPlan) {
        this.has401kPlan = has401kPlan;
    }

    public boolean isHasPensionPlan() {
        return hasPensionPlan;
    }

    public void setHasPensionPlan(boolean hasPensionPlan) {
        this.hasPensionPlan = hasPensionPlan;
    }

    public boolean isHasInsurancePolicy() {
        return hasInsurancePolicy;
    }

    public void setHasInsurancePolicy(boolean hasInsurancePolicy) {
        this.hasInsurancePolicy = hasInsurancePolicy;
    }

    public boolean isHasInvestments() {
        return hasInvestments;
    }

    public void setHasInvestments(boolean hasInvestments) {
        this.hasInvestments = hasInvestments;
    }
}
