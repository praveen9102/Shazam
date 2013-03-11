package com.adivi.ncsa.fasttrack.sport;
*/including comments /*
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;

import com.adivi.ncsa.fasttrack.common.AddressBean;
import com.adivi.ncsa.fasttrack.common.CommonDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

public class SportDAO extends CommonDAO {

//	 Logging 
	static Logger logger = Logger.getLogger(SportDAO.class.getName());


	public static String getSportName (int sportID) throws SportException {

		PreparedStatement st = null;
		ResultSet rs = null;
		Connection conn = null;

		try {
			conn = getConnection();
			st = conn.prepareStatement ("select name from sport where sport_id = ?");
			st.setInt(1, sportID);

			rs = st.executeQuery ();
			if (!rs.next ())
				return null;

			String bean = new String ();
			bean = rs.getString("name");
			
			return bean;

		} catch (SQLException e) {
			logger.error("getSportName",e);
			throw new SportException();
		} finally {
			try {
				if(rs!=null)rs.close ();
				if(st!=null)st.close ();
			} catch (SQLException ignore) {logger.error("getSportName",ignore);}
			releaseConnection(conn);			
		}
	}

	public static PositionID[] getPositions (int sportID) throws SQLException {

		PreparedStatement st = null;
		ResultSet rs = null;
		Connection conn =null;
		try {
			conn = getConnection();
			st = conn.prepareStatement ("select position_id positionID, position_name position from position where sport_id = ?");
			st.setInt(1, sportID);

			rs = st.executeQuery ();
			List list = new ArrayList ();

			while (rs.next ()) {

				PositionID bean = new PositionID ();

				bean.setPositionID (rs.getInt("positionID"));
				bean.setPosition (rs.getString("position"));
				list.add (bean);

			}

			return (PositionID[]) list.toArray (new PositionID[0]);

		} finally {
			try{
				if (rs != null)rs.close ();
				if(st!=null)st.close ();
			}catch(SQLException ignore){logger.error("getPositions",ignore);}
			releaseConnection(conn);
		}
	}

	public static SportBean getSportForCollege (int collegeID, int sportID) throws SQLException {

		PreparedStatement st = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = getConnection();
			st = conn.prepareStatement ("select a.college_id collegeID, a.sport_id sportID, a.athletic_pgm_strength athleticStrength, a.division_id divisionID, a.phone_nbr_sport_dept phoneNumber, a.fax_nbr_sport_dept fax, b.addr1, b.addr2, b.addr3, b.city, b. state_id stateId, b.zip zip, b.address_id addressID, a.entry_user from college_sport a, address b where a.address_id_sport = b.address_id and a.college_id = ? and a.sport_id = ?");
			st.setInt(1, collegeID);
			st.setInt(2, sportID);

			rs = st.executeQuery ();
			if (!rs.next ())
				return null;

			SportBean bean = new SportBean ();

			bean.setCollegeID (rs.getInt("collegeID"));
			bean.setSportID (rs.getInt("sportID"));
			bean.setAthleticStrength (rs.getInt("athleticStrength"));
			bean.setDivisionID (rs.getInt("divisionID"));
			bean.setPhoneNumber (rs.getString("phoneNumber"));
			bean.setFax (rs.getString("fax"));
			bean.setAddr1 (rs.getString("addr1"));
			bean.setAddr2 (rs.getString("addr2"));
			bean.setAddr3 (rs.getString("addr3"));
			bean.setCity (rs.getString("city"));
			bean.setStateId (rs.getInt("stateId"));
			bean.setZip (rs.getString("zip"));
			bean.setAddressID (rs.getInt("addressID"));
			bean.setModifyUser(rs.getInt("entry_user"));
			return bean;

		} finally {

			if (rs != null)
				rs.close ();

			if (st != null) st.close ();
			releaseConnection(conn);
		}
	}

	public static boolean createSport (SportBean sportBean) throws SportException {

		PreparedStatement st =null;
		Connection conn = null;
		try {
			conn = getConnection();
			
			/* adding address fields to the address table */
			AddressBean address = new AddressBean();
			BeanUtils.copyProperties(address, sportBean);
			sportBean.setAddressID(createAddress(address,0,sportBean.getModifyUser(),sportBean.getModifyDate(),conn));
			if(sportBean.getAddressID()==0){
				//die die piggy piggy
			}
			conn.commit();
			st = conn.prepareStatement ("insert into college_sport (college_id, sport_id, athletic_pgm_strength, division_id, address_id_sport,phone_nbr_sport_dept,fax_nbr_sport_dept,entry_user,entry_date) values (?,?,?,?, ?,?,?,?,?)");
			st.setInt(1, sportBean.getCollegeID());
			st.setInt(2, sportBean.getSportID());
			st.setInt(3, sportBean.getAthleticStrength());
			st.setInt(4, sportBean.getDivisionID());
			st.setInt(5, sportBean.getAddressID());
			st.setString(6, sportBean.getPhoneNumber());
			st.setString(7, sportBean.getFax());
			st.setInt(8, sportBean.getModifyUser());
			Timestamp tDate1= new Timestamp(sportBean.getModifyDate());
			st.setTimestamp(9, tDate1);
			
			st.executeUpdate ();
			conn.commit();
			return true;
			
		} catch (IllegalAccessException e) {
			logger.error("createSport",e);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("createSport",e1);
			}
			
			throw new SportException();
		} catch (InvocationTargetException e) {
			logger.error("createSport",e);
			try {
				conn.rollback();
			} catch (SQLException e2) {
				logger.error("createSport",e2);
			}
			
			throw new SportException();
		} catch (SQLException e) {
			logger.error("createSport",e);
			try {
				conn.rollback();
			} catch (SQLException e3) {
				logger.error("createSport",e3);
			}
			throw new SportException();			
		} finally {

			try {
				st.close ();
			} catch (SQLException e) {
				logger.error("createSport",e);
			}
			releaseConnection(conn);
		}
	}

	public static boolean updateSport (SportBean sportBean) throws SportException {

		PreparedStatement st = null;
		Connection conn = null;
		try {
			conn = getConnection();
			
			// update address table 
			AddressBean address = new AddressBean();
			BeanUtils.copyProperties(address, sportBean);
			if (!(updateAddress(address,sportBean.getModifyUser(),sportBean.getModifyDate(),conn))){
				logger.error("address update in sport dao failed");//die die piggy pigy
			}
			conn.commit();
			
			st = conn.prepareStatement ("update college_sport set division_id = ?, athletic_pgm_strength = ?, phone_nbr_sport_dept = ?, fax_nbr_sport_dept = ?, mod_user = ?, mod_date = ? where college_id = ? and sport_id = ?");
			st.setInt(1, sportBean.getDivisionID());
			st.setInt(2, sportBean.getAthleticStrength());
			st.setString(3, sportBean.getPhoneNumber());
			st.setString(4, sportBean.getFax());
			st.setInt(5, sportBean.getModifyUser());
			Timestamp tDate1= new Timestamp(sportBean.getModifyDate());
			st.setTimestamp(6, tDate1);
			st.setInt(7, sportBean.getCollegeID());
			st.setInt(8, sportBean.getSportID());
			st.executeUpdate ();
			conn.commit();
			return true;

		} catch (SQLException e) {
			logger.error("updateSport",e);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("updateSport",e1);
			}
			throw new SportException();
		} catch (IllegalAccessException e) {
			logger.error("updateSport",e);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("updateSport",e1);
			}
			throw new SportException();
		} catch (InvocationTargetException e) {
			logger.error("updateSport",e);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				logger.error("updateSport",e1);
			}
			throw new SportException();
		} finally {

			try {
				st.close ();
			} catch (SQLException e) {
				logger.error("updateSport",e);
			}
			releaseConnection(conn);

		}
	}


	public static void createPositionForSport (int sportID, String positionName, String positionCode, int entryUser, long modifyDate) throws SportException {

		PreparedStatement st =null;
		Connection conn =null;
		try {
			conn = getConnection();
			st = conn.prepareStatement ("insert into position (sport_id, position_name, position_code, entry_user, entry_date) values (?,?,?,?,?)");
			
			st.setInt(1, sportID);
			st.setString(2, positionName);
			st.setString(3, positionCode);
			st.setInt(4, entryUser);
			Timestamp tDate1= new Timestamp(modifyDate);
			st.setTimestamp(5, tDate1);

			st.executeUpdate ();

		} catch (SQLException e) {
			logger.error("createPositionForSport",e);
			throw new SportException();
		} finally {

			try {
				conn.commit();
				st.close ();
			} catch (SQLException e) {
				logger.error("createPositionForSport",e);
			}
			releaseConnection(conn);

		}
	}

	public static String getGenderForSport (int sportID) throws SQLException {

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			st = conn.prepareStatement ("select gender from sport where sport_id=?");
			st.setInt(1, sportID);

			rs = st.executeQuery ();
			if (!rs.next ())
				return null;

			String bean = new String ();
			bean=rs.getString("gender");
			return bean;

		} finally {
			try{
				if(rs != null) rs.close ();
				if(st!=null) st.close ();
			}catch(SQLException ignore){logger.error("getGenderForSport",ignore);}
			releaseConnection(conn);
		}
	}
}
