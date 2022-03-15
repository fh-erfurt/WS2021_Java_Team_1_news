package de.fherfurt.persons.client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DevPersonsServiceTest {

    @Test
    void shouldReturnUser1(){
        DevPersonsService devPersonsService = new DevPersonsService();
        Assertions.assertEquals("user 1", devPersonsService.getPersonByID(1).get().getName());
    }

    @Test
    void shouldReturn2(){
        DevPersonsService devPersonsService = new DevPersonsService();
        Assertions.assertEquals(2, devPersonsService.getPersonByMail("user2@2mail.com").get().getId());
    }
}