配置详情如下：

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
   	  location /videos/ {
   		 root   E:\sex-video;
   	  }
   }
   ```

   

3. 配置mysql数据库，sql文件在项目文件夹db中

4. 初次打开项目时，先run一遍pom.xml文件

5. over！