import com.porterbank.*;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        /*Client[] initialize = new Client[0];
        Data.save(initialize);*/
        Client[] clients = Data.load();
        /*for(Client client:clients){
            System.out.println(client.toString());
        }*/
        Client currentClient = new Client();
        System.out.println("PORTER BANK(US)");
        System.out.println("---------------");
        String userName=Client.getStringFromUser("Enter a Username: ");
        while(userName.length()<5){
            userName=Client.getStringFromUser("Username must be more than 5 characters long. Try again: ");
        }
        boolean userFound=false;
        for(int i=0;i<clients.length;i++){
            if(clients[i].getUsername().equalsIgnoreCase(userName)){
                userFound=true;
                currentClient = clients[i];
                i=clients.length;
                currentClient.login();
                System.out.println("Account information: "+currentClient.toString());
            } else if (clients[i].hasSimilarUserName(userName)){
                userFound=true;
                currentClient = clients[i];
                i=clients.length;
                currentClient.login();
                System.out.println("Account information: "+currentClient.toString());
            }
        }
        if(!userFound&&userName.equalsIgnoreCase("jwp91")){
            currentClient = new Employee("jwp91");
            clients = Arrays.copyOf(clients, clients.length+1);
            clients[clients.length-1]=currentClient;
            Data.save(clients);
        }else if(!userFound){
            currentClient = new Client(userName);
            clients = Arrays.copyOf(clients, clients.length+1);
            clients[clients.length-1]=currentClient;
            Data.save(clients);
        }
        if(currentClient instanceof Employee){
            ((Employee) currentClient).clientManage(clients);
        }

        System.out.println("What would you like to do? ");
        System.out.println("1) Car loan");
        System.out.println("2) Mortgage");
        System.out.println("3) Student loan");
        System.out.println("4) Open Savings Account(set withdraw date)[2% interest]");
        System.out.println("5) Open Savings Account(Random Access)[2% interest]");
        System.out.println("6) View Savings Account(if account is RA)");
        System.out.println("e) Exit");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        if(input.equalsIgnoreCase("one")||input.equalsIgnoreCase("1")){
            double carCost=Client.getDoubleFromUser("How much does your car cost?");
            while(carCost<1000){
                carCost=Client.getDoubleFromUser("Sorry, we only finance loans greater than 1000. Try again: ");
            }
            int loanLength = (int)Client.getDoubleFromUser("What is the period for this loan? (months)");
            while(loanLength<24){
                loanLength=(int)Client.getDoubleFromUser("Sorry, all loans must be over the course of more than 2 years. ");
            }
            double principle = Client.getDoubleFromUser("How much principle are you depositing? ");
            while(principle>carCost){
                principle = Client.getDoubleFromUser("Looks like you're all paid for. Did you misinput? Try again: ");
            }
            System.out.println("Monthly Payments over "+loanLength+" months: $"+
                               currentClient.carPayments(principle, loanLength, carCost));
        } else if(input.equalsIgnoreCase("two")||input.equalsIgnoreCase("2")){
            double mortgage=Client.getDoubleFromUser("How much money are you withdrawing for your mortgage? ");
            while(mortgage<1000){
                mortgage=Client.getDoubleFromUser("Sorry, we only finance loans greater than $1000. Try again: ");
            }
            int loanLength = (int)Client.getDoubleFromUser("What is the period for this mortgage? (years)");
            while(loanLength<2){
                loanLength=(int)Client.getDoubleFromUser("Sorry, all mortgages must be over the course of more than 2 years. ");
            }
            double principle = Client.getDoubleFromUser("How much principle are you depositing? ");
            while(principle>mortgage){
                principle = Client.getDoubleFromUser("Looks like you're all paid for. Did you misinput? Try again: ");
            }
            System.out.println("Monthly Payments over "+loanLength+" years: $"+
                               currentClient.mortgage(mortgage,loanLength,principle));
        } else if(input.equalsIgnoreCase("three")||input.equalsIgnoreCase("3")) {
            double studentLoan = Client.getDoubleFromUser("How much money are you withdrawing for your loan? ");
            while (studentLoan < 1000) {
                studentLoan = Client.getDoubleFromUser("Sorry, we only finance loans for tuition greater than $1000. Try again: ");
            }
            int loanLength = (int) Client.getDoubleFromUser("How long will it take to pay this loan off? (years)");
            while (loanLength < 2) {
                loanLength = (int) Client.getDoubleFromUser("Sorry, all loans must be over the course of more than 2 years. ");
            }
            double principle = Client.getDoubleFromUser("How much principle are you depositing? ");
            while (principle > studentLoan) {
                principle = Client.getDoubleFromUser("Looks like you're all paid for. Did you misinput? Try again: ");
            }
            System.out.println("Monthly Payments over " + loanLength + " years: $" +
                    currentClient.mortgage(studentLoan, loanLength, principle));
        }else if(input.equalsIgnoreCase("four")||input.equalsIgnoreCase("4")){
            double principle = Client.getDoubleFromUser("How much principle are you depositing? ");
            while (principle <1000) {
                principle = Client.getDoubleFromUser("Sorry, all savings accounts must start with a minimum of $1000. Try again: ");
            }
            int loanLength = (int) Client.getDoubleFromUser("How long until you plan of withdrawing the money in this account?  (years)");
            if(loanLength>60){
                System.out.println("Withdraw date set for 60 years from not. After 60 years we require that you reapply for your savings account. ");
                loanLength=60;
            }
            System.out.println("Money in account after "+loanLength+" years: $"+currentClient.savingsAccount(principle, loanLength));
        } else if (input.equalsIgnoreCase("five")||input.equalsIgnoreCase("5")) {
            double principle = Client.getDoubleFromUser("How much principle are you depositing? ");
            while (principle < 1000) {
                principle = Client.getDoubleFromUser("Sorry, all savings accounts must start with a minimum of $1000. Try again: ");
            }
            currentClient.savingsAccount(principle);
        }else if(input.equalsIgnoreCase("six")||input.equalsIgnoreCase("6")){
            System.out.println("Money currently in Savings: $"+currentClient.getCurrentMoneyInSavings());
        } else if(input.equalsIgnoreCase("exit")||input.equalsIgnoreCase("e")){
            System.out.println("Exiting...");
            Data.save(clients);
            System.exit(0);
        }
        Data.save(clients);
    }
}
