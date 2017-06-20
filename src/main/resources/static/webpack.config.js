const path = require('path');
const webpack = require('webpack');
const ExtractTextPlugin = require('extract-text-webpack-plugin');
const extractPlugin = new ExtractTextPlugin('[name].css');
const HtmlWebpackPlugin = require('html-webpack-plugin');

const commonsPlugin = new webpack.optimize.CommonsChunkPlugin({
    name: 'vendor',
    filename: 'vendor.js'
});

const srcPath = path.resolve(__dirname, 'src');
const buildPath = path.resolve(__dirname, 'assets');

module.exports = {
    entry: './app.js', //entry为入口文件，即webpack以这个文件为基础打包成另一个文件，所以entry文件包括了要导入的文件
    devtool: 'source-map',
    output: { //打包输出出bundle.js文件，这个文件就可以导入HTML中了
        path: buildPath,	// 打包文件存放的绝对路径
        filename: '[name].js', // 打包后的文件名
        publicPath: '${contextPath}/static/<%=ConfigHelper.assetsDir%>/'
    },

    plugins: [
        extractPlugin
    ],

    module: {
        rules: [
            // html
            {
                test: /\.(cs)?htm(l?)$/,
                loader: 'html-loader'
            },
            // 提取css
            {
                test: /\.css$/,
                use: ExtractTextPlugin.extract({
                    fallback: 'style-loader',
                    use: 'css-loader'
                })
            },

            {
                test: /\.js$/,
                use: {
                    loader: 'babel-loader'
                }
            },
            {
                test: /\.scss$/,
                use: [
                    'style-loader',
                    'css-loader',
                    'scss-loader'
                ]
            },

            {
                test: /\.less$/,
                use: [
                    'style-loader',
                    'css-loader',
                    'less-loader'
                ]
            },

            {
                test: /\.(png|jpg|gif)$/,
                use: {
                    loader: 'url-loader',
                    options: {
                        limit: '8192'
                    }
                }
            },

            {
                test: /\.woff(2*)$/,
                use: {
                    loader: 'url-loader',
                    options: {
                        limit: '10000',
                        minetype: 'application/font-woff'
                    }
                }
            },
            { test: /\.ttf$/, loader: 'file-loader' },
            { test: /\.eot$/, loader: 'file-loader' },
            { test: /\.svg$/, loader: 'file-loader' }
        ]
    }
};
