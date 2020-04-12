import { WebPlugin } from '@capacitor/core';
import { IonAndroidWebSocketServerPlugin } from './definitions';
export declare class IonAndroidWebSocketServerWeb extends WebPlugin implements IonAndroidWebSocketServerPlugin {
    constructor();
    stopServer(): Promise<any>;
    serverStatus(): Promise<{
        status: boolean;
    }>;
    startServer(): Promise<any>;
    echo(options: {
        value: string;
    }): Promise<{
        value: string;
    }>;
}
declare const IonAndroidWebSocketServer: IonAndroidWebSocketServerWeb;
export { IonAndroidWebSocketServer };
