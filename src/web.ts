import { WebPlugin } from '@capacitor/core';
import { IonAndroidWebSocketServerPlugin } from './definitions';

export class IonAndroidWebSocketServerWeb extends WebPlugin implements IonAndroidWebSocketServerPlugin {
  constructor() {
    super({
      name: 'IonAndroidWebSocketServer',
      platforms: ['web']
    });
  }

  async echo(options: { value: string }): Promise<{value: string}> {
    console.log('ECHO', options);
    return options;
  }
}

const IonAndroidWebSocketServer = new IonAndroidWebSocketServerWeb();

export { IonAndroidWebSocketServer };

import { registerWebPlugin } from '@capacitor/core';
registerWebPlugin(IonAndroidWebSocketServer);
