package de.fherfurt.persons.client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DevPersonClientTest {

    @Test
    void shouldReturnUser1() {
        DevPersonClient devPersonsService = new DevPersonClient();
        Assertions.assertEquals("user 1", devPersonsService.getPersonByID(1).get().getName());
    }

    @Test
    void shouldReturn2() {
        DevPersonClient devPersonsService = new DevPersonClient();
        Assertions.assertEquals(2, devPersonsService.getPersonByMail("user2@2mail.com").get().getId());
    }
}