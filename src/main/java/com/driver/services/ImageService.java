package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Image image = new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);
        Blog blog;
        try{
            blog = blogRepository2.findById(blogId).get();
        }
        catch (Exception e)
        {
            e.getMessage();
            return null;
        }
        image.setBlog(blog);
        blog.getImageList().add(image);
        blogRepository2.save(blog);
        return image;
    }

    public void deleteImage(Integer id){
        try{
            imageRepository2.deleteById(id);
        }
        catch(Exception e)
        {
            e.getMessage();
        }
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        String [] scrArr = screenDimensions.split("x");
        Image image = imageRepository2.findById(id).get();

        String imageDimension = image.getDimensions();
        String [] imgArr = imageDimension.split("x");

        int scrLen = Integer.parseInt(scrArr[0]);
        int scrBre = Integer.parseInt(scrArr[1]);

        int imgLen = Integer.parseInt(imgArr[0]);
        int imgBre = Integer.parseInt(imgArr[1]);

        return  numberOfImages( scrLen,scrBre,imgLen,imgBre);
    }
    public int numberOfImages ( int scrLen,int scrBre,int imgLen,int imgBre){
        return (scrLen/imgLen)*(scrBre/imgBre);
    }
}
