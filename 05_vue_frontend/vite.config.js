import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueDevTools from 'vite-plugin-vue-devtools'

// https://vite.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    vueDevTools(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    },
  }, 
  server: {
    proxy: {
      // 选项一：简单代理（匹配路径前缀）
      '/api': {
        target: 'http://localhost:8080', // 后端地址（Tomcat 端口）
        changeOrigin: true, // 允许跨域，模拟真实请求源
        rewrite: (path) => path.replace(/^\/api/, ''), // 去除路径前缀 `/api`
      },

      // 选项二：正则匹配（适用于复杂路径）
      // '^/api/(.*)': {
      //   target: 'http://localhost:8080',
      //   changeOrigin: true,
      //   rewrite: (path) => path.replace(/^\/api\//, ''), // 去除 `/api/` 前缀
      // },
    },
  },
})
