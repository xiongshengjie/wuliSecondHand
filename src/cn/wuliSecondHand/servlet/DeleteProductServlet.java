package cn.wuliSecondHand.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import cn.wuliSecondHand.domain.Product;
import cn.wuliSecondHand.exception.AddProductException;
import cn.wuliSecondHand.service.ProductService;
import cn.wuliSecondHand.utils.FileUploadUtils;
import cn.wuliSecondHand.utils.IdUtils;

/**
 * 后台系统
 * 删除商品信息的servlet
 */
public class DeleteProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 获取请求参数，产品id
		String id = request.getParameter("id");
		ProductService service = new ProductService();
		// 调用service完成添加商品操作
		service.deleteProduct(id);
		if("del".equals(request.getParameter("type"))){
			response.getWriter().write("{\"errCode\":0,\"errMsg\":\"下架成功^_^\"}");
			return;
		}

		response.sendRedirect(request.getContextPath() + "/listProduct");
		return;
	}

}
