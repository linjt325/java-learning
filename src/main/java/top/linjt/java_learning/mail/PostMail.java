package top.linjt.java_learning.mail;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.Timestamp;
import java.util.Properties;

/**
 * @author: XxX
 * @date: 2018/3/8
 * @Description: 通过代码实现邮件的发送
 */
public class PostMail {

    private static final String AUTH_CODE = "xxfqcrkajqrtbhii";
    private static final String USERNAME = "xxx@qq.com";
    private static final String DEST_ADDRESS = "linjuntao325@163.com";


    public static void main(String[] args) throws Exception {

        sendMail(DEST_ADDRESS);

    }


    public static void sendMail(String to  ) throws Exception {


        Properties properties = new Properties();
        //邮件传输协议类型
        properties.setProperty("mail.transport.protocol", "smtp");
        //smtp服务器ip
        properties.setProperty("mail.smtp.host", "smtp.qq.com");
        //需要请求认证
        properties.setProperty("mail.smtp.auth", "true");

        /*
        * 需要ssl连接时要用到下面的三个属性
        * */
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
        //ssl smtp服务器对应的端口
        properties.setProperty("mail.smtp.socketFactory.port", "465");

        //创建session,传入smtp服务器给的授权码
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME,AUTH_CODE);
            }
        });
        session.setDebug(true);

        //创建右键
        Message message = createMail(session,to);

        //通过静态方法发送邮件, 在邮件中存入了 发件人以及收件人的信息 ;

        Transport.send(message) ;

        //但是如果要通过邮件模板进行发送的话 可以用下面的代码
//        Transport transport = session.getTransport();
//        transport.sendMessage(message,addresses);
    }


    /**
     *创建一个邮件对象（MimeMessage）；
     设置发件人，收件人，可选增加多个收件人，抄送人，密送人；
     设置邮件的主题（标题）；
     设置邮件的正文（内容）；
     设置显示的发送时间；
     保存到本地。
     */
    private static Message createMail(Session session,String to) throws Exception {

        //创建右键
        Message message = new MimeMessage(session);
        //设置发件人
        message.setFrom(new InternetAddress(USERNAME,"xXx","utf-8"));
        //设置邮件主题
        message.setSubject("发件主题");

        //设置内容,会覆盖之前的内容
        message.setContent("<h1>啊屁啊 你收到了么</h1>", "text/html;charset=utf-8");
//        message.setContent("1231", "text/plain");

        message.setRecipient(Message.RecipientType.TO,new InternetAddress(to) );
        //抄送
//        message.setRecipient(Message.RecipientType.CC,new InternetAddress("xxx@qq.com")  );
        //暗送
//        message.setRecipient(Message.RecipientType.BCC, new InternetAddress("xxx@163.com") );

        message.setReplyTo(new InternetAddress[]{new InternetAddress(USERNAME)});
        //设置邮件的发送时间,好像没用
        message.setSentDate(Timestamp.valueOf("2017-01-01 00:00:00"));

        //保存邮件内容
        message.saveChanges();
        return message;
    }
}
