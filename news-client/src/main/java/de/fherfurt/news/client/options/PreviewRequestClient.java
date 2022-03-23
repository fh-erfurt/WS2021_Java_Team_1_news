package de.fherfurt.news.client.options;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public @Data class PreviewRequestClient {

    public String searchTerm;
    public String facultyName;
    public SortSettingsClient sortSettings;


    //internal class and enum declaration to encapsulate the functionality
    public @Data
    static
    class SortSettingsClient {
        public final SortDirectionClient sortDirection;
        public final SortPriorityClient sortPriority;
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
