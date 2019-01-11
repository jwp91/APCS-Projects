package com.porterbank;

import java.util.Arrays;
import java.util.Scanner;

public class Employee extends Client{
    public Employee(String userName){
        System.out.println("Creating new Employee account...");
        mUsername=userName;
        mAge=(int)getDoubleFromUser("Enter your current Age: ");
        setPassword();
    }
    public Client[] clientManage(Client[] clients){
        boolean doneManaging=false;
        do{
            System.out.println("Users: ");
            for(int i=0;i<clients.length;i++){
                System.out.println(i+1+") "+clients[i]);
            }
            System.out.println();
            System.out.println("Admin options: ");
            System.out.println("1) Delete User");
            System.out.println("e) Exit");
            Scanner in = new Scanner(System.in);
            String choice = in.nextLine();
            if(choice.equalsIgnoreCase("one")||choice.contains("1")){
                //PLAYER DELETION
                System.out.println("Which player do you want to delete? (input number below)");
                int playerChoice = 0;
                do{
                    try{
                        playerChoice=Integer.parseInt(in.nextLine());
                    }catch(NumberFormatException nfe){
                        System.out.println("Error selecting player. Try again: ");
                    }
                    if(playerChoice<0||playerChoice>clients.length){
                        System.out.println("No player matching that index. Try again: ");
                    }
                }while(playerChoice==0||playerChoice<0||playerChoice>clients.length);
                Client clientToModify = clients[playerChoice-1];
                if(clientToModify==this){
                    System.out.println("WARNING: ATTEMPTING TO DELETE YOUR ADMIN ACCOUNT!");
                }
                System.out.println("Are you sure you want to delete "+clientToModify.getUsername()+"'s account? ");
                boolean deletionHandled=false;
                do{
                    String yesOrNo = in.nextLine();
                    if(yesOrNo.equalsIgnoreCase("yes")||yesOrNo.equalsIgnoreCase("y")) {
                        System.out.println("Deleting "+clientToModify.getUsername()+"'s account...");
                        for (int i = playerChoice - 1; i < clients.length-1; i++) {
                            if(clients.length==i+1){
                                clients[i]=null;
                                clients = Arrays.copyOf(clients, clients.length-1);
                            }else{
                                clients[i] = clients[i + 1];
                            }
                        }
                        clients = Arrays.copyOf(clients, clients.length-1);
                        Data.save(clients);
                        deletionHandled=true;
                        if(clientToModify==this){
                            System.out.println("ERROR: NullPointerException[this]. Closing program. ");
                            System.exit(0);
                        }
                    }else if(yesOrNo.equalsIgnoreCase("no")||yesOrNo.equalsIgnoreCase("n")){
                        System.out.println("Cancelling deletion.");
                        deletionHandled=true;
                    }else{
                        System.out.println("Unknown input. Try again: ");
                    }
                }while(!deletionHandled);
            } else if(choice.equalsIgnoreCase("e")){
                System.out.println("Closing program. ");
                Data.save(clients);
                System.exit(0);
            } else{
                System.out.println("Unknown input. Try again: ");
            }
        }while(!doneManaging);
        return clients;
    }
}
