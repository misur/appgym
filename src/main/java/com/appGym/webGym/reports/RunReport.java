package com.appGym.webGym.reports;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import org.apache.tapestry5.StreamResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRProperties;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class RunReport {

	public static StreamResponse getRunReport(String name, ExportFormat format,

	HashMap parms, String expName) {

		try {
			JasperDesign jasperDesign = JRXmlLoader.load(name);
			JasperReport rep = JasperCompileManager.compileReport(jasperDesign);
			JasperPrint jp = JasperFillManager.fillReport(rep, parms,
			ReportDriverImpl.connectDB());
			JRProperties.setProperty(
			"net.sf.jasperreports.default.pdf.encoding", "Cp1250");
			ReportDriverImpl.disconectDB();
			if (format == ExportFormat.PDF) {
				byte[] fajl = JasperExportManager.exportReportToPdf(jp);
				File tm = File.createTempFile(expName.toLowerCase(), "."
				+ format.toString().toLowerCase());
				FileOutputStream o = new FileOutputStream(tm);
				o.write(fajl);
				InputStream s = new FileInputStream(tm);
				return new PDFStreamResponse(s, expName);
			}
		} catch (JRException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
