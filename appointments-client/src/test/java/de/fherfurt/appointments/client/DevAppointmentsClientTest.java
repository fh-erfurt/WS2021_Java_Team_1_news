package de.fherfurt.appointments.client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Christof Seelisch <christof.seelisch@fh-erfurt.de>
 */
class DevAppointmentsClientTest {

    @Test
    void shouldReturnAppointment1() {
        //TODO replace with real implementation when going live
        DevAppointmentsClient devAppointmentsService = new DevAppointmentsClient();
        Assertions.assertEquals("appointment1", devAppointmentsService.getAppointmentById(1).get().getTitle());
    }

    @Test
    void shouldReturn2() {
        //TODO replace with real implementation when going live
        DevAppointmentsClient devAppointmentsService = new DevAppointmentsClient();
        Assertions.assertEquals(2, devAppointmentsService.getAppointmentByName("appointment2").get().getId());
    }
}