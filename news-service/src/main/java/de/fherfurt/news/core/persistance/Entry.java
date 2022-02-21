package de.fherfurt.news.core.persistance;

import lombok.Data;

/**
 * basic class for all entries
 *
 * @author Maximilian Röhr <maximilian.roehr@fh-erfurt.de>
 */
public @Data class Entry {
    private int id;

    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
}