//package com.sg.bjftviewprotect.system.config;
//
//import com.ngrok.Http;
//import com.ngrok.Session;
//import lombok.var;
//import org.springframework.boot.CommandLineRunner;
//
//import java.io.IOException;
//import java.nio.ByteBuffer;
//
///**
// * @ClassName NgrokStartRunner
// * @Author wangshuo
// * @Date 2024/5/23 08:57
// * @Version 1.0
// *
// * 这个类在Spring Boot应用启动后自动启动ngrok服务
// **/
////@Component
//public class NgrokStartRunner implements CommandLineRunner {
//    public static void main(String[] args) throws IOException {
//        // Session.withAuthtokenFromEnv() 将创建一个新的会话构建器，从NGROK_AUTHTOKEN环境变量中获取令牌。
//        // 你可以通过注册https://dashboard.ngrok.com来获取你的令牌。
//        var sessionBuilder = Session.withAuthtokenFromEnv().metadata("2cwO7dIVYjFfmIAvGrN1qqKH7vB_2cUigKbZHmpBdbSCcXnxJ");        // UTF-8 编码
//        // Session.Builder让你可以自定义会话的不同方面，详情请参阅文档。
//        // 自定义构建器后，连接：
//        try (var session = sessionBuilder.connect()) {
//            // 创建并配置http监听器，将使用oauth进行保护
//            var listenerBuilder = session.httpEndpoint().metadata("my listener")
//                    .oauthOptions(new Http.OAuth("google"));
//            // 使用上述配置启动监听
//            try (var listener = listenerBuilder.listen()) {
//                System.out.println("ngrok url: " + listener.getUrl());
//                var buf = ByteBuffer.allocateDirect(1024);
//
//                while (true) {
//                    // 接受一个新的连接
//                    var conn = listener.accept();
//
//                    // 从连接中读取
//                    conn.read(buf);
//
//                    System.out.println(buf.asCharBuffer());
//
//                    // 或者写入到连接中
//                    conn.write(buf);
//                    //
//                    //buf.clear();
//                    //buf.put("HTTP/1.0 200 OK\n\nHello from ngrok!".getBytes(utf8));
//                    //buf.flip();
//                    //conn.write(buf);
//                    //conn.close();
//                }
//            }
//        }
//    }
//    //@Override
//    //public void run(String... args) throws IOException {
//    //    // Session.withAuthtokenFromEnv() 将创建一个新的会话构建器，从NGROK_AUTHTOKEN环境变量中获取令牌。
//    //    // 你可以通过注册https://dashboard.ngrok.com来获取你的令牌。
//    //    var sessionBuilder = Session.withAuthtokenFromEnv().metadata("2cwO7dIVYjFfmIAvGrN1qqKH7vB_2cUigKbZHmpBdbSCcXnxJ");        // UTF-8 编码
//    //    // Session.Builder让你可以自定义会话的不同方面，详情请参阅文档。
//    //    // 自定义构建器后，连接：
//    //    try (var session = sessionBuilder.connect()) {
//    //        // 创建并配置http监听器，将使用oauth进行保护
//    //        var listenerBuilder = session.httpEndpoint().metadata("my listener")
//    //                .oauthOptions(new Http.OAuth("google"));
//    //        // 使用上述配置启动监听
//    //        try (var listener = listenerBuilder.listen()) {
//    //            System.out.println("ngrok url: " + listener.getUrl());
//    //            var buf = ByteBuffer.allocateDirect(1024);
//    //
//    //            while (true) {
//    //                // 接受一个新的连接
//    //                var conn = listener.accept();
//    //
//    //                // 从连接中读取
//    //                conn.read(buf);
//    //
//    //                System.out.println(buf.asCharBuffer());
//    //
//    //                // 或者写入到连接中
//    //                conn.write(buf);
//    //                //
//    //                //buf.clear();
//    //                //buf.put("HTTP/1.0 200 OK\n\nHello from ngrok!".getBytes(utf8));
//    //                //buf.flip();
//    //                //conn.write(buf);
//    //                //conn.close();
//    //            }
//    //        }
//    //    }
//    //}
//}
