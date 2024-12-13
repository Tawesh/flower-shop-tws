项目支持邮件分发验证码，详情请去看个人博客https://blog.csdn.net/qq_63704334/article/details/130807260
其他配置详情如下：

1. 下载 Niginx for windows 配置本地文件服务器

2. 修改 Nginx 中 /conf/nginx.conf

   添加一个sever块，内容如下：

   ```yaml
   server{
   	  listen       5200; 
   	  server_name  www.fs.tws.com;
         add_header 'Access-Control-Allow-Origin' *;
         add_header 'Access-Control-Allow-Credentials' 'true';
         add_header 'Access-Control-Allow-Methods' *;
         add_header 'Access-Control-Allow-Headers' *;
   	  location /images/ { 
   		 root   E:\fs-upload;
   		 index  index.html index.htm;
   	  }
   }
   ```

   

3. 配置mysql数据库，sql文件在项目文件夹db中

4. 初次打开项目时，先run一遍pom.xml文件

5. 由于项目某些元素可能涉及其他利益，所以具体商品图片文件未上传！若需要请联系taoweishu29@gmail.com
