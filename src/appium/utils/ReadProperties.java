package appium.utils;

import java.io.*;
import java.util.Properties;

/**
 * Created by chenzepeng on 2020/1/28.
 */
public class ReadProperties {
    private String filePath;
    private Properties properties;

    /**
     * @throws Exception
     * @function:构造方法 创建对象时自动返回pro对象 在new该对象的时候会自动加载readProperties()方法
     *
     * @changedInfo:null
     */
    public ReadProperties(String filePath) {
        this.filePath = filePath;
        // 在new该对象的时候会自动加载readProperties()方法
        this.properties = readProperties();
    }

    public Properties readProperties() {

        // 创建对象
        Properties pro = new Properties();
        // 读取properties文件到缓存
        BufferedInputStream in = null;

        try {
            in = new BufferedInputStream(new FileInputStream(filePath));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 加载缓存到pro对象 prop.load(in)这么写 不能读取properties配置文件中的中文
        try {
            pro.load(new InputStreamReader(in, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 返回pro对象
        return pro;
    }

    /**
     * @function:使用全局的properties对象获取key对应的value值
     *
     * @changedInfo:null
     */
    public String getValue(String key) {

        return properties.getProperty(key);
    }

    public static void main(String[] args) {

        ReadProperties pro = new ReadProperties("./target/test.properties");
        System.out.println(pro.getValue("test"));
    }
}
