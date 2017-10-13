package com.example.beretta.owoxgallary.models.network.response;

/**
 * Created by beretta on 12.10.2017.
 */

public class ProfileImageRest
{
    private String small;

    private String large;

    private String medium;

    public String getSmall ()
    {
        return small;
    }

    public void setSmall (String small)
    {
        this.small = small;
    }

    public String getLarge ()
    {
        return large;
    }

    public void setLarge (String large)
    {
        this.large = large;
    }

    public String getMedium ()
    {
        return medium;
    }

    public void setMedium (String medium)
    {
        this.medium = medium;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [small = "+small+", large = "+large+", medium = "+medium+"]";
    }
}
