/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alodiga.afinitas.json.charge.object;

/**
 *
 * @author ltoro
 */
public class Error {
    
    private String httpStatusCode;
    private String code;
    private String description;
    private BinInformation binInformation;
    

    public String getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(String httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BinInformation getBinInformation() {
        return binInformation;
    }

    public void setBinInformation(BinInformation binInformation) {
        this.binInformation = binInformation;
    }
    
    public String toString() {
        return String.format("httpStatusCode:%s", httpStatusCode);
    }
    
}
