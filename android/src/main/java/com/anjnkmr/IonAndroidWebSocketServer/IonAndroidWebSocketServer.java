package com.anjnkmr.IonAndroidWebSocketServer;

import android.util.Log;

import com.getcapacitor.JSObject;
import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.koushikdutta.async.AsyncServer;
import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.http.WebSocket;
import com.koushikdutta.async.http.server.AsyncHttpServer;
import com.koushikdutta.async.http.server.AsyncHttpServerRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@NativePlugin()
public class IonAndroidWebSocketServer extends Plugin {

    AsyncHttpServer httpServer;
    List<WebSocket> _sockets = new ArrayList<WebSocket>();

    @PluginMethod()
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", value);
        call.success(ret);
    }

    @PluginMethod()
    public void stopServer(PluginCall call) {
        httpServer.stop();
        Log.d("Server Stopped", "WS Server Stopped");
        call.resolve();
    }

    @PluginMethod()
    public void serverStatus(PluginCall call) {
        JSObject object = new JSObject();
        object.put("status", _sockets.size() > 0);
        Log.d("serverStatus", "WS Server Status " + _sockets.size());
        call.resolve(object);
    }

    @PluginMethod()
    public void startServer(final PluginCall call) {
        httpServer = new AsyncHttpServer();

        httpServer.listen(AsyncServer.getDefault(), 8090);

        httpServer.websocket("/game", new AsyncHttpServer.WebSocketRequestCallback() {

            @Override
            public void onConnected(final WebSocket webSocket, AsyncHttpServerRequest request) {
                _sockets.add(webSocket);
                Log.d("startServer", "WS Server Connected " + _sockets.size());

                //Use this to clean up any references to your websocket
                webSocket.setClosedCallback(new CompletedCallback() {
                    @Override
                    public void onCompleted(Exception ex) {
                        try {
                            if (ex != null)
                                Log.e("WebSocket", "An error occurred", ex);
                        } finally {
                            _sockets.remove(webSocket);
                        }
                    }
                });


                webSocket.setStringCallback(new WebSocket.StringCallback() {
                    @Override
                    public void onStringAvailable(String s) {
                        Log.d("onStringAvailable", "WS Server onStringAvailable " + s);
                        if ("Hello Server".equals(s))
                            webSocket.send("Welcome Client!");
                    }
                });

                call.resolve();
            }
        });
    }
}
