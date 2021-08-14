module.exports = {
    outputDir: '../src/main/resources/static',
    devServer: {
        proxy: {
            port: 3000,
            '/api': {
                target: 'http://localhost:8090',
                ws: true,
                changeOrigin: true
            }
        }
    }
}