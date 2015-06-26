package com.gugu.codeGenerate.def;

/**
 * Created by Daniel on 2015-05-26.
 */
public class TableConvert
{
    public static String getNullAble(String nullable)
    {
        if (("YES".equals(nullable)) || ("yes".equals(nullable)) || ("y".equals(nullable)) || ("Y".equals(nullable))) {
            return "Y";
        }
        if (("NO".equals(nullable)) || ("N".equals(nullable)) || ("no".equals(nullable)) || ("n".equals(nullable))) {
            return "N";
        }
        return null;
    }
}