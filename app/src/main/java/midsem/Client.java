package midsem;

import io.javalin.Javalin;

public class Client {
    int port;
    Client(int port){
        this.port = port;
    }
    public void main(){
        Javalin app = Javalin.create().start(port);
        app.post("/check_pan", ctx -> {
            ctx.status(404);
            System.out.println(ctx.body());
        });

        app.post("/cibil", ctx -> {
//            ctx.status(404);
            System.out.println(ctx.body());
        });

        app.post("/translate", ctx -> {
//            ctx.status(404);
            System.out.println(ctx.body());
        });

        app.post("/stock", ctx -> {
//            ctx.status(404);
            System.out.println(ctx.body());
        });

    }
}
