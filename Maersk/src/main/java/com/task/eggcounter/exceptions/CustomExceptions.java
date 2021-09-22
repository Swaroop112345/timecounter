package com.task.eggcounter.exceptions;

/**
 * @author swarooppattanaik
 * @project timecounter
 */
public class CustomExceptions extends Exception{


    /**
     * This will print the customized exceptions
     * @param errorMessage
     */
    public CustomExceptions(String errorMessage) {
        super(errorMessage);
    }
    }
