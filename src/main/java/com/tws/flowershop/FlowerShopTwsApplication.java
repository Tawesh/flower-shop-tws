package com.tws.flowershop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FlowerShopTwsApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowerShopTwsApplication.class, args);
        show();

    }
    public static void show(){
        System.out.println(" ______   __     __     ______    \n" +
                "/\\__  _\\ /\\ \\  _ \\ \\   /\\  ___\\   \n" +
                "\\/_/\\ \\/ \\ \\ \\/ \\ \\ \\  \\ \\___  \\  \n" +
                "   \\ \\_\\  \\ \\__/\\ \\\\_\\  \\/\\_____\\ \n" +
                "    \\/_/   \\/_/   \\/_/   \\/_____/ \n" +
                "                                  "+"Author:Tws v13.14");
    }

}
