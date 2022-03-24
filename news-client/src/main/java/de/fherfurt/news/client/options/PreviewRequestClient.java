package de.fherfurt.news.client.options;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * this class is an equivalent representation of the PreviewRequest on the service side
 *
 * @author Dennis Rinck <dennis.rinck@fh-erfurt.de>
 */
@NoArgsConstructor
@AllArgsConstructor
public @Data
class PreviewRequestClient {

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

    public enum SortDirectionClient {
        ASC,
        DESC
    }

    public enum SortPriorityClient {
        TITLE,
        DATE,
        CLICKS,
        PRIORITY
    }

}
