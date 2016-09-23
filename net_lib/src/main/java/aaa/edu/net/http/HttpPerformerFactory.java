package aaa.edu.net.http;

/**
 * Created by aaa on 2016/9/22.
 */
public class HttpPerformerFactory {
        public static BaseHttpPerformer createHttpStack() {
                return new HttpUrlConnPerformer();
        }
}
