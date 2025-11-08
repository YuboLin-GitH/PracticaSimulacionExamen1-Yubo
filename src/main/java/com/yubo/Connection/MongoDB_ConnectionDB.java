package com.yubo.Connection;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import com.yubo.util.AlertUtils;
import com.yubo.util.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class MongoDB_ConnectionDB {
    static MongoClient mongoClient;
    static MongoDatabase database;

    public static MongoClient conectar() {
        Properties properties = new Properties();
        String host = "", port = "", name = "", username = "", password = "";

        try {
            properties.load(R.getProperties("Mongo_Database.properties"));
            host = String.valueOf(properties.get("host"));
            port = String.valueOf(properties.get("port"));
            name = String.valueOf(properties.get("source"));
            username = String.valueOf(properties.get("user"));
            password = String.valueOf(properties.get("password"));
            mongoClient = new MongoClient(new MongoClientURI("mongodb://" + username + ":" + password + "@" + host + ":" + port + "/?authSource=admin"));
            database =mongoClient.getDatabase("ExamenCitasMedicas");
            return mongoClient;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            AlertUtils.mostrarError("ERROR Error de conexión  No se ha encontrado el archivo database.properties: " + e.getMessage());
            return null;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            AlertUtils.mostrarError("ERROR Error de conexión  Error de E/S Hubo un problema al leer el archivo de configuración: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    } // METODO PARA CONECTAR UNA APP A LA DATABASE DE MONGODB
    public static void closeDataBase(){
        mongoClient.close();
    }
    public static MongoDatabase getDatabase(){
        return database;
    }
}