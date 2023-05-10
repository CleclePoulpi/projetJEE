package com.server.projetjee.api;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/importathleteslist")
public class import_csv {

    @POST
    @Consumes("text/csv")
    public void import_data(String[] csv) {
        for (String line : csv) {
            String[] data = line.split(",");
            if (data[0] != "Nom de famille") {
                athletes athlete = new athletes(data[0], data[1], data[2], data[3], data[4], data[5]);
                //@TODO faire la requete pour ajouter l'athlete dans la base de donnée
            }
        }

    }
}