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

    private String email, password1, password2, firstName, lastName, address, city, state, zipcode, telephone, userType;
    private String creationDate, ccNumber, rating;
    private String ssnNumber, startDate, hourlyRate, empType;
    
    boolean isParamValid;

    /**
     * Creates a new instance of Register
     */
    public Register() {
        userType = "user";
        empType = "rep";
    }
    
    public String page()
    {
        return "register";
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
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
    
    public String validateRegistrationInput() {
        
        System.out.println("Inside validateRegistrationInput");
        // Uncomment this check after testing is done.
        
//        if (email==null || password1==null || password2==null || firstName==null
//                || lastName==null || address==null || city==null || state==null
//                || zipcode==null || telephone==null) {
//            System.out.println("Something not entered");
//            FacesContext.getCurrentInstance().addMessage(
//                null,
//                new FacesMessage(FacesMessage.SEVERITY_WARN,
//                    "All fields marked with red asterisk are mandatory",
//                    "Please enter all fields"));
//        }
        
        if (!password1.equals(password2)) {
            System.out.println("Password does not match");
            FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Password does not match",
                    "Please enter same password values"));
            return "register";
        }
        
        try {
            Integer.parseInt(zipcode);
        } catch (NumberFormatException e) {
            FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Zipcode should only contain numbers.",
                    "Zipcode not numeric"));
            return "register";
        }
        
        try {
            if (!telephone.equals(""))
                Integer.parseInt(telephone);
        } catch (NumberFormatException e) {
            FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Telephone number should only contain numbers.",
                    "Telephone number not numeric"));
            return "register";
        }
        
        try {
            if (!ccNumber.equals(""))
                Integer.parseInt(ccNumber);
        } catch (NumberFormatException e) {
            FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Credit card number should only contain numbers.",
                    "Credit Card number not numeric"));
            return "register";
        }
        
        System.err.println("User Type : " + userType);
        if (userType.equals("emp")) {
            return "register_emp";
        }
        
        isParamValid = RegisterDAO.register_fmuser(email, password1, firstName, lastName,
                address, city, state, zipcode, telephone, ccNumber, userType);
        
        if (isParamValid) {
            FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "User registered successfully.",
                    "Success"));
            return "index";
        } else {
            return "register";
        }
    }
    
    public String validateEmpRegistrationInput() {
        
        System.out.println("Inside validateEmpRegistrationInput");
        
        if (ssnNumber==null) {
            System.out.println("SSN Number not entered");
            FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "All fields marked with red asterisk are mandatory",
                    "Please enter all fields"));
        }
        
        try {
                Integer.parseInt(ssnNumber);
        } catch (NumberFormatException e) {
            FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "SSN Number should only contain numbers.",
                    "SSN Number not numeric"));
            return "register_emp";
        }
        
        try {
            if (!hourlyRate.equals(""))
                Integer.parseInt(hourlyRate);
        } catch (NumberFormatException e) {
            FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Hourly rate should only contain numbers.",
                    "Hourly rate not numeric"));
            return "register_emp";
        }
        
        System.out.println("Email");
        isParamValid = RegisterDAO.register_emp(email, password1, firstName, lastName,
                address, city, state, zipcode, telephone, userType, ssnNumber, hourlyRate, empType);
        
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
