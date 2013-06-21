package com.appGym.webGym.reports;

import java.io.InputStream;


public class DOCStreamResponse extends ExportStreamResponse {

	public DOCStreamResponse(InputStream is, String args) {
		super(is, args, ExportFormat.DOC);
	}

}
