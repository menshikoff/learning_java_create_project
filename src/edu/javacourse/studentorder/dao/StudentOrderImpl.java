package edu.javacourse.studentorder.dao;

import edu.javacourse.studentorder.config.Config;
import edu.javacourse.studentorder.domain.*;
import edu.javacourse.studentorder.exeption.DaoException;

import java.sql.*;
import java.time.LocalDateTime;

public class StudentOrderImpl implements StudentOrderDao{

    private static final String INSERT_ORDER =

    "INSERT INTO st_student_order(" +
            "student_order_status, student_order_date, " +
            "h_su_name, h_given_name, h_patronymic, h_date_of_birth, h_passport_seria, " +
            "h_passport_number, h_passport_date, h_passport_office_id, h_post_index, " +
            "h_street_code, h_building, h_extension, h_apartment, w_su_name, w_given_name, " +
            "w_patronymic, w_date_of_birth, w_passport_seria, w_passport_number, w_passport_date, " +
            "w_passport_office_id, w_post_index, w_street_code, w_building, w_extension, w_apartment, " +
            "certificate_id, register_office_id, marriage_date) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
            "?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String INSERT_CHILD =
            "INSERT INTO st_student_child(" +
                    "student_order_id, c_su_name, " +
                    "c_given_name, c_patronymic, c_date_of_birth, c_certificate_number, " +
                    "c_certificate_date, c_register_office_id, c_post_index, c_street_code, " +
                    "c_building, c_extention, c_appartment)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private Connection getConnection() throws SQLException {

        Connection con = DriverManager.getConnection(
                Config.getProperty(Config.DB_URL),
                Config.getProperty(Config.DB_LOGIN),
                Config.getProperty(Config.DB_PASSWORD));
        return con;
    }

    @Override
    public Long saveStudentOrder(StudentOrder so) throws DaoException {
        Long result = -1L;

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(INSERT_ORDER, new String[]{"student_order_id"}))
        {
            con.setAutoCommit(false);
            try {
                //Header
                stmt.setInt(1, StudentOrderStatus.START.ordinal());
                stmt.setTimestamp(2, java.sql.Timestamp.valueOf(LocalDateTime.now()));

                // Set params to Husband and Wife
                setSqlParams(stmt, 3, so.getHusband());
                setSqlParams(stmt, 16, so.getWife());

                stmt.setString(29, so.getMarriageCertificateID());
                stmt.setLong(30, so.getMarriageOffice().getOfficeId());
                stmt.setDate(31, java.sql.Date.valueOf(so.getMarriageDate()));

                stmt.executeUpdate();

                ResultSet gkRs = stmt.getGeneratedKeys();
                if (gkRs.next()) {
                    result = gkRs.getLong(1);
                }

                saveChildren(con, so, result);
                con.commit();
            } catch(SQLException ex) {
                con.rollback();
                throw ex;
            }

        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return result;
    }

    private void saveChildren(Connection con, StudentOrder so, Long soID) throws SQLException {

        try (PreparedStatement stmt = con.prepareStatement(INSERT_CHILD)) {

            for (Child child : so.getChildren()) {
                stmt.setLong(1, soID);
                stmt.setString(2, child.getLastName());
                stmt.setString(3, child.getFirstName());
                stmt.setString(4, child.getPatronymic());
                stmt.setDate(5, java.sql.Date.valueOf(child.getDateOfBirth()));
                stmt.setString(6, child.getCertificateNumber());
                stmt.setDate(7, java.sql.Date.valueOf(child.getIssueDate()));
                stmt.setLong(8, child.getIssueDepartment().getOfficeId());
                stmt.setString(9, child.getAdress().getPostCode());
                stmt.setLong(10, child.getAdress().getStreet().getStreetCode());
                stmt.setString(11, child.getAdress().getBuilding());
                stmt.setString(12, child.getAdress().getExtention());
                stmt.setString(13, child.getAdress().getAppartment());

                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }

    private void setSqlParams(PreparedStatement stmt, int start, Adult adult) throws SQLException {
        stmt.setString(start, adult.getLastName());
        stmt.setString(start + 1, adult.getFirstName());
        stmt.setString(start + 2, adult.getPatronymic());
        stmt.setDate(start + 3, Date.valueOf(adult.getDateOfBirth()));
        stmt.setString(start + 4, adult.getPassportSeria());
        stmt.setString(start + 5, adult.getPassportNumber());
        stmt.setDate(start + 6, Date.valueOf(adult.getIssueDate()));
        stmt.setLong(start + 7, adult.getIssueDepartment().getOfficeId());
        stmt.setString(start + 8, adult.getAdress().getPostCode());
        stmt.setLong(start + 9, adult.getAdress().getStreet().getStreetCode());
        stmt.setString(start + 10,adult.getAdress().getBuilding());
        stmt.setString(start + 11, adult.getAdress().getExtention());
        stmt.setString(start + 12, adult.getAdress().getAppartment());
    }
}
