/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityClasses;

import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Tarun
 */
@ManagedBean
public class Register {

    private String email, password1, password2, firstName, lastName, city, state, zipcode, telephone;
    private String creationDate, ccNumber, rating;
    private String ssnNumber, startDate, hourlyRate, empType;
    
    boolean isParamValid;

    /**
     * Creates a new instance of Register
     */
    public Register() {
        empType = "rep";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSsnNumber() {
        return ssnNumber;
    }

    public void setSsnNumber(String ssnNumber) {
        this.ssnNumber = ssnNumber;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(String hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public String getEmpType() {
        return empType;
    }

    public void setEmpType(String empType) {
        this.empType = empType;
    }
    
    public String validateUserRegistrationInput() {
        
        System.out.println("Inside validateRegistrationInput");
        // Uncomment this check after testing is done.
        
//        if (email==null || password1==null || password2==null || firstName==null
//                || lastName==null || city==null || state==null
//                || zipcode==null || telephone==null) {
//            System.out.println("Something not entered");
//            FacesContext.getCurrentInstance().addMessage(
//                null,
//                new FacesMessage(FacesMessage.SEVERITY_WARN,
//                    "All fields marked with red asterisk are mandatory",
//                    "Please enter all fields"));
//                return "register_user";
//        }
        
        if (!password1.equals(password2)) {
            System.out.println("Password does not match");
            FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Password does not match",
                    "Please enter same password values"));
            return "register_user";
        }

        if (telephone.equals("")) {
            telephone = "0";
        }
        
        if (ccNumber.equals("")) {
            ccNumber = "0";
        }
        
        isParamValid = RegisterDAO.register_fmuser(email, password1, firstName, lastName,
                city, state, zipcode, telephone, ccNumber);
        
        if (isParamValid) {
            FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "User registered successfully.",
                    "Success"));
            return "index";
        } else {
            return "register_user";
        }
    }
    
    public String validateEmpRegistrationInput() {
        
        System.out.println("Inside validateEmpRegistrationInput");
        // Uncomment this check after testing is done.
        
//        if (email==null || password1==null || password2==null || firstName==null
//                || lastName==null || city==null || state==null
//                || zipcode==null || telephone==null || ssnNumber==null) {
//            System.out.println("Something not entered");
//            FacesContext.getCurrentInstance().addMessage(
//                null,
//                new FacesMessage(FacesMessage.SEVERITY_WARN,
//                    "All fields marked with red asterisk are mandatory",
//                    "Please enter all fields"));
//                return "register_emp";
//        }

        if (!password1.equals(password2)) {
            System.out.println("Password does not match");
            FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Password does not match",
                    "Please enter same password values"));
            return "register_emp";
        }

        if (telephone.equals("")) {
            telephone = "0";
        }
        
        if (hourlyRate.equals("")) {
            hourlyRate = "0";
        }
        
        isParamValid = RegisterDAO.register_emp(email, password1, firstName, lastName,
                city, state, zipcode, telephone, ssnNumber, hourlyRate, empType);
        
        if (isParamValid) {
            FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Employee registered successfully.",
                    "Success"));
            return "index";
        } else {
            return "register_emp";
        }
    }
} 
