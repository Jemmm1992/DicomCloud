var path = require('path');

module.exports = {
    entry: './app.js', //entry为入口文件，即webpack以这个文件为基础打包成另一个文件，所以entry文件包括了要导入的文件
    devtool: 'sourcemaps',
    cache: true,
    debug: true,
    output: { //打包输出出bundle.js文件，这个文件就可以导入HTML中了
        path: __dirname + '/assets',	// 打包文件存放的绝对路径
		// publicPath:'/assets/',	// 网站运行时的访问路径
        filename: '../static/bundle.js'	// 打包后的文件名
    },

    resolve: {
        modulesDirectories: ['node_modules', './src'],  // import时到哪些地方去寻找模块
        extensions: ['', '.js', '.jsx'],  // require的时候可以直接使用require('file')，不用require('file.js')
        alias: {
            antdcss: 'antd/dist/antd.min.css',  // import时的别名
        },
    },

    module: {
        loaders: [   // 定义各种loader
            {
                test: /\.jsx?$/,
                loaders: ['react-hot', 'babel-loader?' + JSON.stringify(babelLoaderConfig)],  // react-hot-loader可以不用刷新页面, 如果用普通的dev-server的话会自动刷新页面
                exclude: /node_modules/,
            }, {
                test: /\.css$/,
                loader: 'style!css',
            }, {
                test: /\.less$/,
                loader: 'style!css!' + `less?{"sourceMap":true,"modifyVars":${JSON.stringify(lessLoaderVars)}}`,  // 用!去链式调用loader
            }, {
                test: /\.(png|jpg|svg)$/,
                loader: 'url?limit=25000',  // 图片小于一定值的话转成base64
            }, {
                test: path.join(__dirname, '.'),
                exclude: /(node_modules)/,
                loader: 'babel', //用babel转换JSX
                query: {
                    cacheDirectory: true,
                    presets: ['es2015', 'react']
                }
            }
        ]
    }
};
