package com.yubo.DAO;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yubo.domain.Especialidad;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class EspecialidadDAO {
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    public static List<Especialidad> obtenerEspecialidad() throws IOException {

        ArrayList<Especialidad> especialidad =
                JSON_MAPPER.readValue(new File("src/main/resources/BaseDatos/especialidades.json"),
                        JSON_MAPPER.getTypeFactory().constructCollectionType
                                (ArrayList.class, Especialidad.class));

        return especialidad;
    }
}
