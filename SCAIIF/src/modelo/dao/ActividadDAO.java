package modelo.dao;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import modelo.mybatis.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import modelo.pojos.Actividad;
import modelo.pojos.ActividadAsesor;

public class ActividadDAO {

    /**
     * Recupera el historial de actividades que realizará o realizó el Asesor
     * con el identificador de noPersonal determinado. 
     * @param noPersonal identificador del Asesor.
     * @return lista de las actividades realizadas y por realizar del Asesor.
     * @throws Exception 
     */
    public static List<Actividad> recuperarHistorial(int noPersonal) throws Exception {
        SqlSession conn = null;
        List<Actividad> historialReservaciones = new ArrayList<>();
        try {
            conn = MyBatisUtils.getSession();
            historialReservaciones = conn.selectList("Actividad.getActividades", noPersonal);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return historialReservaciones;
    }

    /**
     * Método que permite recuperar las actividades asociadas a un asesor
     * @param noPersonal Número de personal del asesor
     * @param fecha Fecha actual
     * @return Regresa una lista de actividades
     * @throws Exception 
     */
    public static List<Actividad> recuperarActividadesAsesor(int noPersonal, Date fecha)
        throws Exception {
        List<Actividad> actividadesProximas = new ArrayList<>();
        Actividad actividad = new Actividad();
        actividad.setNoPersonal(noPersonal);
        actividad.setFecha(fecha);
        SqlSession conn = null;
        try {
            conn = MyBatisUtils.getSession();
            actividadesProximas = conn.selectList("Actividad.recuperarActividadesAsesor",
                actividad);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return actividadesProximas;
    }

    /**
     * Recupera el nombre de cada noActividad registrado en la base de datos.
     * @return 
     * @throws java.io.IOException 
     */
    public static List<Actividad> recuperarNombreActividad(String fecha) throws IOException {
        List<Actividad> nombreActividad = new ArrayList();
        SqlSession conn = null;
        try {
            conn = MyBatisUtils.getSession();
            nombreActividad = conn.selectList("Actividad.getnoActividad", fecha);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return nombreActividad;
    }
    
    /**
     * Recupera una lista de las actividades pendientes
     * @return List que contiene las actividades pendientes que no se encuentren canceladas
     * @throws IOException 
     */
    public static List<ActividadAsesor> recuperarActividadesPendientes() throws IOException {
        List<ActividadAsesor> actividadesPendientes;
        try(SqlSession conn = MyBatisUtils.getSession()){
            actividadesPendientes = conn.selectList("Actividad.getActividadesPendientes");
        } 
        
        return actividadesPendientes;
    }
    
    /**
     * Actualiza el estado de una actividad
     * @param noActividad La actividad a la que se desea actualizar el estado
     * @param estado Número que representa el estado que tendrá la actividad
     * @return 
     * @throws IOException 
     */
    public static boolean actualizarEstado (Integer noActividad, Integer estado) throws IOException{
        boolean resultado = false;
        HashMap<String, Integer> params = new HashMap<>();
        params.put("noActividad", noActividad);
        params.put("idEstado", estado);
        
        try(SqlSession conn = MyBatisUtils.getSession()){
            conn.update("Actividad.cambiarEstado", params);
            conn.commit();
            resultado = true;
        } 
        return resultado;
    }
    
    /**
     * Devuelve las actividades en las que no se haya inscrito un alumno determinado
     * @param matricula La matrícula del alumno
     * @param nrc
     * @return Lista con las actividades disponibles
     * @throws Exception 
     */
    public static List<ActividadAsesor> recuperarActividadesDisponibles(String matricula, Integer nrc) throws Exception {
        List <ActividadAsesor> listaActividades;
        HashMap<String, Object> params = new HashMap<>();
        params.put("matricula", matricula);
        params.put("nrc", nrc);
        
        try(SqlSession conn = MyBatisUtils.getSession()){
            listaActividades = conn.selectList("Actividad.actividadesDisponibles", params);
        }
        
        return listaActividades;
    } 
}
