package de.fherfurt.faculties.client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Christof Seelisch <christof.seelisch@fh-erfurt.de>
 */
class DevFacultyClientTest {

    @Test
    void shouldReturn3() {
        DevFacultyClient devFacultiesService = new DevFacultyClient();
        Assertions.assertTrue(devFacultiesService.isFacultyNameValid("faculty3"));
    }
}