package de.fherfurt.persons.client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DevPersonClientTest {

    @Test
    void shouldReturnUser1() {
        DevPersonClient devPersonsService = new DevPersonClient();
        Assertions.assertEquals("user 1", devPersonsService.findById(1).get().getUsername());
    }

    @Test
    void shouldReturn2() {
        DevPersonClient devPersonsService = new DevPersonClient();
        Assertions.assertEquals(2, devPersonsService.findByEmail("user2@mail.com").get().getId());
    }
}