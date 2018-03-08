package top.linjt.java_learning.jsoup;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.xml.internal.bind.v2.runtime.output.NamespaceContextImpl;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ListIterator;

/**
 * @author XxX
 * @date 2018/3/8
 * @description 1
 *
 */
public class Main {

    private static final String domain = "http://www.17k.com";

    public static void main(String[] args) throws IOException {
        Logger log = Logger.getLogger(Main.class);
        Document doc = Jsoup.connect(domain + "/list/2721543.html").get();
        File novel = new File("D:\\upload\\1.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(novel,true);
        if(novel.exists()){
            novel.mkdirs();
        }
        System.out.println(novel.getAbsolutePath());

        log.info(doc.title()+"\r\n");

        //分卷
        Elements captures = doc.select(".Main.List");
        fileOutputStream.write(captures.select(".Title").text().getBytes());

        Elements selects = captures.select(".Volume");

        ListIterator<Element> iterator = selects.listIterator();
        while (iterator.hasNext()){
            //每一卷的章节
            Elements e = iterator.next().select("dd a");
            for (Element a_Tag : e) {
                //获取每一章的文字
                fileOutputStream.write(getText(domain + a_Tag.attr("href")).getBytes());
            }
        }
    }

    private static String getText(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements capture = doc.select("#readArea");

        String title = capture.select("h1").text()+"\r\n";

        Elements content_before = capture.select(".p");
        content_before.select("div").remove();

        return title+ content_before.html().replace("<br>", "") +"\r\n";

    }
}
