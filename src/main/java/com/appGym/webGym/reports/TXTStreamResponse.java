package com.appGym.webGym.reports;

import java.io.InputStream;


public class TXTStreamResponse extends ExportStreamResponse {

	public TXTStreamResponse(InputStream is, String args) {
		super(is, args, ExportFormat.TXT);
	}

}