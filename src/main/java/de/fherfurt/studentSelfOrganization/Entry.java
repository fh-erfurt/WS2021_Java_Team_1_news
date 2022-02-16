package de.fherfurt.studentSelfOrganization;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Entry implements Comparable<Entry> {
    private String title;
    private String category;
    private String content;
    private List<String> keywordlist;
    private int clicks;

    public Entry()
    {
        title = "";
        category = "";
        keywordlist = new ArrayList<>();
        clicks = 0;
    }
    public Entry(String title,String category,String initialWordlist)
    {
        this.title=title;
        this.category=category;
        keywordlist = new ArrayList<>();
        this.addMultibleKeywordsToList(initialWordlist);
        clicks = 0;
    }

    public Entry(String title,String category,String initialWordlist, int clicks)
    {
        this.title=title;
        this.category=category;
        keywordlist = new ArrayList<>();
        this.addMultibleKeywordsToList(initialWordlist);
        this.clicks = clicks;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public int getClicks() {
        return clicks;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public List<String> getKeywordlist() {
        return keywordlist;
    }
    public void setKeywordlist(ArrayList<String> keywordlist) {
        this.keywordlist = keywordlist;
    }

    public void setClicks(int clicks) {
        this.clicks = clicks;
    }

    public int addKeywordToList(String newKeyword){

        try{
            keywordlist.add(newKeyword);
        }
        catch(Exception e){
            return 1;
        }
        return 0;
    }

    public int addMultibleKeywordsToList(String wordList){
        String[] wordListInputString = wordList.split(";");
        try{
            keywordlist.addAll(Arrays.stream(wordListInputString).toList());
            return 0;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return 1;
        }

    }

    public boolean isWordSimilarInKeywordlist (String searchword){
        Pattern pattern;
        Matcher matcher;
        boolean matchFound=false;
        pattern = Pattern.compile(searchword, Pattern.CASE_INSENSITIVE);

        for(int i=0;i<keywordlist.size() && !matchFound ;i++)
        {
            matcher = pattern.matcher(keywordlist.get(i));
            matchFound = matcher.find();
        }
        return matchFound;
    }

    //Functions to display Test results
    public int  displayEntry(){
        System.out.println("Title: "+this.title);
        System.out.println("Kategorie: "+this.category);
        System.out.println("Inhalt: "+this.content);
        System.out.println("Keywords: "+keywordlist.toString());
        System.out.println("Aufrufe: "+this.clicks);
        return 1;
    }
    public int  displayPreview(){
        System.out.println("Title: "+this.title);
        System.out.println("Kategorie: "+this.category);
        System.out.println("Keywords: "+keywordlist.toString());
        return 1;
    }

    @Override
    public int compareTo(Entry entryToCompare){

        int result = this.title.compareTo(entryToCompare.getTitle());
        if(result==0){
            result = this.category.compareTo(entryToCompare.getCategory());
            if(result == 0){
                result = this.clicks-entryToCompare.getClicks();
            }
        }

        return result;

    }




}