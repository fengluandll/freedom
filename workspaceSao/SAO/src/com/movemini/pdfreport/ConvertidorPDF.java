package com.movemini.pdfreport;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;

public class ConvertidorPDF {

	private File archivoEnvio;
	
	public File getArchivoEnvio() {
		return archivoEnvio;
	}
	
	
	public boolean processFile(String folder, String filename,String baseurl,String jspString, List<NameValuePair> urlParametros){
		boolean res = false;
		InputStreamReader isr = null;
		try{
			
			
			File f = new File(folder);
			if (f.isDirectory()) {
				f.mkdir();
			}
			String fullfilename = folder + filename;
			
			
			HttpClient client = HttpClientBuilder.create().build(); //new DefaultHttpClient();
			URI uri = new URI(baseurl + jspString);
			
			HttpPost h_request = new HttpPost(uri);
			
			h_request.setEntity(new UrlEncodedFormEntity(urlParametros));
			
			HttpResponse h_response = client.execute(h_request);
			
			
			
			//isr = new InputStreamReader(h_response.getEntity().getContent(), "UTF-8");

			//PDFConverter converter = new PDFConverter();
			//String localpath = getServletContext().getInitParameter("pdfDir") + admin.getUserid() + "/" + lessonid + ".pdf";
			//String downloadUrl = getServletContext().getInitParameter("pdfUrl") + admin.getUserid() + "/" + lessonid + ".pdf";
			
			res = generatePDF(h_response.getEntity().getContent(), fullfilename, baseurl);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{isr.close();isr=null;}catch(Exception e){}
		}

		return res;
		
	}
	
	
	public boolean generatePDF(InputStream isr, String pdfFilename, String baseurl){
		FileOutputStream fos = null;
		boolean res = false;

		try{
			
			this.archivoEnvio =  File.createTempFile(pdfFilename, ".pdf");
			this.archivoEnvio.deleteOnExit();
			
			OutputStream outputStream =
                    new FileOutputStream(this.archivoEnvio);

		int read = 0;
		byte[] bytes = new byte[1024];

		while ((read = isr.read(bytes)) != -1) {
			outputStream.write(bytes, 0, read);
		}
			
			outputStream.close();
			
			
			
			res = true;
			
			//res = baos.toByteArray();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{fos.close();fos=null;}catch(Exception ee){}
		}

		return res;
	}
}
