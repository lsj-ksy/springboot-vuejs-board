module.exports = {
    outputDir: process.env.VUE_APP_OUTDIR,
    publicPath: process.env.VUE_APP_PUBLICPATH,
    devServer: {
        proxy: {
            port: 3000,
            '/api': {
                target: {
                    host: 'localhost',
                    protocol: 'http',
                    port: process.env.VUE_APP_PORT
                },
                ws: true,
                changeOrigin: true
            }
        }
    }
}