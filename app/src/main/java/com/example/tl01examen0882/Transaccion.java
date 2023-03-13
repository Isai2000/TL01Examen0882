package com.example.tl01examen0882;

public class Transaccion {
    // Nombre de la bd
    public static final String NameDatabase = "TLENG";

    // Creacion de tabla y objetos
    public static final String tablaperson = "personas";

    /* Campos de la tabla personas */
    public static String Pais = "pais";
    public static String nombres = "nombres";
    public static String Telefono = "telefono";
    public static String Notas = "notas";

    // Consultas SQL DDL
    public static String CreateTBPersonas = "CREATE TABLE personas (id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nombres TEXT, telefono INTEGER, notas TEXT )";

    public static String DropTBPersonas = "DROP TABLE IF EXISTS personas";
}
