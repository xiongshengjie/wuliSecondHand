package cn.wuliSecondHand.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import cn.wuliSecondHand.domain.User;
import cn.wuliSecondHand.service.UserService;
import cn.wuliSecondHand.utils.FileUploadUtils;

/**
 * Servlet implementation class AddUserServlet
 */
@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		User u = (User)request.getSession().getAttribute("user");
		Map<String, String> map = new HashMap<String, String>();
		
		PrintWriter out = response.getWriter();

		DiskFileItemFactory dfif = new DiskFileItemFactory();
		// 设置临时文件存储位置
		dfif.setRepository(new File(this.getServletContext().getRealPath(
				"/temp")));
		// 设置上传文件缓存大小为10m
		dfif.setSizeThreshold(1024 * 1024 * 10);
		// 创建上传组件
		ServletFileUpload upload = new ServletFileUpload(dfif);
		// 处理上传文件中文乱码
		upload.setHeaderEncoding("utf-8");
		try {
			// 解析request得到所有的FileItem
			List<FileItem> items = upload.parseRequest(request);
			// 遍历所有FileItem
			for (FileItem item : items) {
				// 判断当前是否是上传组件
				if (item.isFormField()) {
					// 不是上传组件
					String fieldName = item.getFieldName(); // 获取组件名称
					String value = item.getString("utf-8"); // 解决乱码问题
					map.put(fieldName, value);
				} else {
					// 是上传组件
					// 得到上传文件真实名称
					String fileName = item.getName();
					if ("".equals(fileName)) {
						continue;
					}
					fileName = FileUploadUtils.subFileName(fileName);

					// 得到随机名称
					String randomName = FileUploadUtils
							.generateRandonFileName(fileName);

					// 得到随机目录
					String randomDir = FileUploadUtils
							.generateRandomDir(randomName);
					// 图片存储父目录
					String imgurl_parent = "userResume" + randomDir;

					File parentDir = new File(this.getServletContext()
							.getRealPath(imgurl_parent));
					// 验证目录是否存在，如果不存在，创建出来
					if (!parentDir.exists()) {
						parentDir.mkdirs();
					}

					String resumeurl = imgurl_parent + "/" + randomName;
					
					map.put("resume", resumeurl);
					
					OutputStream ops = new FileOutputStream(new File(parentDir, randomName));
					IOUtils.copy(item.getInputStream(), ops);
					ops.flush();
					ops.close();
					item.delete();
				}

			}
		}catch (FileUploadException e) {
			e.printStackTrace();
		}
		
		try {
			BeanUtils.populate(u, map);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		UserService service = new UserService();

		if (service.findUser(u.getName()) != null) {
			service.editUser(u);
			response.sendRedirect("pages\\personcenter.html");
			return;
		} else {
			service.addUser(u);
			response.sendRedirect("pages\\personcenter.html");
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
