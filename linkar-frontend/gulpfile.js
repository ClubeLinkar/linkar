var gulp = require('gulp');
var clean = require('gulp-clean');
var browserSync = require('browser-sync').create();
var proxy = require('proxy-middleware');
var wiredep = require('wiredep').stream;

var url = require('url');

gulp.task('default', ['copy'], function () {
  gulp.start('wiredep');
});

gulp.task('server', ['copy'], function () {
  browserSync.init({
    port: 9000,
    server: {
      baseDir: 'dist'
    }
  });

  gulp.watch('dist/**/*').on('change', browserSync.reload);
});

gulp.task('wiredep', function () {
  gulp
    .src('app/index.html')
    .pipe(
      wiredep({
        optional: 'configuration',
        goes: 'here'
      })
    ).pipe(gulp.dest('dist'));
});


gulp.task('browser-sync', function() {
    var proxyOptions = url.parse('http://localhost:8080/linkar/api');
    proxyOptions.route = '/api';
    // requests to `/api/x/y/z` are proxied to `http://localhost:3000/secret-api`

    browserSync.init({
        open: true,
        port: 9000,
        server: {
            baseDir: "dist",
            middleware: [proxy(proxyOptions)]
        }
    });
});



gulp.task('copy', ['clean'], function () {
  return gulp
    .src('app/**/*')
    .pipe(gulp.dest('dist'));
});

gulp.task('clean', function () {
  return gulp
    .src('dist')
    .pipe(clean());
});
