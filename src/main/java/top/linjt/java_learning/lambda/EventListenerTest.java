package top.linjt.java_learning.lambda;

import top.linjt.java_learning.lambda.base.BaseCompareWithLambda;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author: XxX
 * @date: 2018/4/26
 * @Description: swing 给botton 添加事件
 */
public class EventListenerTest extends BaseCompareWithLambda {


    JFrame frame = new JFrame("按钮窗体");
    JButton show = new JButton("show");

    @Override
    public void normalMethod() {
        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("event handling without lambda expression");
            }
        });

    }

    @Override
    public void lambdaMethod() {
        show.addActionListener((e)->{
            System.out.println("lambda expression ");

        });
    }

    public static void main(String[] args) {
        EventListenerTest _this = new EventListenerTest();

        JFrame f = _this.frame;
        f.setSize(800, 600);
        f.setVisible(true);
        f.setContentPane(_this.show);
        _this.run();

    }
}
