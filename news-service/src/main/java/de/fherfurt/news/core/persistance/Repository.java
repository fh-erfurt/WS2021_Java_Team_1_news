package de.fherfurt.news.core.persistance;

import de.fherfurt.news.core.entity.Entry;
import de.fherfurt.news.core.persistance.errors.EntryNotFoundException;

import java.util.Set;

/**
 * Basic API definition of the Repository which is used to communicate to the underlying database
 *
 * @param <TYPE> Generic type of entry
 * @author Maximilian Röhr <maximilian.roehr@fh-erfurt.de>
 */

public interface Repository<TYPE extends Entry> {

    /**
     * Find an entry by its id. If there is no entry, an EntryNotFoundException is thrown
     *
     * @param id id of the searched entry
     * @return The entry or empty
     */
    TYPE findBy(int id) throws EntryNotFoundException;

    /**
     * Save an entity to the underlying storage. It doesn't matter, if the entity is new or already saved.
     * In case of update the changes are written too.
     *
     * @param entry Instance to save
     */
    void save(TYPE entry);

    /**
     * Deletes an entry by its id
     * @param id id of the entry which should be deleted
     * @throws EntryNotFoundException Exception is thrown if the entry isn´t available
     */
    void delete(int id) throws EntryNotFoundException;

    /**
     * Fetch all current Entries of the underlying storage.
     *
     * @return all existing entries
     */
    Set<TYPE> fetchAll();

}
