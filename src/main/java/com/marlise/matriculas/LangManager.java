/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.marlise.matriculas;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Jorge Marles
 */
public final class LangManager {
    private static Locale locale = null;
    private static ResourceBundle resourceBundle = null;
    private static String lang = null;
    
    public static void setLang(String lang){
        if(lang == null){
            throw new NullPointerException("Lang can't be null");
        }
        LangManager.lang = lang;
        if(locale == null){
            locale = Locale.forLanguageTag(LangManager.lang);
        }
        if(resourceBundle == null){
            resourceBundle = ResourceBundle.getBundle("application_messages", locale);
        }
    }
    
    public static String getString(String key){
        return LangManager.resourceBundle.getString(key);
    }
}
