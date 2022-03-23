package de.fherfurt.news.client.options;

import lombok.Data;

public @Data class PreviewRequest {

    public final String searchTerm;
    public final String facultyName;
    public final SortSettings sortSettings;


    //internal class and enum declaration to encapsulate the functionality
    public @Data
    class SortSettings {
        public final SortDirection sortDirection;
        public final SortPriority sortPriority;
    }
    public enum SortDirection {
        ASC,
        DESC
    }
    public enum SortPriority {
        TITLE,
        DATE,
        CLICKS,
        PRIORITY
    }

}
