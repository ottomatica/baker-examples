# **Baker** Documentation

Welcome to Baker Documentation! 

Meet Baker---the tool that will Bake virtual machines and containers for you. With Baker, you can prepare an Baker environment with a simple configuration file. Edit/code on your machine, and run commands/tasks/services in the Baker environment.

``` yaml
name: baker-docs
container: 
  ports: 8000
lang:
  - python2
commands:
  build: mkdocs build
  serve: mkdocs serve -a 0.0.0.0:8000
  gh-deploy: mkdocs gh-deploy
```

Install [Baker](installation.md) and see how you can [Get Started](getstarted.md)!

<div>
  <script src="https://asciinema.org/a/S3xtkL2FvnINO4IkQCCja5BTX.js" id="asciicast-S3xtkL2FvnINO4IkQCCja5BTX" async data-autoplay="true" data-speed="2" data-loop="1"></script>
<div>