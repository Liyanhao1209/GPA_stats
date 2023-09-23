package org.Util;

import java.util.Scanner;

public class SingletonScanner {
    private static  Scanner scanner;

    public static synchronized Scanner getSingletonScanner(){
        if(scanner==null){
            scanner = new Scanner(System.in);
        }
        return scanner;
    }
}
