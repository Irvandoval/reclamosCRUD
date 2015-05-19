package com.example.irvandoval.reclamosgrupo17;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.irvandoval.reclamosgrupo17.categoriaempresa.CategoriaEmpresa;
import com.example.irvandoval.reclamosgrupo17.categoriaprodserv.CategoriaProdServ;
import com.example.irvandoval.reclamosgrupo17.detallereclamo.DetalleReclamo;
import com.example.irvandoval.reclamosgrupo17.empresa.Empresa;
import com.example.irvandoval.reclamosgrupo17.estadoreclamo.EstadoReclamo;
import com.example.irvandoval.reclamosgrupo17.prodserv.ProdServ;
import com.example.irvandoval.reclamosgrupo17.reclamo.Reclamo;
import com.example.irvandoval.reclamosgrupo17.sucursal.Sucursal;
import com.example.irvandoval.reclamosgrupo17.usuario.Usuario;
import com.example.irvandoval.reclamosgrupo17.zona.Zona;

/**
 * Created by irvandoval on 05-10-15.
 */
public class ControlDB {
    private static final String[] camposDetalleReclamo = new String[]{"id_detalle", "id_prod_serv", "descripcion_detalle"};
    private static final String[] camposUsuario = new String[]{"dui", "nombre_usuario", "apellido_usuario", "email", "telefono", "edad", "sexo"};
    private static final String[] camposZona = new String[]{"id_zona", "nombre_zona", "municipio", "departamento"};
    private static final String[] camposSucursal = new String[]{"id_sucursal", "id_empresa", "id_zona", "nombre_sucursal", "jefe_sucursal", "direccion_sucursal", "telefono_sucursal"};
    private static final String[] camposCategoriaEmpresa = new String[]{"id_categoria_emp", "nombr_categoria_emp", "descripcion_categoria_emp"};
    private static final String[] camposEmpresa = new String[]{"id_empresa", "id_categoria_emp", "nombre_empresa", "cantidad_sucursales"};
    private static final String[] camposCategoriaProdServ = new String[]{"id_categoria_prod", "nombre_categoria_ps", "descripcion_categoria_ps", "cantidad_productos"};
    private static final String[] camposReclamo = new String[]{"id_reclamo", "dui", "id_estado_reclamo", "id_sucursal", "id_detalle", "titulo", "motivo", "fecha_reclamo"};
    private static final String[] camposProdServ = new String[]{"id_prod_serv", "id_categoria_prod", "nombre_prod_serv", "descripcion_prod_serv"};
    private static final String[] camposEstadoReclamo = new String[]{"id_estado_reclamo", "id_estado_reclamo", "nombre_estado", "descripcion_estado"};
    private final Context context;
    private DataBaseHelper DBHelper;
    private SQLiteDatabase db;

    public ControlDB(Context ctx) {
        this.context = ctx;
        DBHelper = new DataBaseHelper(context);
    }

    private static class DataBaseHelper extends SQLiteOpenHelper {
        private static final String BASE_DATOS = "reclamos.s3db";
        private static final int VERSION = 1;

        public DataBaseHelper(Context context) {
            super(context, BASE_DATOS, null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {/********************************CREACION DE TABLAS************************/
                db.execSQL("CREATE TABLE \"usuario\" (\n" +
                        "\"dui\" VARCHAR(10) NOT NULL,\n" +
                        "\"nombre_usuario\" VARCHAR(50) NOT NULL,\n" +
                        "\"apellido_usuario\" VARCHAR(50) NOT NULL,\n" +
                        "\"email\" VARCHAR(35) NOT NULL,\n" +
                        "\"telefono\" VARCHAR(9),\n" +
                        "\"edad\" INTEGER NOT NULL,\n" +
                        "\"sexo\" VARCHAR(1) NOT NULL,\n" +
                        "PRIMARY KEY (\"dui\") \n" +
                        ");");
                db.execSQL("CREATE TABLE \"reclamo\" (\n" +
                        "\"id_reclamo\" INTEGER NOT NULL,\n" +
                        "\"dui\" VARCHAR(10) NOT NULL,\n" +
                        "\"id_estado_reclamo\" INTEGER NOT NULL,\n" +
                        "\"id_sucursal\" INTEGER NOT NULL,\n" +
                        "\"id_detalle\" INTEGER NOT NULL,\n" +
                        "\"titulo\" VARCHAR(50) NOT NULL,\n" +
                        "\"motivo_reclamo\" VARCHAR(75) NOT NULL,\n" +
                        "\"fecha_reclamo\" DATE NOT NULL,\n" +
                        "PRIMARY KEY (\"id_reclamo\") ,\n" +
                        "CONSTRAINT \"fk_reclamo_estado_reclamo_1\" FOREIGN KEY (\"id_estado_reclamo\") REFERENCES \"estado_reclamo\" (\"id_estado_reclamo\"),\n" +
                        "CONSTRAINT \"fk_reclamo_sucursal_1\" FOREIGN KEY (\"id_sucursal\") REFERENCES \"sucursal\" (\"id_sucursal\"),\n" +
                        "CONSTRAINT \"fk_reclamo_detalle_reclamo_1\" FOREIGN KEY (\"id_detalle\") REFERENCES \"detalle_reclamo\" (\"id_detalle\"),\n" +
                        "CONSTRAINT \"fk_reclamo_usuario_1\" FOREIGN KEY (\"dui\") REFERENCES \"usuario\" (\"dui\")\n" +
                        ");");
                db.execSQL("CREATE TABLE \"zona\" (\n" +
                        "\"id_zona\" INTEGER NOT NULL,\n" +
                        "\"nombre_zona\" VARCHAR(50) NOT NULL,\n" +
                        "\"municipio\" VARCHAR(50) NOT NULL,\n" +
                        "\"departamento\" VARCHAR(50) NOT NULL,\n" +
                        "PRIMARY KEY (\"id_zona\") \n" +
                        ");");
                db.execSQL("CREATE TABLE \"empresa\" (\n" +
                        "\"id_empresa\" INTEGER NOT NULL,\n" +
                        "\"id_categoria_emp\" INTEGER NOT NULL,\n" +
                        "\"nombre_empresa\" VARCHAR(45) NOT NULL,\n" +
                        "\"cantidad_sucursales\" INTEGER NOT NULL,\n" +
                        "PRIMARY KEY (\"id_empresa\") ,\n" +
                        "CONSTRAINT \"fk_empresa_categoria_empresa_1\" FOREIGN KEY (\"id_categoria_emp\") REFERENCES \"categoria_empresa\" (\"id_categoria_emp\")\n" +
                        ");");
                db.execSQL("CREATE TABLE \"categoria_empresa\" (\n" +
                        "\"id_categoria_emp\" INTEGER NOT NULL,\n" +
                        "\"nombre_categoria_emp\" VARCHAR(50) NOT NULL,\n" +
                        "\"descripcion_categoria_emp\" VARCHAR(75) NOT NULL,\n" +
                        "\"cantidad_empresas\" INTEGER NOT NULL,\n" +
                        "PRIMARY KEY (\"id_categoria_emp\") \n" +
                        ");\n");
                db.execSQL("CREATE TABLE \"sucursal\" (\n" +
                        "\"id_sucursal\" INTEGER NOT NULL,\n" +
                        "\"id_empresa\" INTEGER NOT NULL,\n" +
                        "\"id_zona\" INTEGER NOT NULL,\n" +
                        "\"nombre_sucursal\" VARCHAR(50) NOT NULL,\n" +
                        "\"jefe_sucursal\" VARCHAR(50) NOT NULL,\n" +
                        "\"direccion_sucursal\" VARCHAR(80) NOT NULL,\n" +
                        "\"telefono_sucursal\" VARCHAR(9) NOT NULL,\n" +
                        "PRIMARY KEY (\"id_sucursal\") ,\n" +
                        "CONSTRAINT \"fk_sucursal_empresa_1\" FOREIGN KEY (\"id_empresa\") REFERENCES \"empresa\" (\"id_empresa\"),\n" +
                        "CONSTRAINT \"fk_sucursal_zona_1\" FOREIGN KEY (\"id_zona\") REFERENCES \"zona\" (\"id_zona\")\n" +
                        ");");
                db.execSQL("CREATE TABLE \"prod_serv\" (\n" +
                        "\"id_prod_serv\" INTEGER NOT NULL,\n" +
                        "\"id_categoria_prod\" INTEGER NOT NULL,\n" +
                        "\"nombre_prod_serv\" VARCHAR(35) NOT NULL,\n" +
                        "\"descripcion_prod_serv\" VARCHAR(80) NOT NULL,\n" +
                        "PRIMARY KEY (\"id_prod_serv\") ,\n" +
                        "CONSTRAINT \"fk_prod_serv_categoria_prod_serv_1\" FOREIGN KEY (\"id_categoria_prod\") REFERENCES \"categoria_prod_serv\" (\"id_categoria_prod\")\n" +
                        ");");
                db.execSQL("CREATE TABLE \"categoria_prod_serv\" (\n" +
                        "\"id_categoria_prod\" INTEGER NOT NULL,\n" +
                        "\"nombre_categoria_ps\" VARCHAR(35) NOT NULL,\n" +
                        "\"descripcion_categoria_ps\" VARCHAR(80) NOT NULL,\n" +
                        "\"cantidad_productos\" INTEGER NOT NULL,\n" +
                        "PRIMARY KEY (\"id_categoria_prod\") \n" +
                        ");");
                db.execSQL("CREATE TABLE \"estado_reclamo\" (\n" +
                        "\"id_estado_reclamo\" INTEGER NOT NULL,\n" +
                        "\"nombre_estado\" VARCHAR(25) NOT NULL,\n" +
                        "\"descripcion_estado\" VARCHAR(75) NOT NULL,\n" +
                        "PRIMARY KEY (\"id_estado_reclamo\") \n" +
                        ");");
                db.execSQL("CREATE TABLE \"detalle_reclamo\" (\n" +
                        "\"id_detalle\" INTEGER NOT NULL,\n" +
                        "\"id_prod_serv\" INTEGER NOT NULL,\n" +
                        "\"descripcion_detalle\" VARCHAR(80) NOT NULL,\n" +
                        "PRIMARY KEY (\"id_detalle\") ,\n" +
                        "CONSTRAINT \"fk_detalle_reclamo_prod_serv_1\" FOREIGN KEY (\"id_prod_serv\") REFERENCES \"prod_serv\" (\"id_prod_serv\")\n" +
                        ");");
                /**********************************TRIGGERS DE FK*********************************************/
                db.execSQL("CREATE TRIGGER fk_reclamo_estado_reclamo\n" +
                        "BEFORE INSERT ON reclamo\n" +
                        "FOR EACH ROW\n" +
                        "BEGIN\n" +
                        "SELECT CASE\n" +
                        "WHEN ((SELECT id_estado_reclamo FROM estado_reclamo WHERE id_estado_reclamo = NEW.id_estado_reclamo) IS NULL)\n" +
                        "THEN RAISE(ABORT, 'No existe ese estado de reclamo')\n" +
                        "END;\n" +
                        "END;");
                db.execSQL("\n" +
                        "CREATE TRIGGER fk_reclamo_sucursal\n" +
                        "BEFORE INSERT ON reclamo\n" +
                        "FOR EACH ROW\n" +
                        "BEGIN\n" +
                        "SELECT CASE\n" +
                        "WHEN ((SELECT id_sucursal FROM sucursal WHERE id_sucursal = NEW.id_sucursal) IS NULL)\n" +
                        "THEN RAISE(ABORT, 'No existe Sucursal con el ID ingresado')\n" +
                        "END;\n" +
                        "END;");
                db.execSQL("CREATE TRIGGER fk_reclamo_detalle_reclamo\n" +
                        "BEFORE INSERT ON reclamo\n" +
                        "FOR EACH ROW\n" +
                        "BEGIN\n" +
                        "SELECT CASE\n" +
                        "WHEN ((SELECT id_detalle FROM detalle_reclamo WHERE id_detalle = NEW.id_detalle) IS NULL)\n" +
                        "THEN RAISE(ABORT, 'No existe Detalle con el ID ingresado')\n" +
                        "END;\n" +
                        "END;");
                db.execSQL("CREATE TRIGGER fk_empresa_categoria_empresa\n" +
                        "BEFORE INSERT ON empresa\n" +
                        "FOR EACH ROW\n" +
                        "BEGIN\n" +
                        "SELECT CASE\n" +
                        "WHEN ((SELECT id_categoria_emp FROM categoria_empresa WHERE id_categoria_emp = NEW.id_categoria_emp) IS NULL)\n" +
                        "THEN RAISE(ABORT, 'No existe Categoria de Empresa con el ID ingresado')\n" +
                        "END;\n" +
                        "END;");
                db.execSQL("CREATE TRIGGER fk_prod_serv_categoria_prod_serv\n" +
                        "BEFORE INSERT ON prod_serv\n" +
                        "FOR EACH ROW\n" +
                        "BEGIN\n" +
                        "SELECT CASE\n" +
                        "WHEN ((SELECT id_categoria_prod FROM categoria_prod_serv WHERE id_categoria_prod = NEW.id_categoria_prod) IS NULL)\n" +
                        "THEN RAISE(ABORT, 'No existe Categoria de Producto o Servicio con el ID ingresado')\n" +
                        "END;\n" +
                        "END;");
                db.execSQL("CREATE TRIGGER fk_sucursal_empresa\n" +
                        "BEFORE INSERT ON sucursal\n" +
                        "FOR EACH ROW\n" +
                        "BEGIN\n" +
                        "SELECT CASE\n" +
                        "WHEN ((SELECT id_empresa FROM empresa WHERE id_empresa = NEW.id_empresa) IS NULL)\n" +
                        "THEN RAISE(ABORT, 'No existe Empresa con el ID ingresado')\n" +
                        "END;\n" +
                        "END;");
                db.execSQL("CREATE TRIGGER fk_sucursal_zona\n" +
                        "BEFORE INSERT ON sucursal\n" +
                        "FOR EACH ROW\n" +
                        "BEGIN\n" +
                        "SELECT CASE\n" +
                        "WHEN ((SELECT id_zona FROM zona WHERE id_zona = NEW.id_zona) IS NULL)\n" +
                        "THEN RAISE(ABORT, 'No existe Zona con el ID ingresado')\n" +
                        "END;\n" +
                        "END;");
                db.execSQL("CREATE TRIGGER fk_reclamo_usuario\n" +
                        "BEFORE INSERT ON reclamo\n" +
                        "FOR EACH ROW\n" +
                        "BEGIN\n" +
                        "SELECT CASE\n" +
                        "WHEN ((SELECT dui FROM usuario WHERE dui = NEW.dui) IS NULL)\n" +
                        "THEN RAISE(ABORT, 'No existe Usuario con el DUI ingresado')\n" +
                        "END;\n" +
                        "END;");
                /****************************TRIGGERS DE ACTUALIZACION***********************************/
                db.execSQL("CREATE TRIGGER update_cantidad_sucursales_insert\n" +
                        "AFTER INSERT ON sucursal\n" +
                        "BEGIN\n" +
                        "UPDATE empresa SET cantidad_sucursales = cantidad_sucursales + 1\n" +
                        "WHERE empresa.id_empresa = new.id_empresa;\n" +
                        "END;");
                db.execSQL("CREATE TRIGGER update_cantidad_sucursales_delete\n" +
                        "AFTER DELETE ON sucursal\n" +
                        "BEGIN\n" +
                        "UPDATE empresa SET cantidad_sucursales = cantidad_sucursales - 1\n" +
                        "WHERE empresa.id_empresa = old.id_empresa;\n" +
                        "END;");
                db.execSQL("CREATE TRIGGER update_cantidad_sucursales_update\n" +
                        "AFTER UPDATE OF id_empresa ON sucursal\n" +
                        "BEGIN\n" +
                        "UPDATE empresa SET cantidad_sucursales = cantidad_sucursales + 1\n" +
                        "WHERE empresa.id_empresa = new.id_empresa;\n" +
                        "UPDATE empresa SET cantidad_sucursales = cantidad_sucursales - 1\n" +
                        "WHERE empresa.id_empresa = old.id_empresa;\n" +
                        "END;");
                db.execSQL("CREATE TRIGGER update_cantidad_empresas_insert\n" +
                        "AFTER INSERT ON empresa\n" +
                        "BEGIN\n" +
                        "UPDATE categoria_empresa SET cantidad_empresas = cantidad_empresas + 1\n" +
                        "WHERE categoria_empresa.id_categoria_emp = new.id_categoria_emp;\n" +
                        "END;\n");
                db.execSQL("CREATE TRIGGER update_cantidad_empresas_delete\n" +
                        "AFTER DELETE ON empresa\n" +
                        "BEGIN\n" +
                        "UPDATE categoria_empresa SET cantidad_empresas = cantidad_empresas - 1\n" +
                        "WHERE categoria_empresa.id_categoria_emp = old.id_categoria_emp;\n" +
                        "END;");
                db.execSQL("CREATE TRIGGER update_cantidad_empresas_update\n" +
                        "AFTER UPDATE OF id_categoria_emp ON empresa\n" +
                        "BEGIN\n" +
                        "UPDATE categoria_empresa SET cantidad_empresas = cantidad_empresas + 1\n" +
                        "WHERE categoria_empresa.id_categoria_emp = new.id_categoria_emp;\n" +
                        "UPDATE categoria_empresa SET cantidad_empresas = cantidad_empresas - 1\n" +
                        "WHERE categoria_empresa.id_categoria_emp = old.id_categoria_emp;\n" +
                        "END;");
                db.execSQL("CREATE TRIGGER update_cantidad_productos_insert\n" +
                        "AFTER INSERT ON prod_serv\n" +
                        "BEGIN\n" +
                        "UPDATE categoria_prod_serv SET cantidad_productos = cantidad_productos + 1\n" +
                        "WHERE categoria_prod_serv.id_empresa = new.id_empresa;\n" +
                        "END");
                db.execSQL("CREATE TRIGGER update_cantidad_productos_delete\n" +
                        "AFTER DELETE ON prod_serv\n" +
                        "BEGIN\n" +
                        "UPDATE categoria_prod_serv SET cantidad_productos = cantidad_productos - 1\n" +
                        "WHERE categoria_prod_serv.id_empresa = OLD.id_empresa;\n" +
                        "END");
                db.execSQL("CREATE TRIGGER update_cantidad_productos_update\n" +
                        "AFTER UPDATE OF categoria_prod_serv ON prod_serv\n" +
                        "BEGIN\n" +
                        "UPDATE categoria_prod_serv SET cantidad_productos = cantidad_productos + 1\n" +
                        "WHERE categoria_prod_serv.id_categoria_prod = new.id_categoria_prod;\n" +
                        "UPDATE categoria_prod_serv SET cantidad_productos = cantidad_productos - 1\n" +
                        "WHERE categoria_prod_serv.id_categoria_prod = old.id_categoria_prod;\n" +
                        "END");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// TODO Auto-generated method stub
        }
    }

    public void abrir() throws SQLException {
        try {
            db = DBHelper.getWritableDatabase();
        } catch (NullPointerException e) {

        }
        return;

    }

    public void cerrar() {
        DBHelper.close();
    }


    /**
     * *************CRUD DE USUARIO*********************************
     */
    public String insertar(Usuario usuario) {
        String regInsertados = "Registro Insertado No= ";
        long contador = 0;
        ContentValues user = new ContentValues();
        user.put("dui", usuario.getDui());
        user.put("nombre_usuario", usuario.getNombreUsuario());
        user.put("apellido_usuario", usuario.getApellidoUsuario());
        user.put("email", usuario.getEmail());
        user.put("telefono", usuario.getTelefono());
        user.put("edad", usuario.getEdad());
        user.put("sexo", usuario.getSexo());
        contador = db.insert("usuario", null, user);
        if (contador == -1 || contador == 0) {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        } else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }

    public String actualizar(Usuario usuario) {
        if (verificarIntegridad(usuario, 6)) {//si existe el usuario con dui xxx
            String[] id = {usuario.getDui()};
            ContentValues user = new ContentValues();
            user.put("dui", usuario.getDui());
            user.put("nombre_usuario", usuario.getNombreUsuario());
            user.put("apellido_usuario", usuario.getApellidoUsuario());
            user.put("email", usuario.getEmail());
            user.put("telefono", usuario.getTelefono());
            user.put("edad", usuario.getEdad());
            user.put("sexo", usuario.getSexo());
            db.update("usuario", user, "dui = ?", id);
            return "Registro Actualizado Correctamente.";
        } else {
            return "Registro con DUI " + usuario.getDui() + " no existe";
        }

    }

    public String eliminar(Usuario usuario) {
        String regAfectados = "filas afectadas= ";
        int contador = 0;
        if (false) {// si existen reclamos del usuario eliminamos los reclamos
            contador += db.delete("reclamo", "dui = '" + usuario.getDui() + "'", null);
        }
        contador += db.delete("usuario", "dui = '" + usuario.getDui() + "'", null);
        regAfectados += contador;
        return regAfectados;
    }

    public Usuario consultarUsuario(String dui) {
        String[] id = {dui};
        Cursor cursor = db.query("usuario", camposUsuario, "dui = ?", id, null, null, null);
        if (cursor.moveToFirst()) {
            Usuario usuario = new Usuario();
            usuario.setDui(cursor.getString(0));
            usuario.setNombreUsuario(cursor.getString(1));
            usuario.setApellidoUsuario(cursor.getString(2));
            usuario.setEmail(cursor.getString(3));
            usuario.setTelefono(cursor.getString(4));
            usuario.setEdad(cursor.getInt(5));
            usuario.setSexo(cursor.getString(6));
            return usuario;
        } else
            return null;
    }

    /**
     * *******************METODOS CRUD DE ZONA**********************************
     */
    public String insertar(Zona zona) {
        String regInsertados = "Registro Insertado No= ";
        long contador = 0;
        ContentValues zone = new ContentValues();
        zone.put("id_zona", zona.getIdZona());
        zone.put("nombre_zona", zona.getNombreZona());
        zone.put("municipio", zona.getMunicipio());
        zone.put("departamento", zona.getDepartamento());
        contador = db.insert("zona", null, zone);
        if (contador == -1 || contador == 0) {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        } else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }

    public String actualizar(Zona zona) {
        if (verificarIntegridad(zona, 7)) {//si existe el zona con id xxx
            String[] id = {Integer.toString(zona.getIdZona())};
            ContentValues cv = new ContentValues();
            cv.put("id_zona", zona.getIdZona());
            cv.put("nombre_zona", zona.getNombreZona());
            cv.put("municipio", zona.getMunicipio());
            cv.put("departamento", zona.getDepartamento());
            db.update("zona", cv, "id_zona = ?", id);
            return "Registro Actualizado Correctamente.";
        } else {
            return "Registro con ID " + zona.getIdZona() + " no existe";
        }
    }

    public String eliminar(Zona zona) {
        String regAfectados = "filas afectadas= ";
        int contador = 0;
        if (false) {// si existen sucursales con la zona ingresada
            contador += db.delete("sucursal", "id_zona = '" + zona.getIdZona() + "'", null);
        }
        contador += db.delete("zona", "id_zona = '" + zona.getIdZona() + "'", null);
        regAfectados += contador;
        return regAfectados;
    }

    public Zona consultarZona(String idZona) {
        String[] id = {idZona};
        Cursor cursor = db.query("zona", camposZona, "id_zona = ?", id, null, null, null);
        if (cursor.moveToFirst()) {
            Zona zona = new Zona();
            zona.setIdZona(cursor.getInt(0));
            zona.setNombreZona(cursor.getString(1));
            zona.setMunicipio(cursor.getString(2));
            zona.setDepartamento(cursor.getString(3));
            return zona;
        } else
            return null;
    }

    /**
     * ********************CRUD CATEGORIA PROD SERV******************************
     */
    public String insertar(CategoriaProdServ categoriaProdServ) {
        String regInsertados = "Registro Insertado No= ";
        long contador = 0;
        ContentValues cps = new ContentValues();
        cps.put("id_categoria_prod", categoriaProdServ.getIdCategoriaProdServ());
        cps.put("nombre_categoria_ps", categoriaProdServ.getNombreCategoriaPs());
        cps.put("descripcion_categoria_ps", categoriaProdServ.getDescripcionCategoriaPs());
        cps.put("cantidad_productos", categoriaProdServ.getCantidadProductos());
        contador = db.insert("categoria_prod_serv", null, cps);
        if (contador == -1 || contador == 0) {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        } else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }

    public String actualizar(CategoriaProdServ categoriaProdServ) {
        if (verificarIntegridad(categoriaProdServ, 8)) {//si existe la categoria con id ingresado
            String[] id = {Integer.toString(categoriaProdServ.getIdCategoriaProdServ())};
            ContentValues user = new ContentValues();
            user.put("id_categoria_prod", categoriaProdServ.getIdCategoriaProdServ());
            user.put("nombre_categoria_ps", categoriaProdServ.getNombreCategoriaPs());
            user.put("descripcion_categoria_ps", categoriaProdServ.getDescripcionCategoriaPs());
            user.put("cantidad_productos", categoriaProdServ.getCantidadProductos());
            db.update("categoria_prod_serv", user, "id_categoria_prod = ?", id);
            return "Registro Actualizado Correctamente.";
        } else {
            return "Registro con ID " + categoriaProdServ.getIdCategoriaProdServ() + " no existe";
        }
    }

    public String eliminar(CategoriaProdServ categoriaProdServ) {
        return "";
    }

    public CategoriaProdServ consultarCategoriaProdServ(int idCategoriaProd) {
        return null;
    }

    /**
     * ********************CRUD PROD SERV**************************************
     */
    public String insertar(ProdServ prodServ) {
        String regInsertados = "Registro Insertado No= ";
        long contador = 0;
        if (verificarIntegridad(prodServ, 1)) {
            ContentValues ps = new ContentValues();
            ps.put("id_prod_serv", prodServ.getIdProdServ());
            ps.put("id_categoria_prod", prodServ.getIdCategoriaProd());
            ps.put("nombre_prod_serv", prodServ.getNombreProdServ());
            ps.put("descripcion_prod_serv", prodServ.getDescripcionProdServ());
            contador = db.insert("prod_serv", null, ps);
        }
        if (contador == -1 || contador == 0) {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        } else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }

    public String actualizar(ProdServ prodServ) {
        if (verificarIntegridad(prodServ, 9)) {//si existe el usuario con dui xxx
            String[] id = {Integer.toString(prodServ.getIdProdServ())};
            ContentValues user = new ContentValues();
            user.put("id_prod_serv", prodServ.getIdProdServ());
            user.put("id_categoria_prod", prodServ.getIdCategoriaProd());
            user.put("nombre_prod_serv", prodServ.getNombreProdServ());
            user.put("descripcion_prod_serv", prodServ.getDescripcionProdServ());
            db.update("prod_serv", user, "id_prod_serv = ?", id);
            return "Registro Actualizado Correctamente.";
        } else {
            return "Registro con ID " + prodServ.getIdProdServ() + " no existe";
        }
    }

    public String eliminar(ProdServ prodServ) {
        return "";
    }

    public ProdServ consultarProdServ(int idProdServ) {
        return null;
    }

    /**
     * **************************CRUD ESTADO RECLAMO*****************************
     */
    public String insertar(EstadoReclamo estadoReclamo) {
        String regInsertados = "Registro Insertado No= ";
        long contador = 0;
        ContentValues er = new ContentValues();
        er.put("id_estado_reclamo", estadoReclamo.getIdEstadoReclamo());
        er.put("nombre_estado", estadoReclamo.getNombreEstado());
        er.put("descripcion_estado", estadoReclamo.getDescripcionEstado());
        contador = db.insert("estado_reclamo", null, er);
        if (contador == -1 || contador == 0) {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        } else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }

    public String actualizar(EstadoReclamo estadoReclamo) {
        if (verificarIntegridad(estadoReclamo, 10)) {//si existe estado reclamo con el id ingresado
            String[] id = {Integer.toString(estadoReclamo.getIdEstadoReclamo())};
            ContentValues er = new ContentValues();
            er.put("id_estado_reclamo", estadoReclamo.getIdEstadoReclamo());
            er.put("nombre_estado", estadoReclamo.getNombreEstado());
            er.put("descripcion_estado", estadoReclamo.getDescripcionEstado());
            db.update("estado_reclamo", er, "id_estado_reclamo = ?", id);
            return "Registro Actualizado Correctamente.";
        } else {
            return "Registro con ID " + estadoReclamo.getIdEstadoReclamo() + " no existe";
        }
    }

    public String eliminar(EstadoReclamo estadoReclamo) {
        return "";
    }

    public EstadoReclamo consultarEstadoReclamo(int idEstadoReclamo) {
        return null;
    }

    /**
     * ************************CRUD DETALLE RECLAMO*******************************
     */
    public String insertar(DetalleReclamo detalleReclamo) {
        String regInsertados = "Registro Insertado No= ";
        long contador = 0;
        if (verificarIntegridad(detalleReclamo, 2)) {
            ContentValues dr = new ContentValues();
            dr.put("id_detalle", detalleReclamo.getIdDetalle());
            dr.put("id_prod_serv", detalleReclamo.getIdProdServ());
            dr.put("descripcion_detalle", detalleReclamo.getDescripcionDetalle());
            contador = db.insert("detalle_reclamo", null, dr);
        }
        if (contador == -1 || contador == 0) {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        } else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }

    public String actualizar(DetalleReclamo detalleReclamo) {
        if (verificarIntegridad(detalleReclamo, 11)) {//si existe detalle reclamo con el id ingresado
            String[] id = {Integer.toString(detalleReclamo.getIdDetalle())};
            ContentValues er = new ContentValues();
            er.put("id_detalle", detalleReclamo.getIdDetalle());
            er.put("id_prod_serv", detalleReclamo.getIdProdServ());
            er.put("descripcion_detalle", detalleReclamo.getDescripcionDetalle());
            db.update("detalle_reclamo", er, "id_detalle = ?", id);
            return "Registro Actualizado Correctamente.";
        } else {
            return "Registro con ID " + detalleReclamo.getIdDetalle() + " no existe";
        }
    }

    public String eliminar(DetalleReclamo detalleReclamo) {
        return "";
    }

    public DetalleReclamo consultarDetalleReclamo(int idDetaleReclamo) {
        return null;
    }

    /**
     * **************************CRUD RECLAMO*************************************
     */
    public String insertar(Reclamo reclamo) {
        String regInsertados = "Registro Insertado No= ";
        long contador = 0;
        if (verificarIntegridad(reclamo, 3)) {
            ContentValues dr = new ContentValues();
            dr.put("id_reclamo", reclamo.getIdDetalle());
            dr.put("dui", reclamo.getDui());
            dr.put("id_estado_reclamo", reclamo.getIdEstadoReclamo());
            dr.put("id_sucursal", reclamo.getIdSucursal());
            dr.put("id_detalle", reclamo.getIdDetalle());
            dr.put("titulo", reclamo.getTitulo());
            dr.put("motivo_reclamo", reclamo.getMotivoReclamo());
            dr.put("fecha_reclamo", reclamo.getFechaReclamo());
            contador = db.insert("reclamo", null, dr);
        }
        if (contador == -1 || contador == 0) {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        } else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }

    public String actualizar(Reclamo reclamo) {
        if (verificarIntegridad(reclamo, 12)) {//si existe  reclamo con el id ingresado
            String[] id = {Integer.toString(reclamo.getIdReclamo())};
            ContentValues er = new ContentValues();
            er.put("id_reclamo", reclamo.getIdReclamo());
            er.put("dui", reclamo.getDui());
            er.put("id_estado_reclamo", reclamo.getIdEstadoReclamo());
            er.put("id_sucursal", reclamo.getIdSucursal());
            er.put("id_detalle", reclamo.getIdDetalle());
            er.put("titulo", reclamo.getTitulo());
            er.put("motivo_reclamo", reclamo.getMotivoReclamo());
            er.put("fecha_reclamo", reclamo.getFechaReclamo());
            db.update("reclamo", er, "id_reclamo = ?", id);
            return "Registro Actualizado Correctamente.";
        } else {
            return "Registro con ID " + reclamo.getIdReclamo() + " no existe";
        }
    }

    public String eliminar(Reclamo reclamo) {
        return "";
    }

    public Reclamo consultarReclamo(int idReclamo) {
        return null;
    }

    /**
     * ************************CRUD EMPRESA****************************************
     */
    public String insertar(Empresa empresa) {
        String regInsertados = "Registro Insertado No= ";
        long contador = 0;
        ContentValues emp = new ContentValues();
        if (verificarIntegridad(empresa, 4)) {
            emp.put("id_empresa", empresa.getIdEmpresa());
            emp.put("id_categoria_empresa", empresa.getIdCategoriaEmp());
            emp.put("nombre_empresa", empresa.getNombreEmpresa());
            emp.put("cantidad_sucursales", empresa.getCantidadSucursales());
            contador = db.insert("empresa", null, emp);
        }
        if (contador == -1 || contador == 0) {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        } else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }

    public String actualizar(Empresa empresa) {
        if (verificarIntegridad(empresa, 13)) {//si existe empresa con el id ingresado
            String[] id = {Integer.toString(empresa.getIdEmpresa())};
            ContentValues em = new ContentValues();
            em.put("id_empresa", empresa.getIdEmpresa());
            em.put("id_categoria_emp", empresa.getIdCategoriaEmp());
            em.put("nombre_empresa", empresa.getNombreEmpresa());
            em.put("cantidad_sucursales", empresa.getCantidadSucursales());
            db.update("empresa", em, "id_empresa = ?", id);
            return "Registro Actualizado Correctamente.";
        } else {
            return "Registro con ID " + empresa.getIdEmpresa() + " no existe";
        }
    }

    public String eliminar(Empresa empresa) {
        return "";
    }

    public Reclamo consultarEmpresa(int idEmpresa) {
        return null;
    }

    /**
     * ************************CRUD CATEGORIA EMPRESA******************************
     */
    public String insertar(CategoriaEmpresa categoriaEmpresa) {
        String regInsertados = "Registro Insertado No= ";
        long contador = 0;
        ContentValues cemp = new ContentValues();
        cemp.put("id_categoria_emp", categoriaEmpresa.getIdCategoriaEmp());
        cemp.put("nombre_categoria_emp", categoriaEmpresa.getNombreCategoriaEmp());
        cemp.put("descripcion_categoria_emp", categoriaEmpresa.getDescripcionCategoriaEmp());
        cemp.put("cantidad_empresas", categoriaEmpresa.getCantidadEmpresas());
        contador = db.insert("categoria_empresa", null, cemp);
        if (contador == -1 || contador == 0) {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        } else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }

    public String actualizar(CategoriaEmpresa categoriaEmpresa) {
        if (verificarIntegridad(categoriaEmpresa, 14)) {//si existe categoria empresa con el id ingresado
            String[] id = {Integer.toString(categoriaEmpresa.getIdCategoriaEmp())};
            ContentValues er = new ContentValues();
            er.put("id_categoria_emp", categoriaEmpresa.getIdCategoriaEmp());
            er.put("nombre_categoria_emp", categoriaEmpresa.getNombreCategoriaEmp());
            er.put("descripcion_categoria_emp", categoriaEmpresa.getDescripcionCategoriaEmp());
            er.put("cantidad_empresas", categoriaEmpresa.getCantidadEmpresas());
            db.update("categoria_empresa", er, "id_categoria_emp = ?", id);
            return "Registro Actualizado Correctamente.";
        } else {
            return "Registro con ID " + categoriaEmpresa.getIdCategoriaEmp() + " no existe";
        }
    }

    public String eliminar(CategoriaEmpresa categoriaEmpresa) {
        return "";
    }

    public Reclamo consultarCategoriaEmpresa(int idCategoriaEmpresa) {
        return null;
    }

    /**
     * ************************CRUD SUCURSAL***************************************
     */
    public String insertar(Sucursal sucursal) {
        String regInsertados = "Registro Insertado No= ";
        long contador = 0;
        ContentValues sc = new ContentValues();
        if (verificarIntegridad(sucursal, 5)) {
            sc.put("id_sucursal", sucursal.getIdSucursal());
            sc.put("id_empresa", sucursal.getIdEmpresa());
            sc.put("id_zona", sucursal.getIdZona());
            sc.put("nombre_sucursal", sucursal.getNombreSucursal());
            sc.put("jefe_sucursal", sucursal.getJefeSucursal());
            sc.put("direccion_sucursal", sucursal.getDireccionSucursal());
            sc.put("telefono_sucursal", sucursal.getTelefonoSucursal());
        }
        contador = db.insert("sucursal", null, sc);
        if (contador == -1 || contador == 0) {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        } else {
            regInsertados = regInsertados + contador;
        }
        return regInsertados;
    }

    public String actualizar(Sucursal sucursal) {
        if (verificarIntegridad(sucursal, 15)) {//si existe estado reclamo con el id ingresado
            String[] id = {Integer.toString(sucursal.getIdSucursal())};
            ContentValues sc = new ContentValues();
            sc.put("id_sucursal", sucursal.getIdSucursal());
            sc.put("id_empresa", sucursal.getIdEmpresa());
            sc.put("id_zona", sucursal.getIdZona());
            sc.put("nombre_sucursal", sucursal.getNombreSucursal());
            sc.put("jefe_sucursal", sucursal.getJefeSucursal());
            sc.put("direccion_sucursal", sucursal.getDireccionSucursal());
            sc.put("telefono_sucursal", sucursal.getTelefonoSucursal());
            db.update("sucursal", sc, "id_sucursal = ?", id);
            return "Registro Actualizado Correctamente.";
        } else {
            return "Registro con ID " + sucursal.getIdSucursal() + " no existe";
        }
    }

    public String eliminar(Sucursal sucursal) {
        return "";
    }

    public Reclamo consultarSucursal(int idSucursal) {
        return null;
    }

    /**
     * ****************************************************************************
     */
    private boolean verificarIntegridad(Object dato, int relacion) throws SQLException {
        switch (relacion) {
            case 1:// si existe la categoria del producto o servicio para el prod ingresado
            {
                ProdServ prodServ = (ProdServ) dato;
                String[] id1 = {Integer.toString(prodServ.getIdCategoriaProd())};
                Cursor c1 = db.query("categoria_prod_serv", null, "id_categoria_prod = ?", id1, null, null, null);
                if (c1.moveToFirst()) {
                    return true;
                }
                return false;
            }

            case 2:// si existe el producto o servicio para el detalle ingresado
            {
                DetalleReclamo detalleReclamo = (DetalleReclamo) dato;
                String[] id1 = {Integer.toString(detalleReclamo.getIdProdServ())};
                Cursor c1 = db.query("prod_serv", null, "id_prod_serv = ?", id1, null, null, null);
                if (c1.moveToFirst()) {
                    return true;
                }
                return false;
            }

            case 3: // si existen dui, estado reclamo, sucursal y detalle para el reclamo ingresado
            {
                Reclamo reclamo = (Reclamo) dato;
                String[] id1 = {reclamo.getDui()};
                String[] id2 = {Integer.toString(reclamo.getIdEstadoReclamo())};
                String[] id3 = {Integer.toString(reclamo.getIdSucursal())};
                String[] id4 = {Integer.toString(reclamo.getIdDetalle())};
                Cursor c1 = db.query("usuario", null, "dui = ?", id1, null, null, null);
                Cursor c2 = db.query("estado_reclamo", null, "id_estado_reclamo = ?", id2, null, null, null);
                Cursor c3 = db.query("sucursal", null, "id_sucursal = ?", id3, null, null, null);
                Cursor c4 = db.query("detalle_reclamo", null, "id_detalle = ?", id4, null, null, null);
                if (c1.moveToFirst() && c2.moveToFirst() && c3.moveToFirst() && c4.moveToFirst()) {
                    return true;
                }
                return false;
            }

            case 4: // si existe categoria de empresa para la empresa ingresada
            {
                Empresa empresa = (Empresa) dato;
                String[] id1 = {Integer.toString(empresa.getIdCategoriaEmp())};
                Cursor c1 = db.query("categoria_empresa", null, "id_categoria_emp = ?", id1, null, null, null);
                if (c1.moveToFirst()) {
                    return true;
                }
                return false;
            }

            case 5: // si existe una empresa y zona para la sucursal ingresada
            {
                Sucursal sucursal = (Sucursal) dato;
                String[] id1 = {Integer.toString(sucursal.getIdEmpresa())};
                String[] id2 = {Integer.toString(sucursal.getIdZona())};
                Cursor c1 = db.query("empresa", null, "id_empresa = ?", id1, null, null, null);
                Cursor c2 = db.query("zona", null, "id_zona = ?", id2, null, null, null);
                if (c1.moveToFirst() && c2.moveToFirst()) {
                    return true;
                }
                return false;
            }

            case 6: //si existe usuario con el dui ingresado
            {
                Usuario usuario = (Usuario) dato;
                String[] id1 = {usuario.getDui()};
                Cursor c1 = db.query("usuario", null, "dui = ?", id1, null, null, null);
                if (c1.moveToFirst()) {
                    return true;
                }
                return false;
            }

            case 7: //si existe zona con el id ingresado
            {
                Zona zona = (Zona) dato;
                String[] id1 = {Integer.toString(zona.getIdZona())};
                Cursor c1 = db.query("zona", null, "id_zona = ?", id1, null, null, null);
                if (c1.moveToFirst()) {
                    return true;
                }
                return false;
            }

            case 8: //si existe categoria prod serv con id ingresado
            {
                CategoriaProdServ categoriaProdServ = (CategoriaProdServ) dato;
                String[] id1 = {Integer.toString(categoriaProdServ.getIdCategoriaProdServ())};
                Cursor c1 = db.query("categoria_prod_serv", null, "id_categoria_prod = ?", id1, null, null, null);
                if (c1.moveToFirst()) {
                    return true;
                }
                return false;
            }

            case 9:// si existe producto o servicio con el id ingresado
            {
                ProdServ prodServ = (ProdServ) dato;
                String[] id1 = {Integer.toString(prodServ.getIdProdServ())};
                Cursor c1 = db.query("prod_serv", null, "id_prod_serv = ?", id1, null, null, null);
                if (c1.moveToFirst()) {
                    return true;
                }
                return false;
            }

            case 10:// si existe el id para el estado del reclamo ingresado
            {
                EstadoReclamo estadoReclamo = (EstadoReclamo) dato;
                String[] id1 = {Integer.toString(estadoReclamo.getIdEstadoReclamo())};
                Cursor c1 = db.query("estado_reclamo", null, "id_estado_reclamo = ?", id1, null, null, null);
                if (c1.moveToFirst()) {
                    return true;
                }
                return false;
            }

            case 11:// existe el id para el detalle reclamo que se ha introducido
            {
                DetalleReclamo detalleReclamo = (DetalleReclamo) dato;
                String[] id1 = {Integer.toString(detalleReclamo.getIdDetalle())};
                Cursor c1 = db.query("detalle_reclamo", null, "id_detalle = ?", id1, null, null, null);
                if (c1.moveToFirst()) {
                    return true;
                }
                return false;
            }

            case 12: // si existe el id para el reclamo ingresado
            {
                Reclamo reclamo = (Reclamo) dato;
                String[] id1 = {Integer.toString(reclamo.getIdReclamo())};
                Cursor c1 = db.query("reclamo", null, "id_reclamo = ?", id1, null, null, null);
                if (c1.moveToFirst()) {
                    return true;
                }
                return false;
            }
            case 13: // si existe id para la empresa ingresada
            {
                Empresa empresa = (Empresa) dato;
                String[] id1 = {Integer.toString(empresa.getIdEmpresa())};
                Cursor c1 = db.query("empresa", null, "id_empresa = ?", id1, null, null, null);
                if (c1.moveToFirst()) {
                    return true;
                }
                return false;
            }
            case 14: // si existe la id para la categoria ingresada
            {
                CategoriaEmpresa categoriaEmpresa = (CategoriaEmpresa) dato;
                String[] id1 = {Integer.toString(categoriaEmpresa.getIdCategoriaEmp())};
                Cursor c1 = db.query("categoria_empresa", null, "id_categoria_emp = ?", id1, null, null, null);
                if (c1.moveToFirst()) {
                    return true;
                }
                return false;
            }

            case 15: // si existe una empresa y zona para la sucursal ingresada
            {
                Sucursal sucursal = (Sucursal) dato;
                String[] id1 = {Integer.toString(sucursal.getIdSucursal())};
                Cursor c1 = db.query("sucursal", null, "id_sucursal = ?", id1, null, null, null);
                if (c1.moveToFirst()) {
                    return true;
                }
                return false;
            }

            default: {
                return false;
            }
        }

    }

    public String llenarDB() {
        final String[] VUdui = {"01998234-2", "01992384-3"};
        final String[] VUnombre = {"Juan Ernesto", "Chepe Toño"};
        final String[] VUapellido = {"Rivera", "Perez"};
        final String[] VUemail = {"juan@gmail.com", "chepin@gmail.com"};
        final String[] VUtelefono = {"2222-2222", "2222-2221"};
        final int[] VUedad = {35, 12};
        final String[] VUsexo = {"M", "M"};
        abrir();
        db.execSQL("DELETE FROM usuario");
        Usuario usuario = new Usuario();
        for (int i = 0; i < 2; i++) {
            usuario.setDui(VUdui[i]);
            usuario.setNombreUsuario(VUnombre[i]);
            usuario.setApellidoUsuario(VUapellido[i]);
            usuario.setTelefono(VUtelefono[i]);
            usuario.setEmail(VUemail[i]);
            usuario.setEdad(VUedad[i]);
            usuario.setSexo(VUsexo[i]);
            insertar(usuario);
        }
        cerrar();
        return "Guardo Correctamente";
    }


}




