/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestClient.Exception;

import java.io.Serializable;

/**
 *
 * @author wilzone
 */
public class ErrorMessage implements Serializable{

    private String message;

    public ErrorMessage() {
    }
    
    public ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}