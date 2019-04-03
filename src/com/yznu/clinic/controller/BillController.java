package com.yznu.clinic.controller;

import com.alibaba.fastjson.JSON;
import com.yznu.clinic.beans.Bill;
import com.yznu.clinic.beans.Projects;
import com.yznu.clinic.interfaces.BillService;
import com.yznu.clinic.util.PageList;
import com.yznu.clinic.util.Query;
import com.yznu.clinic.util.QueryPage;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("bill")
public class BillController {
	@Autowired
	BillService billservice;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

	//currPage参数表示显示第几页的数据
	private int CURRPAGE=0;
	//pageSize表示每页显示的数据条数
	private final int PAGESIZE=10;
	
	@RequestMapping(value = "billList.do")
    public ModelAndView billList() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("bill_list");
        return mv;
    }
	
	@RequestMapping(value="/billupdate.do")
	public String billupdate(){
		return "bill_update";
	}
	
	@RequestMapping(value="/billadd.do")
	public String billadd(){
		return "bill_add";
	}
	
	@RequestMapping(value="/getallbill.action", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getallbill(HttpSession session){
		List<Bill> data=billservice.getallBill();
		return JSON.toJSONString(data);
	}
	
/*	@RequestMapping(value="/getpageproject.action", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getpageproject(@RequestBody Integer currpage,HttpServletRequest request){
		QueryPage page=new QueryPage();
		String state=request.getParameter("state");
		String sta="0";
		if("stop".equals(state)){
			sta="0";
		}else if("use".equals(state)){
			sta="1";
		}
		int totalsize=0;
		CURRPAGE=currpage;
		long allnum=billservice.queryTotalsize(PAGESIZE,sta);
		totalsize=(int) Math.ceil((float)allnum/(float)PAGESIZE);
		List<Bill> data=billservice.queryProjectsBySql(CURRPAGE, PAGESIZE ,sta);
		page.setCurrpage(CURRPAGE);
		page.setPagesize(PAGESIZE);
		page.setTotalsize(totalsize);
		page.setPagedata(data);
		page.setAllnum(allnum);
		return JSON.toJSONString(page);
	}*/
	
	@RequestMapping(value="/querysomedata.action", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String querysomedata(HttpServletRequest request){
		QueryPage page=new QueryPage();
		Map<String, Object> data1=new HashMap<String, Object>();
		CURRPAGE=Integer.parseInt(request.getParameter("currpage"));
		data1.put("date1", request.getParameter("date1"));
		data1.put("date2", request.getParameter("date2"));
		data1.put("searchindex", request.getParameter("searchindex"));
		data1.put("currpage",CURRPAGE);
		data1.put("delflag1", request.getParameter("delflag1"));
		data1.put("delflag2", request.getParameter("delflag2"));
		data1.put("pageSize", PAGESIZE);
		data1.put("currIndex", (CURRPAGE-1)*PAGESIZE);
		int totalsize=0;
		long allnum=billservice.queryTotalsize(PAGESIZE,data1);
		
		totalsize=(int) Math.ceil((float)allnum/(float)PAGESIZE);
		List<Bill> data=billservice.querysomedata(data1);
		page.setCurrpage(CURRPAGE);
		page.setPagesize(PAGESIZE);
		page.setTotalsize(totalsize);
		page.setPagedata(data);
		page.setAllnum(allnum);
		return JSON.toJSONString(page);
	}
	
	
	@RequestMapping(value="/delbill.action", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String delbill(@RequestBody Integer id,HttpSession session){
		int data=billservice.delbill(id);
		return JSON.toJSONString(data);
	}
	
	@RequestMapping(value="/delallbill.action", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String delallbill(@RequestBody String idstr,HttpSession session){
		idstr=idstr.substring(1,idstr.length()-1);
		String[] idstrs=idstr.split(",");
		
		Integer[] allId=new Integer[idstrs.length];
		int i=0;
		for(String id:idstrs){
			allId[i]=Integer.parseInt(id);
			i++;
		}
		
		int data=billservice.delallbill(allId);
		return JSON.toJSONString(data);
	}
	
	@RequestMapping(value="/addbill.action", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addbill(@RequestBody Bill bill,HttpSession session){
		Date date=new Date();
		String time=sdf.format(date);
		bill.setBillDate(time);
		bill.setBillNum((int)((Math.random()*9+1)*100000));
		int data=billservice.addbill(bill);
		return JSON.toJSONString(data);
	}
	
	@RequestMapping(value="/loadingupdatebill.action", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String loadingupdatebill(@RequestBody Integer id,HttpSession session){
		Bill data=billservice.loadupdatebill(id);
		return JSON.toJSONString(data);
	}
	
	@RequestMapping(value="/updatebill.action", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updatebill(@RequestBody Bill bill,HttpSession session){
		int data=billservice.updatebill(bill);
		return JSON.toJSONString(data);
	}


	/**
	 *  导出到Excel
	 *  参数 ： fileTitle:文件名
	 *
	 */
	@RequestMapping("/toExportFile.do")
	@ResponseBody
	public void downLoadFile(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		Map params = new HashMap();
		String fileTitle = request.getParameter("fileTitle");

		params.put("date1", request.getParameter("date1"));
		params.put("date2", request.getParameter("date2"));
		params.put("searchindex", request.getParameter("searchindex"));

		List list = new ArrayList();

		list = billservice.querybillbyall(params);
		groupDownLoad(request, response, fileTitle, list);

	}



	/**
	 * 账单导出
	 * @param request
	 * @param response
	 * @param fileTitle 文件名
	 * @param list
	 * @throws UnsupportedEncodingException
	 */
	private void groupDownLoad(HttpServletRequest request, HttpServletResponse response, String fileTitle, List<Bill> list) throws UnsupportedEncodingException {
		// 转换数据为excel
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/x-download");

		// 设置返回头，文件名
		String fileName = fileTitle + ".xlsx";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.addHeader("Content-Disposition", "attachment;filename=" + fileName);

		// 定义新的工作簿
		XSSFWorkbook wb = new XSSFWorkbook();

		// 定义边框
		XSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setBorderBottom(cellStyle.BORDER_THIN); // 下边框
		cellStyle.setBorderLeft(cellStyle.BORDER_THIN);// 左边框
		cellStyle.setBorderTop(cellStyle.BORDER_THIN);// 上边框
		cellStyle.setBorderRight(cellStyle.BORDER_THIN);// 右边框

		// 定义背景色
		cellStyle.setFillForegroundColor(IndexedColors.TEAL.getIndex());
		cellStyle.setFillPattern(cellStyle.SOLID_FOREGROUND);


		// 创建sheet页
		XSSFSheet sheet = wb.createSheet("sheet1");
		sheet.setDefaultRowHeight((short) (2 * 256));//设置行高
		sheet.setColumnWidth(0, 4000);//设置列宽
		sheet.setColumnWidth(1,12000);
		sheet.setColumnWidth(2,5500);
		sheet.setColumnWidth(3,5500);
		sheet.setColumnWidth(4,12000);
		sheet.setColumnWidth(5,3000);
		sheet.setColumnWidth(6,3000);


		// 定义字体样式
		XSSFFont font = wb.createFont();
		font.setFontName("黑体");
		font.setFontHeightInPoints((short) 14);
		font.setColor(HSSFColor.WHITE.index);
		cellStyle.setFont(font);

		// 设置单元格居中
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		// 定义左侧标题单元格样式
		XSSFCellStyle cellStyle2 = wb.createCellStyle();
		// 定义居中
		cellStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		cellStyle2.setBorderBottom(cellStyle.BORDER_THIN); // 下边框
		cellStyle2.setBorderLeft(cellStyle.BORDER_THIN);// 左边框
		cellStyle2.setBorderTop(cellStyle.BORDER_THIN);// 上边框
		cellStyle2.setBorderRight(cellStyle.BORDER_THIN);// 右边框

		// 定义正文单元格样式
		XSSFCellStyle cellStyle3 = wb.createCellStyle();
		cellStyle3.setBorderBottom(cellStyle.BORDER_THIN); // 下边框
		cellStyle3.setBorderLeft(cellStyle.BORDER_THIN);// 左边框
		cellStyle3.setBorderTop(cellStyle.BORDER_THIN);// 上边框
		cellStyle3.setBorderRight(cellStyle.BORDER_THIN);// 右边框

		// 定义百分比样式
		XSSFCellStyle cellStyle4 = wb.createCellStyle();
		cellStyle4.setBorderBottom(cellStyle.BORDER_THIN); // 下边框
		cellStyle4.setBorderLeft(cellStyle.BORDER_THIN);// 左边框
		cellStyle4.setBorderTop(cellStyle.BORDER_THIN);// 上边框
		cellStyle4.setBorderRight(cellStyle.BORDER_THIN);// 右边框

		cellStyle4.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 左右居中
		cellStyle4.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 上下居中


		// 设置标题
		XSSFRow row = sheet.createRow(0);
		XSSFCell cell = row.createCell(0);
		cell.setCellValue("账单名称");
		cell.setCellStyle(cellStyle);
		cell = row.createCell(1);
		cell.setCellValue("账单编号");
		cell.setCellStyle(cellStyle);
		cell = row.createCell(2);
		cell.setCellValue("时间");
		cell.setCellStyle(cellStyle);
		cell = row.createCell(3);
		cell.setCellValue("账单类别");
		cell.setCellStyle(cellStyle);
		cell = row.createCell(4);
		cell.setCellValue("账单项目");
		cell.setCellStyle(cellStyle);
		cell = row.createCell(5);
		cell.setCellValue("金额");
		cell.setCellStyle(cellStyle);
		cell = row.createCell(6);
		cell.setCellValue("操作员工");
		cell.setCellStyle(cellStyle);
		cell = row.createCell(7);


		XSSFRow rows;
		XSSFCell cells;

		// 设置正文
		for (int i = 0; i < list.size(); i++) {
			// 第三步：在这个sheet页里创建一行
			rows = sheet.createRow(i+1);
			// 第四步：在该行创建一个单元格
			cells = rows.createCell(0);
			cells.setCellStyle(cellStyle2);
			// 第五步：在该单元格里设置值
			cells.setCellValue(list.get(i).getBillName());

			cells = rows.createCell(1);
			cells.setCellStyle(cellStyle3);
			cells.setCellValue(list.get(i).getBillNum());

			cells = rows.createCell(2);
			cells.setCellValue(list.get(i).getBillDate());
			cells.setCellStyle(cellStyle4);


			cells = rows.createCell(3);
			cells.setCellValue(list.get(i).getBillType());
			cells.setCellStyle(cellStyle4);


			cells = rows.createCell(4);
			cells.setCellStyle(cellStyle3);
			cells.setCellValue(list.get(i).getBillOption());

			cells = rows.createCell(5);
			cells.setCellValue(list.get(i).getBillMon());
			cells.setCellStyle(cellStyle4);


			cells = rows.createCell(6);
			cells.setCellStyle(cellStyle2);
			cells.setCellValue(list.get(i).getEmployeeId());

		}
		try {
			OutputStream out = response.getOutputStream();
			wb.write(out);
			out.close();
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
