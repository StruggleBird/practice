/**
 * 
 */
package test.nio.timedemo;

/**
 * @author Ternence
 * @create 2015年5月1日
 */
public class TimerClient {
    public static void main(String[] args) {
        int serverPort = 8080;
        if (args != null && args.length > 0) {
            serverPort = Integer.parseInt(args[0]);
        }
        TimerClientHandler timerClientHandler = new TimerClientHandler(serverPort);
        new Thread(timerClientHandler).start();
    }
}
