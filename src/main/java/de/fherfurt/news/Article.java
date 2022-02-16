package de.fherfurt.news;


import de.fherfurt.studentSelfOrganization.Entry;
import de.fherfurt.person.Person;
import de.fherfurt.appointments.Appointment;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class Article extends Entry {

    private List<BufferedImage> imageList;
    private Date date;
    private boolean lastModified;
    private List<Person> responsiblePersons;
    private Person author;
    private Priority priority;
    private Appointment appointment;
    private enum language{
        GERMAN, ENGLISH
    }
    //private String title;
    //private String content;
    //private String category
    //private List<String> keywordList;
    //private int clicks;


    //QUESTION: easy way to add additional constructors?
    //          right way to add getters and setters for list even though there are existing methods
    //default-constructor
    public Article(String title, String content,Person author) {

        this.setTitle(title);
        this.setContent(content);
        this.author = author;
        this.imageList = null;
        this.lastModified = false;
        this.responsiblePersons = null;
        this.priority = Priority.NORMAL;
        this.setKeywordlist(null);
        this.setClicks(0);
        this.date = new Date(System.currentTimeMillis());
    }
    //constructor with additional contact info
    public Article(String title, String content,List<Person> responsiblePersons,Person author) {
        this(title, content,author);
        this.responsiblePersons = responsiblePersons;
    }
    //constructor with additional priority level
    public Article(String title, String content,Priority priority,Person author) {
        this(title, content,author);
        this.priority = priority;
    }
    //constructor with additional priority level and contact info
    public Article(String title, String content,List<Person> responsiblePersons,Person author,Priority priority) {
        this(title, content,responsiblePersons,author);
        this.priority = priority;
    }


    public void addImage(String imagePath) {    // loads image from path and adds it to list
        try {
            this.imageList.add(ImageIO.read(new File(imagePath)));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public BufferedImage getImage(int imageIndex) { // returns a specific image at index position
        if (imageIndex > this.imageList.size()) {
            return null;
        } else {
            return this.imageList.get(imageIndex);
        }
    }
    public void clearImageList() {this.imageList.clear();}    // deletes all images present in list
    public void removeImage(int imageIndex) {this.imageList.remove(imageIndex);}   // removes a specific image at index position from list
    public int getImageCount() {return this.imageList.size();} // returns count of stored images in list
    public List<BufferedImage> getImageList() {return this.imageList;}    // returns image list
    public void setImageList(List<BufferedImage> images) {this.imageList = images;}   // for passing an existing list of images

    public Date getDate() {return date;}
    public void setDate(Date date) {this.date = date;}

    public boolean isLastModified() {return lastModified;}
    public void setLastModified(boolean lastModified) {this.lastModified = lastModified;}

    public List<Person> getContact() {return responsiblePersons;}
    public void setContact(List<Person> responsiblePersons) {this.responsiblePersons = responsiblePersons;}

    public Person getAuthor() {return author;}
    public void setAuthor(Person author) {this.author = author;}

    public Priority getPriority() {return priority;}
    public void setPriority(Priority priority) {this.priority = priority;}

    /*public void addKeyword(String keyword){this.keywordList.add(keyword);}
    public void clearKeywordList(){this.keywordList.clear();}
    public List<String> getKeywordList() {return keywordList;}
    public void setKeywordList(List<String> keywordList) {this.keywordList = keywordList;}

    public int getClicks() {return clicks;}
    public void setClicks(int clicks) {this.clicks = clicks;}*/


}
