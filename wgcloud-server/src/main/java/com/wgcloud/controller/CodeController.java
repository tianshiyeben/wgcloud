package com.wgcloud.controller;

import com.wgcloud.util.staticvar.StaticKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @version v2.3
 * @ClassName:CodeController.java
 * @author: http://www.wgstart.com
 * @date: 2019年11月16日
 * @Description: CodeController.java
 * @Copyright: 2017-2022 wgcloud. All rights reserved.
 */
@Controller
@RequestMapping(value = "/code")
public class CodeController {

    private static final Logger logger = LoggerFactory.getLogger(CodeController.class);
    private int codeCount = 4;//定义图片上显示验证码的个数  
    char[] codeSequence = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    /**
     * 获取验证码
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "get")
    public void hostInfoList(Model model, HttpServletRequest req, HttpServletResponse resp) {
        int xx = 13;
        int codeY = 22;
        int width = 70;//定义图片的width
        int height = 30;//定义图片的height
        int fontHeight = 20;
        // 定义图像buffer  
        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//      Graphics2D gd = buffImg.createGraphics();  
        //Graphics2D gd = (Graphics2D) buffImg.getGraphics();  
        Graphics gd = buffImg.getGraphics();
        // 创建一个随机数生成器类  
        Random random = new Random();
        // 将图像填充为白色  
        gd.setColor(Color.getColor("#ef3f22"));
        gd.fillRect(0, 0, width, height);

        // 创建字体，字体的大小应该根据图片的高度来定。  
        Font font = new Font("ITALIC", 0, fontHeight);
        // 设置字体。  
        gd.setFont(font);

        // 画边框。  
        gd.setColor(Color.BLACK);
        gd.drawRect(0, 0, width - 1, height - 1);


        // randomCode用于保存随机产生的验证码，以便用户登录后进行验证。  
        StringBuilder randomCode = new StringBuilder();
        int red = 0, green = 0, blue = 0;
        Font font2 = new Font("Fixedsys", Font.BOLD, fontHeight);
        // 设置字体。  
        gd.setFont(font2);
        // 随机产生codeCount数字的验证码。  
        for (int i = 0; i < codeCount; i++) {
            // 得到随机产生的验证码数字。  
            String code = String.valueOf(codeSequence[random.nextInt(codeSequence.length)]);
            // 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。  
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);

            // 用随机产生的颜色将验证码绘制到图像中。  
            //gd.setColor(new Color(red, green, blue)); 
            gd.drawString(code, (i + 1) * xx, codeY);
            // 将产生的四个随机数组合在一起。  
            randomCode.append(code);
        }
        // 将四位数字的验证码保存到Session中。  
        HttpSession session = req.getSession();
        session.setAttribute(StaticKeys.SESSION_CODE, randomCode.toString());

        // 禁止图像缓存。  
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);

        resp.setContentType("image/jpeg");

        // 将图像输出到Servlet输出流中。  
        ServletOutputStream sos;
        try {
            sos = resp.getOutputStream();
            ImageIO.write(buffImg, "jpeg", sos);
            sos.close();
        } catch (IOException e) {
            logger.error("生成验证码异常：", e);
        }

    }


}