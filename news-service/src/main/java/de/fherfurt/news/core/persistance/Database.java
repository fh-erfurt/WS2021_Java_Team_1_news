package de.fherfurt.news.core.persistance;

import de.fherfurt.news.core.persistance.errors.EntryNotFoundException;

import java.util.Optional;

/**
 * basic API definition for the database
 *
 * @param <TYPE> type of Entry{@link Entry}
 * @author Maximilian Röhr <maximilian.roehr@fh-erfurt.de>
 */
public interface Database<TYPE extends Entry>{

    /**
     * saves the entry to a local storage. if entry is already existing it gets overwritten
     *
     * @param entry entry to be saved
     */
    void save(TYPE entry);

    /**
     * deletes an entry by its given id. throws an exception if the given id doesnt exist in database
     *
     * @param id id of entry to be deleted
     * @throws EntryNotFoundException exception thrown if entry does not exist
     */
    void delete(int id) throws EntryNotFoundException;

    /**
     * Find an entry by its id. If there is no entry, empty{@link Optional} is returned
     *
     * @param id id of the searched entry
     * @return The entry or empty
     */
    Optional<TYPE> findBy(int id);
}
