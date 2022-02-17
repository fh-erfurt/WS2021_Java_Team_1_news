package de.fherfurt.appointments.client;

import de.fherfurt.appointments.client.transfer.objects.DTOAppointment;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DevAppointmentsService implements AppointmentsService{

    private final List<DTOAppointment> persons = Arrays.asList(
            new DTOAppointment(1, "appointment1", "21/02/2022", "Mensa"),
            new DTOAppointment(2, "appointment2", "27/02/2022", "Raum 1"),
            new DTOAppointment(3, "appointment3", "06/03/2022", "Raum 2"),
            new DTOAppointment(4, "appointment4", "30/04/2022", "Hof")
    );


    @Override
    public int getAppointmentIdByTitle(String title){

    }

    @Override
    public DTOAppointment getAppointmentById(int id){

    }
}
