package util;

import java.io.File;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File f) {
		//경로
		String dir = f.getParent();
		// 파일명
		String fileName = f.getName();
		
		String ext = fileName.substring(fileName.lastIndexOf("."));
		fileName = System.currentTimeMillis() + ext;
		return new File(dir+System.getProperty("file.separator")+fileName);
	}
	
	public static void main(String[] args) {
		File f = new File("D:\\kdigital\\web\\img\\americano.jpg");
		MyFileRenamePolicy mrp = new MyFileRenamePolicy();
		File f2 = mrp.rename(f);
		System.out.println(f2.getPath());
	}

}
