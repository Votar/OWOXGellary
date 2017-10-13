package com.example.beretta.owoxgallary.models.network.response;

/**
 * Created by beretta on 12.10.2017.
 */

public class LiknsRest
{
    private String photos;

    private String followers;

    private String following;

    private String portfolio;

    private String likes;

    private String html;

    private String self;

    public String getPhotos ()
    {
        return photos;
    }

    public void setPhotos (String photos)
    {
        this.photos = photos;
    }

    public String getFollowers ()
    {
        return followers;
    }

    public void setFollowers (String followers)
    {
        this.followers = followers;
    }

    public String getFollowing ()
    {
        return following;
    }

    public void setFollowing (String following)
    {
        this.following = following;
    }

    public String getPortfolio ()
    {
        return portfolio;
    }

    public void setPortfolio (String portfolio)
    {
        this.portfolio = portfolio;
    }

    public String getLikes ()
    {
        return likes;
    }

    public void setLikes (String likes)
    {
        this.likes = likes;
    }

    public String getHtml ()
    {
        return html;
    }

    public void setHtml (String html)
    {
        this.html = html;
    }

    public String getSelf ()
    {
        return self;
    }

    public void setSelf (String self)
    {
        this.self = self;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [photos = "+photos+", followers = "+followers+", following = "+following+", portfolio = "+portfolio+", likes = "+likes+", html = "+html+", self = "+self+"]";
    }
}