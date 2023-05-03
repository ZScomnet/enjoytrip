const { defineConfig } = require("@vue/cli-service");
module.exports = defineConfig({
  outputDir: "../src/main/resources/static",
  devServer: {
    // 개발환경에서의 서버 섲어
    proxy: {
      // 프록시 설정
      "/api": {
        // '/api' 로 들어오면 SpringServer로 보낸다.
        target: "http://localhost:8080",
        changeOrigin: true, // cross origin 허용
        // 서로 다른 출처의 리소스를 공유한다 : CORS(Cross Origin Resources Sharing)
      },
    },
  },
  transpileDependencies: true,
});
