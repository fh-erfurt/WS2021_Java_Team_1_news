package de.fherfurt.faculties.client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DevFacultiesServiceTest {

    @Test
    void shouldReturn3(){
        DevFacultiesService devFacultiesService = new DevFacultiesService();
        Assertions.assertEquals(true, devFacultiesService.isFacultynameValid("faculty3"));
    }
}