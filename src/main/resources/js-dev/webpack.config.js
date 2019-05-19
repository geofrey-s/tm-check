const path = require('path');

module.exports = {
    mode: "development",
    entry: path.resolve(__dirname,'app.js'),
    output: {
        filename: 'app-build.js',
        path: path.resolve(__dirname, '../static/js')
    }
};