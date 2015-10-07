package com.o9paathshala.user.servlets;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.o9paathshala.dto.SessionDTO;

@WebServlet("/ProfilePicture")

public class ProfilePicture extends HttpServlet{


	private static final long serialVersionUID = 3846670797197694233L;
	private static final Logger LOGGER = LoggerFactory.getLogger(ProfilePicture.class);
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try{
			HttpSession session=req.getSession(false);
			SessionDTO sessionDTO = (SessionDTO) session.getAttribute("user");
			if(null == sessionDTO){
				return;
			}
			String id = req.getParameter("id").trim();
			if("dp".equalsIgnoreCase(id)){
				id=sessionDTO.getId().toString();
			}
			String path=System.getenv("OPENSHIFT_DATA_DIR")+File.separator+"users"+File.separator+sessionDTO.getCurrentInstituteId()+File.separator+"images"+File.separator+"savedfiles"+File.separator+id;
			String defaultImg=getServletConfig().getServletContext().getRealPath(File.separator)+"users"+File.separator+"images"+File.separator+"default.jpg";
			File imgPath = new File(path+".jpg");
			if(!imgPath.exists()){
				imgPath = new File(path+".png");
				if(!imgPath.exists()){
					imgPath = new File(path+".jpeg");
				}
			}

			if(!imgPath.exists()){
				imgPath = new File(defaultImg);
			}
			BufferedImage image = ImageIO.read(imgPath); 
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image, "png", baos); 
			byte[] res=baos.toByteArray();
			if(null != imgPath){
				resp.setContentType("image/jpeg");
				OutputStream os = resp.getOutputStream();
				os.write(res);
				os.close();
			}
		}catch(Exception e){
			LOGGER.error("error occured during profile pic fetching", e);
			return;
		}

	}

}