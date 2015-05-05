/**
 * 
 */
package test.nio.timedemo;

/**
 * @author Ternence
 * @create 2015年5月1日
 */
public class TimerServer {
    public static void main(String[] args) {
        int port = 8080;
        if (args!=null && args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        
        MultiPlexerTimerServer server = new MultiPlexerTimerServer(port);
        new Thread(server).start();
    }
}
