//const TerserPlugin = require("terser-webpack-plugin");
//
//config.optimization = config.optimization || {};
//config.optimization.minimize = true;
//config.optimization.minimizer = [
//    new TerserPlugin({
//        terserOptions: {
//            mangle: true,    // Note: By default, mangle is set to true.
//            compress: false, // Disable the transformations that reduce the code size.
//            output: {
//                beautify: false,
//            },
//        },
//    }),
//];

config.resolve = {
    fallback: {
        fs: false,
        path: false,
        crypto: false,
    }
};

const CopyWebpackPlugin = require('copy-webpack-plugin');
config.plugins.push(
    new CopyWebpackPlugin({
        patterns: [
            '../../node_modules/sql.js/dist/sql-wasm.wasm'
        ]
    })
);

