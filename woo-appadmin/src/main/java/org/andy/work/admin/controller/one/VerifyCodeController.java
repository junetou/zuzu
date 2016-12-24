package org.andy.work.admin.controller.one;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.andy.work.admin.helper.VerifyCodeCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class VerifyCodeController {

	@Autowired 
	private VerifyCodeCookie codecookie;
	
   //验证码宽度，长度，字数
   private static final int imgwidth=70;
   private static final int imgheight=23;
   private static final int codecount=5;

   //验证吗字体的长度和宽度
   private static final int fontheight=18;
   private static final int fontY=17;
   
   private static final char[] code={'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h','i','j',
		'k', 'k', 'm', 'n','o','p', 'q', 'r', 's', 't', 'u', 'v', 'w',
		'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
   
   @RequestMapping(value="/verifycode")
   private void drawCode(HttpServletResponse response,HttpServletRequest request){
	   
	   BufferedImage bufimg=new BufferedImage(imgwidth,imgheight,BufferedImage.TYPE_INT_BGR);
	   Graphics2D graphics=bufimg.createGraphics();
	   
	   Random random=new Random();
	   
	   //背景颜色
	   int backgroundX=random.nextInt(255);
	   int backgroundY=random.nextInt(255);
	   int backgroundZ=random.nextInt(255);
	   
	   Color color = new Color(backgroundX,backgroundY,backgroundZ);
	   graphics.setColor(color);
       graphics.fillRect(0, 0, imgwidth, imgheight);
       
       Font font=new Font("Fixedsys",Font.PLAIN,fontheight);
	   graphics.setFont(font);
	   
		//随机产生50条干扰线，使图象中的认证码不易被其它程序探测到。
		graphics.setColor(Color.GRAY); 
		for(int i = 0; i < 35; i++) {
			int x = random.nextInt(imgwidth); 
			int y = random.nextInt(imgheight); 
			int xl = random.nextInt(12); 
			int yl = random.nextInt(12); 
			graphics.drawLine(x, y, x + xl, y + yl); 
		}
		
		StringBuffer randcodes = new StringBuffer(); 
		int red = 0, green = 0, blue = 0; 
		
		int fontX=4;
		for(int i=0;i<codecount;i++){
			//得到随机产生的验证码数字。
			String rand=String.valueOf(code[random.nextInt(36)]);
		    
			red=random.nextInt(255);
			green=random.nextInt(10);
			blue=random.nextInt(50);
			//将验证码绘制到图像中。
			graphics.setColor(new Color(red,green,blue));
		    graphics.drawString(rand, fontX, fontY);
		    randcodes.append(rand);
		    fontX=fontX+13;
		}
		
		this.codecookie.setSystemCode(request, randcodes.toString());
		
		// 禁止图像缓存。
		response.setHeader("Pragma", "no-cache"); 
		response.setHeader("Cache-Control", "no-cache"); 
		response.setDateHeader("Expires", 0);
		
		try{
			ServletOutputStream out=response.getOutputStream();
			ImageIO.write(bufimg, "jpeg", out);
			out.flush();
			out.close();
		}catch(Exception e){
			System.out.println(e);
		}
	   
   }
   
   
   
   
}
