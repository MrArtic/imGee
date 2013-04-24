package grabber;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*
 * 
 */

public class ImgGrabber{
	
	private File folder_path;
	private URL url;
	private String gallery_ID;
	
	
	public ImgGrabber(File folder_path, URL url, String gallery_ID) {

		this.folder_path = folder_path;
		this.url = url;
		this.gallery_ID = gallery_ID;
		
		img_download(folder_path, url);
	}
	
	private void img_download(File folder_path, URL url){
		File file = folder_path;
		try {
			org.apache.commons.io.FileUtils.copyURLToFile(url, file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main (String []args) throws IOException{
		URL myURL = new URL("http://i.imgur.com/H2GKKMt.jpg");
		File file_destination = new File("C:\\img_test\\test.jpg");
		
//		ImgGrabber test = new ImgGrabber(file_destination, myURL, "");
		
		Document doc = Jsoup.connect("http://imgur.com/a/i1mtR").get();
		
		Elements links = doc.select("a[href]");
		
		print("\nLinks: (%d)", links.size());
        for (Element link : links) {
            print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35));
        }
		
		
	}
	
	private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }
	
	private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }
}
