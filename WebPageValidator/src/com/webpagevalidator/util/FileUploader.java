package com.webpagevalidator.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;



/**
 * Servlet implementation class FileUploader
 */
@WebServlet("/FileUploader")
public class FileUploader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUploader() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}*/

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	*/
	protected void doPost(HttpServletRequest request,
	        HttpServletResponse response) throws IllegalStateException, IOException, ServletException{
		 response.setContentType("text/html;charset=UTF-8");
		 	
		 	ArrayList<String> urls = new ArrayList<String>();
		 	
		    boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		    
		  
		    if (!isMultipart) {
	        } else {
	        	try{
	            FileItemFactory factory = new DiskFileItemFactory();
	            ServletFileUpload upload = new ServletFileUpload(factory);
	            List<FileItem> items = null;
	            try {
	                items = upload.parseRequest(request);
	            } catch (FileUploadException e) {
	                e.getMessage();
	            }
	     
	            FileItem item = (FileItem) items.get(0);

	            //InputStream is=item.getInputStream();

	             Workbook workbook = WorkbookFactory.create(item.getInputStream());	            
	             Sheet sheet = workbook.getSheetAt(0);
	             //Iterate through each rows one by one  
	             Iterator<Row> rowIterator = sheet.iterator();   
	             while (rowIterator.hasNext()) {            
	                 Row row = rowIterator.next();
	                 //For each row, iterate through all the columns  
	                 Iterator<Cell> cellIterator = row.cellIterator();   
	                 while (cellIterator.hasNext())  {               
	                     Cell cell = cellIterator.next();    
	                     //Check the cell type and format accordingly   
	                     switch (cell.getCellType())                     {        
	                         case Cell.CELL_TYPE_NUMERIC:  System.out.print(cell.getNumericCellValue() + "\t"); 
	                     break;                       
	                         case Cell.CELL_TYPE_STRING: urls.add(cell.getStringCellValue());  
	                     break;     
	                     }           
	                 }             
	                 System.out.println("");         
	             
	             }
	             
	        	}catch(Exception e){
	        		System.out.println(e);
	        	}
	       }
		    
		    /*new Thread(new Runnable() {
		        public void run(){
		            try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		            System.out.println("After Thread");
		        }
		    }).start();*/
		    
	        request.setAttribute("urlList", urls);   
	        getServletConfig().getServletContext().getRequestDispatcher("/validate.jsp").forward(request,response);
	}

}
