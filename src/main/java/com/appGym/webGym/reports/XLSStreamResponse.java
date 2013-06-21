package com.appGym.webGym.reports;

import java.io.InputStream;


public class XLSStreamResponse extends ExportStreamResponse {

	public XLSStreamResponse(InputStream is, String args) {
		super(is, args, ExportFormat.XLS);
	}

}
