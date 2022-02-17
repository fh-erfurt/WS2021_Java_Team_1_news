package de.fherfurt.appointments.client.transfer.objects;

/**
 * Represents the Person information relevant for the News Module.
 * It is used to access Person Metadata to filter news articles with it.
 */
public class NewsPerson {
    private int id;
    private String name;
    private String mail;
    private String tel;

    public NewsPerson(int id, String name, String mail, String tel){
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.tel = tel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
