package com.example.beretta.owoxgallary.models.network.response;

/**
 * Created by beretta on 12.10.2017.
 */

public class PhotoRest {
    private String[] current_user_collections;

    private UrlsRest urls;

    private Integer width;

    private LiknsRest links;

    private String id;

    private String updated_at;

    private Integer height;

    private String color;

    private String likes;

    private String created_at;

    private String[] categories;

    private UserRest user;

    private String liked_by_user;

    public String[] getCurrent_user_collections ()
    {
        return current_user_collections;
    }

    public void setCurrent_user_collections (String[] current_user_collections)
    {
        this.current_user_collections = current_user_collections;
    }

    public UrlsRest getUrls ()
    {
        return urls;
    }

    public void setUrls (UrlsRest urls)
    {
        this.urls = urls;
    }

    public Integer getWidth ()
    {
        return width;
    }

    public void setWidth (Integer width)
    {
        this.width = width;
    }

    public LiknsRest getLinks ()
    {
        return links;
    }

    public void setLinks (LiknsRest links)
    {
        this.links = links;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getUpdated_at ()
    {
        return updated_at;
    }

    public void setUpdated_at (String updated_at)
    {
        this.updated_at = updated_at;
    }

    public Integer getHeight ()
    {
        return height;
    }

    public void setHeight (Integer height)
    {
        this.height = height;
    }

    public String getColor ()
    {
        return color;
    }

    public void setColor (String color)
    {
        this.color = color;
    }


    public String getLikes ()
    {
        return likes;
    }

    public void setLikes (String likes)
    {
        this.likes = likes;
    }

    public String getCreated_at ()
    {
        return created_at;
    }

    public void setCreated_at (String created_at)
    {
        this.created_at = created_at;
    }

    public String[] getCategories ()
    {
        return categories;
    }

    public void setCategories (String[] categories)
    {
        this.categories = categories;
    }

    public UserRest getUser ()
    {
        return user;
    }

    public void setUser (UserRest user)
    {
        this.user = user;
    }

    public String getLiked_by_user ()
    {
        return liked_by_user;
    }

    public void setLiked_by_user (String liked_by_user)
    {
        this.liked_by_user = liked_by_user;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [current_user_collections = "+current_user_collections+", urls = "+urls+", width = "+width+", links = "+links+", id = "+id+", updated_at = "+updated_at+", height = "+height+", color = "+color+", likes = "+likes+", created_at = "+created_at+", categories = "+categories+", user = "+user+", liked_by_user = "+liked_by_user+"]";
    }
}
