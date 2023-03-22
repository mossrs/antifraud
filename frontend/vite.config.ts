import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'
import viteCompression from "vite-plugin-compression";
import vueJsx from '@vitejs/plugin-vue-jsx';

export default defineConfig({
  // ...
  plugins: [
    // ...
    vue(),
    vueJsx(),
    viteCompression({
      ext: ".gz",
      deleteOriginFile: false
    }),
    AutoImport({
      resolvers: [ElementPlusResolver()],
    }),
    Components({
      resolvers: [ElementPlusResolver()],
    }),
  ]
});
