declare module "@capacitor/core" {
  interface PluginRegistry {
    IonAndroidWebSocketServer: IonAndroidWebSocketServerPlugin;
  }
}

export interface IonAndroidWebSocketServerPlugin {
  echo(options: { value: string }): Promise<{value: string}>;
}
