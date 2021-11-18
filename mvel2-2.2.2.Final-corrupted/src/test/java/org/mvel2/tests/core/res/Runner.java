/* Copyright © 2016 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
/**
 *
 */
package org.mvel2.tests.core.res;

public class Runner
{

    public String run(String... args)
    {
        StringBuilder result = new StringBuilder();
        if (args == null)
        {
            return "null";
        }
        for (String arg : args)
        {
            result.append(arg);
            result.append(",");
        }
        return result.toString();
    }

    public String run(String name, String... args)
    {
        StringBuilder result = new StringBuilder();
        if (args == null)
        {
            return name + "null";
        }
        for (String arg : args)
        {
            result.append(arg);
            result.append(",");
        }
        return name + result.toString();
    }
}
