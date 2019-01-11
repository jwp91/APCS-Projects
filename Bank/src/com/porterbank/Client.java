package com.porterbank;

import java.io.Serializable;
import java.util.Date;
import java.util.Scanner;

public class Client implements Serializable {
    private static final long serialVersionUID = -1115638323402749215L;
    String mUsername, mPassword;
    int mAge;
    double moneyInSavings=0;
    long dateSavingsCreated;

    public Client(){
        //Used only for instantiation purposes.
    }
    public Client(String username){
        mUsername=username;
        mAge=(int)getDoubleFromUser("Enter your current Age: ");
        while(mAge<18){
            System.out.println("Sorry! You must be older than 21. Try Again: ");
            mAge=(int)getDoubleFromUser("");
        }
        setPassword();
    }
    public String getUsername(){
        return mUsername;
    }
    private boolean passwordAlreadySet=false;
    public void setPassword(){
        if(passwordAlreadySet){
            System.out.println("Password already set. ");
        } else{
            String input1,input2="";
            Scanner in = new Scanner(System.in);
            do{
                input1 = getStringFromUser("Enter a new Password: ");
                input2 = getStringFromUser("Confirm your password: ");
                if(!input1.equals(input2)){
                    System.out.println("Error: Passwords do not match. Try again: ");
                }
            }while(!input1.equals(input2));
            passwordAlreadySet = true;
            mPassword = input1;
        }
    }
    public void login(){
        boolean correctPassword=false;
        Scanner in = new Scanner(System.in);
        System.out.println("[Press 'e' to exit] Enter Password: ");
        do{
            //System.out.println(password);
            String passwordAttempt = in.nextLine();
            if(passwordAttempt.equals(mPassword)){
                correctPassword=true;
            }else if(passwordAttempt.equalsIgnoreCase("e")){
                System.out.println("Bye! ");
                System.exit(0);
            }else{
                System.out.println("Incorrect Password! Try again: ");
            }
        }while(!correctPassword);
    }
    public boolean hasSimilarUserName(String username){
        int similarCombinations=0;
        int possibleCombinations=0;
        for(int a=0; a<=username.length()-2;a++){
            String charCombo1 = Character.toString(username.charAt(a))+Character.toString(username.charAt(a+1));
            boolean charComboMatch = false;
            for(int b=0; b<=mUsername.length()-2;b++){
                String charCombo2 = Character.toString(mUsername.charAt(b))+Character.toString(mUsername.charAt(b+1));
                if(charCombo1.equalsIgnoreCase(charCombo2)){
                    charComboMatch=true;
                }
            }
            if(charComboMatch){
                similarCombinations++;
            }
            possibleCombinations++;
        }
        double likelihoodSimilar=(double)similarCombinations/possibleCombinations;
        if(username.toLowerCase().contains(mUsername.toLowerCase())){
            likelihoodSimilar=.9;
        }
        //System.out.println(likelihoodSimilar);
        if(likelihoodSimilar>=.5&&getBooleanFromUser("Did you mean to type \""+mUsername+"\"?")){
            return true;
        }else{
            return false;
        }
    }
    public static String getStringFromUser(String promptText){
        Scanner in = new Scanner(System.in);
        System.out.println(promptText);
        String input = "";
        input = in.nextLine();
        while(input.trim().equals("")){
            System.out.println("Error: Blank field. Try Again: ");
            input = in.nextLine();
        }
        return input;
    }
    public static double getDoubleFromUser(String promptText){
        double result=0;
        boolean isValidInput=false;
        Scanner in = new Scanner(System.in);
        System.out.println(promptText);
        do{
            try{
                result = Double.parseDouble(in.nextLine());
                if(result>=0){
                    isValidInput=true;
                }else{
                    System.out.println("Input must be positive. Try again: ");
                }
            } catch(NumberFormatException nfe){
                System.out.println("Invalid input type. Try again: ");
            }
        }while(!isValidInput);
        return result;
    }
    public static boolean getBooleanFromUser(String promptText){
        System.out.println(promptText);
        boolean isValidInput=false;
        boolean b = false;
        Scanner in = new Scanner(System.in);
        while(!isValidInput){
            String input = in.nextLine();
            if(input.equalsIgnoreCase("yes")||input.equalsIgnoreCase("y")){
                b=true;
                isValidInput=true;
            } else if(input.equalsIgnoreCase("no")||input.equalsIgnoreCase("n")){
                b=false;
                isValidInput=true;
            } else{
                System.out.println("Unknown input. Try Again: ");
            }
        }
        return b;
    }
    public String toString(){
        return mUsername + " is "+mAge+" years old.";
    }
    public double carPayments(double principle, int loanLength, double carCost){
        principle = carCost-principle;
        double monthlyPayments = ((.10/12.0)*principle)/(1.0-Math.pow((1+(.10/12.0)),-loanLength));
        return ((int)(monthlyPayments*100))/100d;
    }
    public double savingsAccount(double principle, double length){
        return principle*Math.pow((1+(.02/12)),12*length);
    }
    public void savingsAccount(double principle){
        if(moneyInSavings==0){
            dateSavingsCreated = new Date().getTime();
        }
        System.out.println("New savings balance: $"+(principle+moneyInSavings));
        moneyInSavings+=principle;
    }
    public double getCurrentMoneyInSavings(){
        /*System.out.println(dateSavingsCreated);
        System.out.println(new Date().getTime());*/
        //The above 2 lines were used to test the java.util.Date class.
        if(moneyInSavings==0){
            return 0.0;
        } else{
            long currentDate = new Date().getTime()-dateSavingsCreated;
            double yearsElapsed = currentDate/(31540000000.0);
            return savingsAccount(moneyInSavings, yearsElapsed);
        }
    }
    public double mortgage(double amount, double length, double principle){
        amount=amount-principle;
        double i=.05/12; //monthly interest rate
        length = length*12;
        double monthlyPayments = amount*((i*Math.pow((1+i),length))/(Math.pow((1+i),length)-1));
        return ((int)(monthlyPayments*100))/100d;
    }
}
