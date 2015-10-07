package com.o9paathshala.user.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.o9paathshala.dao.DAO;
import com.o9paathshala.dao.DAOFactory;
import com.o9paathshala.dao.DBConstants;
import com.o9paathshala.dao.PreparedStatementDTO;
import com.o9paathshala.dao.SQLConstants;
import com.o9paathshala.dto.SessionDTO;

/**
 * Servlet implementation class SwitchInstitute
 */
@WebServlet("/SwitchInstitute")
public class SwitchInstitute extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory.getLogger(SwitchInstitute.class);
	  /**
     * @see HttpServlet#HttpServlet()
     */
    public SwitchInstitute() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			HttpSession session = request.getSession(false);
			List<SessionDTO> sessionDTOList = new ArrayList<SessionDTO>(); 
			sessionDTOList = (List<SessionDTO>)(session.getAttribute("chooseInstituteData"));
			
			Integer instituteIdIndex = Integer.parseInt(request.getParameter("instituteRadios").trim());
			SessionDTO sessionDTO = sessionDTOList.get(instituteIdIndex);
			boolean setAsDefault = request.getParameter("setAsDefault")!=null;
			if(setAsDefault){
				
				DAO dao = DAOFactory.getDAOObject();
				List<PreparedStatementDTO> psList = new ArrayList<PreparedStatementDTO>();
				PreparedStatementDTO ps = new PreparedStatementDTO();
				ps.setDataType(DBConstants.INTEGER);
				ps.setPosition(1);
				ps.setValue(sessionDTO.getCurrentInstituteId());
				psList.add(ps);
				ps = new PreparedStatementDTO();
				ps.setDataType(DBConstants.INTEGER);
				ps.setPosition(2);
				ps.setValue(sessionDTO.getId());
				psList.add(ps);
				
				int result = dao.cud(SQLConstants.UPDATE_DEFAULT_INSTITUTE, psList);
				if(result <= 0){
					LOGGER.error("default institute update is not working");
				}
			}
			
			
			
			session.setAttribute("user", sessionDTO);
			String user=null;
			switch(sessionDTO.getType()){
			case 1:user="institute";
			break;
			case 2:user="faculty";
			break;
			case 3:user="student";
			break;
			default:break;
			}
			response.sendRedirect(""+user.toLowerCase()+"/dashboard");
			
			
		}catch(Exception e){
			LOGGER.error(e.getMessage(),e);
			request.getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}
		
	}

}
