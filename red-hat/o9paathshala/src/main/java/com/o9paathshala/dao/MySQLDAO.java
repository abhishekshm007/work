package com.o9paathshala.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.o9paathshala.dto.SessionDTO;
import com.o9paathshala.util.Closing;

public class MySQLDAO implements DAO, DBConstants{
	 private static final Logger LOGGER = LoggerFactory.getLogger(MySQLDAO.class);
		
	private Connection getConnection() throws ClassNotFoundException, SQLException	{
		ResourceBundle rb = ResourceBundle.getBundle("db");
		String driverName = rb.getString("drivername");
		Class.forName(driverName);
		String url = rb.getString("dburl");
		String userid = rb.getString("userid");
		String password = rb.getString("password");
		Connection con = DriverManager.getConnection(url,userid,password);
		return con;
	}


	@Override
	public int cud(List<ConfiguredQuery> configuredQueries) throws ClassNotFoundException, SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try	{
			con = getConnection();
			con.setAutoCommit(false);
			for(ConfiguredQuery conQuery : configuredQueries){
				pstmt = con.prepareStatement(conQuery.getQuery());
				if(conQuery.getPsList()!=null && !conQuery.getPsList().isEmpty()){
					pstmt=prepareQuery(pstmt, conQuery.getPsList());
				}
				LOGGER.info(pstmt.toString());
				rowCount += pstmt.executeUpdate();
			}
			con.commit();
		}finally{
			Closing.closeEverything(null, pstmt, con);
		}
		return rowCount;
	}
	@Override
	public int cud(String sql,List<PreparedStatementDTO> psList) throws ClassNotFoundException, SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		try{
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			if(psList!=null && !psList.isEmpty()){
				pstmt=prepareQuery(pstmt, psList);
			}LOGGER.info(pstmt.toString());
			rowCount = pstmt.executeUpdate();
		}finally{
			Closing.closeEverything(null, pstmt, con);
		}
		return rowCount;
	}
	@Override
	public SessionDTO instituteConfirmation(List<ConfiguredQuery> data) throws ClassNotFoundException, SQLException {
		Connection con = null;
		SessionDTO institutesession=null;	
		try{
			con = getConnection();
			con.setAutoCommit(false);
			institutesession=getInstituteByLink(data.get(0).getQuery(), data.get(0).getPsList(),con);
			if(null==institutesession){
				return null;
			}			
			update(data.get(1).getQuery(), data.get(1).getPsList(),con);
			update(data.get(2).getQuery(), data.get(2).getPsList(),con);
			createTables(data, con, institutesession.getId().toString());
			con.commit();
		}catch(Exception se){
			if(null!=con){
				con.rollback();
			}
			throw se;
		}finally{
			Closing.closeEverything(null, null, con);
		}
		return institutesession;
	}

	private SessionDTO getInstituteByLink(String sql,List<PreparedStatementDTO> psList,Connection con) throws SQLException, ClassNotFoundException {
		PreparedStatement pstmt = null;
		SessionDTO data=null;
		ResultSet rs=null;
		try{
			pstmt = con.prepareStatement(sql);
			if(psList!=null && !psList.isEmpty()){
				pstmt=prepareQuery(pstmt, psList);
			}
			
			LOGGER.info(pstmt.toString());
			rs=pstmt.executeQuery();
			if(rs.next()){
				data=new SessionDTO();
				data.setEmail(rs.getString("institute_email"));
				data.setId(rs.getInt("institute_id"));
				data.setName(rs.getString("name"));
				data.setCurrentInstituteId(rs.getInt("institute_id"));
			}
		}finally{
			Closing.closeEverything(rs, pstmt, null);
			
		}
		return data;
	}
	

	private int update(String sql,List<PreparedStatementDTO> psList,Connection con) throws SQLException, ClassNotFoundException {
		PreparedStatement pstmt = null;
		int rowCount=0;
		try	{
			pstmt = con.prepareStatement(sql);
			if(psList!=null && !psList.isEmpty()){
				prepareQuery(pstmt, psList);
			}LOGGER.info(pstmt.toString());
			rowCount=pstmt.executeUpdate();
		}finally{
			Closing.closeEverything(null, pstmt, null);

		}
		return rowCount;
	}

	private int[] createTables(List<ConfiguredQuery> data,Connection con,String id) throws SQLException, ClassNotFoundException{
		Statement stmt = null;
		int[] rowCount=new int[50];
		try{
			stmt = con.createStatement();
			for(int i=3;i<data.size();i++){
				stmt.addBatch(data.get(i).getQuery().replace("instituteid",id));
			}
			rowCount=stmt.executeBatch();
		}finally{
			if(stmt!=null){
				stmt.close();
			}
		}
		return rowCount;
	}
	@Override
	public List<Map<String, Object>> getAll(String sql,List<PreparedStatementDTO> psList) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Connection con=null;
		List<Map<String, Object>> resultList = null;
		Map<String, Object> row = null;
		try{
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			if(psList!=null && !psList.isEmpty()){
				pstmt=prepareQuery(pstmt, psList);
			}
			LOGGER.info(pstmt.toString());
			rs=pstmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			Integer columnCount = metaData.getColumnCount();
			if(rs.next()){
				resultList=new ArrayList<Map<String, Object>>();
				rs.previous();
				while (rs.next()) {

					row = new HashMap<String, Object>();
					for (int i = 1; i <= columnCount; i++) {
						row.put(metaData.getColumnName(i), rs.getObject(i));
					}
					resultList.add(row);
				}
			}
		}finally{
			Closing.closeEverything(rs, pstmt, con);
		}
		return resultList;
	}

	private PreparedStatement prepareQuery(PreparedStatement pstmt,List<PreparedStatementDTO> psList) throws SQLException{
		for(PreparedStatementDTO psDTO : psList){
			switch(psDTO.getDataType()){
			case STRING:pstmt.setString(psDTO.getPosition(), psDTO.getValue().toString());
			break;
			case INTEGER:pstmt.setInt(psDTO.getPosition(), (Integer)psDTO.getValue());
			break;
			case DOUBLE:pstmt.setDouble(psDTO.getPosition(), (Double)psDTO.getValue());
			break;
			case BOOLEAN:pstmt.setBoolean(psDTO.getPosition(), (Boolean)psDTO.getValue());
			break;
			case DATE:pstmt.setDate(psDTO.getPosition(), (java.sql.Date)psDTO.getValue());
			break;
			case FLOAT:pstmt.setFloat(psDTO.getPosition(), (Float)psDTO.getValue());
			break;
			case BLOB:pstmt.setBytes(psDTO.getPosition(), (byte[])psDTO.getValue());
			break;
			case TIMESTAMP:pstmt.setTimestamp(psDTO.getPosition(), (Timestamp)psDTO.getValue());
			break;
			default :break;
			}
		}
		return pstmt;
	}
	
	
}