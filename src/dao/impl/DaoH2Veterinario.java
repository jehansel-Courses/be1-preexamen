package dao.impl;

import dao.IDao;
import model.Veterinario;
import org.apache.log4j.Logger;

import java.util.List;


public class DaoH2Veterinario implements IDao<Veterinario> {

    public static final Logger logger = Logger.getLogger(DaoH2Veterinario.class);
    public static final String INSERT = "INSERT INTO VETERINARIOS VALUES(DEFAULT,?,?,?,?)";
    public static  final String SELECT_ALL = "SELECT * FROM VETERINARIOS";

    @Override
    public Veterinario guardar(Veterinario veterinario) {

        return null;
    }

    @Override
    public List<Veterinario> listarTodos() {
        return List.of();
    }
}
