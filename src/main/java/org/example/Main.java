package org.example;

import UTILITY.DBUtility;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

         System.out.println("Hello world!");

        Connection conn= DBUtility.provideconnection();
        System.out.println(conn);


    }
}