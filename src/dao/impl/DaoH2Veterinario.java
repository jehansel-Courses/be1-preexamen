package dao.impl;

import dao.IDao;
import db.H2Connection;
import model.Veterinario;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DaoH2Veterinario implements IDao<Veterinario> {

    public static final Logger logger = Logger.getLogger(DaoH2Veterinario.class);
    public static final String INSERT = "INSERT INTO VETERINARIOS VALUES(DEFAULT,?,?,?,?)";
    public static  final String SELECT_ALL = "SELECT * FROM VETERINARIOS";

    @Override
    public Veterinario guardar(Veterinario veterinario) {

        Connection connection = null;
        Veterinario veterinarioARetornar = null;
        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, veterinario.getNroLicencia());
            preparedStatement.setString(2, veterinario.getNombre());
            preparedStatement.setString(3, veterinario.getApellido());
            preparedStatement.setString(4, veterinario.getEspecialidad());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()){
                Integer idDesdeDB = resultSet.getInt(1);
                veterinarioARetornar = new Veterinario(idDesdeDB, veterinario.getNroLicencia(), veterinario.getNombre(),
                        veterinario.getApellido(), veterinario.getEspecialidad());
            }
            logger.info("Veterinario guardado en base de datos"+ veterinarioARetornar );

            connection.commit();

        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return veterinarioARetornar;
    }

    @Override
    public List<Veterinario> buscarTodos() {
        Connection connection = null;
        List<Medicamento> medicamentos = new ArrayList<>();
        Medicamento medicamentoDesdeLaDB = null;
        try{
            connection = H2Connection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()){
                Integer id = resultSet.getInt(1);
                String nombreMed = resultSet.getString(2);
                String laboratorio = resultSet.getString(3);
                int cantidad = resultSet.getInt(4);
                double precio = resultSet.getDouble(5);
                medicamentoDesdeLaDB = new Medicamento(id, nombreMed, laboratorio, cantidad, precio);
                // vamos cargando la lista de medicamentos
                medicamentos.add(medicamentoDesdeLaDB);
                logger.info("medicamento "+ medicamentoDesdeLaDB);
            }

        }catch (Exception e){
            logger.error(e.getMessage());
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        return medicamentos;

    }
}
