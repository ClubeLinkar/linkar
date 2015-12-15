var
  gulp            = require('gulp'),
  clean           = require('gulp-clean'),
  browserSync     = require('browser-sync').create(),
  proxy           = require('proxy-middleware'),
  wiredep         = require('wiredep').stream,
  usemin          = require('gulp-usemin'),
  minifyCss       = require('gulp-minify-css'),
  minifyHtml      = require('gulp-minify-html'),
  uglify          = require('gulp-uglify'),
  url             = require('url')
;

gulp.task('clean', function () {
  return gulp
    .src('dist')
    .pipe(clean());
});

gulp.task('copy', ['clean'], function () {
  return gulp
    .src('app/**/*')
    .pipe(gulp.dest('dist'));
});

gulp.task('wiredep', ['copy'], function () {
  return gulp
    .src('app/index.html')
    .pipe(
      wiredep({
        optional: 'configuration',
        goes: 'here'
      })
    ).pipe(gulp.dest('dist'));
});

gulp.task('usemin', ['wiredep'], function() {
  return gulp.src('app/index.html')
    .pipe(usemin({
      css: [ minifyCss() ],
      vendorcss: [ minifyCss() ],
      // html: [ minifyHtml({ empty: true }) ],
      js: [ uglify() ],
      vendorjs: [ uglify() ],
      inlinejs: [ uglify() ],
      inlinecss: [ minifyCss(), 'concat' ]
    }))
    .pipe(gulp.dest('dist'));
});

gulp.task('default', ['copy'], function () {
  gulp.start('wiredep', 'minify-css');
});


gulp.task('server', function () {

  var proxyOptions = url.parse('http://localhost:8080/linkar/api');
  proxyOptions.route = '/linkar/api';

  browserSync.init({
    port: 9000,
    server: {
      baseDir: 'dist',
      middleware: [proxy(proxyOptions)]
    }
  });

  gulp.watch('dist/**/*').on('change', browserSync.reload);
});



// gulp.task('browser-sync', function() {
//     var proxyOptions = url.parse('http://localhost:8080/linkar/api');
//     proxyOptions.route = '/linkar/api';
//     // requests to `/api/x/y/z` are proxied to `http://localhost:3000/secret-api`
//
//     browserSync.init({
//         open: true,
//         port: 9000,
//         server: {
//             baseDir: "dist",
//             middleware: [proxy(proxyOptions)]
//         }
//     });
// });
