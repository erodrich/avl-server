/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erodrich.util;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

/**
 *
 * @author UserTBS1
 */
public class MainUtil {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here

        
        String inputImei = "01";
        
        byte[] bytes = Hex.decodeHex(inputImei.toCharArray());
        
        String outputImei = new String(bytes);
        System.out.println(outputImei);
    

    }
    
}
