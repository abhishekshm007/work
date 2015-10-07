package com.o9paathshala.institute.servlets;

import java.io.File;
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

import com.o9paathshala.constants.ApplicationContants;
import com.o9paathshala.dao.ConfiguredQuery;
import com.o9paathshala.dao.DAO;
import com.o9paathshala.dao.DAOFactory;
import com.o9paathshala.dao.DBConstants;
import com.o9paathshala.dao.PreparedStatementDTO;
import com.o9paathshala.dao.SQLConstants;
import com.o9paathshala.dto.SessionDTO;
import com.o9paathshala.dto.SetPasswordDTO;
import com.o9paathshala.security.Encryptor;
import com.o9paathshala.util.CreateDirectories;
import com.o9paathshala.util.CreateQueries;

/**
 * Servlet implementation class InstituteRegistrationConfirmation
 */
@WebServlet("/InstituteRegistrationConfirmation")
public class InstituteConfirmEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(InstituteConfirmEmail.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InstituteConfirmEmail() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			String encryptedLink = request.getParameter("id").trim();
			SetPasswordDTO data = new SetPasswordDTO();
			data.setConfirmPassword(request.getParameter("confirmPassword").trim());
			data.setNewPassword(request.getParameter("password").trim());
			SessionDTO sessionDTO = null;
			DAO dao = DAOFactory.getDAOObject();
			ConfiguredQuery cq = null;
			List<ConfiguredQuery> cqList = new ArrayList<ConfiguredQuery>();
			PreparedStatementDTO ps = null;
			List<PreparedStatementDTO> psList = new ArrayList<PreparedStatementDTO>();
			String sql = null;

			ps = new PreparedStatementDTO();
			ps.setDataType(DBConstants.STRING);
			ps.setPosition(1);
			ps.setValue(encryptedLink);
			psList.add(ps);
			sql = SQLConstants.GET_INSTITUTE_BY_LINK;
			cq = new ConfiguredQuery();
			cq.setPsList(psList);
			cq.setQuery(sql);
			cqList.add(cq);

			psList = new ArrayList<PreparedStatementDTO>();
			ps = new PreparedStatementDTO();
			ps.setDataType(DBConstants.STRING);
			ps.setPosition(1);
			ps.setValue(Encryptor.encryptSHA512(data.getNewPassword()));
			psList.add(ps);
			ps = new PreparedStatementDTO();
			ps.setDataType(DBConstants.STRING);
			ps.setPosition(2);
			ps.setValue(encryptedLink);
			psList.add(ps);
			sql = SQLConstants.UPDATE_ACTIVATION_PASSWORD;
			cq = new ConfiguredQuery();
			cq.setPsList(psList);
			cq.setQuery(sql);
			cqList.add(cq);

			psList = new ArrayList<PreparedStatementDTO>();
			ps = new PreparedStatementDTO();
			ps.setDataType(DBConstants.STRING);
			ps.setPosition(1);
			ps.setValue(ApplicationContants.DEFAULT_ENCRYPTED_LINK);
			psList.add(ps);
			ps = new PreparedStatementDTO();
			ps.setDataType(DBConstants.STRING);
			ps.setPosition(2);
			ps.setValue(encryptedLink);
			psList.add(ps);
			sql = SQLConstants.UPDATE_ACTIVATION_LINK;
			cq = new ConfiguredQuery();
			cq.setPsList(psList);
			cq.setQuery(sql);
			cqList.add(cq);

			// for create table configured query setup
			CreateQueries obj = new CreateQueries();
			cqList = obj.getQueries(cqList);
			// create queries
			sessionDTO = dao.instituteConfirmation(cqList);
			if (null == sessionDTO) {
				LOGGER.error("trying to set updated password");
				response.sendError(400);
				return;
			} else {
				final Integer idForThread = sessionDTO.getId();
				new Thread(new Runnable() {
					@Override
					public void run() {
						//						CreateDirectories
						//								.createDirectories(
						//										idForThread,
						//										getServletContext().getRealPath(
						//												File.separator));
						CreateDirectories
						.createDirectories(
								idForThread,
								System.getenv("OPENSHIFT_DATA_DIR"));
					}

				}).start();

				sessionDTO.setType(ApplicationContants.INSTITUTE);
				HttpSession session = request.getSession(true);
				session.setMaxInactiveInterval(ApplicationContants.SESSION_MAX_INACTIVE_INTERVAL);
				session.setAttribute("user", sessionDTO);



				LOGGER.info(session.toString());
				response.sendRedirect("institute/dashboard");
				return;

			}
		} catch (Exception e) {
			LOGGER.error("Error Occured", e);
			response.sendError(500);
		}

	}

}
