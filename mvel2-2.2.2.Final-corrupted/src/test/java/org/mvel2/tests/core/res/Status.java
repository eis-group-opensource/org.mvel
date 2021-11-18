/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package org.mvel2.tests.core.res;

/**
* @author Mike Brock <cbrock@redhat.com>
*/
public class Status {
    public static final int START = 0;
    public static final int STOP = 1;
    
    private int value;
    
    public Status(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + value;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if ( this == obj ) return true;
        if ( obj == null ) return false;
        if ( getClass() != obj.getClass() ) return false;
        Status other = (Status) obj;
        if ( value != other.value ) return false;
        return true;
    }

    @Override
    public String toString() {
        return "Status [value=" + value + "]";
    }
    
}
