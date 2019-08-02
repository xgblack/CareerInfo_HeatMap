package cn.xgblack.heatmap.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * @author 小光
 * @date 2019/8/1 22:39
 * className: CheckCodeController
 * description:
 * ***************************************************************************
 * Copyright(C),2018-2019,https://blog.xgblack.cn  .All rights reserved.
 * ***************************************************************************
 */
@Controller("checkCodeController")
public class CheckCodeController {

    @RequestMapping("/checkCode")
    public void showCheckCode(HttpSession session, HttpServletResponse response) throws IOException {
        //调用方法，生成验证码图片
        String checkcode_session = randomCodeImage(150, 50, 4, response);

        session.setAttribute("checkcode_session",checkcode_session);

    }

    /**
     * 适用于后台验证码图片的输出
     * @param width
     * @param height
     * @param len
     * @param response
     * @return code验证码
     * @throws IOException
     */
    public String randomCodeImage(int width,int height,int len, HttpServletResponse response) throws IOException {
        String code=null;
        //生成随即验证码
        code=checkCode(len);
        //开始画图
        //设置画布大小
        BufferedImage image=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //加入画笔
        Graphics g=image.getGraphics();
        //设置画笔颜色
        g.setColor(new Color(255,255,255));
        //用笔给画布设置背景颜色
        g.fillRect(0, 0, width, height);
        //换笔颜色，等下写字，，随机颜色
        Random r=new Random();
        g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
        //设置字体
        g.setFont(new Font("Microsoft YaHei", Font.ITALIC,(int)(height*0.82)));
        //写入验证码
        g.drawString(code,(int)(width*(1.0/6)), (int)(height*0.85));
        //加干扰线
        for (int i = 0; i < 3+r.nextInt(8); i++) {
            g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
            g.drawLine(r.nextInt(80), r.nextInt(30), r.nextInt(80), r.nextInt(30));
        }
        response.setContentType("image/jpeg");
        OutputStream os=response.getOutputStream();
        //压缩并且输出
        javax.imageio.ImageIO.write(image, "jpeg", os);
        os.close();
        return code;
    }


    /**
     * 生成随即字符串，如果要生成计算体就要改并这段代码
     * @param len
     * @return String 生成的指定长度的随机字符串
     */
    private String checkCode(int len) {
        String code=null;
        char[] codeAll= {'Q','W','E','R','T','Y','U','I','O','P','A','S','D','F','G','H','J','K','L','Z','X','C','V','B','N','M',
                'q','w','e','r','t','y','u','i','o','p','a','s','d','f','g','h','j','k','l','z','x','c','v','b','n','m',
                '1','2','3','4','5','6','7','8','9','0'};
        StringBuffer str=new StringBuffer();
        Random r=new Random();
        for (int i = 0; i <len; i++) {
            str.append(codeAll[r.nextInt(codeAll.length)]);
        }
        code=str.toString();
        return code;
    }

}
