package com.VendingMachine;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Getter
@Slf4j
public class VendingMachine {
    private Integer balance = 0;
    private Map<String, Integer> storag = new HashMap<String, Integer>();
    private Properties properties = new Properties();

    public VendingMachine(File file) {
        try {
            properties.load(new FileReader(file));
        }catch (FileNotFoundException fnfe) {
            log.error("File not found");
        }catch (IOException ioe) {
            log.error("Error input-output");
        }
    }


    //добавить деньги
    public void deposit(int money) {
        if (money == 1 || money == 5 || money == 10 || money == 20) {
            balance = balance + money;
            log.info("add " + money + " money." + "BALANCE IS " + balance);
        }
        else {
            log.error("Not accept " + money + " UAN");
        }
    }

    // снять деньги
    private void withdraw(int money) {
        balance = balance - money;
        log.info("withdraw " + money + " money." + " BALANCE IS " + balance);
    }

    public String byeGood(String good) {
        int cost = Integer.parseInt(properties.getProperty("good"));
        log.info("withdraw " + cost + " UAN") ;
        if(prove(true)) {
            withdraw(cost);
            return good;
        }else {
            log.info("Rollback. The money was returned!");
            return "";
        }
    }

    private boolean prove(Boolean b) {
        return true;
    }
}
