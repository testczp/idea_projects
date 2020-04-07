package appium.server;

import java.util.List;

/**
 * Created by chenzepeng on 2020/1/17.
 */
public class IsPortBeUsed {
    /**
     * 判断本机端口是否被占用
     *
     * @return: false:表示端口没被占用 true：端口被占用
     * */
    public static boolean isLocatePortUsed(int port){

        boolean flag = false;
        //获取OS名称
        String osName = System.getProperty("os.name");

        if(osName.contains("indow")){

            String commonds = "netstat -ano|findstr"+" \""+port+"\"";

            try {
                List<String> executed = ExecuteCommand.executed(commonds, true, 0);

                if(executed.size() > 0){
                    flag = true;
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }else{

            String commonds = "lsof -i:"+port;

            try {
                List<String> executed = ExecuteCommand.executed(commonds, true, 0);
                if(executed.size() > 0){

                    flag = true;
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        return flag;
    }
}
