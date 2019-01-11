package com.porterbank;

import java.io.*;
    public class Data {
        public Data(){}
        public static void save(Client[] clients){
            try(
                    FileOutputStream fos = new FileOutputStream("clients.cli");
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
            ){
                oos.writeObject(clients);
            }catch(IOException ioe){
                System.out.println("There was a problem uploading a new user. ");
                ioe.printStackTrace();
            }
        }

        public static Client[] load(){
            Client[] clients = new Client[0];
            try(
                    FileInputStream fis = new FileInputStream("clients.cli");
                    ObjectInputStream ois = new ObjectInputStream(fis);
            ){
                clients = (Client[])ois.readObject();
            }catch(IOException ioe){
                System.out.println("There was a problem loading the Client. ");
                ioe.printStackTrace();
            }catch(ClassNotFoundException cnfe){
                System.out.println("Error: Corrupted file 'clients.cli'");
                cnfe.printStackTrace();
            }
            return clients;
        }
    }
