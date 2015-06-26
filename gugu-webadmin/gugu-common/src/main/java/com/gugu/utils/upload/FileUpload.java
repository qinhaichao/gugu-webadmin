package com.gugu.utils.upload;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Tom
 */
public class FileUpload {
	/**
	* ģ��form������ʽ ���ϴ��ļ� �����������ʽ���ļ�д�뵽url�У�Ȼ��������������ȡurl����Ӧ
	* 
	* @param url �����ַ form��url��ַ
	* @param filePath �ļ��ڷ���������·��
	* @return String ��ȷ�ϴ�����media_id
	* @throws IOException
	*/
	
	/** ΢���ϴ��ļ��ӿ�  */
	public  String uploadFile(String url,String filePath,String type) throws Exception {
		 File file = new File(filePath);
		 String result = null;
	        if (!file.exists() || !file.isFile()) {
	            return "�ļ�·������";
	        }
	        /**
	         * ��һ����
	         */
	        url = url+"&type="+type;
	        URL urlObj = new URL(url);
	        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

	        /**
	         * ���ùؼ�ֵ
	         */
	        con.setRequestMethod("POST"); // ��Post��ʽ�ύ����Ĭ��get��ʽ
	        con.setDoInput(true);
	        con.setDoOutput(true);
	        con.setUseCaches(false); // post��ʽ����ʹ�û���
	        // ��������ͷ��Ϣ
	        con.setRequestProperty("Connection", "Keep-Alive");
	        con.setRequestProperty("Charset", "UTF-8");
	
	        // ���ñ߽�
	        String BOUNDARY = "----------" + System.currentTimeMillis();
	        con.setRequestProperty("content-type", "multipart/form-data; boundary=" + BOUNDARY);
	        //con.setRequestProperty("Content-Type", "multipart/mixed; boundary=" + BOUNDARY);
	        //con.setRequestProperty("content-type", "text/html");
	        // ����������Ϣ
	
	        // ��һ���֣�
	        StringBuilder sb = new StringBuilder();
	        sb.append("--"); // ////////�����������
	        sb.append(BOUNDARY);
	        sb.append("\r\n");
	        sb.append("Content-Disposition: form-data;name=\"file\";filename=\""
	                + file.getName() + "\"\r\n");
	        sb.append("Content-Type:application/octet-stream\r\n\r\n");
	        byte[] head = sb.toString().getBytes("utf-8");
	        // ��������
	        OutputStream out = new DataOutputStream(con.getOutputStream());
	        out.write(head);
	        
	        // �ļ����Ĳ���
	        DataInputStream in = new DataInputStream(new FileInputStream(file));
	        int bytes = 0;
	        byte[] bufferOut = new byte[1024];
	        while ((bytes = in.read(bufferOut)) != -1) {
	            out.write(bufferOut, 0, bytes);
	        }
	        in.close();
	        // ��β����
	        byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// ����������ݷָ���
	        out.write(foot);
	        out.flush();
	        out.close();
	        /**
	         * ��ȡ��������Ӧ�������ȡ,�����ύ���ɹ�
	         */
	       // con.getResponseCode();

	        /**
	         * ����ķ�ʽ��ȡҲ�ǿ��Ե�
	         */

	         try {

	         // ����BufferedReader����������ȡURL����Ӧ
	        	 StringBuffer buffer = new StringBuffer();
		         BufferedReader reader = new BufferedReader(new InputStreamReader(
		         con.getInputStream(),"UTF-8"));
		         String line = null;
		         while ((line = reader.readLine()) != null) {
		            //System.out.println(line);
		            buffer.append(line);
		         }
		         if(result==null){
					result = buffer.toString();
				}
		         System.out.println(buffer.toString());
		         return buffer.toString();
	         } catch (Exception e) {
	        	 System.out.println("����POST��������쳣��" + e);
	        	 e.printStackTrace();
	         }
	         return result;
	}
	
	
	public static void main(String[] args) throws Exception {
		String filePath = "d:/mv1.jpg";
		String token="Jdr_B5dQzbWlmmTAlMxbpOZiUfe100laWKeNjRgqfYAJ2GkgCdbQCQO4gAA6e0qd7uYM8fhhzx9ehQBCHlQvKQ";
		String result = null;
		FileUpload fileUpload = new FileUpload();
		result = fileUpload.uploadFile(token, filePath, "image");
		System.out.println(result);
	}
}
