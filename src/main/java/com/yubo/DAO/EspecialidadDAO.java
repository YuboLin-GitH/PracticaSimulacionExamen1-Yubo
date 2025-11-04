package com.yubo.DAO;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yubo.domain.Especialidades;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class EspecialidadDAO {
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    public static List<Especialidades> obtenerEspecialidad() throws IOException {

        ArrayList<Especialidades> especialidad =
                JSON_MAPPER.readValue(new File("src/main/resources/BaseDatos/especialidades.json"),
                        JSON_MAPPER.getTypeFactory().constructCollectionType
                                (ArrayList.class, Especialidades.class));

        return especialidad;
    }
}
