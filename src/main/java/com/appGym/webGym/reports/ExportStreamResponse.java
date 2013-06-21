package com.appGym.webGym.reports;

import java.io.IOException;
import java.io.InputStream;

import org.apache.tapestry5.StreamResponse;
import org.apache.tapestry5.services.Response;


public class ExportStreamResponse implements StreamResponse {

	private InputStream is;

	private String filename = "default";

	private ExportFormat FORMAT;

	public ExportStreamResponse(InputStream is, String args, ExportFormat FORMAT) {

		this.is = is;

		this.filename = args;

		this.FORMAT = FORMAT;

	}

	public String getContentType() {

		if (FORMAT == ExportFormat.TXT) {

			return "text/plain";

		} else if (FORMAT == ExportFormat.XLS) {

			return "application/vnd.ms-excel";

		} else {

			return "application/" + String.valueOf(this.FORMAT).toLowerCase();

		}

	}

	public InputStream getStream() throws IOException {

		return is;

	}

	public void prepareResponse(Response arg0) {

		arg0.setHeader("Content-Disposition", "attachment; filename="

		+ filename + "." + String.valueOf(this.FORMAT).toLowerCase());

	}

}
