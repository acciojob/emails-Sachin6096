package com.driver;

public class Email {

    private String emailId;
    private String password;

    public Email() {
    }

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String oldPassword, String newPassword){
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character
        if(oldPassword == this.password)
        {
            //if(newPassword.contains())
            if(isPasswordValid(newPassword))
                this.password = newPassword;
            else
            {
                System.out.println("Please make sure that your password matches the following requirements /n" +
                        "It contains at least 8 characters /n" +
                        "It contains at least one uppercase letter /n" +
                        "It contains at least one lowercase letter /n" +
                        "It contains at least one special character. Any character apart from alphabets and digits is a special character");
            }

        }
        else System.out.println("Authentication Failed");
    }
    private boolean isPasswordValid(String password) {
        // Check if the new password contains at least one lowercase, one uppercase, one digit,
        // one special character, and has a length of at least 8 characters.
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
        return password.matches(regex);
    }
}
