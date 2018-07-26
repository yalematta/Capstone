package com.yalematta.podable.util.progress_pie;

/**
 * Created by yalematta on 7/26/18.
 */

public class PieColor
{
    private String name;
    private int colorRes;


    public PieColor(String name, int colorRes)
    {
        this.name = name;
        this.colorRes = colorRes;
    }


    public int getColorRes()
    {
        return colorRes;
    }


    public void setColorRes(int colorRes)
    {
        this.colorRes = colorRes;
    }


    public String getName()
    {
        return name;
    }


    public void setName(String name)
    {
        this.name = name;
    }


    @Override
    public String toString()
    {
        return name;
    }
}
