'use strict';

module.exports = function (grunt) {

  // Load grunt tasks automatically
  require('load-grunt-tasks')(grunt);

  // Time how long tasks take. Can help when optimizing build times
  require('time-grunt')(grunt);

  // Configurable paths for the application
  var appConfig = {
    app: require('./bower.json').appPath || 'app',
    dist: 'dist',
    apiUrl: 'h-apps.mprj.mp.br',

    e2e: 'coverage/e2e',
    instrumentedE2E: 'coverage/e2e/instrumented'
  };

  // Define the configuration for all the tasks
  grunt.initConfig({

    // Project settings
    yeoman: appConfig,

    // Watches files for changes and runs tasks based on the changed files
    watch: {
      bower: {
        files: ['bower.json'],
        tasks: ['wiredep']
      },

      gruntfile: {
        files: ['Gruntfile.js']
      },
      livereload: {
        options: {
          livereload: '<%= connect.options.livereload %>'
        },
        files: [
          '<%= yeoman.app %>/**/*.{html,js,css,png,jpg,jpeg,gif,webp,svg}',
          '.tmp/css/{,*/}*.css'
        ]
      }
    },

    // The actual grunt server settings
    connect: {
      options: {
        port: 9000,
        // Change this to '0.0.0.0' to access the server from outside.
        hostname: 'localhost',
        livereload: 35729
      },
      livereload: {
        options: {
          open: true,
          middleware: function (connect) {
            return [
              require('grunt-connect-proxy/lib/utils').proxyRequest,
              connect.static('.tmp'),
              connect().use('/bower_components', connect.static('./bower_components')),
              connect.static(appConfig.app)
            ];
          }
        }
      },
      proxies: [
        {
          context: '/linkar/api',
          host: 'localhost',
          port: 8080,
          https: false,
          changeOrigin: false,
          xforward: false,
          headers: {
            'Access-Control-Allow-Headers': 'Origin, X-Requested-With, Content-Type, Accept',
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Max-Age': '3600',
            'Access-Control-Expose-Headers': 'title, message'
          }
        }
      ],
      test: {
        options: {
          port: 9001,
          middleware: function (connect) {
            return [
              connect.static('.tmp'),
              connect.static('test'),
              connect().use('/bower_components', connect.static('./bower_components')),
              connect.static(appConfig.app)
            ];
          }
        }
      },

      runtime: {
        options: {
          middleware: function (connect) {
            return [
              lrSnippet,
              mountFolder(connect, '<%= yeoman.instrumentedE2E% >')
            ];
          }
        }
      },

      dist: {
        options: {
          open: true,
          base: '<%= yeoman.dist %>'
        }
      }
    },

    // Make sure code css are up to par and there are no obvious mistakes
    jshint: {
      options: {
        jshintrc: '.jshintrc',
        reporter: require('jshint-stylish')
      },
      all: {
        src: [
          'Gruntfile.js',
          '<%= yeoman.app %>/modules/{,*/}*.js'
        ]
      },
      test: {
        options: {
          jshintrc: 'test/.jshintrc'
        },
        src: ['test/unit/{,*/}*.js']
      }
    },


    // Empties folders to start fresh
    clean: {
      dist: {
        files: [{
          dot: true,
          src: [
            '.tmp',
            '<%= yeoman.dist %>/{,*/}*',
            '!<%= yeoman.dist %>/.git*'
          ]
        }]
      },
      server: '.tmp'
    },

    // Add vendor prefixed css
    autoprefixer: {
      options: {
        browsers: ['last 1 version']
      },
      dist: {
        files: [{
          expand: true,
          cwd: '.tmp/css/',
          src: '{,*/}*.css',
          dest: '.tmp/css/'
        }]
      }
    },

    // Automatically inject Bower components into the app
    wiredep: {
      app: {
        src: ['<%= yeoman.app %>/index.html'],
        ignorePath: /\.\.\//
      }
    },

    // Renames files for browser caching purposes
    filerev: {
      dist: {
        src: [
          '<%= yeoman.dist %>/scripts/*{,*/}*.js',
          '<%= yeoman.dist %>/css/{,*/}*.css',
          '!<%= yeoman.dist %>/bower_components/pdfjs-dist/build/pdf.js',
          '!<%= yeoman.dist %>/bower_components/pdfjs-dist/build/pdf.worker.js',
          '<%= yeoman.dist %>/images/{,*/}*.{png,jpg,jpeg,gif,webp,svg}',
          '<%= yeoman.dist %>/css/fonts/*'

        ]
      }
    },

    // Reads HTML for usemin blocks to enable smart builds that automatically
    // concat, minify and revision files. Creates configurations in memory so
    // additional tasks can operate on them
    useminPrepare: {
      html: '<%= yeoman.app %>/index.html',
      options: {
        dest: '<%= yeoman.dist %>',
        flow: {
          html: {
            steps: {
              js: ['concat', 'uglifyjs'],
              css: ['cssmin']
            },
            post: {}
          }
        }
      }
    },

    // Performs rewrites based on filerev and the useminPrepare configuration
    usemin: {
      html: ['<%= yeoman.dist %>/{,*/}*.html'],
      css: ['<%= yeoman.dist %>/css/{,*/}*.css'],
      options: {
        assetsDirs: ['<%= yeoman.dist %>', '<%= yeoman.dist %>/images']
      }
    },

    // The following *-min tasks will produce minified files in the dist folder
    // By default, your `index.html`'s <!-- Usemin block --> will take care of
    // minification. These next options are pre-configured if you do not wish
    // to use the Usemin blocks.
    cssmin: {
      dist: {
        files: {
          '<%= yeoman.dist %>/css/main.css': [
            '.tmp/css/{,*/}*.css'
          ]
        }
      }
    },
    uglify: {
      dist: {
        files: {
          '<%= yeoman.dist %>/scripts/scripts.js': [
            '<%= yeoman.dist %>/scripts/scripts.js'
          ]
        }
      },
      flipbook_serve: {
        files: {
          '<%= yeoman.app %>/flipbook/js/pdf_and_turn.min.js' : [
            'flipbook_components/js/pdf.js',
            'flipbook_components/js/turn.js'
          ]
        }
      },
      flipbook_dist: {
        files: {
          '<%= yeoman.dist %>/flipbook/js/pdf_and_turn.min.js' : [
            'flipbook_components/js/pdf.js',
            'flipbook_components/js/turn.js'
          ]
        }
      }
    },
    concat: {
      dist: {}
    },
    htmlmin: {
      dist: {
        options: {
          removeComments: true,
          collapseWhitespace: true,
          conservativeCollapse: true,
          collapseBooleanAttributes: true,
          removeCommentsFromCDATA: true,
          removeOptionalTags: true
        },
        files: [{
          expand: true,
          cwd: '<%= yeoman.dist %>',
          src: ['**/*.html'],
          dest: '<%= yeoman.dist %>'
        }]
      }
    },

    // ng-annotate tries to make the code safe for minification automatically
    // by using the Angular long form for dependency injection.
    ngAnnotate: {
      dist: {
        files: [{
          expand: true,
          cwd: '.tmp/concat/scripts',
          src: ['*.js', '!oldieshim.js'],
          dest: '.tmp/concat/scripts'
        }]
      }
    },

    // Copies remaining files to places other tasks can use
    copy: {
      dist: {
        files: [{
          expand: true,
          dot: true,
          cwd: '<%= yeoman.app %>',
          dest: '<%= yeoman.dist %>',
          src: [
            '*.{ico,png,txt}',
            '.htaccess',
            '*.html',

            './**/*.html',
            './*.html',
            'components/**/*.html',
            'components/*.html',
          ]
        }, {
          expand: true,
          cwd: '.tmp/imagens',
          dest: '<%= yeoman.dist %>/imagens',
          src: ['generated/*']
        } , {
          expand: true,
          cwd: '<%= yeoman.app %>/layout',
          src: 'images/**/*.*',
          dest: '<%= yeoman.dist %>'
        } , {
          expand: true,
          cwd: '<%= yeoman.app %>/layout',
          src: 'fonts/**/*.*',
          dest: '<%= yeoman.dist %>'
        }
          , {
            expand: true,
            cwd: '<%= yeoman.app %>',
            src: 'i18n/*',
            dest: '<%= yeoman.dist %>'
          }
          ,{
            expand : true,
            cwd : 'bower_components/bootstrap-material-design/',
            src: 'fonts/*',
            dest : '<%= yeoman.dist %>'
          },
          {
            expand : true,
            cwd : 'bower_components/mdi/',
            src: 'fonts/*',
            dest : '<%= yeoman.dist %>'
          },

          {
            expand: true,
            cwd: 'bower_components/bootstrap/dist',
            src: 'fonts/*',
            dest: '<%= yeoman.dist %>'
          }, {
            expand: true,
            cwd: 'bower_components/angular-ui-grid/',
            src: ['*.woff', '*.ttf'],
            dest: '<%= yeoman.dist %>/css'
          }, {
            expand: true,
            cwd: 'bower_components/pdfjs-dist/build/',
            src: 'pdf.worker.js',
            dest: '<%= yeoman.dist %>/bower_components/pdfjs-dist/build/'
          }
          , {
            expand: true,
            cwd: 'bower_components/fontawesome',
            src: 'fonts/*',
            dest: '<%= yeoman.dist %>'
          }]
      },
      css: {
        expand: true,
        cwd: '<%= yeoman.app %>/layout/css',
        dest: '.tmp/css/',
        src: '{,*/}*.css'
      },
      flipbook: {
        expand: true,
        cwd: '<%= yeoman.app %>/flipbook',
        dest: '<%= yeoman.dist %>/flipbook/',
        src: '**/*.*'
      }
    },

    // Run some tasks in parallel to speed up the build process
    concurrent: {
      server: [
        'copy:css'
      ],
      test: [
        'copy:css'
      ],
      dist: [
        'copy:css'
      ]
    },

    scp: {
      options: {
        host: 'd-webcacheapp01.pgj.rj.gov.br',
        username: 'thiago.procaci',
        password: ''
        //username: 'linkar',
        //password: 'xeequa7aiB4a'
      },
      yourTarget: {
        files: [{
          cwd: '<%= yeoman.dist %>',
          src: '**/*',
          filter: 'isFile',
          // path on the server
          dest: '/srv/sistema/linkar'
        }]
      }
    },

    replace:{
      local: {
        options: {
          patterns: [{
            json: grunt.file.readJSON('./config/environments/local.json')
          }]
        },
        files: [{
          expand: true,
          flatten: true,
          src: ['./config/app.env.config.js'],
          dest: '<%= yeoman.app %>'
        }]
      },
      dist: {
        options: {
          patterns: [{
            json: grunt.file.readJSON('./config/environments/dist.json')
          }]
        },
        files: [{
          expand: true,
          flatten: true,
          src: ['./config/app.env.config.js'],
          dest: '<%= yeoman.app %>'
        }]
      }
    },

    // unit tests settings
    karma: {
      unit: {
        configFile: 'test/karma.conf.js',
        singleRun: true
      }
    },

    // e2e tests settings
    protractor: {
      options: {
        noColor: false,
        keepAlive: true
      },
      e2e: {   // Grunt requires at least one target to run so you can simply put 'all: {}' here too.
        options: {
          configFile: "test/protractor.conf.js", // Target-specific config file
          args: {} // Target-specific arguments
        }
      }
    },

    instrument: {
      files: 'app/**/*.js',
      options: {
        lazy: true,
        basePath: "<%= yeoman.instrumentedE2E %>"
      }
    },
    protractor_coverage: {
      options: {
        configFile: 'test/protractor.conf.js',
        keepAlive: true,
        noColor: false,
        coverageDir: '<%= yeoman.instrumentedE2E %>',
        args: {
          baseUrl: 'http://localhost:9000'
        },
        run: {}
      },
      local: {
        options: {
          configFile: 'test/protractor.conf.js'
        }
      },
      remote: {
        options: {
          configFile: 'test/protractor.conf.remote.js',
          browser: 'phantomjs'
        }
      }
    },
    makeReport: {
      src: '<%= yeoman.instrumentedE2E %>/*.json',
      options: {
        type: 'lcov',
        dir: 'coverage/e2e/reports',
        print: 'detail'
      }
    }
  });

  grunt.loadNpmTasks('grunt-karma');

  grunt.loadNpmTasks('grunt-protractor-runner');

  grunt.loadNpmTasks('grunt-protractor-coverage');

  grunt.loadNpmTasks('grunt-istanbul');

  grunt.loadNpmTasks('grunt-replace');


  grunt.registerTask('serve', 'Compile then start a connect web server', function (target) {
    if (target === 'dist') {
      return grunt.task.run(['build', 'connect:dist:keepalive']);
    }

    grunt.task.run([
      'clean:server',
      'replace:local',
      'uglify:flipbook_serve',
      'copy:flipbook',
      'wiredep',
      'configureProxies:server',
      'concurrent:server',
      'autoprefixer',
      'connect:livereload',
      'watch'
    ]);
  });


  grunt.registerTask('test', [
    'clean:server',
    'concurrent:test',
    'autoprefixer',
    'connect:test',
    'karma' //,
    //'protractor:e2e'
  ]);

  grunt.registerTask('e2e-test', [
    'instrument',
    'protractor_coverage:local',
    'makeReport'
  ]);

  grunt.registerTask('build', [
    'clean:dist',
    'replace:dist',
    'wiredep',
    'useminPrepare',
    'copy:css',
    'concurrent:dist',
    'autoprefixer',
    'concat',
    'ngAnnotate',
    'copy:dist',
    'cssmin',
    'uglify',
    'copy:flipbook',
    'filerev',
    'usemin',
    'htmlmin',
    'test'
  ]);

  grunt.registerTask('default', [
    'newer:jshint',
    'test',
    'build'
  ]);

  grunt.registerTask('deploy-desenv', [
    'build',
    'scp'
  ]);
};
