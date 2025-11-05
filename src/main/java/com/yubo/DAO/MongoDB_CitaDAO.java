package com.yubo.DAO;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.yubo.Connection.MongoDB_ConnectionDB;
import com.yubo.domain.Citas;
import com.yubo.util.AlertUtils;
import org.bson.Document;

/**
 * ClassName: MongoDB_CitaDAO
 * Package: com.yubo.DAO
 * Description:
 *
 * @Author linlin
 * @Create 04/11/2025 23:41
 * @Version 1.0
 */
public class MongoDB_CitaDAO implements MongoDB_CitaInterface{
    MongoClient mongoClient;
    MongoDatabase mongoDatabase; // BASE DE DATOS DE MONGO
    MongoCollection<Document> collection;


    public MongoDB_CitaDAO() {
        mongoClient = MongoDB_ConnectionDB.conectar();
        mongoDatabase = mongoClient.getDatabase("ExamenCitasMedicas");
        collection = mongoDatabase.getCollection("CitasMedicas");
    }

    @Override
    public boolean insertCita(Citas cita) {
        Document doc = new Document(); // DOCUMENTO BSON QUE SE INSERTARA EN LA BD
        try {
            doc.append("idCita", cita.getIdCita())
                    .append("fechaCita", cita.getFechaCita())
                    .append("idEspecialidad", cita.getEspecialidad().getIdEspecialidad())
                    .append("idPaciente", cita.getPaciente().getIdPaciente());

            collection.insertOne(doc); // INSERTAR EL DOCUMENTO EN LA COLECCION
            return true;
        } catch (Exception e) {
            AlertUtils.mostrarError("Error al conectar a la base de datos: " + e.getMessage());
        }
        return false;
    }
}
