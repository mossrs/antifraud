import { createApp } from 'vue';
import App from './App.vue';
import { createRouter } from 'vue-router';
import routerOptions from './router';
import 'element-plus/dist/index.css';

const router = createRouter(routerOptions);
createApp(App).use(router).mount('#app');