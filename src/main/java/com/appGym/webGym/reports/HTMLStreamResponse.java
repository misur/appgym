package com.appGym.webGym.reports;

import java.io.InputStream;


public class HTMLStreamResponse extends ExportStreamResponse {

	public HTMLStreamResponse(InputStream is, String args) {
		super(is, args, ExportFormat.HTML);
	}

}