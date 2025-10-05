import './assets/main.css'
import { createApp } from 'vue'
import { createPinia } from 'pinia'
// 引入组件库的少量全局样式变量

import App from './App.vue'
import router from './router'

const app = createApp(App)

app.use(createPinia())
app.use(router)


app.mount('#app')
