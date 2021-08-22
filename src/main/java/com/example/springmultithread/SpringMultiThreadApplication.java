package com.example.springmultithread;

import com.example.springmultithread.config.MainConfig;
import com.example.springmultithread.webCrawl.Crawler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class SpringMultiThreadApplication {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        /*Crawler crawler = context.getBean("crawler", Crawler.class);
        crawler.search("https://www.walmart.com/", "the");*/

        SpringApplication.run(SpringMultiThreadApplication.class, args);
    }

}
/*
creating react app addition.

create reactApp directory within main
1.) npm init -y (create package.json)
2.) npm install react react-dom --save (install react)
3.) npm install webpack webpack-dev-server webpack-cli --save (install webpack)
///////////////
babel-core and babel-preset-env and babel-preset-react deprecated
//////////////
4.) npm install @babel/core babel-loader @babel/preset-env @babel/preset-react babel-webpack-plugin --save-dev (install babel)
5.) .>index.html 2>NUL (create html file and catch stderr error with NUL)
6.) .>App.js 2>NUL
7.) .>main.js 2>NUL
8.) .>webpack.config.js 2>NUL
9.) .>.babelrc 2>NUL


npm start (load app)
npm run build ( bundle files)
 */

/*
 webpack.config.js can be modified to change output folder of npm run build generated bundle.

 path: path.join(__dirname, '../resources/static/'),

 ../ up one directory and into resources/static/
 */
