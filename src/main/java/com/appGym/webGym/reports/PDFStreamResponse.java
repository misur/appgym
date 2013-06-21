package com.appGym.webGym.reports;

import java.io.InputStream;


public class PDFStreamResponse extends ExportStreamResponse {

	public PDFStreamResponse(InputStream is, String args) {
		super(is, args, ExportFormat.PDF);
	}

}