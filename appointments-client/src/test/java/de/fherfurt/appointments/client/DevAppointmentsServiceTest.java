package de.fherfurt.appointments.client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DevAppointmentsServiceTest {

    @Test
    void shouldReturnAppointment1(){
        DevAppointmentsService devAppointmentsService = new DevAppointmentsService();
        Assertions.assertEquals("appointment1", devAppointmentsService.getAppointmentById(1).get().getTitle());
    }

    @Test
    void shouldReturn2(){
        DevAppointmentsService devAppointmentsService = new DevAppointmentsService();
        Assertions.assertEquals(2, devAppointmentsService.getAppointmentByTitle("appointment2").get().getId());
    }
}