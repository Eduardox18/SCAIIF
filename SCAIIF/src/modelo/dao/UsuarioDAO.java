package modelo.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modelo.Cifrado;
import modelo.mybatis.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import servicios.pojos.Usuario;

/**
 *
 * @author lalo
 */
public class UsuarioDAO {

    public static Usuario recuperarAsesor(int noPersonal) throws Exception {
        Usuario nombreAsesor = null;
        SqlSession conn = null;
        try {
            conn = MyBatisUtils.getSession();
            nombreAsesor = conn.selectOne("Usuario.getNombreUsuario", noPersonal);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return nombreAsesor;
    }

    public static Usuario recuperarUsuario(int noPersonal) throws Exception {
        Usuario ingresado = new Usuario();
        SqlSession conn = null;
        try {
            conn = MyBatisUtils.getSession();
            ingresado = conn.selectOne("Usuario.getUsuario", noPersonal);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return ingresado;
    }
}
