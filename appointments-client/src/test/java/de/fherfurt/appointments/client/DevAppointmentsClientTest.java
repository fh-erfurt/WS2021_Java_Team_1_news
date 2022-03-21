package de.fherfurt.appointments.client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DevAppointmentsClientTest {

    @Test
    void shouldReturnAppointment1(){
        DevAppointmentsClient devAppointmentsService = new DevAppointmentsClient();
        Assertions.assertEquals("appointment1", devAppointmentsService.getAppointmentById(1).get().getTitle());
    }

    @Test
    void shouldReturn2(){
        DevAppointmentsClient devAppointmentsService = new DevAppointmentsClient();
        Assertions.assertEquals(2, devAppointmentsService.getAppointmentByName("appointment2").get().getId());
    }
}